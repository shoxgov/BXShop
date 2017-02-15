package com.sq.bxstore.bean;

import android.text.TextUtils;
import android.util.Patterns;

public class GoodsInfo {
	private int count;
	private int goodsid;
	private int id;
	private int orderid;
	private float price;
	private String goodsimg;
	private String goodsname;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getGoodsimg() {
		if (!TextUtils.isEmpty(goodsimg)
				&& !Patterns.WEB_URL.matcher(goodsimg).matches()) {
			return NetWorkConfig.IMAGEPATH + goodsimg;
		}
		return goodsimg;
	}

	public void setGoodsimg(String goodsimg) {
		this.goodsimg = goodsimg;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

}