<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>문의하기</title>
<link href="css/form3.css" rel="stylesheet">
<style>
body{
   background-color : white;
   margin :0px;
   }
form{
   text-align:center;
   height : 100px;
   margin : 0px;
}

#register{
   margin: 0px;
   width:20%;
   height : 100px;
   display : inline-block;
   position:absolute;
   right:10%
   
   
}
#avi{
	
	width:100%;
	height:280px;
	margin : 0px;
	
    
	}
#top{
	background-repeat: no-repeat; 
	background-position: bottom center;
	background-image: url('qna_board/asd.jpg');
	background-size:80%;
	width:100%;
	height:417px;
	margin-bottom: 20px;
}
#board_content{
   position:absolute;
   left:10%;
   margin-bottom: 30px;
}
table {background-color:white;border: 1px solid silver; border-radius: 10px;}
#board_content{
    resize: none;
   margin : auto;
   height : 100px;
}
.deleteBtn{
width:10%;
}
.modifyBtn{
width:10%;
}
.replydate{
width:10%;
}
#replytable{
	margin-bottom : 200px;
}
</style>
</head>

<body>
<%@ include file="/MainHeader.jsp" %>
<div id="top"></div>
<div id="avi">
<c:set var="boarddata" value="${boarddata}"/>
<br><br>
<table>

   <tr>
      <th colspan="2">문의하기 </th>
   </tr>
   <tr>
      <th width="20%">글쓴이</th> 
      <td>${boarddata.USER_ID}</td>
   </tr>
   <tr>
      <th>제목</th> 
      <td>${boarddata.BOARD_SUBJECT}</td>
   </tr>
   <tr>
      <th>내 용</th> 
      <td>${boarddata.BOARD_CONTENT}</td>
   </tr>
   <tr>
      <th>첨부파일</th>
       
      <td>
      <c:if test="${!empty boarddata.BOARD_FILE}">
         <img src="./image/down.png" width="10px">
      </c:if>
         <a href="./BoardFileDown.bo?filename=${boarddata.BOARD_FILE}">
                                    ${boarddata.BOARD_FILE}</a>
      </td>
   </tr>

   <tr>
      <th colspan="2" class="center">
      <c:if test="${boarddata.USER_ID ==user_id && sessionScope.user_id !='admin'}">
      <a href="./BoardModifyView.bo?num=${boarddata.BOARD_NUM}">수정</a>&nbsp;&nbsp;
      <a href="./BoardDelete.bo?num=${boarddata.BOARD_NUM}">삭제</a>&nbsp;&nbsp;
      </c:if>
      <c:if test="${ sessionScope.user_id=='admin'}">
      <a href="./BoardModifyView.bo?num=${boarddata.BOARD_NUM}">수정</a>&nbsp;&nbsp;
      <a href="./BoardDelete.bo?num=${boarddata.BOARD_NUM}">삭제</a>&nbsp;&nbsp;
   
      
      </c:if>
      <a href="./BoardList.bo">목록</a>
      

      </th> 
   </tr>
      
      
</table>


<form action="./BoardReplyAction.bo" method="post" name="boardform">

<c:if test="${boarddata.USER_ID ==user_id || sessionScope.user_id =='admin'}">
      <textarea name="content" id="board_content"
               cols="3" rows="10" ></textarea>
      <input type="submit" id="register" value="등록">
         </c:if>


         
         
<input type="hidden" name="num" value="${boarddata.BOARD_NUM}">
<input type="hidden" name="USER_ID" value="${user_id}">
<input type="hidden" name="BOARD_RE_REF" value="${boarddata.BOARD_RE_REF}">
<input type="hidden" name="BOARD_RE_LEV" value="${boarddata.BOARD_RE_LEV}">
<input type="hidden" name="BOARD_RE_SEQ" value="${boarddata.BOARD_RE_SEQ}">
</form>

   <table id="replytable">
      <c:forEach var="b" items="${re }">
      <tr>
      
         <td>${b.USER_ID}</td>
         <td class="contentArea">${b.BOARD_CONTENT}</td>
         
         <td class="replydate">${b.BOARD_DATE}</td>
    
         <c:if test="${b.USER_ID == sessionScope.user_id|| sessionScope.user_id =='admin'}">
      
         <td class="modifyBtn">
            <button class="modify" name="modify" value="${b.BOARD_NUM}">수정</button>
         </td>
         
         <td class="deleteBtn">
            <button class="delete" name="delete" value="${b.BOARD_NUM}">삭제</button>
         
         </td>
         
            </c:if>
            
      </tr>
       
    </c:forEach>  
 
   </table>
   

 
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
   $(document).ready(function () {
      
      $(document).on('click','.modify',function (){
         var content = $(this).parents().children('.contentArea').html();
         var num = $(this).val();
         console.log(content);
         $(this).parents().children('.contentArea').empty().prepend('<input type="text" name="content" value="' + content + '">');
         $(this).parent().empty().prepend('<button class="modifyActionBtn" name="modifyActionBtn" value=' + num + '>수정완료</button>');
      });
      
      $(document).on('click', '.modifyActionBtn', function () {
         var num = $(this).val();
         var content = $(this).parents().children('.contentArea').children().val();
         var o = $(this);
         $.ajax({
            type : "POST",
            data : {'num' : num, 'content' : content},
            url : "BoardModifyAction.bo",
            success : function(){
               alert("수정 완료");
                 //o.text("수정");
                 o.parents().children('.contentArea').empty().text(content);
                 o.parent().empty().prepend('<button class="modify" name="modify" value=' + num + '>수정</button>');
            },
            error: function () {
               console.log(content);
               console.log(num);
            }
         
         });
         return false;
      });
      $(".delete").click(function(){
         location.href="./BoardReplyDeleteAction.bo?num=" + $(".delete").val()+"&board_num=" + ${boarddata.BOARD_NUM};
      })
         
   });

</script>
</div>
</body>
</html>