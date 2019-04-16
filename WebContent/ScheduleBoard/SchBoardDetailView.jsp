<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ include file="../MainHeader.jsp" %>
  <%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41e91c5e42294975805254152247805e&autoload=false&libraries=services"></script>
<c:set var="cb" value="${sbdata}"/> 
<script src="http://code.jQuery.com/jquery-latest.js"></script>
<link rel='stylesheet' href='./Schedule/calendar/fullcalendar.css' />
<script src='./Schedule/calendar/lib/moment.min.js'></script>
<script src='./Schedule/calendar/fullcalendar.js'></script>
<script>
$(function(){
	////////////////지도 생성 하기 ///////////////////////////
	///sch_bean(일정 1건) / List_detBean(세부일정)/// fest_List , trav_List(세부 일정에 관련된 여행 축제내용 가져오기) 
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
			console.log('${item.festival_location} ${item.festival_address}')
			geocoder.addressSearch('${item.festival_location} ${item.festival_address}',
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
							'${item.travel_location} ${item.travel_address}',
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
			

	//////////////지도 생성 종료///////////////////////
	
	//////////////달력 생성 //////////////////////////
	
	var array = new Array();
		
		<c:forEach var="item" items="${List_detBean}">
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
			sch.id = "${sch_bean.user_no}"+"${sch_bean.sch_no}"
			sch.start = "${sch_bean.start_date}" 
			sch.end = "${sch_bean.end_date}"  
			sch.title = "${sch_bean.sch_title}"
			
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
				},
			
		
		})		
		

	
	
	
	
	/////////////달력 생성 종료/////////////////////////
	
	
	
	

	
	$('#golist').click(function(){
		history.back();
		/* location.href="./ListPage.schbo?${sessionScope.url}" */
	})
	
	//up버튼 눌럿을 떄 ! 넘겨줄 변수 : upcheck(up햇었는지 ! false: 햇엇음, true: 안햇엇음), sch_bo_no(게시판 번호)
	$(document).on('click','.upbtn',(function(){
		var sch_bo_no=$("#sch_bo_no").val();
		var upcheck=$(".upbtn").val();
		var downcheck=$(".dwnbtn").val();
	
		if(downcheck=='false'){
			alert("down 한 게시글입니다.");
			return false;
		}
		
		$.ajax({
			type:'post',
			data:{"sch_bo_no":sch_bo_no,"upcheck":upcheck, "downcheck":downcheck},
			url:"./upAction.updown",
			success :function(data){
				console.log(data)
				$("#up_down_section").empty().prepend(data);
				
			},
			error : function(){
				alert('error');
			}
		})
	}))
	
	//down버튼 눌렀을 떄
	$(document).on('click','.dwnbtn',(function(){
		var sch_bo_no=$("#sch_bo_no").val();
		var upcheck=$(".upbtn").val();
		var downcheck=$(".dwnbtn").val();
		
		if(upcheck=='false'){
			alert("up 한 게시글입니다.");
			return false;
		}
		$.ajax({
			type:'post',
			data:{"sch_bo_no":sch_bo_no,"downcheck":downcheck, "upcheck":upcheck},
			url:"./downAction.updown",
			success :function(data){
				$("#up_down_section").empty().prepend(data);
				
			},
			error : function(){
				alert('error');
			}
		})
	}))
	
	//댓글 입력
	$('#insertComt').click(function(){
		$("#nocomts").remove();
		
		var user_no=$("#user_no").val();
		var sch_bo_no=$("#sch_bo_no").val();
		var sch_comt_cont=$("#comment").val();
		
		
		if(sch_comt_cont==""){
			alert("댓글내용을 입력해 주세요!");
		}
		$.ajax({
			type:'post',
			data:{'sch_bo_no':sch_bo_no, 'user_no':user_no, 'sch_comt_cont':sch_comt_cont},
			url: "./sch_comt_insert.schcomt",
			success : function(data){
			
	               $("#clean").empty().prepend(data); 
				  	var comtcount=$("#clean").find("#comt_count").val();
				  	 $("#comt_count").text(comtcount); 
				  	 
				  	 
			},
	            error : function(){
	               alert('error');
	            }
		})	
		
		$("#comment").val("");
	})
	
	
	$(document).on("click", ".a",function(e){
		e.preventDefault();
		var a=$(this).text().trim();
		var page=$("#page").val();
		var realpage=0;
		
		if(a=="이전"){
			realpage=parseInt(page)-1;
			}
		else if(a=="다음"){
			realpage=parseInt(page)+1;
			console.log(realpage);
		}else
			realpage=a;
		
		var sch_bo_no=$("#sch_bo_no").val();
		
		$.ajax({
			type:'post',
			data:{'sch_bo_no':sch_bo_no, 'page':realpage},
			url: "./ListComt.schcomt",
			success : function(data){
			
	               $("#clean").empty().prepend(data); 
				  
			},
	            error : function(){
	               alert('error');
	            }
		})	

	})
	
	$(document).on("click", '.comtDelete', function(){
	
		if($("#page").val()!=null)
			var a=$("#page").val();
		else
			var a=1;
		
		var user_no=$("#user_no").val();
		var sch_bo_no=$("#sch_bo_no").val();
		var sch_comt_no=$(this).val();
		
		$.ajax({
			type:'post',
			data:{'sch_bo_no':sch_bo_no, 'user_no':user_no, 'sch_comt_no':sch_comt_no, 'page':a},
			url: "./sch_comt_delete.schcomt",
			success : function(data){
				
	               $("#clean").empty().prepend(data); 
				  	var comtcount=$("#clean").find("#comt_count").val();
				  	 $("#comt_count").text(comtcount); 
				  	if(comtcount%10==1){
				  		
				  	}
			},
	            error : function(){
	               alert('error');
	            }
		})	
		
		$("#comment").val("");
	})

})



