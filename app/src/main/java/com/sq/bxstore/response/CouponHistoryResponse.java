package com.sq.bxstore.response;

import java.util.List;

import com.sq.bxstore.bean.CouponInfo;
import com.sq.bxstore.net.BXBaseResponse;

public class CouponHistoryResponse extends BXBaseResponse{
/*	
{
    "message":"连接接口成功",
    "result":"0",
    "data":[
        {
            "couponBlance":10,
            "couponCreatdate":1483509933000,
            "couponExpirydate":1486003654000,
            "coupond":1,
            "couponName":"10元优惠券",
            "couponStatus":"0",
            "couponXfBalance":100,
            "id":1,
            "optionAdminid":0,
            "optionTime":1483325272000,
            "userId":1,
            "userName":"yanlifeng"
        },
        {
            "couponBlance":20,
            "couponCreatdate":1483509950000,
            "couponExpirydate":1486005945000,
            "couponId":2,
            "couponName":"20元券",
            "couponStatus":"0",
            "couponXfBalance":200,
            "id":2,
            "optionAdminid":0,
            "optionTime":148600594000,
            "userId":1,
            "userName":"yanlifeng"
        }
    ],
    "ver":"v1.0"
}			
	*/
	private List<CouponInfo> data;

	public List<CouponInfo> getData() {
		return data;
	}

	public void setData(List<CouponInfo> data) {
		this.data = data;
	}

}
