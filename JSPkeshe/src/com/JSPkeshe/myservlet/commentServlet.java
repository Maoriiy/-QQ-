package com.JSPkeshe.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.JSPkeshe.mybean.User;
import com.JSPkeshe.mybean.getDataResult;

public class commentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public commentServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
			Connection con;
			PreparedStatement sql;
			HttpSession session = request.getSession(true);
			getDataResult result = new getDataResult();
			session.setAttribute("commentRes", result);
			User user = (User)session.getAttribute("loginUser");
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
	        int stateID = Integer.parseInt(request.getParameter("stateID"));
	        

	        try {
				String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

		        con=DriverManager.getConnection(uri,"root","root");
		       
		        String insertCondition="INSERT INTO comment(userAccount,message,stateID,time,date) VALUES (?,?,?,?,?)";
		        sql = con.prepareStatement(insertCondition);
		        sql.setInt(1, user.getAccount());
		        sql.setString(2, content);
		        sql.setInt(3, stateID);
		        sql.setTime(4, time);
		        sql.setDate(5, date2);

		        int m = sql.executeUpdate();
		        if(m != 0) {
		        	result.setGetDataSuccess(true);
		        	result.setResult("评论成功");
		        }else{
		        	result.setGetDataSuccess(false);
		        	result.setResult("评论失败");
		       	}
		        con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result.setGetDataSuccess(false);
				result.setResult("评论失败");
				response.sendRedirect("showCommenthRes.jsp");
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
