<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>회원 수정 페이지</title>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script src = "./Client/update1.js"></script>
<link href="css/join1.css" rel="stylesheet">
<style>
	body{background-color : #E0F2F1;}
	input[type=password]{font-family:none;}
	.container{
	margin :0px;
	font-size:22px;
	text-align:center;
	}
	input{text-align:center; font-size:20px;}
	input[type="text"]{width:800px; border-radius:30px;}
	input[type="password"]{width:800px; border-radius:30px;}
	form{border-style:double 1px teal;
		border-radius:30px;}
	form div{ text-align:center; width:800px; margin-bottom:50px border-radius:30px;}
	.clearfix{text-align:center; margin:auto; margin-bottom:50px}
</style>
<script>
	$(document).ready(function(){
		var pandan = '${client.user_type}';
		if(pandan== '1'){
			$("input:radio").eq(0).attr('checked','checked');
		}else if(pandan=='2'){	
			$("input:radio").eq(1).attr('checked','checked');
		}else if(pandan=='3'){	
			$("input:radio").eq(2).attr('checked','checked');
		}else if(pandan=='4'){	
			$("input:radio").eq(3).attr('checked','checked');
		}else{	
			$("input:radio").eq(4).attr('checked','checked');
		}
		$(".cancelbtn").click(function(){
			location.href="main.net";
		})
	});
</script>
<style>
	input[type=submit] {
	border-radius:20px;
	border: 3px outset silver;
    background-color: teal;

    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
   
}	
input[type=submit]:hover{ border: 3px inset silver; }
	input[type=button] { 
	border-radius:20px;
	border: 3px outset silver;
    background-color: crimsonred;

    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
     
}
input[type=button]:hover{ border: 3px inset silver; }

</style>
<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>
<div class="container">
<form name="joinform" action="updateProcess.net" method="post">
	<h1>회원수정 페이지</h1>
	<hr>
	<b>&nbsp;아&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;디&nbsp;</b>
	<input type="text" name="user_id" placeholder="Enter id" value="${client.user_id }" readonly><br>
	<b>비&nbsp;밀&nbsp;번&nbsp;호</b>
	<input type="password" name="user_passwd" id="user_passwd" placeholder="Enter password" value="${client.user_passwd }" ><br>
	<b>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</b>
	<input type="text" name ="user_name" id="user_name" placeholder="Enter name"  value="${client.user_name }" ><br>
	<b>이메일 주소</b>
	<input type="text" name="user_email" id="user_email" placeholder="Enter email" value="${client.user_email }" ><br><br>
	<b>여행자 성향</b>
	<div>
	<label><input type="radio" name="user_type"  value="1" ><span>모험 추구 여행자</span></label>
	<label><input type="radio" name="user_type"  value="2" ><span>경험 추구 여행자</span></label>
	<label><input type="radio" name="user_type"  value="3" ><span>안정 추구 여행자</span></label><br>
	<label><input type="radio" name="user_type"  value="4" ><span>감각 추구 여행자</span></label>
	<label><input type="radio" name="user_type"  value="0" ><span>미생 여행자</span></label>
	
	</div><br>
	
	<div class="clearfix">
		<input type="submit" class="submitbtn" value="수정">
		<input type="button" class="cancelbtn" value="취소">
	</div>
	</form>
</div>
</body>
</html>