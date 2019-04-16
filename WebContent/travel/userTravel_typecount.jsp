<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>


<input type="hidden" id="upcheck"value="${up_check }">
<input type="hidden" id="downcheck"value="${down_check }">
<div class="w3-card" style="border-radius: 10px;width: 25%;margin:0 auto;">
			<img src="./img/1.png" style="border-radius: 10px;height: 297px;">
				<div class="w3-container" style="height: 50px">
					<span style="font-size:large;">Up : ${u1 }  &nbsp;Down : ${d1 }</span>
					
			</div>
		</div>
		
		<div class="w3-card" style="border-radius: 10px;width: 25%;margin:0 auto;">
			<img src="./img/2.png" style="border-radius: 10px; height: 297px;">
				<div class="w3-container" style="height: 50px">
					<span style="font-size:large;">Up : ${u2 }  &nbsp;Down : ${d2 }</span>
					
			</div>
			</div>
			
		<div class="w3-card" style="border-radius: 10px;width: 25%;margin:0 auto;">
			<img src="./img/3.png" style="border-radius: 10px; height: 297px;">
				<div class="w3-container" style="height: 50px">
					<span style="font-size:large;">Up : ${u3 } &nbsp;Down : ${d3 }</span>
					
			</div>
			</div>
			
		<div class="w3-card" style="border-radius: 10px;width: 25%;margin:0 auto;">
			<img src="./img/4.png" style="border-radius: 10px; height: 297px;">
				<div class="w3-container" style="height: 50px">
					<span style="font-size:large;">Up : ${u4 } &nbsp;Down : ${d4 }</span>
					
			</div>
		</div>


</body>