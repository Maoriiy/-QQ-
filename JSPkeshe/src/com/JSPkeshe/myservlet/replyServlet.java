package com.JSPkeshe.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.JSPkeshe.mybean.LoginResult;
import com.JSPkeshe.mybean.User;
import com.JSPkeshe.mybean.getDataResult;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class replyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public replyServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		getDataResult result = new getDataResult();
		session.setAttribute("replyRes", result);
		User user = (User)session.getAttribute("loginUser");
		
        Connection con;
        PreparedStatement sql;
        
        java.util.Date date = new java.util.Date();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();
        int hour = date.getHours();
        int minute = date.getMinutes();
        int second = date.getSeconds();
        Date date2 = new Date(year, month, day);
        Time time = new Time(hour, minute, second);
        String content = request.getParameter("content");
        
        int commentId = Integer.parseInt(request.getParameter("commentID"));

        System.out.print(content+commentId);
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch(Exception e){
                
            }
            try{
                  String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

                  con=DriverManager.getConnection(uri,"root","root");
                  
                  String conditionString = "insert into reply(userAccount,commentID,message,date,time) value(?,?,?,?,?)";
                  sql=con.prepareStatement(conditionString);
                 sql.setInt(1, user.getAccount());
                 sql.setInt(2, commentId);
                 sql.setString(3, content);
                 sql.setDate(4, date2);
                 sql.setTime(5, time);
                 int m = sql.executeUpdate();
                  
                  if (m >0) {
                	result.setGetDataSuccess(true);
                	result.setResult("回复成功");
					
				} else {
					result.setGetDataSuccess(false);
                	result.setResult("回复失败1");
				}
                 
                   con.close();

            }catch(SQLException e){
            	result.setGetDataSuccess(false);
            	result.setResult("回复失败2");
            	e.printStackTrace();
            }
            response.sendRedirect("getData");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
