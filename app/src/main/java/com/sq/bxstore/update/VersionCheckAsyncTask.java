package com.sq.bxstore.update;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.sq.bxstore.BXApplication;
import com.sq.bxstore.utils.Utils;

public class VersionCheckAsyncTask extends AsyncTask<String, Integer, String> {
	/**
	 * versionCode: 要比较的本地版本号
	 */
	private int versionCode;
	/**
	 * versionName: 从读取文件中提取当前版本的字段
	 */
	private String versionName;
	private IVersionUpdate iVersionUpdate;
	private String update_info;

	public interface IVersionUpdate {
		void updateVersion(String flag, String update_info);
	}

	public VersionCheckAsyncTask(IVersionUpdate interf, int versionCode,
			String versionName) {
		this.iVersionUpdate = interf;
		this.versionCode = versionCode;
		this.versionName = versionName;
	}

	@Override
	protected String doInBackground(String... arg0) {
		String verjson = Utils.connect(BXApplication.checkUpdateUrl);
		if (verjson == null)
			return "netness";
		String reValue = "no";
		try {
			JSONObject obj = new JSONObject(verjson);
			JSONObject info;
			if (obj.has("version_info_" + versionCode)) {
				info = obj.getJSONObject("version_info_" + versionCode);
			} else {
				info = obj.getJSONObject("version_info");
			}
			int current = 0;
			int force = 0;
			if (info.has(versionName)) {
				current = info.getInt(versionName);
			}
			if (info.has("updateType")) {
				force = info.getInt("updateType");
			}
			if (info.has("update_info")) {
				update_info = info.getString("update_info");
			}
			if (current > versionCode) {
				reValue = "yes";
			}
			if (force == 1) {
				reValue = "force";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reValue;
	}

	@Override
	protected void onPostExecute(String result) {
		if (result.equals("")) {
			iVersionUpdate.updateVersion("no", update_info);
		} else {
			iVersionUpdate.updateVersion(result, update_info);
		}
	}
}
