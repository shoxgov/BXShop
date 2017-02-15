package com.sq.bxstore.request;

import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.WeixinPayObtailOrderResponse;

public class WeixinPayObtailOrderReq extends BaseCommReq {
	private WeixinPayObtailOrderResponse response;

	@Override
	public String generUrl() {
		setTag("WeixinPayObtailOrderReq");
		return "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
	}

	@Override
	public Class getResClass() {
		return WeixinPayObtailOrderResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new WeixinPayObtailOrderResponse();
		}
		return response;
	}

}
