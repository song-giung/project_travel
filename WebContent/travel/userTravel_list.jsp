<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta>
<title>테마별 여행지 리스트</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
	
	
	body{
		overflow: auto;
	}
	h3{
		padding-left: 1px; display: inline-block;
	}
	
	#typesearch{
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
  margin-top: 3px;
}
#typesearch:hover{
  background:#fff;
  color:#1AAB8A;
}
#typesearch:before,#pocketplus:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #1AAB8A;
  transition:400ms ease all;
}
#typesearch:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
#typesearch:hover:before,#pocketplus:hover:after{
  width:100%;
  transition:800ms ease all;
  }
  
  #typesearch{
  	border-radius: 10px;
  	}
  	
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
</style>

<script>
	$(document).on("click","#typesearch",(function(){
		
		var type= new Array();
		var tema = $("#tema").val();
		
		$("input[name='type']:checked").each(function(index,item){
			type[index]=$(this).val();
			
		})
		
		$.ajax({
			type:'get',
			data : {"type":type ,"tema":tema},
			url:"./usertypelist.tr",
			success:function(data){
				
				$('#list_table').empty().prepend(data);
			}
		})
	}))
	$(document).on("click","#travel_list",(function(){
		
		var user_id =$('#user_id').val();
		var user_no=$('#user_no').val();
		
		if(user_no==""){
			alert("로그인후 이용가능합니다.");
			return false;
		}else{
		$(this).children('.t_list').submit();
		}
		
	}))

</script>

</head>
<body>
	<%@ include file="/MainHeader.jsp"%>
	<div id="head"></div>
	<input type="hidden" id="user_no" value="${sessionScope.user_no}">
	<input type="hidden" id="user_id" value="${sessionScope.user_id}">
	<form>
	<p style="text-align: center">
	<label><input id="margintop" type="checkbox" name="type"  value="1">모험 추구 여행&nbsp;<img height="26px" width="26px" src="img/adv_icon.png"></label>&nbsp;|
	&nbsp;<label><input type="checkbox" name="type" value="2">경험 추구 여행&nbsp;<img height="26px" width="26px" src="img/exp_icon.png"></label>&nbsp;|
	&nbsp;<label><input type="checkbox" name="type" value="3">안정 추구 여행&nbsp;<img height="26px" width="26px" src="img/vac_icon.png"></label>&nbsp;|
	&nbsp;<label><input type="checkbox" name="type" value="4">감각 추구 여행&nbsp;<img height="26px" width="26px" src="img/ses_icon.png"></label>
	<input type="button" id="typesearch" value="검색" >
	</p>
	<input type="hidden" name="tema" id="tema" value="${tema}">
	</form>
	<table style="width: 80% ;height: 100%;margin: 0 auto">
	<div class="container">
	<tbody id="list_table">
	<c:set var ="i" value="0"/>
	<c:set var ="j" value="3"/>
	<div class="row">
	<c:forEach var="travel" items="${travellist }">
		<c:if test="${i%j ==0 }">
		<tr>
		</c:if>
		<td>
			<div class="list" id="travel_list" style="width: 380px; height: 300px; border-radius: 10px;text-align: center;">
			<form class="t_list" action="./userTravel_info.tr">
			<input type="hidden" id="no" name="no" value="${travel.travel_no }">
			<div class="w3-card" style="border-radius: 10px;">
			<img src="./travelUpload/${travel.travel_img_name }" style="width: 100%;height: 250px;border-radius: 10px;">
				<div class="w3-container" style="height: 50px">
					<h4><b>${travel.travel_name }</b></h4>
					
			</div>
			</div>
			
			</form>
			</div>
		</td>
		<c:if test="${i%j== j-1}">
		</tr>
		</c:if>
		<c:set var="i" value="${i+1 }"/>
	</c:forEach>
	</div>
	</tbody>
	</div>
	</table>
</body>
</html>