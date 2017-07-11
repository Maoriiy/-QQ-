<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.JSPkeshe.mybean.*"  %>
<%@ page import="java.text.SimpleDateFormat" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% 
	    getDataResult result = (getDataResult)session.getAttribute("getDataRes");
	if(result.isGetDataSuccess() == false) {
		out.print(result.getResult());
		response.setHeader("refresh", "3;URL=login.jsp");
	} 
	String realPath = request.getSession().getServletContext().getRealPath("/");
	ArrayList stateList = (ArrayList)session.getAttribute("stateList");
	ArrayList commentList = (ArrayList)session.getAttribute("commentList");
	ArrayList replyList = (ArrayList)session.getAttribute("replyList");
	ArrayList userList = (ArrayList)session.getAttribute("userList");
	ArrayList likeList = (ArrayList)session.getAttribute("likeList");
	User loginUser = (User)session.getAttribute("loginUser");
	LoginResult loginRes = (LoginResult)session.getAttribute("loginResult");
	
 %>
<html>
<head>
	<meta charset="utf-8">
	<title>仿QQ空间动态发布系统</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript">
	function show(r){
		var text = r.getElementsByTagName("textarea");
		var btn = r.getElementsByTagName("input");
		//alert(btn.length);
		text[0].className = "rereply_content";
		btn[0].className = "btn rreply_input";
	}
	</script>
