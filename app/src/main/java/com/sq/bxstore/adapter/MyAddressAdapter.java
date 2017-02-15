package com.sq.bxstore.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sq.bxstore.AddressEditActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.bean.AddressInfo;

public class MyAddressAdapter extends BaseAdapter {
	private List<AddressInfo> list = new ArrayList<AddressInfo>();
	private Activity activity;

	public MyAddressAdapter(Activity activity) {
		this.activity = activity;
	}

	public void setData(List<AddressInfo> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public AddressInfo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(activity).inflate(
				R.layout.address_normal_item, null, false);
		ImageView tag = (ImageView) convertView
				.findViewById(R.id.address_tag);
		TextView name = (TextView) convertView
				.findViewById(R.id.address_name);
		TextView tel = (TextView) convertView
				.findViewById(R.id.address_tel);
		TextView detail = (TextView) convertView
				.findViewById(R.id.address_detail);
		ImageView edit = (ImageView) convertView
				.findViewById(R.id.address_edit);
		final AddressInfo ab = list.get(position);
		convertView.setTag(ab);
		if (ab.getIsuse() == 1) {
			SpannableStringBuilder style = new SpannableStringBuilder("　默认   "
					+ ab.getAddress());
			style.setSpan(new BackgroundColorSpan(Color.RED), 0, 4,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			detail.setText(style);
			tag.setVisibility(View.VISIBLE);
		} else {
			detail.setText(ab.getAddress());
			tag.setVisibility(View.INVISIBLE);
		}
		name.setText(ab.getName());
		tel.setText(ab.getPhonenumb());
		edit.setTag(ab);
		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Object tag = v.getTag();
				if (tag == null || !(tag instanceof AddressInfo)) {
					return;
				}
				AddressInfo ai = (AddressInfo) tag;
				Intent intent = new Intent();
				intent.setClass(activity, AddressEditActivity.class);
				intent.putExtra("mode", "edit");
				intent.putExtra("id", ai.getId()+"");
				intent.putExtra("name", ai.getName());
				intent.putExtra("tel", ai.getPhonenumb());
				intent.putExtra("detail", ai.getAddress());
				intent.putExtra("isuse", ai.getIsuse());
				activity.startActivityForResult(intent, 22);
			}
		});
		return convertView;
	}

}
