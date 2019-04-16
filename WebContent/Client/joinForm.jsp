<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<style>
	body{
	background-color :#E0F2F1;
	margin :0px;
	}
	label{text-align:center;}
	input[type=password]{font-family:none;}
</style>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function(){
		<%
		if(session.getAttribute("user_id") != null){%>
			location.href="./Mainpage.jsp";
			<%}%>
		
		var data_check = -1; //아이디 중복 확인 버튼 클릭 유무를 확인하기 위한 변수
							//아이디 존재하여 사용 불가능을 의미
		var sid, sid2;
		$("#message").css('display', 'none');//영역을 숨깁니다.
		$("#idcheck").click(function(){
			if($("input[name=user_id]").val()==''){
				$("#message").empty();
				$("#message").show();
				$("#message").append("중복 확인할 아이디를 입력하세요");
				$("#message").css('color','red');
				$("input[name=user_id]").focus();
				return false;
			}
			sid=$("input[name=user_id]").val();
			$.ajax({
				type : "GET",
				url : "IdcheckAjax.net",
				data : {"user_id" : $("input[name=user_id]").val()},
				success : function(response){
					data_check = response;
					$("#message").empty();
					$("#message").show();
					if(response ==1){
						$("#message").append("사용 가능한 아이디 입니다.");
						$("#message").css('color','red');
					}else{
						$("#message").append("중복된 아이디 입니다.");
						$("#message").css('color','red');
						$("input[name=user_id]").val('');
						$("input[name=user_id]").focus();
					}
				},
					error:function(){
						alert("data error2");
					}
			})//ajax end
		})//click end

	$('form').submit(function(){
		sid2 = $("input[name=user_id]").val();//사용 가능한 아이디인데 변경한 경우
		//중복체크 후 사용 가능한 아이디락 메시지 나왔지만 아이디 변경하고 중복 체크하지 않은 경우
		if(data_check == -1 || sid != sid2){
			$("#message").empty();
			$("#message").show();
			$("#message").append("ID 중복 확인을 하세요");
			$("#message").css('color','red');
			return false;
		}
	})//submit end
})//ready end
	
</script>
</head>
<script src = "http://code.jquery.com/jquery-3.3.1.js"></script>
	<script src = "./Client/join1.js"></script>
	<link href ="css/join1.css" rel="stylesheet">
<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>
	<div class="container">
	<form name="joinform" action="joinProcess.net" method="post">
	<h1>회원가입 페이지</h1>
	<hr>
	<b>아이디&nbsp;<input type="button" id="idcheck" value="중복 검사" >
	<b id="message"></b></b>
	<input type="text" name="user_id" id="user_id" placeholder="Enter id" ><br>
	<b>비밀번호<b id="msgPW"></b></b>
	<input type="password" name="user_passwd" id="user_passwd" placeholder="Enter password" ><br>
	<b>비밀번호 확인<b id="msgPW1"></b></b>
	<input type="password" name="user_passwd1" id="user_passwd1" ><br>
	<b>이름<b id="msgName"></b></b>
	<input type="text" name ="user_name" id="user_name" placeholder="Enter name" ><br>
	<b>이메일 주소<b id="msgEmail"></b></b>
	<input type="text" name="user_email" id="user_email" placeholder="Enter email" ><br><br>
	<hr>
	<b>성향<b id="msgType"></b></b>
	<h3>다음의 문항을 읽고 적합한 대답을 체크해 주세요</h3><br>
	<b>1. 나는 조용한것 보다는 수다스러운 편이다.</b>
	<div>
		<label><input type="radio" name="x1" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="x1" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="x1" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="x1" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="x1" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>2. 나는 주장이 강한 편이다.</b>
	<div>
		<label><input type="radio" name="x2" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="x2" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="x2" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="x2" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="x2" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>3. 나는 모험적인걸 좋아한다.</b>
	<div>
		<label><input type="radio" name="x3" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="x3" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="x3" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="x3" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="x3" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>4. 나는 힘이 넘치는 스타일이다.</b>
	<div>
		<label><input type="radio" name="x4" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="x4" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="x4" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="x4" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="x4" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>5. 나는 대담한 편이다</b>
	<div>
		<label><input type="radio" name="x5" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="x5" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="x5" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="x5" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="x5" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>6. 나는 상상력이 풍부한 편이다.</b>
	<div>
		<label><input type="radio" name="y1" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="y1" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="y1" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="y1" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="y1" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>7. 나는 창의적이라고 생각한다.</b>
	<div>
		<label><input type="radio" name="y2" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="y2" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="y2" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="y2" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="y2" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>8. 나는 호기심이 많다.</b>
	<div>
		<label><input type="radio" name="y3" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="y3" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="y3" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="y3" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="y3" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>9. 나는 사색을 즐긴다.</b>
	<div>
		<label><input type="radio" name="y4" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="y4" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="y4" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="y4" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="y4" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	<b>10. 나는 세련된 사람이다.</b>
	<div>
		<label><input type="radio" name="y5" value="2" ><span>매우 그렇다</span></label>
		<label><input type="radio" name="y5" value="1" ><span>그렇다</span></label>
		<label><input type="radio" name="y5" value="0" ><span>보통이다</span></label>
		<label><input type="radio" name="y5" value="-1" ><span>그렇지 않다</span></label>
		<label><input type="radio" name="y5" value="-2" ><span>매우 그렇지 않다</span></label>
	</div><br>
	
	<div class="clearfix">
		<button type="submit" class="submitbtn">회원가입</button>
		<button type="reset" class="cancelbtn">다시작성</button>
	</div>
	</form>
	</div>
</body>
</html>