package com.sq.bxstore.fragment;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.sq.bxstore.GoodsDetailsActivity;
import com.sq.bxstore.GoodsListActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.WebActivity;
import com.sq.bxstore.adapter.ItemStyleAdapter;
import com.sq.bxstore.base.ActivitiesListItemStyleManager;
import com.sq.bxstore.base.ActivitiesListItemStyleViewBase;
import com.sq.bxstore.bean.AdvertsBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.AdvertsReq;
import com.sq.bxstore.net.request.CatalogsReq;
import com.sq.bxstore.net.response.AdvertsResponse;
import com.sq.bxstore.net.response.CatalogsResponse;
import com.sq.bxstore.net.response.CatalogsResponse.CatalogInfo;
import com.sq.bxstore.utils.CatalogComparator;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.BannerView;
import com.sq.bxstore.view.BannerView.OnBannerItemClickListener;

public class FragmentHome extends Fragment implements OnClickListener,
		NetCallBack {

	private ListView activities_list;
	private ItemStyleAdapter adapter;
	/**
	 * 用以区分是头部的还是下面栏目的
	 */
	private String requestType;
	private EditText edit_search;
	/**
	 * 配置的栏目信息
	 */
	private List<CatalogInfo> catalogList;
	private BannerView bannerView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		init();
	}

	private void initView() {
		// 初始化
		bannerView = new BannerView(getActivity());//
		edit_search = (EditText) getView()
				.findViewById(R.id.et_goods_searchbar);
		getView().findViewById(R.id.btn_search).setOnClickListener(this);
		// 活动推荐列表展示
		activities_list = (ListView) getView().findViewById(
				R.id.activities_list);
		activities_list.addHeaderView(bannerView);
		adapter = new ItemStyleAdapter(getActivity());
		activities_list.setAdapter(adapter);
	}

	private void init() {
		WaitTool.showDialog(getActivity(), "");
		requestAdverts("1");
	}

	private void initBannerOnClick(final List<AdvertsBean> data) {
		bannerView
				.setOnBannerItemClickListener(new OnBannerItemClickListener() {

					@Override
					public void onClick(int position) {
						AdvertsBean ab = data.get(position);
						int linkkind = ab.getLinkkind();// 0商品 1活动 2其他URL 3 类别
						String imglink = ab.getImglink();// imglink
															// 商品ID/活动ID/url/parentid
						switch (linkkind) {
						case 0:
							Intent goods = new Intent();
							goods.setClass(getActivity(),
									GoodsDetailsActivity.class);
							goods.putExtra("goodsid", imglink);
							getActivity().startActivity(goods);
							break;
						case 1:
							Intent activity = new Intent();
							activity.setClass(getActivity(),
									GoodsListActivity.class);
							activity.putExtra("type", "activity");
							activity.putExtra("param", imglink);
							activity.putExtra("title", ab.getName());
							getActivity().startActivity(activity);
							break;
						case 2:
							Intent uri = new Intent();
							uri.setClass(getActivity(), WebActivity.class);
							uri.putExtra("url", imglink);
							getActivity().startActivity(uri);
							break;
						case 3:
							Intent it = new Intent();
							it.setClass(getActivity(), GoodsListActivity.class);
							it.putExtra("type", "classify");
							it.putExtra("param", imglink);
							it.putExtra("title", ab.getName());
							getActivity().startActivity(it);
							break;
						}

					}
				});
	}

	/**
	 * @param type
	 *            1头部 2其他 头部就是首页Banner配置展示的
	 *            其它就是下面配置的栏目所有的，然后根据返回的kind去匹配栏目的code值，进行筛选
	 */
	private void requestAdverts(String type) {
		requestType = type;
		AdvertsReq req = new AdvertsReq();
		req.setNetCallback(this);
		req.setType(type);// 1头部 2其他
		req.setPagesize("100");// 每页显示数量
		req.setNowpage("1");// 当前页数
		req.addRequest();
	}

	/**
	 * 请求Banner下面的栏目活动名和ID，取得后再根据ID请求该个栏目下的活动列表匹配。
	 */
	private void requestCatalogs() {
		CatalogsReq req = new CatalogsReq();
		req.setNetCallback(this);
		req.addRequest();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_search:
			String content = "";
			try {
				content = edit_search.getText().toString().trim();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (TextUtils.isEmpty(content)) {
				ToastTool.showShortBigToast(getActivity(), "请输入搜索关键词");
				return;
			}
			Intent intent = new Intent();
			intent.setClass(getActivity(), GoodsListActivity.class);
			intent.putExtra("type", "search");
			intent.putExtra("param", content);
			intent.putExtra("title", content);
			getActivity().startActivity(intent);
			break;
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof AdvertsResponse) {
			AdvertsResponse ar = (AdvertsResponse) baseRes;
			if (requestType.equals("1")) {// 头部Head Banner
				if (ar.getResult().equals("0")) {
					List<AdvertsBean> data = ar.getData();
					if (data == null || data.isEmpty()) {
						requestCatalogs();
						return;
					}
					bannerView.setAdList(data);
					initBannerOnClick(data);
				} else {
					ToastTool.showShortBigToast(getActivity(), ar.getMessage());
				}
				requestCatalogs();
			} else {// 所有栏目下的广告
				WaitTool.dismissDialog();
				if (ar.getResult().equals("0")) {
					List<AdvertsBean> data = ar.getData();
					if (data == null || data.isEmpty()) {
						return;
					}
					ActivitiesListItemStyleManager manager = new ActivitiesListItemStyleManager();
					List<ActivitiesListItemStyleViewBase> list = manager
							.getListItemView(catalogList, data);
					adapter.setData(list);
				} else {
					ToastTool.showShortBigToast(getActivity(), ar.getMessage());
				}
			}
		} else if (baseRes instanceof CatalogsResponse) {
			CatalogsResponse cr = (CatalogsResponse) baseRes;
			if (cr.getResult().equals("0")) {
				catalogList = cr.getData();
				if (catalogList == null || catalogList.isEmpty()) {
					ToastTool.showShortBigToast(getActivity(), "当前没有活动栏目");
					return;
				}
				Comparator comp = new CatalogComparator();
				Collections.sort(catalogList, comp);
				requestAdverts("2");
			} else {
				ToastTool.showShortBigToast(getActivity(), cr.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		if (tag.equals("AdvertsReq")) {
			requestCatalogs();
		}
	}
}
