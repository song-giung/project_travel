<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta>
<title>관리자 축제 리스트</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="icon" type="image/png" href="/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/travel/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/travel/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/travel/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/travel/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/travel/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/travel/css/util.css">
	<link rel="stylesheet" type="text/css" href="/travel/css/main.css">
<script>
	function deletefestival(){
		var chk = document.getElementsByName("check");
		var index =0;
		var vals ='';
		var array = new Array();
		
		 for(var i=0;i<chk.length;i++){
			if(chk[i].checked == true){
				vals = chk[i].value;
				
				array[index]=vals;
				
				index++;        //체크된 체크박스의 개수
			}
			
		} 
		$.ajax({
	        url: "./festival_delete.fs",
	        type:"post", 
	        data: {"array":array},
	        success: function(result){
	            alert("삭제되었습니다.");
	     
	            location.reload();
	        }
	    })

	
		
	}
	
	function asc(){
		
		var order = $('#order').val();
		
		if(order=="0"){
			
			$.ajax({
				 url: "./festival_list2.fs",
			        type:"post", 
			        data: {"state":"asc"},
			        success: function(data){
			
			           $('#order_list').empty().prepend(data);
			        }
				
			})
			
			$('#order').val("1");
		}
		
		if(order=="1"){
			
			$.ajax({
				 url: "./festival_list2.fs",
			        type:"post", 
			        success: function(data){
			 
			        	$('#order_list').empty().prepend(data);
			        }
				
			})
			
			$('#order').val("0");
		}
	}
	
	
</script>
</head>
<body>
	<%@ include file="/MainHeader.jsp"%>
	<input type="hidden" value="0" id="order">
	
	<h1 style="text-align: center; margin-right: 250px;margin-top: 10px;">축제 리스트</h1>
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
						<table>

							<thead>
								<tr class="row100 head">
									<th class="cell100 column1">선택</th>
									<th class="cell100 column2"><span onclick="asc()">번호</span></th>
									<th class="cell100 column3">축제 이름</th>
									<th class="cell100 column4">지역</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
			<div class="table100-body js-pscroll">
				<table>
					<tbody id="order_list">
			
					<c:forEach var="festival" items="${festivalList}">
						<tr class="row100 body">
							<td class="cell100 column1">
								<input type="checkbox" name="check" value="${festival.festival_no }">
							</td>
							<td class="cell100 column2">
								${festival.festival_no}
							</td>
							<td class="cell100 column3">
								<a href="./festival_info.fs?no=${festival.festival_no}"> ${festival.festival_name}</a>
							</td>
							<td class="cell100 column4">
								${festival.location }
							</td>
						</tr>
					</c:forEach>

					</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<p style="text-align: center;margin-right: 250px;">
	<a href="./festival_write.fs" style="color:white;background-color: #6c7ae0; border-radius: 10px; width: 130px;padding-right: 15px;padding-left:15px; ">축제 추가</a>
	<a href="#" id="d_btn" onclick="javascript:deletefestival();" style="color:white;background-color: #6c7ae0;border-radius: 10px;width: 130px; padding-right: 15px;padding-left:15px;">축제 삭제</a>
	</p>
	
	<script src="/travel/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/travel/vendor/bootstrap/js/popper.js"></script>
	<script src="/travel/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/travel/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/travel/js/main.js"></script>
	
</body>
</html>