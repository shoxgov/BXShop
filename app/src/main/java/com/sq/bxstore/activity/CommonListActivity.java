package com.sq.bxstore.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.sq.bxstore.R;
import com.sq.bxstore.adapter.CouponAdapter;
import com.sq.bxstore.adapter.WalletHistoryAdapter;
import com.sq.bxstore.bean.CouponInfo;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.bean.WalletHistoryBean;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.CouponHistoryReq;
import com.sq.bxstore.net.request.RebateHistoryReq;
import com.sq.bxstore.net.request.WalletHistoryReq;
import com.sq.bxstore.net.response.CouponHistoryResponse;
import com.sq.bxstore.net.response.RebateHistoryResponse;
import com.sq.bxstore.net.response.WalletHistoryResponse;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

public class CommonListActivity extends Activity implements NetCallBack {

	private ListView listView;
	private String comesource;
	private CouponAdapter couponAdapter;
	private TitleBarLayout titleBar;
	private WalletHistoryAdapter walletHistoryAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_list);
		Intent intent = getIntent();
		if (!intent.hasExtra("comesource")) {
			finish();
			return;
		}
		comesource = intent.getStringExtra("comesource");
		initView();
		init();
	}

	private void init() {
		if (comesource.equals("coupon")) {
			couponAdapter = new CouponAdapter(this);
			listView.setAdapter(couponAdapter);
			titleBar.setTitle("我的优惠券");
			CouponHistoryReq req = new CouponHistoryReq();
			req.setNetCallback(this);
			req.setRequestType(RequestType.POST);
			req.setUsername(UserInfoBean.userName);
			req.addRequest();
		} else if (comesource.equals("recharge")) {
			titleBar.setTitle("充值记录");
			walletHistoryAdapter = new WalletHistoryAdapter(this);
			listView.setAdapter(walletHistoryAdapter);
			WalletHistoryReq req = new WalletHistoryReq();
			req.setNetCallback(this);
			req.setRequestType(RequestType.POST);
			req.setUsername(UserInfoBean.userName);
			req.setPassword(UserInfoBean.password);
			req.addRequest();
		} else if (comesource.equals("rebate")) {
			titleBar.setTitle("返利明细");
			RebateHistoryReq req = new RebateHistoryReq();
			req.setNetCallback(this);
			req.setRequestType(RequestType.POST);
			req.setUsername(UserInfoBean.userName);
			req.setPurse_type("1");//积分0、分红1、可用余额2(充值金额消费记录)
			req.addRequest();
		}
	}

	private void initView() {
		titleBar = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleBar.setTitleBarListener(new TitleBarListener() {

			@Override
			public void rightClick() {
			}

			@Override
			public void leftClick() {
				finish();
			}
		});
		listView = (ListView) findViewById(R.id.lv_type_list);
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if(baseRes instanceof WalletHistoryResponse){
			WalletHistoryResponse whr = (WalletHistoryResponse) baseRes;
			List<WalletHistoryBean> whb = new ArrayList<WalletHistoryBean>();
			whb.add(null);
			whb.add(null);
			whb.add(null);
			whb.add(null);
			if (comesource.equals("recharge")) {
				walletHistoryAdapter.setData(whb);
			} else if (comesource.equals("rebate")) {
				walletHistoryAdapter.setData(whb);
			}
		} else if(baseRes instanceof CouponHistoryResponse){
			CouponHistoryResponse chr = (CouponHistoryResponse) baseRes;
			if(chr.getResult().equals("0")){
				List<CouponInfo> list = chr.getData();
				if(list == null || list.isEmpty()){
					ToastTool.showShortBigToast(this, "你当前没有优惠券！");
					return;
				}
				couponAdapter.setData(list);
			}else{
				ToastTool.showShortBigToast(this, "查询失败");
			}
		} else if(baseRes instanceof RebateHistoryResponse){
			RebateHistoryResponse rhr = (RebateHistoryResponse) baseRes;
			if(rhr.getResult().equals("0")){
//				List<CouponInfo> list = chr.getData();
//				if(list == null || list.isEmpty()){
//					ToastTool.showShortBigToast(this, "你当前没有优惠券！");
//					return;
//				}
//				couponAdapter.setData(list);
//			}else{
				ToastTool.showShortBigToast(this, "查询失败");
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		
	}

}
