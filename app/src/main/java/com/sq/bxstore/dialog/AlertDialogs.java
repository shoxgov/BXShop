package com.sq.bxstore.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.sq.bxstore.MainFragmentActivity;
import com.sq.bxstore.R;

public class AlertDialogs extends Dialog implements OnClickListener {

	private String showMessage = "";
	private String cancelText = "";
	private String submitText = "";
	private Alerts alert = null;
	private DisplayMetrics dm;
	private static AlertDialogs dialog = null;
	private static final Object mLock = new Object();

	public interface Alerts {
		void result(boolean agree);
	}

	public static AlertDialogs getDialog(Context context) {
		synchronized (mLock) {
			destroyAlertDialog();
			dialog = new AlertDialogs(context);
			return dialog;
		}
	}

	public static void destroyAlertDialog() {
		try {
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dialog = null;
		}
	}

	private AlertDialogs(Context context) {
		super(context, R.style.dialogStyleRadiusCorner);
		dm = new DisplayMetrics();
		dm = context.getApplicationContext().getResources().getDisplayMetrics();
		// screenWidthPixels = dm.widthPixels;
	}

	private AlertDialogs(Context context, int themeId) {
		super(context, R.style.dialogStyleRadiusCorner);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_join_request);
		TextView showInfo = (TextView) findViewById(R.id.showInfo);
		Button canceBtn = (Button) findViewById(R.id.refuseBtn);
		Button submitBtn = (Button) findViewById(R.id.agreeBtn);
		if (!TextUtils.isEmpty(cancelText)) {
			canceBtn.setText(cancelText);
		}
		if (!TextUtils.isEmpty(submitText)) {
			canceBtn.setText(submitText);
		}
		if (TextUtils.isEmpty(showMessage)) {
			showInfo.setVisibility(View.GONE);
		} else {
			showInfo.setVisibility(View.VISIBLE);
			showInfo.setText(showMessage);
		}
		// titleTview.setText(title);
		int width = (int) (MainFragmentActivity.screenWidthPixels * 0.8);
		if (width <= 0) {
			width = (int) (dm.widthPixels * 0.8);
		}
		if (width <= 0) {
			width = LayoutParams.WRAP_CONTENT;
		}
		getWindow().setLayout(width, LayoutParams.WRAP_CONTENT);//(dm.heightPixels / 4.0)
		submitBtn.setOnClickListener(this);
		canceBtn.setOnClickListener(this);
	}

	public AlertDialogs setMessage(String message) {
		this.showMessage = message;
		return this;
	}

	public AlertDialogs setTitle(String title) {
		return this;
	}

	public AlertDialogs setButtonText(String submitText, String cancelText) {
		this.submitText = submitText;
		this.cancelText = cancelText;
		return this;
	}

	public AlertDialogs setAlert(Alerts alert) {
		this.alert = alert;
		return this;
	}

	@Override
	public void show() {
		if (alert == null)
			throw new NullPointerException(
					"must set alert before show()! error: alert is null");
		super.show();
	}

	@Override
	public void dismiss() {
		alert = null;
		super.dismiss();
	}

	@Override
	public void onBackPressed() {
		dismiss();
		super.onBackPressed();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.refuseBtn:
			alert.result(false);
			break;
		case R.id.agreeBtn:
			alert.result(true);
			break;
		}
		dismiss();
	}
}
