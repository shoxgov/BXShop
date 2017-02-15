package com.sq.bxstore.bean;

import java.io.Serializable;

/**
 * 商品类别类
 * @author Cool
 *
 */
public class GoodsType implements Serializable{
	private static final long serialVersionUID = 1L;
	private String num;
	private String name;
	private String imgsrc;
	private String k3num;
	public GoodsType() {
		// TODO Auto-generated constructor stub
	}
	public GoodsType(String num, String name, String imgsrc, String k3num) {
		super();
		this.num = num;
		this.name = name;
		this.imgsrc = imgsrc;
		this.k3num = k3num;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getK3num() {
		return k3num;
	}
	public void setK3num(String k3num) {
		this.k3num = k3num;
	}
}
