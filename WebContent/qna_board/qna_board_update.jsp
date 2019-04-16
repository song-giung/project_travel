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
eft:10%;
}
table {background-color:white;border: 1px solid silver; border-radius: 10px;}
textarea{
	resize : none;
}

</style>
</head>
<body>
	<%@ include file="/MainHeader.jsp"%>
	<div id="top"></div>
	<div id="avi">
	<c:set var="boarddata" value="${boarddata}" />
	<form action="./BoardUpdateAction.bo" method="post"
		enctype="multipart/form-data" name="boardform">
		<input type="hidden" name="BOARD_NUM" value="${boarddata.BOARD_NUM}">
		<table id="table">
			<tr class="center">
				<th colspan="2">글 수정</th>
			</tr>
			<tr>
				<td><div>글쓴이</div></td>
				<td><input name="USER_ID" id="user_id" readOnly type="text"
					size="10" maxlength="10" value="${user_id}"></td>
			</tr>

			<tr>
				<td><div>비밀번호</div></td>
				<td><input name="BOARD_PASS" id="board_pass" type="password"
					size="5" maxlength="10" value=""></td>
			</tr>

			<tr>
				<td><div>제 목</div></td>
				<td><input name="BOARD_SUBJECT" id="board_subject" type="text"
					size="50" maxlength="100" value="${boarddata.BOARD_SUBJECT}">
				</td>
			</tr>
			<tr>
				<td><div>내 용</div></td>
				<td><textarea name="BOARD_CONTENT" id="board_content" cols="67"
						rows="15">${boarddata.BOARD_CONTENT}</textarea></td>
			</tr>

			<!-- 파일이 첨부되어 있으면 -->
			<c:if test="${!empty boarddata.BOARD_FILE}">
				<tr>
					<td>
						<div>파일 첨부</div>
					</td>
					<td>&nbsp;&nbsp;${boarddata.BOARD_FILE}</td>
				</tr>
			</c:if>
			<tr class="center">
				<td colspan="2" class="h30 lime"><input type="submit"
					value="등록"> <input type="reset" value="취소"
					onClick="history.go(-1)"></td>
			</tr>
		</table>
	</form>
</div>

</body>

</html>