package com.sq.bxstore.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.MyAddressResponse;

/**
 userid	用户id	    string	No	通过用户ID查找
 isuse	是否默认地址	string	Yes	1默认 0非默认

 */
public class MyAddressReq extends BaseCommReq {
	private MyAddressResponse response;

	private String userid;
	private String isuse;

	@Override
	public String generUrl() {
		setTag("MyAddressReq");
		return NetWorkConfig.HTTP_HOST
				+ "/address/search.action?";
	}

	@Override
	protected void handPostParam() {
		postParams.put("userid", getUserid());
		postParams.put("isuse", getIsuse());
	}

	@Override
	public Class getResClass() {
		return MyAddressResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new MyAddressResponse();
		}
		return response;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

}