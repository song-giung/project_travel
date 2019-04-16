<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta>
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
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
	}


</style>


<script>

$(document).ready(function() {
	$('.img_change').hide();
	$('#sample4_roadAddress').hide();
	var location = $('#trvael_location').val();
	$("#location").val(location).prop("selected", true);
	
});
var count = 1;
var addCount;

function addInputBox() {
	 for(var i=1; i<=count; i++) {
	  if(!document.getElementsByName("test"+i)[0]) {
	   addCount = i;
	   break;
	  }
	  else addCount = count;
	 }

	
	 if(addCount>4){
		 alert("이미지는 4개만 추가 가능합니다.")
		 
	 }else{
	 var addStr = "<tr><td width=100%><input type=radio name=imgType value=test"+addCount+" size=40>"
	 		+"<input type=checkbox name=checkList value="+addCount+" size=40 >"
			+"<input type=file name=test"+addCount+" size=40 onchange='javascript:imageURL(this);'></td></tr>";
	var table = document.getElementById("dynamic_table");
	var newRow = table.insertRow();
	var newCell = newRow.insertCell();
	 newCell.innerHTML = addStr;
	 
	 var id="test"+addCount;
	
	 if(!$("div#image-holder").is('.'+id)){
	 	$("div#image-holder").append("<img id=test"+addCount+" class=test"+addCount+"/>");
	 }else{
		 alert("있음");
	 }
	 count++;
	 }
}
	 
	 
	//행삭제
	function subtractInputBox() {
	 var table = document.getElementById("dynamic_table");
	 //var max = document.gForm.checkList.length;
	 //alert(max);
	 var rows = dynamic_table.rows.length;
	 var chk = 0;
	 if(rows > 1){
	  for (var i=0; i<document.gForm.checkList.length; i++) {
	   if (document.gForm.checkList[i].checked == true) {
			
		  var no =document.gForm.checkList[i].value;
		  var id="test"+no;
		  $('#'+id).remove();
		
	    table.deleteRow(i);
	    i--;
	    count--;
	    chk++;
	   }
	  }
	  if(chk <= 0){
	   alert("삭제할 행을 체크해 주세요.");
	  }
	   }else{
	    alert("더이상 삭제할 수 없습니다.");
	   }
	}
	
	
	
	var sel_file;
	
	
	 function imageURL(input) {
		 var id=input.name;
		
		 
		 var fileValue = input.value.split("\\");
		 var fileName = fileValue[fileValue.length-1]; // 파일명

		 var extn=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
			
			
		 if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
	        
			 if(fileName.indexOf("[")==-1 || fileName.indexOf("]")==-1 ){
			 
			 if (input.files && input.files[0]) {
	            var reader = new FileReader();

	            reader.onload = function(e) {
	            	$('#'+id).attr({
	            		"src": e.target.result,
                        "id": id,
                        "width":"138px",
                        "height":"299px",
                        "data-scale":"best-fit-down"
	            		
	            	})
	            
	            }

	            reader.readAsDataURL(input.files[0]);
	          }
			 
			 }else{
				 alert("파일명에 [ 또는 ] 이 포함된 파일명은 업로드할 수 없습니다..");
				 $(input).val("");
			 }
		
		 
		 }else{
				 alert("지원하지않는 형식입니다.")
				 $(input).val("");
				 $('#'+id).remove();
				 $("div#image-holder").append("<img id="+id+" class="+id+"/>");
		}
	 }
	 
	 function address(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

		            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
		            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
		            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

		            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                extraRoadAddr += data.bname;
		            }
		            // 건물명이 있고, 공동주택일 경우 추가한다.
		            if(data.buildingName !== '' && data.apartment === 'Y'){
		               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		            }
		            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		            if(extraRoadAddr !== ''){
		                extraRoadAddr = ' (' + extraRoadAddr + ')';
		            }
		            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
		            if(fullRoadAddr !== ''){
		                fullRoadAddr += extraRoadAddr;
		            }

		            // 우편번호와 주소 정보를 해당 필드에 넣는다.
		          
		            document.getElementById('sample4_roadAddress').value = fullRoadAddr;
		            document.getElementById('sample4_jibunAddress').value = data.jibunAddress;
					
		            $('#add').attr("value",data.jibunAddress);
		          
		        }
		 	   }).open();
			}


	 function changeimg(){
		 
		 if(confirm("이미지를 변경하시겠습니까? 이미지를변경하지않으면 기존이미지가 유지됩니다.")==true){
			 $('.img_origin').remove();
			 $('.img_change').show();
		 }
		 
	 }

	 
	 
	 $(function() {
	 	var maxDate = new Date();
	 
	 	$("#startdate").datepicker({
	 	     onSelect: function(selected){
	 	        $("#enddate").datepicker("option", "minDate", selected)},
	 	     showOn: "both", // 버튼과 텍스트 필드 모두 캘린더를 보여준다.
	 	  
	 	     changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
	 	     changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
	 	     
	 	     nextText: '다음 달', // next 아이콘의 툴팁.
	 	     prevText: '이전 달', // prev 아이콘의 툴팁.
	 	     numberOfMonths: [1,1], // 한번에 얼마나 많은 월을 표시할것인가. [2,3] 일 경우, 2(행) x 3(열) = 6개의 월을 표시한다.
	 	     //stepMonths: 3, // next, prev 버튼을 클릭했을때 얼마나 많은 월을 이동하여 표시하는가. 
	 	     yearRange: 'c-100:c+10', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
	 	     showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
	 	     currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널
	 	     closeText: '닫기',  // 닫기 버튼 패널
	 	     dateFormat: "yy-mm-dd", // 텍스트 필드에 입력되는 날짜 형식.
	 	     showAnim: "slide", //애니메이션을 적용한다.
	 	     showMonthAfterYear: true , // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다. 
	 	     dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
	 	     monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] // 월의 한글 형식.
	 	                       
	 	    })    
		    
		 $("#enddate").datepicker({
		     showOn: "both", // 버튼과 텍스트 필드 모두 캘린더를 보여준다. 
		     changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
		     changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
		     nextText: '다음 달', // next 아이콘의 툴팁.
		     prevText: '이전 달', // prev 아이콘의 툴팁.
		     numberOfMonths: [1,1], // 한번에 얼마나 많은 월을 표시할것인가. [2,3] 일 경우, 2(행) x 3(열) = 6개의 월을 표시한다.
		     //stepMonths: 3, // next, prev 버튼을 클릭했을때 얼마나 많은 월을 이동하여 표시하는가. 
		     yearRange: 'c-100:c+10', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
		     showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
		     currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널
		     closeText: '닫기',  // 닫기 버튼 패널
		     dateFormat: "yy-mm-dd", // 텍스트 필드에 입력되는 날짜 형식.
		     showAnim: "slide", //애니메이션을 적용한다.
		     showMonthAfterYear: true , // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다. 
		     dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
		     monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], // 월의 한글 형식.
		    })
	 
	 });

