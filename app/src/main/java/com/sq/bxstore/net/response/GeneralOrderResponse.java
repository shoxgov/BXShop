package com.sq.bxstore.net.response;

import java.util.List;

import com.sq.bxstore.bean.GoodsInfo;
import com.sq.bxstore.net.BXBaseResponse;

public class GeneralOrderResponse extends BXBaseResponse{
/*	
    id	          	订单id		string	YES	订单ID
	total			总额			string	YES	订单总额
	counts			件数			string	YES	订单当前状态
	paytype			支付方式		string	YES	订单购买总件数
	outway			运送方式		string	YES	购买的商品列表
	orderstatus		订单状态		string	YES	商品图片
	deliveryNumb	快递单号		string	YES	商品名称
	createTime		下单时间		string	YES	
	deliveryTime	物流发送时间	string	YES	
	address			地址			string	YES	
	name			名称			string	YES	
	postcode		邮编			string	YES	
	contactnumb		联系方式		string	YES	
	invoice			是否需要发票	string	YES	
	companyname		抬头			string	YES	
	content			发票内容		string	YES	
	remark			备注			string	YES	
	goodslist		商品(goodsid)	string	YES	
	数量(count)					string	YES	
	单价(price)					string	YES	
	商品图片(goodsimg)				string	YES	
	商品名称(goodsname)			string	YES	
	
	
	
	{
	"message":"查询成功",
	"result":"0",
	"data":[
	{
	"accountAmount":0.00,
	"cash":0.00,
	"commisionCharge":0.00,
	"counts":1,
	"createTime":1478501886000,
	"deliveryNumb":"111111",
	"deliveryTime":1478501886000,
	"id":1,
	"orderdetail":[
	{"count":2,
	"goodsid":1,
	"goodsimg":"http://127.0.0.1:8080/dev/upload/562b59d4-939c-4502-8565-6be961e575951478160691328.png",
	"goodsname":"三星（SAMSUNG）S24D360HL 23.6英寸PLS广视角窄边框LED背光液晶显示器",
	"id":1,
	"orderid":1,
	"price":189
	},
	{"count":5,
	"goodsid":2,
	"goodsimg":"http://127.0.0.1:8080/dev/upload/c6801163-029a-4ab0-8377-78240d3b37171478068160440.png",
	"goodsname":"test",
	"id":2,
	"orderid":1,
	"price":551
	},
	{"count":3,
	"goodsid":3,
	"goodsimg":"http://127.0.0.1:8080/dev/upload/fd507524-38b2-4618-9e77-3aff32792803QQ截图20161101143829.png",
	"goodsname":"test2",
	"id":3,
	"orderid":1,
	"price":210
	}
	],
	"orderstatus":2,
	"outway":"1",
	"paynumb":"11",
	"paytype":1,
	"total":0,
	"userid":2
	}
	],
	"ver":"v1.0"}
*/
	private List<OrderDetail> data;

	public List<OrderDetail> getData() {
		return data;
	}

	public void setData(List<OrderDetail> data) {
		this.data = data;
	}

	public class OrderDetail {
		private float accountAmount;
		private float cash;
		private float commisionCharge;
		private int counts;
		private int orderstatus;
		private int paytype;
		private int userid;
		private int total;
		private int id;
		private long createTime;
		private long deliveryTime;
		private String deliveryNumb;
		private String outway;
		private String paynumb;
		private String name;// 地址
		private String address;// 地址
		private String content;// 发票内容
		private String remark;// 备注
		private int invoice;
		private List<GoodsInfo> orderdetail;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public float getAccountAmount() {
			return accountAmount;
		}

		public void setAccountAmount(float accountAmount) {
			this.accountAmount = accountAmount;
		}

		public float getCash() {
			return cash;
		}

		public void setCash(float cash) {
			this.cash = cash;
		}

		public float getCommisionCharge() {
			return commisionCharge;
		}

		public void setCommisionCharge(float commisionCharge) {
			this.commisionCharge = commisionCharge;
		}

		public int getCounts() {
			return counts;
		}

		public void setCounts(int counts) {
			this.counts = counts;
		}

		public int getOrderstatus() {
			return orderstatus;
		}

		public void setOrderstatus(int orderstatus) {
			this.orderstatus = orderstatus;
		}

		public int getPaytype() {
			return paytype;
		}

		public void setPaytype(int paytype) {
			this.paytype = paytype;
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public long getDeliveryTime() {
			return deliveryTime;
		}

		public void setDeliveryTime(long deliveryTime) {
			this.deliveryTime = deliveryTime;
		}

		public String getDeliveryNumb() {
			return deliveryNumb;
		}

		public void setDeliveryNumb(String deliveryNumb) {
			this.deliveryNumb = deliveryNumb;
		}

		public String getOutway() {
			return outway;
		}

		public void setOutway(String outway) {
			this.outway = outway;
		}

		public String getPaynumb() {
			return paynumb;
		}

		public void setPaynumb(String paynumb) {
			this.paynumb = paynumb;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public int getInvoice() {
			return invoice;
		}

		public void setInvoice(int invoice) {
			this.invoice = invoice;
		}

		public List<GoodsInfo> getGoodsdetail() {
			return orderdetail;
		}

		public void setOrderdetail(List<GoodsInfo> orderdetail) {
			this.orderdetail = orderdetail;
		}

	}
	
	
}
