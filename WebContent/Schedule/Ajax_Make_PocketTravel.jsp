<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<table class='make_table'>
							<thead>
								<tr>
									<th colspan='7'>여 행 지</th>
								</tr>
								<!-- <tr>
							<td><input type="button" id="travel_location_search" value="지역으로 보기"></td>
							<td><input type="button" id="theme_serarch" value="테마로 보기"></td>
							<td><input type="button" id="personality_search" value="성향으로 보기"></td>
							<td><input type="text" id="travel_Search" value="검색하기"></td>
						</tr> -->
								<tr>
									<th>▼</th>
									<th>이름</th>
									<th>주소</th>
									<th>테마</th>
									<th>메모</th>
									<th>날짜</th>
									<th></th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<td colspan='7'><c:if test="${pagePocketTravel<=1}">
							이전&nbsp;
						</c:if> <c:if test="${pagePocketTravel>1 }">
											<a href='#' class='changePagePocketTravel'>이전&nbsp;</a>
										</c:if> <c:forEach var='a' begin="${startpagePocketTravel}"
											end="${endpagePocketTravel}">
											<c:if test="${a==pagePocketTravel}">
							${a}
							</c:if>
											<c:if test="${a!=pagePocketTravel}">
												<a href='#' class='changePagePocketTravel'>${a }</a>
											</c:if>
										</c:forEach> <c:if test="${pagePocketTravel>=maxpagePocketTravel }">
								&nbsp;다음
							</c:if> <c:if test="${pagePocketTravel<maxpagePocketTravel}">
											<a href="#" class='changePagePocketTravel'>&nbsp;다음</a>
										</c:if> <input type="hidden" value="${pagePocketTravel}"
										id="hiddenPagePocketTravel"></td>
								</tr>
							</tfoot>

							<tbody>
								<c:forEach var="p" items="${trav_list }">
									
									<tr>
										<td><a href='#' class="show_make_travel"
											id="${p.travel_no}" style="text-decoration: none;">▼</a></td>
										<td>${p.travel_name }</td>
										<td>${p.travel_address}</td>
										<td>${p.travel_tema }</td>
										<td><textarea name="det_content" class="text" id="content_${p.travel_no}"
												style="width: 200px; height: 60px"
												placeholder="기억할만한 내용을 메모해보세요"></textarea></td>
										<td><input type="text" id="start_${p.travel_no}" class="datepicker_start"
											name="det_start" placeholder="start">~ 
											<input type="text" id="end_${p.travel_no}" class="datepicker_end" name="det_end"
											placeholder="end"></td>
										<td><input type="button" class="submit_travel_button" id="${p.travel_no}"
											value="추가"></td>
									</tr>
									<tr id="show_make_travel_${p.travel_no}" style="display: none">
										<td colspan="7"><br>${p.travel_content}<br> <br></td>

									</tr>

									<input type="hidden" id="travel_no_${p.travel_no}" name="travel_no" value="${p.travel_no}">
									<input type="hidden" name="check" value="travel">
									<!-- <input type="button" value="삭제" onClick="location.href='./del_detail.s?'"><br> -->
									
								</c:forEach>
							</tbody>

						</table>