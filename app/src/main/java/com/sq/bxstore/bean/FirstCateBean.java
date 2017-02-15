package com.sq.bxstore.bean;

import java.util.List;

public class FirstCateBean {
	/*
	 * "cateName": "合资品牌", "cateOrder": 1, "id": 8, "level": 3, "parentId": 5
	 */
	private String cateName;
	private int cateOrder;
	private int id;
	private int level;
	private int parentId;
	private String url;
	private List<SecondaryCateBean> secondaryCateList;

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

	public List<SecondaryCateBean> getSecondaryCateList() {
		return secondaryCateList;
	}

	public void setSecondaryCateList(List<SecondaryCateBean> secondaryCateList) {
		this.secondaryCateList = secondaryCateList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
