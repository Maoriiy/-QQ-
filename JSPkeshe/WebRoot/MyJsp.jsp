<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	      <%
        Connection con;
        Statement sql;
        ResultSet rs;
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch(Exception e){
                out.println("忘记把Mysql数据库的jdbc驱动程序复制到jdk的扩展目录或tomcat的lib中");
            }
            try{
                  String uri="jdbc:mysql://localhost:3306/jspkeshe";
                  String user="root";
                  String password="root";
                  con=DriverManager.getConnection(uri,user,password);
                  sql=con.createStatement();
                  rs=sql.executeQuery("select * from users");
                  out.print("<table border=2>");
                  out.print("<tr>");
                      out.print("<td width=100>"+"用户名");
                      out.print("<td width=100>"+"账号");
                      out.print("<td width=100>"+"密码");
                      out.print("<td width=100>"+"图标");
                  out.print("</tr>");
                  while(rs.next()){
                      out.print("<tr>");
                          out.print("<td>"+rs.getString(1)+"</td>");
                          out.print("<td>"+rs.getInt(2)+"</td>");
                          out.print("<td>"+rs.getString(3)+"</td>");
                          out.print("<td>"+rs.getString(4)+"</td>");
                      out.print("</tr>");

                  }
                   out.print("</table>");
                   con.close();

            }catch(SQLException e){
              out.print(e);
            }

      %> 
  </body>
</html>
