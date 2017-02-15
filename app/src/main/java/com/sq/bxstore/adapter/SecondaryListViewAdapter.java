/**  
 * @Title: CollisionAdapter.java
 * @date: 2015-11-23 下午3:59:04
 * @Copyright: (c) 2015, unibroad.com Inc. All rights reserved.
 */
package com.sq.bxstore.adapter;

import java.util.List;
import java.util.WeakHashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.bean.SecondaryCateBean;
import com.sq.bxstore.view.MyGridView;

/**
 * @Class: CollisionAdapter
 * @Package: com.unibroad.carphone.adapter
 * @Description: TODO(描述类作用)
 * @author: wsy@unibroad.com
 * @version: V1.0
 */
public class SecondaryListViewAdapter extends BaseAdapter {
	private List<SecondaryCateBean> mList;
	private Context mContext;
	private WeakHashMap<String, GridViewAdapter> gridviewHashmap = new WeakHashMap<String, GridViewAdapter>();

	public SecondaryListViewAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		gridviewHashmap.clear();
	}

	/**
	 * @param mArrayList
	 * @Description:
	 */
	public void setData(List<SecondaryCateBean> mList) {
		gridviewHashmap.clear();
		if (mList == null) {
			return;
		}
		this.mList = mList;
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
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(this.mContext).inflate(
					R.layout.secondarysort_adapter_item, null, false);
			holder.title = (TextView) convertView
					.findViewById(R.id.listview_item_title);
			holder.gridView = (MyGridView) convertView
					.findViewById(R.id.listview_item_gridview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final SecondaryCateBean scb = this.mList.get(position);
		holder.title.setText(scb.getCateName());
		GridViewAdapter gridViewAdapter = null;
		if (gridviewHashmap.containsKey(scb.getId()+"")) {
			gridViewAdapter = gridviewHashmap.get(scb.getId()+"");
			gridViewAdapter.setData(scb.getThirdCateList());
			holder.gridView.setAdapter(gridViewAdapter);
		} else {
			gridViewAdapter = new GridViewAdapter(mContext);
			gridViewAdapter.setData(scb.getThirdCateList());
			gridviewHashmap.put(scb.getId()+"", gridViewAdapter);
			holder.gridView.setAdapter(gridViewAdapter);
		}
//		ListViewUtils.setListViewHeightBasedOnChildren(holder.gridView);
		return convertView;
	}

	private class ViewHolder {
		TextView title;
		MyGridView gridView;
	}

}