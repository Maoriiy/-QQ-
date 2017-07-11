<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.JSPkeshe.mybean.getDataResult" %>
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
    	getDataResult result = (getDataResult)session.getAttribute("publishRes");
    	if(result.isGetDataSuccess()) {
    		out.print(result.getResult() + ",正在跳转至主页...");
    	} else {
    		out.print(result.getResult() + ",正在跳转至主页...");
    	}
    	response.setHeader("refresh", "3;URL=getData");
     %>
  </body>
</html>
