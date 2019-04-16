<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
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
	}
	
</style>

<script>

$(document).ready(function() {
	$('.img_change').hide();
	$('#sample4_roadAddress').hide();
	var location = $('#trvael_location').val();
	$("#location").val(location).prop("selected", true);
	
	var tema=$('#travel_tema').val();
	$('.temas').val(tema).prop("checked",true);
	
	
	
	
	var t_type = document.getElementsByName('t_type');
	var type = document.getElementsByName('user_type');
	
	for(var i=0;i<t_type.length;i++){
		
		for(var j=0;j<type.length;j++){
			
			if(t_type[i].value==type[j].value){
				type[j].checked=true;
			}
		}
	}
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

</script>
</head>


<body>
	<%@ include file="/MainHeader.jsp"%>
	<h1 style="text-align: center">여행지 정보 수정</h1>
	<form name="gForm" action="./travel_modifyAction.tr" enctype="multipart/form-data" method="post">
		<input type="hidden" id="trvael_location" value="${travel.travel_location}" >
		<input type="hidden" id="travel_tema" value="${travel.travel_tema }">
		<input type="hidden" name="travel_no" value="${travel.travel_no }">
		<c:forEach var="type"  items="${t_type}">
			<input type="hidden" name="t_type" value="${type.travel_type }">	
		</c:forEach>
		<table id="first_table">
			<tr>
				<th>여행지 이름</th>
				<td colspan="3"><input type="text" name="name" id="name"
					placeholder="여행지 이름을 입력하세요." value="${travel.travel_name }" class="brsilver"></td>
			</tr>
			<tr>
				<th>지역</th>
				
				<td class="brsilver"><select name="location" id="location">
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
				<input type="text" id="sample4_jibunAddress" placeholder="지번주소" style="width: 100%;"
						value ="${travel.travel_address }"readonly><br>
				<input type="button" onclick="address()"
				style="background-color: silver;border-radius: 5px;color:white;"value="우편번호 찾기">
				&nbsp;&nbsp;&nbsp;※실제 주소값은 지번주소만 저장됩니다※
				<span id="guide" style="color:#999"></span>
				<input type="hidden" id="add" name="add" value="${travel.travel_address }">
				</td>
			</tr>
			<tr>
				<th>유형</th>
				<td class="brsilver">
					<input type="checkbox" name="user_type" value="1"/>1
					<input type="checkbox" name="user_type" value="2"/>2
					<input type="checkbox" name="user_type" value="3"/>3
					<input type="checkbox" name="user_type" value="4"/>4
				</td>
				<th>테마</th>
				<td class="brsilver">
					<div>
         				<input type="radio" class="temas" name="tema" value="놀거리"><span>놀거리</span>
        				<input type="radio" class="temas" name="tema" value="먹거리" ><span>먹거리</span>
        				<input type="radio" class="temas" name="tema" value="혼자서" ><span>혼자서</span>
        				<input type="radio" class="temas" name="tema" value="함께하는" ><span>함께하는</span>
        				<input type="radio" class="temas" name="tema" value="관광" ><span>관광</span>
        				<input type="radio" class="temas" name="tema" value="휴양" ><span>휴양</span>
    			   </div>
				
				
				</td>
			</tr>
			
			<tr>
				<th>세부 내용</th>
				<td colspan="3">
				<textarea class="brsilver" name="textarea" rows="10" cols="100" placeholder="Write something here">${travel.travel_content }</textarea>
				</td>
			</tr>
			
			
			
			
	
			
			
			
			
		<tr class="img_origin">
			<th>대표 이미지</th>
			<td colspan="3" class="brsilver">
				<c:if test="${!empty Firstimg.travel_img_name }">
				<img src="./travelUpload/${Firstimg.travel_img_name }" width="138" height="299">
				</c:if>
			</td>
		</tr>
		
		<tr class="img_origin">
			<th>이미지 리스트 </th>
			<td colspan="3" class="brsilver">
				<c:forEach var="img" items="${t_img }">
					<img src="./travelUpload/${img.travel_img_name}" width="138" height="299">			
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
				<td colspan="2">
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
			<td colspan="4">
			<p style="text-align: center;"><input type="button" id="img_col" onclick="javascript:changeimg();" value="이미지 변경하기"
			style="background-color: silver;border-radius: 5px;color:white;"></p>
			</td>
		</tr>
		
		</table>
		
		<p style="text-align: center;">
		<input type="submit" value="등록하기" class="bt blue"> 
		<a href="javascript:history.back()" class="bt blue">뒤로가기</a>
		</p>		
	</form>
	
	
</body>
</html>