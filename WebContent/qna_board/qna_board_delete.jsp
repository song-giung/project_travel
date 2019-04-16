<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>문의하기 게시판</title>
<link href="css/form2.css" rel="stylesheet">
<style>

body{
   background-color : white;
   margin :0px;
   }
form{
   text-align:center;
   height : 100px;
   margin : 0px;
}

#register{
   margin: 0px;
   width:20%;
   height : 100px;
   display : inline-block;
   position:absolute;
   right:10%
   
   
}
#avi{
	
	width:100%;
	height:280px;
	margin : 0px;
	
    
	}
#top{
	background-repeat: no-repeat; 
	background-position: bottom center;
	background-image: url('qna_board/asd.jpg');
	background-size:80%;
	width:100%;
	height:417px;
	margin-bottom: 20px;
}
table {background-color:white;border: 1px solid silver; border-radius: 10px;}
#board_content{
    resize: none;
   margin : auto;
   height : 100px;
}

</style>
</head>
<body>
	<%@ include file="/MainHeader.jsp"%>
	<div id="top"></div>
	<div id="avi">
	<form action="./BoardDeleteAction.bo?num=${param.num}" method="post"
		name="boardform">
		<table id="table">
			<tr class="center">
				<th colspan="2">글 삭제</th>
			</tr>


			<tr>
				<td><div>비밀번호</div></td>
				<td><input name="BOARD_PASS" id="board_pass" type="password"
					size="5" maxlength="10" value=""></td>
			</tr>


			<tr class="center">
				<td colspan="2" class="h30 lime"><input type="submit"
					value="삭제"> <input type="reset" value="취소"
					onClick="history.go(-1)"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>