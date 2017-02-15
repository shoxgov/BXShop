package com.sq.bxstore.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.sq.bxstore.BXApplication;
import com.sq.bxstore.GoodsDetailPicsActivity;
import com.sq.bxstore.GoodsDetailsActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.adapter.CommentAdapter;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.CommentReq;
import com.sq.bxstore.net.response.CommentResponse;
import com.sq.bxstore.net.response.CommentResponse.CommentInfo;
import com.sq.bxstore.net.response.GoodsDetailResponse;
import com.sq.bxstore.net.response.GoodsDetailResponse.GoodsGeneralData;
import com.sq.bxstore.utils.ListViewUtils;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.view.NumEditText;

public class GoodsDetailGeneralFragment extends Fragment implements NetCallBack, OnClickListener {

	private TextView tv_price = null;// 单价
	private TextView tv_brand = null;// 商品类别
	private TextView tv_category = null;// 商品编码
	private TextView tv_name = null;// 商品名称
	private TextView tv_stock = null;// 货物库存
	private NumEditText net_count = null;// 购买数量
	private TextView tv_people = null;// 商品评价人数

	private ViewPager viewpager_pic;// 商品图片
	private ArrayList<View> banners;
	private LinearLayout pic_hint_layout;
	private TextView pic_hint;
	private ListView commentlist;
	private CommentAdapter adapter;
	/**
	 * 详情封面的Banner图
	 */
	private ArrayList<String> goodimgList;
	/**
	 * 打分
	 */
	private TextView rb_marking;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.goodsdetail_general_fragment,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
		requestComment();
	}

	private void initViews() {

		viewpager_pic = (ViewPager) getView().findViewById(R.id.viewpager);
		pic_hint_layout = (LinearLayout) getView().findViewById(
				R.id.pic_hint_layout);
		pic_hint = (TextView) getView().findViewById(R.id.pic_hint);
		tv_price = (TextView) getView().findViewById(R.id.tv_product_price);
		tv_brand = (TextView) getView().findViewById(
				R.id.tv_product_brand);
		tv_category = (TextView) getView().findViewById(R.id.tv_product_category);
		tv_name = (TextView) getView().findViewById(R.id.tv_product_name);
		tv_stock = (TextView) getView().findViewById(R.id.tv_product_stock);
		net_count = (NumEditText) getView()
				.findViewById(R.id.net_product_count);
		rb_marking = (TextView) getView().findViewById(R.id.rb_product_marking);
		tv_people = (TextView) getView().findViewById(R.id.tv_product_people);
		getView().findViewById(R.id.goodsdetail_commentlayout).setOnClickListener(this);
		///////////////////////////////////////////////
		commentlist = (ListView) getView().findViewById(R.id.goodsdetail_commentlist);
		/////////////////////
		// 设置Banner适配器
		banners = new ArrayList<View>();
		// /////
		tv_people.setText(getResources().getString(R.string.goods_comment)
				.replace("XO", "0"));
	}
	private void requestComment() {
		Activity activity = getActivity();
		String goodsid = ((GoodsDetailsActivity) activity).getGoodsid();
		CommentReq req = new CommentReq();
		req.setNetCallback(this);
		req.setGoodsid(goodsid);
		req.setNowpage("0");
		req.setPagesize("3");
		req.addRequest();
	}
	
	private void initPic(ArrayList<String> goodimgList) {
		// banner部分
		if(goodimgList == null || goodimgList.isEmpty()){
			return;
		}
		this.goodimgList = goodimgList;
		int poisition = 0;
		banners.clear();
		ImageLoader imageLoader = BXApplication.getInstance().getImageLoader();
		for (String img : goodimgList) {
			ImageView iv = new ImageView(getActivity());
			iv.setScaleType(ImageView.ScaleType.FIT_XY);//FIT_CENTER 以中心为起点，等比例放缩，塞满一边打止
			imageLoader.get(img, imageLoader.getImageListener(iv, R.mipmap.banner_loading,
					R.mipmap.banner_loading));
			iv.setOnClickListener(new BannerClickListener(poisition));
			poisition ++;
			banners.add(iv);
		}
		if (banners.size() > 1) {
			pic_hint_layout.setVisibility(View.VISIBLE);
			pic_hint.setText("1/" + banners.size());
		} else {
			pic_hint_layout.setVisibility(View.GONE);
		}
		viewpager_pic.setAdapter(new MyPagerAdapter());
		viewpager_pic.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	/**
	 * @return 返回商品选择数
	 */
	public int getShopCount(){
		return net_count.getNum();
	}

	public void updateInfo(GoodsDetailResponse gdr) {
		if(gdr == null || gdr.getData() == null || gdr.getData().isEmpty()){
			return;
		}
		GoodsGeneralData ggd = gdr.getData().get(0);
		initPic(ggd.getGoodimglist());
		tv_price.setText(ggd.getPrice().toString());
		tv_brand.setText(ggd.getBrand());
		tv_name.setText(ggd.getGfullname());
		tv_stock.setText(ggd.getStorenumb()+"");
		tv_category.setText(ggd.getCategory());
	}

	private class BannerClickListener implements OnClickListener {

		private int poisition;

		public BannerClickListener(int poisition) {
			this.poisition = poisition;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(getActivity(), GoodsDetailPicsActivity.class);
			intent.putStringArrayListExtra("data", goodimgList);
			intent.putExtra("position", poisition);
			getActivity().startActivity(intent);
		}

	}

	private class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int position) {
			// 小图标处理
			pic_hint.setText((position + 1) + "/" + banners.size());
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

	}

	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(banners.get(position % banners.size()));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(banners.get(position % banners.size()));
			return banners.get(position % banners.size());
		}

		@Override
		public int getCount() {
			return banners.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object arg) {
			return view == arg;
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if(baseRes instanceof CommentResponse){
			CommentResponse cr = (CommentResponse) baseRes;
			if (cr.getResult().equals("0")) {
				List<CommentInfo> data = cr.getData();
				if (data != null && !data.isEmpty()) {
					adapter = new CommentAdapter(getActivity());
					commentlist.setAdapter(adapter);
					commentlist.setVisibility(View.VISIBLE);
					tv_people.setText(getResources().getString(
							R.string.goods_comment).replace("XO",
							data.size() + ""));
					rb_marking.setText(Html.fromHtml(getResources().getString(
							R.string.goods_marking)
							+ "<font color=#FF4040>"
							+ ((int) data.get(0).getAvgscore() * 20)
							+ "%</font>"));
					if (data.size() > 3) {
						adapter.setData(data.subList(0, 4), false);
					} else {
						adapter.setData(data, false);
					}
					ListViewUtils.setListViewHeightBasedOnChildren(commentlist);
				}
			} else {
				ToastTool.showShortBigToast(getActivity(), "暂无评论");
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.goodsdetail_commentlayout:
			if (Utils.isFastDoubleClick()) {
				return;
			}
			GoodsDetailsActivity activity = (GoodsDetailsActivity) getActivity();
			activity.callOnclick(R.id.rl_goodsdetail_comment);
			break;
			
		}
	}

}
