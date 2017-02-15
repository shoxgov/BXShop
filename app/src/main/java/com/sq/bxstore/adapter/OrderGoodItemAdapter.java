package com.sq.bxstore.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.bean.CartProduct;
/**
 * 订单提交商品列表适配器
 * @author Cool
 *
 */
public class OrderGoodItemAdapter extends BaseAdapter {
	
	private Context context;
	private List<CartProduct> cps = new ArrayList<CartProduct>();
	public OrderGoodItemAdapter(Context context,List<CartProduct> cps){
		this.context = context;
		this.cps =cps;
	}
	@Override
	public int getCount() {
		return cps.size();
	}
	@Override
	public Object getItem(int position) {
		return cps.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.view_ordergoods_item, null);
			holder = new ViewHolder();
			holder.ognumber =  (TextView) convertView.findViewById(R.id.tv_ognumber );
			holder.ogname =  (TextView) convertView.findViewById(R.id.tv_ogname );
			holder.ogprice =  (TextView) convertView.findViewById(R.id.tv_ogprice );
			holder.ogsum =  (TextView) convertView.findViewById(R.id.tv_ogsum );
			holder.ogcount =  (TextView) convertView.findViewById(R.id.tv_ogcount );
		    convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.ognumber.setText(cps.get(position).getFnumber());
		holder.ogname.setText(cps.get(position).getFname());
		holder.ogprice.setText(cps.get(position).getFprice());
		holder.ogsum.setText(cps.get(position).getFprice());
		holder.ogcount.setText(cps.get(position).getCount()+"");
		
		return convertView;
	}
	
	@Override  
	public boolean isEnabled(int position) {   
	   return false;   
	}
	
	private class ViewHolder{
		private TextView ognumber;
		private TextView ogname;
		private TextView ogprice;
		private TextView ogsum;
		private TextView ogcount;
	}
}
