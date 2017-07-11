<%@ page contentType="text/html; charset=utf-8"  %>
<%@ page import="java.sql.*" %>
<%@ page import="com.JSPkeshe.mybean.*" %>
<jsp:useBean id="insert" class="com.JSPkeshe.mybean.User" scope="request"></jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>


  </head>
  
  <body>
    <form action="" method=post >
	    <table border=1>
	    	<tr>
	    		<th><font size=2>用户名</font></th>
	    		<th><font size=2>账号</font></th>
	    		<th><font size=2>密码</font></th>
	    		<th><font size=2>头像</font></th>
	    	</tr>
	    	<tr>
	    		<td><input type="text" name="username"></td>
	    		<td><input type="text" name="account"></td>
	    		<td><input type="text" name="password"></td>
	    		<td><input type="text" name="icon"></td>
	    	</tr>
	    </table>
	    <br>
	    <input type="submit" name="b" value="提交添加">
    </form>
    <jsp:setProperty name="insert" property="*"/><br>
    <% insert.getInsertData(); %>
  </body> 
</html>
