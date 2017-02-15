package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.EditMyAddressResponse;

/**
uerid	          用户ID	string	No	
isuse	           是否默认	string	YES	1默认 0非默认
address	          详细地址	string	No	
name	          姓名	    string	No	
post	          邮编	    string	No	
phonenumb	手机号码	string	YES	
telnumb	           电话号码	string	YES	
type	           操作类型	string	No	0新增 1删除 2修改
addressid	地址id	string	YES	 删除修改用

 */
public class EditMyAddressReq extends BaseCommReq {
	public enum AddressHandType {
		NONE, ADD, DEL, MODIFY
	}

	private EditMyAddressResponse response;

	private String userid;
	private String isuse = "";
	private String address;
	private String name;
	private String post = "";
	private String phonenumb;
	private String telnumb = "00";
	private String addressid;
	private AddressHandType handType = AddressHandType.NONE;

	@Override
	public String generUrl() {
		setTag("EditMyAddressReq");
		return NetWorkConfig.HTTP_HOST
				+ "/address/manager.action";
	}

	@Override
	protected void handPostParam() {
		postParams.put("uerid", getUserid());
		postParams.put("address", getAddress());
		postParams.put("name", getName());
		postParams.put("phonenumb", getPhonenumb());
		// if (!TextUtils.isEmpty(isuse)) {
		postParams.put("isuse", getIsuse());
		// }
		// if (!TextUtils.isEmpty(telnumb)) {
		postParams.put("telnumb", getTelnumb());
		// }
		// if (!TextUtils.isEmpty(post)) {
		postParams.put("post", getPost());
		// }
		switch (handType) {
		case ADD:
			postParams.put("type", "0");
			break;
		case DEL:
			postParams.put("type", "1");
			postParams.put("addressid", getAddressid());
			break;
		case MODIFY:
			postParams.put("type", "2");
			postParams.put("addressid", getAddressid());
			break;
		}
	}

	@Override
	public Class getResClass() {
		return EditMyAddressResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new EditMyAddressResponse();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhonenumb() {
		return phonenumb;
	}

	public void setPhonenumb(String phonenumb) {
		this.phonenumb = phonenumb;
	}

	public String getTelnumb() {
		return telnumb;
	}

	public void setTelnumb(String telnumb) {
		this.telnumb = telnumb;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public AddressHandType getHandType() {
		return handType;
	}

	public void setHandType(AddressHandType handType) {
		this.handType = handType;
	}

}