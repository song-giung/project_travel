<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<title>계정 찾기</title>
<style>
	body{
	background-color : #E0F2F1;

	margin :0px;
	}
	fieldset{
		width : 600px;
		margin : auto;
		border :none;
		background : white;
		padding: 0;
	}
	#findidform1{
		margin : auto;
		border :none;
		background : white;
		width : 48%;
		height:100%;
		float : left;
		padding: 5px;
		text-align: center;
	}
	#findpwform1{
		margin : auto;
		border :none;
		background : white;
		width : 48%;
		height:100%;
		float : right;
		border-left: 1px solid black;
		padding: 5px;
		text-align: center;
	}

	#findid,#findpw{
	width : 100px;
	height : 34px;
	margin :8px 28px 8px 8px;
	border-radius : 5px;
	background-color: white; 
    color: black; 
    border: 2px solid #008CBA;
	}
	#findid:hover, #findpw:hover{
		background-color: #008CBA;
   		 color: white;
	}
</style>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(function(){
		$("#findid").on("click",function(){
			 $("#msgNameid").css('display','none');
		     if($.trim($("#find_id_name").val()) == ""){
		    	  $("#msgNameid").empty();
				  $("#msgNameid").show();
				  $("#msgNameid").append(" 이름을 입력하세요.");
				  $("#msgNameid").css('color','red');
		          $("#find_id_name").focus();
		          return false;
		      }
		     $("#msgEmailid").css('display','none');
		     if($.trim($("#find_id_email").val()) ==""){
		      $("#msgEmailid").empty();
			  $("#msgEmailid").show();
			  $("#msgEmailid").append(" 이메일을 입력하세요.");
			  $("#msgEmailid").css('color','red');
		      $("#find_id_email").focus();
		      return false;
		     }
			
			
			var form = document.findidform;
			var url = "./FindIDAction.net";
			window.open("","popup_window", "width=350, height=150, top=400, left=800");
			form.action = url;
			form.method = "post";
			form.target = "popup_window";
			$("#findidform").submit();
		})
		$("#findpw").on("click",function(){
			if($.trim($("#find_pw_id").val()) == ""){
	    	    $("#msgIdpw").empty();
				$("#msgIdpw").show();
				$("#msgIdpw").append(" ID를 입력하세요");
				$("#msgIdpw").css('color','red');
	        	$("#find_pw_id").focus();
	          return false;
	      }
		 if($.trim($("#find_pw_name").val()) == ""){
	    	  $("#msgNamepw").empty();
			  $("#msgNamepw").show();
			  $("#msgNamepw").append(" 이름을 입력하세요.");
			  $("#msgNamepw").css('color','red');
	          $("#find_pw_name").focus();
	          return false;
	      }
	     $("#msgEmailpw").css('display','none');
	     if($.trim($("#find_pw_email").val()) ==""){
	      $("#msgEmailpw").empty();
		  $("#msgEmailpw").show();
		  $("#msgEmailpw").append(" 이메일을 입력하세요.");
		  $("#msgEmailpw").css('color','red');
	      $("#find_pw_email").focus();
	      return false;
	     }
			
			var form = document.findpwform;
			var url = "./FindPasswdAction.net";
			window.open("","popup_window", "width=350, height=150, top=400, left=800");
			form.action = url;
			form.method = "post";
			form.target = "popup_window";
			$("#findpwform").submit();
		})		
	})
</script>
<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>
<br>
<fieldset>
<div id="findidform1">
<form name="findidform" id="findidform" >
	<div>
		<h3>아이디 찾기</h3>
	</div>
	<br><b id="msgNameid"></b>
	<p>이&nbsp;&nbsp;&nbsp;름 : <input type="text" id="find_id_name" name="user_name"> </p>
	
	<br><b id="msgEmailid"></b>
	<p>이메일 : <input type="text" id="find_id_email" name="user_email"> </p>
	<input type="button" id="findid" value="찾기" >
</form>
</div>

<div id="findpwform1" >
<form name="findpwform" id="findpwform" >
	<div>
		<h3>비밀번호 찾기</h3>
	</div>
	<br><b id="msgIdpw"></b>
	<p>아이디 : <input type="text" id="find_pw_id" name="user_id"></p>
	<br><b id="msgNamepw"></b>
	<p>이&nbsp;&nbsp;&nbsp;름 : <input type="text" id="find_pw_name" name="user_name"></p>
	<br><b id="msgEmailpw"></b>
	<p>이메일 : <input type="text" id="find_pw_email" name="user_email"></p>
	<input type="button" id="findpw" value="찾기">
</form>
</div>
</fieldset>
</body>
</html>