<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.JSPkeshe.mybean.LoginResult" %>
<%@ page import="com.JSPkeshe.mybean.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="loginResult" type="com.JSPkeshe.mybean.LoginResult" scope="session"/>
<jsp:useBean id="loginUser" type="com.JSPkeshe.mybean.User" scope="session"></jsp:useBean>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	

  </head>
  
  <body>
   <%
   		out.print("用户名:"+loginUser.getUsername());
   		out.print("账号:"+loginUser.getAccount());
   		out.print("登录结果:"+loginResult.getResult());
   		out.print("icon:"+loginUser.getIcon());
    %>
    <img src="icon/<%= loginUser.getIcon()%>">
  </body>
</html>
