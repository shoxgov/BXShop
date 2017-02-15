package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.GoodsDetailResponse;

public class GoodsDetailReq extends BaseCommReq {
	// 商品查询接口 goodsid 商品ID string No 有指定商品id 则返回指定商品 ID传空则返回空
	private String goodsid;
	private GoodsDetailResponse response;

	@Override
	public String generUrl() {
		setTag("GoodsDetailReq");
		return NetWorkConfig.HTTP_HOST + "/goods/search.action";
	}

	@Override
	protected void handPostParam() {
		postParams.put("goodsid", getGoodsid());
	}

	@Override
	public Class getResClass() {
		return GoodsDetailResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new GoodsDetailResponse();
		}
		return response;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

}