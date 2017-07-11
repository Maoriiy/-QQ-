package com.JSPkeshe.mybean;
import java.sql.*;

public class Dynamic_state {
	int id;
	Date date;
	Time time;
	String message;
	String image;
	boolean isImage;
	int publisher;

	
	public Dynamic_state() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public boolean isImage() {
		return isImage;
	}
	
	public void setIsimage(boolean isImage){
		this.isImage = isImage;
	}
	
	public int getPublisher() {
		return publisher;
	}
	
	public void setPublisher(int publisher){
		this.publisher = publisher;
	}
	
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
}
