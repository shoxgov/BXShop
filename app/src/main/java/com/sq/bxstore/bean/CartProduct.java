package com.sq.bxstore.bean;

/**
 * 商品类
 * 
 * @author Cool
 * 
 */
public class CartProduct {
	/**
	 * 是否被选中
	 */
	private boolean isCheck;
	/**
	 * 商品数量
	 */
	private int count;
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
	 * 单价
	 */
	private String fprice;
	/**
	 * 备注
	 */
	private String fremark;
	/**
	 * 图片URL
	 */
	private String imgsrc;

	public CartProduct() {
	}

	/**
	 * 
	 * @param isCheck 是否选中
	 * @param count 商品数量
	 * @param fnumber 商品编码
	 * @param fname 商品名称
	 * @param fprice 商品单价
	 * @param fremark 商品备注
	 * @param imgsrc 商品图片
	 */
	public CartProduct(Boolean isCheck, int count, String fcategory,
			String fnumber, String fname, String fprice,
			String fremark, String imgsrc) {
		super();
		this.isCheck = isCheck;
		this.count = count;
		this.fcategory = fcategory;
		this.fnumber = fnumber;
		this.fname = fname;
		this.fprice = fprice;
		this.fremark = fremark;
		this.imgsrc = imgsrc;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String getFprice() {
		return fprice;
	}

	public void setFprice(String fprice) {
		this.fprice = fprice;
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

}
