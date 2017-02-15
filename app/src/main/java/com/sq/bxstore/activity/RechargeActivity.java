package com.sq.bxstore.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.sq.bxstore.R;
import com.sq.bxstore.adapter.RechargeGridViewAdapter;
import com.sq.bxstore.alipay.PayResult;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.RechargeObtainOrderidReq;
import com.sq.bxstore.net.request.WeixinPayObtailOrderReq;
import com.sq.bxstore.net.response.RechargeObtainOrderidResponse;
import com.sq.bxstore.net.response.RechargeObtainOrderidResponse.RechargeOrderInfo;
import com.sq.bxstore.net.response.WeixinPayObtailOrderResponse;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.SignUtils;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.view.MyGridView;
import com.sq.bxstore.view.TitleBarLayout;
import com.sq.bxstore.view.TitleBarLayout.TitleBarListener;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class RechargeActivity extends Activity implements IWXAPIEventHandler,
		OnClickListener, NetCallBack, TitleBarListener {
	protected static final int SDK_PAY_FLAG = 0;
	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI api;
	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
	// ///// 支付宝支付用的属性
	public static final String PARTNER = "2088522299051760";
	// 商户收款账号
	public static final String SELLER = "hunanbeixiang@126.com";
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKNQQOirq/VUjp+zx6mjPU08OOftfRonOFxEbquDQb1h6uHYFAPr73AF32WTU2gsQSXcJ4cq8uGVi1vs2nOsO6CcRSvBUz13piUdSmmiDEmCoNi8d3WUVhfx49VPCmJPpAfh4ncAG30c43lc35CxFwrFF1oYVIYKuwzXZI6NDpQpAgMBAAECgYEAlK7yCWKSMc8D8l3o/4aAvxp+7JiI9xCR/V7IKxpa9aJ6B5eD0XYyDZkrI/0XSJZR0bg3vJXbfol50P04fWtm9IPZFPcmyip0DUJfB9uUdeElJdLKBwunJ0AI2OeYr2qwVtXwesHJZ+a9xQyn3JUmufCPCddYaGMbdcUlfblLiQECQQDWQsm9wz+DfBGTzaPyEEGii61PAIF23GXvYFPJQX4sKkHtfT5Xut9ZhUrqwjXGmNCpMvpxX5daHItbS66ygMtxAkEAwyC2rI9Kka/L/p5BM4FzuWug7/4QUGLdoYv9Fqt4hpI/pF74pm/4HOnBAleHmh3qpqlVfZGtCbnZmfrPpAHIOQJAWwwMgGfcsHnjWkRzW2SPD4x9O+oCGck0Q9FSKV58SDY4x7uF52/Qg1EB8dpPUbIUUSIJjG3Leg5RJZ12ggH68QJBAMFMuApsDQgT6fc7mQXIGPc3qv/0ZP7tYfd2MWtyxHwzhz5zIsnmpBkobB7AzUGcn2co2tsBJDU9WlaSw34KkekCQQCkQR9HzSQd8R7CV/cNZIT7xPctdceNg3aI6ZkrQ63wEIVy9gu33Vjgp0ouk41tcXrdAN0KAqq38ssMplO55V1k";
	// RSA_PUBLIC="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnVj+iVd79IUBQhVO3IXlbB24uHDkVWj/iLaWWjH+x1EFqVVxNmIw5vME8uH26+P4ZLqqh6LJFGE+IJfNtSRQiZupSuH2+PW8cibcTp4AnHmHzx8DR7alnuWj5BrcaEovEd0nMXz30ZqnPKkyMQeR+mfWO2/uiCPJYAmZxp4/ltpbd+Kc4/G1qzuRPXlCRxdy4C90J+EMT058UEvuJJQkDYE2mZa/uqPP0C4dth08GqduV7wki9NqGmrfd5e8xotJQcbkePVmfZiyUFuUR/VlwX2apiZFjnMfYtmevij1+dz8nRIaYy4irJItOCM4suWbl2KmSSgWb5n/ztVTZMNf7QIDAQAB";
	// /////////////////////////////////
	/**
	 * 支付方式：0-账户支付；1-支付宝；2-微信支付
	 */
	private int payType = 1;
	private Button pay;
	private Dialog dialog;
	private TextView payTypeText;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG:// 支付宝返回
				PayResult payResult = new PayResult(
						(Map<String, String>) msg.obj);
				/**
				 * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为9000则代表支付成功
				if (TextUtils.equals(resultStatus, "9000")) {
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
					Toast.makeText(RechargeActivity.this, "支付成功",
							Toast.LENGTH_SHORT).show();
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					Toast.makeText(RechargeActivity.this, "支付失败",
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	};
	private MyGridView gridView;
	private RechargeGridViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recharge);
		initViews();
		init();
	}

	private void init() {
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
		titleLayout.setTitle("充值中心");
		titleLayout.setTitleBarListener(this);
		gridView = (MyGridView) findViewById(R.id.recharge_gridview);
		payTypeText = (TextView) findViewById(R.id.pay_type_txt);
		pay = (Button) findViewById(R.id.pay);
		pay.setOnClickListener(this);
		findViewById(R.id.pay_type_layout).setOnClickListener(this);
		adapter = new RechargeGridViewAdapter(this);
		gridView.setAdapter(adapter);
		List<String> data = new ArrayList<String>();
		data.add("100元");
		data.add("200元");
		data.add("300元");
		data.add("400元");
		data.add("500元");
		adapter.setData(data);
		gridView.setOnItemClickListener(gridlistener);
	}

	OnItemClickListener gridlistener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			if (Utils.isFastDoubleClick()) {
				return;
			}
			adapter.setSelect(position);
			switch (position) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			}
		}

	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void wxpayMethod(String tradeSN, float price) {
		// 通过WXAPIFactory工厂，获取IWXAPI的实例
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
		// check
		int wxSdkVersion = api.getWXAppSupportAPI();
		if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
		} else {
			Toast.makeText(
					RechargeActivity.this,
					"wxSdkVersion = " + Integer.toHexString(wxSdkVersion)
							+ "\ntimeline not supported", Toast.LENGTH_LONG)
					.show();
		}

		String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
		Toast.makeText(RechargeActivity.this, "获取订单中...", Toast.LENGTH_SHORT)
				.show();
		String postParams = genProductArgs(tradeSN, price);
		WeixinPayObtailOrderReq req = new WeixinPayObtailOrderReq();
		req.setNetCallback(this);
		req.setStringPostParams(postParams);
		req.addRequest();
	}

	/**
	 * 微信支付获取订单
	 * 
	 * @Title: genProductArgs
	 * @param orderID
	 * @param money
	 * @return 返回类型 String
	 */
	private String genProductArgs(String orderID, float money) {
		StringBuffer xml = new StringBuffer();
		try {
			xml.append("</xml>");
			List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams
					.add(new BasicNameValuePair("appid", Constants.APP_ID));
			packageParams.add(new BasicNameValuePair("body", "nice付"));// 商品描述，商品或支付单简要描述，必填
			packageParams.add(new BasicNameValuePair("mch_id", "1428332902")); // 商户ID
			packageParams.add(new BasicNameValuePair("nonce_str", Utils
					.genNonceStr()));// 随机字符串，不长于32位。必填
			packageParams.add(new BasicNameValuePair("notify_url",
					"http://tc.weixinxa.com/app/weixin/example/****.php"));// 接收微信支付异步通知回调地址.必填
			packageParams.add(new BasicNameValuePair("out_trade_no", orderID));// 商户系统内部的订单号,32个字符内、可包含字母,必填
			packageParams.add(new BasicNameValuePair("spbill_create_ip",
					"127.0.0.1"));// Utils.getLocalIpAddress(this.getApplicationContext())));//
									// APP和网页支付提交用户端ip.必填
			packageParams.add(new BasicNameValuePair("total_fee", money * 100
					+ ""));// 订单总金额，只能为整数.必填
			packageParams.add(new BasicNameValuePair("trade_type", "APP"));// 取值如下：JSAPI，NATIVE，APP，WAP,必填
			String sign = Utils.genPackageSign(packageParams);
			packageParams.add(new BasicNameValuePair("sign", sign)); // 签名
			String xmlstring = toXml(packageParams);
			xmlstring = new String(xmlstring.getBytes("UTF-8"), "ISO-8859-1");
			return xmlstring;
		} catch (Exception e) {
			Log.e("e", "genProductArgs fail, 异常: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title: toXml
	 * @return 返回类型 String
	 */
	private static String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");
			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 支付宝支付
	 */
	private void alipayMethod() {
		// final String orderInfo =
		// "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D";
		// // 订单信息
		// 订单信息
		String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();
		// 支付行为需要在独立的非ui线程中执行
		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(RechargeActivity.this);
				Map<String, String> result = alipay.payV2(payInfo, true);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};
		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	// 微信发送请求到第三方应用时，会回调到该方法
	@Override
	public void onReq(BaseReq req) {
		switch (req.getType()) {
		case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
			Toast.makeText(this, "COMMAND_GETMESSAGE_FROM_WX",
					Toast.LENGTH_LONG).show();
			break;
		case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
			Toast.makeText(this, "COMMAND_SHOWMESSAGE_FROM_WX",
					Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

	// 第三方应用发送到微信的请求处理后的响应结果，支付完成后，微信APP会返回到商户APP并回调onResp函数
	@Override
	public void onResp(BaseResp resp) {
		int result = 0;
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			result = R.string.errcode_success;
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			result = R.string.errcode_cancel;
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			result = R.string.errcode_deny;
			break;
		/*
		 * 0 成功 展示成功页面 -1 错误
		 * 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。 -2 用户取消
		 * 无需处理。发生场景：用户不支付了，点击取消，返回APP。
		 */
		default:
			result = R.string.errcode_unknown;
			break;
		}
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pay:
			RechargeObtainOrderidReq req = new RechargeObtainOrderidReq();
			req.setNetCallback(this);
			req.setRequestType(RequestType.POST);
			req.setUsername(UserInfoBean.userName);
			req.setTrade_amount("100");
			req.setTrade_type("1");//1 支付宝 2微信 3银联
			req.addRequest();
//			switch (payType) {
//			case 1:
//				alipayMethod();
//				break;
//			case 2:
//				wxpayMethod();
//				break;
//			}
			break;
		case R.id.pay_type_layout:
			if (dialog == null) {
				dialog = new Dialog(this, R.style.MyDialog);
				View view = View.inflate(this, R.layout.dialog_pay_type, null);
				RadioGroup payGroup = (RadioGroup) view
						.findViewById(R.id.dialog_pay_group);
				dialog.setContentView(view);
				dialog.setCanceledOnTouchOutside(false);
				payGroup.setOnCheckedChangeListener(listener);
				view.findViewById(R.id.dialog_pay_ok).setOnClickListener(
						onClickListener);
				view.findViewById(R.id.dialog_pay_cancel).setOnClickListener(
						onClickListener);
			}
			dialog.show();
			break;
		default:
			break;
		}
	}

	/**
	 * create the order info. 创建订单信息--------支付宝
	 */
	private String getOrderInfo(String subject, String body, String price) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";
		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";
		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";
		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";
		// 服务器异步通知页面路径
		// 首先我们用支付宝支付之后，支付宝会返回给我们两个通知，一个是同步的，就是我们点击支付后支付宝直接反馈给我们客户端的信息，我们可以直接拿到，根据反馈的结果可以初步判定该次交易是否成功，第二个就是服务器异步的通知，这个异步的通知是支付宝的服务器端发给我们服务器端的信息，我们在客户端是直接获取不了的，那支付宝的服务器怎么知道我们服务器的路径呢，那就是这参数的作用了，我们给支付宝服务器一个路径，它就会在订单状态改变的时候给我们服务器端一个反馈，告诉服务器这次交易的状态，如果服务器结果判定该次交易成功了，就必须返给支付宝服务器一个success，要不服务器会一直给我们异步通知，因为它不知道该次交易是否完成了
		orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
				+ "\"";
		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";
		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";
		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";
		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";
		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";
		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.dialog_pay_cancel:
				dialog.dismiss();
				dialog = null;
				break;
			case R.id.dialog_pay_ok:
				switch (payType) {
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

	OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup rg, int arg1) {
			// 获取变更后的选中项的ID
			int radioButtonId = rg.getCheckedRadioButtonId();
			switch (radioButtonId) {
			case R.id.dialog_pay_alipay:
				payType = 1;
				// ToastTool.showShortBigToast(PayActivity.this, "你选择了支付宝支付方式");
				break;
			case R.id.dialog_pay_weixin:
				payType = 2;
				// ToastTool.showShortBigToast(PayActivity.this, "你选择了微信支付方式");
				break;
			}
		}

	};

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (isFinishing()) {
			return;
		}
		if (baseRes instanceof WeixinPayObtailOrderResponse) {
			WeixinPayObtailOrderResponse wpoor = (WeixinPayObtailOrderResponse) baseRes;
			int retCode = wpoor.getRetcode();
			if (retCode == -99) {
				PayReq req = new PayReq();
				// req.appId = "wxf8b4f85f3a794e77"; // 测试用appId
				req.appId = wpoor.getAppid();
				req.partnerId = wpoor.getPartnerid();
				req.prepayId = wpoor.getPrepayid();
				req.nonceStr = wpoor.getNoncestr();
				req.timeStamp = wpoor.getTimestamp();
				req.packageValue = "Sign=WXPay";
				req.sign = wpoor.getSign();
				req.extData = "app data"; // optional
				Toast.makeText(RechargeActivity.this, "正常调起支付",
						Toast.LENGTH_SHORT).show();
				// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
				api.sendReq(req);
			} else {
				Log.d("PAY_GET", "返回错误" + wpoor.getRetmsg());
				Toast.makeText(RechargeActivity.this,
						"返回错误" + wpoor.getRetmsg(), Toast.LENGTH_SHORT).show();
			}
		} else if(baseRes instanceof RechargeObtainOrderidResponse){
			RechargeObtainOrderidResponse roor = (RechargeObtainOrderidResponse) baseRes;
			if (roor.getResult().equals("0")) {
				RechargeOrderInfo data = roor.getData();
				if (data != null) {
					switch (payType) {
					case 1:
						alipayMethod();
						break;
					case 2:
						wxpayMethod(data.getTradeSn(), data.getTradeAmount());
						break;
					}
				}
			} else {
				Toast.makeText(RechargeActivity.this, "充值失败",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
	}
}
