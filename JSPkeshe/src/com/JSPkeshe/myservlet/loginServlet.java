package com.JSPkeshe.myservlet;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import com.JSPkeshe.mybean.LoginResult;
import com.JSPkeshe.mybean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public loginServlet() {
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

		response.setContentType("text/html");
		LoginResult loginRes =null;
		HttpSession session = request.getSession(true);
		try {
			loginRes = (LoginResult)session.getAttribute("loginResult");
			if (loginRes == null) {
				loginRes = new LoginResult();
				session.setAttribute("loginResult", loginRes);
			}
		} catch (Exception e) {
			loginRes = new LoginResult();
			session.setAttribute("loginResult", loginRes);
		}
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println(account + password);
		
		
		loginRes.setAccount(account);
		
		
        Connection con;
        PreparedStatement sql;
        
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch(Exception e){
                
            }
            try{
                  String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

                  con=DriverManager.getConnection(uri,"root","root");
                  
                  String conditionString = "select * from users where account =? and password =?";
                  sql=con.prepareStatement(conditionString);
                  sql.setString(1,account);
                  sql.setString(2, password);
                  ResultSet rs = sql.executeQuery();
                  boolean m = rs.next();
                  
                  if (m == true) {
                	System.out.println("登陆成功");
					loginRes.setResult("登陆成功");
					loginRes.setLoginSuccess(true);
					loginRes.setAccount(account);
					User loginUser = new User();
					loginUser.setUsername(rs.getString(1));
					loginUser.setAccount(rs.getInt(2));
					loginUser.setPassword(rs.getString(3));
					loginUser.setIcon(rs.getString(4));
					session.setAttribute("loginUser",loginUser);
					
				} else {
					loginRes.setLoginSuccess(false);
					System.out.println("登陆失败");
					loginRes.setResult("你输入的用户名不存在,或密码错误");
					loginRes.setAccount(null);
				}
                 
                   con.close();

            }catch(SQLException e){
            	loginRes.setLoginSuccess(false);
            	e.printStackTrace();
            }
            response.sendRedirect("showLogMessage.jsp");
             
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
