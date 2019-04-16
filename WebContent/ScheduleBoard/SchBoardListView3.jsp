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


<%@ include file="../MainHeader.jsp" %>
<script src="Http://code.jquery.com/jquery-latest.js"></script>
<script>
   $(function(){
	  $("input[type=checkbox]").on("click",(function(){
		
		  if($(this).is(":checked")){ //처음 체크할때 그림 더해줌
				if($(this).val()==1){
					 $("#selectView2").append("<img id='tv1' width='23px' height='23px' src='img/adv_icon.png'>");
				 }
				 if($(this).val()==2){
					 $("#selectView2").append("<img id='tv2' width='23px' height='23px' src='img/exp_icon.png'>");
				 }
				if($(this).val()==3){
					$("#selectView2").append("<img id='tv3' width='23px' height='23px' src='img/vac_icon.png'>");
				}
				if($(this).val()==4){
					$("#selectView2").append("<img id='tv4' width='23px' height='23px' src='img/ses_icon.png'>");
				 }
		  }else{
			  if($(this).val()==1){
					 $("#tv1").remove();
				 }
				 if($(this).val()==2){
					 $("#tv2").remove();
				 }
				if($(this).val()==3){
					$("#tv3").remove();
				}
				if($(this).val()==4){
					$("#tv4").remove();
				 }
		  }
		  
	  })
	  )
	  
	  $(document).on("click","#searchbtn",function(){
		  var searchcontent =$("#searchcontent").val();
    	  var searchindex=$("#searchindex option:selected").val();
    	  var loadcheck=$("#loadcheck:checked").val();
    	  var type=new Array();
	   	  $("input[name='type']:checked").each(function(index,item ){
	   		  type[index]=$(this).val(); 
	   	  	})
	   	  	
	   	  	if(loadcheck!=null){
	   	  		alert("저장하고 검색");
	   	  	}
    	  var limit=10;
		  $.ajax({
			  type:'get',
		  	  data : { "limit" : limit, "searchcheck":"searchcheck", "search":loadcheck, "searchindex":searchindex, "searchcontent":searchcontent , "type":type},
			  url: "./ListPage.schbo",
	          chche:false,
	          headers : {"cache-conterl" : "no-cache",
	                   "pragma" : "no-cache"},
	          success : function(data){
	      
	             $("#listtable").empty().prepend(data);
	             var selectedTP=$("#listtable").find("#showtype").val();
	         
	          },
	          error : function(){
	             alert('error');
          	  }
           
      		 })//ajax1 end
		  })
	  
	  
      $(document).on("click","#typesearchbutton",(function(){
    	 var limit=10;
         var type=new Array();
	   	  $("input[name='type']:checked").each(function(index,item ){
	   		  type[index]=$(this).val(); 
	   	  	})
     
         $.ajax({
            type : 'get',
            data : {'limit':limit,"state":"ajax", "type":type},
            url: "./ListPage.schbo",
            chche:false,
            headers : {"cache-conterl" : "no-cache",
                     "pragma" : "no-cache"},
            success : function(data){
        
               $("#listtable:first").empty().prepend(data);
            },
            error : function(){
               alert('error');
            }
             
         })//ajax1 end
         
         $.ajax({
             type : 'get',
             data : {'limit':limit,"state":"ajax", "type":type},
             url: "./get3.schbo",
             chche:false,
             headers : {"cache-conterl" : "no-cache",
                      "pragma" : "no-cache"},
             success : function(data){
         
                $("#best3").empty().prepend(data);
               
             },
             error : function(){
                alert('error');
             }
              
          })//ajax end
          
         
      }))
    
   })

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>일정 공유 게시판</title>
<style>
@font-face{font-family:"nanums"; src:url('fonts/NanumSquareR.ttf')}
@font-face{font-family:"nanumsB"; src:url('fonts/NanumSquareR.ttf')}

