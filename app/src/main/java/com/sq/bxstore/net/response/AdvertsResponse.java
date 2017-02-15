package com.sq.bxstore.net.response;

import java.util.List;

import com.sq.bxstore.bean.AdvertsBean;
import com.sq.bxstore.bean.PageInfo;
import com.sq.bxstore.net.BXBaseResponse;

public class AdvertsResponse extends BXBaseResponse{
	/*
	  {
	  "message":"查询成功~",
	  "result":"0",
	  "page":{
	  "begin":1,
	  "count":true,
	  "currentPage":1,
	  "currentPageRows":0,
	  "first":true,
	  "last":true,
	  "length":15,
	  "totalPage":1,
	  "totalRows":1
	  },
	  "data":
	  [
	  {
	  "id":8,
	  "imglink":"1",
	  "imgurl":"20161121/25a455be-c787-4ef4-910f-bb0ad7218d531479720624797.png",
	  "kind":1,
	  "linkkind":0,
	  "memo":"点点滴滴单独",
	  "name":"test2",
	  "ordernumb":3
	  }
	  ],
	  "ver":"v1.0"}
	 */
	private PageInfo page;
	private List<AdvertsBean> data;

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public List<AdvertsBean> getData() {
		return data;
	}

	public void setData(List<AdvertsBean> data) {
		this.data = data;
	}
	
}
