package com.sq.bxstore.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sq.bxstore.AddressEditActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.bean.AddressInfo;
import com.sq.bxstore.bean.UserInfoBean;
import com.sq.bxstore.dialog.AlertDialogs;
import com.sq.bxstore.dialog.AlertDialogs.Alerts;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.EditMyAddressReq;
import com.sq.bxstore.net.request.EditMyAddressReq.AddressHandType;
import com.sq.bxstore.net.response.EditMyAddressResponse;
import com.sq.bxstore.utils.ToastTool;
import com.sq.bxstore.utils.Utils;

public class MyAddressManagerAdapter extends BaseAdapter implements NetCallBack {
	private List<AddressInfo> list = new ArrayList<AddressInfo>();
	private Activity activity;
	/**
	 * 记录删除的位置
	 */
	private int delPosition = -1;

	public MyAddressManagerAdapter(Activity activity) {
		this.activity = activity;
	}

	public void setData(List<AddressInfo> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public AddressInfo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(activity).inflate(
				R.layout.address_manager_item, null, false);
		TextView name = (TextView) convertView
				.findViewById(R.id.address_manager_name);
		TextView tel = (TextView) convertView
				.findViewById(R.id.address_manager_tel);
		TextView detail = (TextView) convertView
				.findViewById(R.id.address_manager_detail);
		TextView tag = (TextView) convertView
				.findViewById(R.id.address_tag);
		Button edit = (Button) convertView
				.findViewById(R.id.address_manager_edit);
		Button del = (Button) convertView
				.findViewById(R.id.address_manager_del);
		final AddressInfo ab = list.get(position);
		name.setText(ab.getName());
		tel.setText(ab.getPhonenumb());
		detail.setText(ab.getAddress());
		if(ab.getIsuse() == 1){//// 1默认 0非默认
			tag.setVisibility(View.VISIBLE);
		} else {
			tag.setVisibility(View.INVISIBLE);
		}
		edit.setTag(ab);
		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(activity, AddressEditActivity.class);
				intent.putExtra("mode", "edit");
				intent.putExtra("id", ab.getId()+"");
				intent.putExtra("name", ab.getName());
				intent.putExtra("tel", ab.getPhonenumb());
				intent.putExtra("detail", ab.getAddress());
				intent.putExtra("isuse", ab.getIsuse());
				activity.startActivityForResult(intent, 11);
			}
		});
		del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Utils.isFastDoubleClick()) {
					return;
				}
				Alerts alert = new Alerts() {

					@Override
					public void result(boolean agree) {
						if (agree) {
							EditMyAddressReq req = new EditMyAddressReq();
							req.setNetCallback(MyAddressManagerAdapter.this);
							req.setUserid(UserInfoBean.userId);
							req.setAddressid(ab.getId() + "");
							req.setHandType(AddressHandType.DEL);
							req.addRequest();
						}
					}
				};
				AlertDialogs dialog = AlertDialogs.getDialog(activity);
				String showInfo = "确认删除吗？";
				dialog.setAlert(alert).setMessage(showInfo).show();
				delPosition = position;
			}
		});
		return convertView;
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		if (baseRes instanceof EditMyAddressResponse) {
			EditMyAddressResponse emr = (EditMyAddressResponse) baseRes;
			if (emr.getResult().equals("0")) {
				if (delPosition >= 0) {
					list.remove(delPosition);
					notifyDataSetChanged();
				}
				delPosition = -1;
			} else {
				ToastTool.showShortBigToast(activity, emr.getMessage());
			}
		}

	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {

	}

}
