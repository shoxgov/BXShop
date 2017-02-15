package com.sq.bxstore.activity;

import com.sq.bxstore.R;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonMyWalletActivity extends Activity implements OnClickListener {
	private TextView balance;
	private TextView recharge;
	private TextView rebate;
	private LinearLayout coupon_layout;
	private TextView coupon_num;
	private LinearLayout rechargeHistory;
	private LinearLayout rebateDetail;
	private String BALANCET = "<font color=black><big>XXOO</big></font>元";
	private TextView withdraw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mywallet);
		initView();
		init();
	}

	private void init() {
		balance.setText(Html.fromHtml(BALANCET.replace("XXOO", "56")));
		recharge.setOnClickListener(this);
		coupon_layout.setOnClickListener(this);
		rechargeHistory.setOnClickListener(this);
		rebateDetail.setOnClickListener(this);
		recharge.setOnClickListener(this);
		withdraw.setOnClickListener(this);
	}

	private void initView() {
		TitleBarLayout titleBar = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleBar.setTitle("我的钱包");
		titleBar.setTitleBarListener(new TitleBarListener() {

			@Override
			public void rightClick() {
			}

			@Override
			public void leftClick() {
				finish();
			}
		});
		balance = (TextView) findViewById(R.id.mywallet_balance);
		recharge = (TextView) findViewById(R.id.mywallet_recharge);
		rebate = (TextView) findViewById(R.id.mywallet_rebate);
		coupon_num = (TextView) findViewById(R.id.mywallet_coupon_num);
		coupon_layout = (LinearLayout) findViewById(R.id.mywallet_coupon_layout);
		rechargeHistory = (LinearLayout) findViewById(R.id.mywallet_recharge_history_layout);
		rebateDetail = (LinearLayout) findViewById(R.id.mywallet_rebate_list_layout);
		withdraw = (TextView) findViewById(R.id.mywallet_withdraw);
	}

	@Override
	public void onClick(View v) {
		Intent activity = new Intent();
		switch (v.getId()) {
		case R.id.mywallet_recharge:
			activity.setClass(this, RechargeActivity.class);
			startActivity(activity);
			break;
		case R.id.mywallet_coupon_layout:
			activity.setClass(this, CommonListActivity.class);
			activity.putExtra("comesource", "coupon");
			startActivity(activity);
			break;
		case R.id.mywallet_recharge_history_layout:
			activity.setClass(this, CommonListActivity.class);
			activity.putExtra("comesource", "recharge");
			startActivity(activity);
			break;
		case R.id.mywallet_rebate_list_layout:
			activity.setClass(this, CommonListActivity.class);
			activity.putExtra("comesource", "rebate");
			startActivity(activity);
			break;
		case R.id.mywallet_withdraw:
			activity.setClass(this, WithdrawActivity.class);
			activity.putExtra("withdraw", "88.4");
			startActivity(activity);
			break;
		}
	}
}
