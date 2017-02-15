package com.sq.bxstore.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.adapter.OrderItemAdapter;
import com.sq.bxstore.bean.CartProduct;
import com.sq.bxstore.imp.OrderRouteOnCilckListener;

public class FragmentSearch extends Fragment {
	private TextView tv;
	private ListView orders;
	private List<CartProduct> cps = new ArrayList<CartProduct>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_search, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tv = (TextView) getView().findViewById(R.id.title_name);
		tv.setText("我的订单");
		
		
		for (int i = 0; i < 4; i++) {
			cps.add(new CartProduct(true, 4+i, 200*(i+1)+"", "值得拥有的三防材质男士运动鞋", "", "123.00", "", ""));
		}
		orders = (ListView) getView().findViewById(R.id.lv_order_products_list);
		orders.setAdapter(new OrderItemAdapter(getActivity(), cps,
				cilckListener));

	}
	
	OrderRouteOnCilckListener cilckListener = new OrderRouteOnCilckListener() {
		
		@Override
		public void OnClicked(int num) {
			// TODO Auto-generated method stub
//			Intent intent = new Intent(getActivity(),RouteActivity.class);
//			startActivity(intent);
		}
	};

	@Override
	public void onPause() {
		super.onPause();
	}

}
