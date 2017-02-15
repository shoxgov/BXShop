package com.sq.bxstore.adapter;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
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
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.dialog.HandleCommentDialog;
import com.sq.bxstore.dialog.HandleCommentDialog.DialogCallBack;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.CommentReq;
import com.sq.bxstore.net.response.CommentResponse;
import com.sq.bxstore.net.response.CommentResponse.CommentInfo;
import com.sq.bxstore.utils.Utils;

public class OrderGoodlistAdapter extends BaseAdapter implements NetCallBack {
	private List<GoodsInfo> list;
	private Context context;
	private ImageLoader imageLoader;
	private Vector<String> commentList = new Vector<String>();
	private boolean isShowComment = true;

	public OrderGoodlistAdapter(Context context) {
		this.context = context;
		imageLoader = BXApplication.getInstance().getImageLoader();
	}

	public void setData(List<GoodsInfo> list, boolean isShowComment) {
		if (list == null || list.isEmpty()) {
			return;
		}
		this.list = list;
		this.isShowComment = isShowComment;
		notifyDataSetChanged();
		requestOrdersComment(list.get(0).getOrderid());
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public GoodsInfo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.ordergoodlist_item, null, false);
			viewHolder.img = (ImageView) convertView
					.findViewById(R.id.ordergoodlist_goodsimg);
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.ordergoodlist_goodsname);
			viewHolder.comment = (TextView) convertView
					.findViewById(R.id.ordergoodlist_comment);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final GoodsInfo gi = list.get(position);
		imageLoader.get(gi.getGoodsimg(), imageLoader.getImageListener(
				viewHolder.img, R.mipmap.goods_list_item_bg,
				R.mipmap.goods_list_item_bg));
		viewHolder.title.setText(gi.getGoodsname());
		if (!commentList.isEmpty()
				&& commentList.contains(gi.getGoodsid() + "")) {
			viewHolder.comment.setText("已评论");
			viewHolder.comment.setEnabled(false);
			viewHolder.comment.setBackgroundResource(R.color.mainbg);
		} else {
			viewHolder.comment.setText("评论");
			viewHolder.comment.setBackgroundResource(R.color.mainbg);
			if (viewHolder.comment.getTag() == null
					|| viewHolder.comment.getTag().equals("true")) {
				viewHolder.comment.setEnabled(true);
			} else {
				viewHolder.comment.setEnabled(false);
			}
		}
		if (isShowComment) {
			viewHolder.comment.setVisibility(View.VISIBLE);
			viewHolder.comment.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(final View v) {
					if (Utils.isFastDoubleClick()) {
						return;
					}
					HandleCommentDialog dialog = new HandleCommentDialog(
							context, gi, new DialogCallBack() {
								
								@Override
								public void callback(boolean flag, GoodsInfo gi) {
									if(flag){
										commentList.add(gi.getGoodsid()+"");
										v.setTag("false");
										notifyDataSetChanged();
									} else {
										v.setTag("true");
									}
								}
							});
					dialog.show();
				}
			});
		} else {
			viewHolder.comment.setVisibility(View.INVISIBLE);
		}
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, GoodsDetailsActivity.class);
				intent.putExtra("goodsid", gi.getGoodsid()+"");
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	private class ViewHolder {

		public TextView comment;
		public TextView title;
		public ImageView img;

	}
	private void requestOrdersComment(int orderid) {
		CommentReq req = new CommentReq();
		req.setNetCallback(this);
		req.setUserid(UserInfoBean.userId);
		req.setOrderid(orderid+"");
		req.setNowpage("0");
		req.setPagesize("100");
		req.addRequest();
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if(baseRes instanceof CommentResponse){
			CommentResponse cr = (CommentResponse) baseRes;
			if (cr.getResult().equals("0")) {
				List<CommentInfo> data = cr.getData();
				if(data != null && !data.isEmpty()){
					commentList.clear();
					for (CommentInfo ci : data) {
						commentList.add(ci.getGoodsid() + "");
					}
					notifyDataSetChanged();
				}
			} else {

			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		
	}
	
}
