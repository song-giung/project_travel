<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%System.out.println("!!!schBoardListView2 실행중!!!"); %>

<tbody>
			   	 	<c:set var="num" value="${listcount-(page-1)*limit}"/>
					<c:forEach var="sbb" items="${sbb}">
						<tr class="row100 body">
							<td class="cell100 column1">
								<c:out value="${num}"/>
								<c:set var="num" value="${num-1}"/>
							</td>
							<td class="cell100 column2">
								<div>
								<a href="./SchDetailView.schbo?num=${sbb.sch_bo_no}">${sbb.sch_bo_title}</a>
								</div>
							</td>
							<td class="cell100 column3">
								<div>${sbb.getUser_id()}
								<c:if test="${sbb.getUser_type()==1}"><img height="26px" width="26px" src="img/adv_icon.png"> </c:if>
								 <c:if test="${sbb.getUser_type()==2}"><img height="26px" width="26px" src="img/exp_icon.png"> </c:if>
								 <c:if test="${sbb.getUser_type()==3}"><img height="26px" width="26px" src="img/vac_icon.png"></c:if>
								 <c:if test="${sbb.getUser_type()==4}"><img height="26px" width="26px" src="img/ses_icon.png"> </c:if>
								</div>
							</td>
							<td class="cell100 column4">
								<div>${sbb.sch_bo_date}</div>
							</td>
						</tr>
					</c:forEach>
					
					<c:if test="${listcount==0}">
					  <tr >
					 	<td colspan="4" class="cell100 column3" style="text-align:center">
					 	  <font size=2>등록된 글이 없습니다.</font></td>
					   </tr>
					 </c:if>
				<tr class="h30 lime center row100 body">
						<td class="cell100 column1 paging" colspan=4>
						<c:if test="${page <=1}">
						 이전 &nbsp;
						</c:if>
						<c:if test="${page >1 }">
							<a href="./ListPage.schbo?page=${page-1}${sessionScope.url}">이전</a>
						</c:if>
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a==page}">
							${a}
						</c:if>
						<c:if test="${a!=page}">
						<a href="./ListPage.schbo?page=${a}${sessionScope.url}">${a}</a>
						</c:if>
						</c:forEach>
						
						<c:if test="${page >= maxpage }">
							&nbsp; 다음
						</c:if>
						<c:if test="${page <maxpage }">
							<a href="./ListPage.schbo?page=${page+1}${sessionScope.url}">&nbsp;다음</a>
						</c:if>
						</td>
				</tr>
			   </tbody>