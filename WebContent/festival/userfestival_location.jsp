<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:set var ="i" value="0"/>
	<c:set var ="j" value="3"/>
		<c:forEach items="${festivalList}" var="fs">
		
		 		 <c:if test="${i%j ==0 }">
				<tr>
				</c:if>
				<td>
		
				 	<div  id="festival_list" style="width: 380px; height: 300px; border-radius: 10px;text-align: center;">
					<form class="f_list" action="./userFestival_info.fs" style="width:500px;">
					<input type="hidden"  name="no" value="${fs.festival_no }">
					<div class="w3-card" style="border-radius: 10px;">
					<img src="./festivalUpload/${fs.festival_img_name }" style="width: 100%;height: 250px;border-radius: 10px;">
						<div class="w3-container" style="height: 50px">
							<h4><b>${fs.festival_name }</b></h4>
					</div>
					</div>

					</form>
					</div> 
				</td>
				<c:if test="${i%j== j-1}">
				</tr>
				</c:if>
				<c:set var="i" value="${i+1 }"/> 
		 
		
		</c:forEach> 