<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
	
	<table class='main_table'>
					<thead>
					<tr><th colspan='5'>축 제</th></tr>
					<tr>
						<th></th>
						<th>▼</th>
						<th>축 제</th>
						<th>주소</th>
						<th>일시</th>
					</tr>
					</thead>
					
					<tfoot>
						<tr>
						
						<td colspan='5'>
						<c:if test="${pagePocketFestival<=1}">
							이전&nbsp;
						</c:if>
						<c:if test="${pagePocketFestival>1 }">
							<a href='#' class='changePagePocketFestival'>이전&nbsp;</a>
						</c:if>
						<c:forEach var='a' begin="${startpagePocketFestival}" end="${endpagePocketFestival}" >
							<c:if test="${a==pagePocketFestival}">
							${a}
							</c:if>
							<c:if test="${a!=pagePocketFestival}">
								<a href='#' class='changePagePocketFestival'>${a }</a>
							</c:if>
							</c:forEach>
							<c:if test="${pagePocketFestival>=maxpagePocketFestival}">
								&nbsp;다음
							</c:if>
							<c:if test="${pagePocketFestival<maxpagePocketFestival}">
								<a href="#" class='changePagePocketFestival'>&nbsp;다음</a>
							</c:if>
							<input type="hidden" value="${pagePocketFestival}" id="hiddenPagePocketFestival">
						</td>
					</tr>			
						</tfoot>
					
					
					
					
					
					<tbody>
					<c:forEach var="p" items="${fest_list }">
					<tr>
					<td>
						<input type="checkbox" class="pocket_list" name="pock_fest_list" value="${p.festival_no}">	
						</td>
						<td><a href="#" class="show_festival" id="${p.festival_no}" style="text-decoration:none;">▼</a></td>
						<td>${p.festival_name}</td>
						<td>${p.festival_address }</td>
						<td>${p.festival_startday}~${p.festival_endday }</td>
						</tr>
						<tr id="show_festival_${p.festival_no}" style="display:none"><td colspan="5"> <br>${p.festival_content }<br><br></td></tr>
						</c:forEach>
						</tbody>
	
						
						
						
					</table>