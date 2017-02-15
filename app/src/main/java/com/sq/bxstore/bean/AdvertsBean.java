package com.sq.bxstore.bean;

import android.text.TextUtils;
import android.util.Patterns;

/**
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
	name		名称		string	No	名称
	imgurl		封面		string	No	广告图片
	linkkind	类别		string	No	0商品 1活动 3其他URL 4 类别
	imglink		广告类型	string	No	商品ID/活动ID/url/parentid
	ordernumb	排列顺序	string	YES	显示顺序 倒序排列 数值越大越靠前
	kind		广告分组	string	No	类型(在字典表中自定义~广告栏目分组)
	memo				
 */
public class AdvertsBean {
	private int id;
	private int kind;
	private int linkkind;
	private int ordernumb;
	private String imglink;
	private String imgurl;
	private String memo;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getLinkkind() {
		return linkkind;
	}
	public void setLinkkind(int linkkind) {
		this.linkkind = linkkind;
	}
	public int getOrdernumb() {
		return ordernumb;
	}
	public void setOrdernumb(int ordernumb) {
		this.ordernumb = ordernumb;
	}
	public String getImglink() {
		return imglink;
	}
	public void setImglink(String imglink) {
		this.imglink = imglink;
	}
	public String getImgurl() {
		if (!TextUtils.isEmpty(imgurl) && !Patterns.WEB_URL.matcher(imgurl).matches()) {
			imgurl = NetWorkConfig.IMAGEPATH + imgurl;
		}
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}