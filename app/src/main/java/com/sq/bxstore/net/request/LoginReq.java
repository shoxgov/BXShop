package com.sq.bxstore.net.request;

import org.json.JSONObject;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.LoginResponse;
import com.sq.bxstore.utils.encryption.DataSecret;

/**
 * JSONObject rsJson = new JSONObject(); rsJson.put("username", "yanlifeng");
 * rsJson.put("password", "123456"); rsJson.put("services", "user_login");
 */
public class LoginReq extends BaseCommReq {
	private LoginResponse response;

	private String username;
	private String password;
	private String services = "user_login";
	private JSONObject params = new JSONObject();
	@Override
	public String generUrl() {
		setTag("LoginReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return LoginResponse.class;
	}

	@Override
	protected void handPostParam() {
		try {
			params.put("password", getPassword());
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
			response = new LoginResponse();
		}
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}