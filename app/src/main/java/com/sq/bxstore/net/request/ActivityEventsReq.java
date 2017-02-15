package com.sq.bxstore.net.request;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.BaseCommReq;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.response.ActivityEventsResponse;

/**
eventsid	活动id	string	No	通过活动ID来确定返回哪个活动内容
pagesize	每页显示数	string	YES	每页显示数量
nowpage		当前页数	string	YES	当前页数
 */
public class ActivityEventsReq extends BaseCommReq {
	private ActivityEventsResponse response;

	private String eventsid;
	private String pagesize;
	private String nowpage;

	@Override
	public String generUrl() {
		setTag("ActivityEventsReq");
		return NetWorkConfig.HTTP_HOST
				+ "/adevent/searchevent.action";
	}

	@Override
	protected void handPostParam() {
		postParams.put("eventsid", getEventsid());
		postParams.put("pagesize", getPagesize());
		postParams.put("nowpage", getNowpage());
	}

	@Override
	public Class getResClass() {
		return ActivityEventsResponse.class;
	}

	@Override
	public BaseResponse getResBean() {
		if (response == null) {
			response = new ActivityEventsResponse();
		}
		return response;
	}

	public String getEventsid() {
		return eventsid;
	}

	public void setEventsid(String eventsid) {
		this.eventsid = eventsid;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getNowpage() {
		return nowpage;
	}

	public void setNowpage(String nowpage) {
		this.nowpage = nowpage;
	}

}