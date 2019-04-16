<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>공지 사항</title>
	<link href="css/list.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-3.0.0.js"></script>
</head>
<style>
	body{
		margin : 0px;
	}
	table{
		width : 830px;
		background-color : white;
		margin-top : 20px;
		margin-bottom : 200px;
		border: 1px solid silver; 
		border-radius: 10px; 
	}
	#write{
		float : right;
		margin-right : 20px;
	}
	#under{

		background-image: url('./Notice/notice.png');
		margin : auto;
		background-repeat: no-repeat; 
		width : 100%;
		height : 400px;
		background-position: bottom center;
		margin-top: 20px;
	}
	#date{
		text-align : center;
	}
</style>
<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>
<div id="under">
</div>
<!-- 게시판 리스트 -->

<table>
<!-- 레코드가 있으면 --><!-- core 라이브러리 사용 -->
<c:if test="${listcount > 0 }">
	<tr>
		<th colspan="3">공지 사항</th>
		<th>
			<font size=2>글 개수 : ${listcount }</font>
		</th>
	</tr>
	<tr>
		<th width="10%"><div>번호</div></th>
		<th  width="52%"><div >제목</div></th>
		<th width="16%"><div >작성자</div></th>
		<th  width="19%"><div >날짜</div></th>
	</tr>		
	<c:set var="num" value="${listcount-(page-1)*limit}"/>	
	<c:forEach var="n" items="${noticelist}">	
	<tr>
	  <td>
		<c:out value="${num}"/><%-- num 출력 --%>		
		<c:set var="num" value="${num-1}"/>	<%-- num = num - 1 의미--%>	
	  </td>
	  <td>
	     <div>			
			<a href="./NoticeDetailAction.no?num=${n.notice_no}">
				${n.notice_title}
			</a>
			</div>
		</td>
		<td>
			<div>${n.admin_id}</div>
		</td>
		<td>
			<div id="date">${n.notice_date}</div>
		</td>	
	</tr>
	</c:forEach>
	
	<tr class="h30 lime center btn">
		<td colspan=4>			
			<c:if test="${page <= 1 }">
				이전&nbsp;
			</c:if>
			<c:if test="${page > 1 }">			
				 <a href="./NoticeList.no?page=${page-1}">이전</a>&nbsp;
			</c:if>
					
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					${a}
				</c:if>
				<c:if test="${a != page }">
					<a href="./NoticeList.no?page=${a}">${a}</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
				&nbsp;다음 
			</c:if>
			<c:if test="${page < maxpage }">
				<a href="./NoticeList.no?page=${page+1}">&nbsp;다음</a>
			</c:if>
			<c:if test="${user_id == 'admin' }">
			<a href="./NoticeWrite.no" id="write">[글쓰기]</a>
			</c:if>			
		</td>
	</tr>
	</c:if>
</table>

	<!-- 레코드가 없으면 -->
<c:if test="${listcount == 0 }">
<table>
	   <tr>
		<td colspan="4">공지 사항</td>
		<td style="text-align:right">
			<font size=2>등록된 글이 없습니다.</font>
		</td>
	  </tr>
	<c:if test="${user_id == 'admin' }">
	  <tr>
		<td colspan="4" style="text-align:right">
	   		<a href="./NoticeWrite.no">[글쓰기]</a>
		</td>
	  </tr>
	</c:if>

</table>
</c:if>
</body>
</html>