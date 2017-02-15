package com.sq.bxstore.response;

import java.util.List;

import com.sq.bxstore.bean.AddressInfo;
import com.sq.bxstore.net.BXBaseResponse;

public class MyAddressResponse extends BXBaseResponse{
	/*{"message":"执行成功","result":"0","data":[],"ver":"v1.0"}
	 * 
	 * {
    "message": "执行成功",
    "result": "0",
    "data": [
        {
            "address": "长沙市开福区四方坪",
            "id": 1,
            "isuse": 0,
            "name": "陈成",
            "phonenumb": "18101954697",
            "post": "20001",
            "telnumb": "0213331157",
            "uerid": 1,
            "username": "森林"
        },
        {
            "address": "广州市白云区",
            "id": 2,
            "isuse": 0,
            "name": "陈成",
            "phonenumb": "18074548620",
            "post": "21000",
            "telnumb": "0213331157",
            "uerid": 1,
            "username": "森林"
        }
    ],
    "ver": "v1.0"
}
*/
	private List<AddressInfo> data;
	
	
	public List<AddressInfo> getData() {
		return data;
	}


	public void setData(List<AddressInfo> data) {
		this.data = data;
	}

}
