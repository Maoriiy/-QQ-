package com.JSPkeshe.mybean;
import java.sql.*;

public class Comment {
	int userAccount;
	int id;
	String message;
	int stateID;
	Time time;
	Date date;
	
	public Comment() {
		
	}
	
	public int getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(int userAccount) {
		this.userAccount = userAccount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getStateID() {
		return stateID;
	}
	public void setStateID(int stateID) {
		this.stateID = stateID;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Time getTime() {
		return time;
	}
	public void setTime(Time time){
		this.time = time;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
