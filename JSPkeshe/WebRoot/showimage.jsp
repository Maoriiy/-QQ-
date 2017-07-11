<%@ page contentType="text/html; charset=utf-8"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showimage.jsp' starting page</title>
    


  </head>
  
  <body>
  <% String imagepath=  "icon/123.png"; %>
  <%= imagepath %>
    <img src= <%= imagepath %>>
  </body>
</html>
