<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.JSPkeshe.mybean.registerResult" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%registerResult registerRes = (registerResult)session.getAttribute("registerRes"); %>
  <head>
	<title><%= registerRes.getResult() %></title>
  </head>
  
  <body>
    <% 
    	
    	if (registerRes.isRegisterSuccess() == true) {
    		out.print(registerRes.getResult() + "  你的账号是 " + registerRes.getAccount());
    		out.print("\n正在跳转到登录页面...");
    		response.setHeader("refresh", "3;URL=login.jsp");
    	} else {
    	 	out.print(registerRes.getResult()+",正在返回注册页面...");
    	 	response.setHeader("refresh", "3;URL=register.jsp");
    	}
     %>
  </body>
</html>
