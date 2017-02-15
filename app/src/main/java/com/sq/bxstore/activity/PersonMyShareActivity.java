package com.sq.bxstore.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.sq.bxstore.R;
import com.sq.bxstore.utils.AndroidShare;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

public class PersonMyShareActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_myshare);
		initView();
	}

	private void initView() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("推荐分享");
		titleLayout.setRightBtnShow("分享");
		titleLayout.setTitleBarListener(new TitleBarListener() {

			@Override
			public void rightClick() {
				AndroidShare as = new AndroidShare(
						PersonMyShareActivity.this,
						"哈哈---超方便的分享！！！来自allen--http://blog.csdn.net/cwcwj3069/article/details/41698417",
						"");
				as.show();
			}

			@Override
			public void leftClick() {
				finish();
			}
		});
		findViewById(R.id.share_layout).setOnClickListener(this);
		findViewById(R.id.share_gotorecharge).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.share_layout:
			AndroidShare as = new AndroidShare(
					PersonMyShareActivity.this,
					"哈哈---超方便的分享！！！来自allen--http://blog.csdn.net/cwcwj3069/article/details/41698417",
					"");
			as.show();
			break;
		case R.id.share_gotorecharge:
			Intent intent = new Intent();
			intent.setClass(PersonMyShareActivity.this, RechargeActivity.class);
			startActivity(intent);
			break;
		}
	}
}
