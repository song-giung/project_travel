$(document).ready(function(){

	   //회원가입 버튼 클릭했을 경우
	   $("form").submit(function (){     
		   //id 공백 유효성 검사
		   $("#msgID").css('display','none');
	       if($.trim($("#user_id").val()) == ""){
	    	  $("#msgID").empty();
	 		  $("#msgID").show();
	 		  $("#msgID").append(" 아이디를 입력하세요");
	 		  $("#msgID").css('color','red');
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
	    
	   });  
	  
 });
  