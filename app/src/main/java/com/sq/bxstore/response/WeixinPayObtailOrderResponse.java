package com.sq.bxstore.response;

import com.sq.bxstore.net.BaseResponse;

public class WeixinPayObtailOrderResponse extends BaseResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * {
	 * "appid":"wxb4ba3c02aa476ea1",
	 * "partnerid":"1305176001",
	 * "package":"Sign=WXPay",
	 * "noncestr":"3fe247fcf0768458ccf4c1df43d7fe25",
	 * "timestamp":1476685190,
	 * "prepayid":"wx20161017141950431dff8be70523214476",
	 * "sign":"EF09C1874C8199026B0DD8E6C437DAA0"
	 * }
	 */
	/////////////////// error start
	private int retcode = -99;
	private String retmsg;
	////////////////////end
	private String appid;
	private String partnerid;
//	private String package;
	private String noncestr;
	private String timestamp;
	private String prepayid;
	private String sign;
	
	public int getRetcode() {
		return retcode;
	}

	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
