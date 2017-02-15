package com.sq.bxstore.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;
import android.util.Patterns;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BXBaseResponse;

public class GoodsDetailResponse extends BXBaseResponse {
	/*
	 * {
	 *{
	 *"message":"查询成功",
	 *"result":"0",
	 "data":[
        {
            "brand":"显示器1",
            "detail":"<p><img src="http://127.0.0.1:8080/dev/ueditor/jsp/upload/image/20161101/1477982516861023893.jpg" title="1477982516861023893.jpg"/></p><p><img src="http://127.0.0.1:8080/dev/ueditor/jsp/upload/image/20161101/1477982516891089390.png" title="1477982516891089390.png"/></p><p><br/></p>",
            "gfullname":"三星（SAMSUNG）S24D360HL 23.6英寸PLS广视角窄边框LED背光液晶显示器",
            "gname":"三星（SAMSUNG）S24D360HL 23.6英寸PLS广视角窄边框LED背光液晶显示器",
            "goodimg":"20161121/13b98ba7-5665-40f6-94ed-424657845bca1479719056855.jpg",
            "id":1,
            "img1":"20161121/904018f1-80cb-4211-be28-9c0d9422c5531479719061785.png",
            "img2":"http://127.0.0.1:8080/dev/upload/80c8f4eb-2531-4fc7-8832-f573c66c03aa1478160691422.png",
            "img3":"http://127.0.0.1:8080/dev/upload/29cc7c17-459c-4024-a867-add72ea2fa111478160691453.png",
            "img4":"http://127.0.0.1:8080/dev/upload/c988134d-277b-4f57-90e0-5b24e8b72de31478160691515.png",
            "price":799.01,
            "storenumb":0
        }
    ],
	 *"ver":"v1.0"
	 *}
	 * }
	 * 
	 * 
	 * 
	 * 
	 * {
	 * "message":"查询成功",
	 * "result":"0",
	 * "data":[
	 * {
	 * "brand":"枕头",
	 * "category":"居家",
	 * "detail":"<p><img src=\"http://139.196.121.226/dev/ueditor/jsp/upload/image/20161228/1482889814453074360.jpg\" title=\"1482889814453074360.jpg\"/></p><p><img src=\"http://139.196.121.226/dev/ueditor/jsp/upload/image/20161228/1482889814453059401.jpg\" title=\"1482889814453059401.jpg\"/></p><p><img src=\"http://139.196.121.226/dev/ueditor/jsp/upload/image/20161228/1482889814562047378.jpg\" title=\"1482889814562047378.jpg\"/></p><p><img src=\"http://139.196.121.226/dev/ueditor/jsp/upload/image/20161228/1482889814594056932.jpg\" title=\"1482889814594056932.jpg\"/></p><p><img src=\"http://139.196.121.226/dev/ueditor/jsp/upload/image/20161228/1482889814609089063.jpg\" title=\"1482889814609089063.jpg\"/></p><p><br/></p>",
	 * "gfullname":"枕头 超柔软枕心 压不扁枕头 枕芯",
	 * "gname":"保健枕头",
	 * "goodimg":"20161228/5f2209a6-ba76-4bd8-9403-96e7555cc35f1482889911234.jpg",
	 * "id":1,
	 * "img1":"20161228/412dad9a-d16b-4384-b59d-cd0f404df47c1482889911234.jpg",
	 * "img2":"20161228/16d56e38-e806-4a82-861d-330f8a0a6d611482889911234.jpg",
	 * "kind":"被枕",
	 * "price":98.00
	 * }
	 * ],"ver":"v1.0"}
	 * 
	 */
	private List<GoodsGeneralData> data;
	
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

		private String brand;//第三级
		
		private String category;//第二级
		private String kind;//第一级

		private String gfullname;

		private int storenumb;

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

		public BigDecimal getPrice() {
			return price;
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

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getKind() {
			return kind;
		}

		public void setKind(String kind) {
			this.kind = kind;
		}

		public ArrayList<String> getGoodimglist() {
			ArrayList<String> goodimgList = new ArrayList<String>();
			if (!TextUtils.isEmpty(img1) && Patterns.WEB_URL.matcher(img1).matches()) {
				goodimgList.add(img1);
			} else if (!TextUtils.isEmpty(img1)){
				goodimgList.add(NetWorkConfig.IMAGEPATH + img1);
			}
			if (!TextUtils.isEmpty(img2) && Patterns.WEB_URL.matcher(img2).matches()) {
				goodimgList.add(img2);
			} else if (!TextUtils.isEmpty(img2)){
				goodimgList.add(NetWorkConfig.IMAGEPATH + img2);
			}
			if (!TextUtils.isEmpty(img3) && Patterns.WEB_URL.matcher(img3).matches()) {
				goodimgList.add(img3);
			} else if (!TextUtils.isEmpty(img3)){
				goodimgList.add(NetWorkConfig.IMAGEPATH + img3);
			}
			if (!TextUtils.isEmpty(img4) && Patterns.WEB_URL.matcher(img4).matches()) {
				goodimgList.add(img4);
			} else if (!TextUtils.isEmpty(img4)){
				goodimgList.add(NetWorkConfig.IMAGEPATH + img4);
			}
			if (!TextUtils.isEmpty(img5) && Patterns.WEB_URL.matcher(img5).matches()) {
				goodimgList.add(img5);
			} else if (!TextUtils.isEmpty(img5)){
				goodimgList.add(NetWorkConfig.IMAGEPATH + img5);
			}
			return goodimgList;
		}

		public String getGfullname() {
			return gfullname;
		}

		public void setGfullname(String gfullname) {
			this.gfullname = gfullname;
		}

		public int getStorenumb() {
			return storenumb;
		}

		public void setStorenumb(int storenumb) {
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

	}
}
