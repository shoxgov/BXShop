package com.sq.bxstore;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.sq.bxstore.adapter.MyOrderAdapter;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.MyOrdersReq;
import com.sq.bxstore.net.response.MyOrdersResponse;
import com.sq.bxstore.net.response.MyOrdersResponse.OrderDetail;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;
import com.sq.bxstore.view.XListView;
import com.sq.bxstore.view.XListView.IXListViewListener;

public class PersonMyOrdersActivity extends Activity implements
		TitleBarListener, NetCallBack, IXListViewListener {

	private XListView listView;
	private MyOrderAdapter adapter;
	private List<OrderDetail> orderList;
	private int nowpage = 0;
	private int totalpage = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_personal_list);
		initViews();
		requestMyOrders();
	}

	private void initViews() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("我的订单");
		titleLayout.setTitleBarListener(this);
		listView = (XListView) findViewById(R.id.person_list);
		listView.setPullRefreshEnable(false);
		adapter = new MyOrderAdapter(this);
		listView.setAdapter(adapter);
		listView.setXListViewListener(this);
	}

	private void requestMyOrders() {
		WaitTool.showDialog(this, "");
		MyOrdersReq req = new MyOrdersReq();
		req.setNetCallback(this);
		req.setNowpage(nowpage + "");
		req.setPagesize(Constants.pagesize + "");
		req.setUserid(UserInfoBean.userId);
		req.addRequest();
	}
	
	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {

	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof MyOrdersResponse) {
			WaitTool.dismissDialog();
			listView.stopRefresh();
			MyOrdersResponse mor = (MyOrdersResponse) baseRes;
			if (mor.getResult().equals("0")) {
				nowpage = mor.getPage().getCurrentPage();
				totalpage = mor.getPage().getTotalPage();
				if (nowpage >= totalpage || totalpage == 0) {
					listView.setFooterPullRefresh(false);
				} else {
					listView.setFooterPullRefresh(true);
				}
				orderList = mor.getData();
				if (orderList == null || orderList.isEmpty()) {
					ToastTool.showShortBigToast(this, "没有查询到交易数据");
					adapter.setData(orderList);
					return;
				}
				adapter.setData(orderList);
			} else {
				ToastTool.showShortBigToast(this, mor.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}

	@Override
	public void onHeaderRefresh() {
		
	}

	@Override
	public void onFooterRefresh() {
		if (nowpage < totalpage && totalpage != 0) {
			requestMyOrders();
		} else {
			listView.stopRefresh();
		}
	}

}
