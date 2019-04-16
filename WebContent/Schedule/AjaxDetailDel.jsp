<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

     			<c:forEach var="d" items="${detail}">	
						<input type="checkbox" class="det_list" name="det_list" value="${d.detail_no}">
						[일정  번호 : ${d.sch_no}]
						[세부 일정 번호 : ${d.detail_no}]
						[회원 번호 : ${d.user_no}]
						[여행 번호 : ${d.travel_no}]
						[축제 번호 : ${d.festival_no}]
						[세부 일정 내용 : ${d.det_content}]
						[세부 일정 시작일 : ${d.det_start}]
						[세부 일정 종료일 : ${d.det_end}]
						[세부 일정 날짜: ${d.det_date}]
						
						<br>
					</c:forEach>