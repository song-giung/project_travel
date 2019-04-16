<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta>
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
	#image-holder{
		width: 580px;height: 300px
	}
	
	#image-holder  div{
		display: inline-block;
	}
	#img_remove{
		display: block;
	
	}
	#dynamic_table{
		width: 100%;
		padding: 0px
	}
	#first_table tr:nth-child(5) {
	height: 150px
}
	#first_table tr:nth-child(6) {
	height: 150px
}
	#first_table td:nth-child(2) {
	width: 250px
}
	#name{
		width: 100%
	}
	
	textarea{
		width: 100%
	}
	img{
		padding-right: 5px;
		border-radius: 10px;
	}
	th{
		background-color: silver;
		text-align: center;
		border-radius: 8px;
		color:white;
		padding-bottom: 3px;
	}

	.brsilver{
	border: 1px solid silver;
	border-radius: 5px;
	}
	
	.bt{  
      text-decoration: none;
      font-size:2rem;
      color:white;
      padding:10px 20px 10px 20px;
      margin:20px;
      display:inline-block;
      border-radius: 10px;
      transition:all 0.1s;
      text-shadow: 0px -2px rgba(0, 0, 0, 0.44);
      font-family: 'Lobster', cursive; <!-- google font -->
    }
    .bt:active{
      transform: translateY(3px);
    }
    .bt.blue{
      background-color: silver;
     
    }
    .bt.blue:active{
      border-bottom:2px solid #165195;
    }
    
    table{
		margin: 0 auto;
		border-radius: 10px;
		border: 1px solid silver;
		width: 840px;
		height: 750px;
		margin-top: 20px;
	}

</style>


</head>
<body>
	<%@ include file="/MainHeader.jsp"%>
	<table>
		<tr>
			<th>여행지 이름</th>
			<td colspan=3 class="brsilver">${travel.travel_name }</td>
		</tr>
		<tr>
			<th>지역</th>
			<td class="brsilver">${travel.travel_location}</td>
			<th>주소</th>
			<td class="brsilver">${travel.travel_address }</td>
		</tr>
		<tr>
			<th>유형</th>
			<td class="brsilver">
			<c:forEach var="type" items="${t_type }">
			${type.travel_type }
			</c:forEach>
			</td>
			<th>테마</th>
			<td class="brsilver">${travel.travel_tema }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3" class="brsilver">
				${travel.travel_content }
			</td>
		</tr>
		<tr>
			<th>대표 이미지</th>
			<td colspan="3" class="brsilver">
				<c:if test="${!empty Firstimg.travel_img_name }">
				<img src="./travelUpload/${Firstimg.travel_img_name }" width="138" height="299">
				</c:if>
			</td>
		</tr>
		
		<tr>
			<th>이미지 리스트 </th>
			<td colspan="3" class="brsilver">
				<c:forEach var="img" items="${t_img }">
					<img src="./travelUpload/${img.travel_img_name }" width="138" height="299">			
				</c:forEach>
			</td>
		</tr>
	
	</table>
	<p style="text-align: center;">
	<a href="./travel_modify.tr?no=${travel.travel_no }" class="bt blue">수정하기 </a>
	<a href="./travel_list.tr" class="bt blue">목록으로 </a>
	</p>
</body>
</html>