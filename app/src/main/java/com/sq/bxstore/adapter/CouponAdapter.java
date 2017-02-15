package com.sq.bxstore.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.bean.CouponInfo;

public class CouponAdapter extends BaseAdapter {
	private List<CouponInfo> list = new ArrayList<CouponInfo>();;
	private Context context;

	public CouponAdapter(Context context) {
		this.context = context;
	}

	public void setData(List<CouponInfo> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public CouponInfo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.coupon_item, null, false);
			viewHolder.price = (TextView) convertView.findViewById(R.id.coupon_item_price);
			viewHolder.title = (TextView) convertView.findViewById(R.id.coupon_item_title);
			viewHolder.date = (TextView) convertView.findViewById(R.id.coupon_item_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final CouponInfo ci = list.get(position);
		viewHolder.price.setText(ci.getCouponBlance()+"");
		viewHolder.title.setText(ci.getCouponName());
		viewHolder.date.setText(ci.getCouponCreatdate()+"-"+ci.getCouponExpirydate());
		return convertView;
	}

	private class ViewHolder {

		public TextView title;
		public TextView date;
		public TextView price;

	}
}
