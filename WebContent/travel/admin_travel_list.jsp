<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="travel" items="${travellist}">
						<tr class="row100 body">
							<td class="cell100 column1">
								<input type="checkbox" name="check" value="${travel.travel_no }">
							</td>
							<td class="cell100 column2">
								${travel.travel_no}
							</td>
							<td class="cell100 column3">
								<a href="./travel_info.tr?no=${travel.travel_no}"> ${travel.travel_name}</a>
							</td>
							<td class="cell100 column4">
								${travel.travel_location }
							</td>
						</tr>
</c:forEach>