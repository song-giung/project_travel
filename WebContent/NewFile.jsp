<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(function(){
		//확인 | 취소 란에 확인을 누르면 true가 반환
        var con =confirm("정말 삭제 하시겠습니까?");
        //확인을 누르면 true가 반환됨.
        if(con==true){
        //삭제 처리 창 띄워보자.
	       alert("삭제가 완료 되었습니다.");
	     }else{
	    	 alert("삭제 안됨");
	     }
		 
	})
</script>
<body>

</body>
</html>