package com.JSPkeshe.mybean;
import java.sql.*;

import com.sun.org.apache.regexp.internal.recompile;

public class User {
	String username;
	int account;
	String password;
	String icon;
	
	public User() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch(ClassNotFoundException e) {
			
		}
	}
	
	public void setUsername(String name) {
		username = name;
	}
	
	public String getUsername() {
		return username;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	
	public int getAccount() {
		return account;
	}
	
	public void setPassword(String pwd) {
		password = pwd;
	}
	
	public String getPassword() {
		return password;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getInsertData() {
		String str="";
		Connection con;
		 PreparedStatement sql;
		try {
			String uri="jdbc:mysql://localhost:3306/jspkeshe";
	        String user="root";
	        String password="root";
	        con=DriverManager.getConnection(uri,user,password);
	        String insertCondition="INSERT INTO users(username,account,password,icon) VALUES (?,?,?,?)";
	        sql = con.prepareStatement(insertCondition);
	       
	        if(account > 0) {
	        	sql.setString(1, username);
	        	sql.setInt(2, account);
	        	sql.setString(3, password);
	        	sql.setString(4, icon);
	        	int m = sql.executeUpdate();
	        	if(m != 0) {
	        		str = "对表中添加0" + m + "条记录成功";
	        		
	        	}else{
	        		str = "添加记录失败";
	        	}
	        } else{
	        	str = "用户名不能为空";
	        }
	        con.close();
		} catch (SQLException e) {
			str = "你还没有提供添加的数据或" + e;
		} 
		return str;

	}
	
}
