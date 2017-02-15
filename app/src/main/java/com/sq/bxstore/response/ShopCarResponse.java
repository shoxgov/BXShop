package com.sq.bxstore.response;

import java.util.List;

import android.text.TextUtils;
import android.util.Patterns;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.bean.PageInfo;
import com.sq.bxstore.net.BXBaseResponse;

public class ShopCarResponse extends BXBaseResponse {
	/*
	 * 
	 * {
	 * "message":"查询成功~",
	 * "result":"0",
	 * "data":[
	 * {
	 * "count":1,
	 * "goodsid":1,
	 * "goodsname":"三星（SAMSUNG）S24D360HL 23.6英寸PLS广视角窄边框LED背光液晶显示器",
	 * "goodsuse":"0",
	 * "id":2,
	 * "price":799.00,
	 * "storenumb":490,
	 * "userid":1
	 * }
	 * ],
	 * "ver":"v1.0"
	 * }
	 */
	private PageInfo page;
	private List<ShopItemInfo> data;

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public List<ShopItemInfo> getData() {
		return data;
	}

	public void setData(List<ShopItemInfo> data) {
		this.data = data;
	}


	/**
	 	id			购物车ID		string	No	
		goodsid		商品id		string	No	商品id
		count		数量			string	No	购买数量
		price		单价			string	No	商品单价
		goodsname	商品名称		string	No	
		storenumb	商品剩余库存	string	No	
		goodsuse	商品销售状态	string	No	0销售中  1非销售状态
	 */
	public class ShopItemInfo {
		private int count;
		private int goodsid;
		private int id;
		private int userid;
		private int storenumb;
		private float price;
		private String goodsname;
		private String goodsuse;
		/**
		 * 是否被选中
		 */
		private boolean check = false;
		private String img;

		public boolean isCheck() {
			return check;
		}

		public void setCheck(boolean check) {
			this.check = check;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getGoodsid() {
			return goodsid;
		}

		public void setGoodsid(int goodsid) {
			this.goodsid = goodsid;
		}

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

		public int getStorenumb() {
			return storenumb;
		}

		public void setStorenumb(int storenumb) {
			this.storenumb = storenumb;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public String getGoodsname() {
			return goodsname;
		}

		public void setGoodsname(String goodsname) {
			this.goodsname = goodsname;
		}

		public String getGoodsuse() {
			return goodsuse;
		}

		public void setGoodsuse(String goodsuse) {
			this.goodsuse = goodsuse;
		}

		public String getImg() {
			if (!TextUtils.isEmpty(img)
					&& !Patterns.WEB_URL.matcher(img).matches()) {
				return NetWorkConfig.IMAGEPATH + img;
			}
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

	}
}
