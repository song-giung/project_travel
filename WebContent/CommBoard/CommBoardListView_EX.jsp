<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../MainHeader.jsp" %>
<script src="Http://code.jquery.com/jquery-latest.js"></script>
<script>
   $(function(){
      
      $("#viewcount").change(function(){
         var limit = $("#viewcount option:selected").val();
         var searchcontent =$("#searchcontent").val();
	   	  var searchindex=$("#searchindex option:selected").val();
	   	  var type=new Array();
	   	  $("input[name='type']:checked").each(function(index,item ){
	   		  type[index]=$(this).val(); 
	   	  })
	   	  
         $.ajax({
            type : 'get',
            data : {'limit':limit,"state":"ajax", "searchindex":searchindex, "searchcontent":searchcontent, "type":type},
            url: "./ListPage.combo",
            chche:false,
            headers : {"cache-conterl" : "no-cache",
                     "pragma" : "no-cache"},
            success : function(data){
               
               $("#listtable:first").empty().prepend(data);
            },
            error : function(){
               alert('에러');
            }
             
         })//ajax end
         
      })//change end
      $("#viewcount").val("${limit}").prop("selected", true);
   
 /*ajax 없앰   	
      $("#searchbutton").click(function(){
    	  var limit = $("#viewcount option:selected").val();
    	 var searchcontent =$("#searchcontent").val();
    	  var searchindex=$("#searchindex option:selected").val();
    	  var type=new Array();
    	  $("input[name='type']:checked").each(function(index,item ){
    		  type[index]=$(this).val(); 
    	  }) 
    	  
    	   $.ajax({
    	            type : 'get',
    	            data : {'limit':limit,"state":"ajax", "searchindex":searchindex, "searchcontent":searchcontent, "type":type},
    	            url: "./ListPage.combo",
    	            chche:false,
    	            headers : {"cache-conterl" : "no-cache",
    	                     "pragma" : "no-cache"},
    	            success : function(data){
    	               
    	               $("#listtable:first").empty().prepend(data);
    	            },
    	            error : function(){
    	               alert('에러');
    	            }
    	             
    	         })
    	 
      }) */
   
   })

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	
	<form id="search" action="./ListPage.combo"> 
	
	<!--ajax사용할떄 -->
	<!-- <form id="search"> -->
	<table>
		<tr>
			<td rowspan="2" onClick="location.href='./ListPage.combo'">커뮤니티 임당</td>
			<td rowspan><select id="searchindex" name="searchindex">
				<option value="title">제목</option>
				<option value="user">글쓴이</option>
				<option value="content">내용</option>	</select></td>
			<td colspan="2" width="80%"> <input type="text" name="searchcontent" id="searchcontent" placeholder="검색할 단어를 입력하세요"></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2"><input type="checkbox" name="type"  value="1">성향1
				<input type="checkbox" name="type" value="2">성향2
				<input type="checkbox" name="type" value="3">성향3
				<input type="checkbox" name="type" value="4">성향4
			</td>
			<td> <input type="submit" id="searchbutton" value="검색"></td>
		</tr>
	</table>
	</form>
	
<table id="listtable" border='1'>
<c:if test="${listcount>0}">
	<tr>
		<th colspan="3"> 커뮤니티 게시판 </th>
		<td>글 개수 : ${listcount}</td>
	</tr>
	<tr>
		<th width="8%"> <div> 번호 </div></th>
		<th width="50%"> <div>제목 </div></th>
		<th width="14%"> <div>작성자-성향 </div></th>
		<th width="17%"> <div>날짜 </div></th>
	</tr>
	 <c:set var="num" value="${listcount-(page-1)*limit}"/>
	<c:forEach var="cb" items="${cblist}">
	<tr>
		<td>
			<c:out value="${num}"/>
			<c:set var="num" value="${num-1}"/>
		</td>
		<td>
			<div>
			<a href="./CommDetailAction.combo?num=${cb.comm_bo_no}">${cb.comm_bo_title}</a>
			</div>
		</td>
		
		<td> <div>${cb.getUser_id()} - ${cb.getUser_type()}</div></td>
		<td> <div>${cb.comm_bo_date}</div></td>
	</tr>
	
	</c:forEach>
	
</c:if>



<c:if test="${listcount==0}">
  <tr>
 	<td colspan="4">커뮤니티 게시판</td>
 	<td style="text-align:right">
 	  <font size=2>등록된 글이 없습니다.</font></td>
   </tr>
 </c:if>
 
 <!-- **url만들기** -->
 <c:if test="${searchcontent==''}">
 	<c:if test="${type==null}">
 		<%System.out.println("**searchcontent NULL type NULL "); %>
 	</c:if>
 	<c:if test="${type!=null}">
 		<%System.out.println("**searchcontent NULL type NOT NULL");%>
 	</c:if>
 </c:if>
 <c:if test="${searchcontent!=''}">
 	<c:if test="${type==null}">
 		<%System.out.println("**searchcontent NOTNULL type NULL"); %>
 	</c:if>
 	<c:if test="${type!=null}">
 		<%System.out.println("**searchcontent NOTNULL type NOT NULL");%>
 	</c:if>
 </c:if>
 
 	<tr class="h30 lime center btn">
		<td colspan=5>
			<c:if test="${page <=1}">
			 이전 &nbsp;
			</c:if>
			<c:if test="${page >1 }">
				<a href="./ListPage.combo?page=${page-1}&searchindex=${searchindex}&searchcontent=${searchcontent}">이전</a>
			</c:if>
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
			<c:if test="${a==page}">
				${a}
			</c:if>
			<c:if test="${a!=page}">
			<a href="./ListPage.combo?page=${a}&searchindex=${searchindex}&searchcontent=${searchcontent}" >${a}</a>
			</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
				&nbsp; 다음
			</c:if>
			<c:if test="${page <maxpage }">
				<a href="./ListPage.combo?page=${page+1}&searchindex=${searchindex}&searchcontent=${searchcontent}">&nbsp;다음</a>
			</c:if>
			</td>
			
			</tr>
	</table>
  <tr>

  	<td colspan="4" style="text-align:right">
  	<a href="./InsertPage.combo">[글쓰기]</a> </td>
  	<td><select id="viewcount">
  		<option value="3"> 3줄 보기 </option>
  		<option value="5"> 5줄 보기 </option>
  		<option value="7"> 7줄 보기 </option>
  		<option value="10" selected> 10줄 보기 </option>
  	</select></td>
  </tr>
</table>

</body>
</html>