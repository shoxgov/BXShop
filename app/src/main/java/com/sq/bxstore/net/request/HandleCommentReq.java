package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.HandleCommentResponse;

/**
 * http://vip.neigrp.com/api_receiver.php?
 * pointsmanager/comment/manager.action
 * 
userid	           用户id 	string	YES	
goodsid	           产品id	string	YES	
comment	           评论内容	string	YES	
score	           评分	    string	YES	
ishidden	是否匿名	string	YES	1匿名 0不匿名  默认不匿名
orderid		订单id	string	NO	
 */
public class HandleCommentReq extends BaseCommReq {
	private HandleCommentResponse response;

	private String goodsid;
	private String userid;
	private String comment;
	private String score;
	private String ishidden = "0";
	private String orderid ;

	@Override
	public String generUrl() {
		setTag("HandleCommentReq");
		return NetWorkConfig.HTTP_HOST
				+ "/comment/manager.action";
	}

	@Override
	protected void handPostParam() {
		postParams.put("goodsid", getGoodsid());
		postParams.put("userid", getUserid());
		postParams.put("comment", getComment());
		postParams.put("score", getScore());
		postParams.put("ishidden", getIshidden());
		postParams.put("orderid", getOrderid());
	}

	@Override
	public Class getResClass() {
		return HandleCommentResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new HandleCommentResponse();
		}
		return response;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getIshidden() {
		return ishidden;
	}

	public void setIshidden(String ishidden) {
		this.ishidden = ishidden;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}