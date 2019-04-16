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
			$('#select_start').empty();
			var area = "area=" + $(this).val();
			output='';		
			$.getJSON('select_start.bus', area, function(data){
				output +='<option></option>'
				$(data).each(function(index){
					output += '<option value=\"' + this.bus_start + '\">';
					output += this.bus_start +'</option>';
				})//each end
				$("#select_start").append(output);
			})//JSON end
		})//change end
		$("#select_start").change(function(){
			$("#bus_start").val($("#select_start").val());
		})//change end
		$("#addline").click(function(){
			output='';
			output +='<tr>';
			output += '<td><input type=\"text\" name=\"bus_seq"></td>';
			output += '<td><input type=\"text\" name=\"bus_departure"></td>';
			output += '<td><input type=\"text\" name=\"bus_arrival"></td>';
			output +='</tr>';
			$("#tbody").append(output);
		})//click end
		$("#deleteline").click(function(){
			$('#addschedule > tbody:last > tr:last').remove();
		})//click end
		
	})//ready end
</script>
<style>
	body{
		margin : 0px;
		background-color : #a6e6f0;
	}
	h3{
		text-align : center;
	}
	fieldset{
		width : 650px;
		margin:auto;
		background-color : white;
	}
</style>
</head>
<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>
<h3>버스 노선 추가</h3>
<fieldset>
<form name="BusAdd" action="BusAddAction.bus" method="post">
	<table>
		<tr>
			<th>지역</th>
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
		<tr>
			<th>노선 이름</th>
			<td><input type="text" name="bus_linename"></td>
		</tr>
		<tr>
			<th>출발지</th>
			<td><input type="text" id="bus_start" name="bus_start">
			<select id="select_start" name="select_start">
			</select></td>
		</tr>
		<tr>
			<th>도착지</th>
			<td><input type="text" id="bus_end" name="bus_end">
		</tr>
		<tr>
			<th>이동 시간</th>
			<td><input type="text" name="bus_traveltime"></td>
		</tr>
		<tr>
			<th>성인 비용</th>
			<td><input type="text" name="bus_cost_ad"></td>
		</tr>
		<tr>
			<th>청소년 비용</th>
			<td><input type="text" name="bus_cost_st"></td>
		</tr>
		<tr>
			<th>아동 비용</th>
			<td><input type="text" name="bus_cost_ch"></td>
		</tr>
	</table>
	<table id="addschedule">
		<tr>
			<th>순서</th><th>출발 시간</th><th>도착 시간</th>
			<th><input type="button" id="addline" value="추가">
			<input type="button" id="deleteline" value="삭제"></th>
		</tr>
	<tbody id="tbody">
		<tr>
			<td><input type="text" name="bus_seq" value="1"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq" value="2"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq" value="3"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq" value="4"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq"  value="5"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq" value="6"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq" value="7"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq" value="8"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq" value="9"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
		<tr>
			<td><input type="text" name="bus_seq" value="10"></td>
			<td><input type="text" name="bus_departure"></td>
			<td><input type="text" name="bus_arrival"></td>
		</tr>
	</tbody>
	</table>
	<input type="submit" value="노선 추가">
	<input type="reset" value="초기화">
</form>
</fieldset>
</body>
</html>