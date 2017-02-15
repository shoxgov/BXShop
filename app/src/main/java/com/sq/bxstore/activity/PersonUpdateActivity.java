package com.sq.bxstore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.PersonUpdateReq;
import com.sq.bxstore.net.response.PersonUpdateResponse;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

public class PersonUpdateActivity extends Activity implements OnClickListener,
		NetCallBack, TitleBarListener {

	private EditText sponsor;
	private EditText username;
	private EditText name;
	private EditText sex;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_person_update);
		initViews();
		init();
	}

	private void initViews() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("用户资料");
		titleLayout.setTitleBarListener(this);
		sponsor = (EditText) findViewById(R.id.person_update_sponsor);
		username = (EditText) findViewById(R.id.person_update_username);
		name = (EditText) findViewById(R.id.person_update_name);
		sex = (EditText) findViewById(R.id.person_update_sex);
		password = (EditText) findViewById(R.id.person_update_password);
		ID = (EditText) findViewById(R.id.person_update_id);
		telephone = (EditText) findViewById(R.id.person_update_telephone);
		email = (EditText) findViewById(R.id.person_update_email);
		companyWallet = (EditText) findViewById(R.id.person_update_company_wallet);
		wallet = (EditText) findViewById(R.id.person_update_wallet);
		bankUsername = (EditText) findViewById(R.id.person_update_bank_username);
		bankNumber = (EditText) findViewById(R.id.person_update_bankid);
		bankName = (EditText) findViewById(R.id.person_update_bankname);
		securityPassword = (EditText) findViewById(R.id.person_update_security_password);

		sponsor_check = (TextView) findViewById(R.id.person_update_sponsor_check);
		username_check = (TextView) findViewById(R.id.person_update_username_check);
		wallet_remaining = (TextView) findViewById(R.id.person_update_wallet_remaining);
		sponsor_check.setOnClickListener(this);
		username_check.setOnClickListener(this);
		wallet_remaining.setOnClickListener(this);
		findViewById(R.id.person_update_submit).setOnClickListener(this);
	}

	private void init() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.person_update_sponsor_check:
			break;
		case R.id.person_update_username_check:
			break;
		case R.id.person_update_wallet_remaining:
			break;
		case R.id.person_update_submit:
			break;
		}
	}

	private boolean checkRegister() {
		return true;
	}

	private void requestUpdate() {
		WaitTool.showDialog(this, "");
		PersonUpdateReq req = new PersonUpdateReq();
		req.setRequestType(RequestType.GET);
		req.setNetCallback(this);
		req.addRequest();
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof PersonUpdateResponse) {
			WaitTool.dismissDialog();
			PersonUpdateResponse pur = (PersonUpdateResponse) baseRes;
			if (pur.getStatus_code() == 100) {

			} else {
				ToastTool.showShortBigToast(this, pur.getMessage());
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