body{font-family:"nanums"; margin:0px;}
 #container{margin:auto;  flex-direction:column; width:1500px; } /*display:flex 삭제함*/
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

select{height:32px;}
.indent{width:100px;}

	.btns {
	  width:105px;
	  height:30px;
	  border-radius:8px;
	  background-color: #ddd;
	  border: none;
	  color: black;
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
	#goright{width:1500px; text-align:right}
	#goleft{ text-align:left}
	#margintop{margin-top:30px;}
	#best3{ margin-left:30px; width:100% ;display:flex; flex-direction:row;}
	.best3each{text-align:center; border-radius:5px; box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); bordmargin-top:30px; width:32%; height:80px; }
	
	#best3imgcontainer{margin-left:32px;  margin-top:30px; display:flex; flex-direction:row;}
	.best3imgs img{height:40px;}
	.best3imgs{width:33%}
	#selectView{text-align:center; margin-top:30px;font-size:25px;display:flex; flex-direction:row; margin-left:500px}
	#selectView2{margin-top:20px; text-align:center;}
	label{word-spacing:3px;}
/*---------------------------------------------------------------------------------------------------------------*/
	
	#searching form {margin-top:20px; display:flex; flex-direction:row; }
	#searching form div:nth-child(1){ width:10%}
	#searching form div:nth-child(2){ width:7%}
	#searching form div:nth-child(3){ width:68%}
	#searching form div:nth-child(4){width:15%}
	#searchcontent{width:100%;}
	select{width:62px}
	.btns{margin-left:30px;}
	#goright{margin-top:10px;text-align:right}
	
	/*-----------------------*/
	.wrap-table100{
		margin-bottom : 100px;
	}
	
	#compic{
		width:1500px; height:350px; 
		background:url("commboardimgs/schboardimgs2.jpg");
		background-size:cover;
		background-repeat: no-repeat;
		border-radius:20px;
		}
	
</style>

</head>
<body>
	<div id="container">
	<form id="typeSearch" > 
	<table id="commuboardTAB">
		<tr><td colspan="4"><div id="compic" onClick="location.href='./ListPage.schbo'"></div></td></tr>
		<tr>
			<td colspan="4">
				<label><input id="margintop" type="checkbox" name="type"  value="1">&nbsp;모험 추구 여행&nbsp;<img height="26px" width="26px" src="img/adv_icon.png"></label>&nbsp;|
				&nbsp;<label><input type="checkbox" name="type" value="2">&nbsp;경험 추구 여행&nbsp;<img height="26px" width="26px" src="img/exp_icon.png"></label>&nbsp;|
				&nbsp;<label><input type="checkbox" name="type" value="3">&nbsp;안정 추구 여행&nbsp;<img height="26px" width="26px" src="img/vac_icon.png"></label>&nbsp;|
				&nbsp;<label><input type="checkbox" name="type" value="4">&nbsp;감각 추구 여행&nbsp;<img height="26px" width="26px" src="img/ses_icon.png"></label>
				&nbsp;<label><input type="hidden" name="check" value="check">
				<input type="button" class="btns" id="typesearchbutton" value="검색"></td>
		</tr>
	</table>
	</form>
	
	<div id="selectView">
	 
		
		 <img src="img/top3.png" width="30px" height="30px"> &nbsp;&nbsp; 선택하신 성향 의 사람들은 이런곳을 좋아했어요 !
	</div>
	<div id="selectView2"></div>
	<div id="best3imgcontainer">
			<div class="best3imgs"><img src="img/best1.png"></div>
			<div class="best3imgs"><img src="img/best2.png"></div>
			<div class="best3imgs"><img src="img/best3.png"></div>
	</div>
	
	<div id="best3">
		<c:forEach var="sbb3" items="${sbb3}">
		<div class="best3each" onclick="location.href='./SchDetailView.schbo?num=${sbb3.sch_bo_no}'">
			글 제목: ${sbb3.sch_bo_title}
			<div>작성자 : ${sbb3.user_id}</div>
			<div>
									<c:if test="${sbb3.getUser_type()==1}"><img height="26px" width="26px" src="img/adv_icon.png"> </c:if>
								 <c:if test="${sbb3.getUser_type()==2}"><img height="26px" width="26px" src="img/exp_icon.png"> </c:if>
								 <c:if test="${sbb3.getUser_type()==3}"><img height="26px" width="26px" src="img/vac_icon.png"></c:if>
								 <c:if test="${sbb3.getUser_type()==4}"><img height="26px" width="26px" src="img/ses_icon.png"> </c:if>
			</div>
		</div>
		</c:forEach>
		
	</div>

