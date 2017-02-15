package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.WalletInfoResponse;
import com.sq.bxstore.utils.Utils;

/**
 * http://vip.neigrp.com/api_receiver.php?
 * customer_id=4002
 * &api_id=6
 * &type=mall.read_wallet
 * &signature=9a0964570e2a523be1179d74eb42b8b4
 */
public class WalletInfoReq extends BaseCommReq {
	private WalletInfoResponse response;

	private String customer_id;
	private int api_id = 6;
	private String type = "mall.read_wallet";
	private String signature;// md5(customer_id + api_id +type + secreat key)

	@Override
	public String generUrl() {
		setTag("WalletInfoReq");
		return NetWorkConfig.HTTPS + "customer_id=" + getCustomer_id()
				+ "&api_id=" + api_id + "&type=" + type
				+ "&signature=" + getSignature();
	}

	@Override
	public Class getResClass() {
		return WalletInfoResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new WalletInfoResponse();
		}
		return response;
	}


	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	//md5(customer_id + api_id +type + secreat key)
	public String getSignature() {
		return Utils
				.getMd5((customer_id + api_id + type + UserInfoBean.secreatkey)
						.getBytes());
	}

}