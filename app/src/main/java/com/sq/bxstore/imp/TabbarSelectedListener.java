package com.sq.bxstore.imp;

/**
 * 排序方式选择监听器
 * 
 * @author Cool
 * 
 */
public interface TabbarSelectedListener {
	/**
	 * 商品大略
	 */
	public abstract void GoodsGeneral();

	/**
	 * 商品详情
	 */
	public abstract void GoodsDetail();

	/**
	 * 商品评论
	 */
	public abstract void GoodsComment();
}
