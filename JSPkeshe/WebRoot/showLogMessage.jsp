<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"  %>
<%@ page import="com.JSPkeshe.mybean.LoginResult" %>
<jsp:useBean id="loginResult" type="com.JSPkeshe.mybean.LoginResult" scope="session"></jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showLogMessage.jsp' starting page</title>
    

  </head>
  
  <body>
  	<%
  		LoginResult loginRes = (LoginResult)session.getAttribute("loginResult");
  		if(loginRes.isLoginSuccess()) {
  			out.print("登陆成功,正在跳转...");
  			response.setHeader("refresh", "2;URL=getData");
  			
  		} else {
  			out.print("登录失败,请重新登录。");
  			response.setHeader("refresh", "1;URL=login.jsp");
  		}
  	 %>
  	
    
  </body>
</html>
