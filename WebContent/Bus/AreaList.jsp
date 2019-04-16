<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import = "javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<title>지역</title>

<style>
	body{ 

	margin : 0px;
		}
	fieldset{
	width : 600px;
	height : 150px;
	background-color : white;
	border-radius : 5px;
	border : solid black 3px;
	margin: 40px auto 20px auto;
	}	
	#bus_area button{
		width : 90px;
		height : 40px;
		margin : 3px;
		border-radius : 5px;
		background-color : #00bcd487; 
	}
	#bus_area button:hover{
		background-color : #00bcd436;
	}
	#bar{
	text-align : center;
	margin-bottom : 10px;
	}
	#select{
	display : inline-block;
	}
	#select option{
		width:50px;
	}
	#scheduletable{
		border-collapse : collapse;
		text-align : center;
		background : white;
		margin : auto;
		margin-bottom : 200px;
	}
	#thead{
		background-color : #abaeac9c
	}
	#search {
  		display: inline-block;
  		border-radius: 4px;
  		background-color: #00bcd487;
  		border: none;
  		color: black;
  		text-align: center;
  		font-size: 15px;
  		padding: 4px;
 	 	width: 50px;
 	 	transition: all 0.5s;
		cursor: pointer;
 		margin: 1px;
	}

	#search span {
	  cursor: pointer;
	  display: inline-block;
  	 position: relative;
	  transition: 0.5s;
	}

	#search span:after {
	  content: '\00bb';
	  position: absolute;
	  opacity: 0;
 	 top: 0;
 	 right: -10px;
 	 transition: 0.5s;
	}

#search:hover span {
  padding-right: 10px;
	}

	#search:hover span:after {
 	 opacity: 1;
 	 right: 0;
		}
	#under{
		margin : auto;
		background-repeat: no-repeat; 
		width : 100%;
		height : 400px;
		background-image : url("./Bus/under.jpg");
		background-position: bottom center;
		margin-top: 20px;
	}
</style>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function(){		
		$("#bus_area button").click(function(event){
			 event.preventDefault();
				var senddata = "area=" + $(this).attr('value');	
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
					$("#search").val(this.bus_no);
				})
			})//JSON end
		})//change end
		
		$("#select button").click(function(event){
			event.preventDefault();
			$('#output').empty();
			var nodata = "bus_no=" + $("#search").val();	
			output='';
			$.getJSON('search.bus', nodata,function(data){
				$(data).each(function(index){
					
					output += '<tr>';
					output += '<td>'+this.bus_seq+'</td>';
					output += '<td>'+this.bus_linename+'</td>';
					output += '<td>'+this.bus_departure+'</td>';
					output += '<td>'+this.bus_traveltime+'</td>';
					output += '<td>'+this.bus_arrival+'</td>';
					output += '<td>'+this.bus_cost_ad+'</td>';
					output += '<td>'+this.bus_cost_st+'</td>';
					output += '<td>'+this.bus_cost_ch+'</td>'; 
					/* output += '<td><button type="button" name="add" id="add" value="'+ this.bus_seq +'">일정추가</td>'; */
					/* output += '<td><a href ="./ScheduleAddAction.bus?area='+this.bus_area+'&no='
							+this.bus_no+'&seq='+this.bus_seq+'">일정 추가</a></td>'; */
					/* output += '<td><a href ="./ScheduleAddAction.bus?seq='+this.bus_seq+'" id="add">일정 추가</a></td>'; */
					output += '<td id="none"><a href ="#" name="'+this.bus_seq +'"id="add">일정 추가</a></td>';
					output += '</tr>';
					output += '</tr>';
				})//function end
				$('#output').append(output);
			})//JSON end
		
		})//click end
	
		
	
		$("#output").on("click","#add", function(e){
			e.preventDefault();	
			<%
			if(session.getAttribute("user_id") == null){%>
				alert("로그인을 해주세요");
				return false;
				<%}%>
			var address = "./ScheduleAddAction.bus?seq=" + $(this).attr("name")+"&date="+$("#datepicker").val();
			var seq =$(this).attr("name");
			var date=$("#datepicker").val();
			/* window.open( address,"add","width=500 height=500");  */
			
			
			$.ajax({
				type:'get',
				url:'./ScheduleAddAction.bus',
				data:{"seq":seq,"date":date},
				success:function(data){
					alert("추가되었습니다.");
				}
			})
			
		})//click end
	})//ready end
</script>

<body> 
<jsp:include page="/MainHeader.jsp"></jsp:include>
<div id="under">
</div>
<form id="bus_area">
	<fieldset id="field">
		<table>
			<tr>
			<td><button name="area" value="Seoul">서울</button></td>
			<td><button name="area" value="Incheon">인천</button></td>
			<td><button name="area" value="Daejeon">대전</button></td>
			<td><button name="area" value="Busan">부산</button></td>
			<td><button name="area" value="Daegu">대구</button></td>
			<td><button name="area" value="Gwanju">광주</button></td>
			</tr>
			<tr>
			<td><button name="area" value="Ulsan">울산</button></td>
			<td><button name="area" value="Sejong">세종</button></td>
			<td><button name="area" value="Jeju">제주</button></td>
			<td><button name="area" value="Gyeonggi">경기</button></td>
			<td><button name="area" value="Gangwon">강원</button></td>
			<td><button name="area" value="Chungbuk">충북</button></td>
			</tr>
			<tr>
			<td><button name="area" value="Chungnam">충남</button></td>
			<td><button name="area" value="Jeonbuk">전북</button></td>
			<td><button name="area" value="Jeonnam">전남</button></td>
			<td><button name="area" value="Gyeongbuk">경북</button></td>
			<td><button name="area" value="Gyeongnam">경남</button></td>
			</tr>
		</table>
	</fieldset>
</form>
<div id="bar">
<jsp:include page="Calendar.jsp"></jsp:include>

<form id=select>
	<select id="bus_start" name="bus_start">
	<option>&nbsp;</option>
	</select>
	<select id="bus_end"  name="bus_end">
	<option>&nbsp;</option>
	</select>
	<button name="search" style="vertical-align:middle" id="search"><span>조회</span></button>
</form>
</div>

<form name="busschedule" id ="busschedule">
	<table id ="scheduletable" border="1">
	<thead id="thead">
		<tr>
			<th width=50px>순서</th><th width=150px>노선명</th>
			<th width=100px>출발 시간</th><th width=100px>이동 시간</th>
			<th width=100px>도착 시간</th><th width=100px>성인 비용</th>
			<th width=100px>청소년 비용</th><th width=100px>아동 비용</th>
			
			<th></th>
		
		</tr>
	</thead>
	<tbody id="output"></tbody>
	</table>
</form>

</body>
</html>

