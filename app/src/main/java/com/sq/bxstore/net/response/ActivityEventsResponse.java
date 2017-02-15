package com.sq.bxstore.net.response;

import java.util.List;

import android.text.TextUtils;
import android.util.Patterns;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BXBaseResponse;

public class ActivityEventsResponse extends BXBaseResponse{
	/*
{
"message":"查询成功~",
"result":"0",
"data":{
"goodslist":[
{
"goodsid":10,
"goodsimg":"20161209/2b7570d5-fd45-4320-ad36-1553f48638a71481250306718.jpg",
"goodsname":"黛璐莎美版升级hifu超声刀"
},
{
"goodsid":13,
"goodsimg":"20161212/14125419-5203-403d-a39b-43a2695e16af1481528642296.jpg",
"goodsname":"帮宝适 Pampers 超薄干爽 婴儿拉拉裤 "
}
],
"id":1,
"memo":"“光棍村”如今变“俄罗斯新娘村” 单身狗们你们还在做什么？",
"minpicture":"20161208/b98cf067-3030-4546-ac25-5b6be376f4471481160830265.png",
"name":"测试20161208",
"picture":"20161208/fa4b8c6e-ea48-4687-9781-95a231c6a6d41481160830265.jpg"
},
"ver":"v1.0"
}
	 */
	private EventsData data;
	
	public EventsData getData() {
		return data;
	}

	public void setData(EventsData data) {
		this.data = data;
	}

	public class EventsData {
		private int id;
		private String memo;
		private String minpicture;
		private String name;
		private String picture;
		private List<GoodsListInfo> goodslist; 

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public String getMinpicture() {
			return minpicture;
		}

		public void setMinpicture(String minpicture) {
			this.minpicture = minpicture;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPicture() {
			if (!TextUtils.isEmpty(picture)
					&& !Patterns.WEB_URL.matcher(picture).matches()) {
				return NetWorkConfig.IMAGEPATH + picture;
			}
			return picture;
		}

		public void setPicture(String picture) {
			this.picture = picture;
		}

		public List<GoodsListInfo> getGoodslist() {
			return goodslist;
		}

		public void setGoodslist(List<GoodsListInfo> goodslist) {
			this.goodslist = goodslist;
		}

	}

	public class GoodsListInfo {
		private int goodsid;
		private String price;
		private String goodsimg;
		private String goodsname;

		public int getGoodsid() {
			return goodsid;
		}

		public void setGoodsid(int goodsid) {
			this.goodsid = goodsid;
		}

		public String getPrice() {
			if (TextUtils.isEmpty(price)) {
				return "0";
			}
			return price.replace(",", "");
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getGoodsimg() {
			return goodsimg;
		}

		public void setGoodsimg(String goodsimg) {
			this.goodsimg = goodsimg;
		}

		public String getGoodsname() {
			return goodsname;
		}

		public void setGoodsname(String goodsname) {
			this.goodsname = goodsname;
		}

	}
}
