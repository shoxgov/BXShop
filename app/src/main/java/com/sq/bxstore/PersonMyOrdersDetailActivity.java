package com.sq.bxstore;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sq.bxstore.adapter.GoodsItemAdapter;
import com.sq.bxstore.adapter.MyOrderAdapter;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.AccountPayReq;
import com.sq.bxstore.net.request.GeneralOrderReq;
import com.sq.bxstore.net.response.AccountPayResponse;
import com.sq.bxstore.net.response.GeneralOrderResponse;
import com.sq.bxstore.net.response.MyOrdersResponse.OrderDetail;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.ListViewUtils;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

public class PersonMyOrdersDetailActivity extends Activity implements
		TitleBarListener, OnClickListener, NetCallBack {

	private ListView listView;
	private GoodsItemAdapter adapter;
	private TextView id;
	private TextView status;
	private RelativeLayout address_layout;
	private TextView address_name;
	private TextView address_tel;
	private TextView address_info;
	private TextView pay_type;
	private TextView transport_info;
	private TextView receipt_status;
	private TextView goods_total;
	private TextView commission;
	private TextView total;
	private TextView time;
	private TextView confirm;
	private OrderDetail orderDetail;
	/**
	 * 1：待付款；2：已付款；3：已发货；0：已取消
	 */
	private int orderStatus = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_myorder_detail);
		initViews();
		init();
	}

	private void initViews() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("订单详情");
		titleLayout.setTitleBarListener(this);
		id = (TextView) findViewById(R.id.orderdetail_id);
		status = (TextView) findViewById(R.id.orderdetail_status);
		address_layout = (RelativeLayout) findViewById(R.id.orderdetail_address_layout);
		address_name = (TextView) findViewById(R.id.orderdetail_address_name);
		address_tel = (TextView) findViewById(R.id.orderdetail_address_tel);
		address_info = (TextView) findViewById(R.id.orderdetail_address_info);
		pay_type = (TextView) findViewById(R.id.orderdetail_pay_type);
		transport_info = (TextView) findViewById(R.id.orderdetail_transport_info);
		receipt_status = (TextView) findViewById(R.id.orderdetail_receipt_status);
		goods_total = (TextView) findViewById(R.id.orderdetail_goods_total);
		commission = (TextView) findViewById(R.id.orderdetail_commission);
		total = (TextView) findViewById(R.id.orderdetail_total);
		time = (TextView) findViewById(R.id.orderdetail_time);
		confirm = (TextView) findViewById(R.id.orderdetail_del);
		listView = (ListView) findViewById(R.id.orderdetail_list);
		adapter = new GoodsItemAdapter(this);
		listView.setAdapter(adapter);
		confirm.setOnClickListener(this);
	}

	private void init() {
		orderDetail = BXApplication.getInstance().getOrderDetail();
		orderStatus = orderDetail.getOrderstatus();
		adapter.setData(orderDetail.getGoodsdetail(),true);
		id.setText("订单号："+orderDetail.getId());
		address_name.setText(orderDetail.getName());
		address_tel.setText(orderDetail.getContactnumb());
		address_info.setText(orderDetail.getAddress());
		pay_type.setText(orderDetail.getPaytype()+"");
		if (TextUtils.isEmpty(orderDetail.getDeliveryNumb())) {
			transport_info.setText("包邮");
		} else {
			transport_info.setText(orderDetail.getDeliveryNumb());
		}
		receipt_status.setText(orderDetail.getCompanyname());
		goods_total.setText(Html.fromHtml(Constants.RMB+orderDetail.getAccountAmount()));
		commission.setText(orderDetail.getCommisionCharge()+"");
		total.setText(Html.fromHtml("实付金额：<font color=red>&#160;&#165;"
				+ orderDetail.getTotal() + "</font>"));
		time.setText("下单时间："+orderDetail.getCreateTime());
		adapter.setData(orderDetail.getGoodsdetail(),true);
		ListViewUtils.setListViewHeightBasedOnChildren(listView);
		switch (orderStatus) {
		case 1:// 待付款
			status.setText("待付款");
			confirm.setText("去支付");
			break;
		case 2:// 已付款
			status.setText("已付款");
			break;
		case 3:// 已发货
			status.setText("已发货");
			break;
		case 0:// 已取消
		default:
			status.setText("已取消");
			break;
		}
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.orderdetail_del:
			if(Utils.isFastDoubleClick()){
				return;
			}
			if (orderStatus == 1) {//去支付
				final Dialog mCameraDialog = new Dialog(this, R.style.shareDialogTheme);  
	            LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(  
	                    R.layout.popwindow_dialog, null);  
	            TextView totalTV = (TextView) root.findViewById(R.id.btn_pay_total);
	            totalTV.setText(orderDetail.getTotal()+"元");
	            root.findViewById(R.id.btn_pay_ok).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						WaitTool.showDialog(PersonMyOrdersDetailActivity.this);
						AccountPayReq req = new AccountPayReq();
						req.setNetCallback(PersonMyOrdersDetailActivity.this);
						req.setUserid(UserInfoBean.userId);
						req.setOrderid(orderDetail.getId()+"");
						req.addRequest();
						mCameraDialog.dismiss();
					}
				});  
	            root.findViewById(R.id.btn_pay_cancel).setOnClickListener(new OnClickListener() {
	            	
	            	@Override
	            	public void onClick(View v) {
	            		mCameraDialog.dismiss();
	            	}
	            });  
	            mCameraDialog.setContentView(root);  
	            Window dialogWindow = mCameraDialog.getWindow();  
	            dialogWindow.setGravity(Gravity.BOTTOM);  
	            dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画  
	            WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值  
	            lp.x = 0; // 新位置X坐标  
	            lp.y = -20; // 新位置Y坐标  
	            lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度  
	    //      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度  
	    //      lp.alpha = 9f; // 透明度  
	            root.measure(0, 0);  
	            lp.height = root.getMeasuredHeight();  
	            lp.alpha = 9f; // 透明度  
	            dialogWindow.setAttributes(lp);  
	            mCameraDialog.setCanceledOnTouchOutside(false);
	            mCameraDialog.show();  
			} else {
				GeneralOrderReq req = new GeneralOrderReq();
				req.setNetCallback(this);
				req.setType("1");// // 0新增 1删除
				req.setOrderid(orderDetail.getId() + "");
				req.setUserid(UserInfoBean.userId);
				req.addRequest();
			}
			break;
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if(baseRes instanceof GeneralOrderResponse){
			GeneralOrderResponse gor = (GeneralOrderResponse) baseRes;
			if (gor.getResult().equals("0")) {
				ToastTool.showShortBigToast(this, gor.getMessage());
				finish();
			} else {
				ToastTool.showShortBigToast(this, gor.getMessage());
			}
		} else if (baseRes instanceof AccountPayResponse) {
			AccountPayResponse apr = (AccountPayResponse) baseRes;
			if (apr.getResult().equals("0")) {
				orderStatus = 2;// 已付款
				status.setText("已付款");
				confirm.setText("删除订单");
			} else {
				ToastTool.showShortBigToast(this, apr.getMessage());
			}
			WaitTool.dismissDialog();
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		
	}
}
