<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" type="image/png" href="Table_Fixed_Header/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Fixed_Header/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Fixed_Header/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Fixed_Header/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Fixed_Header/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Fixed_Header/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Fixed_Header/css/util.css">
	<link rel="stylesheet" type="text/css" href="Table_Fixed_Header/css/main.css">

<style>
@font-face{font-family:"nanums"; src:url('fonts/NanumSquareR.ttf')}
@font-face{font-family:"nanumsB"; src:url('fonts/NanumSquareR.ttf')}

body{font-family:"nanums"; margin:0px;}
 #container{margin:auto;  flex-direction:column; width:1500px; }
/* #commuboardTAB, #listtable{
		
		text-align:center;
		margin:auto;
	 	width:1500px;
} */
#commuboardTAB{
		
		text-align:center;
		margin:auto;
	 	width:1500px;
	 	
}

#commuboardTAB{font-size:19px; border-spacing:18px;}
select, option, #searchcontent{ height:28px; font-size:inherit}
#searchcontent{margin-top:30px}
select{height:32px;}
.indent{width:100px; margin-top:30px; }
	.btns {
	  width:105px;
	  height:75px;
	  border-radius:8px;
	  background-color: #ddd;
	  border: none;
	  color: black;
	  padding: 16px 32px;
	  text-align: center;
	  font-size: 16px;
	  margin: 0px;
	  transition: 0.3s;
	}

	.btns:hover {
	  border-radius:8px;
	  background-color: teal;
	  color: white;
	}
	
	.third{margin:0px; display:flex; flex-direction:row;}
	#goright{width:1400px; text-align:right}
	#goleft{ text-align:left}
	
	.hi{margin-top:15px; font-size:25px; font-family:'nanums'}
	label{line-height:4}
	#compic{
		width:1500px; height:350px; 
		background:url("commboardimgs/commu.png");
		background-size:cover;
		 background-repeat: no-repeat;
		border-radius:20px;
		}
		
		
</style>
<%@ include file="../MainHeader.jsp" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
   $(function(){
      	
	
      $("#viewcount").change(function(){
         var limit = $("#viewcount option:selected").val();
         var type=new Array();
         type=$("#hidetype").val();
     	  
		 if(limit==null)
			 limit=10;
/*          var searchcontent =$("#searchcontent").val();
	   	  var searchindex=$("#searchindex option:selected").val(); */
	   
         $.ajax({
            type : 'get',
            data : {'limit':limit,"state":"ajax","type":type},
            url: "./ListPage.combo",
            chche:false,
            headers : {"cache-conterl" : "no-cache",
                     "pragma" : "no-cache"},
            success : function(data){
               
               $("#listtable").empty().prepend(data);
            },
            error : function(){
               alert('error');
            }
             
         })//ajax end
         
      })//change end
      $("#viewcount").val("${limit}").prop("selected", true);

   
   })

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>커뮤니티 게시판 리스트</title>
</head>
<body>
<div id="container">

	<form id="search" action="./ListPage.combo"> 
	
	<!--ajax사용할떄 -->
	<!--<form id="search">-->
	<input type="hidden" id="hidtype" value="${sessionScope.type}">
	<table id="commuboardTAB">
		<tr><td colspan="4" ><div id="compic" onClick="location.href='./ListPage.combo'"></div>
			</td></tr>
		<tr>
			<td colspan="4">
				<div class="hi"><img src="commboardimgs/commuicon.png" height=30px width=30px> 커뮤니티 게시판 입니다 ! </div>
			</td>
		</tr>
		<tr>
			<td class="indent"></td>
			<td ><select id="searchindex" name="searchindex">
				<option value="title">제목</option>
				<option value="user">글쓴이</option>
				<option value="content">내용</option>	</select>
			<input type="text" size="100px" name="searchcontent" id="searchcontent" placeholder="검색할 단어를 입력하세요"></td>
			<td rowspan="2"> <input class="btns" type="submit" id="searchbutton" value="검색"></td>
			<td class="indent"></td>
		</tr>
		<tr>
			<td class="indent"></td>
			<td colspan="2" height="50px">
				<label><input type="checkbox" name="type"  value="1">&nbsp;모험 추구 여행&nbsp;<img height="26px" width="26px" src="img/adv_icon.png"></label>&nbsp;|
				&nbsp;<label><input type="checkbox" name="type" value="2">&nbsp;경험 추구 여행&nbsp;<img height="26px" width="26px" src="img/exp_icon.png"></label>&nbsp;|
				&nbsp;<label><input type="checkbox" name="type" value="3">&nbsp;안정 추구 여행&nbsp;<img height="26px" width="26px" src="img/vac_icon.png"></label>&nbsp;|
				&nbsp;<label><input type="checkbox" name="type" value="4">&nbsp;감각 추구 여행&nbsp;<img height="26px" width="26px" src="img/ses_icon.png"></label>
				&nbsp;<label><input type="hidden" name="check" value="check">
			</td>
			<td class="indent"></td>
		</tr>
	</table>


	</form>

	<div class="limiter">

		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
		<table>
			<thead>
					<tr class="row100 head">
						<th  class="cell100 column1" width="8%"> <div> 번호 </div></th>
						<th   class="cell100 column2" width="50%"> <div>제목 </div></th>
						<th  class="cell100 column3"  width="14%"> <div>작성자-성향 </div></th>
						<th   class="cell100 column4"width="17%"> <div>날짜 </div></th>
					</tr>
			</thead>
		</table>
	</div>
	
	<div class="table100-body">
	<table id="listtable">
		<tbody>
	 <c:set var="num" value="${listcount-(page-1)*limit}"/>
	 <c:forEach var="cb" items="${cblist}">
	 <tr class="row100 body">
		<td class="cell100 column1">
			<c:out value="${num}"/>
			<c:set var="num" value="${num-1}"/>
			<input type="hidden" value="${num}" name="shownum" id="shownum">
		</td>
		<td class="cell100 column2">
			<div>
			<a href="./CommDetailView.combo?num=${cb.comm_bo_no}">${cb.comm_bo_title}</a>
			</div>
		</td>
		
		<td class="cell100 column3"> <div>${cb.getUser_id()}
		<c:if test="${cb.getUser_type()==1}"><img height="26px" width="26px" src="img/adv_icon.png"> </c:if>
		 <c:if test="${cb.getUser_type()==2}"><img height="26px" width="26px" src="img/exp_icon.png"> </c:if>
		 <c:if test="${cb.getUser_type()==3}"><img height="26px" width="26px" src="img/vac_icon.png"></c:if>
		 <c:if test="${cb.getUser_type()==4}"><img height="26px" width="26px" src="img/ses_icon.png"> </c:if>
			</div></td>
		<td class="cell100 column4"> <div>${cb.comm_bo_date}</div></td>
	</tr>
	</c:forEach>