</script>
</head>
<body>
	<%@ include file="/MainHeader.jsp"%>
	<h1 style="text-align: center">축제 정보 수정</h1>
		<input type="hidden" value="${festival.location }">
	<form name="gForm" action="./travel_modifyAction.tr" enctype="multipart/form-data" method="post">
	<table >
		<tr>
				<th>축제 이름</th>
				<td colspan="3" class="brsilver"><input type="text" name="name" id="name"
					placeholder="축제 이름을 입력하세요." value="${festival.festival_name }"></td>
			</tr>
			<tr>
				<th>지역</th>
				<td class="brsilver"><select name="location">
						<option value="서울" selected="selected">서울</option>
						<option value="부산">부산</option>
						<option value="인천">인천</option>
						<option value="경기도">경기도</option>
						<option value="전라남도">전라남도</option>
						<option value="전라북도">전라북도</option>
						<option value="경상남도">경상남도</option>
						<option value="경상북도">경상북도</option>
						<option value="충청남도">충청남도</option>
						<option value="충청북도">충청북도</option>
						<option value="강원도">강원도</option>
						<option value="제주도">제주도</option>
				</select></td>
				<th>주소</th>
				<td>
				<input type="text" id="sample4_roadAddress" placeholder="도로명주소" style="width: 100%;" readonly><br>
				<input type="text" id="sample4_jibunAddress" placeholder="지번주소" style="width: 100%;" readonly value="${festival.festival_address }"><br>
				<input type="button" onclick="address()"
				style="background-color: silver;border-radius: 5px;color:white;"value="우편번호 찾기">
				&nbsp;&nbsp;&nbsp;※실제 주소값은 지번주소만 저장됩니다※
				<span id="guide" style="color:#999"></span>
				</td>
			</tr>
			<tr>
				<th>축제 기간</th>
				<td colspan="3" class="brsilver">
				<input type="text" class="search_date" size="14" id="startdate" value="${festival.startday }" name="startdate" readonly> -
               	<input type="text" class="search_date" size="14" id="enddate" value="${festival.endday }" name="enddate" readonly>
				 </td>
			</tr>
		<tr>
			<th>내용</th>
			<td colspan="3" class="brsilver">
			<textarea name="textarea" rows="10" cols="100" placeholder="Write something here">${festival.festival_content }</textarea>
			</td>
		</tr>
		
	
			
		<tr class="img_origin">
			<th>대표 이미지</th>
			<td colspan="3" class="brsilver">
				<c:if test="${!empty Firstimg.festival_img_name }">
				<img src="./festivalUpload/${Firstimg.festival_img_name }" width="138" height="299">
				</c:if>
			</td>
		</tr>
		
		<tr class="img_origin">
			<th>이미지 리스트 </th>
			<td colspan="3" class="brsilver">
				<c:forEach var="img" items="${f_img }">
					<img src="./festivalUpload/${img.festival_img_name }" width="138" height="299">			
				</c:forEach>
			</td>
		</tr>
	
		<tr class="img_change">
				<th>이미지 </th>
				<td class="brsilver">
				<input type="button" value="이미지 추가" style="background-color: silver;border-radius: 5px;color:white;" onclick="javascript:addInputBox();"> :
				<input type="button" value="이미지 삭제" style="background-color: silver;border-radius: 5px;color:white;" onclick="javascript:subtractInputBox();" >
				<p><img src="./img/radio.png" width="50px" height="25px"><span style="color:blue;">대표이미지</span></p>
				<p><img src="./img/check.png" width="50px" height="25px"><span style="color:red;">삭제</span></p>
				</td>
				<td colspan="2" class="brsilver">
				<table id="dynamic_table" >
				</table>
				</td>
			</tr>
			
		<tr class="img_change">
				<th>이미지 리스트</th>
				<td colspan="3" class="brsilver">
					<div id="image-holder">				
					
					</div>
				</td>
			</tr>
			
		<tr class="img_origin">
			<td colspan="4"><p style="text-align: center;"><input type="button" id="img_col" onclick="javascript:changeimg();" value="이미지 변경하기"
			style="background-color: silver;border-radius: 5px;color:white;"></p></td>
		</tr>
	</table>
	<p style="text-align: center;">
	<a href="./festival_modify.fs?no=${festival.festival_no }" class="bt blue">수정하기 </a>
	<a href="./festival_list.fs" class="bt blue">목록으로 </a>
	</p>
	</form>
</body>
</html>