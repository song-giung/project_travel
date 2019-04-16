<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta>
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<style>
	#pocketplus{
  background:#1AAB8A;
  color:#fff;
  border:none;
  position:relative;
  height:60px;
  font-size:1.6em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
  margin-left: 140px;
  margin-top: 3px;
}
#pocketplus:hover{
  background:#fff;
  color:#1AAB8A;
}
#pocketplus:before,#pocketplus:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #1AAB8A;
  transition:400ms ease all;
}
#pocketplus:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
#pocketplus:hover:before,#pocketplus:hover:after{
  width:100%;
  transition:800ms ease all;
}
ul{
	list-style: none;
}
	#map_li{
		border: 1px solid silver; border-radius: 5px; height: 650px;width: 1300px;
	}
	.navBulletsWrapper{
		display: block;
	}
	
	body{
		font-family: "돋움", dotum, "굴림", gulim, arial, helvetica, sans-serif;
	}
	.w3-card{
		display: inline-block;height: 347px;float: left;
	}
	#up{
		border:2px solid skyblue;
		background-color:white;
		border-radius:8px;
		width: 80px;
		height:40px;
		text-align:center;
	}
	#down{
		border:2px solid pink;
		background-color: white;
		
		border-radius:8px;
		width: 80px;
		height:40px;
		text-align:center;
	}
</style>
	<link href="./themes/1/js-image-slider.css" rel="stylesheet" type="text/css" />
 	<script src="./themes/1/js-image-slider.js" type="text/javascript"></script> 
 	<link href="./generic.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ca54db42b85d3d636e505e5c38cf5103&libraries=services"></script>

<script>
$( document ).ready(function() {
var address =$('#address').val();
var travel_name = $('#name').val();

if($('#upcheck').val()=="1"){
	$('#up').val("추천중!");
	}
	
if($('#downcheck').val()=="1"){
	$('#down').val("비추천중!");
	
}


var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch(address, function(result, status) {

	   if (status === daum.maps.services.Status.OK) {

	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new daum.maps.Marker({
	            map: map,
	            position: coords
	        });

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new daum.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+travel_name+'</div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
    } 
	});  
});

$(document).on("click","#pocketplus",(function(){
	
	var no=$("#no").val();
	var user_no =$("#user_no").val();
	
	if(user_no==""){
		alert("로그인을 해주세요");
	}else{
	$.ajax({
		
		type:'get',
		data : {"no":no,"user_no":user_no},
		url : "./travel_pocketplus.tr",
		success:function(data){
			alert("장바구니에 담기 성공!");
		}
	}) 
	}
}))


	$(document).on("click","#up",(function(){
		 var upcheck = $('#upcheck').val();
		 var downcheck = $('#downcheck').val();
		 var no = $('#no').val();
		 var user_no=$('#user_no').val();
		 
		 if(upcheck=="0" && downcheck=="0"){
			 
			 if (confirm("해당 여행지를 추천하겠습니까?") == true){    
				   
				    
				    
				    $.ajax({
						
						data : {"travel_no":no,"user_no":user_no},
						url:"./travel_upadd.tr",
						success:function(data){
							$('#updown_counter').empty().append(data);
						},
						error : function( jqXHR, textStatus, errorThrown ) {

							alert( jqXHR.status );

							alert( jqXHR.statusText );

							alert( jqXHR.responseText );

							alert( jqXHR.readyState );

							}
					})
					 $('#upcheck').val("1");
				    $('#up').val("추천중!");
				}
		 }
		 
		 if(upcheck=="1" && downcheck=="0"){
			 
			 if(confirm("해당 여행지 추천을 취소하겠습니까?") == true){
				 $('#upcheck').val("0");
				 $('#up').val("추천!");
				 
				    $.ajax({
						
						data : {"travel_no":no,"user_no":user_no},
						url:"./travel_updelete.tr",
						success:function(data){
							$('#updown_counter').empty().append(data);
						},
						error : function( jqXHR, textStatus, errorThrown ) {

							alert( jqXHR.status );

							alert( jqXHR.statusText );

							alert( jqXHR.responseText );

							alert( jqXHR.readyState );

							}
					})
				 
			 }
			 
		 }
		 
		 if(upcheck=="0" && downcheck=="1"){
			 alert("추천 비추천중 하나만 선택가능합니다");
		 }
	
		
		
	}))

		
	function down(){
	 var upcheck = $('#upcheck').val();
	 var downcheck = $('#downcheck').val();
	 var no = $('#no').val();
	 var user_no=$('#user_no').val();
	 
	 if(upcheck=="0" && downcheck=="0"){
		 
		 if (confirm("해당 여행지를 비추천하겠습니까?") == true){    
			    $('#downcheck').val("1");
			    $('#down').val("비추천중!");
			    
			    $.ajax({
					
					data : {"travel_no":no,"user_no":user_no},
					url:"./travel_downadd.tr",
					success:function(data){
						$('#updown_counter').empty().append(data);
					},
					error : function( jqXHR, textStatus, errorThrown ) {

						alert( jqXHR.status );

						alert( jqXHR.statusText );

						alert( jqXHR.responseText );

						alert( jqXHR.readyState );

						}
				})
			}
		 
	 }
	 
	 if(upcheck=="0" && downcheck=="1"){
		 
		 if(confirm("해당 여행지 추천을 취소하겠습니까?") == true){
			 $('#downcheck').val("0");
			 $('#down').val("비추천!");
			 $.ajax({
					
					data : {"travel_no":no,"user_no":user_no},
					url:"./travel_downdelete.tr",
					success:function(data){
						$('#updown_counter').empty().append(data);
					},
					error : function( jqXHR, textStatus, errorThrown ) {

						alert( jqXHR.status );

						alert( jqXHR.statusText );

						alert( jqXHR.responseText );

						alert( jqXHR.readyState );

						}
				})
			 
		 }
		 
	 }
	 
	 if(upcheck=="1" && downcheck=="0"){
		 alert("추천 비추천중 하나만 선택가능합니다");
	 }

	}
