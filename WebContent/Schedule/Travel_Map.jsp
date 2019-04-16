<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>다음 지도 API</title>
</head>
<body>
	<div id="map"></div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41e91c5e42294975805254152247805e&autoload=false&libraries=services"></script>
	<script type="text/javascript">
		
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
				map
						.setCenter(new daum.maps.LatLng(36.18501,
								127.77231)), map.setLevel(13)
			})

			// 지도 타입 변경 컨트롤을 생성한다
			var mapTypeControl = new daum.maps.MapTypeControl();

			// 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
			map.addControl(mapTypeControl,
					daum.maps.ControlPosition.TOPRIGHT);

			// 지도에 확대 축소 컨트롤을 생성한다
			var zoomControl = new daum.maps.ZoomControl();

			// 지도의 우측에 확대 축소 컨트롤을 추가한다
			map
					.addControl(zoomControl,
							daum.maps.ControlPosition.RIGHT);

			// 지도 중심 좌표 변화 이벤트를 등록한다
			daum.maps.event
					.addListener(map, 'center_changed',
							function() {
								console.log('지도의 중심 좌표는 '
										+ map.getCenter().toString()
										+ ' 입니다.');
							});

			// 지도 확대 레벨 변화 이벤트를 등록한다
			daum.maps.event.addListener(map, 'zoom_changed',
					function() {
						console.log('지도의 현재 확대레벨은 ' + map.getLevel()
								+ '레벨 입니다.');
					});

			// 지도 영역 변화 이벤트를 등록한다
			daum.maps.event
					.addListener(
							map,
							'bounds_changed',
							function() {
								var mapBounds = map.getBounds(), message = '지도의 남서쪽, 북동쪽 영역좌표는 '
										+ mapBounds.toString() + '입니다.';

								console.log(message);
							});

			// 지도 시점 변화 완료 이벤트를 등록한다
			daum.maps.event.addListener(map, 'idle', function() {
				var message = '지도의 중심좌표는 ' + map.getCenter().toString()
						+ ' 이고,' + '확대 레벨은 ' + map.getLevel()
						+ ' 레벨 입니다.';
				console.log(message);
			});

			// 지도 클릭 이벤트를 등록한다 (좌클릭 : click, 우클릭 : rightclick, 더블클릭 : dblclick)
			daum.maps.event.addListener(map, 'click', function(
					mouseEvent) {
				console.log('지도에서 클릭한 위치의 좌표는 '
						+ mouseEvent.latLng.toString() + ' 입니다.');
			});

			// 지도 드래깅 이벤트를 등록한다 (드래그 시작 : dragstart, 드래그 종료 : dragend)
			daum.maps.event.addListener(map, 'drag', function() {
				var message = '지도를 드래그 하고 있습니다. ' + '지도의 중심 좌표는 '
						+ map.getCenter().toString() + ' 입니다.';
				console.log(message);
			});

			var geocoder = new daum.maps.services.Geocoder();
			///////////////////////////////

			<c:forEach var="item" items="${fest_List}">
			geocoder
					.addressSearch(
							'${item.festival_location} ${item.festival_address}',
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
			geocoder
					.addressSearch(
							'${item.travel_location} ${item.travel_address}',
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
			
	</script>
</body>
</html>
