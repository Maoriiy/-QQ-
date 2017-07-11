<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.JSPkeshe.mybean.*"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	
 %>

<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
    <%
    getDataResult result = (getDataResult)session.getAttribute("getDataRes");
    System.out.println("result:"+result.getResult()+"bool:"+ result.isGetDataSuccess());

	if(result.isGetDataSuccess() == false) {
		out.print(result.getResult());
		response.setHeader("refresh", "3;URL=../login.jsp");
	} 
	ArrayList stateList = (ArrayList)session.getAttribute("stateList");
	ArrayList commentList = (ArrayList)session.getAttribute("commentList");
	ArrayList replyList = (ArrayList)session.getAttribute("replyList");
    	out.print("<table border=2>");
                  out.print("<tr>");
                      out.print("<td width=100>"+"id");
                      out.print("<td width=100>"+"date");
                      out.print("<td width=100>"+"time");
                      out.print("<td width=100>"+"message");
                      out.print("<td width=100>"+"image");
                      out.print("<td width=100>"+"isImage");
                      out.print("<td width=100>"+"publisher");
                      out.print("<td width=100>"+"like");
                  out.print("</tr>");
                  
                  for(int i = 0; i < stateList.size();i++){
                  Dynamic_state state = (Dynamic_state)stateList.get(i);
                      out.print("<tr>");
                          out.print("<td>"+state.getId()+"</td>");
                          out.print("<td>"+state.getDate()+"</td>");
                          out.print("<td>"+state.getTime()+"</td>");
                          out.print("<td>"+state.getMessage()+"</td>");
                          out.print("<td>"+state.getImage()+"</td>");
                          out.print("<td>"+state.isImage()+"</td>");
                          out.print("<td>"+state.getPublisher()+"</td>");

                      out.print("</tr>");

                  }
                   out.print("</table>");
                   out.print("<table border=2>");
                  out.print("<tr>");
                      out.print("<td width=100>"+"id");
                      out.print("<td width=100>"+"date");
                      out.print("<td width=100>"+"time");
                      out.print("<td width=100>"+"message");
                      out.print("<td width=100>"+"image");
                      out.print("<td width=100>"+"isImage");
                      out.print("<td width=100>"+"publisher");
                      out.print("<td width=100>"+"like");
                  out.print("</tr>");
                  
                  for(int i = 0; i < stateList.size();i++){
                  Dynamic_state state = (Dynamic_state)stateList.get(i);
                      out.print("<tr>");
                          out.print("<td>"+state.getId()+"</td>");
                          out.print("<td>"+state.getDate()+"</td>");
                          out.print("<td>"+state.getTime()+"</td>");
                          out.print("<td>"+state.getMessage()+"</td>");
                          out.print("<td>"+state.getImage()+"</td>");
                          out.print("<td>"+state.isImage()+"</td>");
                          out.print("<td>"+state.getPublisher()+"</td>");

                      out.print("</tr>");

                  }
                   out.print("</table>");
                   
                   out.print("</table>");
                   out.print("<table border=2>");
                  out.print("<tr>");
                      out.print("<td width=100>"+"id");
                      out.print("<td width=100>"+"userAccount");
                      out.print("<td width=100>"+"time");
                      out.print("<td width=100>"+"message");
                      out.print("<td width=100>"+"getCommentID");


                  out.print("</tr>");
                  
                  for(int i = 0; i < replyList.size();i++){
                  Reply reply = (Reply)replyList.get(i);
                      out.print("<tr>");
                          out.print("<td>"+reply.getId()+"</td>");
                          out.print("<td>"+reply.getUserAccount()+"</td>");
                          out.print("<td>"+reply.getTime()+"</td>");
                          out.print("<td>"+reply.getMessage()+"</td>");
                          out.print("<td>"+reply.getCommentID()+"</td>");


                      out.print("</tr>");

                  }
                   out.print("</table>");
     %>
  </body>
</html>
