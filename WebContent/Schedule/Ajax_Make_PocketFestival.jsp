<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class='make_table'>
							<thead>
								<tr>
									<th colspan='7'>축 제</th>
								</tr>
								<!-- <tr>
				<td><input type="button" id="festival_location_search" value="지역으로 보기"></td>
				<td><input type="button" id="time_serarch" value="기간으로 보기"></td>
				<td><input type="text" id="festival_search" value="검색하기"></td>
				</tr> -->

								<tr>
									<th>▼</th>
									<th>이름</th>
									<th>주소</th>
									<th>기간</th>
									<th>메모</th>
									<th>날짜</th>
									<th></th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<td colspan='7'><c:if test="${pagePocketFestival<=1}">
							이전&nbsp;
						</c:if> <c:if test="${pagePocketFestival>1 }">
											<a href='#' class='changePagePocketFestival'>이전&nbsp;</a>
										</c:if> <c:forEach var='a' begin="${startpagePocketFestival}"
											end="${endpagePocketFestival}">
											<c:if test="${a==pagePocketFestival}">
							${a}
							</c:if>
											<c:if test="${a!=pagePocketFestival}">
												<a href='#' class='changePagePocketFestival'>${a }</a>
											</c:if>
										</c:forEach> <c:if test="${pagePocketFestival>=maxpagePocketFestival}">
								&nbsp;다음
							</c:if> <c:if test="${pagePocketFestival<maxpagePocketFestival}">
											<a href="#" class='changePagePocketFestival'>&nbsp;다음</a>
										</c:if> <input type="hidden" value="${pagePocketFestival}"
										id="hiddenPagePocketFestival"></td>
								</tr>
							</tfoot>

							<tbody>
								<c:forEach var="p" items="${fest_list }">
									
									<tr>
										<td><a href='#' class="show_make_festival"
											id="${p.festival_no}" style="text-decoration: none;">▼</a></td>
										<td>${p.festival_name}</td>

										<td>${p.festival_address}</td>
										<td>${p.festival_startday}~${p.festival_endday }</td>
										<td><textarea name="det_content" id="content_${p.festival_no }"class="text"
												style="width: 200px; height: 60px"
												placeholder="기억할만한 내용을 메모해보세요"></textarea></td>
										<td><input type="text" id="start_${p.festival_no }" class="datepicker_start"
											name="det_start" placeholder="start"> ~ <input
											type="text" id="end_${p.festival_no }" class="datepicker_end" name="det_end"
											placeholder="end"></td>
										<td><input type="button" class="submit_festival_button" id=${p.festival_no }
											value="추가"></td>
										<!-- <input type="button" value="삭제" onClick="location.href='./del_detail.s?'"><br> -->
									</tr>
									<tr id="show_make_festival_${p.festival_no}"
										style="display: none">
										<td colspan="7"><br>${p.festival_content}<br> <br></td>

									</tr>
									<input type="hidden" id="festival_no_${p.festival_no}" name="festival_no"
										value="${p.festival_no}">
									<input type="hidden" name="check" value="festival">
									
								</c:forEach>
							</tbody>

						</table>