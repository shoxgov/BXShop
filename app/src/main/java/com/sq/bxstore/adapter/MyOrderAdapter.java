package com.sq.bxstore.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sq.bxstore.BXApplication;
import com.sq.bxstore.PersonMyOrdersDetailActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.bean.GoodsInfo;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.dialog.AlertDialogs;
import com.sq.bxstore.dialog.AlertDialogs.Alerts;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.GeneralOrderReq;
import com.sq.bxstore.net.response.GeneralOrderResponse;
import com.sq.bxstore.net.response.MyOrdersResponse.OrderDetail;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.ListViewUtils;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.utils.WaitTool;

public class MyOrderAdapter extends BaseAdapter implements NetCallBack {
	private List<OrderDetail> list = new ArrayList<OrderDetail>();
	private Context context;
	private HashMap<String, OrderGoodlistAdapter> adapterMap = new HashMap<String, OrderGoodlistAdapter>();
	/**
	 * 用来记录删除时的位置
	 */
	private int deleteId = -1;

	public MyOrderAdapter(Context context) {
		this.context = context;
	}

	public void setData(List<OrderDetail> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public OrderDetail getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.myorder_item, null, false);
			viewHolder.orderid = (TextView) convertView
					.findViewById(R.id.myorder_item_id);
			viewHolder.status = (TextView) convertView
					.findViewById(R.id.myorder_item_status);
			viewHolder.del = (ImageView) convertView
					.findViewById(R.id.myorder_item_del);
			viewHolder.goodslist = (ListView) convertView
					.findViewById(R.id.myorder_goodslist);
			viewHolder.detail = (TextView) convertView
					.findViewById(R.id.myorder_item_detail);
			viewHolder.tishi = (TextView) convertView
					.findViewById(R.id.myorder_item_tishi);
			viewHolder.total = (TextView) convertView
					.findViewById(R.id.myorder_item_total);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final OrderDetail ab = list.get(position);
		boolean isShowComment = false;
		switch (ab.getOrderstatus()) {
		case 1:// 待付款
			viewHolder.status.setText("待付款");
			break;
		case 2:// 已付款
			viewHolder.status.setText("已付款");
			break;
		case 3:// 已发货
			viewHolder.status.setText("已发货");
			isShowComment = true;
			break;
		case 0:// 已取消
		default:
			viewHolder.status.setText("已取消");
			break;
		}
		viewHolder.orderid.setText("订单ID："+ab.getId());
		OrderGoodlistAdapter adapter;
		String tag = "goodslistadapter"+position;
		if(adapterMap.containsKey(tag)){
			adapter = adapterMap.get(tag);
		}else{
			adapter = new OrderGoodlistAdapter(context);
			adapterMap.put(tag, adapter);
		}
		viewHolder.goodslist.setAdapter(adapter);
		List<GoodsInfo> goodsDetail = ab.getGoodsdetail();
		adapter.setData(goodsDetail, isShowComment);
		if (goodsDetail != null && !goodsDetail.isEmpty()) {
			ListViewUtils
					.setListViewHeightBasedOnChildren(viewHolder.goodslist);
		}
		viewHolder.tishi.setText(ab.getGoodsdetail().size() + "件商品");
		viewHolder.total.setText(Html.fromHtml(Constants.RMB+ab.getTotal()));
		viewHolder.del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(Utils.isFastDoubleClick()){
					return;
				}
				if(ab.getOrderstatus() != 0){
					return;
				}
				 Alerts alert = new Alerts() {

						@Override
		                public void result(boolean agree) {
		                    if (agree) {
		                    	deleteId = position;
		                    	WaitTool.showDialog(context);
		                    	GeneralOrderReq req = new GeneralOrderReq();
		                    	req.setNetCallback(MyOrderAdapter.this);
		                    	req.setType("1");//// 0新增 1删除 
		                    	req.setOrderid(ab.getId()+"");
		                    	req.setUserid(UserInfoBean.userId);
		                    	req.addRequest();
		                    }
		                }
		            };
				AlertDialogs dialog = AlertDialogs.getDialog(context);
				String showInfo = "确认取消定单吗？";
				dialog.setAlert(alert)
						.setTitle(context.getString(R.string.title_notify))
						.setMessage(showInfo).show();
			}
		});
		viewHolder.detail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(Utils.isFastDoubleClick()){
					return;
				}
				BXApplication.getInstance().setOrderDetail(ab);
				Intent intent = new Intent();
				intent.setClass(context, PersonMyOrdersDetailActivity.class);
				intent.putExtra("orderid", ab.getId());
				context.startActivity(intent);
			}
		});
		return convertView;
	}
	
	private class ViewHolder {

		public TextView orderid;
		public TextView detail;
		public ListView goodslist;
		public TextView status;
		public TextView tishi;
		public TextView total;
		public ImageView del;

	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if(baseRes instanceof GeneralOrderResponse){
			GeneralOrderResponse gor = (GeneralOrderResponse) baseRes;
			WaitTool.dismissDialog();
			if (gor.getResult().equals("0")) {
				if (deleteId >= 0) {
//					list.remove(deleteId);
//					notifyDataSetChanged();
				}
				deleteId = -1;
				ToastTool.showShortBigToast(context, "订单已取消~");
			} else {
				ToastTool.showShortBigToast(context, gor.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		
	}

}
