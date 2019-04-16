<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>여행자 유형이란?</title>
<style>
	body{
	font-size:20px;
	background-color:#E0F2F1;
	margin :0px;
	}
	#center{
	margin-top :50px;
	text-align:center;
	display:flex;
	flex-direction:row;
	flex-wrap:wrap;
	}
	#type{
		color : red;
	}

	.border1{ margin:auto; width:1000px; height:600px; display:flex; flex-direction:row; flex-wrap: wrap}
	.border1 div{font-size:25px;border:1px dotted lightgrey; width:47%; border-radius:40px; background-color: mintcream; padding:10px}
	#section1,#section2,#section3,#section4{display:table;height:295px}
	.span{display:table-cell;vertical-align:middle}
	#type{font-weight:bold}
	#left{margin:auto;}
	#bottom {border: 1px dotted white; padding-top:30px; margin:auto; font-size:30px; }
	#bottom pre{ letter-spacing:2px;     line-height: 1.3;}
	.border1 img{width:30px; height:30px}
</style>
</head>
<body>
<jsp:include page="/MainHeader.jsp"></jsp:include>

<div id="center">
<div id="left"><img id="type" src="./Client/type.png"/> <h4>외·내향성과 개방성을 조합하면 위 <그림>과 같은 사분면을 그릴 수 있다.</h4></div>
		
		
<div class="border1">
	
	
	<div id="section2">
	<span class='span'>
		<img src='img/exp_icon.png'> 경험 추구 여행자<br><br>
		<span id="type">내향적이고 개방성이 높은 사람(2사분면)</span>은 이국적인 문화유적지나 마을을 찾아다니며<br>
		개인과 사회와 역사에 대해 성찰하기를 즐긴다. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
	</div>
	<div id="section1">
	<span class='span'>
		<img src='img/adv_icon.png'> 모험 추구 여행자 <br><br>
		<span id="type">외향적이며 개방성이 높은 사람(1사분면)</span>에게 좋은 여행은 문화적 차이가 크고 고향에서
		멀리 떨어진 여행지에서 흥미롭고 짜릿한 경험을 하며 새로운 친구를 찾는 여행이다.
	</span >
	</div>
	<div id="section3">
	<span class='span'>
		<img src='img/vac_icon.png'> 안정 추구 여행자 <br><br>
		<span id="type">내향적이고 개방성이 낮은 사람(3사분면)</span>은 조용한 휴양지나 작은 마을에 오래 머무르며<br>
		푸근하고 아늑한 느낌을 받을 때 만족감을 느낀다.
	</span>
	</div>
	<div id="section4">
	<span class='span'>
		<img src='img/ses_icon.png'> 감각 추구 여행자<br><br>
		<span id="type">외향적이고 개방성이 낮은 사람(4사분면)</span>은 보다 친숙한 문화권이나 유명한 리조트 관광지를<br>
		여행하며 신나는 활동을 즐기고 유쾌한 친구들을 많이 만나볼수록 즐거워한다.
	</span>
	</div>

</div>

	<div id="bottom">
	<hr>
	<!-- <p>
		당신이 소심하고 새로운 환경을 무서워한다고 해서 ‘나는 여행에 어울리지 않는다’라고 <br>
		생각할 필요는 없다. 단지 당신과 어울리지 않는 여행(이를테면 1사분면 여행)에 덴 것뿐일지도<br>
		모른다. 성격심리학과 여행 연구를 조합한 여행심리학의 도움을 받으면,<br>
		당신도 얼마든지 자신에 어울리는 여행을 찾아낼 수 있다. 외·내향성과 개방성을 척도로<br>
		“나는 어떤 사람일까?”라는 질문에 답할 수 있다면 나에게 어울리는 여행이 무엇인지도 알게 된다.<br>
		외·내향성과 개방성은 누구나 쉽게 알아볼 수 있는 성격 특성이다. 자신의 외·내향성과 개방성을<br>
		파악했다면 좋은 여행을 위한 준비를 절반은 끝낸 셈이다. 이제는 나머지 반쪽,<br> 
		즉 ‘여행의 다양한 요소들’에 대해 생각할 때다. “나는 어떤 여행을 즐기는사람인가?”라는<br>
		질문에 답을 찾았다면, 이제 “나는 여행에서 무엇을 해야 할까?”에 답해보자. 
		</p> -->
		
		<pre>
		당신이 소심하고 새로운 환경을 무서워한다고 해서 ‘나는 여행에 어울리지 않는다’라고 생각할 필요는 없다.
		단지 당신과 어울리지 않는 여행(이를테면 1사분면 여행)에 덴 것뿐일지도 모른다.
		성격심리학과 여행 연구를 조합한 여행심리학의 도움을 받으면, 당신도 얼마든지 자신에 어울리는 여행을 찾아낼 수 있다.
		외·내향성과 개방성을 척도로 “나는 어떤 사람일까?”라는 질문에 답할 수 있다면 나에게 어울리는 여행이 무엇인지도 알게 된다.
		외·내향성과 개방성은 누구나 쉽게 알아볼 수 있는 성격 특성이다.
		자신의 외·내향성과 개방성을 파악했다면 좋은 여행을 위한 준비를 절반은 끝낸 셈이다.
		이제는 나머지 반쪽, 즉 ‘여행의 다양한 요소들’에 대해 생각할 때다.
		“나는 어떤 여행을 즐기는사람인가?”라는 질문에 답을 찾았다면, 이제 “나는 여행에서 무엇을 해야 할까?”에 답해보자. 
		</pre>
	</div>
</div>
</body>
</html>