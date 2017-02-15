package com.sq.bxstore;

import java.lang.reflect.Field;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

import com.android.volley.toolbox.ImageLoader;
import com.sq.bxstore.photo.PhotoView;
import com.sq.bxstore.photo.PhotoViewAttacher.OnViewTapListener;
import com.sq.bxstore.utils.LocationBitmapCacheTools;
import com.sq.bxstore.view.HackyViewPager;

public class GoodsDetailPicsActivity extends Activity {

	private HackyViewPager viewpager;
	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goodsdetail_pics);
		imageLoader = BXApplication.getInstance().getImageLoader();
		initViews();
	}

	private void initViews() {
		viewpager = (HackyViewPager) findViewById(R.id.goodsdetail_pics_viewpager);
		Intent intent = getIntent();
		List<String> imagePaths = intent.getStringArrayListExtra("data");
		if(imagePaths == null || imagePaths.isEmpty()){
			finish();
			return;
		}
		int position = intent.getIntExtra("position", 0);
		SamplePagerAdapter adapter = new SamplePagerAdapter(imagePaths);
		viewpager.setAdapter(adapter);
	    // 先强制设置到指定页面  
		try {
			Field field = viewpager.getClass().getField("mCurItem");
			field.setAccessible(true);
			field.setInt(viewpager, position);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 通过数据修改
		adapter.notifyDataSetChanged();
		// 切换到指定页面
		viewpager.setCurrentItem(position);
	}

	class SamplePagerAdapter extends PagerAdapter {

		private List<String> imagePaths;

		public SamplePagerAdapter(List<String> imagePaths) {
			this.imagePaths = imagePaths;
		}

		@Override
		public int getCount() {
			return imagePaths == null ? 0 : imagePaths.size();
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			if (LocationBitmapCacheTools.isFileExist(imagePaths.get(position))) {
				photoView.setImageBitmap(LocationBitmapCacheTools
						.getCompressBitmap(imagePaths.get(position)));
			} else {
				imageLoader.get(imagePaths.get(position), imageLoader
						.getImageListener(photoView, R.mipmap.loading,
								R.mipmap.loadfail));
			}
			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			photoView.setOnViewTapListener(new OnViewTapListener() {
				
				@Override
				public void onViewTap(View view, float x, float y) {
					finish();
				}
			});
			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
