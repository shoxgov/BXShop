package com.sq.bxstore.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.sq.bxstore.imp.SortSelectedScrolledListener;

public class ScrollLayout extends LinearLayout {

	private Scroller mScroller;
	private SortSelectedScrolledListener listener = null;

	public void setSortSelectedScrolledListener(
			SortSelectedScrolledListener listener) {
		this.listener = listener;
	}

	public ScrollLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);
	}

	public void startSrcoll(int startX, int startY, int dx, int dy) {
		mScroller.startScroll(startX, startY, dx, dy, 500);
		invalidate();
	}

	public boolean isSrcoll() {
		return !mScroller.isFinished();
	}

	@Override
	public void computeScroll() {
		if (listener != null && mScroller.isFinished()) {
			listener.isFinished();
		}
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}

}
