<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<table class='main_table'>
					<thead>
					<tr><th colspan='5'>여 행 지</th></tr>
					<tr>
						<th></th>
						<th>▼</th>
						<th>여행지</th>
						<th>주소</th>
						<th>테마</th>
					</tr>
					</thead>
					
					<tfoot>
					<tr>
						<td colspan='5'>
						<c:if test="${pagePocketTravel<=1}">
							이전&nbsp;
						</c:if>
						<c:if test="${pagePocketTravel>1}">
							<a href='#' class='changePagePocketTravel'>이전&nbsp;</a>
						</c:if>
						<c:forEach var='a' begin="${startpagePocketTravel}" end="${endpagePocketTravel}" >
							<c:if test="${a==pagePocketTravel}">
							${a}
							</c:if>
							<c:if test="${a!=pagePocketTravel}">
								<a href='#' class='changePagePocketTravel'>${a }</a>
							</c:if>
							</c:forEach>
							<c:if test="${pagePocketTravel>=maxpagePocketTravel }">
								&nbsp;다음
							</c:if>
							<c:if test="${pagePocketTravel<maxpagePocketTravel}">
								<a href="#" class='changePagePocketTravel'>&nbsp;다음</a>
							</c:if>
							<input type="hidden" value="${pagePocketTravel}" id="hiddenPagePocketTravel">
						</td>
					</tr>	
					</tfoot>		
					
					<tbody>
					<c:forEach var="p" items="${trav_list }">
					<tr>
					<td>
						<input type="checkbox" class="pocket_list" name="pock_trav_list" value="${p.travel_no}">		
						</td>	
						<td><a href="#" class="show_travel" id="${p.travel_no}" style="text-decoration:none;">▼</a></td>
						<td>${p.travel_name}</td>
						
						<td>${p.travel_address }</td>
						<td> ${p.travel_tema }</td>
						
					</tr>
					<tr id="show_travel_${p.travel_no}" style="display:none"><td colspan="5"> <br>${p.travel_content }<br><br> </td></tr>
					</c:forEach>
					</tbody>
					
					</table>