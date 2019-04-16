<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta>
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<style>


#head{
	width:1500px;
	height: 350px;
	background-image: url("img/baloon.jpg");
	border-radius:20px;
	background-repeat:no-repeat;
	background-position: center;
	background-size: cover;
	text-align: center;
	margin: 0 auto;
}


.button {
	border-radius: 20px;
   background-color: teal;
    color: white;
    border: 2px solid #008CBA;
    width: 95%;
    height: 95%;
    font-size:40px;

    margin: 0 auto;
    margin-top: 10px;
    margin-left: 10px;
}


.button:hover {
	opacity:0.8;
    color: white; 
}

.container{ font-family:nanums;display:flex; flex-wrap:wrap; flex-direction:column; width:1500px; height:900px; margin:auto;}
.section {width: 49% ; height:280px;}

</style>
</head>
<body>
	<%@ include file="/MainHeader.jsp"%>
	
	<div id="head"></div>
	<div class="container" style="width: 1500px;margin: 0 auto; text-align: center;">
		<div class="section"><input type="button" class="button" value="관광" onclick="location='./userTravelList.tr?tema=관광'"></div>
		<div class="section"><input type="button" class="button" value="휴양" onclick="location='./userTravelList.tr?tema=휴양'"></div>
		<div class="section"><input type="button" class="button" value="혼자서" onclick="location='./userTravelList.tr?tema=혼자서'"></div>
		<div class="section"><input type="button" class="button" value="함께하는" onclick="location='./userTravelList.tr?tema=함께하는'"></div>
		<div class="section"><input type="button" class="button" value="먹거리" onclick="location='./userTravelList.tr?tema=먹거리'"></div>
		<div class="section"><input type="button" class="button" value="놀거리" onclick="location='./userTravelList.tr?tema=놀거리'"></div>
	</div>
</body>
</html>