<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>공지사항 작성 페이지</title>
<style>
*{box-sizing:border-box;}
td{border-bottom:1px solid #cdc9c9;padding:1%;}
tr:nth-child(1){border-bottom:2px solid #cdc9c9; height:20px;}
td:nth-child(1){width:15%;    background-color: #f1f1f1;}
th{background:#f1f1f1;padding:1% 0.5%;}
input, td{font-family:돋음; font-size:18; }
.center{text-align:center}
.center a, input[type=submit],input[type=reset],input[type=button]
{display:inline-block;background:gray;color:white;text-decoration:none;padding:0 5px;font-size:16px;border:none}
.center a:hover,input[type=submit]:hover,input[type=reset]:hover,input[type=button]:hover{opacity:0.8}
input[type=text], input[type=password]{width:100%;}
input[type=submit],input[type=reset]{border-radius:3px}


	body{
		margin : 0px;
	}
	table{
	background-color : white;
	border: 1px solid silver; 
	border-radius: 10px; 
	margin:0 auto;
	width:830px; 
	}
	form{
		margin-top : 20px;
		margin-bottom : 200px;
	}
	textarea{
		width : 100%;
		resize : none;
	}
	input[type=button]{border-radius:3px}
	#under{

		background-image: url('./Notice/notice.png');
		margin : auto;
		background-repeat: no-repeat; 
		width : 100%;
		height : 400px;
		background-position: bottom center;
		margin-top: 20px;
	}
</style>
</head>

<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>
<div id="under">
</div>
	<form action="./NoticeAddAction.no" method="post" name="boardform">
		<table>
			<tr class="center">
				<th colspan="2">공지 사항 작성</th>
			</tr>
			<tr>
				<td><div>글쓴이</div></td>
				<td><input name="admin_id" id="admin_id" readonly
				type="text" size="10" maxlength="30" value="${user_id}" ></td>
			</tr>
			<tr>
				<td><div>제  목</div></td>
				<td><input name="notice_title" id="notice_title" type="text"
				size="50" maxlength="100" value=""></td>
			</tr>
			<tr>
				<td><div>내  용</div></td>
				<td><textarea name="notice_content" id="notice_content"
				rows="15" cols="65"></textarea></td>
			</tr>
			<tr class="center">
				<td colspan="2" class="h30_lime">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
					<input type="button" value="목록" onClick="history.back()">
				</td>
			</tr>
		</table>		
	</form>
</body>
</html>




