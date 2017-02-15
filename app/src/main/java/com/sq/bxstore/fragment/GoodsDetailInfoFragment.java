package com.sq.bxstore.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sq.bxstore.GoodsDetailsActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.bean.NetWorkConfig;
import com.sq.bxstore.net.response.GoodsDetailResponse;

public class GoodsDetailInfoFragment extends Fragment {

	private WebView webview;
	private View progress;
	String html  = "";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.goodsdetail_info_fragment, container,
				false);
	}

	@Override
	public void onResume() {
		super.onResume();
		updateInfo(((GoodsDetailsActivity) getActivity()).getGoodsDetailResponse());
//		progress.setVisibility(View.VISIBLE);
//		webview.loadUrl("https://detail.tmall.com/item.htm?spm=a1z10.5-b-s.w4011-14874520605.79.ojS5YT&id=523758101357&rn=9b02277da0565e2173e576251bb26292&abbucket=20#description");
		// WaitTool.showDialog(getActivity(), "");
		// 防止跳到系统浏览器
//		webview.setWebViewClient(new WebViewClient() {
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				view.loadUrl(url);
//				return super.shouldOverrideUrlLoading(view, url);
//			}
//
//			@Override
//			public void onPageFinished(WebView view, String url) {
//				// WaitTool.dismissDialog();
//				super.onPageFinished(view, url);
//				progress.setVisibility(View.GONE);
//			}
//
//			@Override
//			public void onReceivedError(WebView view, int errorCode,
//					String description, String failingUrl) {
//				progress.setVisibility(View.GONE);
//				// WaitTool.dismissDialog();
//				ToastTool.showShortBigToast(getActivity(), "网页加载出错!");
//				super.onReceivedError(view, errorCode, description, failingUrl);
//			}
//		});
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		webview = (WebView) getView().findViewById(R.id.goodsdetail_webview);
		progress = (View) getView().findViewById(R.id.goodsdetail_progress);
		WebSettings settings = webview.getSettings();
		// 设置加载进来的页面自适应手机屏幕 ,页面适应手机屏幕的分辨率，完整的显示在屏幕上，可以放大缩小
		settings.setUseWideViewPort(true);// 设置webview推荐使用的窗口，设置为true
		settings.setLoadWithOverviewMode(true);// 设置webview加载的页面的模式，也设置为true
		settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
		settings.setDefaultTextEncodingName("utf-8");  
	}

	public void updateInfo(GoodsDetailResponse gdr) {
		if(gdr == null){
			return;
		}
		try {
			html = gdr.getData().get(0).getDetail();
			webview.loadDataWithBaseURL(null,html.replaceAll("http://127.0.0.1:8080", NetWorkConfig.HTTP_HOST), "text/html", "utf-8",null);
			progress.setVisibility(View.GONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
