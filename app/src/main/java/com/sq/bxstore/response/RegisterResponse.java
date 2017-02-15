package com.sq.bxstore.response;

import com.sq.bxstore.net.BaseResponse;

/**
 * Output: In JSON format 
 * Success info status_code:100 
 * Example {"status_code":100,"customer_id":6177,"message":"success"}
 */
public class RegisterResponse extends BaseResponse {

	private String message;
	private int customer_id;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

}
