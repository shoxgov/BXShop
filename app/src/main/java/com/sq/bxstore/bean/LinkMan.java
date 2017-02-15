package com.sq.bxstore.bean;
/**
 * 联系人
 * @author Administrator
 *
 */
public class LinkMan {
	private String name;
	private String phone;
	private String address;
	
	public LinkMan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 实例化联系人
	 * @param name 联系人
	 * @param phone 电话
	 * @param address 地址
	 */
	public LinkMan(String name, String phone, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
