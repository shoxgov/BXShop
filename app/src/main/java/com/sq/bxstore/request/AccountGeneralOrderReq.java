package com.sq.bxstore.request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.bxBean.GoodsInfoParcelable;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.AccountGeneralOrderResponse;

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
{
	goodsid		商品id			string	YES
	price		价格				string	YES	
	count		数量				string	YES	
}
type			操作类型			string	No	0新增 1删除 
orderid			订单id			string	YES	删除修改需要提供订单id
 */
public class AccountGeneralOrderReq extends BaseCommReq {
	private AccountGeneralOrderResponse response;

	private String userid ;
	private String total ;
	private String counts ;
	private String paytype ;
	private String positionid ;
	private String invoice ;
	private String companyname ;
	private String content ;
	private String remark ;
	private String commisionCharge ;
	private String cash ;
	private String accountAmount ;
	private String type ;
	private String orderid ;
	private List<GoodsInfoParcelable> goodslist;
	
	@Override
	public String generUrl() {
		setTag("AccountGeneralOrderReq");
		return NetWorkConfig.HTTP_HOST +"/order/manager.do";
	}

	@Override
	public Class getResClass() {
		return AccountGeneralOrderResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new AccountGeneralOrderResponse();
		}
		return response;
	}

	@Override
	protected void handPostParam() {
		postParams.put("userid", getUserid());
		postParams.put("total", getTotal());
		postParams.put("counts", getCounts());
		postParams.put("paytype", getPaytype());
		postParams.put("positionid", getPositionid());
		postParams.put("invoice", getInvoice());
		postParams.put("companyname", getCompanyname());
		postParams.put("content", getContent());
		postParams.put("remark", getRemark());
		postParams.put("commisionCharge", getCommisionCharge());
		postParams.put("cash", getCash());
		postParams.put("accountAmount", getAccountAmount());
		postParams.put("type", getType());
		if (!TextUtils.isEmpty(getOrderid())) {
			postParams.put("orderid", getOrderid());
		}
		postParams.put("goodslist", getGoodslist().toString());
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public List<String> getGoodslist() {
		List<String> goodsList = new ArrayList<String>();
		for(GoodsInfoParcelable gip : goodslist){
			JSONObject goods = new JSONObject();
			try {
				goods.put("goodsid", gip.getGoodsid());
				goods.put("price", gip.getPrice());
				goods.put("count", gip.getCount());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			goodsList.add(goods.toString());
		}
		return goodsList;
	}

	public void setGoodslist(List<GoodsInfoParcelable> goodslist) {
		this.goodslist = goodslist;
	}

}