package com.sq.bxstore.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sq.bxstore.GoodsDetailsActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.net.response.CommentResponse.CommentInfo;
import com.sq.bxstore.utils.Utils;

public class CommentAdapter extends BaseAdapter {
	private List<CommentInfo> list = new ArrayList<CommentInfo>();;
	private Context context;
	/**
	 * 是否需要跳转
	 */
	private boolean isGoto= false;

	public CommentAdapter(Context context) {
		this.context = context;
	}

	public void setData(List<CommentInfo> list, boolean isGoto) {
		this.list.addAll(list);
		this.isGoto = isGoto;
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
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.comment_item, null, false);
			viewHolder.username = (TextView) convertView
					.findViewById(R.id.comment_username);
			viewHolder.date = (TextView) convertView
					.findViewById(R.id.comment_time);
			viewHolder.rating = (RatingBar) convertView
					.findViewById(R.id.comment_rating);
			viewHolder.content = (TextView) convertView
					.findViewById(R.id.comment_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final CommentInfo ci = list.get(position);
		String name = ci.getUserid()+"转昵称";
		if (ci.getIshidden().equals("1")) {// 是否匿名 1：匿 名；
			viewHolder.username.setText(Html.fromHtml(name.charAt(0) + "***"
					+ name.charAt(name.length() - 1)
					+ "<font color=\"#c0c0c0\">( 匿名 )</font>"));
		} else {
			viewHolder.username.setText(name);
		}
		// mill为你龙类型的时间戳
//		Date date = new Date(ci.getCreateTime());
//		String strs = "";
//		try {
//			// yyyy表示年MM表示月dd表示日
//			// yyyy-MM-dd是日期的格式，比如2015-12-12如果你要得到2015年12月12日就换成yyyy年MM月dd日
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			// 进行格式化
//			strs = sdf.format(date);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		viewHolder.date.setText(ci.getCreateTime());
		viewHolder.rating.setRating(ci.getScore());
		viewHolder.content.setText(ci.getComment());
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(Utils.isFastDoubleClick() || !isGoto){
					return;
				}
				Intent goods = new Intent();
				goods.setClass(context, GoodsDetailsActivity.class);
				goods.putExtra("goodsid", ci.getGoodsid()+"");
				context.startActivity(goods);
			}
		});
		return convertView;
	}

	private class ViewHolder {

		public RatingBar rating;
		public TextView content;
		public TextView date;
		public TextView username;

	}
}
