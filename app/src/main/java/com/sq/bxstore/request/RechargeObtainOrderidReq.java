package com.sq.bxstore.request;

import org.json.JSONObject;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.RechargeObtainOrderidResponse;
import com.sq.bxstore.utils.encryption.DataSecret;

/**
username		用户名	string	No	
trade_amount	充值金额	string	No	
services		服务名	string	No	user_tochongzhi 该值固定
trade_type		充值方式	string	No	1 支付宝 2微信 3银联
 */
public class RechargeObtainOrderidReq extends BaseCommReq {
	private RechargeObtainOrderidResponse response;

	private String username;
	private String trade_amount;
	private String services = "user_tochongzhi";
	private String trade_type;
	private JSONObject params = new JSONObject();

	@Override
	public String generUrl() {
		setTag("RechargeObtainOrderidReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return RechargeObtainOrderidResponse.class;
	}

	@Override
	protected void handPostParam() {
		try {
			params.put("username", getUsername());
			params.put("trade_amount", getTrade_amount());
			params.put("trade_type", getTrade_type());
			params.put("services", services);
		} catch (Exception e) {
			e.printStackTrace();
		}
		postParams.put("params", DataSecret.encryptDES(params.toString()));
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new RechargeObtainOrderidResponse();
		}
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTrade_amount() {
		return trade_amount;
	}

	public void setTrade_amount(String trade_amount) {
		this.trade_amount = trade_amount;
	}

}