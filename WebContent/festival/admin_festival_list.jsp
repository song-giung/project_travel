<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach var="festival" items="${festivalList}">
						<tr class="row100 body">
							<td class="cell100 column1">
								<input type="checkbox" name="check" value="${festival.festival_no }">
							</td>
							<td class="cell100 column2">
								${festival.festival_no}
							</td>
							<td class="cell100 column3">
								<a href="./festival_info.fs?no=${festival.festival_no}"> ${festival.festival_name}</a>
							</td>
							<td class="cell100 column4">
								${festival.location }
							</td>
						</tr>
</c:forEach>