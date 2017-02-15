package com.sq.bxstore.net.request;

import android.text.TextUtils;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.HandleShopCarResponse;

/**
userid		用户id	string	No	用户id
type		操作类型	string	No	0新增(id不用传) 1删除(goodsid不用传) 2修改()
goodsid		商品id	string	No	商品id
count		数量	    string	No	购买数量
id	                      唯一主键ID	string	No	修改时使用
goodslist	产品id列表	string	No	批量删除时使用 ,分割 形如：{"goodslist":"1,2,3"}
 */
public class HandleShopCarReq extends BaseCommReq {
	private HandleShopCarResponse response;

	private String userid;
	private HandShopType type;
	private String goodsid;
	private String count;
	private String id;
	private String goodslist;

	public enum HandShopType {
		NONE, ADD, DEL, MODIFY
	}
	@Override
	public String generUrl() {
		setTag("HandleShopCarReq");
		return NetWorkConfig.HTTP_HOST + "/shopcar/manager.action";
	}

	@Override
	protected void handPostParam() {
		postParams.put("userid", getUserid());
		postParams.put("count", getCount());
		switch (getType()) {
		case ADD:
			postParams.put("type", "0");
			postParams.put("goodsid", getGoodsid());
			break;
		case DEL:
			postParams.put("type", "1");
			if (!TextUtils.isEmpty(getId())) {
				postParams.put("id", getId());
			}
			if (!TextUtils.isEmpty(getGoodslist())) {
				postParams.put("goodslist", getGoodslist());
			}
			break;
		case MODIFY:
			postParams.put("type", "2");
			postParams.put("id", getId());
			postParams.put("goodsid", getGoodsid());
			break;
		}
	}

	@Override
	public Class getResClass() {
		return HandleShopCarResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new HandleShopCarResponse();
		}
		return response;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public HandShopType getType() {
		return type;
	}

	public void setType(HandShopType type) {
		this.type = type;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodslist() {
		return goodslist;
	}

	public void setGoodslist(String goodslist) {
		this.goodslist = goodslist;
	}

}