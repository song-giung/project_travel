<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<style>
	body{
	background-color : #E0F2F1;
	margin :0px;
	}
	h1, h2, h3{
		text-align : center;
	}
</style>
<script>
function login1(){
	window.open("./login.net","login","width=500, height=500, top=250, left=750, resizable=no, scrollbar=no");
}
</script>
<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>
<h1>가입을 환영합니다.</h1>
<h2>
<c:choose>
	<c:when test="${c==1 }">
	<img src="./Client/1type.png"><br>
	회원님은 모험 추구 여행자
	</c:when>
	<c:when test="${c==2 }">
	<img src="./Client/2type.png"><br>
	회원님은 경험 추구 여행자
	</c:when>
	<c:when test="${c==3 }">
	<img src="./Client/3type.png"><br>
	회원님은 안정 추구 여행자
	</c:when>
	<c:when test="${c==4 }">
	<img src="./Client/4type.png"><br>
	회원님은 감각 추구 여행자
	</c:when>
	<c:otherwise>
	<img src="./Client/0type.png"><br>
	회원님은 미생 여행자
	</c:otherwise>
</c:choose>
입니다.</h2><br>
<h3><a href="./Mainpage.jsp" onClick="login1()">로그인</a></h3>
</body>
</html>