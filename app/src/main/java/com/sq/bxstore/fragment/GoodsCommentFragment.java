package com.sq.bxstore.fragment;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sq.bxstore.GoodsDetailsActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.adapter.CommentAdapter;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.CommentReq;
import com.sq.bxstore.net.response.CommentResponse;
import com.sq.bxstore.net.response.CommentResponse.CommentInfo;
import com.sq.bxstore.net.response.GoodsDetailResponse;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.view.XListView;
import com.sq.bxstore.view.XListView.IXListViewListener;

public class GoodsCommentFragment extends Fragment implements NetCallBack {

	private XListView list;
	private CommentAdapter adapter;
	/**
	 * 请求列表的页面信息
	 */
	private int nowpage = 0;
	private int totalpage = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_goods_list, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
		init();
	}

	private void init() {
		nowpage = 1;
		requestComment();
	}

	private void requestComment() {
		Activity activity = getActivity();
		String goodsid = ((GoodsDetailsActivity) activity).getGoodsid();
		CommentReq req = new CommentReq();
		req.setNetCallback(this);
		req.setGoodsid(goodsid);
		req.setPagesize(Constants.pagesize+"");
		req.setNowpage(nowpage +"");
		req.addRequest();
	}

	private void initViews() {
		getView().findViewById(R.id.titlelayout).setVisibility(View.GONE);
		list = (XListView) getView().findViewById(R.id.lv_goods_list);
		list.setHeaderPullRefresh(false);
		list.setFooterPullRefresh(false);
		adapter = new CommentAdapter(getActivity());
		list.setAdapter(adapter);
		list.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onHeaderRefresh() {
				
			}
			
			@Override
			public void onFooterRefresh() {
				if(nowpage < totalpage){
//					nowpage ++;
					requestComment();
				}else{
					list.stopRefresh();
				}
			}
		});
	}

	public void updateInfo(GoodsDetailResponse gdr) {
		if (gdr == null) {
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if(baseRes instanceof CommentResponse){
			CommentResponse cr = (CommentResponse) baseRes;
			list.stopRefresh();
			if(cr.getResult().equals("0")){
				nowpage = cr.getPage().getCurrentPage();
				totalpage = cr.getPage().getTotalPage();
				List<CommentInfo> data = cr.getData();
				if(data != null && !data.isEmpty()){
					adapter.setData(data, false);
				}else{
					ToastTool.showShortBigToast(getActivity(), "暂无评论");
				}
				if(nowpage == totalpage || totalpage == 0){
					list.setFooterPullRefresh(false);
				}else{
					list.setFooterPullRefresh(true);
				}
			}else{
				ToastTool.showShortBigToast(getActivity(), "暂无评论");
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		
	}
}
