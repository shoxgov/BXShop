package com.sq.bxstore.bean;
/**
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
	begin		名称		string	No	名称
	count		封面		string	No	广告图片
	currentPage	类别		string	No	0商品 1活动 3其他URL 4 类别
	currentPageRows		广告类型	string	No	商品ID/活动ID/url/parentid
	first	排列顺序	string	YES	显示顺序 倒序排列 数值越大越靠前
	last		广告分组	string	No	类型(在字典表中自定义~广告栏目分组)
	length				
	totalPage				
	totalRows				
 */
public class PageInfo {
	private int begin;
	private int currentPage;
	private int length;
	private int totalPage;

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}