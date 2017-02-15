package com.sq.bxstore.net.response;

import java.util.List;

import com.sq.bxstore.net.BXBaseResponse;

/**
code		code	
name		名称	
parentcode	父code	
remark		备注	
order		顺序	

 */
public class CatalogsResponse extends BXBaseResponse{
	/*
	 * {"message":"查询失败~","result":"1","data":"","ver":"v1.0"}
	 * 
	 * {
	 * "message":"查询成功~",
	 * "result":"0",
	 * "data":
	 * [
	 * {
	 * "code":"2",
	 * "id":2,
	 * "name":"热销商品",
	 * "order":2,
	 * "type":"ADV_BANNER"
	 * },
	 * {
	 * "code":"1",
	 * "id":1,
	 * "name":"最新上架",
	 * "order":1,
	 * "type":"ADV_BANNER"
	 * }
	 * ],
	 * "ver":"v1.0"}
	 * */
	private List<CatalogInfo> data;
	
	
	public List<CatalogInfo> getData() {
		return data;
	}


	public void setData(List<CatalogInfo> data) {
		this.data = data;
	}


	public class CatalogInfo {
		private String code;
		private String name;
		private String type;
		private int id;
		private int order;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}
	}
}
