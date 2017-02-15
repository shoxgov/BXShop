package com.sq.bxstore;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.sq.bxstore.adapter.GoodsItemAdapter;
import com.sq.bxstore.bean.GoodsInfo;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.ActivityEventsReq;
import com.sq.bxstore.net.request.GoodsListReq;
import com.sq.bxstore.net.response.ActivityEventsResponse;
import com.sq.bxstore.net.response.ActivityEventsResponse.GoodsListInfo;
import com.sq.bxstore.net.response.GoodsListResponse;
import com.sq.bxstore.net.response.GoodsListResponse.GoodsGeneralData;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.LocationBitmapCacheTools;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;
import com.sq.bxstore.view.XListView;
import com.sq.bxstore.view.XListView.IXListViewListener;

public class GoodsListActivity extends Activity implements NetCallBack,
		IXListViewListener {

	private XListView productList = null;
	private GoodsItemAdapter goodsItemAdapter = null;
	// 是从搜索进来的还是从分类列表进来的 classify:来自分类；activity：来自活动；search:来自搜索
	private String type;
	private String param;
	/**
	 * 当前请求的页
	 */
	private int nowpage = 0;
	private int totalpage = 0;
	/**
	 * 商品列表数据
	 */
	private List<GoodsGeneralData> goodsList;
	private View bannerlayout;
	private ImageView bannerImg;
	private TextView bannerTitle;
	private TextView bannerContent;
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goods_list);
		Intent intent = getIntent();
		if (!intent.hasExtra("type")) {
			finish();
		}
		this.type = intent.getStringExtra("type");
		this.param = intent.getStringExtra("param");
		this.title = intent.getStringExtra("title");
		initViews();
		requestGoodsList();
	}

	private void initViews() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle(title);
		titleLayout.setTitleBarListener(new TitleBarListener() {
			
			@Override
			public void rightClick() {
			}
			
			@Override
			public void leftClick() {
				finish();
			}
		});
		bannerlayout = (View) findViewById(R.id.lv_goods_list_banner);
		bannerImg = (ImageView) findViewById(R.id.headview_banner_img);
		bannerTitle = (TextView) findViewById(R.id.headview_banner_title);
		bannerContent = (TextView) findViewById(R.id.headview_banner_content);
		productList = (XListView) findViewById(R.id.lv_goods_list);
		productList.setXListViewListener(this);
		productList.setPullRefreshEnable(false);
		goodsItemAdapter = new GoodsItemAdapter(this);
		productList.setAdapter(goodsItemAdapter);
		productList.setOnItemClickListener(itemClickListener);
	}

	private void requestGoodsList() {
		if (type.equals("search")) {
			GoodsListReq req = new GoodsListReq();
			req.setNetCallback(this);
			req.setNowpage(nowpage + "");
			req.setPagesize(Constants.pagesize + "");
			req.setSearchcode(param);
			req.addRequest();
		} else if (type.equals("classify")) {
			GoodsListReq req = new GoodsListReq();
			req.setNetCallback(this);
			req.setNowpage(nowpage + "");
			req.setPagesize(Constants.pagesize + "");
			req.setParentid(param);
			req.addRequest();
		} else if(type.equals("activity")){
			ActivityEventsReq req = new ActivityEventsReq();
			req.setNetCallback(this);
			req.setEventsid(param);
			req.setNowpage(nowpage + "");
			req.setPagesize(Constants.pagesize + "");
			req.addRequest();
		}
	}

	OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long arg3) {
			GoodsGeneralData good = goodsList.get(position);
			Intent intent = new Intent(GoodsListActivity.this,
					GoodsDetailsActivity.class);
			intent.putExtra("goodsid", good.getId()+"");
			startActivity(intent);
		}
	};

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof GoodsListResponse) {
			GoodsListResponse glr = (GoodsListResponse) baseRes;
			if (glr.getResult().equals("0")) {
				productList.stopRefresh();
				nowpage = glr.getPage().getCurrentPage();
				totalpage = glr.getPage().getTotalPage();
				if (nowpage >= totalpage || totalpage == 0) {
					productList.setFooterPullRefresh(false);
				} else {
					productList.setFooterPullRefresh(true);
				}
				goodsList = glr.getData();
				if (goodsList == null || goodsList.isEmpty()) {
					if (type.equals("search")) {
						ToastTool.showShortBigToast(this, "没有搜到相关商品");
					} else {
						ToastTool.showShortBigToast(this, "当前类下没有相关商品");
					}
					finish();
					return;
				}
				goodsItemAdapter.setDataList(goodsList, false);
			} else {
				if (type.equals("search")) {
					ToastTool.showShortBigToast(this, "没有搜到相关商品");
					finish();
					return;
				}
				ToastTool.showShortBigToast(this, glr.getMessage());
			}
		} else if (baseRes instanceof ActivityEventsResponse){
			ActivityEventsResponse aer = (ActivityEventsResponse) baseRes;
			if (aer.getResult().equals("0")) {
				try {
					String picture = aer.getData().getPicture();
					if (LocationBitmapCacheTools.isFileExist(picture)) {
						bannerImg.setImageBitmap(LocationBitmapCacheTools
								.getCompressBitmap(picture));
					} else {
						ImageLoader imageLoader = BXApplication.getInstance()
								.getImageLoader();
						imageLoader.get(picture, imageLoader.getImageListener(
								bannerImg, R.mipmap.banner_loading,
								R.mipmap.banner_loading));
					}
					bannerTitle.setText(aer.getData().getName());
					bannerContent.setText(aer.getData().getMemo());
					bannerlayout.setVisibility(View.VISIBLE);
					List<GoodsListInfo> goodlistdata = aer.getData().getGoodslist();
					if(goodlistdata == null || goodlistdata.isEmpty()){
						ToastTool.showShortBigToast(this, "活动下没有任何商品");
						return;
					}
					goodsItemAdapter.setGoodsListData(goodlistdata, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ToastTool.showShortBigToast(this, aer.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}

	@Override
	public void onHeaderRefresh() {
		ToastTool.showShortBigToast(this, "onRefresh");
		productList.stopRefresh();
	}

	@Override
	public void onFooterRefresh() {
		ToastTool.showShortBigToast(this, "onLoadMore");
		if (nowpage < totalpage) {
//			nowpage++;
			requestGoodsList();
		} else {
			productList.stopRefresh();
		}
	}

}
