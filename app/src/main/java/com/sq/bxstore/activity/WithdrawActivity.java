package com.sq.bxstore.activity;

import com.sq.bxstore.R;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.WithdrawReq;
import com.sq.bxstore.net.response.WithdrawResponse;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class WithdrawActivity extends Activity implements OnClickListener, NetCallBack {

	private TextView withdraw_total;
	private Spinner withdraw_spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_widthdraw);
		initView();
		Intent intent = getIntent();
		if (intent.hasExtra("withdraw")) {
			withdraw_total.setText("当前返利总额："
					+ intent.getStringExtra("withdraw"));
		}
	}

	private void initView() {
		withdraw_total = (TextView) findViewById(R.id.withdraw_total);
		withdraw_spinner = (Spinner) findViewById(R.id.withdraw_spinner);
		findViewById(R.id.widthdraw_btn).setOnClickListener(this);
		TitleBarLayout titleBar = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleBar.setTitle("返利提现");
		titleBar.setTitleBarListener(new TitleBarListener() {

			@Override
			public void rightClick() {
			}

			@Override
			public void leftClick() {
				finish();
			}
		});

		// 建立数据源
		String[] mItems = { "支付宝", "微信" };
		// 建立Adapter并且绑定数据源
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mItems);
		withdraw_spinner.setAdapter(adapter);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.widthdraw_btn:
			requestWithdraw();
			break;
		}
	}

	private void requestWithdraw() {
		WithdrawReq req = new WithdrawReq();
		req.setNetCallback(this);
		req.setRequestType(RequestType.POST);
		req.addRequest();
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof WithdrawResponse) {
			WithdrawResponse wr = (WithdrawResponse) baseRes;
			if (wr.getResult().equals("0")) {

			} else {

			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		
	}
}
