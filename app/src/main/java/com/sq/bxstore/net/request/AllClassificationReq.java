package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.AllClassificationResponse;

/**
           商品列表查询接口	
    parentid	父ID	    string	yes	通过parentID查询含该parentID的子集
	pagesize	每页显示数	string	YES	每页显示数量
	nowpage	           当前页数	string	YES	当前页数

 */
public class AllClassificationReq extends BaseCommReq {
	private AllClassificationResponse response;

//	private String parentid;//当值为-1时，就返回一级分类列表
//	private String pagesize;
//	private String nowpage;

	@Override
	public String generUrl() {
		setTag("AllClassificationReq");
		return NetWorkConfig.HTTP_HOST + "/goods/searchcategoryall.action";
	}

	@Override
	protected void handPostParam() {
	}
	@Override
	public Class getResClass() {
		return AllClassificationResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new AllClassificationResponse();
		}
		return response;
	}

//	public String getParentid() {
//		return parentid;
//	}
//
//	public void setParentid(String parentid) {
//		this.parentid = parentid;
//	}
//
//	public String getPagesize() {
//		return pagesize;
//	}
//
//	public void setPagesize(String pagesize) {
//		this.pagesize = pagesize;
//	}
//
//	public String getNowpage() {
//		return nowpage;
//	}
//
//	public void setNowpage(String nowpage) {
//		this.nowpage = nowpage;
//	}

}