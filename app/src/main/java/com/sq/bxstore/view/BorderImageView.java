package com.sq.bxstore.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BorderImageView extends ImageView
{
    private int borderwidth;
    private Drawable mCoverDrawable = null;
    private int mInnerRadius = 0;
    private Drawable mBorderDrawable = null;
    private Paint paint;
    private PorterDuffXfermode duffMode;
    private Rect mOutterRect;
    private Rect mInnerRect;
    private RectF mInnerRectF;

    public Drawable getBorderDrawable()
    {
        return mBorderDrawable;
    }

    public void setBorderDrawable(Drawable mBorderDrawable)
    {
        this.mBorderDrawable = mBorderDrawable;
    }

    public int getRadius()
    {
        return mInnerRadius;
    }

    public void setInnerRadius(int radius)
    {
        this.mInnerRadius = radius;
    }

    public void setBorderById(int id)
    {
        mBorderDrawable = getResources().getDrawable(id);
    }

    public BorderImageView(Context context)
    {
        super(context);
    }

    public BorderImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public BorderImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paint = new Paint();
        duffMode = new PorterDuffXfermode(Mode.SRC_IN);
        mOutterRect = new Rect();
        mInnerRect = new Rect();
        mInnerRectF = new RectF();
    }

    // 设置边框宽度
    public void setBorderWidth(int width)
    {

        borderwidth = width;
    }

    /* 此函数在draw函数之后执行，draw中执行background的绘制工作，然后才调用此函数绘制前景， 如果在此函数中getBackgroundDrawable并清空会产生黑色块，并且闪烁，无法解决，
     * 所以在setBackgroundDrawable中拦截对背景的设置。 by doom119 */
    @Override protected void onDraw(Canvas canvas)
    {
        if((mBorderDrawable == null) && (mCoverDrawable == null))
            return;
        
        mOutterRect.set(0, 0, getWidth(), getHeight());
        mInnerRect.set(mOutterRect);
        mInnerRect.bottom -= borderwidth;
        mInnerRect.right -= borderwidth;
        mInnerRect.top += borderwidth;
        mInnerRect.left += borderwidth;
        
        mInnerRectF.set(mOutterRect);
        
        drawBorder(canvas);

        //如果设置了封面圆角，则进行圆角的绘制
        if (mInnerRadius != 0)
        {
            //新建图层绘制一个圆角矩形，注意新图层包含上一图层的图形
            canvas.saveLayer(mInnerRectF, paint, Canvas.ALL_SAVE_FLAG);
            canvas.drawRoundRect(mInnerRectF, mInnerRadius, mInnerRadius, paint);
            paint.setXfermode(duffMode);
            //再建图层，使用混合模式在圆角矩形内绘制封面
            canvas.saveLayer(mInnerRectF, paint, Canvas.ALL_SAVE_FLAG);
            drawCover(canvas);
            canvas.restore();
            paint.setXfermode(null);
            canvas.restore();
        }
        else
        {
            drawCover(canvas);
        }
    }
    
    private void drawCover(Canvas canvas)
    {
        if(mCoverDrawable != null)
        {
            if (mCoverDrawable instanceof BitmapDrawable 
                    && (((BitmapDrawable) mCoverDrawable).getBitmap() != null)
                    && ((BitmapDrawable) mCoverDrawable).getBitmap().isRecycled())
                return;
            mCoverDrawable.setBounds(mInnerRect);
            mCoverDrawable.draw(canvas);
        }
    }

    private void drawBorder(Canvas canvas)
    {
        if(mBorderDrawable != null)
        {
            if (mBorderDrawable instanceof BitmapDrawable
                    && ((BitmapDrawable) mBorderDrawable).getBitmap().isRecycled())
                return;
            mBorderDrawable.setBounds(mOutterRect);
            mBorderDrawable.draw(canvas);
        }
    }

	@Override public void setImageResource(int resId)
    {
	    setImageDrawable(getResources().getDrawable(resId));
    }

	/*
	 * 代替setBackgroundDrawable，解决列表滑动时图片闪烁的问题
	 * @see android.widget.ImageView#setImageDrawable(android.graphics.drawable.Drawable)
	 */
    @Override public void setImageDrawable(Drawable drawable)
    {
        mCoverDrawable = drawable;
        super.setImageDrawable(drawable);
    }

    /**
     * @deprecated
     * 目前只针对ImageUtil进行设计，ImageUtil通过此函数设置ImageView的背景， 所以在此拦截背景设置，复制其Bitmap后清空，也就是不让绘制背景，
     * 并在onDraw中把此Bitmap覆盖在所设置的背景边框图片的上。by doom119 
     * 设置背景会导致列表滑动时出现闪烁的现象，请用setImageDrawable代替, by doom119*/
    @Override public void setBackgroundDrawable(Drawable d)
    {
//        mCoverBitmap = ((BitmapDrawable) d).getBitmap();
        mCoverDrawable = d;
        d = null; //在此拦截背景的设置，不让在draw中绘制背景
        super.setBackgroundDrawable(d);
    }

    /**
     * @deprecated
     */
	@Override
	public void setBackgroundResource(int resid) {
		setBackgroundDrawable(getResources().getDrawable(resid));
	}

}