</head>
<body>
	<div class="top">
		
		<% 
			if (loginRes != null ) {
				if (loginRes.isLoginSuccess() == true) {
				out.print(" <a href=\"logoutServlet\" class=\"logout\">退出 </a> ");
					out.print(" <span>" + loginUser.getUsername() + "</span> <div class=\"top_pho\">");
					if(loginUser.getIcon() != null) {
						out.print("<img src=\"icon/" + loginUser.getIcon() + "\">" );	
					} else {
						out.print("<img src=\"icon/default.jpg \">");
					}
					out.print("</div>");
				} else {
					out.print("<span> <a href=\"login.jsp\">登录</a> | <a href=\"register.jsp\">注册</a> </span> <div class=\"top_pho\"><img src=\"icon/default.jpg\"></div>");
				}
			} else {
				out.print("<span> <a href=\"login.jsp\">登录</a> | <a href=\"register.jsp\">注册</a> </span> <div class=\"top_pho\"><img src=\"icon/default.jpg\"></div>");
			}
		 %>

	</div>
	<div class="header">
		<form action="publishState" method="post" enctype="multipart/form-data">
		
			<textarea class="header_set" rows="4" cols="88" placeholder="说点什么吧..." name="content"></textarea>
			<% 
				out.print("<input class=\" ");
				if(loginRes != null){
					if(loginRes.isLoginSuccess() == true){
						out.print("btn");
					}
					else{
						out.print("disable");
					}
				} else{
					out.print("disable");
				}
				out.print("\" value=\"发表\" type=\"submit\">");
			 %>
			 <% 
				out.print("<input class=\"");
				if(loginRes != null){
					if(loginRes.isLoginSuccess() == true){
						out.print("btn btn3\" value=\"添加图片\" > <input type=\"file\" id=\"file\" name=\"file\" class=\"btn btn2\">");
						
					}
					else{
						out.print("disable btn3\" value=\"添加图片\" >");
					}
				} else{
					out.print("disable btn3\" value=\"添加图片\" >");
				}
				
			 %>
		
		

			
		</form>
	</div>
	<div class="container">
	<%
		for(int i = stateList.size()-1; i>=0; i--){
			Dynamic_state state = (Dynamic_state)stateList.get(i);
			out.print(" <div class=\" content\" >");
			
			out.print("<div class=\"content_item\">");
				SimpleDateFormat sdf = new SimpleDateFormat("M月d日 ");
	
				out.print("<div class=\"content_date\"> "+ sdf.format(state.getDate()) +"</div>");
				out.print("<div class=\"content_item_icon_arrow\"></div>");
				out.print("<div class=\"content_item_icon_dot\">");
					out.print("<div class=\"content_item_icon_dot_child\"></div>");
				out.print("</div>");
				out.print("<div class=\"content_item_head\">");
					out.print("<div class=\"content_item_head_title\">");
						User stateUser = null;
						for (int k = 0; k < userList.size(); k++) {
							User uu = (User)userList.get(k);
							if(uu.getAccount() == state.getPublisher()) {
								stateUser = uu;
								break;
							}
						}
						out.print("<div class=\"content_item_head_title_pho\">");
							if(stateUser.getIcon() == null) {
								out.print("<img src=\"icon/default.jpg\">");
							} else{
								out.print("<img src=\"icon/"+ stateUser.getIcon() +"\">"); // 用户头像
							}
							
						out.print("</div>");
						
						
						out.print("<span>"+ stateUser.getUsername() +"</span>"); //用户名
						out.print("<p>"+ state.getTime().toString() +"</p>");
						if(loginRes != null){
							if((loginRes.isLoginSuccess() == true && state.getPublisher() == loginUser.getAccount()) || loginUser.getAccount() == 110){
								out.print("<a href=\"deleteState?stateID="+ state.getId() +"\" class=\"btn delete \">删除</a>");
							}
						}
						
						
					out.print("</div>");
					out.print("<div class=\"content_item_head_intro\">");
						out.print("<i class=\"ui_icon quote_before\"></i><!-- 引号用的是图片 -->");
						out.print( state.getMessage().toString() ); //动态的内容 
						out.print("<i class=\"ui_icon quote_after\"></i>");
					out.print("</div>");
				out.print("</div>");
				if (state.isImage()) {
					out.print("<div class=\"content_item_media\">");
					out.print("<img src=\"image/" + state.getImage() + "\" >"); // 动态内的图片 
					out.print("</div>");
				}
				out.print("<div class=\"content_item_footer\">");
					out.print("<div class=\"content_item_footer_info\">");
					//点赞功能
						out.print("<a href=\"likeServlet?stateID="+ state.getId() +" \" title=\"赞\"><i class=\"");
							if(loginRes != null) {
								if(loginRes.isLoginSuccess()){
									boolean flag = false;
									for(int d = 0; d < likeList.size();d++){
										Like like = (Like)likeList.get(d);
										if(like.getAccount() == loginUser.getAccount() && state.getId() == like.getStateID()){
											flag = true;
											break;
										}
									}
									if(flag){
										out.print("icon_zaned");
									}else{
										out.print("icon_zan");
									}
								} else{
									out.print("icon_zan");
								}
							} else{
							out.print("icon_zan");
							}
							
							out.print("\"></i></a>");//赞//赞
						out.print("<a href=\"javascript:;\" title=\"评论\"><i class=\"icon_pin\"></i></a>");
					out.print("</div>");
					out.print("<div class=\"content_item_footer_like\">");
						int num = 0;
							for(int l = 0; l<likeList.size(); l++) {
								Like like = (Like)likeList.get(l);
								if(like.getStateID() == state.getId()) {
									num++;
								}
							}
							out.print( num + "人觉得很赞");
					out.print("</div>");
					out.print("<div class=\"content_item_footer_comment\">");
					//评论
					for(int j = 0; j<commentList.size(); j++){
						Comment cmt = (Comment)commentList.get(j);
						if(cmt.getStateID() == state.getId()) {
							User commentUser = null;
								for (int a = 0; a < userList.size(); a++) {
									User uu = (User)userList.get(a);
									if(uu.getAccount() == cmt.getUserAccount()) {
										commentUser = uu;
										break;
									}
								}
						
						out.print("<div class=\"comment\">");
						if(commentUser.getIcon() != null) {
						out.print("<div class=\"content_item_footer_comment_pho\"><img src=\"icon/"+commentUser.getIcon()+"\"><!-- 评论头像 --></div>");
					} else {
						out.print("<div class=\"content_item_footer_comment_pho\"><img src=\"icon/default.jpg\"><!-- 评论头像 --></div>");
					}
							
							out.print("<div class=\"comment_content\">");
								out.print("<span class=\"content_item_footer_comment_name\">"+commentUser.getUsername()+":</span><!-- 用户名 -->");
								out.print("<span class=\"content_item_footer_comment_comment\" >"+ cmt.getMessage() +"</span>");//评论内容
							out.print("</div>");
							out.print("<div class=\"setted_time\"> "+cmt.getTime().toString());
								out.print("<span class=\"content_item_footer_comment_reply\">");
								if(loginRes != null){
									if(loginRes.isLoginSuccess() == true){
										out.print("<a href=\"javascript:;\" id=\"reply\" onclick=\"show(this)\">回复");
									}
								}
									
										out.print("<div class=\"rreplay\">");
											out.print("<form action=\"replyServlet\" method=\"post\">");
											
												out.print("<textarea class=\"rereplay_content\" name=\"content\"  cols=\"63\" rows=\"3\" placeholder=\"回复 "+ commentUser.getUsername() +"：\"></textarea>");
												out.print("<input name=\"btn2\" type=\"submit\" class=\"btn rreplay_input\" value=\"发表\" > ");
												out.print("<input type=\"hidden\" name=\"commentID\" value=\""+ cmt.getId() +"\">");
											out.print("</form>");
										out.print("</div>");
									out.print("</a>");
								out.print("</span> ");
								if(loginRes != null){
									if((loginRes.isLoginSuccess() == true && cmt.getUserAccount() == loginUser.getAccount())||loginUser.getAccount() == 110){
										out.print("<span class=\"content_item_footer_comment_delete\"><a href=\"deleteComment?commentID="+ cmt.getId() +"\">删除</a></span>");
									}
								} 
							out.print("</div>");
						out.print("</div>");
						//回复	
						for(int c =replyList.size()-1; c>=0 ; c--) {
							Reply reply = (Reply)replyList.get(c);
							if(reply.getCommentID() == cmt.getId()) {
								User replyUser = null;
								for (int a = 0; a < userList.size(); a++) {
									User uu = (User)userList.get(a);
									if(uu.getAccount() == reply.getUserAccount()) {
										replyUser = uu;
										break;
									}
								}
							out.print("<div class=\"ccomment\">");
							if(commentUser.getIcon() != null) {
						out.print("<div class=\"content_item_footer_comment_pho\"><img src=\"icon/"+ replyUser.getIcon() +"\"><!-- 评论头像 --></div>");
					} else {
						out.print("<div class=\"content_item_footer_comment_pho\"><img src=\"icon/default.jpg\"><!-- 评论头像 --></div>");
					}
								
								out.print("<div class=\"comment_content\">");
									out.print("<span class=\"content_item_footer_comment_name\">"+replyUser.getUsername()+":</span><!-- 用户名 -->");
									out.print("<span class=\"content_item_footer_comment_comment\" >"+ reply.getMessage() +"</span");
								out.print("</div>");
								out.print("<div class=\"setted_time\"> "+reply.getTime().toString());
									
									if(loginRes != null){
										if((loginRes.isLoginSuccess() == true && reply.getUserAccount() == loginUser.getAccount())||loginUser.getAccount() == 110){
											out.print("<span class=\"content_item_footer_comment_delete\"><a href=\"deleteReply?replyID="+ reply.getId() +"\">删除</a></span>");
										}
									} 
								out.print("</div>");
							out.print("</div>");
							out.print("</div>");
								}
							}//回复
							
						}
					}
						
						
					out.print("</div>");
								if(loginRes != null){
										if(loginRes.isLoginSuccess() == true ){
											out.print("<div class=\"content_item_footer_comment_set\">");
						out.print("<form action=\"commentServlet\" method=\"post\">");
						out.print("<input type=\"hidden\" value=\""+ state.getId() +"\" name=\"stateID\">");
						out.print("<textarea name=\"content\" class=\"content_item_footer_comment_set_content\" cols=\"80\" rows=\"3\" placeholder=\"评论\" ></textarea>");
						out.print("<input name=\"btn\" class=\"btn\" value=\"发表\" type=\"submit\">");
						out.print("</form>");
					out.print("</div>");
										}
									}
					
				out.print("</div>");
			out.print("</div>");
		out.print("</div>");
		}
	 %>
		
	</div>
</body>	

</html>