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
    
    <title>My JSP 'insert.jsp' starting page</title>
    
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
String url="jdbc:mysql://localhost:3306/jspkeshe";
String user="root";
			String password="root";
			Connection conn;
            PreparedStatement st =null;
try{
Class.forName("com.mysql.jdbc.Driver");
}
catch(Exception e){
}
			conn=DriverManager.getConnection(url,user,password);
			
			//String sql="INSERT INTO users(username,account,password,icon) VALUES('22','123','12','23')";
			String sql="INSERT INTO users VALUES(?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, "xxx");
			st.setInt(2, 11);
			st.setString(3, "yyy");
			st.setString(4, "www");
			st.execute();
			//st.executeUpdate();
			
            //st=(Statement)conn.createStatement();

            //st.executeUpdate(sql);
		/* 	if(count>0)
			{
response.sendRedirect("success.jsp");
} */
			conn.close();

%>
  </body>
</html>
