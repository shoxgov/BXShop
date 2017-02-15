package com.sq.bxstore;


import com.sq.bxstore.utils.encryption.DataSecret;


public class MainClass {

	public static void main(String args[]) {
		System.out
				.println("params=" + DataSecret.encryptDES("{\"services\":\"user_getuserlist\",\"username\":\"yanlifeng\"}"));
//		.println("params=" + DataSecret.encryptDES("{\"password\":\"12345678\",\"services\":\"user_coupon\",\"username\":\"yanlifeng\"}"));
	}
}
