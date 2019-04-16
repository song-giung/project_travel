<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


				<tr>
					<td colspan="4"><c:if test="${upcheck==false}"><button  type="button" class="upbtn udbtn" value="${upcheck}"><img src="img/hold.png"></button></c:if>
				 					<c:if test="${upcheck==true }"><button   type="button" class="upbtn udbtn" value="${upcheck}"><img src="img/up.png"></button></c:if>
					 </td>
					<td colspan="4"><c:if test="${downcheck==false}"><button  type="button" class="dwnbtn udbtn" value="${downcheck}"><img src="img/hold.png"></button></c:if>
				 					<c:if test="${downcheck==true }"><button  type="button" class="dwnbtn udbtn" value="${downcheck}"><img src="img/down.png"></button></c:if>
					 </td>
				</tr>
				<tr>
					<td colspan="4">up(${uplist.getUp_total_count()})  </td>
					<td colspan="4">down(${downlist.getDown_total_count()}) </td>
				</tr>
				<tr>
					<td colspan="2"><img class="ccimg" height="25px" width="25px" src="img/adv_icon.png"></td>
					<td colspan="2"><img class="ccimg" height="25px" width="25px" src="img/exp_icon.png"></td>
					<td colspan="2"><img class="ccimg" height="25px" width="25px" src="img/vac_icon.png"></td>
					<td colspan="2"><img class="ccimg" height="25px" width="25px" src="img/ses_icon.png"> </td>
				</tr>
				<tr>
					<c:forEach var="typecount" items="${downlist.getDown_type_count()}" varStatus="index">
							 <td colspan="2"> up : ${uplist.getUp_type_count()[index.index]} / down:  ${typecount}</td>
					</c:forEach>
				</tr>