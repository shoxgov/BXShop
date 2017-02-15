package com.sq.bxstore.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sq.bxstore.BXApplication;
import com.sq.bxstore.R;
import com.sq.bxstore.adapter.SecondaryListViewAdapter;
import com.sq.bxstore.bean.SecondaryCateBean;
import com.sq.bxstore.net.BaseResponse;
import com.sq.bxstore.net.NetCallBack;
import com.sq.bxstore.net.request.AllClassificationReq;

public class FragmentSecondarySort extends Fragment implements NetCallBack {

	private ListView listView;
	private SecondaryListViewAdapter listAdapter;

	/**
	 * @param position
	 *            当切换不同的类时，要重新加载不同的数据
	 */
	public void fresh() {
		List<SecondaryCateBean> scbList = null;
		try {
			scbList = BXApplication.getInstance().getSortData().get(0).getSecondaryCateList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		fresh(scbList);
	}
	
	public void fresh(List<SecondaryCateBean> scbList) {
		if(scbList == null){
			return;
		}
		listAdapter.setData(scbList);
	}

	private void requestFirstClassification() {
		AllClassificationReq req = new AllClassificationReq();
		req.setNetCallback(this);
		req.addRequest();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_secondary_sort, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		fresh();
	}

	private void initView() {
		listView = (ListView) getView().findViewById(
				R.id.fragment_listview_secondary);
		listAdapter = new SecondaryListViewAdapter(getActivity());
		listView.setAdapter(listAdapter);
	}

	@Override
	public void onNetResponse(BaseResponse baseRes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetErrorResponse(String tag, Object error) {
		// TODO Auto-generated method stub
		
	}

}
