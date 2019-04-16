<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<title>여행자용</title>
<link rel="stylesheet" href="css/css.css">
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(function(){
		$(".needlogin").css('display','none');
		$(".needlogin").fadeIn(2000,function(){
			$(".needlogin").css('display','block');
		})
	})
</script>
</head>
<style>
   
   body{
   margin :0px;
   background: url("yeosu1.png");
   background-size : cover;
   
   background-repeat: no-repeat;
   }
   #mainback{

   }
   #left{
        width: 48%;
      height : 700px;
      margin-right : 0px;
      float : left; 
        
   }
   #right{
      width: 48%;
      height : 700px;
      margin-left : 0px;
      float : right;
   }
   
   h1{
      text-align : center;
       font-size : 130px;
       
       z-index : 2;
       font-family : lemon;
       text-shadow:10px 10px 40px gray;
       color : #ffffff;
       margin-bottom : 5px;
       
   }
   
   #left h3, #right h3{font-family:nanumsb;
   font-size: 50px;
    text-align: center;
    line-height: 700px;}
    p{text-align:center;}
    
    
    
  /*   <!--로그인 해야될 떄--> */
  
    .container{
        width: 100%;
      height : 1000px;
      text-align:center;
      font-family:lemonl;
   }
    .needlogin{
      width:35%;
      margin:auto;
      font-size:50px;
      letter-spacing:3px;
      opacity:0.5;
        background-color:black;
        color:white;
        border-radius:50px;
       
   }
   .needlogin table span{font-family:lemonl;}
   .needlogin table{padding-top:30px;}
   input[type=text]{ width:300px; height:50px; font-size:40px;}
   input[type=password]{ width:300px; height:50px; font-size:40px;font-family:none;}
   .needlogin table{margin:auto;}
   .submitbtn{font-family:lemonl; margin-left:10px;height:110px;}
   
   .needlogin a{font-family:lemonl; text-align:right; text-decoration:none; color:white; font-size:30px; border-bottom:1px dotted gray;}
   .submitbtn:hover{ background-color: darkgray; color:white}
	.snip1384 h3{height:80%;font-family:nanumsB }
	.snip1384 p{font-size:30px; }
</style>


<body>
<div id="mainback" >
<jsp:include page="/MainHeader.jsp"></jsp:include>



<h1>For Traveler</h1>

<c:if test="${sessionScope.user_id!=null}">
<figure class="snip1384" id="left">
  <figcaption>
    <h3>여긴 어때요?</h3>
    <p>6가지 테마 별 여행지 추천으로 일정을 짜보세요!</p><i class="ion-ios-arrow-right"></i>
  </figcaption>
  <a href="./userTravelTemalist.tr"></a>
</figure>


<figure class="snip1384"  id="right" >
  <figcaption>
    <h3>이렇게 가보세요!</h3>
    <p>성향 별 사람들의 일정을 공유하고 체험해 보세요!</p>
    <i class="ion-ios-arrow-right"></i>
  </figcaption>
  <a href="./ListPage.schbo"></a>
</figure>
</c:if>

<c:if test="${sessionScope.user_id==null}">
<div class="container">
<div class="needlogin">
   <form name ="loginform.jsp" action="./loginProcess.net" method="post">
     <table>
        <tr> 
           <td><span>ID</span><b id="msgID"></b></td>
           <td><input type="text" name="user_id" id="user_id" placeholder="Enter id" ></td>
           <td rowspan="2"><input type="hidden" name="mainlogin" value="mainlogin">
         <button type="submit" class="submitbtn" >GO</button></td>
        </tr>
        <tr>
           <td><span>PW</span> <b id="msgPW"></b> </td>
           <td><input type="password" name="user_passwd" id="user_passwd" placeholder="Enter pw"><br></td>
        </tr>
        <tr>
           <td colspan="3"><a href="FindClientData.net" id="find">I FORGOT ID/PW</a><br>
         <a href="join.net" >WANT JOIN</a></td>
        </tr>
     </table>
   </form>
</div>
</div>


</c:if>
</div>
</body>
</html>