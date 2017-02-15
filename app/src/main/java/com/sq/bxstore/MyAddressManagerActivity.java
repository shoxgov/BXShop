package com.sq.bxstore;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sq.bxstore.adapter.MyAddressAdapter;
import com.sq.bxstore.adapter.MyAddressManagerAdapter;
import com.sq.bxstore.bean.AddressInfo;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.MyAddressReq;
import com.sq.bxstore.net.response.MyAddressResponse;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.WaitTool;

public class MyAddressManagerActivity extends Activity implements
		OnClickListener, NetCallBack {
	/**
	 * 此页面为共用页面，此标识用于区分是从哪个页面过来的
	 */
	private String comeSource = "";
	private ListView myAddressList;
	private Button confirm;
	private TextView title;
	private BaseAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_myaddress);
		initViews();
		Intent intent = getIntent();
		if (intent != null && intent.hasExtra("comeSource")
				&& intent.getStringExtra("comeSource").equals("PayActivity")) {// 支付页面进来的，没有删除项
			comeSource = "PayActivity";
			adapter = new MyAddressAdapter(this);
			myAddressList.setAdapter(adapter);
			myAddressList.setOnItemClickListener(listener);
		} else {
			adapter = new MyAddressManagerAdapter(this);
			myAddressList.setAdapter(adapter);
		}
		confirm.setText("+ 新建地址");
		title.setText("收货地址管理");
		requestMyAddress();
	}

	private void initViews() {
		title = (TextView) findViewById(R.id.title_name);
		findViewById(R.id.title_left_img).setOnClickListener(this);
		myAddressList = (ListView) findViewById(R.id.address_list);
		confirm = (Button) findViewById(R.id.address_confirm);
		// /////////////////////////////////////////////
		confirm.setOnClickListener(this);
	}

	private void requestMyAddress() {
		WaitTool.showDialog(this, "");
		MyAddressReq req = new MyAddressReq();
		req.setNetCallback(this);
		req.setUserid(UserInfoBean.userId);
		req.addRequest();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1) {
			requestMyAddress();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	OnItemClickListener listener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View parent, int position,
				long arg3) {
			try {
				AddressInfo ai = (AddressInfo) parent.getTag();
				Intent result = new Intent();
				result.putExtra("address_name", ai.getName());
				result.putExtra("address_telephone", ai.getPhonenumb());
				result.putExtra("address_info", ai.getAddress());
				result.putExtra("addressId", ai.getId());
				setResult(0, result);
				finish();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	};
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.address_confirm:
			Intent intent = new Intent();
			intent.setClass(this, AddressEditActivity.class);
			startActivityForResult(intent, 1);
			break;

		case R.id.title_left_img:
			finish();
			break;
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (isFinishing()) {
			return;
		}
		if (baseRes instanceof MyAddressResponse) {
			WaitTool.dismissDialog();
			MyAddressResponse mar = (MyAddressResponse) baseRes;
			if (mar.getResult().equals("0")) {
				List<AddressInfo> data = mar.getData();
				if (data == null || data.isEmpty()) {
					ToastTool.showShortBigToast(this, "当前你没有创建收货地址");
					return;
				}
				if (adapter instanceof MyAddressManagerAdapter) {
					((MyAddressManagerAdapter) adapter).setData(data);
				} else if (adapter instanceof MyAddressAdapter) {// 从支付页面进来的
					((MyAddressAdapter) adapter).setData(data);
				}
			} else {
				ToastTool.showShortBigToast(this, mar.getMessage());
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}
}