</script>

<style>
@font-face{font-family:"nanums"; src:url('fonts/NanumSquareR.ttf')}
@font-face{font-family:"nanumsB"; src:url('fonts/NanumSquareR.ttf')}

hr{border:0;border-top:1px solid rgba(0,0,0,.1)}
 body{font-family:"nanums"; margin :0px;}
  .container{margin: auto; display:flex; flex-direction:column; width:1500px; }
  
 #elsecomts{display:flex; flex-flow:wrap;align-content:flex-start;width:1500px; }
	 .first{ display:flex; flex-flow:wrap; margin:10px 0px 0px 0px; width:100%; height:10% }
 	#title{border-radius:10px; box-shadow: 5px 10px 8px lightslategray; border:1px solid lightgray; font-family:"nanumsB"; font-color:gray margin:0; letter-spacing:4px; text-align:left; line-height:100px; font-size:40px; width:84%; height:100%}
  	.dates{  font-family:"nanums"; margin:auto; text-align:center; font-size: 20px; line-height:50px; height:100%}
  	
  	.second{margin: 15px 0px;  display:flex; flex-direction:row; font-family:"nanums"; line-height:50px;  width:100%;}
  	#content{margin:0px 30px; width:84%; font-family:"nanums"; font-size:25px; }
  	.userinfo{border-radius:10px;  box-shadow: 5px 10px 8px lightslategray; line-height:100px; border:1px solid lightgray; margin: 15px 10px; text-align:center; font-size:20px; font-family:"nanums"; width:16% ; /* height:40% */}
  	
  	#update_delete{width:84%; text-align:right}
  	#commentsboard{text-align:center; width:98%;}
	#clean{width:100%;margin:auto;font-size:20px; text-align:center}
	
	#commentview{margin:auto; font-size:20px; text-align:center}
	#commentview td:nth-child(1){width:20%}
	#commentview td:nth-child(2){width:60%}
	#commentview td:nth-child(3){width:20%}
	
	
	#clean tr{height:40px;}
	#clean td{border-bottom: 1px solid lightgray;}
	span{color:gray}
	.ccimg{line-height:26px}
	summary{font-size:20px}
	button img{width:40px; height:40px;background-color:white;}
	.udbtn{width:80px; height:80px; background-color:white; border-radius:100%; }
	.btns {
	  border-radius:12px;
	  background-color: #ddd;
	  border: none;
	  color: black;
	  padding: 16px 32px;
	  text-align: center;
	  font-size: 16px;
	  margin: 4px 2px;
	  transition: 0.3s;
	}

	.btns:hover {
	  border-radius:12px;
	  background-color: teal;
	  color: white;
	}
	#up_down_section{
		border-spacing: 30px 4px;
		margin:auto;
		margin-top:20px;
	text-align:center}
	
	#up_down_section .ccimg{
		width:40px; height:40px;
	}
	
	/*  지도 달력 컨텐츠  */
	#share_sch{
	 display: flex;
    flex-wrap:wrap;}
	#map{
		
		width:47%;
    	height: 600px;
    	margin-top:10px;
    	margin-left:5%
    }
    #calendar{
    	width:47%;
    	margin-top:10px
    }
    #content_sch{
    	
    	
    }
    
    .sch_table {
    	margin-top:20px;
    	text-align:center;
    	border-radius:13px;
    	border:1px groove gray;
    	width:100%
    }
    
    #sch_section{
    	margin-top:30px;
    	border : dotted gainsboro 3px;
    	border-radius:30px;
    	padding:15px;
    }
    .fc-title{
    	color:darkgrey;
    }
 	#compic{
		width:1500px; height:350px; 
		background:url("commboardimgs/schboardimgs1.jpg");
		background-size:cover;
		 background-repeat: no-repeat;
		border-radius:20px;
		}

	
    
