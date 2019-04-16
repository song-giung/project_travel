<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>PW 찾기</title>
<style>
	body{
		text-align : center;
	}
	#close{
	border-radius : 5px;
	border: 2px solid #e7e7e7;
	color: black;
	background-color: #e7e7e7;
	}
</style>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(function(){
		$("#close").click(function(){
			self.close();
			opener.location.reload();
		})
	})
</script>
</head>

<body>
<c:if test="${result ==1 }">
<h3>회원님의 비밀번호는
<br>${client.user_passwd }입니다.</h3>
</c:if>
<c:if test="${result !=1 }">
<h3>입력하신 정보와 일치하는 <br>계정이 없습니다.</h3>
</c:if><br>
<input type="button" id="close" value="닫기">
</body>
</html>