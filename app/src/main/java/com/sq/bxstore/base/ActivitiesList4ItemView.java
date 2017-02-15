package com.sq.bxstore.base;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.sq.bxstore.BXApplication;
import com.sq.bxstore.GoodsDetailsActivity;
import com.sq.bxstore.GoodsListActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.WebActivity;
import com.sq.bxstore.bean.AdvertsBean;

public class ActivitiesList4ItemView extends
		ActivitiesListItemStyleViewBase {

	private List<AdvertsBean> ads;
	private String catalogName;
	private ImageLoader imageLoader;

	public ActivitiesList4ItemView(String catalogName,
			List<AdvertsBean> ads) {
		this.catalogName = catalogName;
		this.ads = ads;
	}

	@Override
	public View getView(int position, final Context context, View convertView) {
		if (imageLoader == null) {
			imageLoader = BXApplication.getInstance().getImageLoader();
		}
		convertView = LayoutInflater.from(context).inflate(
				R.layout.list_itemview_colum, null);
		TextView catalogNameTv = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_title);
		TextView name1Tv_1_1 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_1_1);
		TextView name2Tv_1_1 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_1_1);
		TextView name1Tv_1_2 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_1_2);
		TextView name2Tv_1_2 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_1_2);
		TextView name1Tv_1_3 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_1_3);
		TextView name2Tv_1_3 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_1_3);
		TextView name1Tv_2_1 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_2_1);
		TextView name2Tv_2_1 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_2_1);
		TextView name1Tv_2_2 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_2_2);
		TextView name2Tv_2_2 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_2_2);
		TextView name1Tv_2_3 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_2_3);
		TextView name2Tv_2_3 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_2_3);
		TextView name1Tv_3_1 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_3_1);
		TextView name2Tv_3_1 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_3_1);
		TextView name1Tv_3_2 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_3_2);
		TextView name2Tv_3_2 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_3_2);
		TextView name1Tv_3_3 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name1_3_3);
		TextView name2Tv_3_3 = (TextView) convertView
				.findViewById(R.id.itemstyle_colum_name2_3_3);
		// /////////////////////////////////////////////
		ImageView iv_1_1 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_1_1);
		ImageView iv_1_2 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_1_2);
		ImageView iv_1_3 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_1_3);
		ImageView iv_2_1 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_2_1);
		ImageView iv_2_2 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_2_2);
		ImageView iv_2_3 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_2_3);
		ImageView iv_3_1 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_3_1);
		ImageView iv_3_2 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_3_2);
		ImageView iv_3_3 = (ImageView) convertView
				.findViewById(R.id.itemstyle_colum_img_3_3);
		convertView.findViewById(R.id.itemstyle_colum_layout_1_0)
				.setOnClickListener(new ImageClickListener(context, 0));
		convertView.findViewById(R.id.itemstyle_colum_layout_1_1)
				.setOnClickListener(new ImageClickListener(context, 0));
		convertView.findViewById(R.id.itemstyle_colum_layout_1_2)
				.setOnClickListener(new ImageClickListener(context, 1));
		convertView.findViewById(R.id.itemstyle_colum_layout_1_3)
				.setOnClickListener(new ImageClickListener(context, 2));
		convertView.findViewById(R.id.itemstyle_colum_layout_2_0)
				.setOnClickListener(new ImageClickListener(context, 3));
		convertView.findViewById(R.id.itemstyle_colum_layout_2_1)
				.setOnClickListener(new ImageClickListener(context, 3));
		convertView.findViewById(R.id.itemstyle_colum_layout_2_2)
				.setOnClickListener(new ImageClickListener(context, 4));
		convertView.findViewById(R.id.itemstyle_colum_layout_2_3)
				.setOnClickListener(new ImageClickListener(context, 5));
		convertView.findViewById(R.id.itemstyle_colum_layout_3_0)
				.setOnClickListener(new ImageClickListener(context, 6));
		convertView.findViewById(R.id.itemstyle_colum_layout_3_1)
				.setOnClickListener(new ImageClickListener(context, 6));
		convertView.findViewById(R.id.itemstyle_colum_layout_3_2)
				.setOnClickListener(new ImageClickListener(context, 7));
		convertView.findViewById(R.id.itemstyle_colum_layout_3_3)
				.setOnClickListener(new ImageClickListener(context, 8));
		catalogNameTv.setText(catalogName);
		int length = ads == null ? 0 : ads.size();
		if (length > 0) {
			imageLoader.get(ads.get(0).getImgurl(), imageLoader
					.getImageListener(iv_1_1, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_1_1.setText(ads.get(0).getName());
			name2Tv_1_1.setText(ads.get(0).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_layout_1_0)
					.setVisibility(View.GONE);
			convertView.findViewById(R.id.itemstyle_colum_layout_1_1)
					.setVisibility(View.GONE);
		}
		if (length > 1) {
			imageLoader.get(ads.get(1).getImgurl(), imageLoader
					.getImageListener(iv_1_2, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_1_2.setText(ads.get(1).getName());
			name2Tv_1_2.setText(ads.get(1).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_layout_1_2)
					.setVisibility(View.GONE);
		}
		if (length > 2) {
			imageLoader.get(ads.get(2).getImgurl(), imageLoader
					.getImageListener(iv_1_3, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_1_3.setText(ads.get(2).getName());
			name2Tv_1_3.setText(ads.get(2).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_layout_1_3)
					.setVisibility(View.GONE);
		}
		if (length > 3) {
			imageLoader.get(ads.get(3).getImgurl(), imageLoader
					.getImageListener(iv_2_1, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_2_1.setText(ads.get(3).getName());
			name2Tv_2_1.setText(ads.get(3).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_line_2)
					.setVisibility(View.GONE);
			convertView.findViewById(R.id.itemstyle_colum_layout_2)
					.setVisibility(View.GONE);
			convertView.findViewById(R.id.itemstyle_colum_line_3)
					.setVisibility(View.GONE);
			convertView.findViewById(R.id.itemstyle_colum_layout_3)
					.setVisibility(View.GONE);
			return convertView;
		}
		if (length > 4) {
			imageLoader.get(ads.get(4).getImgurl(), imageLoader
					.getImageListener(iv_2_2, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_2_2.setText(ads.get(4).getName());
			name2Tv_2_2.setText(ads.get(4).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_layout_2_2)
					.setVisibility(View.GONE);
		}
		if (length > 5) {
			imageLoader.get(ads.get(5).getImgurl(), imageLoader
					.getImageListener(iv_2_3, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_2_3.setText(ads.get(5).getName());
			name2Tv_2_3.setText(ads.get(5).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_layout_2_3)
					.setVisibility(View.GONE);
		}
		if (length > 6) {
			imageLoader.get(ads.get(6).getImgurl(), imageLoader
					.getImageListener(iv_3_1, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_3_1.setText(ads.get(6).getName());
			name2Tv_3_1.setText(ads.get(6).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_line_3)
					.setVisibility(View.GONE);
			convertView.findViewById(R.id.itemstyle_colum_layout_3)
					.setVisibility(View.GONE);
			return convertView;
		}
		if (length > 7) {
			imageLoader.get(ads.get(7).getImgurl(), imageLoader
					.getImageListener(iv_3_2, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_3_2.setText(ads.get(7).getName());
			name2Tv_3_2.setText(ads.get(7).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_layout_3_2)
					.setVisibility(View.GONE);
		}
		if (length > 8) {
			imageLoader.get(ads.get(8).getImgurl(), imageLoader
					.getImageListener(iv_3_3, R.mipmap.goods_list_item_bg,
							R.mipmap.goods_list_item_bg));
			name1Tv_3_3.setText(ads.get(8).getName());
			name2Tv_3_3.setText(ads.get(8).getMemo());
		} else {
			convertView.findViewById(R.id.itemstyle_colum_layout_3_3)
					.setVisibility(View.GONE);
		}
		return convertView;
	}

	private class ImageClickListener implements OnClickListener {

		private Context context;
		private int position;

		public ImageClickListener(Context context, int position) {
			this.context = context;
			this.position = position;
		}

		@Override
		public void onClick(View arg0) {
			AdvertsBean ab = ads.get(position);
			int linkkind = ab.getLinkkind();//0商品 1活动 2其他URL 3 类别
			String imglink = ab.getImglink();//imglink	商品ID/活动ID/url/parentid
			switch (linkkind) {
			case 0:
				Intent goods = new Intent();
				goods.setClass(context, GoodsDetailsActivity.class);
				goods.putExtra("goodsid", imglink);
				context.startActivity(goods);
				break;
			case 1:
				Intent activity = new Intent();
				activity.setClass(context, GoodsListActivity.class);
				activity.putExtra("type", "activity");
				activity.putExtra("param", imglink);
				activity.putExtra("title", ab.getName());
				context.startActivity(activity);
				break;
			case 2:
				Intent uri = new Intent();
				uri.setClass(context, WebActivity.class);
				uri.putExtra("url", imglink);
				context.startActivity(uri);
				break;
			case 3:
				Intent it = new Intent();
				it.setClass(context, GoodsListActivity.class);
				it.putExtra("type", "classify");
				it.putExtra("param", imglink);
				it.putExtra("title", ab.getName());
				context.startActivity(it);
				break;
			}
		}

	}
}
