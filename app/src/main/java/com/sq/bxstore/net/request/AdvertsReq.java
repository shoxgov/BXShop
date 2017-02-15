package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.AdvertsResponse;

/**
广告查询接口	type		广告类型	string	No	通过广告类型来确认返回的是什么位置的广告  1头部 2其他
			pagesize	每页显示数	string	YES	每页显示数量
			nowpage		当前页数	string	YES	当前页数

 */
public class AdvertsReq extends BaseCommReq {
	private AdvertsResponse response;

	private String type;
	private String pagesize;
	private String nowpage;

	@Override
	public String generUrl() {
		setTag("AdvertsReq");
		return NetWorkConfig.HTTP_HOST + "/adevent/searchad.action";
	}
	
	@Override
	protected void handPostParam() {
		postParams.put("type", getType());
		postParams.put("pagesize", getPagesize());
		postParams.put("nowpage", getNowpage());
	}

	@Override
	public Class getResClass() {
		return AdvertsResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new AdvertsResponse();
		}
		return response;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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