package com.sq.bxstore.imp;

/**
 * 购物车商品状态改变监听器
 * @author Cool
 *
 */
public interface CartProductItemChangedListener {
	/**
	 * 商品数量改变
	 */
	public abstract void itemNumChanged(int position, int num);
	/**
	 * 更新总价
	 */
	public abstract void updateSum();
	/**
	 * 商品选中状态
	 */
	public abstract void itemCheckChanged(int position, boolean isCheck);
}
