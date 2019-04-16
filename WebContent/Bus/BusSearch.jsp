<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function(){
		$("#bus_area").change(function(event){
			 event.preventDefault();
				var senddata = "area=" + $(this).val();	
			 output='';
			//var data = $("bus_area").serialize(); input인 경우
			$.getJSON('select_start.bus',senddata,function(rdata){
				$("#bus_start").empty();
				$("#bus_end").empty();
				$("#output").empty();
				output +='<option></option>'
				$(rdata).each(function(index){
					output += '<option value=\"' + this.bus_start + '\">';
					output += this.bus_start +'</option>';
				})//function end
				console.log(output);
					$('#bus_start').append(output);
			})//JSON end						
		})//click end		
		
		$("#bus_start").change(function(event){
			var startdata = "bus_start=" + $(this).val();	
			output='';
			$.getJSON('select_end.bus', startdata,function(data){
				$("#bus_end").empty();
				output +='<option></option>'
				$(data).each(function(index){					
					output += '<option value=\"' + this.bus_end + '\">';
					output += this.bus_end +'</option>';
				})//function end
				$('#bus_end').append(output);
			})//JSON end
		})//change end	
		
		$("#bus_end").change(function(envent){
			var enddata = "bus_end=" + $(this).val();
			$.getJSON('select_no.bus', enddata, function(data){	
				$(data).each(function(index){
					$("#delete").val(this.bus_no);
					$("#hidden").val(this.bus_no);
				})
			})//JSON end
		})//change end
		$("#delete").click(function(event){
			event.preventDefault();
			var con = confirm("지역 : " + $("#bus_area").val() + "\n"
					+ "출발지 : " + $("#bus_start").val() + "\n"
					+ "도착지 : " + $("#bus_end").val() + "\n"
					+ "해당 버스 노선을 삭제하시겠습니까?");
			if(con==true){
				var nodata = "bus_no=" + $("#delete").val();	
				location.href='BusDelete.bus?'+ nodata;
			}			
		})//click end
		
	})//ready end
	
</script>
<style>
	body{
		margin : 0px;
		background-color : #a6e6f0;
	}
	form{
		border-radius : 5px;
		width : 300px;
		height : 170px;
		margin : auto;
		text-align : center;
		background-color : white;
	}
	h3{text-align:center}
	table {
    width: 100%;
    margin-bottom : 10px;
	}
	tr, td{ 
	padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
	}
	th{
		text-align : center
	}
</style>
</head>
<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>
	<h3>버스 노선 검색</h3>
	<form action="BusModify.bus" method="post">
		<table>
			<tr height=30px>
				<th width=100px >지역</th>
				<td><select id="bus_area" name="bus_area">
				<option></option>
				<option value="Seoul">서울</option>
				<option value="Incheon">인천</option>
				<option value="Daejeon">대전</option>
				<option value="Busan">부산</option>
				<option value="Daegu">대구</option>
				<option value="Gwangju">광주</option>
				<option value="Ulsan">울산</option>
				<option value="Sejong">세종</option>
				<option value="Jeju">제주</option>
				<option value="Gyeonggi">경기</option>
				<option value="Gangwon">강원</option>
				<option value="Chungbuk">충북</option>
				<option value="Chungnam">충남</option>
				<option value="Jeonbuk">전북</option>
				<option value="Jeonnam">전남</option>
				<option value="Gyeongbuk">경북</option>
				<option value="Gyeongnam">경남</option>
			</select></td>
			</tr>
			<tr height=30px>
				<th>출발지</th>
				<td><select id="bus_start" name="bus_start">
				</select></td>
			</tr>
			<tr height=30px>
				<th>도착지</th>
				<td><select id="bus_end" name="bus_end">
				</select></td>
			</tr>
		</table>
				<input type="hidden" name="hidden" id="hidden">
				<input type="submit" value="수정">&nbsp;
				<button name="delete" id="delete">삭제</button>
	</form>
</body>
</html>