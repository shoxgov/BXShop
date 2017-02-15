/**  
 * @Title: GridViewAdapter.java
 * @date: 2015-11-23 下午4:01:40
 * @Copyright: (c) 2015, unibroad.com Inc. All rights reserved.
 */
package com.sq.bxstore.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.sq.bxstore.BXApplication;
import com.sq.bxstore.GoodsListActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.net.response.AllClassificationResponse.SingleCateInfo;

/**
 * @Class: GridViewAdapter
 * @Package: com.unibroad.carphone.adapter
 * @Description: TODO(描述类作用)
 * @author: wsy@unibroad.com
 * @version: V1.0
 */
public class GridViewAdapter extends BaseAdapter {
	private Context mContext;
	private List<SingleCateInfo> mList;
	public static String selectedTarget = "";
	private ImageLoader imageLoader;

	public GridViewAdapter(Context mContext) {
		super();
		selectedTarget = "";
		this.mContext = mContext;
		imageLoader = BXApplication.getInstance().getImageLoader();
	}

	public void setData(List<SingleCateInfo> mList) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(this.mContext).inflate(
					R.layout.gridview_item, null, false);
			holder.img = (ImageView) convertView
					.findViewById(R.id.gridview_item_img);
			holder.name = (TextView) convertView
					.findViewById(R.id.gridview_item_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final SingleCateInfo sci = mList.get(position);
		holder.img.setTag(sci);
		String url = sci.getUrl();
		if (TextUtils.isEmpty(url)) {
			holder.img.setBackgroundResource(R.mipmap.loadfail);
		} else {
			ImageListener listener = imageLoader.getImageListener(holder.img,
					R.mipmap.goods_list_item_bg, R.mipmap.goods_list_item_bg);
			imageLoader.get(url, listener);
		}
		holder.name.setText(sci.getCateName());
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int id = -1;
				String titleName ="";
				try {
					ViewHolder viewHolder = ((ViewHolder) v.getTag());
					SingleCateInfo sci = (SingleCateInfo) viewHolder.img
							.getTag();
					id = sci.getId();
					titleName = sci.getCateName();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (id == -1) {
					return;
				}
				Intent it = new Intent();
				it.setClass(mContext, GoodsListActivity.class);
				it.putExtra("type", "classify");
				it.putExtra("param", "" + id);
				it.putExtra("title", titleName);
				mContext.startActivity(it);
			}
		});
		return convertView;
	}

	private class ViewHolder {
		public TextView name;
		ImageView img;
	}
}