package com.JSPkeshe.mybean;

import java.sql.Date;
import java.sql.Time;

public class Reply {
	int userAccount;
	int commentID;
	String message;
	int id;
	Time time;
	Date date;
	
	public Reply() {
		
	}
	
	public int getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(int userAccount) {
		this.userAccount = userAccount;
	}
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getId() {
		return  id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
}
