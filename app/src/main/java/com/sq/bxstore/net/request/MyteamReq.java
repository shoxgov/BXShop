package com.sq.bxstore.net.request;

import org.json.JSONObject;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.MyteamResponse;
import com.sq.bxstore.utils.encryption.DataSecret;

/**
 	username	用户名	string	No	
	services	服务名	string	No	user_getuserlist 该值固定
 */
public class MyteamReq extends BaseCommReq {
	private MyteamResponse response;

	private String username;
	private String services = "user_getuserlist";
	private JSONObject params = new JSONObject();
	@Override
	public String generUrl() {
		setTag("MyteamReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return MyteamResponse.class;
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
			response = new MyteamResponse();
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