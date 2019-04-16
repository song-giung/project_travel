<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta>
<title>HEADER</title>
 <!-- <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>  -->

<script>
	/* $(function(){
		$("#login").click(function(){
			window.open("./login.net","login",
					"width=500, height=500, scrollbar=no");
		})//click end
		$("#join").click(function(){
			location.href="./join.net"
		})
		
	})//ready end */
	
	function login(){
		window.open("./login.net","login","width=500, height=470, top=250, left=750, fullscreen=no, resizable=no, scrollbar=no");
	}
	function join(){
		location.href="./join.net"
	}
</script>
<style>
@font-face{font-family:"nanums"; src:url('fonts/NanumSquareR.ttf')} 
@font-face{font-family:"lemon"; src:url('fonts/LemonMilkitalic.otf')} 
@font-face{font-family:"lemonl"; src:url('fonts/LemonMilklight.otf')} 
@font-face{font-family:"nanumsB"; src:url('fonts/NanumSquareR.ttf')}
*{font-family:nanums}
#homebtn{
float : left
}
#homebtn:hover{
	src : "./Client/login.png";
}
#join{
	width : 100px;
	height : 34px;
	margin :8px 28px 8px 8px;
	border-radius : 5px;
	background-color: white; 
    color: black; 
    border: 2px solid #008CBA;
}
#join:hover{
	background-color: #008CBA;
    color: white;
}

.dropbtn {
    background-color: white;
    color: black;
    padding: 16px;
    font-size: 16px;
    height : 50px;
    border: none;
    
}

.dropdown {

   position: relative; 
   top : -17px;
    display: inline-block;
    
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
  
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #ddd}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    box-shadow : 1px 1px 10px 0px gray
}

.homeimg{
	display: inline-block;
}

.id{
   display: inline-block; float:right;
}
.id span:nth-child(1){font-size:22px;}
.id span:nth-child(2){text-align:center}
.full{
   box-sizing : border-box;
   background-color : white;
   padding : 0px;
   margin : 0px;
   height : 53px;
}
</style>
</head>
<body>
<div class="full">

   <div class="homeimg">
 		<a href = "./Mainpage.jsp"><img src ="traveler.png" id="homebtn" height=50px></a>
   </div>
<c:if test="${empty user_id }">
<div class="dropdown">
  <button class="dropbtn">여행지</button>
  <div class="dropdown-content">
    <a href="userFestivallist.fs">축제 리스트</a>
	<a href="userTravelTemalist.tr">여행지 리스트</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">일정</button>
  <div class="dropdown-content">
    <a href="ListPage.schbo">일정 게시판</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">커뮤니티</button>
  <div class="dropdown-content">
    <a href="CommBoardMain.combo">커뮤니티 게시판</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">버스</button>
  <div class="dropdown-content">
    <a href="AreaList.bus">버스 조회</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">고객센터</button>
  <div class="dropdown-content">
    <a href="NoticeList.no">공지 사항</a>
    <a href="BoardList.bo">문의 하기</a>
  </div>
</div>

</c:if>

<c:if test="${!empty user_id }">
	
<c:if test="${user_id!='admin'}">
<div class="dropdown">
  <button class="dropbtn">여행지</button>
  <div class="dropdown-content">
    <a href="userTravelTemalist.tr">여행지 리스트</a>
    <a href="userFestivallist.fs">축제 리스트</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">일정</button>
  <div class="dropdown-content">
    <a href="ListPage.schbo">일정게시판</a>
    <a href="MySchedule.s">장바구니</a>
    <a href="Schedule_make.s">일정 등록하기</a>
    <a href="MyScheduleManage.s?Schedule_choice=all">내 일정 보기</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">커뮤니티</button>
  <div class="dropdown-content">
    <a href="CommBoardMain.combo">커뮤니티 게시판</a>
  </div>
</div>
<div class="dropdown">
  <button class="dropbtn">버스</button>
  <div class="dropdown-content">
    <a href="AreaList.bus">버스 조회</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">고객센터</button>
  <div class="dropdown-content">
    <a href="NoticeList.no">공지 사항</a>
    <a href="BoardList.bo">문의 하기</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">마이 페이지</button>
  <div class="dropdown-content">
    <a href="client_update.net">정보 조회</a>
    <a href="TravelerType.net">여행자 유형이란?</a>
  </div>
</div>
</c:if>

<c:if test="${user_id=='admin' }">
<div class="dropdown">
  <button class="dropbtn">여행지 관리</button>
  <div class="dropdown-content">
    <a href="festival_list.fs">축제 관리</a>
    <a href="travel_list.tr">여행지 관리</a>
    <a href="userFestivallist.fs">축제 리스트</a>
	<a href="userTravelTemalist.tr">여행지 리스트</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">게시판 관리</button>
  <div class="dropdown-content">
    <a href="ListPage.schbo">일정 게시판</a>
    <a href="ListPage.combo">커뮤니티 게시판</a>
  </div>
</div>
<div class="dropdown">
  <button class="dropbtn">회원 관리</button>
  <div class="dropdown-content">
    <a href="client_list.net">회원 리스트 </a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">버스 관리</button>
  <div class="dropdown-content">
    <a href="AreaList.bus">버스 조회</a>
    <a href="BusAdd.bus">버스 추가</a>
    <a href="BusSearch.bus">버스 검색</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">고객센터</button>
  <div class="dropdown-content">
    <a href="NoticeList.no">공지 사항</a>
    <a href="BoardList.bo">문의 하기</a>
  </div>
</div>

</c:if>
</c:if>

<div class="id">
  	<c:if test="${empty user_id }">
		<a href="#" id="login"><img src="login.png" id="homebtn" height=50px onclick="login()"></a>&nbsp;
		<input type="button" id="join" value="회원가입" onclick="join()">
	</c:if>
	<c:if test="${!empty user_id }">
		<c:if test="${user_id=='admin' }">
			<span>관리자 ${user_id} 님</span><br>
			<span>(<a href="logout.net">로그아웃</a>)</span>
		</c:if>
		<c:if test="${user_id!='admin'}">
			<span>일반회원 ${user_id } 님 </span><br>
			<span>(<a href="logout.net">로그아웃</a>)</span>
		</c:if>
	</c:if>
</div>
</div>
</body>
</html>

