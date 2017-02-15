package com.sq.bxstore;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.sq.bxstore.adapter.GoodsItemAdapter;
import com.sq.bxstore.bean.AddressInfo;
import com.sq.bxstore.bean.CouponInfo;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.bxBean.GoodsInfoParcelable;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.AccountGeneralOrderReq;
import com.sq.bxstore.net.request.AccountPayReq;
import com.sq.bxstore.net.request.CouponHistoryReq;
import com.sq.bxstore.net.request.MyAddressReq;
import com.sq.bxstore.net.response.AccountGeneralOrderResponse;
import com.sq.bxstore.net.response.AccountPayResponse;
import com.sq.bxstore.net.response.CouponHistoryResponse;
import com.sq.bxstore.net.response.MyAddressResponse;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.ListViewUtils;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.WaitTool;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;

public class PayActivity extends Activity implements OnClickListener, NetCallBack, TitleBarListener {
	private static final String TAG = "PayActivity";
	private static final int ADDRESS_REQUESTCODE = 3;
	// ///// 支付宝支付用的属性
	private static final String PARTNER = "2088521487506378";
	// 商户收款账号
	private static final String SELLER = "shangqingyun@126.com";
	// 商户私钥，pkcs8格式
	private static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK41oU9ig4mrQTndKdgnDZSgPtTx2ORZJx0QOLnkOW9cq2eWwRdVrE0VShCSXwyvhdip37JB6HWAQkHYO5a3g0496cLGN/DvqEa0DWgJE3b8j/noSMGlosTjaqLOJsKdW4v3LA2RDyfqlLpUc9Xtx6nWfYa8A9clClNjtZ0tdBCbAgMBAAECgYEAl+LUUHVQv8/bH4sO95EdCw8nmNt/QZ/lNTa2boRn+wrtunQ6x5B/l/lOyOIFEvdZmAyLp0xFU2TO37+aPI6lYDnyiu/b0vwYrreLEeVTakIz4pvLbCyqZzOnOHLGA15i3h0ebju34ZH8QjkZqdz6uaCjODHIDQ+yNfcsQnVFFKkCQQDi5uBoYPlDXQERQ5I780+jGdUY4yvVz0DePVlACkQWw/93+0P0uiWixoFizOBNSPiMIw/GM4cPNxP60eeXjfUlAkEAxIzgyaZ6VRq3IBugJwDo8e4WOUVM30IfHDC5J645clgtW5C0aw1+NxhEeFyohuoJsbOByNybhEpRVpKwbkpivwJAZPp2uD0f8Vi8luBJfiCkOmPOsEq5QWgA6GbJB3UKJE+Jq+3IqmsFQNGy1zNyRzLIWiVoab6Yxq6uV5t+w7XLeQJAS6O/mjrkFz5ZvRSElJ4IB5rNmKqvw7eJi2rAxqGI4BVJF1Tf6dbefMttBjj6bjlBE872IlurycRj+wImvbsItwJBAKDVvFgzb+aIf5D0H6yLWrbUh5nWkY7vCtRvpPUDVcKtxkAbtkrE7MhxkkWPOfKQYVQOrb0h/zXRVEevm6euKBc=";
	// RSA_PUBLIC="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuNaFPYoOJq0E53SnYJw2UoD7U8djkWScdEDi55DlvXKtnlsEXVaxNFUoQkl8Mr4XYqd+yQeh1gEJB2DuWt4NOPenCxjfw76hGtA1oCRN2/I/56EjBpaLE42qizibCnVuL9ywNkQ8n6pS6VHPV7cep1n2GvAPXJQpTY7WdLXQQmwIDAQAB";
	// /////////////////////////////////
	/**
	 * 支付方式：0-账户支付；1-支付宝；2-微信支付
	 */
	private int payType = 0;
	private Button pay;
	private Dialog dialog;
	private TextView payTypeText;
	private TextView payCashTotal;
	private CheckBox receiptCheckBox;
	private View receiptLayout;
	private View receiptLine;
	private TextView goodsTotal;
	private ListView goodsList;
	private GoodsItemAdapter adapter;
	/**
	 * 从哪个页面跳入的来源
	 */
	private String source;
	/**
	 * 地址请求是否已经返回，没有返回时，不能点击地址列
	 */
	private boolean isRequsetAddress = false;
	private int addressId = 1;
	private ArrayList<GoodsInfoParcelable> goodsListdata;
	private TextView address_name;
	private TextView address_telephone;
	private TextView address_info;
	private Spinner payCouponSpinner;
	private float total;
	private EditText receiptCompanyEdit;
	private EditText remarkEdit;
	/**
	 * 优惠券查询信息
	 */
	private List<CouponInfo> couponList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pay);
		initViews();
		Intent intent = getIntent();
		if(!intent.hasExtra("source")){
			finish();
			return;
		}
		requestMyAddress();
		source = intent.getStringExtra("source");
		goodsListdata = intent.getParcelableArrayListExtra("data");
		adapter.setParcelableDataList(goodsListdata, true);
		ListViewUtils.setListViewHeightBasedOnChildren(goodsList);
		init();
	}

	private void init() {
		for(GoodsInfoParcelable gp : goodsListdata){
			total += gp.getPrice()*gp.getCount();
		}
		goodsTotal.setText(Html.fromHtml("合计 " + Constants.COLON
				+ "<font color=red>" + Constants.RMB + total + "  </font>"));
		payCashTotal.setText(Html.fromHtml("总金额 " + Constants.COLON
				+ "<font color=red>" + Constants.RMB + total + "  </font>"));
		receiptCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isCheck) {
				if(isCheck){
					receiptLayout.setVisibility(View.VISIBLE);
					receiptLine.setVisibility(View.VISIBLE);
				}else{
					receiptLayout.setVisibility(View.GONE);
					receiptLine.setVisibility(View.GONE);
				}
			}
		});
		receiptLayout.setVisibility(View.GONE);
		receiptLine.setVisibility(View.GONE);
		 // 建立数据源
        String[] mItems =  { "无优惠券" };
