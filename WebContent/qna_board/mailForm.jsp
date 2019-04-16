<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<style>
.container{margin-top:50px; font-family:nanums; font-size:20px; width:830px; height:440px;}
body{
   background-color : white;
   margin :0px auto;
   font-size:20px;
   }
form{
   font-size:20px;
   text-align:center;
   height : 80%;
   width: 98%;
   margin : auto;
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
table {
margin:auto;
 height : 80%;
   width: 98%;
background-color:white;
border: 1px solid lightgray;
border-radius: 20px;
}

#board_content{
    resize: none;
   margin : auto;
   height : 100px;
}

table tr:nth-child(1){background-color:#E0F2F1;}
input[type='text']{width:98% ; font-size:20px; }
textarea{width:98%}
tabe td{
   border-right:1px solid lightgray;
   }
input[type=submit] {
   font-size:20px;
   border-radius:20px;
   color:white;
    background-color: teal;
    }
 input[type=reset] {
    font-size:20px;
   border-radius:20px;
   color:white;
    background-color: crimson;
    }
</style>
</head>
<body>
   <div class="container">
   <form action="mailSend.jsp" method="post">
      <table>
         <tr>
            <td colspan=2>문의메일 보내기</td>
         </tr>
         <tr>
            <td>보내는 사람 메일</td>
            <td><input type="text" name="sender" value="rodgj@naver.com" readonly></td>
         </tr>
         <tr>
            <td>받는 사람 메일</td>
            <td><input type="text" name="receiver"
               value="ghfn901@naver.com" readonly></td>
         </tr>
         <tr>
            <td>제목</td>
            <td><input type="text" name="subject"></td>
         </tr>
         <tr>
            <td>내용</td>
            <td><textarea name="content" cols=40 rows=10></textarea></td>
         </tr>
         <tr>
            <td colspan=2><input type="submit" value="보내기"
               onclick="history.go(-1)"> <input type="reset" value="닫기"
               onclick="self.close()"></td>
         </tr>
      </table>
   </form>
   </div>
</body>
</html>