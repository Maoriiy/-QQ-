package com.JSPkeshe.mybean;

public class getDataResult {
	String result;
	boolean getDataSuccess;

	public getDataResult() {
		getDataSuccess = false;
		result="";
	};
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public boolean isGetDataSuccess() {
		return getDataSuccess;
	}
	public void setGetDataSuccess(boolean getDataSuccess) {
		this.getDataSuccess = getDataSuccess;
	}
}
