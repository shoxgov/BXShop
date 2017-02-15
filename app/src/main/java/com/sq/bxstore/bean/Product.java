package com.sq.bxstore.bean;
/**
 * 商品类
 * @author Cool
 *
 */
public class Product {
	
	/**
	 * 产品类别
	 */
	private String fcategory;
	/**
	 * 编码
	 */
	private String fnumber;
	/**
	 * 名称
	 */
	private String fname;
	/**
	 * 规格
	 */
	private String fmodel;
	/**
	 * 包重
	 */
	private String fbagweight;
	/**
	 * 单价
	 */
	private String fprice;
	/**
	 * 所有者
	 */
	private String fowner;
	/**
	 * 备注
	 */
	private String fremark;
	/**
	 * 图片URL
	 */
	private String imgsrc;
	/**
	 * 推荐
	 */
	private String tag;
	public Product() {
	}
	public Product(String fcategory, String fnumber, String fname,
			String fmodel, String fbagweight, String fprice, String fowner,
			String fremark, String imgsrc, String tag) {
		super();
		this.fcategory = fcategory;
		this.fnumber = fnumber;
		this.fname = fname;
		this.fmodel = fmodel;
		this.fbagweight = fbagweight;
		this.fprice = fprice;
		this.fowner = fowner;
		this.fremark = fremark;
		this.imgsrc = imgsrc;
		this.tag = tag;
	}
	public String getFcategory() {
		return fcategory;
	}
	public void setFcategory(String fcategory) {
		this.fcategory = fcategory;
	}
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFmodel() {
		return fmodel;
	}
	public void setFmodel(String fmodel) {
		this.fmodel = fmodel;
	}
	public String getFbagweight() {
		return fbagweight;
	}
	public void setFbagweight(String fbagweight) {
		this.fbagweight = fbagweight;
	}
	public String getFprice() {
		return fprice;
	}
	public void setFprice(String fprice) {
		this.fprice = fprice;
	}
	public String getFowner() {
		return fowner;
	}
	public void setFowner(String fowner) {
		this.fowner = fowner;
	}
	public String getFremark() {
		return fremark;
	}
	public void setFremark(String fremark) {
		this.fremark = fremark;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