</style>
<head>
<title>일정게시판-상세보기(${cb.getSch_bo_title() })</title>
</head>
<body>
	<div class="container">
		<div id="compic" onClick="location.href='./ListPage.schbo'"></div>
		
		<div id="elsecomts">
		</div>
		
			<div class="first">
				<div id="title"><span>&nbsp;&nbsp;글 제목 |</span>${cb.getSch_bo_title() } </div>
				<div class="dates">작성일|${cb.getSch_bo_date() }</div>
			</div>

			<div id="sch_section">
			<div id="share_sch" width="100%">
					<!-- 일정 들어감 -->
					<div id="calendar"></div>
					<div id="map">지도</div>	
			</div>
			<div id="content_sch">
					<table class="sch_table">
						<thead>
							<tr>
								<th>이름</th>
								<th>기간</th>
								
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${sch_bean.sch_title}</td>
								<td>${sch_bean.start_date}~${sch_bean.end_date }</td>
							</tr>
							<tr>
								<th colspan='2'>후기</th>
							</tr>
							<tr>
								<td colspan='2'>${sch_bean.sch_content }</td>
							</tr>
						</tbody>
					</table>
					<br><br>
					<table class="sch_table">
						<thead>
							<tr>
								<th>세부 일정 이름</th>
								<th>세부 일정 내용</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var='a' items='${List_detBean}'>
								<tr>
									<td>${a.det_title }</td>
									<td>${a.det_content }</td>
								</tr>
							</c:forEach>
						</tbody>
					
					</table>
					</div>
			</div>

			<div class="second">
				<div id="content">${cb.getSch_bo_content()}</div>
				<div class="userinfo">
					<div id="userinfo_id"> 작성자 | ${cb.getUser_id()}</div>
					<div id="user_type">
					 <c:if test="${cb.getUser_type()==1}"><img class="ccimg" height="150px" width="150px" src="img/adv_icon.png"> <div>모험 추구 여행자</div> </c:if>
					 <c:if test="${cb.getUser_type()==2}"><img class="ccimg" height="150px" width="150px" src="img/exp_icon.png"> <div>경험 추구 여행자 </div></c:if>
					 <c:if test="${cb.getUser_type()==3}"><img class="ccimg" height="150px" width="150px" src="img/vac_icon.png"><div> 안정 추구 여행자 </div></c:if>
					 <c:if test="${cb.getUser_type()==4}"><img class="ccimg" height="150px" width="150px" src="img/ses_icon.png"> <div>감각 추구 여행자 </div></c:if>
					</div>
				</div>
			</div>
			

			
			<div id="commentsboard">
				<div id="update_delete">
						<c:if test="${cb.user_no==sessionScope.user_no||'admin'==sessionScope.user_id}"> 
								 <a href="./SchBoardDelete.schbo?num=${cb.sch_bo_no}">삭제</a>  &nbsp;&nbsp;
						</c:if>
						<button type="button" id="golist">목록</button> 
				</div>
			 	
			 
			 
			<table id="up_down_section">
				<tr>
					<td colspan="4"><c:if test="${upcheck==false}"><button  type="button" class="upbtn udbtn" value="${upcheck}"><img src="img/hold.png"></button></c:if>
				 					<c:if test="${upcheck==true }"><button   type="button" class="upbtn udbtn" value="${upcheck}"><img src="img/up.png"></button></c:if>
					 </td>
					<td colspan="4"><c:if test="${downcheck==false}"><button  type="button" class="dwnbtn udbtn" value="${downcheck}"><img src="img/hold.png"></button></c:if>
				 					<c:if test="${downcheck==true }"><button  type="button" class="dwnbtn udbtn" value="${downcheck}"><img src="img/down.png"></button></c:if>
					 </td>
				</tr>
				<tr>
					<td colspan="4">up(${uplist.getUp_total_count()})  </td>
					<td colspan="4">down(${downlist.getDown_total_count()}) </td>
				</tr>
				<tr>
					<td colspan="2"><img class="ccimg"  src="img/adv_icon.png"></td>
					<td colspan="2"><img class="ccimg"  src="img/exp_icon.png"></td>
					<td colspan="2"><img class="ccimg"  src="img/vac_icon.png"></td>
					<td colspan="2"><img class="ccimg" src="img/ses_icon.png"> </td>
				</tr>
				<tr>
					<c:forEach var="typecount" items="${downlist.getDown_type_count()}" varStatus="index">
							 <td colspan="2"> up : ${uplist.getUp_type_count()[index.index]} / down:  ${typecount}</td>
					</c:forEach>
				</tr>
			</table>
			
			
			
			 	<details id="show_comments">
				<summary>댓글(<span id="comt_count">${sch_comt_count}</span>)</summary>
				
				<table id="clean">
					<input id="page" type="hidden" value="${page}"/>
					<c:if test="${sch_comt_count>0}"> 	<!-- 댓글 있는 경우 -->
					<c:forEach var="ccb" items="${scb}">
						<tr>
						<td>${ccb.user_id}
							 <c:if test="${ccb.getUser_type()==1}"><img height="26px" width="26px" src="img/adv_icon.png"> </c:if>
							 <c:if test="${ccb.getUser_type()==2}"><img height="26px" width="26px" src="img/exp_icon.png"> </c:if>
							 <c:if test="${ccb.getUser_type()==3}"><img height="26px" width="26px" src="img/vac_icon.png"></c:if>
							 <c:if test="${ccb.getUser_type()==4}"><img height="26px" width="26px" src="img/ses_icon.png"> </c:if>
						</td>
						<td>${ccb.sch_comt_cont}</td>
						<td>${ccb.sch_comt_date}
							<c:if test="${ccb.user_no==sessionScope.user_no||'admin'==sessionScope.user_id}"> 
							<!-- 댓글 수정도 나중에 하자  ||<button type="button" id="comtModify">수정</button> -->
							||<button type="button" class="comtDelete"  value="${ccb.sch_comt_no}" >삭제</button> </c:if>
						</td>
						</tr>
				    </c:forEach>
				    </c:if>
				
					<c:if test="${sch_comt_count==0}"> <!-- 댓글 없는 경우 -->
						<div id="nocomts">등록된 댓글이 없습니다.</div>
					</c:if> 
				<!-- 댓글 페이징 -->
				   <tr class="h30 lime center btn">
					<td colspan=3>
						<c:if test="${page <=1}">
						 이전 &nbsp;
						</c:if>
						<c:if test="${page >1 }">
							<a href="./ListComt.schcomt?page=${page-1}" class="a">이전</a>
						</c:if>
							
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a==page}">
								${a}
							</c:if>
							<c:if test="${a!=page}">
							
							 <%-- <button type="button" value="${a}" class="a">${a}</button>  --%>
							 <a href="./ListComt.schccomt?page=${a}" class="a">${a}</a> 
							</c:if>
						</c:forEach>
						
						<c:if test="${page >= maxpage }">
							&nbsp; 다음
						</c:if>
						<c:if test="${page <maxpage }">
							<a href="./ListComt.schcomt?page=${page+1}" class="a">&nbsp;다음</a>
						</c:if>
						</td>
						
						</tr>
				</table>
				
				
				<!-- 로그인 되어있는 상태 @@ sessionScope.id? -->
				<form id="cmform">
				<table id="commentview">
					<tr>
					<td >댓글달기 | <input type="hidden" name="user_no" id="user_no" value="${sessionScope.user_no}"><!-- 아이디!!!!user_no!! -->
								<input type="hidden" name="sch_bo_no" id="sch_bo_no" value="${sch_bo_no}"></td>
					<td ><input size="100px" type="text" name="comment" id="comment" style='height:35px'></td>
					<td><button type="button" class="btns" id="insertComt" name="commentinsert">댓글달기</button></td>
					</tr>
				</table>
				</form>
				</details>
			
			</div>
	
			
			
	
	</div>
</body>
</html>