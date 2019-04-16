<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:forEach var="ccb" items="${scb}">
		<tr>
		<td>${ccb.user_id}[${ccb.user_type}]</td>
		<td>${ccb.sch_comt_cont}</td>
		<td>${ccb.sch_comt_date}
		<c:if test="${ccb.user_no==sessionScope.user_no}"> 
			||<button type="button" class="comtDelete"  value="${ccb.sch_comt_no}" >삭제</button>
			</c:if>
		</td>
		</tr>
	</c:forEach>
	<tr class="h30 lime center btn">
		<td colspan=3>
			<c:if test="${page <=1}">
			 이전 &nbsp;
			</c:if>
			<c:if test="${page >1 }">
				<a href="./ListComt.schcomt?page=${page-1}" class="a">이전</a>
			</c:if>
				
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a==page}">
					${a}
				</c:if>
				<c:if test="${a!=page}">
				
				 <%-- <button type="button" value="${a}" class="a">${a}</button>  --%>
				 <a href="./ListComt.schcomt?page=${a}" class="a">${a}</a> 
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
				&nbsp; 다음
			</c:if>
			<c:if test="${page <maxpage }">
				<a href="./ListComt.schcomt?page=${page+1}" class="a">&nbsp;다음</a>
			</c:if>
			</td>
			
			</tr>
	
	<input id="comt_count" type="hidden" value="${comt_count}"/>
	<input id="page" type="hidden" value="${page}"/>
	