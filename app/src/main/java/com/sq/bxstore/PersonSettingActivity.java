package com.sq.bxstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.update.CheckAndUpdateApk;
import com.sq.bxstore.utils.AndroidShare;
import com.sq.bxstore.utils.LocationBitmapCacheTools;
import com.sq.bxstore.utils.PreferenceUtil;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

public class PersonSettingActivity extends Activity implements OnClickListener,
		TitleBarListener {

	private TextView name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_personal_setting);
		initViews();
	}

	private void initViews() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("设置");
		titleLayout.setTitleBarListener(this);
		findViewById(R.id.person_setting_address_manager_layout)
				.setOnClickListener(this);
		findViewById(R.id.person_setting_logout).setOnClickListener(this);
		findViewById(R.id.person_setting_clear_layout).setOnClickListener(this);
		findViewById(R.id.person_setting_update_layout)
				.setOnClickListener(this);
		findViewById(R.id.person_setting_about_layout).setOnClickListener(this);
		name = (TextView) findViewById(R.id.person_setting_name);
		name.setText(UserInfoBean.name);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.person_setting_address_manager_layout:
			Intent intent = new Intent();
			intent.setClass(this, MyAddressManagerActivity.class);
			startActivity(intent);
			break;
		case R.id.person_setting_logout:
			if (Utils.isFastDoubleClick()) {
				return;
			}
			PreferenceUtil.commitBoolean("remember", false);
			PreferenceUtil.commitString("lastUsername", "");
			PreferenceUtil.commitString("lastPassword", "");
			break;
		case R.id.person_setting_update_layout:
			if (Utils.isFastDoubleClick()) {
				return;
			}
			WaitTool.showDialog(this, "");
			CheckAndUpdateApk checkUpdate = CheckAndUpdateApk.getInstance();
			checkUpdate.init(this);
			checkUpdate.checkUpdate();
			break;
			
		case R.id.person_setting_clear_layout:
			if(Utils.isFastDoubleClick()){
				return;
			}
			LocationBitmapCacheTools.clearCacheFile();
			ToastTool.showShortBigToast(this, "清除成功！");
			break;
			
		case R.id.person_setting_about_layout:
			break;
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
