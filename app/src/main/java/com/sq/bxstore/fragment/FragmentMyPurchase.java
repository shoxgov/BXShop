package com.sq.bxstore.fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.sq.bxstore.PayActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.adapter.ShopItemAdapter;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.bxBean.GoodsInfoParcelable;
import com.sq.bxstore.dialog.AlertDialogs;
import com.sq.bxstore.dialog.AlertDialogs.Alerts;
import com.sq.bxstore.imp.CartProductItemChangedListener;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.HandleShopCarReq;
import com.sq.bxstore.net.request.HandleShopCarReq.HandShopType;
import com.sq.bxstore.net.request.ShopCarReq;
import com.sq.bxstore.net.response.HandleShopCarResponse;
import com.sq.bxstore.net.response.ShopCarResponse;
import com.sq.bxstore.net.response.ShopCarResponse.ShopItemInfo;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.XListView;
import com.sq.bxstore.view.XListView.IXListViewListener;

public class FragmentMyPurchase extends Fragment implements
		CartProductItemChangedListener, NetCallBack, IXListViewListener {
	private Button btn_del_selected;// 删除选中按钮
	private CheckBox allCheck;// 全部选中按钮
	private TextView allSum;// 总计
	private TextView allProductSum;// 商品金额
	private Button iv_buy_product;// 购买商品
	private XListView shopList = null;// 商品列表
	private Map<Integer, ShopItemInfo> unCheckedList = new HashMap<Integer, ShopItemInfo>();

	private ShopItemAdapter shopItemAdapter;

	private List<ShopItemInfo> data = new ArrayList<ShopItemInfo>();
	private boolean checkMark = true;// true变化来自全按钮，false变化来自列表状变化
	private boolean isLoaded = false;
	private View progress;
	private int nowpage = 0;
	private int totalpage = 0;
	private TextView emptyhint;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_shopping_cart, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		isLoaded = false;
		initView();
	}
	
	@Override
	public void onResume() {
		super.onResume();
//		if (!isLoaded) {
			requestShopCar();
//		}
	}

	private void requestShopCar() {
		ShopCarReq req = new ShopCarReq();
		req.setNetCallback(this);
		req.setUserid(UserInfoBean.userId);
		req.setPagesize(Constants.pagesize+"");
		req.setNowpage(""+nowpage);
		req.addRequest();
	}

	private void initView() {
		emptyhint = (TextView) getView().findViewById(
				R.id.lv_cart_emptyhint);
		progress = (View) getView().findViewById(
				R.id.mypurchase_progress);
		btn_del_selected = (Button) getView().findViewById(
				R.id.btn_cart_del_selected);
		allCheck = (CheckBox) getView().findViewById(R.id.cb_cart_all_check);
		allSum = (TextView) getView().findViewById(R.id.tv_cart_all_sum);
		allProductSum = (TextView) getView().findViewById(
				R.id.tv_cart_product_sum);
		iv_buy_product = (Button) getView().findViewById(R.id.iv_cart_buy);
		shopList = (XListView) getView().findViewById(R.id.lv_cart_list);
		shopList.setPullRefreshEnable(false);
		shopList.setXListViewListener(this);
		btn_del_selected.setOnClickListener(clickListener);
		iv_buy_product.setOnClickListener(clickListener);

		allCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				if (checkMark) {
					for (ShopItemInfo cProduct : data) {
						cProduct.setCheck(isChecked);
					}
					if (isChecked) {
						unCheckedList.clear();
					} else {
						for (int i = 0; i < data.size(); i++) {
							unCheckedList.put(i, data.get(i));
						}
					}
					shopItemAdapter.notifyDataSetChanged();
				}
			}
		});

		if (shopItemAdapter == null) {
			shopItemAdapter = new ShopItemAdapter(getActivity(), this);
		}
		shopList.setAdapter(shopItemAdapter);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.btn_cart_del_selected:
				if (unCheckedList.size() < data.size()) {
					Alerts alert = new Alerts() {

						@Override
						public void result(boolean agree) {
							if (agree) {
								isLoaded = false;
								Set<Integer> positions = unCheckedList.keySet();
								/////////////////////////////////
								for (Integer position : positions) {
									data.remove(unCheckedList.get(position));
								}
								int checked = data.size();
								WaitTool.showDialog(getActivity(), "");
								HandleShopCarReq req = new HandleShopCarReq();
								if (checked == 1) {
									WaitTool.showDialog(getActivity());
									req.setNetCallback(FragmentMyPurchase.this);
									req.setUserid(UserInfoBean.userId);
									req.setType(HandShopType.DEL);
									req.setId(data.get(0).getId()+"");
									req.addRequest();
								} else if (checked > 1) {//批量删除时使用 ,分割 形如：{"goodslist":"1,2,3"}
									WaitTool.showDialog(getActivity());
									req.setNetCallback(FragmentMyPurchase.this);
									req.setUserid(UserInfoBean.userId);
									req.setType(HandShopType.DEL);
									String goodslist = "";
									for (int i = 0; i < checked; i++) {
										if (i == checked - 1) {
											goodslist += data.get(i).getId();
										} else {
											goodslist += data.get(i).getId() + ",";
										}
									}
									req.setGoodslist(goodslist);
									req.addRequest();
								}
								/////////////////////////////////
								data.clear();
								for (Integer position : positions) {
									ShopItemInfo cartProduct = unCheckedList.get(position);
									cartProduct.setCheck(false);
									data.add(cartProduct);
								}
								unCheckedList.clear();
								shopItemAdapter.setDat(data);
								allCheck.setChecked(false);
								allSum.setText("0.00");
							}
						}
					};
					AlertDialogs dialog = AlertDialogs.getDialog(getContext());
					String showInfo = "确认删除吗？";
					dialog.setAlert(alert).setMessage(showInfo).show();
				} else {
					ToastTool.showShortBigToast(getActivity(), "请至少选择一件商品");
				}
				break;
			case R.id.iv_cart_buy:
				if (Utils.isFastDoubleClick()) {
					return;
				}
				ArrayList<GoodsInfoParcelable> goodsData = getCheckedGoodsInfo();
				if (goodsData.isEmpty()) {
					ToastTool.showShortBigToast(getActivity(), "请至少选择一件商品");
					return;
				}
				Intent intent = new Intent(getActivity(), PayActivity.class);
				intent.putExtra("source", "myPurchase");
				intent.putParcelableArrayListExtra("data", goodsData);
				startActivity(intent);
				break;

			default:
				break;
			}
		}
	};

	private ArrayList<GoodsInfoParcelable> getCheckedGoodsInfo(){
		ArrayList<GoodsInfoParcelable> goodsData = new ArrayList<GoodsInfoParcelable>();
		if(data == null || data.isEmpty()){
			return goodsData;
		}
		for (ShopItemInfo sii : data) {
			if (sii.isCheck()) {
				GoodsInfoParcelable gip = new GoodsInfoParcelable(
						sii.getCount(), sii.getGoodsid(), sii.getPrice(),
						sii.getImg(), sii.getGoodsname());
				goodsData.add(gip);
			}
		}
		return goodsData;
	}

	/**
	 * 购物车中商品数目改变
	 */
	@Override
	public void itemNumChanged(int position, int num) {
		WaitTool.showDialog(getActivity(), "");
		HandleShopCarReq req = new HandleShopCarReq();
		req.setNetCallback(this);
		req.setUserid(UserInfoBean.userId);
		req.setType(HandShopType.MODIFY);
		req.setCount(num + "");
		req.setId(data.get(position).getId()+"");
		req.setGoodsid(data.get(position).getGoodsid()+"");
		req.addRequest();
	}
	
	@Override
	public void updateSum() {
		float sum = 0;
		for(ShopItemInfo cp : data){
			if(cp.isCheck()){
				sum += cp.getCount() * cp.getPrice();
			}
		}
		DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String price= decimalFormat.format(sum);//format 返回的是字符串
		allSum.setText(price);
	}

	/**
	 * 购物车列表中商品选中状改变
	 */
	@Override
	public void itemCheckChanged(int position, boolean isCheck) {
		if (isCheck) {
			if (unCheckedList.containsKey(position)) {
				unCheckedList.remove(data.get(position));
				unCheckedList.keySet().remove(position);
			}
		} else {
			checkMark = false;
			allCheck.setChecked(false);
			unCheckedList.put(position, data.get(position));
			checkMark = true;
		}
		if (unCheckedList.size() == 0) {
			checkMark = false;
			allCheck.setChecked(true);
			checkMark = true;
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if(baseRes instanceof ShopCarResponse){
			progress.setVisibility(View.GONE);
			shopList.stopRefresh();
			ShopCarResponse scr = (ShopCarResponse) baseRes;
			nowpage = scr.getPage().getCurrentPage();
			totalpage = scr.getPage().getTotalPage();
			if (nowpage >= totalpage || totalpage == 0) {
				shopList.setFooterPullRefresh(false);
			} else {
				shopList.setFooterPullRefresh(true);
			}
			if(scr.getResult().equals("0")){
				data = scr.getData();
				isLoaded = true;
				if(data == null || data.isEmpty()){
					if(nowpage == 0 || nowpage == 1){
						emptyhint.setVisibility(View.VISIBLE);
					}
					return;
				}else{
					emptyhint.setVisibility(View.GONE);
				}
				shopItemAdapter.setDat(data);
			}else{
				ToastTool.showShortBigToast(getActivity(), scr.getMessage());
			}
		}else if(baseRes instanceof HandleShopCarResponse){
			WaitTool.dismissDialog();
//			HandleShopCarResponse hscr = (HandleShopCarResponse) baseRes;
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		if (tag.equals("ShopCarReq")) {
			progress.setVisibility(View.GONE);
		}
	}

	@Override
	public void onHeaderRefresh() {
		
	}

	@Override
	public void onFooterRefresh() {
		if(nowpage < totalpage && totalpage != 0){
			requestShopCar();
		}else{
			shopList.stopRefresh();
		}
	}

}
