package com.JSPkeshe.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.JSPkeshe.mybean.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class getData extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getData() {
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

		Connection con;
        PreparedStatement sql;
        ArrayList<Dynamic_state> stateList = new ArrayList<Dynamic_state>();
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        ArrayList<Reply> replyList = new ArrayList<Reply>();
        HttpSession session = request.getSession(true);
        getDataResult result = new getDataResult();
        session.setAttribute("getDataRes", result);
        result.setGetDataSuccess(true);
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch(Exception e){
                
            }
            try{
                  String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

                  con=DriverManager.getConnection(uri,"root","root");
                  
                  String conditionString = "select * from dynamic_state ";
                  sql=con.prepareStatement(conditionString);
                  
                  ResultSet rs = sql.executeQuery();
                  while(rs.next()) {
                	  Dynamic_state state = new Dynamic_state();
                	  state.setId(rs.getInt(1));
                	  state.setDate(rs.getDate(2));
                	  state.setTime(rs.getTime(3));
                	  state.setMessage(rs.getString(4));
                	  state.setImage(rs.getString(5));
                	  state.setIsimage(rs.getBoolean(6));
                	  state.setPublisher(rs.getInt(7));
                	  stateList.add(state);
                  }
                  
                   con.close();

            }catch(SQLException e){
            	e.printStackTrace();
            	result.setResult("获取动态数据时错误");
            	response.sendRedirect("showData.jsp");
            	return;
            }
            
            try{
                String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

                con=DriverManager.getConnection(uri,"root","root");
                
                String conditionString = "select * from comment ";
                sql=con.prepareStatement(conditionString);
                
                ResultSet rs = sql.executeQuery();
                while(rs.next()) {
              	  Comment cmt = new Comment();
              	  cmt.setUserAccount(rs.getInt(1));
              	  cmt.setId(rs.getInt(2));
              	  cmt.setMessage(rs.getString(3));
              	  cmt.setStateID(rs.getInt(4));
              	  cmt.setTime(rs.getTime(5));
              	  cmt.setDate(rs.getDate(6));
              	  commentList.add(cmt);
                }
                 con.close();

          }catch(SQLException e){
          	e.printStackTrace();
          	result.setResult("获取评论数据时错误");
          	response.sendRedirect("showData.jsp");
          	return;
          }
            
            try{
                String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

                con=DriverManager.getConnection(uri,"root","root");
                
                String conditionString = "select * from reply ";
                sql=con.prepareStatement(conditionString);
                
                ResultSet rs = sql.executeQuery();
                while(rs.next()) {
              	  Reply reply = new Reply();
              	  reply.setUserAccount(rs.getInt(1));
              	  reply.setCommentID(rs.getInt(2));
              	  reply.setMessage(rs.getString(3));
              	  reply.setId(rs.getInt(4));
              	  reply.setDate(rs.getDate(5));
              	  reply.setTime(rs.getTime(6));
              	  replyList.add(reply);
                }
                 con.close();

          }catch(SQLException e){
          	e.printStackTrace();
          	result.setGetDataSuccess(false);
          	result.setResult("获取回复数据时错误");
          	response.sendRedirect("showData.jsp");
          	return;
          }
            ArrayList<User> userList = new ArrayList<User>();
            
            try{
                String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

                con=DriverManager.getConnection(uri,"root","root");
                
                String conditionString = "select * from users ";
                sql=con.prepareStatement(conditionString);
                
                ResultSet rs = sql.executeQuery();
                while(rs.next()) {
              	  User user = new User();
              	  user.setUsername(rs.getString(1));
              	  user.setAccount(rs.getInt(2));
              	  user.setIcon(rs.getString(4));
              	  userList.add(user);
              	  
                }
                 con.close();

          }catch(SQLException e){
          	e.printStackTrace();
          	result.setResult("获取用户数据时错误");
          	response.sendRedirect("showData.jsp");
          	return;
          }       
            
            ArrayList<Like> likeList = new ArrayList<Like>();
            
            try{
                String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

                con=DriverManager.getConnection(uri,"root","root");
                
                String conditionString = "select * from star ";
                sql=con.prepareStatement(conditionString);
                
                ResultSet rs = sql.executeQuery();
                while(rs.next()) {
              	 Like like = new Like();
              	 like.setAccount(rs.getInt(1));
              	 like.setStateID(rs.getInt(2));
              	 like.setId(rs.getInt(3));
              	  likeList.add(like);
                }
                 con.close();

          }catch(SQLException e){
          	e.printStackTrace();
          	result.setGetDataSuccess(false);
          	result.setResult("获取点赞数据时错误");
          	response.sendRedirect("showData.jsp");
          	return;
          }
           
            
           session.setAttribute("likeList", likeList);
           session.setAttribute("stateList", stateList);
           session.setAttribute("commentList", commentList);
           session.setAttribute("replyList", replyList);
           
           session.setAttribute("userList", userList);
           response.sendRedirect("Main.jsp");
           return;
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
