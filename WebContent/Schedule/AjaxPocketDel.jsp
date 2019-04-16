<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--  장바구니 삭제 ajax로 불러올 화면 -->
		<fieldset>
				<legend>[장바구니목록]</legend>	<br>
				<div id="pocket_travel">				
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
						
						<td> ${p.travel_address }</td>
						<td> ${p.travel_tema }</td>
						
					</tr>
					<tr id="show_travel_${p.travel_no}" style="display:none"><td colspan="5"> <br>${p.travel_content }<br><br> </td></tr>
					</c:forEach>
					</tbody>
					
					</table>
				</div>
				<br> <br>
				
				<div id="pocket_festival">
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
						<td> ${p.festival_address }</td>
						<td>${p.festival_startday}~${p.festival_endday }</td>
						</tr>
						<tr id="show_festival_${p.festival_no}" style="display:none"><td colspan="5"> <br>${p.festival_content }<br><br></td></tr>
						</c:forEach>
						</tbody>
	
						
						
						
					</table>
		
				</div>	
								
				<%-- 장바구니 정보 출력할 필요 없다/ 장바구니 정보를 이용해서 담아둔 축제와 여행지 정보를 가져오므로  
				<div id="pocket"> 
					<c:forEach var="p" items="${pocket}">	
					<input type="checkbox" class="pocket_list" name="pock_list" value="${p.pocket_no}">		
						[유저번호 : ${p.user_no}]   
						[포켓번호 : ${p.pocket_no}]
						[여행 번호 : ${p.travel_no}] 
						[축제 번호 : ${p.festival_no}]
						<br>
					</c:forEach>
					</div> --%>
						<br>					
						<input type="button" value="장바구니 삭제" id="pocket_del">	
						
			</fieldset>				