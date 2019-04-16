<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./Schedule/css/file_1.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41e91c5e42294975805254152247805e&autoload=false&libraries=services"></script>
<script src='./Schedule/js/jquery-3.3.1.min.js'></script>
<!--달력 불러오기  -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	jQuery.noConflict();
	jQuery(document).ready(function(j) {

		daum.maps.load(function(){
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new daum.maps.LatLng(36.18501, 127.77231), // 지도의 중심좌표
				level : 13, // 지도의 확대 레벨
				mapTypeId : daum.maps.MapTypeId.ROADMAP
			// 지도종류
			};

			// 지도를 생성한다 
			var map = new daum.maps.Map(mapContainer, mapOption);

			daum.maps.event.addListener(map, 'click', function() {
				map.setCenter(new daum.maps.LatLng(36.18501,127.77231)), map.setLevel(13)
			})

			// 지도 타입 변경 컨트롤을 생성한다
			var mapTypeControl = new daum.maps.MapTypeControl();

			// 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
			map.addControl(mapTypeControl,daum.maps.ControlPosition.TOPRIGHT);

			// 지도에 확대 축소 컨트롤을 생성한다
			var zoomControl = new daum.maps.ZoomControl();

			// 지도의 우측에 확대 축소 컨트롤을 추가한다
			map.addControl(zoomControl,daum.maps.ControlPosition.RIGHT);

			var geocoder = new daum.maps.services.Geocoder();
			///////////////////////////////

			<c:forEach var="item" items="${fest_List}">
			console.log("festival 지도 ")
			console.log('${item.festival_address}')
			geocoder.addressSearch('${item.festival_address}',
							function(result, status) {
								if (status === daum.maps.services.Status.OK) {
									console.log(status+"festival")
									var coords = new daum.maps.LatLng(result[0].y, result[0].x);
									var marker = new daum.maps.Marker({
										map : map,
										position : coords
									});

									var infowindow = new daum.maps.InfoWindow(
											{
												content : "<a href='./userFestival_info.fs?no=${item.festival_no}'>${item.festival_name}</a>",
												removable : true
											});
									// 마커에 mouseover 이벤트를 등록한다
									daum.maps.event.addListener(marker,
											'mouseover', function() {
												infowindow.open(map,
														marker);

											});
									// 마커에 mouseout 이벤트 등록
									/* daum.maps.event.addListener(infowindow, 'mouseout', function() {
									   infowindow.close();
									}); */

									// 마커에 클릭 이벤트를 등록한다 (우클릭 : rightclick)
									daum.maps.event.addListener(marker,
											'click', function() {

												map.setLevel(4);
												map.setCenter(coords);
											});

								}
							})

			</c:forEach>

			<c:forEach var="item" items="${trav_List}">
			geocoder.addressSearch(
							'${item.travel_address}',
							function(result, status) {

								if (status === daum.maps.services.Status.OK) {
									console.log(status+"travel")
									var coords = new daum.maps.LatLng(
											result[0].y, result[0].x);

									var marker = new daum.maps.Marker({
										map : map,
										position : coords
									});

									var infowindow = new daum.maps.InfoWindow(
											{
												content : "<a href='./userTravel_info.tr?no=${item.travel_no}'>${item.travel_name}</a>",
												removable : true
											});

									// 마커에 mouseover 이벤트를 등록한다
									daum.maps.event.addListener(marker,
											'mouseover', function() {
												infowindow.open(map,
														marker);

											});

									// 마커에 mouseout 이벤트 등록
									/* 	daum.maps.event.addListener(infowindow, 'mouseout', function() {
									    infowindow.close();
									});	   */

									// 마커에 클릭 이벤트를 등록한다 (우클릭 : rightclick)
									daum.maps.event.addListener(marker,
											'click', function() {

												map.setLevel(4);
												map.setCenter(coords);
											});

								}
							})

			</c:forEach>

			/////////////////////////////////////////////////////////////////
		


		})
			
		

		
		
		j("#pocket_view").css({
			'display' : 'none'
		})

		j.ajax({
			url : "./Calendar_load.s",
			success : function(data) {
				j('#cal_view').empty().prepend(data);
			},
			error : function() {
				alert('error');
			}
		})//달력 load ajax end 



		j(".datepicker").datepicker({
			dateFormat : "yy-mm-dd",
			minDate : 0
		})
		
		
		j(document).on("submit", "#Schedule_make", function() {
			j.ajax({
				type : 'post',
				url : "./make_Schedule.s",
				data : j(this).serialize(),
				success : function(data) {

					j.ajax({
						url : "./Calendar_load.s",
						success : function(data) {
							j('#cal_view').empty().prepend(data);

						},
						error : function() {
							alert('error');
						}
					})//달력 load ajax end 

					j("#submit_buttion").css("display", "none")
				},
				error : function() {
					alert("error!!!!");
				},
				complete : function() {
					j("#pocket_view").css({
						'display' : 'block'
					})
					j(".submit_button").val("일정이 생성되었습니다.").css({
						'width' : '200px'
					});
					j(".submit_button").attr('disabled', 'true');
					j("input[name='sch_title']").attr('readonly', 'true');
					j("input[name='sch_content']").attr('readonly', 'true');
					j("#sch_make_start").datepicker('disable');
					j("#sch_make_end").datepicker('disable');
					j(".datepicker_start").datepicker({
						dateFormat : "yy-mm-dd",
						minDate : j("#sch_make_start").val(),
						maxDate : j("#sch_make_end").val()
					})
					j(".datepicker_end").datepicker({
						dateFormat : "yy-mm-dd",
						minDate : j("#sch_make_start").val(),
						maxDate : j("#sch_make_end").val()
					})
				}
			})

			return false;
		})
			
		
		
							
		j(document).on("click", ".submit_travel_button", function() {
		/* 	alert('click');
			var c = $(this).attr('id');
			console.log(c)
			console.log('travel_no'+$('#travel_no_'+c).val())
			console.log('det_start'+$('#start_'+c).val())
			console.log('det_end'+$('#end_'+c).val())
			console.log('det_content'+$('#content_'+c).val()) */
			var c = $(this).attr('id');
			j.ajax({
				typt : 'post',
				url : "./make_detail.s",
				data : {'check':'travel',
						'travel_no':$('#travel_no_'+c).val(),
						'det_start':$('#start_'+c).val(),
						'det_end':$('#end_'+c).val(),
						'det_content':$('#content_'+c).val()},
				success : function(data) {
					
					j.ajax({
						url : "./Calendar_load.s",
						success : function(data) {
							j('#cal_view').empty().prepend(data);

						},
						error : function() {
							alert('error');
						}
					})//달력 load ajax end 

				}
			})
			return false;
		})

		j(document).on("click", ".submit_festival_button", function() {
		/* 	alert('click');
// 			var c = $(this).attr('id');
			console.log(c)
			console.log('travel_no'+$('#travel_no_'+c).val())
			console.log('det_start'+$('#start_'+c).val())
			console.log('det_end'+$('#end_'+c).val())
			console.log('det_content'+$('#content_'+c).val() )*/
			var c = $(this).attr('id');
			console.log('det_content'+$('#content_'+c).val())
			j.ajax({
				typt : 'post',
				url : "./make_detail.s",
				data : {'check':'festival',
						'festival_no':$('#festival_no_'+c).val(),
						'det_start':$('#start_'+c).val(),
						'det_end':$('#end_'+c).val(),
						'det_content':$('#content_'+c).val()},
				success : function(data) {
					
					j.ajax({
						url : "./Calendar_load.s",
						success : function(data) {
							j('#cal_view').empty().prepend(data);

						},
						error : function() {
							alert('error');
						}
					})//달력 load ajax end 

				}
			})
			return false;
		})

		/////////////////////////////////////////////////
		j(document).on('click', '.changePagePocketTravel', function() {
			var pagePT = j(this).text().trim();
			if (pagePT == '이전')
				pagePT = parseInt(j("#hiddenPagePocketTravel").val()) - 1
			if (pagePT == '다음')
				pagePT = parseInt(j("#hiddenPagePocketTravel").val()) + 1
			j.ajax({
				method : "post",
				url : "./Schedule_make.s",
				data : {
					"pagePocketTravel" : pagePT,
					"statePocketTravel" : "ajax"
				},
				success : function(data) {

					j("#pocket_Travel").empty().prepend(data);

				},
				error : function() {

					alert('error');
				},
				complete : function() {

					j(".datepicker_start").datepicker({
						dateFormat : "yy-mm-dd",
						minDate : j("#sch_make_start").val(),
						maxDate : j("#sch_make_end").val()
					})
					j(".datepicker_end").datepicker({
						dateFormat : "yy-mm-dd",
						minDate : j("#sch_make_start").val(),
						maxDate : j("#sch_make_end").val()
					})
				}
			})
			return false;

		})
		////////////////////////////////////////////////////////

		j(document).on('click', '.changePagePocketFestival', function() {
			var pagePF = j(this).text().trim();
			if (pagePF == '이전')
				pagePF = parseInt(j("#hiddenPagePocketFestival").val()) - 1
			if (pagePF == '다음')
				pagePF = parseInt(j("#hiddenPagePocketFestival").val()) + 1

			j.ajax({
				method : "post",
				url : "./Schedule_make.s",
				data : {
					"pagePocketFestival" : pagePF,
					"statePocketFestival" : "ajax"
				},
				success : function(data) {
					j("#pocket_Festival").empty().prepend(data);

				},
				error : function() {
					alert('error');
				},
				complete : function() {

					j(".datepicker_start").datepicker({
						dateFormat : "yy-mm-dd",
						minDate : j("#sch_make_start").val(),
						maxDate : j("#sch_make_end").val()
					})
					j(".datepicker_end").datepicker({
						dateFormat : "yy-mm-dd",
						minDate : j("#sch_make_start").val(),
						maxDate : j("#sch_make_end").val()
					})
				}
			})
			return false;
		})

		j(document).on('click', '.show_make_travel', function() {

			var a = 'show_make_travel_' + j(this).attr('id');
			var b = j("#" + a).css('display');
			if (b == 'none') {
				j(this).html('△');
				j("#" + a).css('display', 'table-row');
			}
			if (b == 'table-row') {
				j(this).html('▼')
				j("#" + a).css('display', 'none');
			}

			return false;
		})

		j(document).on('click', '.show_make_festival', function() {

			var a = 'show_make_festival_' + j(this).attr('id');
			var b = j("#" + a).css('display');
			if (b == 'none') {
				j(this).html('△');
				j("#" + a).css('display', 'table-row');
			}
			if (b == 'table-row') {
				j(this).html('▼')
				j("#" + a).css('display', 'none');
			}

			return false;
		})

	})
