package com.sq.bxstore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.sq.bxstore.R;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.AccountGradeLevelReq;
import com.sq.bxstore.net.request.MyteamReq;
import com.sq.bxstore.net.response.AccountGradeLevelResponse;
import com.sq.bxstore.net.response.MyteamResponse;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

public class PersonMyTeamActivity extends Activity implements NetCallBack {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_myteam);
		initView();
		requestMyteam();
	}

	private void initView() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("我的团队");
		titleLayout.setTitleBarListener(new TitleBarListener() {

			@Override
			public void rightClick() {
			}

			@Override
			public void leftClick() {
				finish();
			}
		});
	}
	
	private void requestMyteam(){
		MyteamReq req = new MyteamReq();
		req.setNetCallback(this);
		req.setRequestType(RequestType.POST);
		req.setUsername(UserInfoBean.userName);
		req.addRequest();
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if(baseRes instanceof MyteamResponse){
			MyteamResponse mr = (MyteamResponse) baseRes;
			if(mr.getResult().equals("0")){
				AccountGradeLevelReq req = new AccountGradeLevelReq();
				req.setNetCallback(this);
				req.setRequestType(RequestType.POST);
				req.addRequest();
			}else{
				
			}
		} else if(baseRes instanceof AccountGradeLevelResponse){
			AccountGradeLevelResponse aglr = (AccountGradeLevelResponse) baseRes;
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		
	}
}
