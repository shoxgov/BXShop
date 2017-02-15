package com.sq.bxstore.request;

import java.util.Date;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.RegisterResponse;
import com.sq.bxstore.utils.Utils;

/**
 * @author http://vip.neigrp.com/api_receiver.php?ref_username=demos
 * &username=test111
 * &name=account+1
 * &country=CN
 * &email=testing%40neigrp.com
 * &password=1111111
 * &ic=123456789
 * &phone=112233445566
 * &dob=2016-09-13
 * &gender=f
 * &bank_name=www
 * &bank_account=eee
 * &bank_account_holder=rr
 * &bank_address=tt
 * &bank_swift_code=yy
 * &api_id=8
 * &type=mall.registration
 * &signature=321ec1c40ea43151050b75385953fad4
 */
public class RegisterReq extends BaseCommReq {
	private RegisterResponse response;

	private String ref_username;
	private String username;
	private String name;
	private String country;
	private String email;
	private String password;
	private String ic;
	private String phone;
	private String dob;
	private String gender;
	private String bank_name;
	private String bank_account;
	private String bank_account_holder;
	private String bank_address;
	private String bank_swift_code;
	private int api_id = 8;
	private String type = "mall.registration";
	// private String secreat_key;
	private String signature;
	//  md5(ref_username + username + name + country + email + password + ic + phone dob + gender 
	//      + bank_name + bank_account + bank_account_holder + bank_address + bank_swift_code 
	//      + api_id + type + secreat key)

	@Override
	public String generUrl() {
		setTag("RegisterReq");
		return NetWorkConfig.HTTPS + "ref_username=" + getRef_username()
				+ "&username=" + getUsername() + "&api_id=" + api_id + "&type="
				+ type + "&name=" + getName() + "&country=" + getCountry()
				+ "&email=" + getEmail() + "&password=" + getPassword()
				+ "&ic=" + getIc() + "&phone=" + getPhone() + "&dob="
				+ new Date() + "&gender=" + getGender() + "&bank_name="
				+ getBank_name() + "&bank_account=" + getBank_account()
				+ "&bank_account_holder=" + getBank_account_holder()
				+ "&bank_address=" + getBank_address() + "&bank_swift_code="
				+ getBank_swift_code() + "&signature=" + getSignature();
	}

	@Override
	public Class getResClass() {
		return RegisterResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new RegisterResponse();
		}
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getRef_username() {
		return ref_username;
	}

	public void setRef_username(String ref_username) {
		this.ref_username = ref_username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getBank_account_holder() {
		return bank_account_holder;
	}

	public void setBank_account_holder(String bank_account_holder) {
		this.bank_account_holder = bank_account_holder;
	}

	public String getBank_address() {
		return bank_address;
	}

	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}

	public String getBank_swift_code() {
		return bank_swift_code;
	}

	public void setBank_swift_code(String bank_swift_code) {
		this.bank_swift_code = bank_swift_code;
	}
//  md5(ref_username + username + name + country + email + password + ic + phone dob + gender 
	//      + bank_name + bank_account + bank_account_holder + bank_address + bank_swift_code 
	//      + api_id + type + secreat key)
	public String getSignature() {
		return Utils.getMd5((ref_username + username + name + country + email
				+ password + ic + phone + dob + gender + bank_name
				+ bank_account + bank_account_holder + bank_address
				+ bank_swift_code + api_id + type + UserInfoBean.secreatkey)
				.getBytes());
	}

}