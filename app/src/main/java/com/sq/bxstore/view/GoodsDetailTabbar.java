package com.sq.bxstore.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sq.bxstore.R;
import com.sq.bxstore.imp.SortSelectedScrolledListener;
import com.sq.bxstore.imp.TabbarSelectedListener;

/**
 * 商品排序选择
 */
public class GoodsDetailTabbar extends RelativeLayout implements
		SortSelectedScrolledListener {
	private ImageButton img_detail_back;
	private TextView tv_goods_general;
	private TextView tv_goods_info;
	private TextView tv_goods_comment;
	private TextView[] tvs = new TextView[3];

	private RelativeLayout rl_goods_general;
	private RelativeLayout rl_goods_info;
	private RelativeLayout rl_goods_comment;

	private ImageView sortSelected;
	private ScrollLayout scrollLayout;

	private static final int TV_SELECTED_COLOR = R.color.grey_dark;
	private static final int TV_NONE_COLOR = R.color.grey;

	private int sortPosition = 0;

	private int space = 0;

	private TabbarSelectedListener tabbarSelectedListener = null;

	public void setTabbartSelectedListener(
			TabbarSelectedListener tabbarSelectedListener) {
		this.tabbarSelectedListener = tabbarSelectedListener;
	}

	public GoodsDetailTabbar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GoodsDetailTabbar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GoodsDetailTabbar(Context context) {
		super(context);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		space = ((WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 3;
		initView();
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.rl_goodsdetail_general:
				if (sortPosition != 0) {
					setDisable();
					int[] location = new int[2];
					sortSelected.getLocationOnScreen(location);
					scrollLayout.startSrcoll(-location[0], 0, (sortPosition)
							* space, 0);
					tvs[0].setTextColor(getContext().getResources().getColor(
							TV_SELECTED_COLOR));
					tvs[sortPosition].setTextColor(getContext().getResources()
							.getColor(TV_NONE_COLOR));
					sortPosition = 0;
					tabbarSelectedListener.GoodsGeneral();
				}
				break;

			case R.id.rl_goodsdetail_info:
				if (sortPosition != 1) {
					setDisable();
					int[] location = new int[2];
					sortSelected.getLocationOnScreen(location);
					scrollLayout.startSrcoll(-location[0], 0,
							(sortPosition - 1) * space, 0);
					tvs[1].setTextColor(getContext().getResources().getColor(
							TV_SELECTED_COLOR));
					tvs[sortPosition].setTextColor(getContext().getResources()
							.getColor(TV_NONE_COLOR));
					sortPosition = 1;
					tabbarSelectedListener.GoodsDetail();
				}
				break;

			case R.id.rl_goodsdetail_comment:
				if (sortPosition != 2) {
					setDisable();
					int[] location = new int[2];
					sortSelected.getLocationOnScreen(location);
					scrollLayout.startSrcoll(-location[0], 0,
							(sortPosition - 2) * space, 0);
					tvs[2].setTextColor(getContext().getResources().getColor(
							TV_SELECTED_COLOR));
					tvs[sortPosition].setTextColor(getContext().getResources()
							.getColor(TV_NONE_COLOR));
					sortPosition = 2;
					tabbarSelectedListener.GoodsComment();
				}
				break;

			default:
				break;
			}
		}
	};

	private void initView() {
		img_detail_back = (ImageButton) findViewById(R.id.detail_left_img);
		tv_goods_general = (TextView) findViewById(R.id.tv_goodsdetail_general);
		tv_goods_info = (TextView) findViewById(R.id.tv_goodsdetail_info);
		tv_goods_comment = (TextView) findViewById(R.id.tv_goodsdetail_comment);

		tvs[0] = tv_goods_general;
		tvs[1] = tv_goods_info;
		tvs[2] = tv_goods_comment;
		tvs[sortPosition].setTextColor(getContext().getResources().getColor(
				TV_SELECTED_COLOR));

		rl_goods_general = (RelativeLayout) findViewById(R.id.rl_goodsdetail_general);
		rl_goods_info = (RelativeLayout) findViewById(R.id.rl_goodsdetail_info);
		rl_goods_comment = (RelativeLayout) findViewById(R.id.rl_goodsdetail_comment);

		sortSelected = (ImageView) findViewById(R.id.iv_sort_selected);
		scrollLayout = (ScrollLayout) findViewById(R.id.sort_selected_layout);

		scrollLayout.setSortSelectedScrolledListener(this);
		sortSelected.setLayoutParams(new LinearLayout.LayoutParams(space, 8));

		rl_goods_general.setOnClickListener(clickListener);
		rl_goods_info.setOnClickListener(clickListener);
		rl_goods_comment.setOnClickListener(clickListener);

		if (sortPosition != 0) {
			scrollLayout.scrollTo(-(space * sortPosition), 0);
			postInvalidate();
		}
	}

	private void setDisable() {
//		img_detail_back.setClickable(false);
//		rl_goods_general.setClickable(false);
//		rl_goods_info.setClickable(false);
//		rl_goods_comment.setClickable(false);
	}

	public void onClick(int id) {
		switch (id) {
		case R.id.rl_goodsdetail_general:
			rl_goods_general.callOnClick();
			break;
		case R.id.rl_goodsdetail_info:
			rl_goods_info.callOnClick();
			break;
		case R.id.rl_goodsdetail_comment:
			rl_goods_comment.callOnClick();
			break;
		}
	}

	public void setEnable() {
		img_detail_back.setClickable(true);
		rl_goods_general.setClickable(true);
		rl_goods_info.setClickable(true);
		rl_goods_comment.setClickable(true);
	}

	@Override
	public void isFinished() {
		setEnable();
	}
}
