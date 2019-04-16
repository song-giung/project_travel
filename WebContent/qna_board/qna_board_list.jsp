<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>

<script>
	$(function() {
		$("#sendEmail").click(
				function() {
					window.open("qna_board/mailForm.jsp", "SendEmail",
							"width=550, height=350, left=100, top=50");
				})
	})
</script>
<title>문의하기 게시판</title>
<link href="css/list.css" rel="stylesheet">
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
	
	width:80%;
	height:280px;
	margin :0 auto;
	
    
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
#board_content{
   position:absolute;
   left:10%;
}
table {background-color:white;border: 1px solid silver; border-radius: 10px; width:100%}
#board_content{
    resize: none;
   margin : auto;
   height : 100px;
}
#date{
	text-align : center;
}

</style>
</head>
<body>
	<%@ include file="/MainHeader.jsp"%>
<div id="top"></div>
<div id="avi">
	

	<table>
		<!-- 레코드가 없으면 -->
		<c:if test="${listcount ==0 }">
			<tr>
				<td colspan="4">문의 하기 게시판</td>
				<td style="text-align: right"><font size=2>등록된 글이 없습니다.</font>
				</td>
			</tr>
		</c:if>




		<!-- 레코드가 있으면 -->
		<c:if test="${listcount !=0 }">
			<tr>
				<th colspan="3">문의하기 게시판 - list</th>
				<th colspan="2"><font size=2>글 개수 : ${listcount}</font></th>
			</tr>
			<tr>
				<th width="8%"><div>번호</div></th>
				<th width="50%"><div>제목</div></th>
				<th width="14%"><div>작성자</div></th>
				<th width="17%"><div>날짜</div></th>

			</tr>

			<c:set var="num" value="${listcount-(page-1)*10}" />
			<c:forEach var="list" items="${boardlist}">
				<tr>
					<td><c:out value="${num}" />
						<%-- num 출력 --%> <c:set var="num" value="${num-1}" /> <%-- num = num-1 --%>
					</td>
					<td>
						<div>
							<%-- 답변글 제목앞에 여백 처리 부분
					BOARD_RE_LEV, BOARD_RE_LEV, BOARD_NUM,
					BOARD_SUBJECT, BOARD_NAME, BOARD_DATE,
					BOARD_READCOUNT : property 이름 --%>
							<c:if test="${list.BOARD_RE_LEV !=0}">
								<!-- 답글인 경우 -->
								<c:forEach var="a" begin="0" end="${list.BOARD_RE_LEV*2}"
									step="1">
					&nbsp;
					</c:forEach>
				▶
				<a href="BoardDetailAction.bo?num=${list.BOARD_NUM}">${list.BOARD_SUBJECT}</a>
							</c:if>
						</div>
						<div>
							<c:if test="${list.BOARD_RE_LEV ==0}">
								<!-- 원문인 경우 -->
				&nbsp; ▶
				<a href="BoardDetailAction.bo?num=${list.BOARD_NUM}">${list.BOARD_SUBJECT}</a>
							</c:if>

						</div>
					</td>


					<td><div>${list.USER_ID}</div></td>
					<td><div id="date">${list.BOARD_DATE}</div></td>

				</tr>
			</c:forEach>
		
			<tr class="h30 lime center btn">
				<td colspan="5"><c:if test="${page<=1}">
				이전&nbsp;
			</c:if> <c:if test="${page>1}">
						<a href="./BoardList.bo?page=${page-1}">이전</a>
					</c:if> <c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a==page}">
					${a}
				</c:if>
						<c:if test="${a!=page}">
							<a href="./BoardList.bo?page=${a}">${a}</a>
						</c:if>
					</c:forEach> <c:if test="${page>=maxpage}">
				다음&nbsp;
			</c:if> <c:if test="${page<maxpage}">
						<a href="./BoardList.bo?page=${page+1}">다음</a>
					</c:if></td>


			</tr>

		</c:if>


		<tr>
			<td colspan="5" style="text-align: right"><a
				href="./BoardWrite.bo">【글쓰기】</a> <a href="" id="sendEmail">【문의
					메일 보내기】</a></td>
		</tr>

	</table>
	</div>
</body>
</html>