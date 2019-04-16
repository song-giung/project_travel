<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta>
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
	#location{margin:0 auto;width:1500px;text-align: center;}
	body{font-family:nanums}
	.btn{
		background-color: teal;color:white;border-radius: 10px; height:50px; width:114px;
	}
	.btn:hover{
		opacity:0.8;
	}
	
	
	#head{
	width:1500px;
	height: 350px;
	background-image: url("img/festheader.jpg");
	border-radius:20px;
	background-repeat:no-repeat;
	background-position: center;
	background-size: cover;
	text-align: center;
	margin: 0 auto;
} 
	.w3-card{
	margin: 14px; auto;
	}
</style>
<script>
	function search(e){
		var location =e;

		
		$.ajax({
			type:'get',
			data:{"location":location,"state":"state"},
			url:"./userfestival_location.fs",
			success:function(data){
				$('#list_festival').empty().prepend(data);
			}
			
		})
	}
	$(document).on("click","#festival_list",(function(){
		
		if($('#user_no').val()==""){
			
			alert("로그인후 이용가능합니다.");
			return false;
		}else{
		$(this).children('.f_list').submit();
		}
	}))
	
	

</script>
</head>
<body>
<%@ include file="/MainHeader.jsp"%>
<input type="hidden" id="user_no" value="${sessionScope.user_no }">
<div id="head"></div>
	<div id="location" style="margin-top: 20px; margin-bottom: 20px">
		<input type="button" class="btn" value="서울" onclick="search(this.value);">
		<input type="button" class="btn" value="부산" onclick="search(this.value);">
		<input type="button" class="btn" value="경기도" onclick="search(this.value);">
		<input type="button" class="btn" value="인천" onclick="search(this.value);">
		<input type="button" class="btn" value="전라남도" onclick="search(this.value);">
		<input type="button" class="btn" value="전라북도" onclick="search(this.value);">
		<input type="button" class="btn" value="경상남도" onclick="search(this.value);">
		<input type="button" class="btn" value="경상북도" onclick="search(this.value);">
		<input type="button" class="btn" value="충청남도" onclick="search(this.value);">
		<input type="button" class="btn" value="충청북도" onclick="search(this.value);">
		<input type="button" class="btn" value="강원도" onclick="search(this.value);">
		<input type="button" class="btn" value="제주도" onclick="search(this.value);">
		
		
	</div>
	<table style="width: 80% ;height: 100%;margin: 0 auto">
	<tbody id="list_festival">
	<c:set var ="i" value="0"/>
	<c:set var ="j" value="3"/>
		<c:forEach items="${festivalList}" var="fs">
		
		 		 <c:if test="${i%j ==0 }">
				<tr>
				</c:if>
				<td>
		
				 	<div  id="festival_list" style="width: 380px; height: 300px; border-radius: 10px;text-align: center;">
					<form class="f_list" action="./userFestival_info.fs" style="width:500px;">
					<input type="hidden"  name="no" value="${fs.festival_no }">
					<div class="w3-card" style="border-radius: 10px; width: 100%">
					<img src="./festivalUpload/${fs.festival_img_name }" style="width: 100%;height: 250px;border-radius: 10px;">
						<div class="w3-container" style="height: 50px">
							<h4><b>${fs.festival_name }</b></h4>
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
		
	</tbody>
	</table>
</body>
</html>