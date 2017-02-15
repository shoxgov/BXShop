package com.sq.bxstore.net.request;

import org.json.JSONObject;

import android.text.TextUtils;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.CouponHistoryResponse;
import com.sq.bxstore.utils.encryption.DataSecret;

/**
 * username 		用户名 	string No services 服务名 string No user_getcouponlist该值固定
 * coupon_status 	优惠券状态 	string No 0未使用 1已使用 2作废 查所有就不用传
 */
public class CouponHistoryReq extends BaseCommReq {
	private CouponHistoryResponse response;

	private String username;
	private String coupon_status;
	private String services = "user_getcouponlist";
	private JSONObject params = new JSONObject();

	@Override
	public String generUrl() {
		setTag("CouponHistoryReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return CouponHistoryResponse.class;
	}

	@Override
	protected void handPostParam() {
		try {
			params.put("services", services);
			if(!TextUtils.isEmpty(getCoupon_status())){
				params.put("coupon_status", getCoupon_status());
			}
			params.put("username", getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		postParams.put("params", DataSecret.encryptDES(params.toString()));
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new CouponHistoryResponse();
		}
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCoupon_status() {
		return coupon_status;
	}

	public void setCoupon_status(String coupon_status) {
		this.coupon_status = coupon_status;
	}

}