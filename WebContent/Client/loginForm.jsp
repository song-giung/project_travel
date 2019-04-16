<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인 페이지</title>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script src = "Client/login.js"></script>
<link href ="./css/login1.css" rel="stylesheet">
<style>
	.submitbtn{
		width:100%;
		background-color:teal;
	}
	body{
		font-family:nanums;
		background-color :#E0F2F1;
		margin : 0px;
	}
</style>
<script>
	$(function(){
		$("#find").click(function(){
			self.close();
			opener.location.href="FindClientData.net";
		})
	})
</script>
<body>
	<div class="container">
	<form name ="loginform.jsp" action="./loginProcess.net" method="post">
		아이디 <b id="msgID"></b>
		<input type="text" name="user_id" id="user_id" placeholder="Enter id" >
		<br>
		비밀번호 <b id="msgPW"></b> 
		<input type="password" name="user_passwd" id="user_passwd" placeholder="Enter pw"><br>
	<hr>
		<div class="clearfix">
			<button type="submit" class="submitbtn" >로그인</button>
		</div>
			<a href="" id="find">아이디/비밀번호 찾기</a>
	</form>
</div>
</body>
</html>