<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="Java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"  %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	
	<style type="text/css">
		*{margin: 0; padding: 0;font-family: 微软雅黑;}
		body{background-image: url(img/bg.jpeg);background-size:cover ;}
		#form{
			width: 500px;height: 350px;
			background-color: #fff;
			margin: 350px auto;
			border-radius: 3px;
			opacity: 0.95;
			position: relative;
		}
		input,button{
			width: 300px; height: 40px;
			left: 100px;padding: 10px;
			font-size: 15px;
			border-style: solid;border-radius: 3px;
			position: absolute;
		}
		::webkit-input-placeholder { /* WebKit 内核浏览器 */;color: #aaaaaa;font-size: 15px;}
　　    	:-moz-placeholder { /* Mozilla Firefox 4 to 18 */color:#aaaaaa;font-size: 15px;}
		::-moz-placeholder {/* Mozilla Firefox 19+ */color:#aaaaaa;font-size: 15px;}
　　    	:-ms-input-placeholder { /* Internet Explorer 10+ */color:#aaaaaa;font-size: 15px;}
		#id{top: 80px;}
		#pwd{top: 140px;}
		#submit{
			height: 45px;
			top: 200px;
			text-align: center;padding-left: 30px;
			background-color: #8eb900;
			font-size: 20px;color: #fff;letter-spacing: 23px;
			border: 0px;
		}
		a{
			text-decoration: none;
			color: #000;
		}
		p{
			font-size: 20px;color: #000;letter-spacing: 18px;
			position: absolute;top: 20px;left: 220px;
		}
		span{
			text-decoration: none;color: #666666;font-size: 13px;letter-spacing: 2px;
			position: absolute;bottom: 20px;right: 20px;
		}
	</style> 
</head>
<body>
	<div id="form">
		<form action="loginServlet" method="post">
			<p>登录</p>
			<input placeholder="账号" id="id" name="account">
			<input placeholder="密码" id="pwd" name="password" type="password">
			<input type="submit" id="submit" name="submit" value="登录">
			<span> <a href="register.jsp" id>注册新账号</a></span><!-- 跳转到注册界面 -->
		</form>
	</div>
</body>
</html>
