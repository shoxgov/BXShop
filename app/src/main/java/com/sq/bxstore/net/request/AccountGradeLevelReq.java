package com.sq.bxstore.net.request;

import org.json.JSONObject;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.AccountGradeLevelResponse;
import com.sq.bxstore.utils.encryption.DataSecret;

/**
services	服务名	string	No	user_getgradelist 该值固定
 */
public class AccountGradeLevelReq extends BaseCommReq {
	private AccountGradeLevelResponse response;

	private String services = "user_getgradelist";
	private JSONObject params = new JSONObject();
	
	@Override
	public String generUrl() {
		setTag("AccountGradeLevelReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return AccountGradeLevelResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new AccountGradeLevelResponse();
		}
		return response;
	}

	@Override
	protected void handPostParam() {
		try {
			params.put("services", services);
		} catch (Exception e) {
			e.printStackTrace();
		}
		postParams.put("params", DataSecret.encryptDES(params.toString()));
	}

}