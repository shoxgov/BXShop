package com.sq.bxstore.bean;

/**
 "data": [
        {
            "address": "长沙市开福区四方坪",
            "id": 1,
            "isuse": 0,
            "name": "陈成",
            "phonenumb": "18101954697",
            "post": "20001",
            "telnumb": "0213331157",
            "uerid": 1,
            "username": "森林"
        },
        {
            "address": "广州市白云区",
            "id": 2,
            "isuse": 0,
            "name": "陈成",
            "phonenumb": "18074548620",
            "post": "21000",
            "telnumb": "0213331157",
            "uerid": 1,
            "username": "森林"
        }
    ],
 */
public class AddressInfo {
	private String address;
	private String name;
	private String phonenumb;
	private String post;
	private String telnumb;
	private int id;
	private int isuse;// 1默认 0非默认
	private int uerid;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumb() {
		return phonenumb;
	}

	public void setPhonenumb(String phonenumb) {
		this.phonenumb = phonenumb;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getTelnumb() {
		return telnumb;
	}

	public void setTelnumb(String telnumb) {
		this.telnumb = telnumb;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsuse() {
		return isuse;
	}

	public void setIsuse(int isuse) {
		this.isuse = isuse;
	}

	public int getUerid() {
		return uerid;
	}

	public void setUerid(int uerid) {
		this.uerid = uerid;
	}

}
