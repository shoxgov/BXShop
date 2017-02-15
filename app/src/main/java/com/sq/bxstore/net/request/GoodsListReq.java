package com.sq.bxstore.net.request;

import android.text.TextUtils;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.GoodsListResponse;

/**
          商品列表查询接口	
    parentid	父ID	    string	yes	通过parentID查询含该parentID的子集
	searchcode	查询内容	string	yes	模糊查询内容 对商品名称跟商品分类进行模糊查询返回批量
	pagesize	每页显示数	string	YES	每页显示数量
	nowpage	           当前页数	string	YES	当前页数

 */
public class GoodsListReq extends BaseCommReq {
	private String parentid;
	private String searchcode;
	private String pagesize;
	private String nowpage;
	private GoodsListResponse response;

	@Override
	public String generUrl() {
		setTag("GoodsListReq");
		return NetWorkConfig.HTTP_HOST + "/goods/searchlist.action";
	}

	@Override
	protected void handPostParam() {
		if (!TextUtils.isEmpty(getParentid())) {
			postParams.put("parentid", getParentid());
		}
		if (!TextUtils.isEmpty(getSearchcode())) {
			postParams.put("searchcode", getSearchcode());
		}
		postParams.put("pagesize", getPagesize());
		postParams.put("nowpage", getNowpage());
	}

	@Override
	public Class getResClass() {
		return GoodsListResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new GoodsListResponse();
		}
		return response;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getSearchcode() {
		return searchcode;
	}

	public void setSearchcode(String searchcode) {
		this.searchcode = searchcode;
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