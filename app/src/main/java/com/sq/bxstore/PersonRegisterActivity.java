package com.sq.bxstore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.RegisterReq;
import com.sq.bxstore.net.response.RegisterResponse;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

/**
 * @author hnbx
 *http://192.168.1.251:9000/pointsmanager/user_web/user/register.do
 */
public class PersonRegisterActivity extends Activity implements
		OnClickListener, NetCallBack, TitleBarListener {

	private EditText sponsor;
	private EditText username;
	private EditText name;
	private EditText password;
	private EditText ID;
	private EditText telephone;
	private EditText email;
	private EditText companyWallet;
	private EditText wallet;
	private EditText bankUsername;
	private EditText bankNumber;
	private EditText bankName;
	private EditText securityPassword;
	private TextView sponsor_check;
	private TextView username_check;
	private TextView wallet_remaining;
	private WebView web;
	private String registerUrl = NetWorkConfig.HTTP_HOST
			+ "/jsp/register.jsp?userPid=" + UserInfoBean.userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		initViews();
		init();
	}

	private void initViews() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("注册新用户");
		titleLayout.setTitleBarListener(this);
		web = (WebView) findViewById(R.id.web);
		goURL();
		/*sponsor = (EditText) findViewById(R.id.register_sponsor);
		username = (EditText) findViewById(R.id.register_username);
		name = (EditText) findViewById(R.id.register_name);
		// sex = (EditText) findViewById(R.id.register_sex);
		password = (EditText) findViewById(R.id.register_password);
		ID = (EditText) findViewById(R.id.register_id);
		telephone = (EditText) findViewById(R.id.register_telephone);
		email = (EditText) findViewById(R.id.register_email);
		companyWallet = (EditText) findViewById(R.id.register_company_wallet);
		wallet = (EditText) findViewById(R.id.register_wallet);
		bankUsername = (EditText) findViewById(R.id.register_bank_username);
		bankNumber = (EditText) findViewById(R.id.register_bankid);
		bankName = (EditText) findViewById(R.id.register_bankname);
		securityPassword = (EditText) findViewById(R.id.register_security_password);

		sponsor_check = (TextView) findViewById(R.id.register_sponsor_check);
		username_check = (TextView) findViewById(R.id.register_username_check);
		wallet_remaining = (TextView) findViewById(R.id.register_wallet_remaining);
		sponsor_check.setOnClickListener(this);
		username_check.setOnClickListener(this);
		wallet_remaining.setOnClickListener(this);
		findViewById(R.id.register_submit).setOnClickListener(this);*/
	}

	private void init() {

	}
	
	private void goURL(){
		WebSettings settings = web.getSettings();
		// 设置加载进来的页面自适应手机屏幕 ,页面适应手机屏幕的分辨率，完整的显示在屏幕上，可以放大缩小
		settings.setUseWideViewPort(true);// 设置webview推荐使用的窗口，设置为true
		settings.setLoadWithOverviewMode(true);// 设置webview加载的页面的模式，也设置为true
		settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
		settings.setDomStorageEnabled(true); // 
		settings.setJavaScriptEnabled(true);
		web.loadUrl(registerUrl);
		// 防止跳到系统浏览器
		web.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				ToastTool.showShortBigToast(PersonRegisterActivity.this, "网页加载出错!");
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
		});
	}

	@Override
	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.register_sponsor_check:
//			break;
//		case R.id.register_username_check:
//			break;
//		case R.id.register_wallet_remaining:
//			break;
//		case R.id.register_submit:
//			if (checkRegister()) {
//				requestRegister();
//			} else {
//
//			}
//			break;
//		}
	}

	private boolean checkRegister() {
		return true;
	}

	private void requestRegister() {
		WaitTool.showDialog(this, "");
		RegisterReq req = new RegisterReq();
		req.setRequestType(RequestType.GET);
		req.setNetCallback(this);
		req.addRequest();
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof RegisterResponse) {
			WaitTool.dismissDialog();
			RegisterResponse rr = (RegisterResponse) baseRes;
			if (rr.getStatus_code() == 100) {

			} else {
				ToastTool.showShortBigToast(this, rr.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		if (tag.equals("RegisterReq")) {
			WaitTool.dismissDialog();
		}
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {

	}
}
