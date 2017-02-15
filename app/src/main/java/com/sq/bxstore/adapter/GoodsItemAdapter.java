package com.sq.bxstore.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.sq.bxstore.BXApplication;
import com.sq.bxstore.GoodsDetailsActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.bean.GoodsInfo;
import com.sq.bxstore.bxBean.GoodsInfoParcelable;
import com.sq.bxstore.net.response.ActivityEventsResponse.GoodsListInfo;
import com.sq.bxstore.net.response.GoodsListResponse.GoodsGeneralData;

/**
 * 商品类别适配器
 * 
 * @author Cool
 * 
 */
public class GoodsItemAdapter extends BaseAdapter {

	private Context context;
	private ImageLoader imageLoader;
	private List<GoodsInfo> products;
	private boolean isShowNum = false;

	public GoodsItemAdapter(Context context, List<GoodsInfo> products) {
		this.context = context;
		this.products = products;
		imageLoader = BXApplication.getInstance().getImageLoader();
	}

	public GoodsItemAdapter(Context context) {
		this.context = context;
		imageLoader = BXApplication.getInstance().getImageLoader();
	}

	public void setData(List<GoodsInfo> products, boolean isShowNum) {
		this.products = products;
		this.isShowNum = isShowNum;
		notifyDataSetChanged();
	}

	public void setDataList(List<GoodsGeneralData> data, boolean isShowNum) {
		this.isShowNum = isShowNum;
		this.products = new ArrayList<GoodsInfo>();
		for (GoodsGeneralData gd : data) {
			GoodsInfo gi = new GoodsInfo();
			gi.setCount(gd.getIsuse());
			gi.setGoodsimg(gd.getGoodimg());
			gi.setGoodsname(gd.getGfullname());
			gi.setPrice(gd.getPrice());
			gi.setGoodsid(gd.getId());
			products.add(gi);
		}
		notifyDataSetChanged();
	}

	/**
	 * 解析Parcelable型的数据
	 */
	public void setParcelableDataList(ArrayList<GoodsInfoParcelable> data,
			boolean isShowNum) {
		this.isShowNum = isShowNum;
		this.products = new ArrayList<GoodsInfo>();
		for (GoodsInfoParcelable gd : data) {
			GoodsInfo gi = new GoodsInfo();
			gi.setCount(gd.getCount());
			gi.setGoodsimg(gd.getGoodsimg());
			gi.setGoodsname(gd.getGoodsname());
			gi.setPrice(gd.getPrice());
			gi.setGoodsid(gd.getGoodsid());
			products.add(gi);
		}
		notifyDataSetChanged();
	}
	
	public void setGoodsListData(List<GoodsListInfo> goodlistdata, boolean b) {
		this.isShowNum = b;
		this.products = new ArrayList<GoodsInfo>();
		for (GoodsListInfo gd : goodlistdata) {
			GoodsInfo gi = new GoodsInfo();
			gi.setCount(0);
			gi.setGoodsimg(gd.getGoodsimg());
			gi.setGoodsname(gd.getGoodsname());
			gi.setPrice(Float.parseFloat(gd.getPrice()));
			gi.setGoodsid(gd.getGoodsid());
			products.add(gi);
		}
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return products == null ? 0 : products.size();
	}

	@Override
	public Object getItem(int position) {
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.view_goodslist_item,
					null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.tv_goods_name);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.iv_goods_pic);
			holder.price = (TextView) convertView
					.findViewById(R.id.tv_goods_price);
			holder.count = (TextView) convertView
					.findViewById(R.id.tv_goods_count);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GoodsInfo og = products.get(position);
		holder.name.setText(og.getGoodsname());
		DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String price= decimalFormat.format(og.getPrice());//format 返回的是字符串
		holder.price.setText(price);
		if (isShowNum && og.getCount() > 0) {
			holder.count.setText("数量   x " + og.getCount());
		}
		imageLoader.get(og.getGoodsimg(), imageLoader.getImageListener(
				holder.icon, R.mipmap.goods_list_item_bg,
				R.mipmap.goods_list_item_bg));
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent goods = new Intent();
				goods.setClass(context, GoodsDetailsActivity.class);
				goods.putExtra("goodsid", og.getGoodsid() + "");
				context.startActivity(goods);
			}
		});
		return convertView;
	}

	private class ViewHolder {
		public TextView count;
		private ImageView icon;
		private TextView name;
		private TextView price;
	}

}
