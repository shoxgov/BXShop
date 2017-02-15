package com.sq.bxstore.response;

import java.util.List;

import android.text.TextUtils;

import com.sq.bxstore.bean.PageInfo;
import com.sq.bxstore.net.BXBaseResponse;

/**
{
"message":"查询成功~",
"result":"0",
"page":
{
"begin":1,
"count":true,
"currentPage":1,
"currentPageRows":0,
"first":true,
"last":true,
"length":15,
"totalPage":1,
"totalRows":2
},
"data":[
{
"comment":"暗室逢灯三",
"createTime":1478489608000,
"goodsid":1,
"id":2,
"ishidden":"1",
"score":5,
"userid":2
},
{
  "avgscore": 5, 
  "comment": "闃胯惃寰峰彂qw123", 
  "createTime": 1478489671000, 
  "goodsid": 1, 
  "id": 4, 
  "ishidden": "1", 
  "score": 5, 
  "userid": 2
}
],
"ver":"v1.0"
}
 */
public class CommentResponse extends BXBaseResponse {

	private PageInfo page;
	private List<CommentInfo> data;

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public List<CommentInfo> getData() {
		return data;
	}

	public void setData(List<CommentInfo> data) {
		this.data = data;
	}

	public class CommentInfo {
		private String comment;
		private String ishidden;// 是否匿名 1：匿 名；
		private String createTime;
		private int goodsid;
		private int id;
		private int score;
		private int userid;
		private float avgscore;//平均分

		public float getAvgscore() {
			return avgscore;
		}

		public void setAvgscore(float avgscore) {
			this.avgscore = avgscore;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getIshidden() {
			if (TextUtils.isEmpty(ishidden)) {
				return "0";
			}
			return ishidden;
		}

		public void setIshidden(String ishidden) {
			this.ishidden = ishidden;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
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

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}

	}
}
