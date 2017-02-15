package com.sq.bxstore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.EditMyAddressReq;
import com.sq.bxstore.net.request.EditMyAddressReq.AddressHandType;
import com.sq.bxstore.net.response.EditMyAddressResponse;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;

public class AddressEditActivity extends Activity implements OnClickListener,
		NetCallBack {

	private EditText contactName;
	private EditText contactTel;
	private EditText detailAddress;
	private CheckBox checkbox;
	private Button confirm;
	private TextView title;
	private String mode = "";
	/**
	 * 编辑时，要传ID
	 */
	private String addressId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_myaddress_edit);
		initViews();
		Intent intent = getIntent();
		if (intent.hasExtra("mode")
				&& intent.getStringExtra("mode").equals("edit")) {
			mode = "edit";
			addressId = intent.getStringExtra("id");
			contactName.setText(intent.getStringExtra("name"));
			contactTel.setText(intent.getStringExtra("tel"));
			detailAddress.setText(intent.getStringExtra("detail"));
			int isuse = intent.getIntExtra("isuse", 0);// // 1默认 0非默认
			if (isuse == 1) {
				checkbox.setChecked(true);
			} else {
				checkbox.setChecked(false);
			}
			title.setText("编辑收货地址");
		} else {
			title.setText("新建收货地址");
		}
	}

	private void initViews() {
		title = (TextView) findViewById(R.id.title_name);
		findViewById(R.id.title_left_img).setOnClickListener(this);
		findViewById(R.id.address_edit_select_contact).setOnClickListener(this);
		confirm = (Button) findViewById(R.id.address_edit_confirm);
		confirm.setOnClickListener(this);
		contactName = (EditText) findViewById(R.id.address_edit_contact_name);
		contactTel = (EditText) findViewById(R.id.address_edit_contact_tel);
		detailAddress = (EditText) findViewById(R.id.address_detail_edittext);
		checkbox = (CheckBox) findViewById(R.id.address_default_checkbox);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.address_edit_select_contact:
			if (Utils.isFastDoubleClick()) {
				return;
			}
			Uri uri = Uri.parse("content://contacts/people");
			Intent intent = new Intent(Intent.ACTION_PICK, uri);
			startActivityForResult(intent, 0);
			break;

		case R.id.address_edit_confirm:
			if (Utils.isFastDoubleClick()) {
				return;
			}
			String name = "";
			String tel = "";
			String address = "";

			try {
				name = contactName.getText().toString();
				tel = contactTel.getText().toString();
				address = detailAddress.getText().toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (TextUtils.isEmpty(name) || TextUtils.isEmpty(tel)
					|| TextUtils.isEmpty(address)) {
				ToastTool.showShortBigToast(this, "请完善信息");
				return;
			}
			EditMyAddressReq req = new EditMyAddressReq();
			req.setNetCallback(this);
			req.setUserid(UserInfoBean.userId + "");
			req.setName(name);
			req.setAddress(address);
			req.setPhonenumb(tel);
			if (mode.equals("edit")) {
				req.setHandType(AddressHandType.MODIFY);
				req.setAddressid(addressId);
			} else {
				req.setHandType(AddressHandType.ADD);
			}
			if (checkbox.isChecked()) {
				req.setIsuse("1");// 1默认 0非默认
			} else {
				req.setIsuse("0");
			}
			req.addRequest();
			break;
			
		case R.id.title_left_img:
			finish();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0:
			if (data == null) {
				return;
			}
			// 处理返回的data,获取选择的联系人信息
			Uri uri = data.getData();
			String[] contacts = Utils.getPhoneContacts(this, uri);
			if (contacts == null) {
				return;
			}
			contactName.setText(contacts[0]);
			contactTel.setText(contacts[1]);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof EditMyAddressResponse) {
			EditMyAddressResponse emar = (EditMyAddressResponse) baseRes;
			if (emar.getResult().equals("0")) {
				ToastTool.showShortBigToast(this, "保存成功");
				try {
					Intent result = new Intent();
					result.putExtra("address_name", contactName.getText()
							.toString());
					result.putExtra("address_telephone", contactTel.getText()
							.toString());
					result.putExtra("address_info", detailAddress.getText()
							.toString());
					result.putExtra("addressId", addressId);
					setResult(1, result);
				} catch (Exception e) {
					e.printStackTrace();
				}
				finish();
			} else {
				ToastTool.showShortBigToast(this, "提交失败");
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}
}
