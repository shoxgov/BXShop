package com.sq.bxstore.request;

import android.text.TextUtils;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.MyOrdersResponse;

/**
orderid		订单编号		string	No	通过订单id查询订单详细信息
userid		用户id		string	No	通过用户id查询用户所拥有订单
pagesize	每页大小		string	No	
nowpage		当前页		string	No	

 */
public class MyOrdersReq extends BaseCommReq {
	private MyOrdersResponse response;

	private String userid;
	private String orderid;
	private String nowpage;
	private String pagesize;

	@Override
	public String generUrl() {
		setTag("MyOrdersReq");
		return NetWorkConfig.HTTP_HOST + "/order/search.do";
	}

	@Override
	protected void handPostParam() {
		if (!TextUtils.isEmpty(getUserid())) {
			postParams.put("userid", getUserid());// 查询我的订单列表时传
		}
		if (!TextUtils.isEmpty(getOrderid())) {
			postParams.put("orderid", getOrderid());// 查询单个订单详情
		}
		postParams.put("nowpage", getNowpage());
		postParams.put("pagesize", getPagesize());
	}

	@Override
	public Class getResClass() {
		return MyOrdersResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new MyOrdersResponse();
		}
		return response;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getNowpage() {
		return nowpage;
	}

	public void setNowpage(String nowpage) {
		this.nowpage = nowpage;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

}