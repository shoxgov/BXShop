package com.sq.bxstore.net.response;

import com.sq.bxstore.net.BXBaseResponse;

/*
{
"message":"连接接口成功",
"result":"0",
"data":
{
"createTime":1486540253119,
"optionAdminid":0,
"optionType":"1",
"purseStatus":"2",
"purseType":1,
"tradeAmount":100,
"tradeSn":"ZS1000000221325390",
"tradeState":"0",
"tradeType":"1",
"userAmount":1000.00,
"userId":1
}
"ver":"v1.0"
}
 */
public class RechargeObtainOrderidResponse extends BXBaseResponse {
	private RechargeOrderInfo data;

	public RechargeOrderInfo getData() {
		return data;
	}

	public void setData(RechargeOrderInfo data) {
		this.data = data;
	}

	public class RechargeOrderInfo {
		private long createTime;
		private int optionAdminid;
		private int purseType;
		private int tradeAmount;
		private int userId;
		private float userAmount;
		private String optionType;
		private String purseStatus;
		private String tradeSn;
		private String tradeState;
		private String tradeType;

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public int getOptionAdminid() {
			return optionAdminid;
		}

		public void setOptionAdminid(int optionAdminid) {
			this.optionAdminid = optionAdminid;
		}

		public int getPurseType() {
			return purseType;
		}

		public void setPurseType(int purseType) {
			this.purseType = purseType;
		}

		public int getTradeAmount() {
			return tradeAmount;
		}

		public void setTradeAmount(int tradeAmount) {
			this.tradeAmount = tradeAmount;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public float getUserAmount() {
			return userAmount;
		}

		public void setUserAmount(float userAmount) {
			this.userAmount = userAmount;
		}

		public String getOptionType() {
			return optionType;
		}

		public void setOptionType(String optionType) {
			this.optionType = optionType;
		}

		public String getPurseStatus() {
			return purseStatus;
		}

		public void setPurseStatus(String purseStatus) {
			this.purseStatus = purseStatus;
		}

		public String getTradeSn() {
			return tradeSn;
		}

		public void setTradeSn(String tradeSn) {
			this.tradeSn = tradeSn;
		}

		public String getTradeState() {
			return tradeState;
		}

		public void setTradeState(String tradeState) {
			this.tradeState = tradeState;
		}

		public String getTradeType() {
			return tradeType;
		}

		public void setTradeType(String tradeType) {
			this.tradeType = tradeType;
		}

	}
}
