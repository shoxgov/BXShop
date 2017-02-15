/**  
 * @Title: CollisionAdapter.java
 * @date: 2015-11-23 下午3:59:04
 * @Copyright: (c) 2015, unibroad.com Inc. All rights reserved.
 */
package com.sq.bxstore.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.bean.FirstCateBean;

/**
 * @version: V1.0
 */
public class SortListViewAdapter extends BaseAdapter {
	private List<FirstCateBean> mList;
	private Context mContext;
	private int selectedTag = 0;

	public SortListViewAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	/**
	 * @param mArrayList
	 * @Description:
	 */
	public void setData(List<FirstCateBean> mList) {
		this.mList = mList;
		if (mList == null) {
			return;
		}
		notifyDataSetChanged();
	}

	public void setSelectedTag(int position) {
		this.selectedTag = position;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (mList == null) {
			return 0;
		} else {
			return this.mList.size();
		}
	}

	@Override
	public Object getItem(int position) {
		if (mList == null) {
			return null;
		} else {
			return this.mList.get(position);
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
//		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(this.mContext).inflate(
					R.layout.sort_adapter_item, null, false);
			holder.title = (TextView) convertView
					.findViewById(R.id.listview_sort_item_name);
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}

		final FirstCateBean ci = this.mList.get(position);
		convertView.setTag(ci);
		holder.title.setText(ci.getCateName());
		if (selectedTag == position) {
			convertView.setBackgroundResource(R.color.white);
		} else {
			convertView.setBackgroundResource(R.color.mainbg);
		}
		return convertView;
	}

	private class ViewHolder {
		TextView title;
	}

}