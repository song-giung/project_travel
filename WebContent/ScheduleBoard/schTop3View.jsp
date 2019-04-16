<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
				<c:forEach var="sbb3" items="${sbb3}">
		<div class="best3each" onclick="location.href='./SchDetailView.schbo?num=${sbb3.sch_bo_no}'">
			글 제목: ${sbb3.sch_bo_title}
			<div>작성자 : ${sbb3.user_id}</div>
			<div>
									<c:if test="${sbb3.getUser_type()==1}"><img height="26px" width="26px" src="img/adv_icon.png"> </c:if>
								 <c:if test="${sbb3.getUser_type()==2}"><img height="26px" width="26px" src="img/exp_icon.png"> </c:if>
								 <c:if test="${sbb3.getUser_type()==3}"><img height="26px" width="26px" src="img/vac_icon.png"></c:if>
								 <c:if test="${sbb3.getUser_type()==4}"><img height="26px" width="26px" src="img/ses_icon.png"> </c:if>
			</div>
		</div>
		</c:forEach>