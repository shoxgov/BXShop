/**************************************************************************************
 * [Project]
 *       MyProgressDialog
 * [Package]
 *       com.lxd.widgets
 * [FileName]
 *       CustomProgressDialog.java
 * [Copyright]
 *       Copyright 2012 LXD All Rights Reserved.
 * [History]
 *       Version          Date              Author                        Record
 *--------------------------------------------------------------------------------------
 *       1.0.0           2012-4-27         lxd (rohsuton@gmail.com)        Create
 **************************************************************************************/

package com.sq.bxstore.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.android.volley.toolbox.ImageLoader;
import com.sq.bxstore.BXApplication;
import com.sq.bxstore.R;
import com.sq.bxstore.bean.GoodsInfo;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.HandleCommentReq;
import com.sq.bxstore.net.response.HandleCommentResponse;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.WaitTool;

/*
 *
 */

public class HandleCommentDialog extends Dialog implements
		View.OnClickListener, NetCallBack {
	private Context context = null;
	private ImageView img;
	private EditText content;
	private RatingBar rating;

	private int mark = 5;
	private GoodsInfo gi;
	private CheckBox checkBox;
	/**
	 * 记录评论是否成功
	 */
	private boolean flag = false;
	
	public interface DialogCallBack {
		public void callback(boolean flag, GoodsInfo gi);
	}
	
	private DialogCallBack callback;

	public HandleCommentDialog(Context context, GoodsInfo gi, DialogCallBack callback) {
		super(context);
		this.context = context;
		this.gi = gi;
		this.callback = callback;
	}
	
	public HandleCommentDialog(Context context, GoodsInfo gi) {
		super(context);
		this.context = context;
		this.gi = gi;
	}

	public HandleCommentDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	public void dismiss() {
		if(callback != null){
			callback.callback(flag,gi);
		}
		super.dismiss();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setTitle("发布评论");
		setCanceledOnTouchOutside(false);
		LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		setContentView(R.layout.dialog_handcomment);
		Window win = getWindow();
		win.setGravity(Gravity.TOP);
		win.getDecorView().setPadding(0, 0, 0, 0);
		WindowManager.LayoutParams lp = win.getAttributes();
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		win.setAttributes(lp);
		findViewById(R.id.dialog_comment_back).setOnClickListener(this);
		findViewById(R.id.dialog_comment_send).setOnClickListener(this);
		img = (ImageView) findViewById(R.id.dialog_comment_img);
		content = (EditText) findViewById(R.id.dialog_comment_content);
		rating = (RatingBar) findViewById(R.id.dialog_comment_rating);
		checkBox = (CheckBox) findViewById(R.id.dialog_comment_checkbox);
		rating.setOnRatingBarChangeListener(listener);
		ImageLoader imageLoader = BXApplication.getInstance().getImageLoader();
		imageLoader.get(gi.getGoodsimg(), imageLoader.getImageListener(img,
				R.mipmap.goods_list_item_bg, R.mipmap.goods_list_item_bg));
	}

	OnRatingBarChangeListener listener = new OnRatingBarChangeListener() {

		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			mark = (int) rating;
		}

	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_comment_back:
			dismiss();
			break;
		case R.id.dialog_comment_send:
			WaitTool.showDialog(context, "");
			HandleCommentReq req = new HandleCommentReq();
			req.setNetCallback(this);
			req.setGoodsid(gi.getGoodsid()+"");
			req.setOrderid(gi.getOrderid()+"");
			req.setScore(mark + "");
			req.setUserid(UserInfoBean.userId);
			req.setComment(content.getText().toString());
			if(checkBox.isChecked()){//1匿名 0不匿名  默认不匿名
				req.setIshidden("1");
			}else{
				req.setIshidden("0");
			}
			req.addRequest();
			break;
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof HandleCommentResponse) {
			WaitTool.dismissDialog();
			HandleCommentResponse hcr = (HandleCommentResponse) baseRes;
			if (hcr.getResult().equals("0")) {
				ToastTool.showShortBigToast(context, hcr.getMessage());
				flag = true;
				dismiss();
			} else {
				ToastTool.showShortBigToast(context, hcr.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}

}
