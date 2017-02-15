package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.PersonUpdateResponse;

/**
username				用户名		string	No		修改用户密码
login_pwd				登陆密码		string	yes		修改此3项 申请提现 需要先调用短信接口拿到短信验证码	修改用户支付密码
pay_pwd					支付密码		string	yes		修改实名认证
user_phone				电话			string	yes		修改手机号
user_dj_balance			冻结金额		string	yes		申请提现时下列必须	提现
tixian_status			体现申请为1		string	yes		
nick_name				昵称			string	yes		
user_mail				邮箱			string	yes		
user_sex				性别			string	yes		
true_name				真实姓名		string	yes		身份认证时下列必须	
identity_card			身份证号		string	yes		
identity_card_validity	有效期		string	yes		
identity_issuing		发证机关		string	yes		
identity_status			申请认证为1		string	yes		
services				服务名		string	No		user_update 该值固定	
 */
public class PersonUpdateReq extends BaseCommReq {
	private PersonUpdateResponse response;

	private String username;
	private String login_pwd;
	private String pay_pwd;
	private String user_phone;
	private String user_dj_balance;
	private String tixian_status;
	private String nick_name;
	private String user_mail;
	private String user_sex;
	private String true_name;
	private String identity_card;
	private String identity_card_validity;
	private String identity_issuing;
	private String identity_status;
	private String services;

	@Override
	public String generUrl() {
		setTag("PersonUpdateReq");
		return NetWorkConfig.HTTPS;
	}

	@Override
	public Class getResClass() {
		return PersonUpdateResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new PersonUpdateResponse();
		}
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLogin_pwd() {
		return login_pwd;
	}

	public void setLogin_pwd(String login_pwd) {
		this.login_pwd = login_pwd;
	}

	public String getPay_pwd() {
		return pay_pwd;
	}

	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_dj_balance() {
		return user_dj_balance;
	}

	public void setUser_dj_balance(String user_dj_balance) {
		this.user_dj_balance = user_dj_balance;
	}

	public String getTixian_status() {
		return tixian_status;
	}

	public void setTixian_status(String tixian_status) {
		this.tixian_status = tixian_status;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public String getTrue_name() {
		return true_name;
	}

	public void setTrue_name(String true_name) {
		this.true_name = true_name;
	}

	public String getIdentity_card() {
		return identity_card;
	}

	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}

	public String getIdentity_card_validity() {
		return identity_card_validity;
	}

	public void setIdentity_card_validity(String identity_card_validity) {
		this.identity_card_validity = identity_card_validity;
	}

	public String getIdentity_issuing() {
		return identity_issuing;
	}

	public void setIdentity_issuing(String identity_issuing) {
		this.identity_issuing = identity_issuing;
	}

	public String getIdentity_status() {
		return identity_status;
	}

	public void setIdentity_status(String identity_status) {
		this.identity_status = identity_status;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

}