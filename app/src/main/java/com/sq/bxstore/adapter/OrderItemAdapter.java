package com.sq.bxstore.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.bean.CartProduct;
import com.sq.bxstore.imp.OrderRouteOnCilckListener;
import com.sq.bxstore.utils.ListViewUtils;
/**
 * 订单提交商品列表适配器
 * @author Cool
 *
 */
public class OrderItemAdapter extends BaseAdapter {
	
	private Context context;
	private List<CartProduct> cps = new ArrayList<CartProduct>();
	private OrderRouteOnCilckListener clickListener;
	public OrderItemAdapter(Context context,List<CartProduct> cps,OrderRouteOnCilckListener clickListener){
		this.context = context;
		this.cps =cps;
		this.clickListener =clickListener;
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
			convertView = View.inflate(context, R.layout.view_order_item, null);
			holder = new ViewHolder();
			holder.number =  (TextView) convertView.findViewById(R.id.tv_orl_number );
			holder.sum =  (TextView) convertView.findViewById(R.id.tv_orl_sum );
			holder.time =  (TextView) convertView.findViewById(R.id.tv_orl_time );
			holder.client =  (TextView) convertView.findViewById(R.id.tv_orl_client );
			holder.phone =  (TextView) convertView.findViewById(R.id.tv_orl_phone );
			holder.linkman =  (TextView) convertView.findViewById(R.id.tv_orl_linkman );
			holder.state =  (TextView) convertView.findViewById(R.id.tv_orl_state );
			holder.route =  (Button) convertView.findViewById(R.id.btn_route );
			holder.gooodslist =  (ListView) convertView.findViewById(R.id.lv_order_products_list );
		    convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.route.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				clickListener.OnClicked(position);
			}
		});
		
		initProductDate(holder);
		return convertView;
	}
	
	
	
	/**
	 * 赋值商品列表数据
	 * @param holder
	 */
	private void initProductDate(ViewHolder holder) {
		// TODO Auto-generated method stub
		OrderGoodItemAdapter goodItemAdapter = new OrderGoodItemAdapter(context,cps);
		holder.gooodslist.setAdapter(goodItemAdapter);
		ListViewUtils.setListViewHeightBasedOnChildren(holder.gooodslist);
	}
	@Override  
	public boolean isEnabled(int position) {   
	   return false;   
	}
	
	
	private class ViewHolder{
		private TextView number;
		private TextView sum;
		private TextView time;
		private TextView client;
		private TextView phone;
		private TextView linkman;
		private TextView state;
		private Button route;
		private ListView gooodslist;
	}
}
