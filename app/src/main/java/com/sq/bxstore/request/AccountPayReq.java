package com.sq.bxstore.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.AccountPayResponse;

/**
userid	用户id	string	No
orderid	订单号	string	No
 */
public class AccountPayReq extends BaseCommReq {
	private AccountPayResponse response;

	private String userid ;
	private String orderid ;
	
	@Override
	public String generUrl() {
		setTag("AccountPayReq");
		return NetWorkConfig.HTTP_HOST +"/order/pay.do";
	}

	@Override
	public Class getResClass() {
		return AccountPayResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new AccountPayResponse();
		}
		return response;
	}

	@Override
	protected void handPostParam() {
		postParams.put("userid", getUserid());
		postParams.put("orderid", getOrderid());
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}