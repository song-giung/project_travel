<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="../MainHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jQuery.com/jquery-latest.js"></script>
<script>
$(function(){
	$("#goback").click(function(){
		var check = confirm("입력 취소하고 뒤로가시겠습니까 ? ");
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
	
	input,textarea{border-radius:10px;}
	#compic{
		width:1500px; height:350px; 
		background:url("commboardimgs/commu.png");
		background-size:cover;
		 background-repeat: no-repeat;
		border-radius:20px;
		}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>커뮤니티 게시판에 글쓰기</title>
</head>
<body>	
		<div class="container">
		<form action="./Insert.combo" method="post" enctype="multipart/form-data" name="boardform">
		<table>
			<tr>
			<td colspan="3"><div id="compic" onClick="location.href='./ListPage.combo'"></div>
			</td>
			</tr>
			<tr>
				<th colspan="3" > 커뮤니티 게시판 글쓰기</th>
			</tr>
			<tr class="inin">
				<td> <div>제목  &nbsp;|</div></td>
				<td colspan="2"> <input id="comb_title" type="text" name="comb_title" size="50" maxlength="100" value="" required> </td>
				
			</tr>
			<tr class="inin">
				<td><div>파일첨부 &nbsp;|</div></td>
				<td colspan="2"><input type="file" id="upfile" name="comb_file" ></td>
			</tr>
			<tr class="inin">
				<td> <div>내용 &nbsp;|</div></td>
				<td colspan="2"> <textarea id="comb_content" name="comb_content" id="comb_content" cols="70" rows="20" required></textarea>
			</tr>
	
			<tr>
				<td id="buttons" colspan="3"> <input class="btns" type="submit" value="등록"> <button type="button" class="btns"  id="goback" >뒤로</button> <input  class="btns"  type="reset" value="취소">

				</td> 
			</tr>
				
			
		</table>
		</form>
		</div>
</body>
</html>