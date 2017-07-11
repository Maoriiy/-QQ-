package com.JSPkeshe.myservlet;

import com.JSPkeshe.mybean.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class publishState extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public publishState() {
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

		HttpSession session = request.getSession(true);
		String content = null;
		String image = null;
		getDataResult result = new getDataResult();
		result.setGetDataSuccess(true);
		session.setAttribute("publishRes", result);

		 //�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
        String savePath = this.getServletContext().getRealPath("/image");
        File file = new File(savePath);
        //�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
        String imagename = null;
       if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
            //����Ŀ¼
            file.mkdir();
        }
        try{
            //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
            //1������һ��DiskFileItemFactory����
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2������һ���ļ��ϴ�������
            ServletFileUpload upload = new ServletFileUpload(factory);
             //����ϴ��ļ�������������
            upload.setHeaderEncoding("UTF-8"); 
            //3���ж��ύ�����������Ƿ����ϴ���������
            if(!ServletFileUpload.isMultipartContent(request)){
                //���մ�ͳ��ʽ��ȡ����
                return;
            }
            //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
            	
                //���fileitem�з�װ������ͨ�����������
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //�����ͨ����������ݵ�������������
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                  
                    if (name.equals("content")) {
						content = value;
					}
                    
                }else{//���fileitem�з�װ�����ϴ��ļ�
                    //�õ��ϴ����ļ����ƣ�
                	//System.out.println("vvv");
                     String filename = item.getName();
                     
                     if(filename==null || filename.trim().equals("")){
                     	System.out.print("continue");
                         continue;
                     }
                     //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
                     //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
                     filename = filename.substring(filename.lastIndexOf("\\")+1);
                     imagename = filename;

                     //��ȡitem�е��ϴ��ļ���������
                     InputStream in = item.getInputStream();
                     //����һ���ļ������
                     FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                     //����һ��������
                     byte buffer[] = new byte[1024];
                     //�ж��������е������Ƿ��Ѿ�����ı�ʶ
                     int len = 0;
                     //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
                     while((len=in.read(buffer))>0){
                         //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
                         out.write(buffer, 0, len);
                     }
                     //�ر�������
                     in.close();
                     //�ر������
                     out.close();
                     //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
                     item.delete();
                     
                 }
             }
         }catch (Exception e) {
         
             e.printStackTrace();
             
         }
        
        Connection con;
		PreparedStatement sql;
		User loginUser = (User)session.getAttribute("loginUser");
		if (loginUser == null) {
			result.setGetDataSuccess(false);
			result.setResult("�û�δ��¼");
			response.sendRedirect("showPublishRes.jsp");
		}
		
		System.out.print(content+imagename);
		
        try {
			String uri="jdbc:mysql://localhost:3306/jspkeshe?useSSL=false";

	        con=DriverManager.getConnection(uri,"root","root");

	      	java.util.Date date = new java.util.Date();
	        int year = date.getYear();
	        int month = date.getMonth();
	        int day = date.getDate();
	        int hour = date.getHours();
	        int minute = date.getMinutes();
	        int second = date.getSeconds();
	        Date date2 = new Date(year, month, day);
	        Time time = new Time(hour, minute, second);
	       
	        String insertCondition="INSERT INTO dynamic_state(date,time,message,image,isImage,publisher) VALUES (?,?,?,?,?,?)";
	        sql = con.prepareStatement(insertCondition);
	        
	        sql.setDate(1, date2);
	        sql.setTime(2, time);
	        sql.setString(3, content);
	        sql.setString(4, imagename);
	        if (imagename == null) {
	        	sql.setInt(5, 0);
			} else {
				sql.setInt(5, 1);
			}
	        sql.setInt(6, loginUser.getAccount());
	        
	        
	        int m = sql.executeUpdate();
	        if(m != 0) {
	        	result.setResult("������̬�ɹ�");
	        }else{
	        	result.setGetDataSuccess(false);
	        	result.setResult("������̬ʧ��");
	       	}
	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result.setGetDataSuccess(false);
			result.setResult("�û�δ��¼");
			response.sendRedirect("showPublishRes.jsp");
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