</script>
<title>일정 생성 페이지</title>
</head>
<body>

	<jsp:include page="/MainHeader.jsp" />
	<div class="pic"><img src="./Schedule/css/pic10.jpg" style="width:1500px;height:350px"></div>
	<div id='content'>
		<div id="empty" class="box">
			&nbsp;
			<div id="cal_view"></div>
		</div>
		
		<div id="map_view"><div id="map"></div></div>
		
		<div id='make_schedule'>
			<fieldset>
				<legend>[일 정]</legend>
				<table class='make_table' id='make_schedule'>
					<form id="Schedule_make">
						<tr>
							<td><input type="text" name="sch_title"
								placeholder="일정 제목을 입력하세요"
								style='width: 500px; height: 25px; text-align: center'>
							</td>
						</tr>
						<tr>
							<td><textarea name="sch_content"
									placeholder="여행에 대한 메모를 작성해 주세요"
									style='width: 500px; height: 250px; text-align: center'></textarea>
							</td>
						</tr>
						<tr>
							<td><input type="text" id='sch_make_start'
								class="datepicker" name="start_date" placeholder="Start">
								~ <input type="text" id='sch_make_end' class="datepicker"
								name="end_date" placeholder="End"></td>
						</tr>
						<tr>
							<td><input class="submit_button" type="submit" value="추 가">
								<!-- <input type="button" value="삭제" onClick="location.href='./MySchedule.s'"> -->
							</td>
						</tr>

					</form>

				</table>


			</fieldset>
			<div id="pocket_view">

				<fieldset>
					<legend>[장바구니목록]</legend>

					<div id="pocket_Travel">
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
					</div>
					<br> <br>
					<div id="pocket_Festival">

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


					</div>
					<br>





				</fieldset>
			</div>


		</div>



		<div id="buttons_make">
			<input type="button" class='make_button' value="장바구니"
				onClick="location.href='./MySchedule.s'"> <input
				type="button" class='make_button' value="내 일정 보기"
				onClick="location.href='./MyScheduleManage.s?Schedule_choice=all'">
		</div>

	</div>
</body>
</html>