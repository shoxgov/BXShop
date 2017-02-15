package com.sq.bxstore.adapter;

import java.text.DecimalFormat;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.sq.bxstore.BXApplication;
import com.sq.bxstore.GoodsDetailsActivity;
import com.sq.bxstore.R;
import com.sq.bxstore.imp.CartProductItemChangedListener;
import com.sq.bxstore.imp.NumChangedListener;
import com.sq.bxstore.net.response.ShopCarResponse.ShopItemInfo;
import com.sq.bxstore.utils.Utils;
import com.sq.bxstore.view.NumEditText;

/**
 * 商品类别适配器
 */
@SuppressLint("HandlerLeak")
public class ShopItemAdapter extends BaseAdapter {

	private Context context;
	private List<ShopItemInfo> data;
	private CartProductItemChangedListener itemChangedListener;

	// 对话框
	private Dialog dialog = null;
	private Button btn_ok = null;
	private Button btn_cancel = null;
	private TextView tv_price = null;
	private TextView tv_count = null;
	private NumEditText net_numedit = null;
	int count = 1;
	private ImageLoader imageLoader;

	public ShopItemAdapter(Context context,CartProductItemChangedListener itemChangedListener) {
		this.context = context;
		this.itemChangedListener = itemChangedListener;
		imageLoader = BXApplication.getInstance().getImageLoader();
	}

	public void setDat(List<ShopItemInfo> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.view_cartlist_item,
					null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.tv_cart_name);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.iv_cart_pic);
			holder.checked = (CheckBox) convertView
					.findViewById(R.id.cb_cart_item_check);
			holder.price = (TextView) convertView
					.findViewById(R.id.tv_cart_item_price);
			holder.numadd = (ImageView) convertView
					.findViewById(R.id.iv_product_num_add);
			holder.numdes = (ImageView) convertView
					.findViewById(R.id.iv_product_num_des);
			holder.numedit = (TextView) convertView
					.findViewById(R.id.et_product_num);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.checked
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean isCheck) {
						data.get(position).setCheck(isCheck);
						if (itemChangedListener != null) {
							itemChangedListener.itemCheckChanged(position,
									isCheck);
							itemChangedListener.updateSum();
						}
						// notifyDataSetChanged();
					}
				});
		holder.checked.setChecked(data.get(position).isCheck());// 要放到定义监听的后面，不然会有错
		holder.name.setText(data.get(position).getGoodsname());
		DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String price= decimalFormat.format(data.get(position).getPrice());//format 返回的是字符串
		holder.price.setText(price);
		holder.numedit.setText(data.get(position).getCount() + "");
		imageLoader.get(data.get(position).getImg(), imageLoader
				.getImageListener(holder.icon, R.mipmap.goods_list_item_bg,
						R.mipmap.goods_list_item_bg));
		holder.numedit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog(position);
			}
		});
		holder.numdes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int count_des = data.get(position).getCount();
				count_des--;
				if (count_des < 1) {
					return;
				}
				data.get(position).setCount(count_des);
				if (itemChangedListener != null) {
					itemChangedListener.updateSum();
					itemChangedListener.itemNumChanged(position, count_des);
				}
				notifyDataSetChanged();
			}
		});
		holder.numadd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int count_add = data.get(position).getCount();
				count_add++;
				data.get(position).setCount(count_add);
				if (itemChangedListener != null) {
					itemChangedListener.updateSum();
					itemChangedListener.itemNumChanged(position, count_add);
				}
				notifyDataSetChanged();
			}
		});
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(Utils.isFastDoubleClick()){
					return;
				}
				Intent goods = new Intent();
				goods.setClass(context, GoodsDetailsActivity.class);
				goods.putExtra("goodsid", data.get(position).getGoodsid()+"");
				context.startActivity(goods);
			}
		});
		return convertView;
	}

	private void showDialog(final int position) {
		count = data.get(position).getCount();
		if (dialog == null) {
			dialog = new Dialog(context, R.style.MyDialog);
			View view = View.inflate(context, R.layout.dialog_edit_cart_num,
					null);
			btn_ok = (Button) view.findViewById(R.id.btn_cart_dialog_ok);
			btn_cancel = (Button) view
					.findViewById(R.id.btn_cart_dialog_cancel);
			tv_price = (TextView) view.findViewById(R.id.tv_dialog_cart_price);
			tv_count = (TextView) view.findViewById(R.id.tv_dialog_cart_count);
			net_numedit = (NumEditText) view
					.findViewById(R.id.net_dialog_count);

			dialog.setContentView(view);
			dialog.setCanceledOnTouchOutside(false);
		}
		// 设置它的ContentView
		net_numedit.setNum(count);
		tv_count.setText(String.valueOf(count));
		tv_price.setText(data.get(position).getPrice()+"");
		net_numedit.setNumChangedListener(new NumChangedListener() {
			@Override
			public void numchanged(int num) {
				count = num;
				tv_count.setText(String.valueOf(num));
			}
		});

		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});

		btn_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!net_numedit.isEmpty()) {
					data.get(position).setCount(count);
					if (itemChangedListener != null) {
						itemChangedListener.updateSum();
						itemChangedListener.itemNumChanged(position, count);
					}
					notifyDataSetChanged();
					dialog.dismiss();
				}
			}
		});

		dialog.show();
	}

	private class ViewHolder {
		public TextView numedit;
		public ImageView numadd;
		public ImageView numdes;
		private ImageView icon;
		private TextView name;
		private CheckBox checked;
		private TextView price;
	}

}
