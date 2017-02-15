package com.sq.bxstore.bxBean;

import android.os.Parcel;
import android.os.Parcelable;

public class GoodsInfoParcelable implements Parcelable {

	private int count;
	private int goodsid;
	private float price;
	private String goodsimg;
	private String goodsname;

	/**
	int 	count; 数量
	int 	goodsid; 商品id
	float 	price; 价格
	String 	goodsimg; 商品图片
	String 	goodsname;	商品名称
	 */
	public GoodsInfoParcelable(int count, int goodsid, float price, String goodsimg, String goodsname) {
		this.count = count;
		this.goodsid = goodsid;
		this.price = price;
		this.goodsimg = goodsimg;
		this.goodsname = goodsname;
	}


	public int getCount() {
		return count;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public float getPrice() {
		return price;
	}

	public String getGoodsimg() {
		return goodsimg;
	}

	public String getGoodsname() {
		return goodsname;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int arg1) {
		parcel.writeInt(count);
		parcel.writeInt(goodsid);
		parcel.writeFloat(price);
		parcel.writeString(goodsimg);
		parcel.writeString(goodsname);
	}

	public static final Creator CREATOR = new Creator() {
		public GoodsInfoParcelable createFromParcel(Parcel source) {
			GoodsInfoParcelable temp = new GoodsInfoParcelable(0, 0, 0, "", "");// 初始化

			temp.count = source.readInt();
			temp.goodsid = source.readInt();
			temp.price = source.readFloat();
			temp.goodsimg = source.readString();
			temp.goodsname = source.readString();

			return temp;
		}

		public GoodsInfoParcelable[] newArray(int size) {
			return new GoodsInfoParcelable[size];
		}
	};
}
