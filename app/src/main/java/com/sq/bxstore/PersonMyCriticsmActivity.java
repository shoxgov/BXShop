package com.sq.bxstore;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.sq.bxstore.adapter.CommentAdapter;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.CommentReq;
import com.sq.bxstore.net.response.CommentResponse;
import com.sq.bxstore.net.response.CommentResponse.CommentInfo;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;
import com.sq.bxstore.view.XListView;
import com.sq.bxstore.view.XListView.IXListViewListener;

public class PersonMyCriticsmActivity extends Activity implements
		TitleBarListener, NetCallBack {

	private XListView listView;
	private CommentAdapter adapter;
	private int totalpage = 0;
	private int nowpage =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_personal_list);
		initViews();
		WaitTool.showDialog(this);
		requestComment();
	}

	private void initViews() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("我的评论");
		titleLayout.setTitleBarListener(this);
		listView = (XListView) findViewById(R.id.person_list);
		adapter = new CommentAdapter(this);
		listView.setAdapter(adapter);
		listView.setPullRefreshEnable(false);
		listView.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onHeaderRefresh() {
				
			}
			
			@Override
			public void onFooterRefresh() {
				if(nowpage < totalpage){
//					nowpage ++;
					requestComment();
				}else{
					listView.stopRefresh();
				}
			}
		});
	}

	private void requestComment() {
		CommentReq req = new CommentReq();
		req.setNetCallback(this);
		req.setUserid(UserInfoBean.userId);
		req.setNowpage(nowpage + "");
		req.setPagesize(Constants.pagesize+"");
		req.addRequest();
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {

	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof CommentResponse) {
			listView.stopRefresh();
			WaitTool.dismissDialog();
			CommentResponse cr = (CommentResponse) baseRes;
			nowpage = cr.getPage().getCurrentPage();
			totalpage = cr.getPage().getTotalPage();
			if (nowpage >= totalpage || totalpage == 0) {
				listView.setFooterPullRefresh(false);
			} else {
				listView.setFooterPullRefresh(true);
			}
			if (cr.getResult().equals("0")) {
				List<CommentInfo> data = cr.getData();
				if (data != null && !data.isEmpty()) {
					adapter.setData(data,true);
//					findViewById(R.id.person_list_line).setVisibility(View.VISIBLE);
				}else{
					ToastTool.showShortBigToast(this, "当前你还没有评论过商品~");
				}
			}else{
				ToastTool.showShortBigToast(this, cr.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}

}
