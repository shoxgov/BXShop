package com.sq.bxstore;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.wxlib.util.SysUtil;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.sq.bxstore.bean.FirstCateBean;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.net.response.MyOrdersResponse.OrderDetail;
import com.sq.bxstore.update.CheckAndUpdateApk;
import com.sq.bxstore.utils.BitmapLruCache;
import com.sq.bxstore.utils.Constants;
import com.sq.bxstore.utils.CrashHandler;
import com.sq.bxstore.utils.PreferenceUtil;
import com.umeng.analytics.MobclickAgent;

/**
mdpi 		120dpi~160dpi
hdpi 		160dpi~240dpi
xhdpi 		240dpi~320dpi
xxhdpi 		320dpi~480dpi
xxxhdpi 	480dpi~640dpi
 */
public class BXApplication extends Application {
	public static final String APP_SECRET = "91cf3f86d08fdcafce0b473ccda08b99";
	private static final String tag = "BXApplication";
	private static final String CONTENT_URI = "content://downloads/my_downloads/";
	/**
	 * updateUrl: 有更新时的下载Apk地址
	 */
	public static final String UPDATE_URL = "http://update.touchus.com/apps/TouChus.apk";
	/**
	 * checkUpdateUrl: 检查更新包的路径文件
	 */
	public static final String checkUpdateUrl = "http://update.touchus.com/apps/AppsVersion.txt";// HOST_URL
	private static BXApplication instance;
	/**
	 * 全局统一一个实例，节省资源
	 */
	private RequestQueue mRequestQueue;
	/**
	 * mImageLoader: 网络加载图片并缓存
	 */
	private ImageLoader mImageLoader;
	private String dirPATH = "bx_download";
	/**
	 * downloadId: 更新下载时的任务ID号
	 */
	public long downloadId = -1;
	/**
	 * isNeedUpdate: 是否需要升级标记
	 */
	public static boolean isNeedUpdate = false;
	public static String lastTime;
	// 下载任务管理器 有监听器
	private DownloadManager downloadManager;
	private DownloadChangeObserver apkDownloadObserver;
	private int fileTotalLength = -1;
	/**
	 * 全局变量 用于保存从我的订单列表跳到订单详情时，传值。
	 */
	private OrderDetail orderDetail;
	
	/**
	 * 全局变量，用于保存分类数据传递
	 */
	private List<FirstCateBean> sortData;

	public static BXApplication getInstance() {
		if (instance == null) {
			instance = new BXApplication();
		}
		return instance;
	}

	/**
	 * @return The Volley Request queue, the queue will be created if it is null
	 */
	public RequestQueue getRequestQueue() {
		// lazy initialize the request queue, the queue instance will be
		// created when it is accessed for the first time
		if (mRequestQueue == null) {
			synchronized (BXApplication.class) {
				if (mRequestQueue == null) {
					mRequestQueue = Volley
							.newRequestQueue(getApplicationContext());
				}
			}
		}
		return mRequestQueue;
	}

	/**
	 * @return
	 * @Description:图片加载网络
	 */
	public ImageLoader getImageLoader() {
		if (mImageLoader == null) {
			// int memClass = ((ActivityManager)
			// getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
			// // Use 1/8th of the available memory for this memory cache.
			// int cacheSize = 1024 * 1024 * memClass / 4;
			mImageLoader = new ImageLoader(getRequestQueue(),
					new BitmapLruCache());
		}
		return mImageLoader;
	}

