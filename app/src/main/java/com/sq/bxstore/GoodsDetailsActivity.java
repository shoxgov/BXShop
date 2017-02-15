package com.sq.bxstore;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.conversation.EServiceContact;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.bxBean.GoodsInfoParcelable;
import com.sq.bxstore.fragment.GoodsCommentFragment;
import com.sq.bxstore.fragment.GoodsDetailGeneralFragment;
import com.sq.bxstore.fragment.GoodsDetailInfoFragment;
import com.sq.bxstore.imp.TabbarSelectedListener;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.GoodsDetailReq;
import com.sq.bxstore.net.request.HandleShopCarReq;
import com.sq.bxstore.net.request.HandleShopCarReq.HandShopType;
import com.sq.bxstore.net.response.GoodsDetailResponse;
import com.sq.bxstore.net.response.GoodsDetailResponse.GoodsGeneralData;
import com.sq.bxstore.net.response.HandleShopCarResponse;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.GoodsDetailTabbar;

public class GoodsDetailsActivity extends Activity implements
		TabbarSelectedListener, OnClickListener, NetCallBack {

	private GoodsDetailTabbar goodsDetailBar;
	// Fragment管理器
	private FragmentManager manager;
	private GoodsDetailGeneralFragment detailGeneral;
	private GoodsDetailInfoFragment detailInfo;
	private GoodsCommentFragment commentInfo;
	private ImageView iv_buy;
	private ImageView iv_add_cart;
	/**
	 * 保存请求，用于不同页面读取数据
	 */
	protected GoodsDetailResponse detailResponse;
	private String goodsid = "0";
	private ImageView iv_cs;

	public String getGoodsid() {
		return goodsid;
	}
	public GoodsDetailResponse getGoodsDetailResponse(){
		return detailResponse;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goods_details);
		BXApplication.getInstance().loginCS();
		Intent intent = getIntent();
		if(!intent.hasExtra("goodsid")){
			finish();
			return;
		}
		goodsid = intent.getStringExtra("goodsid");
		initView();
		init();
	}

	private void initView() {
		goodsDetailBar = (GoodsDetailTabbar) findViewById(R.id.gls_sort_mode);
		goodsDetailBar.setTabbartSelectedListener(this);
		iv_cs = (ImageView) findViewById(R.id.iv_product_cs);
		iv_buy = (ImageView) findViewById(R.id.iv_product_buy);
		iv_add_cart = (ImageView) findViewById(R.id.iv_product_add_cart);
		// ////////////////
		findViewById(R.id.detail_left_img).setOnClickListener(this);
		iv_cs.setOnClickListener(this);
		iv_buy.setOnClickListener(this);
		iv_add_cart.setOnClickListener(this);
	}

	private void init() {
		manager = getFragmentManager();
		changeFragment(0);
		requestGoodsDetail();
	}

	private void requestGoodsDetail() {
		GoodsDetailReq req = new GoodsDetailReq();
		req.setNetCallback(this);
		req.setGoodsid(goodsid+"");
		req.addRequest();
		WaitTool.showDialog(this, "");
	}

	private void changeFragment(int which) {
		FragmentTransaction ft = manager.beginTransaction();
		if (detailGeneral != null) {
			ft.hide(detailGeneral);
		}
		if (detailInfo != null) {
			ft.hide(detailInfo);
		}
		if (commentInfo != null) {
			ft.hide(commentInfo);
		}
		switch (which) {
		case 0:
			if (detailGeneral == null) {
				detailGeneral = new GoodsDetailGeneralFragment();
				ft.add(R.id.detail_fragment, detailGeneral).commit();
			} else {
				ft.show(detailGeneral).commit();
			}
			break;
		case 1:
			if (detailInfo == null) {
				detailInfo = new GoodsDetailInfoFragment();
				ft.add(R.id.detail_fragment, detailInfo).commit();
			} else {
				ft.show(detailInfo).commit();
			}
			break;
		case 2:
			if (commentInfo == null) {
				commentInfo = new GoodsCommentFragment();
				ft.add(R.id.detail_fragment, commentInfo).commit();
			} else {
				ft.show(commentInfo).commit();
			}
			break;
		}
	}

	/*
	 * 点击商品的大略时回调
	 */
	@Override
	public void GoodsGeneral() {
		changeFragment(0);
	}

	/*
	 * 点击商品的详情时回调
	 */
	@Override
	public void GoodsDetail() {
		changeFragment(1);
	}

	/*
	 * 点击商品的评论时回调
	 */
	@Override
	public void GoodsComment() {
		changeFragment(2);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		"brand":32,
//		 *"detail":"<p><img src=\"http://127.0.0.1:8080/dev/ueditor/jsp/upload/image/20161101/1477982516861023893.jpg\" title=\"1477982516861023893.jpg\"/></p><p><img src=\"http://127.0.0.1:8080/dev/ueditor/jsp/upload/image/20161101/1477982516891089390.png\" title=\"1477982516891089390.png\"/></p><p><br/></p>",
//		 *"gfullname":"三星（SAMSUNG）S24D360HL 23.6英寸PLS广视角窄边框LED背光液晶显示器",
//		 *"gname":"三星（SAMSUNG）S24D360HL 23.6英寸PLS广视角窄边框LED背光液晶显示器",
//		 *"goodimg":"http://127.0.0.1:8080/dev/upload/562b59d4-939c-4502-8565-6be961e575951478160691328.png",
//		 *"id":1,
//		 *"price":799.00,
//		 *"storenumb":0
		case R.id.iv_product_buy:
			if (Utils.isFastDoubleClick() || detailResponse == null
					|| detailResponse.getData() == null
					|| detailResponse.getData().isEmpty()) {
				return;
			}
			GoodsGeneralData gi = detailResponse.getData().get(0);
			GoodsInfoParcelable gip = new GoodsInfoParcelable(detailGeneral.getShopCount(), gi.getId(),
					gi.getPrice().floatValue(), gi.getGoodimg(), gi.getGfullname());
			ArrayList<GoodsInfoParcelable> data = new ArrayList<GoodsInfoParcelable>();
			data.add(gip);
			Intent intent = new Intent(this, PayActivity.class);
			intent.putExtra("source", "goods");
			intent.putParcelableArrayListExtra("data", data);
			startActivity(intent);
			break;
		case R.id.iv_product_add_cart:
			if(Utils.isFastDoubleClick()){
				return;
			}
			WaitTool.showDialog(this);
			HandleShopCarReq req = new HandleShopCarReq();
			req.setNetCallback(this);
			req.setType(HandShopType.ADD);
			req.setCount(detailGeneral.getShopCount()+"");
			req.setUserid(UserInfoBean.userId);
			req.setGoodsid(goodsid+"");
			req.addRequest();
			break;
			
		case R.id.iv_product_cs:
			//获取的是会话列表
//			Intent intent =mIMKit.getConversationActivityIntent();
//			startActivity(intent);
			//此对象获取到后，保存为全局对象，供APP使用
			//此对象跟用户相关，如果切换了用户，需要重新获取
			//第一个参数就是淘宝客服账号，第二个参数是客服分组ID，快速集成环节建议分组ID设置0。
			EServiceContact contact = new EServiceContact(Constants.CS_NAME, 0);
			YWIMKit mIMKit = YWAPI.getIMKitInstance(Constants.CS_PREFIX
					+ UserInfoBean.userId, Constants.CS_APP_KEY);
			Intent cs = mIMKit.getChattingActivityIntent(contact);
			cs.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			startActivity(cs);
			break;
			
		case R.id.detail_left_img:
			finish();
			break;
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (isFinishing()) {
			WaitTool.dismissDialog();
			return;
		}
		if (baseRes instanceof GoodsDetailResponse) {
			WaitTool.dismissDialog();
			GoodsDetailResponse gdr = (GoodsDetailResponse) baseRes;
			if(gdr.getResult().equals("0")){
				detailResponse = gdr;
				if (detailGeneral != null) {
					detailGeneral.updateInfo(gdr);
				}
				if (detailInfo != null) {
					detailInfo.updateInfo(detailResponse);
				}
			}else{
				Toast.makeText(this, "GoodsDetailResponse fail", Toast.LENGTH_SHORT)
				.show();
			}
		}else if(baseRes instanceof HandleShopCarResponse){
			WaitTool.dismissDialog();
			HandleShopCarResponse smr = (HandleShopCarResponse) baseRes;
			if(smr.getResult().equals("0")){
				ToastTool.showShortBigToast(this, smr.getMessage());
			}else{
				ToastTool.showShortBigToast(this, smr.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		if (tag.equals("GoodsDetailReq")) {
			WaitTool.dismissDialog();
		}
	}

	/**
	 * @param 其它页面调用页面切换点击事件
	 */
	public void callOnclick(int id) {
		goodsDetailBar.onClick(id);
	}

}
