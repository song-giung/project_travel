<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Community main</title>
<style>
hr{border:0; border-top:1px solid rgba(0,0,0,.1)}
		@font-face{font-family:"nanums"; src:url('fonts/NanumSquareR.ttf')}
	@font-face{font-family:"nanumsB"; src:url('fonts/NanumSquareR.ttf')}
	
	body{font-family:"nanums"; margin :0px;}
	
	 .container{margin:auto; display:flex; flex-direction:column; width:1500px; }
	
	 table{	
	 	 width:1500px;
	 	 margin:auto;
	 	text-align:center;
	 	}
	 .typeimg{  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
				border-radius:10px;
 				 height:450px;}
 	 .typeimg:hover{ opacity : 0.3; color:whitesmoke; background-color:teal}

	 .types{ margin-top:45px;width:200px ;height:200px}
	 .tt{text-align: center;
 			 padding: 10px 20px;
 			 font-size:20px;
 			 margin-top:50px}
 	#compic{
		width:1500px; height:350px; 
		background:url("commboardimgs/commu.png");
		background-size:cover;
		 background-repeat: no-repeat;
		border-radius:20px;
		}
	
 		
</style>

</head>
<body>
<jsp:include page="../MainHeader.jsp"/>
	<div class="container">
		<table>
			<tr><td colspan="4" onClick="location.href='./ListPage.combo'"><div id="compic"></div></td></tr>
			<tr>
				<td> <div class="typeimg" onClick="location.href='./ListPage.combo?type=1'"><img class="types" src="img/adv_icon.png"><div class="tt">모험 추구 여행자</div></div></td>
				<td><div class="typeimg" onClick="location.href='./ListPage.combo?type=2'"><img class="types"  src="img/exp_icon.png"><div class="tt">경험 추구 여행자</div></div></td>
				<td><div class="typeimg" onClick="location.href='./ListPage.combo?type=3'"><img class="types" onClick="location.href='./ListPage.combo?type=3'" src="img/vac_icon.png"><div class="tt">안정 추구 여행자</div> </div></td>
				<td><div class="typeimg"  onClick="location.href='./ListPage.combo?type=4'" ><img class="types"src="img/ses_icon.png"><div class="tt">감각 추구 여행자</div></div></td>				
			</tr>
		</table>
	</div>
</body>
</html>