<c:if test="${listcount==0}">
  <tr  class="row100 body">
 	<td colspan="4" class="cell100 column3" style="text-align:center">
 	  <font size=2>등록된 글이 없습니다.</font></td>
   </tr>
 </c:if>
 

 <!-- ---------- -->
 
 	<tr class="h30 lime center row100 body">
		<td class="cell100 column1 paging" colspan=5>
			<c:if test="${page <=1}">
			 이전 &nbsp;
			</c:if>
			<c:if test="${page >1 }">
				<a href="./ListPage.combo?page=${page-1}&searchindex=${sessionScope.searchindex}&searchcontent=${sessionScope.searchcontent}${sessionScope.url}">이전</a>
			</c:if>
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
			<c:if test="${a==page}">
				${a}
			</c:if>
			<c:if test="${a!=page}">
			<a href="./ListPage.combo?page=${a}&searchindex=${sessionScope.searchindex}&searchcontent=${sessionScope.searchcontent}${sessionScope.url}">${a}</a>
			</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
				&nbsp; 다음
			</c:if>
			<c:if test="${page <maxpage }">
				<a href="./ListPage.combo?page=${page+1}&searchindex=${sessionScope.searchindex}&searchcontent=${sessionScope.searchcontent}${sessionScope.url}">&nbsp;다음</a>
			</c:if>
			</td>
			
			</tr>
		</tbody>
	</table>

	</div>
	</div>
	<div class="third">

		<div id="goleft">
		  	<select id="viewcount">
		  		<option value="3"> 3줄 보기 </option>
		  		<option value="5"> 5줄 보기 </option>
		  		<option value="7"> 7줄 보기 </option>
		  		<option value="10" selected> 10줄 보기 </option>
		  	</select>
		</div>
			<div id="goright">
		  	<c:if test="${sessionScope.user_id!=null}">
		  	<a href="./InsertPage.combo">[글쓰기]</a> </c:if> </div>
	</div>
	</div>
	

</div>
</div>
</div>
</body>
</html>