</script>
    
    
<body>
	<%@ include file="/MainHeader.jsp"%>
	<input type="hidden" id="address" name="address" value="${travel.travel_address }">
	<input type="hidden" id="name" name="name" value="${travel.travel_name }">
	<input type="hidden" id="no" name="no" value="${travel.travel_no }">
	<input type="hidden" id="user_no" name="user_no" value="${sessionScope.user_no }">
	<h1 style="text-align: center;">${travel.travel_name }</h1>
	<ul style="width: 1300px; height: 80%; margin: 0 auto;">
	<li style="display:block;height: 350px;">
	<div style="display: block; width: 100%; margin: 0 auto;" >
		<c:if test="${!empty imglist }" >
		<div id="sliderFrame" style="display:inline; width: 800px;" >
				<div id="slider" style="width: 700px; float:left;">
					<c:forEach var="img" items="${imglist }">
						<img src="./travelUpload/${img.travel_img_name }" width="700px" height="306px" >
					</c:forEach>
				</div>
		</div>
		</c:if>
		<c:if test="${!empty img }">
			<div style="display:inline; width: 800px;" >
				<div style="width: 700px; float:left;">
				<c:forEach var="img" items="${img}">
						<img src="./travelUpload/${img.travel_img_name }" width="700px" height="306px">
						</c:forEach>
				</div>
		</div>
		</c:if>
		<div class="travel_content" style="width:43%;height:100%; float: right;">
		<div style="width:580px;height:80%;border:1px solid silver;border-radius: 5px;overflow: auto;">
		1.주소지 :${travel.travel_address }<br><br>
		<br><span>2.여행지 소개글</span><br>${travel.travel_content }
		</div>
			<input type="button" id="pocketplus" value="여행 장바구니에 추가하기" onclick="add()" style="align-items: center;">
		</div>
	</div>
	</li>
	<div id="updown">
	<li id="up_down" style="width: 1300px;height: 450px;border: 1px solid silver; border-radius: 5px;">
		<div id='up_downlist' style="width: 90%;height: 450px; margin: 0 auto">
			<p style="text-align: center">
				
				<input type="button" id="up" value="추천!">
				<input type="button" id="down" value="비추천!" onclick="down()">
			</p>
		<div id="updown_counter">
		<input type="hidden" id="upcheck"value="${up_check }">
		<input type="hidden" id="downcheck"value="${down_check }">
		<div class="w3-card" style="border-radius: 10px;width: 25%;margin:0 auto;">
			<img src="./img/1.png" style="border-radius: 10px;height: 297px;width: 280px;">
				<div class="w3-container" style="height: 50px">
					<span style="font-size:large;">Up : ${u1 }  &nbsp;Down : ${d1 }</span>
					
			</div>
		</div>
		
		<div class="w3-card" style="border-radius: 10px;width: 25%;margin:0 auto;">
			<img src="./img/2.png" style="border-radius: 10px; height: 297px; width: 280px;">
				<div class="w3-container" style="height: 50px">
					<span style="font-size:large;">Up : ${u2 }  &nbsp;Down : ${d2 }</span>
					
			</div>
			</div>
			
		<div class="w3-card" style="border-radius: 10px;width: 25%;margin:0 auto;">
			<img src="./img/3.png" style="border-radius: 10px; height: 297px; width: 280px;">
				<div class="w3-container" style="height: 50px">
					<span style="font-size:large;">Up : ${u3 } &nbsp;Down : ${d3 }</span>
					
			</div>
			</div>
			
		<div class="w3-card" style="border-radius: 10px;width: 25%;margin:0 auto;">
			<img src="./img/4.png" style="border-radius: 10px; height: 297px; width: 280px;">
				<div class="w3-container" style="height: 50px">
					<span style="font-size:large;">Up : ${u4 } &nbsp;Down : ${d4 }</span>
					
			</div>
		</div>
		</div>
		</div>
	</li>
	</div>
	<li id="map_li">
	<div id="map" style="width:90%;height:650px; margin: 0 auto">
	</div>
	</li>

	</ul>
</body>
</html>