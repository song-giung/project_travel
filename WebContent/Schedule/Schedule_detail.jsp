<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/Schedule/Calendar_view.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>일정 관리하기</title>
	<link rel="stylesheet" type="text/css" href="./Schedule/css/file_1.css">
	<script>
		$(function(){
	
			
		})
	</script>
</head>
<body>
	<jsp:include page="/MainHeader.jsp"/> 
	<jsp:include page="/Schedule/Calendar_view.jsp"/>
	<div id="Schedule_View" class="box">
			<fieldset>
				<legend>일 정 </legend>	
					<form>
						<input type="checkbox" class="Shcedule_list" name="sch_list" value="item">[일정 목록]				<br>	
					<c:forEach var="m" items="${list}">		
						${m.sch_no} /  
						${m.user_no} /
						${m.start_date} /
						${m.end_date} /
						${m.sch_title} /
						<br>
					</c:forEach>
						<br>
						<a href="#">공유하기</a>
						<input type="submit" value="삭제">						
 					</form>
			</fieldset>				
		</div>
		<div id="detail" class="box">
			<form>
				
			
			</form>
		</div>


</body>
</html>