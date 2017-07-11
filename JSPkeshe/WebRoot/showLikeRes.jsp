<%@page import="com.JSPkeshe.mybean.getDataResult"%>
<%@page import="com.JSPkeshe.myservlet.getData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
  </head>
  
  <body>
    <%
    	getDataResult result = (getDataResult)session.getAttribute("likeRes");
    	out.print(result.getResult() +",正在跳转至主页");
    	response.setHeader("refresh", "0;URL=getData");
     %>
  </body>
</html>
