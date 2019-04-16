$(document).ready(function(){

	   //회원가입 버튼 클릭했을 경우
	   $("form").submit(function (){     
		   //id 공백 유효성 검사
	       if($.trim($("#user_id").val()) == ""){
	    	    $("#message").empty();
				$("#message").show();
				$("#message").append(" ID를 입력하세요");
				$("#message").css('color','red');
	        	$("#user_id").focus();
	          return false;
	      }
	     
	     //비밀번호 공백 유효성 검사
	     $("#msgPW").css('display','none');
	      if($.trim($("#user_passwd").val()) == ""){
	    	$("#msgPW").empty();
			$("#msgPW").show();
			$("#msgPW").append(" 비밀번호를 입력하세요");
			$("#msgPW").css('color','red');
	        $("#user_passwd").focus();
	      return false;
	     }
	     
	     //비밀번호 확인 유효성 검사
	      $("#msgPW1").css('display','none');
	     if($.trim($("#user_passwd").val()) != $("#user_passwd1").val()){
	      $("#msgPW1").empty();
		  $("#msgPW1").show();
		  $("#msgPW1").append(" 비밀번호가 다릅니다.");
		  $("#msgPW1").css('color','red');
	      $("#user_passwd1").empty();
	      $("#user_passwd1").focus();
	      return false;
	     }
	     
	     //이름 공백 유효성 검사
	     $("#msgName").css('display','none');
	     if($.trim($("#user_name").val()) == ""){
	    	  $("#msgName").empty();
			  $("#msgName").show();
			  $("#msgName").append(" 이름을 입력하세요.");
			  $("#msgName").css('color','red');
	          $("#user_name").focus();
	          return false;
	      }
	     
	   //email 공백 유효성 검사
	     $("#msgEmail").css('display','none');
	     if($.trim($("#user_email").val()) ==""){
	      $("#msgEmail").empty();
		  $("#msgEmail").show();
		  $("#msgEmail").append(" 이메일을 입력하세요.");
		  $("#msgEmail").css('color','red');
	      $("#user_email").focus();
	      return false;
	     }

	     //라디오 버튼 선택 유효성 검사
	     /*if($("#gender1")is(":checked") == false &&
	     $("#gender2").is(":checked") == false){
	        alert("남, 여 중에서 1개를 선택하세요");
	          return false;}*/
	     $("#msgType").css('display','none');
	     if($('input:radio:checked').length!=10){
	    	 $("#msgType").empty();
			 $("#msgType").show();
			 $("#msgType").append("모든 문항을 선택해주세요");
			 $("#msgType").css('color','red');
	    	 return false;
	     }
	     	     
	   });  
	  
 });
  