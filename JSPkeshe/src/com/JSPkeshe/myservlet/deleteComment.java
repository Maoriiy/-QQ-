package com.JSPkeshe.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class deleteComment extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public deleteComment() {
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
		
		
		int commentID = Integer.parseInt(request.getParameter("commentID"));
		try{
            String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

            con=DriverManager.getConnection(uri,"root","root");
            
            String conditionString = "delete from reply where commentID=?";
            sql=con.prepareStatement(conditionString);
            sql.setInt(1, commentID);
            int rs = sql.executeUpdate();
            
         
             con.close();

      }catch(SQLException e){
    
      	e.printStackTrace();
      }
		try{
            String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

            con=DriverManager.getConnection(uri,"root","root");
            
            String conditionString = "delete from comment where id=?";
            sql=con.prepareStatement(conditionString);
            sql.setInt(1, commentID);
            int rs = sql.executeUpdate();
           
           
             con.close();

      }catch(SQLException e){
    
      	e.printStackTrace();
      }
      response.sendRedirect("getData");
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
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
