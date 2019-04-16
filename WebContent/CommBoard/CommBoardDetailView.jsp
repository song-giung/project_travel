
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ include file="../MainHeader.jsp" %>
  <%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="cb" value="${cbdata}"/> 
<script src="http://code.jQuery.com/jquery-latest.js"></script>
<script>
$(function(){
	$('#golist').click(function(){
		location.href="./ListPage.combo?page=${page}&searchindex=${sessionScope.searchindex}&searchcontent=${sessionScope.searchcontent}${sessionScope.url}"
	})
	
	$('#insertComt').click(function(){
		$("#nocomts").remove();
		var user_no=$("#user_no").val();
		var comm_bo_no=$("#comm_bo_no").val();
		var c_comt_cont=$("#comment").val();
		
		$.ajax({
			type:'post',
			data:{'comm_bo_no':comm_bo_no, 'user_no':user_no, 'c_comt_cont':c_comt_cont},
			url: "./c_comt_insert.ccomt",
			success : function(data){
			
	               $("#clean").empty().prepend(data); 
				  	var comtcount=$("#clean").find("#comt_count").val();
				  	 $("#comt_count").text(comtcount); 
			},
	            error : function(){
	               alert('error');
	            }
		})	
		
		$("#comment").val("");
	})
	
	//댓글 페이징 처리
	$(document).on("click", ".a",function(e){
		e.preventDefault();
		
		var a=$(this).text().trim();
		var page=$("#page").val();
		var realpage=0;
		
		if(a=="이전"){
			realpage=parseInt(page)-1;
			}
		else if(a=="다음"){
			realpage=parseInt(page)+1;
			console.log(realpage);
		}else
			realpage=a;
		
		
		var user_no=$("#user_no").val();
		var comm_bo_no=$("#comm_bo_no").val();
		var c_comt_cont=$("#comment").val();
		
		$.ajax({
			type:'post',
			data:{'comm_bo_no':comm_bo_no, 'user_no':user_no, 'c_comt_cont':c_comt_cont,'page':realpage},
			url: "./ListComt.ccomt",
			success : function(data){
			
	               $("#clean").empty().prepend(data); 
				  
			},
	            error : function(){
	               alert('error');
	            }
		})	

	})
	
	$(document).on("click", '.comtDelete', function(){
	
		if($("#page").val()!=null)
			var a=$("#page").val();
		else
			var a=1;
		
		var user_no=$("#user_no").val();
		var comm_bo_no=$("#comm_bo_no").val();
		var c_comt_cont=$("#comment").val();
		var c_comt_no=$(this).val();
		
		$.ajax({
			type:'post',
			data:{'comm_bo_no':comm_bo_no, 'user_no':user_no, 'c_comt_no':c_comt_no, 'page':a},
			url: "./c_comt_delete.ccomt",
			success : function(data){
				
	               $("#clean").empty().prepend(data); 
				  	var comtcount=$("#clean").find("#comt_count").val();
				  	 $("#comt_count").text(comtcount); 
				  	if(comtcount%10==1){
				  		
				  	}
			},
	            error : function(){
	               alert('error');
	            }
		})	
		
		$("#comment").val("");
	})
	
	
	

})
</script>

<style>
hr{border:0;border-top:1px solid rgba(0,0,0,.1)}
@font-face{font-family:"nanums"; src:url('fonts/NanumSquareR.ttf')}
@font-face{font-family:"nanumsB"; src:url('fonts/NanumSquareR.ttf')}

/* .container{margin:0px 150px; display:inline-flex;width:1800px; height:1000px; }
 */
 body{font-family:"nanums"; margin :0px;}
 .container{margin: auto; display:flex; flex-direction:column; width:1500px; }

 #elsecomts{display:flex; flex-flow:wrap;align-content:flex-start;width:1500px; }
	 .first{ display:flex; flex-flow:wrap; margin:10px 0px 0px 0px; width:100%; height:10% }
 	#title{border-radius:10px; box-shadow: 5px 10px 8px lightslategray; border:1px solid lightgray; font-family:"nanumsB"; font-color:gray margin:0; letter-spacing:4px; text-align:left; line-height:100px; font-size:40px; width:84%; height:100%}
  	.dates{  font-family:"nanums"; margin:auto; text-align:center; font-size: 20px; line-height:50px; height:100%}	#date, #update{height:50px}
  	
  	#content{margin:0px 30px; width:84%; font-family:"nanums"; font-size:25px; }
  	.userinfo{border-radius:10px; box-shadow: 5px 10px 8px lightslategray; line-height:100px; border:1px solid lightgray; margin: 15px 10px; text-align:center; font-size:20px; font-family:"nanums"; width:15% ;/*  height:40% */}

  
  
	.second{margin: 15px 0px; font-family:"nanums"; line-height:50px; text-align:left; width:83%}
	#showfile{text-align:right ;font-size:18px;}
	#update_delete{width:84%; text-align:right}
  	#commentsboard{text-align:center; width:98%;}
#clean{font-size:20px; text-align:center}

#commentview{margin:auto; font-size:20px; text-align:center}
	#commentview td:nth-child(1){width:20%}
	#commentview td:nth-child(2){width:60%}
	#commentview td:nth-child(3){width:20%}
	
