package com.sq.bxstore.net.request;

import org.json.JSONObject;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.WalletHistoryResponse;
import com.sq.bxstore.net.response.WithdrawResponse;
import com.sq.bxstore.utils.encryption.DataSecret;

/**
username			用户名	string	No	
user_dj_balance		提现金额	string	No	
services			服务名	string	No	user_tixian 该值固定
trade_type			提现方式	string	No	1 支付宝 2微信 3银联
pay_account				
pay_open_bank				
 */
public class WithdrawReq extends BaseCommReq {
	private WithdrawResponse response;

	private String username;
	private String user_dj_balance;
	private String services = "user_tixian";
	private String trade_type;
	private String pay_account;
	private String pay_open_bank;
	private JSONObject params = new JSONObject();
	@Override
	public String generUrl() {
		setTag("WithdrawReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return WalletHistoryResponse.class;
	}

	@Override
	protected void handPostParam() {
		try {
			params.put("username", getUsername());
			params.put("user_dj_balance", getUser_dj_balance());
			params.put("trade_type", getTrade_type());
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
			response = new WithdrawResponse();
		}
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_dj_balance() {
		return user_dj_balance;
	}

	public void setUser_dj_balance(String user_dj_balance) {
		this.user_dj_balance = user_dj_balance;
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

	public String getPay_account() {
		return pay_account;
	}

	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}

	public String getPay_open_bank() {
		return pay_open_bank;
	}

	public void setPay_open_bank(String pay_open_bank) {
		this.pay_open_bank = pay_open_bank;
	}

}