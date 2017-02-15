package com.sq.bxstore.net.response;

import com.sq.bxstore.net.BXBaseResponse;

/**
{
"message":"操作成功",
"result":"0",
"data":
{
"accountAmount":98.0,
"address":"国际足坛",
"cash":98.0,
"commisionCharge":0.0,
"contactnumb":"177755256858",
"content":"办公用品",
"counts":1,
"id":8,
"invoice":"1",
"name":"丁甘婷",
"orderstatus":"1",
"paytype":"1",
"positionid":1,
"total":98.0,
"userid":1
},
"ver":"v1.0"}
 */
public class AccountGeneralOrderResponse extends BXBaseResponse {

	private OrderInfo data;

	public OrderInfo getData() {
		return data;
	}

	public void setData(OrderInfo data) {
		this.data = data;
	}

	public class OrderInfo {
		private int id;
		private int userid;
		private int positionid;
		private int counts;
		private float total;
		private float commisionCharge;
		private float cash;
		private float accountAmount;
		private String paytype;
		private String orderstatus;
		private String name;
		private String invoice;
		private String content;
		private String contactnumb;
		private String address;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}

		public int getPositionid() {
			return positionid;
		}

		public void setPositionid(int positionid) {
			this.positionid = positionid;
		}

		public int getCounts() {
			return counts;
		}

		public void setCounts(int counts) {
			this.counts = counts;
		}

		public float getTotal() {
			return total;
		}

		public void setTotal(float total) {
			this.total = total;
		}

		public float getCommisionCharge() {
			return commisionCharge;
		}

		public void setCommisionCharge(float commisionCharge) {
			this.commisionCharge = commisionCharge;
		}

		public float getCash() {
			return cash;
		}

		public void setCash(float cash) {
			this.cash = cash;
		}

		public float getAccountAmount() {
			return accountAmount;
		}

		public void setAccountAmount(float accountAmount) {
			this.accountAmount = accountAmount;
		}

		public String getPaytype() {
			return paytype;
		}

		public void setPaytype(String paytype) {
			this.paytype = paytype;
		}

		public String getOrderstatus() {
			return orderstatus;
		}

		public void setOrderstatus(String orderstatus) {
			this.orderstatus = orderstatus;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInvoice() {
			return invoice;
		}

		public void setInvoice(String invoice) {
			this.invoice = invoice;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContactnumb() {
			return contactnumb;
		}

		public void setContactnumb(String contactnumb) {
			this.contactnumb = contactnumb;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	}
}
