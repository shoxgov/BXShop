package com.sq.bxstore.response;

import com.sq.bxstore.net.BXBaseResponse;

/**
 * {"message":"成功","result":"0","data":"{\"createTime\":1483275204000,\"id\":1,\"identityCard\":\"220181198503070619\",\"identityCardValidity\":1484132836000,\"identityIssuing\":\"九台市公安局\",\"identityStatus\":1,\"loginPwd\":\"25D55AD283AA400AF464C76D713C07AD\",\"nickName\":\"权亦辰\",\"optionTime\":1483275204000,\"payPwd\":\"E10ADC3949BA59ABBE56E057F20F883E\",\"tixianStatus\":0,\"trueName\":\"yanlifeng\",\"userDjBalance\":0.00,\"userFxBalance\":1000.00,\"userGrade\":1,\"userJfBalance\":0.00,\"userKyBalance\":0.00,\"userMail\":\"yanlifeng@126.com\",\"userName\":\"yanlifeng\",\"userPhone\":\"13456783169\",\"userSex\":1,\"userStatus\":2}","ver":"v1.0"}
 * 
 * 
 * {"message":"成功","result":"0","data":"{"createTime":1483275204000,"id":1,"identityCard":"220181198503070619","identityCardValidity":1484132836000,"identityIssuing":"九台市公安局","identityStatus":1,"loginPwd":"25D55AD283AA400AF464C76D713C07AD","nickName":"权亦辰","optionTime":1483275204000,"payPwd":"E10ADC3949BA59ABBE56E057F20F883E","tixianStatus":0,"trueName":"yanlifeng","userDjBalance":0.00,"userFxBalance":1000.00,"userGrade":1,"userJfBalance":0.00,"userKyBalance":0.00,"userMail":"yanlifeng@126.com","userName":"yanlifeng","userPhone":"13456783169","userSex":1,"userStatus":2}","ver":"v1.0"}
 * 
{
"message":"成功",
"result":"0",
"data":
{
"createTime":1483275204000,
"id":1,
"identityCard":"220181198503070619",
"identityCardValidity":1484132836000,
"identityIssuing":"九台市公安局",
"identityStatus":1,
"loginPwd":"25D55AD283AA400AF464C76D713C07AD",
"nickName":"权亦辰",
"optionTime":1483275204000,
"payPwd":"E10ADC3949BA59ABBE56E057F20F883E",
"tixianStatus":0,
"trueName":"yanlifeng",
"userDjBalance":0.00,
"userFxBalance":1000.00,
"userGrade":1,
"userJfBalance":0.00,
"userKyBalance":0.00,
"userMail":"yanlifeng@126.com",
"userName":"yanlifeng",
"userPhone":"13456783169",
"userSex":1,
"userStatus":2
},
"ver":"v1.0"}
 */
public class LoginResponse extends BXBaseResponse {

	private boolean status;
	private CustomInfo data;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public CustomInfo getData() {
		return data;
	}

	public void setData(CustomInfo data) {
		this.data = data;
	}

	public class CustomInfo {
		private int id;
		private String identityCard;
		private String identityIssuing;
		private String identityStatus;
		private String nickName;
		private String trueName;
		private String userMail;
		private String userName;
		private String userPhone;
		private float userDjBalance;
		private float userFxBalance;
		private float userJfBalance;
		private float userKyBalance;
		private int userGrade;
		private int userSex;
		private int userStatus;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getIdentityCard() {
			return identityCard;
		}

		public void setIdentityCard(String identityCard) {
			this.identityCard = identityCard;
		}

		public String getIdentityIssuing() {
			return identityIssuing;
		}

		public void setIdentityIssuing(String identityIssuing) {
			this.identityIssuing = identityIssuing;
		}

		public String getIdentityStatus() {
			return identityStatus;
		}

		public void setIdentityStatus(String identityStatus) {
			this.identityStatus = identityStatus;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getTrueName() {
			return trueName;
		}

		public void setTrueName(String trueName) {
			this.trueName = trueName;
		}

		public String getUserMail() {
			return userMail;
		}

		public void setUserMail(String userMail) {
			this.userMail = userMail;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPhone() {
			return userPhone;
		}

		public void setUserPhone(String userPhone) {
			this.userPhone = userPhone;
		}

		public float getUserDjBalance() {
			return userDjBalance;
		}

		public void setUserDjBalance(float userDjBalance) {
			this.userDjBalance = userDjBalance;
		}

		public float getUserFxBalance() {
			return userFxBalance;
		}

		public void setUserFxBalance(float userFxBalance) {
			this.userFxBalance = userFxBalance;
		}

		public float getUserJfBalance() {
			return userJfBalance;
		}

		public void setUserJfBalance(float userJfBalance) {
			this.userJfBalance = userJfBalance;
		}

		public float getUserKyBalance() {
			return userKyBalance;
		}

		public void setUserKyBalance(float userKyBalance) {
			this.userKyBalance = userKyBalance;
		}

		public int getUserGrade() {
			return userGrade;
		}

		public void setUserGrade(int userGrade) {
			this.userGrade = userGrade;
		}

		public int getUserSex() {
			return userSex;
		}

		public void setUserSex(int userSex) {
			this.userSex = userSex;
		}

		public int getUserStatus() {
			return userStatus;
		}

		public void setUserStatus(int userStatus) {
			this.userStatus = userStatus;
		}

	}

}
