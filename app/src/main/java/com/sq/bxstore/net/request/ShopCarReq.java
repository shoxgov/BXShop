package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.ShopCarResponse;

/**
userid	           用户id	string	No	通过用户id将用户对应的购物车内容显示
pagesize	每页显示数	string	YES	每页显示数量
nowpage	           当前页数	string	YES	当前页数

 */
public class ShopCarReq extends BaseCommReq {
	private ShopCarResponse response;

	private String userid;
	private String pagesize;
	private String nowpage;

	@Override
	public String generUrl() {
		setTag("ShopCarReq");
		return NetWorkConfig.HTTP_HOST + "/shopcar/search.do";
	}

	@Override
	protected void handPostParam() {
		postParams.put("userid", getUserid());
		postParams.put("pagesize", getPagesize());
		postParams.put("nowpage", getNowpage());
	}

	@Override
	public Class getResClass() {
		return ShopCarResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new ShopCarResponse();
		}
		return response;
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

}