package com.sq.bxstore.net.response;

import java.util.List;

import com.sq.bxstore.net.BaseResponse;

/**
Output:	In JSON format
Success info 	status_code:100
Example	{
"status_code":100,
"wallets":
[
{"name":"E-Cash Wallet","balance":"3780.00"},
{"name":"Casino Point","balance":"1620.00"}
],
"message":"success"
}
 */
public class WalletInfoResponse extends BaseResponse {

	private List<Wallet> wallets;
	private int customer_id;

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public class Wallet {
		private String name;
		private String balance;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBalance() {
			return balance;
		}

		public void setBalance(String balance) {
			this.balance = balance;
		}

	}
}
