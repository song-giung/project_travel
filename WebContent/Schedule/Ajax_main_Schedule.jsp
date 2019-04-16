<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<table class='main_table'>
					<thead>
					<tr><th colspan='5'>일 정</th></tr>
					<tr>
						<th>&nbsp;</th>
						<th>일정 번호</th>
						<th>일정 이름</th>
						<th>시작일</th>
						<th>종료일</th>
					</tr>
				 	</thead>
				 	
				 	<tfoot>
					<tr>
						<td colspan='5'> 
						<c:if test="${page<=1}">
							이전&nbsp;
						</c:if>
						<c:if test="${page>1 }">
							<a href='#' class='changePage'>이전&nbsp;</a>
						</c:if>
						<c:forEach var='a' begin="${startpage}" end="${endpage}" >
							<c:if test="${a==page}">
							${a}
							</c:if>
							<c:if test="${a!=page}">
								<a href='#' class='changePage'>${a }</a>
							</c:if>
							</c:forEach>
							<c:if test="${page>=maxpage }">
								&nbsp;다음
							</c:if>
							<c:if test="${page<maxpage}">
								<a href="#" class='changePage'>&nbsp;다음</a>
							</c:if>
							<input type="hidden" value="${page}" id="hiddenPage">
						</td>
					</tr>	
					</tfoot>
				 	
				 	<tbody>
					<c:if test='${empty list}'>
					<tr><td colspan="5">
						생성된 일정이 없습니다. 나만의 일정을 추가해 보세요
					</td></tr>
					</c:if>
				
					<c:forEach var="m" items="${list}">	
					<tr>
						<td><input type="checkbox" class="sch_list" name="sch_list" value="${m.sch_no}">
						</td>
						<td> ${m.sch_no}</td>
						<td>${m.sch_title}</td>
						
						<%--  [일정 번호 : ${m.sch_no}]  --%>
						<%-- [회원 번호 : ${m.user_no}] --%>
						<td>${m.start_date}</td>
						<td>${m.end_date}</td>
					
						<tr>
					</c:forEach>	
					</tbody>
		
					</table>