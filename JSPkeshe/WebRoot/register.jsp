
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<meta charset="utf-8">
	<title>注册</title>
	<link rel="stylesheet" type="text/css" href="css/logIn.css">
	<!--<script type="text/javascript" src="js/logIn.js"></script> -->
</head>
<body>
	<form action="register_servlet" method="post" enctype="multipart/form-data">
		<div id="content">
			<p class="p">注册</p>
			<div class="vsb">
				<span>设置昵称：</span>
				<input type="text" name="username" id="name">
				<p></p>
			</div>
			<div class="vsb">
				<span>设置密码：</span>
				<input type="password" name="password" id="pwd1">
				<p></p>
			</div>
			<div class="vsb">
				<span>确认密码：</span>
				<input type="password" disabled="" id="pwd2">
				<p></p>
			</div>
			<div class="vsb">
				<span>上传头像：</span>
				<input value="添加图片" class="button btn2">
				<input class="button btn1" type="file" name="file">
				<p></p>
			</div>
			<div class="vsb">
				<input type="submit" id="submit" name="submit" value="注册" disabled="" class="btn">
			</div>
			<a href="login.jsp" class="a">已有账号登陆</a>
		</div>	
		
	</form>
	<script type="text/javascript">
	
function getLength(str){
	return str.replace(/[^\x00-xff]/g,"xx").length;
}
function findStr(str,n){
	var tmp = 0;
	for (var i = 0; i < str.length; i++) {
		if(str.charAt(i) == n){
			tmp++
		}
	}
	return tmp;
}


window.onload = function(){
	var aInput = document.getElementsByTagName('input');
	var oName = aInput[0];
	var pwd = aInput[1];
	var pwd2 = aInput[2];
	var photo = aInput[3];
	var sub = aInput[5];
	
	var aP=document.getElementsByTagName('p');
	var name_msg=aP[0];
	var pwd_msg=aP[1];
	var pwd2_msg=aP[2];
	var photo_msg = aP[3];
	
	var count=document.getElementById('name');
	var aEm=document.getElementsByTagName('li');
	
	var name_length=0;
	var num1_length=0;


	oName.onfocus=function(){
		name_msg.style.display="block";
		name_msg.innerHTML='不超过20个字符，一个汉字为两个字符';
	}
	
	oName.onkeyup = function(){
		count.style.visibility = "visible";
		name_length = getLength(this.value);
		count.innerHTML = name_length+"个字符";
		if(name_length == 0){
			count.style.visibility="hidden";
		}
	}
	
	oName.onblur=function(){
		
		var re=/[^a-zA-Z0-9]/g;
		var st=/^[\d]/;
		
		
		if(this.value==""){
			name_msg.innerHTML = '用户名不得为空！';
		}
		
		else if(name_length > 18){
			name_msg.innerHTML = '用户名长度不得超过18个字符！';
		}
		
		else if(name_length < 2){
			name_msg.innerHTML = '用户名长度不得少于2个字符！';
		}
		else if( !(re.test(this.value)) && (st.test(this.value)) ) {	
			name_msg.innerHTML='不能用数字开头!';
		}
		else{
			name_msg.innerHTML = '可以使用！';
		}
		
	}


	pwd.onfocus = function(){
		pwd_msg.style.display = "block";
		pwd_msg.innerHTML = '5-10个字符，请使用特殊符号和大小写字母组合。';
	}
	pwd.onkeyup = function(){
		pwd2.removeAttribute("disabled");
		pwd2_msg.style.display = "block";
	}
	pwd.onblur = function(){
		var m = findStr(pwd.value,pwd.value[0]);
		var re_n = /[^a-zA-Z0-9]/g; // 
		var re_a = /[a-z]/g;
		var re_A = /[A-Z]/g;
		
		if (this.value=="") {
			pwd_msg.innerHTML = '密码不能为空！';
		}
		
		else if (this.value.length < 5 || this.value.length > 10) {
			pwd_msg.innerHTML = '密码长度应为5-10个字符';
		}
		
		else if (  (!re_n.test(this.value)) || (!re_a.test(this.value)) || (!re_A.test(this.value))  ) {
			pwd_msg.innerHTML = '请使用特殊字符和大小写字母组合！';
		}
		else{
			pwd_msg.innerHTML = '可以使用！';
		}
	}


	pwd2.onblur = function(){
		if (this.value != pwd.value) {
			pwd2_msg.innerHTML = '两次输入的密码不一致！';
		}else{
			pwd2_msg.innerHTML = '输入正确';
			sub.removeAttribute("disabled");
			sub.style.cursor="pointer";
			sub.style.background="#8eb900";
			}
		}

}
	</script>

</body>

</html>