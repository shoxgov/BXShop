package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.CatalogsResponse;

/**
code	type类型	string	No	
 */
public class CatalogsReq extends BaseCommReq {
	private CatalogsResponse response;

	private String code = "ADV_BANNER";//"code":"ADV_BANNER"

	@Override
	public String generUrl() {
		setTag("CatalogsReq");
		return NetWorkConfig.HTTP_HOST + "/adevent/searchpgm.action";
	}
	
	@Override
	protected void handPostParam() {
		postParams.put("code", code);
	}

	@Override
	public Class getResClass() {
		return CatalogsResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new CatalogsResponse();
		}
		return response;
	}

}