table{margin: 0px 40px; width:100%}
#clean tr{height:40px;}
#clean td{border-bottom: 1px solid lightgray;}
span{color:gray}
.ccimg{line-height:26px}
summary{font-size:20px;}


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
<head>
<title>여행가용 |커뮤니티 | ${cb.getComm_bo_title() }</title>
</head>
<body>
	<div class="container">
	
	<div id="elsecomts">
		<div id="compic" onClick="location.href='./ListPage.combo'"></div>
	
		 <div class="first">
			<div id="title"> <span>&nbsp;&nbsp;글 제목 |</span> ${cb.getComm_bo_title() } </div>
			<div class="dates">
				<div id="date"> 작성일|${cb.getComm_bo_date() } </div>
				<c:if test="${cb.getComm_bo_update()!=null}"> <div id="update">수정날짜 |${cb.getComm_bo_update() }</div></c:if>
			</div>
		 </div>
		 
		<div class="second">
			<c:if test="${cb.getComm_bo_file()!=null}">
			<div id="showfile">첨부파일 | <a href="./CommBoardDown.combo?filename=${cb.getComm_bo_file()}">${cb.getComm_bo_file()}</a></div>
			</c:if>
			
			
			<div id="content">${cb.getComm_bo_content()}</div>
		
		
		</div>
		
	
		
		<div class="userinfo">
			<div id="userinfo_id"> 작성자 | ${cb.getUser_id()}</div>
			<div id="user_type">
			 <c:if test="${cb.getUser_type()==1}"><img class="ccimg" height="150px" width="150px" src="img/adv_icon.png"> <div>모험 추구 여행자</div> </c:if>
			 <c:if test="${cb.getUser_type()==2}"><img class="ccimg" height="150px" width="150px" src="img/exp_icon.png"> <div>경험 추구 여행자 </div></c:if>
			 <c:if test="${cb.getUser_type()==3}"><img class="ccimg"  height="150px" width="150px" src="img/vac_icon.png"><div> 안정 추구 여행자 </div></c:if>
			 <c:if test="${cb.getUser_type()==4}"><img class="ccimg" height="150px" width="150px" src="img/ses_icon.png"> <div>감각 추구 여행자 </div></c:if>
			</div>
		</div>
	

	
					
	</div>

	
	<div id="commentsboard">
		<div id="update_delete">
				<c:if test="${cb.user_no==sessionScope.user_no||'admin'==sessionScope.user_id}"> 
						 <a href="./CommModifyView.combo?num=${cb.comm_bo_no}">수정</a>  &nbsp;&nbsp;
						 <a href="./CommBoardDelete.combo?num=${cb.comm_bo_no}">삭제</a>  &nbsp;&nbsp;
				</c:if>
				<button type="button" id="golist">목록</button> 
		</div>
	<details id="show_comments">
	<summary>댓글(<span id="comt_count">${c_comt_count}</span>)</summary>
	
	<table id="clean">
		<input id="page" type="hidden" value="${page}"/>
		<c:if test="${c_comt_count>0}"> 	<!-- 댓글 있는 경우 -->
		<c:forEach var="ccb" items="${ccb}">
			<tr>
			
			<td>${ccb.user_id}
	 		<c:if test="${ccb.getUser_type()==1}"><img height="26px" width="26px" src="img/adv_icon.png"> </c:if>
			 <c:if test="${ccb.getUser_type()==2}"><img height="26px" width="26px" src="img/exp_icon.png"> </c:if>
			 <c:if test="${ccb.getUser_type()==3}"><img height="26px" width="26px" src="img/vac_icon.png"></c:if>
			 <c:if test="${ccb.getUser_type()==4}"><img height="26px" width="26px" src="img/ses_icon.png"> </c:if>
			</td>
			<td>${ccb.c_comt_cont}</td>
			<td>${ccb.c_comt_date}
				<c:if test="${ccb.user_no==sessionScope.user_no||'admin'==sessionScope.user_id}"> 
				<!-- 댓글 수정도 나중에 하자  ||<button type="button" id="comtModify">수정</button> -->
				||<button type="button" class="comtDelete"  value="${ccb.c_comt_no}" >삭제</button> </c:if>
			</td>
			</tr>
		</c:forEach>
		</c:if>
			<c:if test="${c_comt_count==0}"> <!-- 댓글 없는 경우 -->
				<div id="nocomts">등록된 댓글이 없습니다.</div>
		</c:if> 
		<!-- 댓글 페이징 -->
		<tr class="h30 lime center btn">
			<td colspan=3>
				<c:if test="${page <=1}">
				 이전 &nbsp;
				</c:if>
				<c:if test="${page >1 }">
					<a href="./ListComt.ccomt?page=${page-1}" class="a">이전</a>
				</c:if>
					
				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a==page}">
						${a}
					</c:if>
					<c:if test="${a!=page}">
					
					 <%-- <button type="button" value="${a}" class="a">${a}</button>  --%>
					 <a href="./ListComt.ccomt?page=${a}" class="a">${a}</a> 
					</c:if>
				</c:forEach>
				
				<c:if test="${page >= maxpage }">
					&nbsp; 다음
				</c:if>
				<c:if test="${page <maxpage }">
					<a href="./ListComt.ccomt?page=${page+1}" class="a">&nbsp;다음</a>
				</c:if>
				</td>
				
				</tr>
	</table>


	<form>
	<table id="commentview">
		<tr>
		<td >댓글달기 | <input type="hidden" name="user_no" id="user_no" value="${sessionScope.user_no}"><!-- 아이디!!!!user_no!! -->
					<input  type="hidden" name="comm_bo_no" id="comm_bo_no" value="${comm_bo_no}"></td>
		<td ><input size="100px" type="text" name="comment" id="comment" style='height:35px'></td>
		<td ><button type="button" class="btns" id="insertComt" name="commentinsert">댓글달기</button></td>
		</tr>
	</table>
	</form>
	</details>

	</div>
	
	</div>
</body>
</html>