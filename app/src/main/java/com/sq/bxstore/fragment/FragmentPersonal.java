package com.sq.bxstore.fragment;

import java.io.File;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sq.bxstore.LoginActivity;
import com.sq.bxstore.PersonMyCriticsmActivity;
import com.sq.bxstore.PersonMyOrdersActivity;
import com.sq.bxstore.PersonRegisterActivity;
import com.sq.bxstore.PersonSettingActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.activity.PersonMyShareActivity;
import com.sq.bxstore.activity.PersonMyTeamActivity;
import com.sq.bxstore.activity.PersonMyWalletActivity;
import com.sq.bxstore.adapter.PersonGridViewAdapter;
import com.sq.bxstore.bean.SettingItemInfo;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.dialog.AlertDialogs;
import com.sq.bxstore.dialog.AlertDialogs.Alerts;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.BaseCommReq.RequestType;
import com.sq.bxstore.net.request.UserInfoReq;
import com.sq.bxstore.net.response.UserInfoResponse;
import com.sq.bxstore.net.response.WalletInfoResponse.Wallet;
import com.sq.bxstore.utils.PreferenceUtil;
import com.sq.bxstore.utils.Tools;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.view.MyGridView;

@SuppressLint("NewApi")
public class FragmentPersonal extends Fragment implements OnClickListener,
		NetCallBack {
	private ImageView uPic; // 用户头像
	private TextView name; // 名字

	private String[] items = new String[] { "选择本地图片", "拍照" };
	private TextView cashWallet;
	private TextView casinoPoint;
	private MyGridView gridView;
	private PersonGridViewAdapter adapter;
	private ArrayList<SettingItemInfo> data;
	private TextView starGrade;
	/* 头像名称 */
	// private static final String IMAGE_FILE_NAME = "faceimage.jpg";
	private static final String IMAGE_FILE_NAME = Environment
			.getExternalStorageDirectory() + File.separator + "faceimage.jpg";

	/* 请求 */
	private static final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_personal, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		uPic = (ImageView) getView().findViewById(R.id.iv_user_pic);
		name = (TextView) getView().findViewById(R.id.person_name);
		cashWallet = (TextView) getView().findViewById(R.id.person_cash_wallet);
		casinoPoint = (TextView) getView().findViewById(
				R.id.person_casino_point);
		starGrade = (TextView) getView().findViewById(R.id.person_star_grade);
		gridView = (MyGridView) getView().findViewById(R.id.person_gridview);
		uPic.setOnClickListener(this);
		getActivity().findViewById(R.id.person_logout).setOnClickListener(this);
		cashWallet.setText(Html.fromHtml(getString(R.string.cash_wallet)
				+ "  --"));
		casinoPoint.setText(Html.fromHtml(getString(R.string.casino_point)
				+ "  --"));
		name.setText(UserInfoBean.name);
		adapter = new PersonGridViewAdapter(getActivity());
		gridView.setAdapter(adapter);
		initInfos();
		adapter.setData(data);
		gridView.setOnItemClickListener(listener);
	}

	private void initInfos() {
		data = new ArrayList<SettingItemInfo>();
		SettingItemInfo mydallor = new SettingItemInfo();
		mydallor.setImgId(R.mipmap.person_wallet);
		mydallor.setName("我的钱包");
		data.add(mydallor);
		SettingItemInfo sii0 = new SettingItemInfo();
		sii0.setImgId(R.mipmap.person_myorder);
		sii0.setName("我的订单");
		data.add(sii0);
		SettingItemInfo sii1 = new SettingItemInfo();
		sii1.setImgId(R.mipmap.person_mycritics);
		sii1.setName("我的评论");
		data.add(sii1);
		SettingItemInfo sii2 = new SettingItemInfo();
		sii2.setImgId(R.mipmap.person_register);
		sii2.setName("推荐注册");
		data.add(sii2);
		SettingItemInfo sii3 = new SettingItemInfo();
		sii3.setImgId(R.mipmap.person_setting);
		sii3.setName("设置");
		data.add(sii3);
		SettingItemInfo sii4 = new SettingItemInfo();
		sii4.setImgId(R.mipmap.person_myteam);
		sii4.setName("我的团队");
		data.add(sii4);
		SettingItemInfo share = new SettingItemInfo();
		share.setImgId(R.mipmap.person_share);
		share.setName("我的推荐分享");
		data.add(share);
	}

	@Override
	public void onResume() {
		super.onResume();
		requestMyWalletInfo();
		cashWallet.setText(Html.fromHtml(getString(R.string.cash_wallet)
				+ "<big><font color=red>" + UserInfoBean.userKyBalance
				+ " RMB</font></big>"));
		casinoPoint.setText(Html.fromHtml(getString(R.string.casino_point)
				+ "</big><font color=red>" + UserInfoBean.userDjBalance
				+ " RMB</font></big>"));
		starGrade.setText(Html.fromHtml(getString(R.string.star_grade)
				+ "</big><font color=red>" + UserInfoBean.userGrade
				+ "星</font></big>"));
	}

	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			if (Utils.isFastDoubleClick()) {
				return;
			}
			switch (position) {
			case 0:
				Intent mywallet = new Intent();
				mywallet.setClass(getActivity(), PersonMyWalletActivity.class);
				getActivity().startActivity(mywallet);
				break;
			case 1:
				Intent myorders = new Intent();
				myorders.setClass(getActivity(), PersonMyOrdersActivity.class);
				getActivity().startActivity(myorders);
				break;
			case 2:
				Intent mycriticsm = new Intent();
				mycriticsm.setClass(getActivity(),
						PersonMyCriticsmActivity.class);
				getActivity().startActivity(mycriticsm);
				break;
			case 3:
				Intent register = new Intent();
				register.setClass(getActivity(), PersonRegisterActivity.class);
				getActivity().startActivity(register);
				break;
			case 4:
				Intent intent = new Intent();
				intent.setClass(getActivity(), PersonSettingActivity.class);
				getActivity().startActivity(intent);
				break;
			case 5:
				Intent myteam = new Intent();
				myteam.setClass(getActivity(), PersonMyTeamActivity.class);
				getActivity().startActivity(myteam);
				break;
			case 6:
				Intent share = new Intent();
				share.setClass(getActivity(), PersonMyShareActivity.class);
				getActivity().startActivity(share);
				break;
			}
		}

	};

	private void requestMyWalletInfo() {
		UserInfoReq req = new UserInfoReq();
		req.setNetCallback(this);
		req.setRequestType(RequestType.POST);
		req.setUsername(UserInfoBean.userName);
		req.addRequest();
	}

	/**
	 * 显示选择对话
	 */
	private void showDialog() {

		new AlertDialog.Builder(getActivity())
				.setTitle("设置头像")
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							Intent intentFromGallery = new Intent();
							intentFromGallery.setType("image/*"); // 设置文件类型
							intentFromGallery
									.setAction(Intent.ACTION_GET_CONTENT);
							startActivityForResult(intentFromGallery,
									IMAGE_REQUEST_CODE);
							break;
						case 1:

							Intent intentFromCapture = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);
							// 判断存储卡是否可以用，可用进行存
							if (Tools.hasSdcard()) {

								// intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new
								// File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME)));
								intentFromCapture.putExtra(
										MediaStore.EXTRA_OUTPUT,
										Uri.fromFile(new File(IMAGE_FILE_NAME)));
							}
							System.out.println("进入拍照");
							startActivityForResult(intentFromCapture,
									CAMERA_REQUEST_CODE);
							break;
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 结果码不等于取消时
		if (resultCode != getActivity().RESULT_CANCELED) {

			switch (requestCode) {
			case IMAGE_REQUEST_CODE:
				startPhotoZoom(data.getData());
				break;
			case CAMERA_REQUEST_CODE:
				if (Tools.hasSdcard()) {
					// File tempFile = new
					// File(Environment.getExternalStorageDirectory()+
					// IMAGE_FILE_NAME);
					File tempFile = new File(IMAGE_FILE_NAME);
					startPhotoZoom(Uri.fromFile(tempFile));
				} else {
					System.out.println("照片照完，但是找不到照片，无法切图！");
					Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！",
							Toast.LENGTH_LONG).show();
				}

				break;
			case RESULT_REQUEST_CODE:
				if (data != null) {
					getImageToView(data);
				}
				break;
			}
		}
	};

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 2);
	}

	/**
	 * 保存裁剪之后的图片数
	 * 
	 * @param picdata
	 */
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			uPic.setImageDrawable(drawable);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_user_pic:
			// showDialog();
			break;

		case R.id.person_logout:
			Alerts alert = new Alerts() {

				@Override
				public void result(boolean agree) {
					if (agree) {
						PreferenceUtil.commitBoolean("remember", false);
						PreferenceUtil.commitString("lastUsername", "");
						PreferenceUtil.commitString("lastPassword", "");
						Intent login = new Intent();
						login.setClass(getActivity(), LoginActivity.class);
						getActivity().startActivity(login);
						getActivity().finish();
					}
				}
			};
			AlertDialogs dialog = AlertDialogs.getDialog(getContext());
			String showInfo = "确认退出吗？";
			dialog.setAlert(alert).setMessage(showInfo).show();
			break;
		default:
			break;
		}
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (getActivity() == null || getActivity().isFinishing()
				|| getActivity().isFinishing()) {
			return;
		}
		if (baseRes instanceof UserInfoResponse) {
			UserInfoResponse wir = (UserInfoResponse) baseRes;
			if (wir.getResult().equals("0")) {
//						cashWallet.setText(Html
//								.fromHtml(getString(R.string.cash_wallet)
//										+ "<big><font color=red>"
//										+ wallet.getBalance()
//										+ " USD</font></big>"));

			}
		}
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}
}
