package com.JSPkeshe.myservlet;

import java.io.*;

import com.JSPkeshe.mybean.User;
import com.JSPkeshe.mybean.registerResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class register_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public register_servlet() {
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

		doPost(request, response);
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
		System.out.println(getServletContext().getRealPath("/"));
		registerResult result = new registerResult();
		HttpSession session = request.getSession(true);
		session.setAttribute("registerRes", result);
		String icon = null;
		String username = null;
		String password = null;
		
		 //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/icon");
        session.setAttribute("iconPath", savePath);
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
       if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                  
                    if (name.equals("username")) {
						username = value;
					} else if (name.equals("password")) {
						password = value;
					}
                    
                }else{//如果fileitem中封装的是上传文件
                   //得到上传的文件名称，
                    String filename = item.getName();
                    
                    if(filename==null || filename.trim().equals("")){
                    	System.out.print("continue");
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    icon = filename;
                    System.out.println("获得icon");
                    System.out.print("icon:"+icon);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    
                }
            }
        }catch (Exception e) {
            result.setResult("文件上传失败");
            result.setRegisterSuccess(false);
            response.sendRedirect("showRegisterRes.jsp");
            e.printStackTrace();
            
        }
        Connection con;
		PreparedStatement sql;
		
		System.out.println("username="+ username +"\npassword="+password +"\nicon="+icon);
		
		try {
			String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

	        con=DriverManager.getConnection(uri,"root","root");
	        String insertCondition="INSERT INTO users(username,password,icon) VALUES (?,?,?)";
	        sql = con.prepareStatement(insertCondition);
	        
	        sql.setString(1, username);
	        sql.setString(2, password);
	        sql.setString(3, icon);
	        int m = sql.executeUpdate();
	        if(m != 0) {
	        	result.setResult("注册成功");
	        	result.setRegisterSuccess(true);
	        	System.out.print("注册成功");
	        }else{
	        	result.setResult("注册失败");
	        	result.setRegisterSuccess(false);
	        	System.out.print("注册失败");
	       	}
	        con.close();
		} catch (SQLException e) {
			result.setResult("注册失败");
			result.setRegisterSuccess(false);
			System.out.print("注册失败");
			e.printStackTrace();
		} 
		if (result.isRegisterSuccess() == true) {
			try{
	            String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

	            con=DriverManager.getConnection(uri,"root","root");
	            
	            String conditionString = "select * from users where username =? and password =?";
	            sql=con.prepareStatement(conditionString);
	            sql.setString(1,username);
	            sql.setString(2, password);
	            ResultSet rs = sql.executeQuery();
	            boolean m = rs.next();
	            
	            if (m == true) {
	            	System.out.print("查询成功,账号为"+rs.getInt(2));
					result.setAccount(rs.getInt(2));
				} 
	           
	             con.close();

	      }catch(SQLException e){
	     
	      	e.printStackTrace();
	      }
		}
		response.sendRedirect("showRegisterRes.jsp");
		// request.getRequestDispatcher("../showRegisterRes.jsp").forward(request,response);
		//request.getRequestDispatcher("../showRegisterRes.jsp");

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		 super.init();

	}

}
