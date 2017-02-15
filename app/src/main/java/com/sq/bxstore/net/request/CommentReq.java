package com.sq.bxstore.net.request;

import android.text.TextUtils;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.CommentResponse;

/**
 * http://vip.neigrp.com/api_receiver.php?
 * pointsmanager/comment/search.action
 * 
 * goodsid	           产品id	string	No	
   userid	           用户id 	string	No	
   pagesize		每页显示数	string	YES	每页显示数量
   nowpage	           当前页数	string	YES	当前页数
   orderid		订单id	string	No	

 */
public class CommentReq extends BaseCommReq {
	private CommentResponse response;

	private String goodsid;
	private String userid;
	private String orderid;
	private String pagesize;
	private String nowpage;

	@Override
	public String generUrl() {
		setTag("CommentReq");
		return NetWorkConfig.HTTP_HOST + "/comment/search.action";
	}
	
	@Override
	protected void handPostParam() {
		if (!TextUtils.isEmpty(goodsid)) {
			postParams.put("goodsid", getGoodsid());
		}
		if (!TextUtils.isEmpty(userid)) {
			postParams.put("userid", getUserid());
		}
		if (!TextUtils.isEmpty(orderid)) {
			postParams.put("orderid", getOrderid());
		}
		postParams.put("pagesize", getPagesize());
		postParams.put("nowpage", getNowpage());
	}

	@Override
	public Class getResClass() {
		return CommentResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new CommentResponse();
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

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getNowpage() {
		return nowpage;
	}

	public void setNowpage(String nowpage) {
		this.nowpage = nowpage;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


}