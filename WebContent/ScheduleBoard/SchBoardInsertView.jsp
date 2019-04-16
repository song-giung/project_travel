<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ include file="../MainHeader.jsp" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jQuery.com/jquery-latest.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SchBoardInsertView</title>
<script>
$(function(){
	$("#schno option:selected").removeAttr("selected");
	
	$("#goback").click(function(){
		var check = confirm("입력 취소하고 뒤로가시겠습니까 ? ");
		if(check==true)
			history.back()
	})	
	
	$("#schno").change(function(){
		var schno=$("#schno").val();
		$("#sch_no").val(schno);
	})
	
	$("#insertform").submit(function(e){
		if($("#sch_no").val()==""){
			alert("일정을 선택해주세요");
			e.preventDefault();
		}
	})
	

})

</script>

<style>
	hr{border:0;border-top:1px solid rgba(0,0,0,.1)}
	@font-face{font-family:"nanums"; src:url('fonts/NanumSquareR.ttf')}
	@font-face{font-family:"nanumsB"; src:url('fonts/NanumSquareR.ttf')}

	body{font-family:"nanums"; margin :0px;}
	 .container{margin: 0px 150px; display:flex; flex-direction:column; width:1500px; }
	table{border-spacing:10px 10px; width:1500px;}
	th{font-size :30px; font-color: gray; border-bottom:1px dotted gray; height:60px; font-family:"nanums"; font-color:gray margin:0; letter-spacing:4px;  }
	td:first-child{ letter-spacing:4px; width:250px;}
	td:nth-child(2){ width:1000px;}
	.inin div{font-size:20px; text-align:right;}
	#schb_title{font-family:"nanums"; width: 1000px; height:35px; font-size: 27px;}
	#schb_content{ font-family:inherit; width: 1000px;  font-size:24px;}
	#buttons{text-align:center;}
	#schno{height: 30px; font-size:inherited}
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
		background:url("commboardimgs/schboardimgs1.jpg");
		background-size:cover;
		 background-repeat: no-repeat;
		border-radius:20px;
		}
	.container{margin:auto;}
</style>
</head>
<body>
<div class="container">
	<form action="./InsertAction.schbo" id="insertform" method="post" name="boardform">
			<table>
				<tr>
					<td colspan="3"><div id="compic" onClick="location.href='./ListPage.schbo'"></div>
					</td>
				</tr>
				<tr>
					<td colspan="3"> <input type="hidden" name="user_no" size="10" height="30" maxlength="30" value="${sessionScope.user_no}"> </td>
				</tr>
				<tr>
				<th colspan="3" > 일정게시판 게시판 글쓰기</th>
				</tr>
				<tr class="inin">
					<td> <div>제목  &nbsp;|</div></td>
					<td colspan="2"> <input type="text" id="schb_title" name="schb_title" size="50" maxlength="100" placeholder="제목을 입력하세요." value="" required> </td>
				</tr>
				<tr class="inin">
					<td> <div>공유할 일정 &nbsp;|</div></td>
					<td colspan="2"><select id="schno">
						<option selected></option>
						<c:forEach var="schs" items="${sblist}">
							<option value="${schs.sch_no}" selected="false">${schs.sch_title}</option>			
						</c:forEach>
						</select>
						<input type="hidden" name="sch_no" id="sch_no" value="">
					</td>
				</tr>
				<tr class="inin">
					<td> <div>내용 &nbsp;|</div></td>
					<td colspan="2"> <textarea id="schb_content" name="schb_content" id="comb_content" cols="70" rows="20" placeholder="내용을 입력하세요." required></textarea>
				</tr>
	
				<tr>
					<td id="buttons" colspan="3"> <input class="btns" type="submit" value="등록">
					<input  class="btns" type="button" id="goback" value="뒤로">
					<input  class="btns" type="reset" value="취소">
					</td> 
				</tr>
	
			</table>
	  </form>
</div>
</body>
</html>