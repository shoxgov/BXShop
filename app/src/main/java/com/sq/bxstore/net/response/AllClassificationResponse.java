package com.sq.bxstore.net.response;

import java.util.List;

import android.text.TextUtils;
import android.util.Patterns;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BXBaseResponse;

public class AllClassificationResponse extends BXBaseResponse {
	/*
	 * 
{
    "message": "查询成功~", 
    "result": "0", 
    "data": [
        {
            "cateName": "全部商品分类", 
            "cateOrder": 0, 
            "id": 1, 
            "level": 0, 
            "parentId": -1
        }, 
        {
            "cateName": "家用电器", 
            "cateOrder": 1, 
            "id": 2, 
            "level": 1, 
            "parentId": 1, 
            "url": "/img/img1.jpg"
        }, 
        {
            "cateName": "手机/运营商/数码", 
            "cateOrder": 2, 
            "id": 3, 
            "level": 1, 
            "parentId": 1
        }, 
        {
            "cateName": "电脑/办公", 
            "cateOrder": 3, 
            "id": 4, 
            "level": 1, 
            "parentId": 1
        }, 
        {
            "cateName": "电视", 
            "cateOrder": 1, 
            "id": 5, 
            "level": 2, 
            "parentId": 2
        }, 
        {
            "cateName": "空调", 
            "cateOrder": 2, 
            "id": 6, 
            "level": 2, 
            "parentId": 2
        }, 
        {
            "cateName": "洗衣机", 
            "cateOrder": 3, 
            "id": 7, 
            "level": 2, 
            "parentId": 2
        }, 
        {
            "cateName": "合资品牌", 
            "cateOrder": 1, 
            "id": 8, 
            "level": 3, 
            "parentId": 5
        }, 
        {
            "cateName": "国产品牌", 
            "cateOrder": 2, 
            "id": 9, 
            "level": 3, 
            "parentId": 5
        }, 
        {
            "cateName": "互联网品牌", 
            "cateOrder": 3, 
            "id": 10, 
            "level": 3, 
            "parentId": 5
        }, 
        {
            "cateName": "柜式空调", 
            "cateOrder": 1, 
            "id": 11, 
            "level": 3, 
            "parentId": 6
        }, 
        {
            "cateName": "壁挂式空调", 
            "cateOrder": 2, 
            "id": 12, 
            "level": 3, 
            "parentId": 6
        }, 
        {
            "cateName": "中央空调", 
            "cateOrder": 3, 
            "id": 13, 
            "level": 3, 
            "parentId": 6
        }, 
        {
            "cateName": "滚筒洗衣机", 
            "cateOrder": 1, 
            "id": 14, 
            "level": 3, 
            "parentId": 7
        }, 
        {
            "cateName": "洗烘一体机", 
            "cateOrder": 2, 
            "id": 15, 
            "level": 3, 
            "parentId": 7
        }, 
        {
            "cateName": "波轮洗衣机", 
            "cateOrder": 3, 
            "id": 16, 
            "level": 3, 
            "parentId": 7
        }, 
        {
            "cateName": "迷你洗衣机", 
            "cateOrder": 4, 
            "id": 17, 
            "level": 3, 
            "parentId": 7
        }, 
        {
            "cateName": "洗衣机配件", 
            "cateOrder": 5, 
            "id": 18, 
            "level": 3, 
            "parentId": 7
        }, 
        {
            "cateName": "手机通讯", 
            "cateOrder": 1, 
            "id": 19, 
            "level": 2, 
            "parentId": 3
        }, 
        {
            "cateName": "手机配件", 
            "cateOrder": 2, 
            "id": 20, 
            "level": 2, 
            "parentId": 3
        }, 
        {
            "cateName": "手机", 
            "cateOrder": 1, 
            "id": 21, 
            "level": 3, 
            "parentId": 19
        }, 
        {
            "cateName": "对讲机", 
            "cateOrder": 2, 
            "id": 22, 
            "level": 3, 
            "parentId": 19
        }, 
        {
            "cateName": "手机壳", 
            "cateOrder": 1, 
            "id": 23, 
            "level": 3, 
            "parentId": 20
        }, 
        {
            "cateName": "贴膜", 
            "cateOrder": 2, 
            "id": 24, 
            "level": 3, 
            "parentId": 20
        }, 
        {
            "cateName": "手机存储卡", 
            "cateOrder": 3, 
            "id": 25, 
            "level": 3, 
            "parentId": 20
        }, 
        {
            "cateName": "数据线", 
            "cateOrder": 4, 
            "id": 26, 
            "level": 3, 
            "parentId": 20
        }, 
        {
            "cateName": "电脑整机", 
            "cateOrder": 1, 
            "id": 27, 
            "level": 2, 
            "parentId": 4
        }, 
        {
            "cateName": "电脑配件", 
            "cateOrder": 2, 
            "id": 28, 
            "level": 2, 
            "parentId": 4
        }, 
        {
            "cateName": "笔记本", 
            "cateOrder": 1, 
            "id": 29, 
            "level": 3, 
            "parentId": 27
        }, 
        {
            "cateName": "游戏本", 
            "cateOrder": 2, 
            "id": 30, 
            "level": 3, 
            "parentId": 27
        }, 
        {
            "cateName": "平板电脑", 
            "cateOrder": 3, 
            "id": 31, 
            "level": 3, 
            "parentId": 27
        }, 
        {
            "cateName": "显示器", 
            "cateOrder": 1, 
            "id": 32, 
            "level": 3, 
            "parentId": 28
        }, 
        {
            "cateName": "CPU", 
            "cateOrder": 2, 
            "id": 33, 
            "level": 3, 
            "parentId": 28
        }, 
        {
            "cateName": "主板", 
            "cateOrder": 3, 
            "id": 34, 
            "level": 3, 
            "parentId": 28
        }, 
        {
            "cateName": "硬盘", 
            "cateOrder": 4, 
            "id": 35, 
            "level": 3, 
            "parentId": 28
        }, 
        {
            "cateName": "内存", 
            "cateOrder": 5, 
            "id": 36, 
            "level": 3, 
            "parentId": 28
        }
    ], 
    "ver": "v1.0"
	 */
	private List<SingleCateInfo> data;
	
	public List<SingleCateInfo> getData() {
		return data;
	}

	public void setData(List<SingleCateInfo> data) {
		this.data = data;
	}

	public class SingleCateInfo {
		private String cateName;
		private int cateOrder;
		private int id;
		private int level;
		private int parentId;
		private String url;

		public String getCateName() {
			return cateName;
		}

		public void setCateName(String cateName) {
			this.cateName = cateName;
		}

		public int getCateOrder() {
			return cateOrder;
		}

		public void setCateOrder(int cateOrder) {
			this.cateOrder = cateOrder;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getParentId() {
			return parentId;
		}

		public void setParentId(int parentId) {
			this.parentId = parentId;
		}

		public String getUrl() {
			if (!TextUtils.isEmpty(url) && !Patterns.WEB_URL.matcher(url).matches()) {
				url = NetWorkConfig.IMAGEPATH + url;
			}
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}
}
