package com.sq.bxstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.sq.bxstore.activity.RechargeActivity;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.LoginReq;
import com.sq.bxstore.net.response.LoginResponse;
import com.sq.bxstore.utils.PreferenceUtil;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.utils.WaitTool;

public class LoginActivity extends Activity implements OnClickListener,
		NetCallBack {

	private EditText username;
	private EditText pwd;
	private EditText identityCode;
	private ImageView identityCodeImg;
	private CheckBox remember;
	String name = "";
	String pass = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 初始化PreferenceUtil
		PreferenceUtil.init(this);
		BXApplication.getInstance().updateLocale(
				PreferenceUtil.getString("language", "zh"));
		setContentView(R.layout.activity_login);
		initViews();
		init();
	}

	private void initViews() {
		username = (EditText) findViewById(R.id.login_username);
		pwd = (EditText) findViewById(R.id.login_pwd);
		remember = (CheckBox) findViewById(R.id.login_remember);
		identityCode = (EditText) findViewById(R.id.login_identity);
		identityCodeImg = (ImageView) findViewById(R.id.login_identity_img);
		findViewById(R.id.language_en).setOnClickListener(this);
		findViewById(R.id.language_zh).setOnClickListener(this);
	}

	private void init() {
		boolean isRemember = PreferenceUtil.getBoolean("remember", false);
		remember.setChecked(isRemember);
		if (isRemember) {
			username.setText(PreferenceUtil.getString("lastUsername", ""));
			pwd.setText(PreferenceUtil.getString("lastPassword", ""));
		}
		remember.setOnCheckedChangeListener(listener);
	}

	OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
			PreferenceUtil.commitBoolean("remember", isChecked);
		}

	};

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.login:
			requestLogin();
			break;
		case R.id.language_en:
			if (Utils.isFastDoubleClick()) {
				return;
			}
			finish();
			BXApplication.getInstance().updateLocale("en");
			startActivity((new Intent(this, LoginActivity.class)));
			break;
		case R.id.language_zh:
			intent = new Intent();
			intent.setClass(LoginActivity.this, RechargeActivity.class);
			startActivity(intent);
			if (Utils.isFastDoubleClick()) {
				return;
			}
//			finish();
//			BXApplication.getInstance().updateLocale("zh");
//			startActivity((new Intent(this, LoginActivity.class)));
			break;
		}
	}

	private void requestLogin() {
		try {
			name = username.getText().toString();
			pass = pwd.getText().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
			ToastTool.showShortBigToast(this, R.string.login_info);
			return;
		}
		WaitTool.showDialog(this, "");
		LoginReq req = new LoginReq();
		req.setNetCallback(this);
		req.setRequestType(RequestType.POST);
		req.setPassword(pass);
		req.setUsername(name);
		req.addRequest();
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof LoginResponse) {
			WaitTool.dismissDialog();
			LoginResponse lr = (LoginResponse) baseRes;
			if (TextUtils.isEmpty(lr.getResult()) || lr.getResult().equals("1")
					|| lr.getData() == null) {
				ToastTool.showShortBigToast(this, lr.getMessage());
			} else {
				UserInfoBean.userName = lr.getData().getUserName();
				UserInfoBean.password = pass;
				UserInfoBean.name = lr.getData().getNickName();
				UserInfoBean.userId = lr.getData().getId() + "";
				UserInfoBean.phone = lr.getData().getUserPhone();
				UserInfoBean.email = lr.getData().getUserMail();
				UserInfoBean.identityCard = lr.getData().getIdentityCard();
				UserInfoBean.trueName = lr.getData().getTrueName();
				UserInfoBean.userGrade = lr.getData().getUserGrade();
				UserInfoBean.userSex = lr.getData().getUserSex();
				UserInfoBean.userKyBalance = lr.getData().getUserKyBalance();
				UserInfoBean.userDjBalance = lr.getData().getUserDjBalance();
				UserInfoBean.userJfBalance = lr.getData().getUserJfBalance();
				UserInfoBean.userFxBalance = lr.getData().getUserFxBalance();
				UserInfoBean.userName = name;
				PreferenceUtil.commitString("lastUsername", name);
				PreferenceUtil.commitString("lastPassword", pass);
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainFragmentActivity.class);
				startActivity(intent);
				finish();
			}
		}

	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		if (tag.equals("LoginReq")) {
			WaitTool.dismissDialog();
		}
	}
}
