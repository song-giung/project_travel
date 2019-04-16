<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 목록</title>
<link href ="css/list.css" rel="stylesheet">
<style>
	body{
	background-color : #E0F2F1;
	margin :0px;
	}
	table{
		margin-top : 50px;
		background-color: white;
	}
</style>
</head>
<body>
	<jsp:include page="/MainHeader.jsp"></jsp:include>
	<table>
		<thead>
			<tr>
				<th colspan="3">회원 목록</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${totallist}">
			<tr>
				<td><a href="client_info.net?user_id=${c.user_id}">
				${c.user_id}</a></td>
				<td>${c.user_name}</td>
				<td><a href="client_delete.net?user_id=${c.user_id}">
				삭제</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
</html>







