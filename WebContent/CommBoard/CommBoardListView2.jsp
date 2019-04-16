<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%System.out.println("!!!commboardListview2 실행중!!!"); %>


	<tbody>
	 <c:set var="num" value="${listcount-(page-1)*limit}"/>
	 <c:forEach var="cb" items="${cblist}">
	 <tr class="row100 body">
		<td class="cell100 column1">
			<c:out value="${num}"/>
			<c:set var="num" value="${num-1}"/>
			<input type="hidden" value="${num}" name="shownum" id="shownum">
		</td>
		<td class="cell100 column2">
			<div>
			<a href="./CommDetailView.combo?num=${cb.comm_bo_no}">${cb.comm_bo_title}</a>
			</div>
		</td>
		
		<td class="cell100 column3"> <div>${cb.getUser_id()}
		<c:if test="${cb.getUser_type()==1}"><img height="26px" width="26px" src="img/adv_icon.png"> </c:if>
		 <c:if test="${cb.getUser_type()==2}"><img height="26px" width="26px" src="img/exp_icon.png"> </c:if>
		 <c:if test="${cb.getUser_type()==3}"><img height="26px" width="26px" src="img/vac_icon.png"></c:if>
		 <c:if test="${cb.getUser_type()==4}"><img height="26px" width="26px" src="img/ses_icon.png"> </c:if>
			</div></td>
		<td class="cell100 column4"> <div>${cb.comm_bo_date}</div></td>
	</tr>
	</c:forEach>



<c:if test="${listcount==0}">
  <tr >
 	<td colspan="4" class="cell100 column3" style="text-align:center">
 	  <font size=2>등록된 글이 없습니다.</font></td>
   </tr>
 </c:if>
 

 <!-- ---------- -->
 
 	<tr class="h30 lime center row100 body">
		<td class="cell100 column1 paging" colspan=5>
			<c:if test="${page <=1}">
			 이전 &nbsp;
			</c:if>
			<c:if test="${page >1 }">
				<a href="./ListPage.combo?page=${page-1}&searchindex=${sessionScope.searchindex}&searchcontent=${sessionScope.searchcontent}${sessionScope.url}">이전</a>
			</c:if>
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
			<c:if test="${a==page}">
				${a}
			</c:if>
			<c:if test="${a!=page}">
			<a href="./ListPage.combo?page=${a}&searchindex=${sessionScope.searchindex}&searchcontent=${sessionScope.searchcontent}${sessionScope.url}">${a}</a>
			</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
				&nbsp; 다음
			</c:if>
			<c:if test="${page <maxpage }">
				<a href="./ListPage.combo?page=${page+1}&searchindex=${sessionScope.searchindex}&searchcontent=${sessionScope.searchcontent}${sessionScope.url}">&nbsp;다음</a>
			</c:if>
			</td>
			
			</tr>
		</tbody>