package com.sq.bxstore.net.response;

import java.math.BigDecimal;
import java.util.List;

import com.sq.bxstore.bean.PageInfo;
import com.sq.bxstore.net.BXBaseResponse;

public class GoodsListResponse extends BXBaseResponse {
	/*
	 * {
	 *{
	 *"message":"查询成功",
	 *"result":"0",
	 *"data":[
	       {
            "brand":9,
            "gfullname":"test",
            "gname":"test",
            "goodimg":"http://127.0.0.1:8080/dev/upload/c6801163-029a-4ab0-8377-78240d3b37171478068160440.png",
            "id":2,
            "img1":"http://127.0.0.1:8080/dev/upload/a9600545-ce8a-4b9d-8b87-88835b682a2e1478068644730.png",
            "img2":"http://127.0.0.1:8080/dev/upload/0d9a363a-de96-4a09-ab96-5635cd4518391478070992135.png",
            "img3":"",
            "isuse":"0",
            "price":123,
            "storenumb":0
        },
	 *],
	 *"ver":"v1.0"
	 *}
	 * }
	 */
	private PageInfo page;
	private List<GoodsGeneralData> data;
	
	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public List<GoodsGeneralData> getData() {
		return data;
	}

	public void setData(List<GoodsGeneralData> data) {
		this.data = data;
	}

	public class GoodsGeneralData {
		private int id;

		private String gname;

		private BigDecimal price;

		private String brand;

		private String goodimglist;

		private String gfullname;

		private Integer storenumb;
		private int isuse;

		private String goodimg;

		private String detail;

		private String img1;

		private String img2;

		private String img3;

		private String img4;

		private String img5;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getGname() {
			return gname;
		}

		public void setGname(String gname) {
			this.gname = gname;
		}

		public float getPrice() {
			return price.floatValue();
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getGoodimglist() {
			return goodimglist;
		}

		public void setGoodimglist(String goodimglist) {
			this.goodimglist = goodimglist;
		}

		public String getGfullname() {
			return gfullname;
		}

		public void setGfullname(String gfullname) {
			this.gfullname = gfullname;
		}

		public Integer getStorenumb() {
			return storenumb;
		}

		public void setStorenumb(Integer storenumb) {
			this.storenumb = storenumb;
		}

		public String getGoodimg() {
			return goodimg;
		}

		public void setGoodimg(String goodimg) {
			this.goodimg = goodimg;
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		public String getImg1() {
			return img1;
		}

		public void setImg1(String img1) {
			this.img1 = img1;
		}

		public String getImg2() {
			return img2;
		}

		public void setImg2(String img2) {
			this.img2 = img2;
		}

		public String getImg3() {
			return img3;
		}

		public void setImg3(String img3) {
			this.img3 = img3;
		}

		public String getImg4() {
			return img4;
		}

		public void setImg4(String img4) {
			this.img4 = img4;
		}

		public String getImg5() {
			return img5;
		}

		public void setImg5(String img5) {
			this.img5 = img5;
		}

		public int getIsuse() {
			return isuse;
		}

		public void setIsuse(int isuse) {
			this.isuse = isuse;
		}

	}
}
