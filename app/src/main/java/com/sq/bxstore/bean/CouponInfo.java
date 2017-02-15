package com.sq.bxstore.bean;

/**
 "data": [
         {
            "couponBlance":10,
            "couponCreatdate":1483509933000,
            "couponExpirydate":1486003654000,
            "coupond":1,
            "couponName":"10元优惠券",
            "couponStatus":"0",
            "couponXfBalance":100,
            "id":1,
            "optionAdminid":0,
            "optionTime":1483325272000,
            "userId":1,
            "userName":"yanlifeng"
        },
    ],
 */
public class CouponInfo {
	private String couponName;
	private String couponStatus;
	private int couponXfBalance;
	private int couponBlance;
	private long couponCreatdate;
	private long couponExpirydate;
	private long optionTime;
	private String userName;
	private String telnumb;
	private int coupond;
	private int id;
	private int userId;

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public int getCouponXfBalance() {
		return couponXfBalance;
	}

	public void setCouponXfBalance(int couponXfBalance) {
		this.couponXfBalance = couponXfBalance;
	}

	public int getCouponBlance() {
		return couponBlance;
	}

	public void setCouponBlance(int couponBlance) {
		this.couponBlance = couponBlance;
	}

	public long getCouponCreatdate() {
		return couponCreatdate;
	}

	public void setCouponCreatdate(long couponCreatdate) {
		this.couponCreatdate = couponCreatdate;
	}

	public long getCouponExpirydate() {
		return couponExpirydate;
	}

	public void setCouponExpirydate(long couponExpirydate) {
		this.couponExpirydate = couponExpirydate;
	}

	public long getOptionTime() {
		return optionTime;
	}

	public void setOptionTime(long optionTime) {
		this.optionTime = optionTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelnumb() {
		return telnumb;
	}

	public void setTelnumb(String telnumb) {
		this.telnumb = telnumb;
	}

	public int getCoupond() {
		return coupond;
	}

	public void setCoupond(int coupond) {
		this.coupond = coupond;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
