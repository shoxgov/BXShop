package com.sq.bxstore.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.sq.bxstore.BXApplication;
import com.sq.bxstore.GoodsListActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.adapter.SortListViewAdapter;
import com.sq.bxstore.bean.FirstCateBean;
import com.sq.bxstore.bean.SecondaryCateBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.AllClassificationReq;
import com.sq.bxstore.net.response.AllClassificationResponse;
import com.sq.bxstore.net.response.AllClassificationResponse.SingleCateInfo;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;

public class FragmentSort extends Fragment implements NetCallBack,
		OnClickListener {

	private Fragment rightFragment;
	private FragmentTransaction fragmentTransaction;
	private ListView leftListView;
	private SortListViewAdapter sortListAdapter;
	private boolean isLoaded = false;
	private EditText edit_search;
	private View progress;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sort, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		isLoaded = false;
		initView();
		init();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!isLoaded) {
			requestFirstClassification();
		}
	}

	private void initView() {
		progress = (View) getView().findViewById(
				R.id.fragement_sort_progress);
		leftListView = (ListView) getView().findViewById(
				R.id.fragement_sort_left);
		rightFragment = getChildFragmentManager().findFragmentById(
				R.id.fragement_sort_right);
		fragmentTransaction = getChildFragmentManager().beginTransaction()
				.hide(rightFragment);
		edit_search = (EditText) getView()
				.findViewById(R.id.et_goods_searchbar);
		getView().findViewById(R.id.btn_search).setOnClickListener(this);
	}

	private void init() {
		sortListAdapter = new SortListViewAdapter(getActivity());
		leftListView.setAdapter(sortListAdapter);
		leftListView.setOnItemClickListener(listener);
		fragmentTransaction.show(rightFragment).commit();
	}

	private void requestFirstClassification() {
		BXApplication.getInstance().setSortData(null);
		AllClassificationReq req = new AllClassificationReq();
		req.setNetCallback(this);
		req.addRequest();
	}

	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			if (Utils.isFastDoubleClick()) {
				return;
			}
			try {
				sortListAdapter.setSelectedTag(position);
				Object obj = view.getTag();
				if (obj instanceof FirstCateBean) {
					((FragmentSecondarySort) rightFragment)
							.fresh(((FirstCateBean) obj).getSecondaryCateList());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	};

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof AllClassificationResponse) {
			progress.setVisibility(View.GONE);
			AllClassificationResponse fcr = (AllClassificationResponse) baseRes;
			if (fcr.getResult().equals("0")) {
				List<SingleCateInfo> data = fcr.getData();
				if (data == null || data.isEmpty()) {
					ToastTool.showShortBigToast(getActivity(), "没有查询到分类");
					return;
				}
				filterCateToBean(data);
				isLoaded = true;
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		if (tag.equals("AllClassificationReq")) {
			progress.setVisibility(View.GONE);
		}
	}

	private void filterCateToBean(List<SingleCateInfo> data) {
		List<FirstCateBean> cateData = new ArrayList<FirstCateBean>();
		List<SingleCateInfo> temp2 = new ArrayList<SingleCateInfo>();
		for (SingleCateInfo sci : data) {
			if (sci.getParentId() == 1) {// 一级分类项
				FirstCateBean first = new FirstCateBean();
				first.setCateName(sci.getCateName());
				first.setCateOrder(sci.getCateOrder());
				first.setId(sci.getId());
				first.setUrl(sci.getUrl());
				first.setLevel(sci.getLevel());
				first.setParentId(sci.getParentId());
				List<SecondaryCateBean> secondary = new ArrayList<SecondaryCateBean>();
				first.setSecondaryCateList(secondary);
				cateData.add(first);
			} else if (sci.getParentId() != -1) {
				temp2.add(sci);
			}
		}
		List<SingleCateInfo> temp3 = new ArrayList<SingleCateInfo>();
		for (SingleCateInfo sci : temp2) {
			boolean isFound = false;
			for (FirstCateBean fcb : cateData) {// 这一轮，二级分类肯定是空的，所以父ID等于一级分ID的肯定就是二级分类
				if (sci.getParentId() == fcb.getId()) {// 二级分类项
					isFound = true;
					SecondaryCateBean secondary = new SecondaryCateBean();
					secondary.setCateName(sci.getCateName());
					secondary.setCateOrder(sci.getCateOrder());
					secondary.setId(sci.getId());
					secondary.setUrl(sci.getUrl());
					secondary.setLevel(sci.getLevel());
					secondary.setParentId(sci.getParentId());
					List<SingleCateInfo> third = new ArrayList<SingleCateInfo>();
					secondary.setThirdCateList(third);
					fcb.getSecondaryCateList().add(secondary);// 将找到的二级分类放入一级分类List中
				}
			}
			if (!isFound) {
				temp3.add(sci);
			}
		}
		temp2.clear();
		temp2 = null;
		for (SingleCateInfo sci : temp3) {// 剩下的都是分配到三级分类下的
			boolean isFound = false;
			for (FirstCateBean fcb : cateData) {// 一级分类目录下有多个二级分类
				List<SecondaryCateBean> secondary = fcb.getSecondaryCateList();
				for (SecondaryCateBean scb : secondary) {// 逐个匹配每个一级分类下的二级分类List
					if (sci.getParentId() == scb.getId()) {// 是三级分类项，并属于该二级分类名下
						isFound = true;
						scb.getThirdCateList().add(sci);
					}
				}
			}
		}
		isLoaded = true;
		progress.setVisibility(View.GONE);
		if (cateData == null || cateData.isEmpty()) {
			return;
		}
		BXApplication.getInstance().setSortData(cateData);
		sortListAdapter.setData(cateData);
		if (rightFragment != null) {
			((FragmentSecondarySort) rightFragment).fresh();
		}
		temp3.clear();
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
}
