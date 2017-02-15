package com.sq.bxstore.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.bean.RouteInfo;
/**
 * 物流信息
 * @author Cool
 *
 */
public class RouteItemAdapter extends BaseAdapter {
	
	private Context context;
	private List<RouteInfo> infos = new ArrayList<RouteInfo>();
	public RouteItemAdapter(Context context,List<RouteInfo> infos){
		this.context = context;
		this.infos = infos;
	}
	@Override
	public int getCount() {
		return infos.size();
	}
	@Override
	public Object getItem(int position) {
		return infos.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.view_route_item, null);
			holder = new ViewHolder();
			holder.icon =  (ImageView) convertView.findViewById(R.id. iv_route_icon );
			holder.icon_top_line =  convertView.findViewById(R.id.icon_top_line);
			holder.icon_bottom_line =  convertView.findViewById(R.id.icon_bottom_line );
			holder.ll_bottom_line =  (LinearLayout) convertView.findViewById(R.id.ll_bottom_line );
			holder.info =  (TextView) convertView.findViewById(R.id.tv_route_info );
			holder.info.setAutoLinkMask(Linkify.ALL);
			holder.time =  (TextView) convertView.findViewById(R.id.tv_route_time );
		    convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (position == 0) {
			holder.icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.logistics_track_arrive));
			convertView.setBackgroundColor(Color.WHITE);
			holder.icon_top_line.setVisibility(View.INVISIBLE);
			holder.icon_bottom_line.setVisibility(View.VISIBLE);
			holder.ll_bottom_line.setVisibility(View.VISIBLE);
		}else if (position == infos.size()-1) {
			holder.icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.logistics_track_point));
			convertView.setBackgroundColor(Color.parseColor("#F4F5F5"));
			holder.icon_bottom_line.setVisibility(View.INVISIBLE);
			holder.ll_bottom_line.setVisibility(View.INVISIBLE);
			holder.icon_top_line.setVisibility(View.VISIBLE);
		}else {
			holder.icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.logistics_track_point));
			convertView.setBackgroundColor(Color.parseColor("#F4F5F5"));
			holder.icon_top_line.setVisibility(View.VISIBLE);
			holder.icon_bottom_line.setVisibility(View.VISIBLE);
			holder.ll_bottom_line.setVisibility(View.VISIBLE);
		}
		
		holder.info.setText(infos.get(position).getInfo());
		holder.time.setText(infos.get(position).getTime());
		return convertView;
	}
	
	private class ViewHolder{
		private ImageView icon;
		private View icon_top_line;
		private View icon_bottom_line;
		private LinearLayout ll_bottom_line;
		private TextView info;
		private TextView time;
	}
}
