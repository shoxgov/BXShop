package com.sq.bxstore.request;

import org.json.JSONObject;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.UserInfoResponse;
import com.sq.bxstore.utils.encryption.DataSecret;

/**
 username	用户名	string	No	
services	服务名	string	No	user_getuserinfo 该值固定
 */
public class UserInfoReq extends BaseCommReq {
	private UserInfoResponse response;

	private String username;
	private String services = "user_getuserinfo";
	private JSONObject params = new JSONObject();

	@Override
	public String generUrl() {
		setTag("UserInfoReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return UserInfoResponse.class;
	}

	@Override
	protected void handPostParam() {
		try {
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
			response = new UserInfoResponse();
		}
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}