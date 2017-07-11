package com.JSPkeshe.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.JSPkeshe.mybean.Like;
import com.JSPkeshe.mybean.LoginResult;
import com.JSPkeshe.mybean.User;
import com.JSPkeshe.mybean.getDataResult;

public class likeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public likeServlet() {
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
		System.out.print("123");
		HttpSession session = request.getSession(true);
		
		User loginUser = (User)session.getAttribute("loginUser");
		getDataResult result = new getDataResult();
		session.setAttribute("likeRes", result);
		if (loginUser == null) {
			result.setResult("用户未登录,点赞失败");
			result.setGetDataSuccess(false);
			request.getRequestDispatcher("getData").forward(request,response);
			return;
		}
		int stateID = Integer.parseInt(request.getParameter("stateID")) ;
		int account = loginUser.getAccount();
		
		ArrayList likeList = (ArrayList)session.getAttribute("likeList");
		
		
        Connection con;
		PreparedStatement sql;

		try {
			String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

	        con=DriverManager.getConnection(uri,"root","root");
	        boolean flag = false;
	        String insertCondition;
	        for(int i=0; i< likeList.size();i++) {
				Like like = (Like)likeList.get(i);
				if (like.getAccount() == account && like.getStateID() == stateID) {
					flag = true;
					break;
				}
			}
	        if (flag) {
	        	System.out.print("已点过赞");
	        	insertCondition ="delete from star where account=? and stateID=?";
			} else{
				System.out.print("还未点过");
				 insertCondition ="insert into star(account,stateID) values(?,?)";
			}

	        sql = con.prepareStatement(insertCondition);
	        
	        sql.setInt(1, account);
	        sql.setInt(2, stateID);
	        
	        
	        int m = sql.executeUpdate();
	        if(m != 0) {
	        	result.setResult("点赞成功");
	        }else{
	        	result.setGetDataSuccess(false);
	        	result.setResult("点赞失败");
	       	}
	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result.setGetDataSuccess(false);
			result.setResult("点赞失败");
			response.sendRedirect("showLikeRes.jsp");
			return;
		} 
		System.out.print("result:"+result.getResult() +"bool:"+result.isGetDataSuccess());
		request.getRequestDispatcher("getData").forward(request,response);
		
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
		
		doGet(request, response);
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
