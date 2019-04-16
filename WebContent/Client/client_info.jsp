<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:include page="/MainHeader.jsp"></jsp:include>

<%--<c:set var="m" value="${memberinfo}"/> --%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보</title>
<style>
	table{
		margin:50px auto auto auto;
		background-color : white;
		border-radius : 30px;
		border : none;
		width:500px;
		font-size:30px;
		
	}
	td{
		width : 150px;
	}
	tr td:first-child{
		text-align : center;
	}
	tr td:last-child{
	
		border-left:1px dotted lightgray;
		padding-left : 10px;
	}
	body{
	background-color : #E0F2F1;
	margin :0px;
	}
</style>
</head>
<body>
	<form>
		<table>
			<tr>
				<td colspan="2">회원 정보</td>
			</tr>
			<tr>
				<td>회원번호</td>
				<td>${info.user_no}</td>
			</tr>
			<tr>
				<td>ID</td>
				<td>${info.user_id}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${info.user_name}</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>${info.user_email}</td>
			</tr>
			<tr>
				<td>성향</td>
				<td><c:choose>
				<c:when test="${info.user_type==1 }">
				모험 추구 여행자
				</c:when>
				<c:when test="${info.user_type==2 }">
				경험 추구 여행자
				</c:when>
				<c:when test="${info.user_type==3 }">
				안정 추구 여행자
				</c:when>
				<c:when test="${info.user_type==4 }">
				감각 추구 여행자
				</c:when>
				<c:otherwise>
				미생 여행자
				</c:otherwise>
				</c:choose></td>
			</tr>
			<tr>
				<td colspan="2"><a href="client_list.net">리스트로 돌아가기</a></td>			
			</tr>
		</table>
	</form>
</body>
</html>