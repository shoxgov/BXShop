package com.sq.bxstore.adapter;

import java.util.List;

import android.content.Context;
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

import com.sq.bxstore.R;
import com.sq.bxstore.bean.AddressInfo;
import com.sq.bxstore.net.response.CommentResponse.CommentInfo;

public class MyCriticsmAdapter extends BaseAdapter {
	private List<CommentInfo> list;
	private Context context;

	public MyCriticsmAdapter(Context context) {
		this.context = context;
	}

	public void setData(List<CommentInfo> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public CommentInfo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(
				R.layout.mycriticsm_item, null, false);
		TextView detail = (TextView) convertView
				.findViewById(R.id.address_detail);
		ImageView edit = (ImageView) convertView
				.findViewById(R.id.address_edit);
		final CommentInfo ci = list.get(position);
		convertView.setTag(ci);
		SpannableStringBuilder style = new SpannableStringBuilder("　默认　"
				+ "楝查清町；硒鼓国；国查点李思思战火西北狼");
		style.setSpan(new BackgroundColorSpan(Color.RED), 0, 4,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		detail.setText(style);
		edit.setTag(ci);
		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Object tag = v.getTag();
				if (tag == null || !(tag instanceof AddressInfo)) {
					return;
				}
			}
		});
		return convertView;
	}

}
