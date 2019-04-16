<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
   .fc-title{
    	color:darkgrey;
    }
</style>
<div id="calendar" class="box1"></div>
<link rel='stylesheet' href='./Schedule/calendar/fullcalendar.css' />
<script src='./Schedule/js/jquery-3.3.1.min.js'></script>
<script src='./Schedule/calendar/lib/moment.min.js'></script>
<script src='./Schedule/calendar/fullcalendar.js'></script>
<script>
	$(function(){
	//list
	//detail
	//pocket
		var array = new Array();
		
		<c:forEach var="item" items="${detail}">
			var det = new Object();
			det.id ="${item.user_no}"+"${item.sch_no}"+"${item.detail_no}";
			det.title="${item.det_title}"
			<c:if test="${!empty item.det_start}">		
				det.start="${item.det_start}";
			</c:if>
			<c:if test="${!empty item.det_end}">		
				det.end="${item.det_end}" ;
			</c:if>
			<c:if test="${!empty item.det_date}">		
				det.start="${item.det_date}";
				det.end="${item.det_date}"
			</c:if>
			det.color="#E0F2F1";
			det.allDay =true	
			array.push(det);
		</c:forEach> 
		<c:forEach var='item' items='${list}' >	
			var sch = new Object();
			sch.id = "${item.user_no}"+"${item.sch_no}"
			sch.start = "${item.start_date}" 
			sch.end = "${item.end_date}"  
			sch.title = "${item.sch_title}"
			
			sch.allDay=true
			
			sch.color="teal"
			array.push(sch);
		</c:forEach>
		var jsonInfo = JSON.stringify(array);

				
		$("#calendar").fullCalendar({
			lang:'ko',
			header: { left: 'prev,next today', center: 'title', right:'month,basicWeek,basicDay' },
			events: JSON.parse(jsonInfo),
			eventDataTransform: function(event) {                                                                                                                                
				  if(event.allDay) {                                                                                                                                               
				    event.end = moment(event.end).add(1, 'days')                                                                                                                 
				  }
				  return event;  
				}  
		
		})		
		
		
		
	})//function end


</script>


 
