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
import com.sq.bxstore.bean.WalletHistoryBean;

public class WalletHistoryAdapter extends BaseAdapter {
	private List<WalletHistoryBean> list = new ArrayList<WalletHistoryBean>();;
	private Context context;

	public WalletHistoryAdapter(Context context) {
		this.context = context;
	}

	public void setData(List<WalletHistoryBean> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public WalletHistoryBean getItem(int position) {
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
					R.layout.wallethistory_item, null, false);
			viewHolder.mode = (TextView) convertView
					.findViewById(R.id.wallethistory_mode);
			viewHolder.price = (TextView) convertView
					.findViewById(R.id.wallethistory_price);
			viewHolder.date = (TextView) convertView
					.findViewById(R.id.wallethistory_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final WalletHistoryBean ci = list.get(position);
		return convertView;
	}

	private class ViewHolder {

		public TextView price;
		public TextView mode;
		public TextView date;

	}
}
