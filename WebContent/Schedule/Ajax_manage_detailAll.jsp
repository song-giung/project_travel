<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<table  class="manage_table">		
					<thead>
					<tr>
					<th>체크</th>
					<th>일정번호</th>
					<th>세부일정번호</th>
					<th>제목</th>
					<th>메모</th>
					<th>날짜</th>
					</tr>
					</thead>
					<tfoot>
						
					<tr>
						<td colspan='6'>
						<c:if test="${pageDT<=1}">
							이전&nbsp;
						</c:if>
						<c:if test="${pageDT>1 }">
							<a href='#' class='changePageDT'>이전&nbsp;</a>
						</c:if>
						<c:forEach var='a' begin="${startpageDT}" end="${endpageDT}" >
							<c:if test="${a==pageDT}">
							${a}
							</c:if>
							<c:if test="${a!=pageDT}">
								<a href='#' class='changePageDT'>${a }</a>
							</c:if>
							</c:forEach>
							<c:if test="${pageDT>=maxpageDT }">
								&nbsp;다음
							</c:if>
							<c:if test="${pageDT<maxpageDT}">
								<a href="#" class='changePageDT'>&nbsp;다음</a>
							</c:if>
							<input type="hidden" value="${pageDT}" id="hiddenPageDT">
						</td>
					</tr>
					</tfoot>
					<tbody>
						<c:forEach var="d" items="${detail}">	
						<tr>
						<td>
						<input type="checkbox" class="det_list" name="det_list" value="${d.detail_no}">
						</td>
						<td>${d.sch_no}</td>
						<td>${d.detail_no}</td>
						<td>${d.det_title}</td>
						<td>${d.det_content}</td>
						<td>${d.det_start}~${d.det_end} ${d.det_date}</td>
						<%-- [일정  번호 : ${d.sch_no}]
						[세부 일정 번호 : ${d.detail_no}]
						[세부 일정 제목  : ${d.det_title}]
						[회원 번호 : ${d.user_no}]
						[여행 번호 : ${d.travel_no}]
						[축제 번호 : ${d.festival_no}]
						[세부 일정 내용 : ${d.det_content}]
						[세부 일정 시작일 : ${d.det_start}]
						[세부 일정 종료일 : ${d.det_end}]
						[세부 일정 날짜: ${d.det_date}] --%>
						
						
						</tr>
					</c:forEach>
					</tbody>
		 			
					
					</table>