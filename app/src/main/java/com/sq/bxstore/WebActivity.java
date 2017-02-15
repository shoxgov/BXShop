package com.sq.bxstore;

import com.sq.bxstore.utils.ToastTool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends Activity {

	private WebView webview;
	private View progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.web);
		Intent intent = getIntent();
		if (!intent.hasExtra("url") || TextUtils.isEmpty(intent.getStringExtra("url"))) {
			finish();
			return;
		}
		String url = intent.getStringExtra("url");
		webview = (WebView) findViewById(R.id.web);
		progress = (View) findViewById(R.id.web_progress);
		WebSettings settings = webview.getSettings();
		// 设置加载进来的页面自适应手机屏幕 ,页面适应手机屏幕的分辨率，完整的显示在屏幕上，可以放大缩小
		settings.setUseWideViewPort(true);// 设置webview推荐使用的窗口，设置为true
		settings.setLoadWithOverviewMode(true);// 设置webview加载的页面的模式，也设置为true
		settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
		progress.setVisibility(View.VISIBLE);
		webview.loadUrl(url);
		// 防止跳到系统浏览器
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				progress.setVisibility(View.GONE);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				progress.setVisibility(View.GONE);
				ToastTool.showShortBigToast(WebActivity.this, "网页加载出错!");
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}
}
