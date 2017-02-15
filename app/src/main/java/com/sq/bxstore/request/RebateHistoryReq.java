package com.sq.bxstore.request;

import org.json.JSONObject;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.RebateHistoryResponse;
import com.sq.bxstore.utils.encryption.DataSecret;

/**
username	用户名	string	No	
services	服务名	string	No	user_getpurselist该值固定
purse_type	流水类型	string	No	积分0、分红1、可用余额2(充值金额消费记录)
date_time	起始时间	string	No	

 */
public class RebateHistoryReq extends BaseCommReq {
	private RebateHistoryResponse response;

	private String username;
	private String purse_type;
	private String services = "user_getpurselist";
	private JSONObject params = new JSONObject();
	@Override
	public String generUrl() {
		setTag("RebateHistoryReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return RebateHistoryResponse.class;
	}

	@Override
	protected void handPostParam() {
		try {
			params.put("purse_type", getPurse_type());
			params.put("services", services);
			params.put("username", getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		postParams.put("params", DataSecret.encryptDES(params.toString()));
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new RebateHistoryResponse();
		}
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPurse_type() {
		return purse_type;
	}

	public void setPurse_type(String purse_type) {
		this.purse_type = purse_type;
	}

}