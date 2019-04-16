<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41e91c5e42294975805254152247805e&autoload=false&libraries=services"></script>
<link rel="stylesheet" type="text/css" href="./Schedule/css/file_1.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel='stylesheet' href='./Schedule/calendar/fullcalendar.css' />
<script src='./Schedule/calendar/lib/moment.min.js'></script>
<script src='./Schedule/calendar/fullcalendar.js'></script>
<script>
$(function(){
	//list
	//detail
	//pocket
	

	
	
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
			geocoder.addressSearch('${item.festival_address}',
							function(result, status) {
								if (status === daum.maps.services.Status.OK) {
									console.log(status)
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
							' ${item.travel_address}',
							function(result, status) {

								if (status === daum.maps.services.Status.OK) {
									console.log(status)
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		var array = new Array();
		
		<c:forEach var="item" items="${detail}">
			var det = new Object();
			det.id ="${item.user_no}"+"${item.sch_no}"+"${item.detail_no}";
			det.title="${item.det_title}"
			<c:if test="${!empty item.det_start}">		
				det.start="${item.det_start}";
			</c:if>
			<c:if test="${!empty item.det_end}">		
				det.end="${item.det_end}" ;
			</c:if>
			<c:if test="${!empty item.det_date}">		
				det.start="${item.det_date}";
				det.end="${item.det_date}"
			</c:if>
			det.color="#E0F2F1";
			det.allDay =true	
			array.push(det);
		</c:forEach> 
		
			var sch = new Object();
			sch.id = "${list.user_no}"+"${list.sch_no}"
			sch.start = "${list.start_date}" 
			sch.end = "${list.end_date}"  
			sch.title = "${list.sch_title}"
			
			sch.allDay=true
			
			sch.color="teal"
			array.push(sch);
		
		var jsonInfo = JSON.stringify(array);

				
		$("#calendar").fullCalendar({
			lang:'ko',
			header: { left: 'prev,next today', center: 'title', right:'month,basicWeek,basicDay' },
			events: JSON.parse(jsonInfo),
			eventDataTransform: function(event) {                                                                                                                                
				  if(event.allDay) {                                                                                                                                               
				    event.end = moment(event.end).add(1, 'days')                                                                                                                 
				  }
				  return event;  
				}  
		
		})		
		

		
		$(document).on('click','#schedule_del',function(){
			 var ch1 = '';
				$("input:checkbox[name='sch_list']").each(function(){
					if($(this).is(':checked'))
						ch1+=$(this).val()+" ";
				})	
				
			$.ajax({			//일정 지우기
				type : 'post',
				data : {"ch1":ch1},
				url : "./schedule_del.s",

				success : function(data){				
					$('#sch_det').empty().prepend(data);

					$.ajax({ //일정 갱신ajax
						type:'post',
						url : "./detail_reload.s",
						success : function(data){
							$("#detail").html(data)
							/* $("#detail").empty().prepend(data) */
						
						},
						error : function(){
							alert("/detail_reload.s 에러");
						}				
					
					})//ajax end
				
					
					//달력 새로고침 ajax 시작
					$.ajax({
							url : "./Calendar_load.s",
							success : function(data){		
								$('#cal_view').empty().prepend(data);
							},
							error : function(){
								alert('error');
							}
						})
					
				},
				error : function(){
					alert('AjaxDelSchedule error');
				}
			})//ajax end
			
			

		
		})//일정 삭제 버튼 end
		
		$("#detail_del").click(function(){	
			
			var ch2 ='';
			$("input:checkbox[name='det_list']").each(function(){
				if($(this).is(':checked'))
					ch2+=$(this).val()+" ";
				
			})
			
			$.ajax({
				type:'post',
				data:{"ch2":ch2},
				
				url :"./detail_del.s",

				success : function(data){
					$("#detail").empty().prepend(data)
				
				},
				error : function(){
					alert("AjaxDelDetail error");
				}				
			})//ajax end
		 
			$.ajax({
				url : "./Calendar_load.s",
				success : function(data){		
					$('#cal_view').empty().prepend(data);
				},
				error : function(){
					alert('error');
				}
			}) 
			
	
	})
		

  	$("#calendar").fullCalendar('gotoDate','${list.start_date}')
	
	
		
			
	 $(document).on('click','.changePageDT',function(){
			var pageDT = $(this).text().trim();		
			if(pageDT=='이전')
				pageDT = parseInt($("#hiddenPageDT").val())-1
			if(pageDT=='다음')
				pageDT =parseInt($("#hiddenPageDT").val())+1
	
		 	$.ajax({
				url: "./MyScheduleManage.s",
				data:{"pageDT":pageDT,"stateDT":"ajax","Schedule_choice":"${sch_no}"},
				success: function(data){
					
					$('#detail').empty().prepend(data);
					
				},
				error:function(){
					alert('error');
				}
			})
			 
		}) 
		
	$("#update_sch_content").submit(function(){
		
		$.ajax({
			url:"./Managepage_make_review.s",
			data:$(this).serialize()
		})
		
		
		return false;
	})
		
		
	})//function end
</script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/MainHeader.jsp"/> 
<div class="pic"><img src="./Schedule/css/pic10.jpg" style="width:1500px;height:350px"></div>
<div id="content">
 	<div id="empty" class="box">&nbsp;
		<div id="cal_view">
			<div id="calendar" class="box1"></div>
		</div>
	</div>
	
	 <div id="map_view" class="map_view_manage">
	 	<div id="map"></div>
	 </div>
	
	
	<div id="schedule_Manage_view" class="box">
			<fieldset>
				<legend>[일 정 목 록]</legend>
				<div>
					<form method="post" action="./MyScheduleManage.s" >
						<select name="Schedule_choice" size="1" style="width:250px"  class='select_box'>
						<option value="all"> 일정을 선택하세요 </option>
						<c:forEach var='m' items='${scheduleList}'>
						<option value="${m.sch_no}">${m.sch_title} 기간 : ${m.start_date}~${m.end_date} </option>
						</c:forEach>				
						</select>
						<input type="submit" id="select_view" value="보기">
					</form>
				</div>	
				<div id ="sch_det">
					<table class="manage_table">
					<thead>
						<tr>
							<td>&nbsp;</td>
							<td>일정 번호</td>
							<td>일정 이름</td>
							<td>시작일</td>
							<td>종료일</td>
						</tr>
					<thead>
					<tbody>
						<tr>
							<td><input type="checkbox" class="sch_list" name="sch_list" value="${list.sch_no}"></td>
							<td>${list.sch_no}</td>
							<td>${list.sch_title}</td>
							<td>${list.start_date}</td>
							<td>${list.end_date}</td>
						</tr>
						<tr>
						<form id="update_sch_content" method="post" action="./Managepage_make_review.s">
						<td colspan='5'>-일정에 대한 메모를 수정하여 나만의 후기를 만들어 주세요-<br><textarea name ="sch_content"  style="width:500px;height:200px">${list.sch_content}</textarea>
						<input type="hidden" name="sch_no" value="${list.sch_no}">
						
						
						<br><br>
						<input type="submit" id="manage_make_review" value="후기 작성하기">
						</td>
						</form>
						</tr>
					</tbody>

					</table>
				</div>
						<br>
						<input type="button" value="공유하기 " id="schedule_share" onClick="location.href='./ListPage.schbo'">
						<input type="button" value="삭제" id="schedule_del">						
			</fieldset>				
		</div>
		
				<div id="detail_Manage_view" class="box">
			<fieldset>
				<legend>[세부일정 목록]</legend>	
				<div id="detail">
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
				</div>
					<br>
						<input type="button" value="삭제" id="detail_del"> 				
			</fieldset>				

		</div>
	
	
	<div id="button_manage">
	<input type="button" class='manage_button' value="장바구니" onClick="location.href='./MySchedule.s'">
	<input type="button" class='manage_button' value="일정 등록하기" onClick="location.href='./Schedule_make.s'">
	</div>
</div>
</body>
</html>