package com.JSPkeshe.mybean;

public class registerResult {
	String result;
	int account;
	boolean registerSuccess;
	
	public registerResult() {
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	
	public boolean isRegisterSuccess() {
		return registerSuccess;
	}
	public void setRegisterSuccess(boolean registerSuccess) {
		this.registerSuccess = registerSuccess;
	}
}