	@Override
	public void onCreate() {
		Log.d("BXstore", "BXApplication>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>onCreate");
		super.onCreate();
		//必须首先执行这部分代码, 如果在":TCMSSevice"进程中，无需进行云旺（OpenIM）和app业务的初始化，以节省内存;
		SysUtil.setApplication(this);
		if (SysUtil.isTCMSServiceProcess(this)) {
			Log.d("BXstore", "isTCMSServiceProcess");
			return;
		}
		// 第一个参数是Application Context
		// 这里的APP_KEY即应用创建时申请的APP_KEY，同时初始化必须是在主进程中
		if (SysUtil.isMainProcess()) {
			Log.d("BXstore", "isMainProcess");
			YWAPI.init(this, Constants.CS_APP_KEY);
		}
		instance = this;
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(this);
		String SAVEPATH = Environment.getExternalStorageDirectory()
				+ "/beixiang/ImageCache";
		MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, "58a54f7b7666136e16000eca", "beixiangAndroid", MobclickAgent.EScenarioType.E_UM_NORMAL,true));
	}

	/**
	 * @param launguage
	 *            zh:中文；en:英文
	 */
	public void updateLocale(String language) {
//		Locale locale = new Locale(language);
//		Locale.setDefault(locale);
		Configuration config = getResources().getConfiguration();
		String lang = config.locale.getLanguage();
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		if (language.equals("zh")) {
			config.locale = Locale.SIMPLIFIED_CHINESE;
		} else {
			config.locale = Locale.ENGLISH;
		}
		getResources().updateConfiguration(config, metrics);
		 //保存设置语言的类型
        PreferenceUtil.commitString("language", language);
	}

	/**
	 * 取消更新下载
	 * 
	 * @Description:
	 */
	public void cancelDownloadById() {
		if (downloadId < 0)
			return;
		downloadManager.remove(downloadId);
		downloadId = -1;
	}

	/**
	 * 下载URL路径更新包
	 * 
	 * @param url
	 * @param mHandler
	 * @Description:
	 */
	public void downloadFileByUrl(String url, Handler mHandler) {
		if (downloadId > 0) {
			try {
				DownloadManager.Query query = new DownloadManager.Query();
				query.setFilterById(downloadId);
				Cursor c = downloadManager.query(query);
				if (c.moveToFirst()) {// 有记录
					int status = c.getInt(c
							.getColumnIndex(DownloadManager.COLUMN_STATUS));
					if (status == DownloadManager.STATUS_PAUSED) {
						Message msg = new Message();
						msg.what = CheckAndUpdateApk.SHOW_TOAST;
						msg.obj = getString(R.string.download_pause_hint);
						if (mHandler != null)
							mHandler.sendMessage(msg);
					}
					c.close();
					return;
				}
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
			// 创建下载请求
			DownloadManager.Request down = new DownloadManager.Request(
					Uri.parse(url));
			// 设置允许使用的网络类型，这里是移动网络和wifi都可以
			down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
					| DownloadManager.Request.NETWORK_WIFI);
			DateFormat formatter0 = new SimpleDateFormat("yyyyMMddHHmmss");
			down.setTitle("BxStore_" + formatter0.format(new Date()) + ".apk");// 设置下载中通知栏提示的标题
			down.setDescription(getString(R.string.downloaded));// 设置下载中通知栏提示的介绍
			down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);// 表示下载进行中和下载完成的通知栏是否显示
			// 禁止发出通知，既后台下载
			// down.setShowRunningNotification(false);
			// 不显示下载界面
			down.setVisibleInDownloadsUi(false);
			// 设置下载后文件存放的位置
			File dir = new File(dirPATH);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File folder = Environment
					.getExternalStoragePublicDirectory(dirPATH);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			down.setDestinationInExternalPublicDir(dirPATH, "BxStore_"
					+ formatter.format(new Date()) + ".apk");
			// 将下载请求放入队列
			downloadId = downloadManager.enqueue(down);
			// 把当前下载的ID保存起来
			SharedPreferences sPreferences = getSharedPreferences(
					"downloadcomplete", 0);
			sPreferences.edit().putLong("downloadId", downloadId).commit();
			// registerReceiver(receiver, new
			// IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
			if (apkDownloadObserver == null) {
				apkDownloadObserver = new DownloadChangeObserver(mHandler);
			}
			apkDownloadObserver.setDownloadId(mHandler, downloadId);
			getContentResolver().registerContentObserver(
					Uri.parse(CONTENT_URI + downloadId), false,
					apkDownloadObserver);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Uri uri = Uri.parse(UPDATE_URL);
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * @Class: DownloadChangeObserver
	 * @Package: com.unibroad.carphone
	 * @Description: 监听下载进度条变化 回调
	 * @version: V1.0
	 */
	class DownloadChangeObserver extends ContentObserver {
		private Handler handler;
		private long downloadId;

		public DownloadChangeObserver(Handler handler) {
			super(handler);
			this.handler = handler;
		}

		public void setDownloadId(Handler handler, long downloadId) {
			this.handler = handler;
			this.downloadId = downloadId;
		}

		@Override
		public void onChange(boolean selfChange) {
			try {
				queryDownloadStatus(handler, this.downloadId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param handler
	 * @Description: 从下载管理数据库查询状态
	 */
	private synchronized void queryDownloadStatus(Handler handler,
			long downloadId) throws Exception {
		if (downloadId < 0)
			return;
		DownloadManager.Query query = new DownloadManager.Query();
		query.setFilterById(downloadId);
		Cursor c = downloadManager.query(query);
		if (c != null && c.moveToFirst()) {
			int status = c.getInt(c
					.getColumnIndex(DownloadManager.COLUMN_STATUS));
			// int reasonIdx = c.getColumnIndex(DownloadManager.COLUMN_REASON);
			int titleIdx = c.getColumnIndex(DownloadManager.COLUMN_TITLE);
			int fileSizeIdx = c
					.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
			int bytesDLIdx = c
					.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
			String title = c.getString(titleIdx);
			int fileSize = c.getInt(fileSizeIdx);
			int bytesDL = c.getInt(bytesDLIdx);
			StringBuilder sb = new StringBuilder();
			sb.append(title);
			sb.append(">>>>").append(bytesDL).append("/").append(fileSize);
			// Display the status
			Log.d(tag, " " + sb.toString());
			switch (status) {
			case DownloadManager.STATUS_PAUSED:
				Log.d(tag, "  STATUS_PAUSED");
			case DownloadManager.STATUS_PENDING:
				Log.d(tag, "  STATUS_PENDING");
			case DownloadManager.STATUS_RUNNING:
				// 正在下载，不做任何事情
				Log.d(tag, "  STATUS_RUNNING");
				if (fileSize <= 0) {
					break;
				} else {
					fileTotalLength = fileSize;
				}
				if (title.contains("-")) {
					// downloadManager.remove(downloadId);
					Log.d(tag, "STATUS_RUNNING   title.contains(-)=" + title);
					break;
				}
				float progress = 0;
				float num = bytesDL * 1.0f / fileTotalLength;
				progress = (float) (Math.round(num * 1000)) / 10;// 这里的100就是2位小数点
				Message msg = new Message();
				msg.what = CheckAndUpdateApk.DOWNLOAD;
				msg.getData().putFloat("progress", progress);
				msg.getData().putString("title", title.toLowerCase());
				if (handler != null)
					handler.sendMessage(msg);
				break;
			case DownloadManager.STATUS_SUCCESSFUL:
				// 完成
				fileTotalLength = -1;
				// int filenameIdx =
				// c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
				int filenameUriIdx = c
						.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
				// String filePath = c.getString(filenameIdx);
				String fileUri = c.getString(filenameUriIdx);
				Log.d(tag, "STATUS_SUCCESSFUL hotspot  filenameuri" + fileUri);
				if (handler != null) {
					Message msg2 = new Message();
					msg2.what = CheckAndUpdateApk.DOWNLOAD_FINISH;
					msg2.obj = fileUri.toLowerCase();
					handler.sendMessage(msg2);
				}
				this.downloadId = -1;
				getSharedPreferences("downloadcomplete", 0).edit()
						.putLong("downloadId", 0).commit();
				try {
					getContentResolver().unregisterContentObserver(
							apkDownloadObserver);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case DownloadManager.STATUS_FAILED:
				// 清除已下载的内容，重新下载
				fileTotalLength = -1;
				// Translate the pause reason to friendly text.
				Log.d(tag, "STATUS_FAILED ");
				downloadManager.remove(downloadId);
				this.downloadId = -1;
				getSharedPreferences("downloadcomplete", 0).edit()
						.putLong("downloadId", 0).commit();
				try {
					getContentResolver().unregisterContentObserver(
							apkDownloadObserver);
				} catch (Exception e) {
					e.printStackTrace();
				}
				int reasonIdx = c.getColumnIndex(DownloadManager.COLUMN_REASON);
				int reason = c.getInt(reasonIdx);
				if (reason == DownloadManager.ERROR_INSUFFICIENT_SPACE) {
					if (handler != null)
						handler.sendEmptyMessage(CheckAndUpdateApk.DOWNLOAD_ERROR_INSUFFICIENT_SPACE);
				} else if (handler != null) {
					handler.sendEmptyMessage(CheckAndUpdateApk.DOWNLOAD_ERROR);
				}
				break;
			}
		}
		c.close();
	}

	/**
	 * *全局变量 用于保存从我的订单列表跳到订单详情时，传值。
	 * 
	 * @param orderDetail
	 */
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public OrderDetail getOrderDetail() {
		return this.orderDetail;
	}

	/**
	 * @param cateData 全局保存分类请求的数据，用于传值 
	 */
	public void setSortData(List<FirstCateBean> cateData) {
		this.sortData = cateData;
	}
	
	public List<FirstCateBean> getSortData(){
		return this.sortData;
	}
	///////////////////////////////////////////////
	//注册客户资询账号及登录
	//////////////////////////////////////////////
	public void loginCS() {
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				registerCSUser();
//			}
//		}).start();
		// 此实现不一定要放在Application onCreate中
		final String userid = Constants.CS_PREFIX + UserInfoBean.userId;
		YWIMKit mIMKit = YWAPI.getIMKitInstance(userid, Constants.CS_APP_KEY);
		// 开始登录
		IYWLoginService loginService = mIMKit.getLoginService();
		YWLoginParam loginParam = YWLoginParam.createLoginParam(userid,
				Constants.CS_PASSPORD);
		loginService.login(loginParam, new IWxCallback() {

			@Override
			public void onSuccess(Object... obj) {
				System.out.println("YWAPI login onSuccess:" + obj == null ? "null"
						: obj.toString());
			}

			@Override
			public void onProgress(int arg0) {
			}

			@Override
			public void onError(int errCode, String description) {
				// 如果登录失败，errCode为错误码,description是错误的具体描述信息
				System.out.println("YWAPI errCode=" + errCode + ", description="
						+ description);
			}
		});
	}
	
	private void registerCSUser() {
		/*
		 * 正式环境 	http://gw.api.taobao.com/router/rest 	    https://eco.taobao.com/router/rest
		 * 沙箱环境 	http://gw.api.tbsandbox.com/router/rest 	https://gw.api.tbsandbox.com/router/rest
		 
	└ nick 		String 	可选 		king 					昵称，最大长度64字节
    └ icon_url 	String 	可选 		http://xxx.com/xxx   	头像url，最大长度512字节
    └ email 	String  可选 		uid@taobao.com email	地址，最大长度128字节
    └ mobile 	String  可选 		18600000000 			手机号码，最大长度16字节
    └ taobaoid 	String 	可选 		taobaouser 				淘宝账号，最大长度64字节
    └ userid 	String  必须 		imuser 					im用户名，最大长度64字节
    └ password 	String 	必须		xxxxx 					im密码，最大长度64字节
    └ remark 	String 	可选 		demo 					remark，最大长度128字节
    └ extra 	String 	可选 		{} 						扩展字段（Json），最大长度4096字节
    └ career 	String 	可选 		demo  					职位，最大长度128字节
    └ vip 		String 	可选 		{} 						vip（Json），最大长度512字节
    └ address 	String 	可选 		demo 					地址，最大长度512
    └ name 		String  可选 		demo 					名字，最大长度64
    └ age 		Number 	可选 		123 					年龄
    └ gender 	String 	可选 		M 						性别。M: 男。 F：女
    └ wechat 	String  可选 		demo 					微信，最大长度64字节
    └ qq 		String 	可选 		demo 					qq，最大长度20字节
    └ weibo 	String 	可选 		demo 					微博，最大长度256字节
		 */
//		String url = "http://gw.api.taobao.com/router/rest";
//		TaobaoClient client = new DefaultTaobaoClient(url,
//				Constants.CS_APP_KEY, BXApplication.APP_SECRET);
//		OpenimUsersAddRequest req = new OpenimUsersAddRequest();
//		List<Userinfos> list2 = new ArrayList<Userinfos>();
//		Userinfos obj3 = new Userinfos();
//		obj3.setNick("" + UserInfoBean.name);
//		obj3.setIconUrl("http://c.hiphotos.baidu.com/baike/w%3D268%3Bg%3D0/sign=9d5ab0c0d300baa1ba2c40bd7f2bde2f/0d338744ebf81a4c536891e5d52a6059252da647.jpg");
//		obj3.setEmail("" + UserInfoBean.email);
//		obj3.setMobile("" + UserInfoBean.phone);
//		obj3.setTaobaoid("");
//		obj3.setUserid("bxuserid_" + UserInfoBean.userId);
//		obj3.setPassword("123456");
//		obj3.setRemark("demo");
//		obj3.setExtra("{}");
//		obj3.setCareer("demo");
//		obj3.setVip("{}");
////		obj3.setAddress("" + UserInfoBean.address);
//		obj3.setName("" + UserInfoBean.userName);
//		obj3.setAge(23L);
//		obj3.setGender("M");
//		obj3.setWechat("demo");
//		obj3.setQq("demo");
//		obj3.setWeibo("demo");
//		list2.add(obj3);
//		 req.setUserinfos(list2);
//		req.setUserinfos(obj3.toString());//是点提交测试按钮{"userid":"bxuserid_4002","password":"123456","nick":"翔宝宝","mobile":"18958090000","email": "hehe@taobao.com","icon_url": "http://c.hiphotos.baidu.com/baike/w%3D268%3Bg%3D0/sign=9d5ab0c0d300baa1ba2c40bd7f2bde2f/0d338744ebf81a4c536891e5d52a6059252da647.jpg"}
//		OpenimUsersAddResponse rsp = null;
//		try {
//			rsp = client.execute(req);
//			System.out.println(rsp.getBody());
//		} catch (ApiException e) {
//			e.printStackTrace();
//		}
	}
	////////////////////////////////////////end end 
}
