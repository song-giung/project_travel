<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ include file="../MainHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://code.jQuery.com/jquery-latest.js"></script>
<script>
$(function(){
	$("#goback").click(function(){
		var check = confirm("수정 취소하고 뒤로가시겠습니까 ? ");
		if(check==true)
			history.back()
	})	


})
</script>

<style>
hr{border:0;border-top:1px solid rgba(0,0,0,.1)}
@font-face{font-family:"nanums"; src:url('fonts/NanumSquareR.ttf')}
	@font-face{font-family:"nanumsB"; src:url('fonts/NanumSquareR.ttf')}

	body{font-family:"nanums"; margin :0px;}
	 .container{margin: auto; display:flex; flex-direction:column; width:1500px; }
	table{border-spacing:10px 5px; width:1500px;}
	th{font-size :30px; font-color: gray; border-bottom:1px dotted gray; height:60px; font-family:"nanums"; font-color:gray margin:0; letter-spacing:4px;  }
	td:first-child{ letter-spacing:4px; width:250px;}
	td:nth-child(2){ width:1000px;}
	.inin div{font-size:20px; text-align:right;}
	#comb_title{font-family:"nanums"; width: 1000px; height:35px; font-size: 27px;}
	#comb_content{ font-family:inherit; width: 1000px; font-size:24px;}
	#buttons{text-align:center;}
	input, textarea{border-radius:10px;}
	.btns {
	  border-radius:12px;
	  background-color: #ddd;
	  border: none;
	  color: black;
	  padding: 16px 32px;
	  text-align: center;
	  font-size: 16px;
	  margin: 4px 2px;
	  transition: 0.3s;
	}

	.btns:hover {
	  border-radius:12px;
	  background-color: teal;
	  color: white;
	}
	#compic{
		width:1500px; height:350px; 
		background:url("commboardimgs/commu.png");
		background-size:cover;
		 background-repeat: no-repeat;
		border-radius:20px;
		}
		
	</style>
<title>수정페이지</title>
</head>
<body>
	
			<div class="container">
			<form action="./ModifyAction.combo" method="post" name="boardform">
			<input type="hidden" value="${boarddata.comm_bo_no}" name="comm_bo_no">
		<table>
			<tr>
			<td colspan="2"><div id="compic" onClick="location.href='./ListPage.combo'"></div>
			</td>
			</tr>
			<tr>
				<th colspan="3" > 커뮤니티 게시판 수정하기 </th>
			</tr>
			<tr class="inin">
				<td><div>파일첨부 &nbsp;|</div></td>
				<td colspan="2"><input type="file" id="upfile" name="comb_file"  value="${boarddata.comm_bo_file}"></td>
			</tr>

			<tr class="inin">
				<td> <div>제목  &nbsp;|</div></td>
				<td colspan="2"> <input type="text" name="comb_title" size="50" maxlength="100" value="${boarddata.comm_bo_title}"> </td>
			</tr>
			<tr class="inin">
				<td> <div>내용  &nbsp;| </div></td>
				<td colspan="2"> <textarea name="comb_content" id="comb_content" cols="70" rows="20">${boarddata.comm_bo_content}</textarea>
			</tr>

			<tr>
				<td id="buttons" colspan="3"> <input class="btns" type="submit" value="등록"> <input class="btns" type="button" id="goback" value="뒤로"> <input class="btns" type="reset" value="초기화"></td> 
			</tr>
				
			
		</table>
		</form>
		</div>
</body>
</html>