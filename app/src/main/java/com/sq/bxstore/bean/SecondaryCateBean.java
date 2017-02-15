package com.sq.bxstore.bean;

import java.util.List;

import com.sq.bxstore.net.response.AllClassificationResponse.SingleCateInfo;

public class SecondaryCateBean {
	/*
	 * "cateName": "合资品牌", "cateOrder": 1, "id": 8, "level": 3, "parentId": 5
	 */
	private String cateName;
	private int cateOrder;
	private int id;
	private int level;
	private int parentId;
	private String url;
	private List<SingleCateInfo> thirdCateList;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getCateOrder() {
		return cateOrder;
	}

	public void setCateOrder(int cateOrder) {
		this.cateOrder = cateOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<SingleCateInfo> getThirdCateList() {
		return thirdCateList;
	}

	public void setThirdCateList(List<SingleCateInfo> thirdCateList) {
		this.thirdCateList = thirdCateList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
