package com.sq.bxstore.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.GeneralOrderResponse;

/**
userid			用户id 			string	YES	新增时需要传递的内容
total			总额				string	YES	
counts			件数				string	YES	
paytype			支付方式			string	YES	
positionid		地址id			string	YES	
invoice			是否需要发票		string	YES	
companyname		抬头				string	YES	
content			发票内容			string	YES	
remark			备注				string	YES	
commisionCharge	手续费			string	YES	
cash			现金金额			string	YES	
accountAmount	账户金额			string	YES	
goodslist		
	(goodsid)	商品id			string	YES	
	(price)		价格				string	YES	
	(count)		数量				string	YES	
type			操作类型			string	No	0新增 1删除 
orderid			订单id			string	YES	删除修改需要提供订单id

 */
public class GeneralOrderReq extends BaseCommReq {
	private GeneralOrderResponse response;

	private String userid;
	private String orderid;
	private String total;
	private String counts;
	private String paytype;
	private String positionid;
	private String invoice;
	private String companyname;
	private String content;
	private String remark;
	private String commisionCharge;
	private String cash;
	private String accountAmount;
	private String type;
	// goodslist
	private String goodsid;
	private String price;
	private String count;

	@Override
	public String generUrl() {
		setTag("GeneralOrderReq");
		return NetWorkConfig.HTTP_HOST + "/order/manager.do";
	}

	@Override
	protected void handPostParam() {
		postParams.put("type", getType());// 0新增 1删除 
		postParams.put("userid", getUserid());// 查询我的订单列表时传
		if (getType().equals("0")) {
			postParams.put("total", getTotal());//
			postParams.put("counts", getCount());//
			postParams.put("paytype", getPaytype());//
			postParams.put("positionid", getPositionid());//
			postParams.put("invoice", getInvoice());//
			postParams.put("companyname", getCompanyname());//
			postParams.put("content", getContent());//
			postParams.put("remark", getRemark());//
			postParams.put("commisionCharge", getCommisionCharge());//
			postParams.put("cash", getCash());//
			postParams.put("accountAmount", getAccountAmount());//
			postParams.put("goodslist", getGoodslist());//
		} else {
			postParams.put("orderid", getOrderid());// 删除修改需要提供订单id
		}
	}

	@Override
	public Class getResClass() {
		return GeneralOrderResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new GeneralOrderResponse();
		}
		return response;
	}
	
	public String getGoodslist(){
		return "{}";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPositionid() {
		return positionid;
	}

	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
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

	public String getCommisionCharge() {
		return commisionCharge;
	}

	public void setCommisionCharge(String commisionCharge) {
		this.commisionCharge = commisionCharge;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(String accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}