<!-- ------------------------------------------------------------------------- -->

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
					<c:forEach var="sbb" items="${sbb}">
						<tr class="row100 body">
							<td class="cell100 column1">
								<c:out value="${num}"/>
								<c:set var="num" value="${num-1}"/>
							</td>
							<td class="cell100 column2">
								<div>
								<a href="./SchDetailView.schbo?num=${sbb.sch_bo_no}">${sbb.sch_bo_title}</a>
								</div>
							</td>
							<td class="cell100 column3">
								<div>${sbb.getUser_id()}
								<c:if test="${sbb.getUser_type()==1}"><img height="26px" width="26px" src="img/adv_icon.png"> </c:if>
								 <c:if test="${sbb.getUser_type()==2}"><img height="26px" width="26px" src="img/exp_icon.png"> </c:if>
								 <c:if test="${sbb.getUser_type()==3}"><img height="26px" width="26px" src="img/vac_icon.png"></c:if>
								 <c:if test="${sbb.getUser_type()==4}"><img height="26px" width="26px" src="img/ses_icon.png"> </c:if>
								</div>
							</td>
							<td class="cell100 column4">
								<div>${sbb.sch_bo_date}</div>
							</td>
						</tr>
					</c:forEach>
					
					<c:if test="${listcount==0}">
					  <tr >
					 	<td colspan="4" class="cell100 column3" style="text-align:center">
					 	  <font size=2>등록된 글이 없습니다.</font></td>
					   </tr>
					 </c:if>
				<tr class="h30 lime center row100 body">
						<td class="cell100 column1 paging" colspan=5>
						<c:if test="${page <=1}">
						 이전 &nbsp;
						</c:if>
						<c:if test="${page >1 }">
							<a href="./ListPage.schbo?page=${page-1}${sessionScope.url}">이전</a>
						</c:if>
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a==page}">
							${a}
						</c:if>
						<c:if test="${a!=page}">
						<a href="./ListPage.schbo?page=${a}${sessionScope.url}">${a}</a>
						</c:if>
						</c:forEach>
						
						<c:if test="${page >= maxpage }">
							&nbsp; 다음
						</c:if>
						<c:if test="${page <maxpage }">
							<a href="./ListPage.schbo?page=${page+1}${sessionScope.url}">&nbsp;다음</a>
						</c:if>
						</td>
				</tr>
			   </tbody>
			</table> 
	
	
			</div>
			</div>


	  <div id="searching">
	  	  <form>
		 	<div><label><input type="checkbox" id="loadcheck" value="load" name="loadcheck"> 상위검색저장</label></div>
		 	<div>
		 			<select id="searchindex" name="searchindex">
						<option value="title">제목</option>
						<option value="user">ID</option>
						<option value="content">내용</option>	</select>
			</div>
			<div><input type="text" name="searchcontent" id="searchcontent" placeholder="검색할 단어를 입력하세요"></div>
		 	<div><button class="btns" type="button" id="searchbtn" >검색</button></div>
	 	</form>
	 </div>

 	<div id="goright">
 	<c:if test="${sessionScope.user_id!=null}">
  	<a href="./InsertPage.schbo">[글쓰기]</a>
  	</c:if> 
 	</div>


	</div>
</body>
</html>