// 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        payCouponSpinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        requestCoupons();
	}

	private void requestCoupons() {
		CouponHistoryReq req = new CouponHistoryReq();
		req.setNetCallback(this);
		req.setRequestType(RequestType.POST);
		req.setUsername(UserInfoBean.userName);
		req.setCoupon_status("0");//0未使用 1已使用 2作废 查所有就不用传
		req.addRequest();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

	private void initViews() {
		TitleBarLayout titleLayout = (TitleBarLayout) findViewById(R.id.titlelayout);
		titleLayout.setTitle("确认订单");
		titleLayout.setTitleBarListener(this);
		address_name = (TextView) findViewById(R.id.order_address_name);
		address_telephone = (TextView) findViewById(R.id.order_address_telephone);
		address_info = (TextView) findViewById(R.id.order_address);
		address_telephone.setText("");
		goodsList = (ListView) findViewById(R.id.pay_goods_list);
		receiptLayout = (View) findViewById(R.id.pay_receipt_layout);
		receiptCompanyEdit = (EditText) findViewById(R.id.pay_receipt_company_edit);
		receiptLine = (View) findViewById(R.id.pay_receipt_line);
		receiptCheckBox = (CheckBox) findViewById(R.id.pay_receipt_checkbox);
		payTypeText = (TextView) findViewById(R.id.pay_type_txt);
		goodsTotal = (TextView) findViewById(R.id.goods_pay_total);
		payCashTotal = (TextView) findViewById(R.id.pay_cash_total);
		payCouponSpinner = (Spinner) findViewById(R.id.pay_coupon_spinner);
		pay = (Button) findViewById(R.id.pay);
		pay.setOnClickListener(this);
		remarkEdit = (EditText) findViewById(R.id.pay_remark_edittext);
		findViewById(R.id.pay_goods_address_layout).setOnClickListener(this);
//		findViewById(R.id.pay_type_layout).setOnClickListener(this);
		adapter = new GoodsItemAdapter(this);
		goodsList.setAdapter(adapter);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent == null) {
			return;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ADDRESS_REQUESTCODE){
			try {
				address_name.setText(data.getStringExtra("address_name"));
				address_telephone.setText(data.getStringExtra("address_telephone"));
				address_info.setText(data.getStringExtra("address_info"));
				addressId = data.getIntExtra("addressId", -1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void requestMyAddress() {
		MyAddressReq req = new MyAddressReq();
		req.setNetCallback(this);
		req.setUserid(UserInfoBean.userId);
		req.addRequest();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pay:
			accountPayMethod();
			break;
		case R.id.pay_goods_address_layout:
			if (!isRequsetAddress) {
				return;
			}
			String space = address_telephone.getText().toString();
			if (TextUtils.isEmpty(space)) {
				Intent addressIntent = new Intent();
				addressIntent.setClass(this, AddressEditActivity.class);
				addressIntent.putExtra("comeSource", "PayActivity");
				startActivityForResult(addressIntent, ADDRESS_REQUESTCODE);
			} else {
				Intent addressIntent = new Intent();
				addressIntent.setClass(this, MyAddressManagerActivity.class);
				addressIntent.putExtra("comeSource", "PayActivity");
				startActivityForResult(addressIntent, ADDRESS_REQUESTCODE);
			}
			break;
		case R.id.pay_type_layout:
			if (dialog == null) {
				dialog = new Dialog(this, R.style.MyDialog);
				View view = View.inflate(this, R.layout.dialog_pay_type, null);
				RadioGroup payGroup = (RadioGroup) view.findViewById(R.id.dialog_pay_group);
				dialog.setContentView(view);
				dialog.setCanceledOnTouchOutside(false);
				payGroup.setOnCheckedChangeListener(listener);
				view.findViewById(R.id.dialog_pay_ok).setOnClickListener(onClickListener);
				view.findViewById(R.id.dialog_pay_cancel).setOnClickListener(onClickListener);
			}
			dialog.show();
			break;
		default:
			break;
		}
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.dialog_pay_cancel:
				dialog.dismiss();
				dialog = null;
				break;
			case R.id.dialog_pay_ok:
				switch (payType) {
				case 0:
					payTypeText.setText("账户支付");
					break;
				case 1:
					payTypeText.setText("支付宝支付");
					break;
				case 2:
					payTypeText.setText("微信支付");
					break;
				}
				dialog.dismiss();
				dialog = null;
				break;
			}
		}
	};
			
			
	OnCheckedChangeListener listener = new OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(RadioGroup rg, int arg1) {
			 //获取变更后的选中项的ID
			int radioButtonId = rg.getCheckedRadioButtonId();
			switch(radioButtonId){
			case R.id.dialog_pay_alipay:
				payType = 1;
//				ToastTool.showShortBigToast(PayActivity.this, "你选择了支付宝支付方式");
				break;
			case R.id.dialog_pay_weixin:
				payType = 2;
//				ToastTool.showShortBigToast(PayActivity.this, "你选择了微信支付方式");
				break;
//			case R.id.dialog_pay_account:
//				payType = 0;
//				ToastTool.showShortBigToast(PayActivity.this, "你选择了账户支付方式");
//				break;
			}
		}

	};
	
	/**
	 * 
	 * 	userid			用户id 			string	YES	新增时需要传递的内容
		total			总额				string	YES	
		counts			件数				string	YES	
		paytype			支付方式			string	YES	
		positionid		地址id			string	YES	
		invoice			是否需要发票		string	YES	
		companyname		抬头				string	YES	
		content			发票内容			string	YES	
		remark			备注				string	YES	
		commisionCharge	手续费			string	YES	
		cash			现金金额			string	YES	
		accountAmount	账户金额			string	YES	
		goodslist		商品id(goodsid)	string	YES	
		{
			price		价格				string	YES	
			count		数量				string	YES	
		}
		type			操作类型			string	No	0新增 1删除 
		orderid			订单id			string	YES	删除修改需要提供订单id
	 */
	private void accountPayMethod(){
		WaitTool.showDialog(this, "");
		AccountGeneralOrderReq req = new AccountGeneralOrderReq();
		req.setNetCallback(this);
		req.setRequestType(RequestType.POST_JSON);
		req.setUserid(UserInfoBean.userId);
		req.setTotal(total+"");
		req.setCounts(goodsListdata.size()+"");
		req.setPaytype("1");
		req.setPositionid(addressId+"");
		if(receiptLayout.getVisibility() == View.VISIBLE){
			req.setInvoice("0");
			req.setCompanyname(receiptCompanyEdit.getText().toString());
			req.setContent("办公用品");
		}else{
			req.setInvoice("1");	
			req.setCompanyname(receiptCompanyEdit.getText().toString());
			req.setContent("办公用品");
		}
		req.setRemark(remarkEdit.getText().toString());
		req.setCommisionCharge("0");
		req.setCash(total+"");
		req.setAccountAmount(total+"");
		req.setGoodslist(goodsListdata);
		req.setType("0");
		req.addRequest();
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (isFinishing()) {
			return;
		}
		if(baseRes instanceof AccountGeneralOrderResponse){
			WaitTool.dismissDialog();
			final AccountGeneralOrderResponse apr = (AccountGeneralOrderResponse) baseRes;
			if (apr.getResult().equals("0") && apr.getData() != null) {
				ToastTool.showLongBigToast(this, "已成功生成订单！");
				pay.setText("订单已生成");
				pay.setEnabled(false);
			    final Dialog mCameraDialog = new Dialog(this, R.style.shareDialogTheme);  
	            LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(  
	                    R.layout.popwindow_dialog, null);  
	            TextView totalTV = (TextView) root.findViewById(R.id.btn_pay_total);
	            totalTV.setText(apr.getData().getTotal()+"元");
	            root.findViewById(R.id.btn_pay_ok).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						WaitTool.showDialog(PayActivity.this);
						AccountPayReq req = new AccountPayReq();
						req.setNetCallback(PayActivity.this);
						req.setUserid(UserInfoBean.userId);
						req.setOrderid(apr.getData().getId()+"");
						req.addRequest();
						mCameraDialog.dismiss();
					}
				});  
	            root.findViewById(R.id.btn_pay_cancel).setOnClickListener(new OnClickListener() {
	            	
	            	@Override
	            	public void onClick(View v) {
	            		ToastTool.showLongBigToast(PayActivity.this, "请到我的订单支付！");
	            		mCameraDialog.dismiss();
	            		PayActivity.this.finish();
	            	}
	            });  
	            mCameraDialog.setContentView(root);  
	            Window dialogWindow = mCameraDialog.getWindow();  
	            dialogWindow.setGravity(Gravity.BOTTOM);  
	            dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画  
	            WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值  
	            lp.x = 0; // 新位置X坐标  
	            lp.y = -20; // 新位置Y坐标  
	            lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度  
	    //      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度  
	    //      lp.alpha = 9f; // 透明度  
	            root.measure(0, 0);  
	            lp.height = root.getMeasuredHeight();  
	            lp.alpha = 9f; // 透明度  
	            dialogWindow.setAttributes(lp);  
	            mCameraDialog.setCanceledOnTouchOutside(false);
	            mCameraDialog.show();  
			} else {
				ToastTool.showLongBigToast(this, apr.getMessage());
			}
		} else if(baseRes instanceof AccountPayResponse) {
			AccountPayResponse app = (AccountPayResponse) baseRes;
			WaitTool.dismissDialog();
			PayActivity.this.finish();
		} else if(baseRes instanceof MyAddressResponse){
			MyAddressResponse mar = (MyAddressResponse) baseRes;
			isRequsetAddress = true;
			if(mar.getResult().equals("0")){
				List<AddressInfo> address = mar.getData();
				if(address == null || address.isEmpty()){
					address_name.setText("你没有创建收货地址，现在去创建~");
					address_telephone.setText("");
					address_info.setText("");
					return;
				}
				for(AddressInfo ai : address){
					if(ai.getIsuse() == 1){// 1默认 0非默认
						address_name.setText(ai.getName());
						address_telephone.setText(ai.getPhonenumb());
						address_info.setText(ai.getAddress());
						addressId = ai.getId();
						break;
					}
					address_name.setText(ai.getName());
					address_telephone.setText(ai.getPhonenumb());
					address_info.setText(ai.getAddress());
					addressId = ai.getId();
				}
			}else{
				
			}
		} else if(baseRes instanceof CouponHistoryResponse){
			CouponHistoryResponse chr = (CouponHistoryResponse) baseRes;
			if(chr.getResult().equals("0")){
				couponList = chr.getData();
				if (couponList == null || couponList.isEmpty()) {
					String[] mItems = { "无优惠券" };
					// 建立Adapter并且绑定数据源
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							this, android.R.layout.simple_spinner_item, mItems);
					payCouponSpinner.setAdapter(adapter);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					return;
				}
				String[] mItems = new String[couponList.size()+1];
				mItems[0] = "不使用优惠券";
				int i = 1;
				for(CouponInfo ci : couponList){
					mItems[i] = ci.getCouponName();
					i++;
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						this, android.R.layout.simple_spinner_item, mItems);
				payCouponSpinner.setAdapter(adapter);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				payCouponSpinner.setOnItemSelectedListener(spinnerItemSelectedClickListener);
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		if(tag.equals("MyAddressReq")){
			isRequsetAddress = true;
		}

	}
	
	OnItemSelectedListener spinnerItemSelectedClickListener = new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			ToastTool.showLongBigToast(PayActivity.this, "选择了第"+position+"个");
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
		
	};

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
	}
}
