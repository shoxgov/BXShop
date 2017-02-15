/**  
 * @Title: MyGridView.java
 * @date: 2015-11-23 上午9:54:48
 * @Copyright: (c) 2015, unibroad.com Inc. All rights reserved.
 */
package com.sq.bxstore.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @Class: MyGridView
 * @Package: com.unibroad.carphone.widget
 * @Description: TODO(描述类作用)
 * @author: wsy@unibroad.com
 * @version: V1.0
 */
public class MyGridView extends GridView {

	public boolean hasScrollBar = true;// 要为true，否则会高度计算无效

	/**
	 * @param context
	 */
	public MyGridView(Context context) {
		this(context, null);
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
		int expandSpec = heightMeasureSpec;
		if (hasScrollBar) {
			expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
					MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);// 注意这里,这里的意思是直接测量出GridView的高度
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

}