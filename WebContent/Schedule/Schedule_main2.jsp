<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./Schedule/css/file_1.css">
<script src="./Schedule/js/jquery-3.3.1.min.js"></script>
<script>

	$(function(){
	
		
		
		  	$.ajax({
			url : "./Calendar_load.s",
			success : function(data){		
				$('#cal_view').prepend(data);
			},
			error : function(){
				alert('error');
			}
		})  //달력 load ajax
	
		
		$(document).on('click',"#pocket_del",(function(){
			var ch ='';
			var check ='';
			$("input:checkbox[name='pock_trav_list']").each(function(){
				if($(this).is(':checked')){
				ch += $(this).val()+" ";
				check="travel";
				}
			})			
			
			$("input:checkbox[name='pock_fest_list']").each(function(){
				if($(this).is(':checked')){
				ch += $(this).val()+" ";
				check="festival";
				}
			})			
			
			console.log('check : '+check);
				$.ajax({
					type : 'post',
					data : {"ch":ch,"check":check},
					url : "./pocket_del.s",	
					success : function(data){
						console.log(data);
						$('#pocket_view').empty().prepend(data);
					},
					error : function(){
						alert('AjaxDelPocket error');
					}
				}) //ajax end					
		}))//pocket del click end
		
		
		
	//일정 삭제 ajax
			
		$(document).on('click','#schedule_del',function(){
			 var ch1 = '';
				$("input:checkbox[name='sch_list']").each(function(){
					if($(this).is(':checked'))
						ch1+=$(this).val()+" ";
				})	
				
			$.ajax({			//일정 지우기
				type : 'post',
				data : {"ch1":ch1},
				url : "./schedule_del.s",

				success : function(data){				
					$('#sch_det').empty().prepend(data);

					$.ajax({ //일정 갱신ajax
						type:'post',
						url : "./detail_reload.s",
						success : function(data){
							$("#detail").html(data)
							/* $("#detail").empty().prepend(data) */
						
						},
						error : function(){
							alert("/detail_reload.s 에러");
						}				
					
					})//ajax end
				
					
					//달력 새로고침 ajax 시작
					$.ajax({
							url : "./Calendar_load.s",
							success : function(data){		
								$('#cal_view').empty().prepend(data);
							},
							error : function(){
								alert('error');
							}
						})
					
				},
				error : function(){
					alert('AjaxDelSchedule error');
				}
			})//ajax end
			
		
		})//일정 삭제 버튼 end
		
		$("#detail_del").click(function(){	
			
			var ch2 ='';
			$("input:checkbox[name='det_list']").each(function(){
				if($(this).is(':checked'))
					ch2+=$(this).val()+" ";
				
			})
			
			$.ajax({
				type:'post',
				data:{"ch2":ch2},
				
				url :"./detail_del.s",

				success : function(data){
					$("#detail").empty().prepend(data)
				
				},
				error : function(){
					alert("AjaxDelDetail error");
				}				
			})//ajax end
		 
			$.ajax({
				url : "./Calendar_load.s",
				success : function(data){		
					$('#cal_view').empty().prepend(data);
				},
				error : function(){
					alert('error');
				}
			}) 
			
			
	})
		//일정 목록 페이지 전환 ajax
		$(document).on('click','.changePage',function(){
			var page = $(this).text().trim();		
			if(page=='이전')
				page = parseInt($("#hiddenPage").val())-1
			if(page=='다음')
				page=parseInt($("#hiddenPage").val())+1
	
		 	$.ajax({
				url: "./MySchedule.s",
				data:{"page":page,"state":"ajax"},
				success: function(data){
					
					$('#sch_det').empty().prepend(data);
					
					
				},
				error:function(){
					alert('error');
				}
			})
			return false;
		})
	
		$(document).on('click','.changePagePocketTravel',function(){
			var pagePT = $(this).text().trim();
			if(pagePT=='이전')
				pagePT=parseInt($("#hiddenPagePocketTravel").val())-1
			if(pagePT=='다음')
				pagePT=parseInt($("#hiddenPagePocketTravel").val())+1		
				$.ajax({
					url:"./MySchedule.s",
					data:{"pagePocketTravel":pagePT,"statePocketTravel":"ajax"},
					success:function(data){	
						
						$("#pocket_travel").empty().prepend(data);
					},
					error:function(){
						
							alert('error');
						}
					
				})
				return false;
		})
		
		
		$(document).on('click','.changePagePocketFestival',function(){
			var pagePF = $(this).text().trim();
			if(pagePF=='이전')
				pagePF=parseInt($("#hiddenPagePocketFestival").val())-1
			if(pagePF=='다음')
				pagePF=parseInt($("#hiddenPagePocketFestival").val())+1
			
				$.ajax({
					url:"./MySchedule.s",
					data:{"pagePocketFestival":pagePF,"statePocketFestival":"ajax"},
					success:function(data){
					 
						$("#pocket_festival").empty().prepend(data);
						
					},
					error:function(){
						
							alert('error');
					}
					
				})
			return false;
		})
		
		
		$(document).on('click','.show_travel',function(){
			
			var a ='show_travel_'+ $(this).attr('id');
			var b =$("#"+a).css('display');
			if(b=='none'){
				$(this).html('△');
				$("#"+a).css('display','table-row');
			}
			if(b=='table-row'){
				$(this).html('▼')
				$("#"+a).css('display','none');
			}
						
			return false;
		})
	
		
		$(document).on('click','.show_festival',function(){
			
			var a ='show_festival_'+ $(this).attr('id');
			var b =$("#"+a).css('display');
			if(b=='none'){
				$(this).html('△');
				$("#"+a).css('display','table-row');
			}
			if(b=='table-row'){
				$(this).html('▼')
				$("#"+a).css('display','none');
			}
						
			return false;
		})
		
		
		
		
		
	})
	
	
	
	
</script>
<title>일정 페이지</title>
</head>
<body>
	<jsp:include page="/MainHeader.jsp"/> 
	<div class="pic"><img src="./Schedule/css/pic10.jpg" style="width:1500px;height:350px"></div> 
	<div id='content'>
		<div id="empty" class="box">&nbsp;
			<div id="cal_view">&nbsp;</div>
		</div>
		
	
		<div id="schedule_view" >
			<fieldset>
				<legend>[일 정 목 록]</legend>	<br>
				<div id ="sch_det" >
					<table class='main_table'>
					<thead>
					<tr><th colspan='5'>일 정</th></tr>
					<tr>
						<th>&nbsp;</th>
						<th>일정 번호</th>
						<th>일정 이름</th>
						<th>시작일</th>
						<th>종료일</th>
					</tr>
				 	</thead>
				 	
				 	<tfoot>
					<tr>
						<td colspan='5'> 
						<c:if test="${page<=1}">
							이전&nbsp;
						</c:if>
						<c:if test="${page>1 }">
							<a href='#' class='changePage'>이전&nbsp;</a>
						</c:if>
						<c:forEach var='a' begin="${startpage}" end="${endpage}" >
							<c:if test="${a==page}">
							${a}
							</c:if>
							<c:if test="${a!=page}">
								<a href='#' class='changePage'>${a }</a>
							</c:if>
							</c:forEach>
							<c:if test="${page>=maxpage }">
								&nbsp;다음
							</c:if>
							<c:if test="${page<maxpage}">
								<a href="#" class='changePage'>&nbsp;다음</a>
							</c:if>
							<input type="hidden" value="${page}" id="hiddenPage">
						</td>
					</tr>	
					</tfoot>
				 	
				 	<tbody>
					<c:if test='${empty list}'>
					<tr><td colspan="5">
						생성된 일정이 없습니다. 나만의 일정을 추가해 보세요
					</td></tr>
					</c:if>
				
					<c:forEach var="m" items="${list}">	
					<tr>
						<td><input type="checkbox" class="sch_list" name="sch_list" value="${m.sch_no}">
						</td>
						<td> ${m.sch_no}</td>
						<td>${m.sch_title}</td>
						
						<%--  [일정 번호 : ${m.sch_no}]  --%>
						<%-- [회원 번호 : ${m.user_no}] --%>
						<td>${m.start_date}</td>
						<td>${m.end_date}</td>
					
						<tr>
					</c:forEach>	
					</tbody>
		
					</table>
				</div>
					<br>
					<input type="button" value="공유하기 " id="schedule_share" onClick="location.href='./ListPage.schbo'">
					<input type="button" value="삭제" id="schedule_del">						
			</fieldset>				
		</div>
		
	<%-- 	<div id="detail_view" class="box">
			<fieldset>
				<legend>[세부일정 목록]</legend>	
				<div id="detail">
		 			<c:forEach var="d" items="${detail}">	
						<input type="checkbox" class="det_list" name="det_list" value="${d.detail_no}">
						
						[일정  번호 : ${d.sch_no}]
						[세부 일정 번호 : ${d.detail_no}]
						[세부 일정 제목  : ${d.det_title}]
						[회원 번호 : ${d.user_no}]
						[여행 번호 : ${d.travel_no}]
						[축제 번호 : ${d.festival_no}]
						[세부 일정 내용 : ${d.det_content}]
						[세부 일정 시작일 : ${d.det_start}]
						[세부 일정 종료일 : ${d.det_end}]
						[세부 일정 날짜: ${d.det_date}]
						
						<br>
					</c:forEach>
					</div>
						<br>
						
						<input type="button" value="삭제" id="detail_del"> 				
			</fieldset>				
		</div> --%>
	
		<div id="pocket_view" class='box'>
			<fieldset>
				<legend>[장바구니목록]</legend>	<br>
				<div id="pocket_travel">				
					<table class='main_table'>
					<thead>
					<tr><th colspan='5'>여 행 지</th></tr>
					<tr>
						<th></th>
						<th>▼</th>
						<th>여행지</th>
						<th>주소</th>
						<th>테마</th>
					</tr>
					</thead>
					
					<tfoot>
					<tr>
						<td colspan='5'>
						<c:if test="${pagePocketTravel<=1}">
							이전&nbsp;
						</c:if>
						<c:if test="${pagePocketTravel>1}">
							<a href='#' class='changePagePocketTravel'>이전&nbsp;</a>
						</c:if>
						<c:forEach var='a' begin="${startpagePocketTravel}" end="${endpagePocketTravel}" >
							<c:if test="${a==pagePocketTravel}">
							${a}
							</c:if>
							<c:if test="${a!=pagePocketTravel}">
								<a href='#' class='changePagePocketTravel'>${a }</a>
							</c:if>
							</c:forEach>
							<c:if test="${pagePocketTravel>=maxpagePocketTravel }">
								&nbsp;다음
							</c:if>
							<c:if test="${pagePocketTravel<maxpagePocketTravel}">
								<a href="#" class='changePagePocketTravel'>&nbsp;다음</a>
							</c:if>
							<input type="hidden" value="${pagePocketTravel}" id="hiddenPagePocketTravel">
						</td>
					</tr>	
					</tfoot>		
					
					<tbody>
					<c:forEach var="p" items="${trav_list }">
					<tr>
					<td>
						<input type="checkbox" class="pocket_list" name="pock_trav_list" value="${p.travel_no}">		
						</td>	
						<td><a href="#" class="show_travel" id="${p.travel_no}" style="text-decoration:none;">▼</a></td>
						<td>${p.travel_name}</td>
						
						<td> ${p.travel_address }</td>
						<td> ${p.travel_tema }</td>
						
					</tr>
					<tr id="show_travel_${p.travel_no}" style="display:none"><td colspan="5"> <br>${p.travel_content }<br><br> </td></tr>
					</c:forEach>
					</tbody>
					
					</table>
				</div>
				<br> <br>
				
				<div id="pocket_festival">
					<table class='main_table'>
					<thead>
					<tr><th colspan='5'>축 제</th></tr>
					<tr>
						<th></th>
						<th>▼</th>
						<th>축 제</th>
						<th>주소</th>
						<th>일시</th>
					</tr>
					</thead>
					
					<tfoot>
						<tr>
						
						<td colspan='5'>
						<c:if test="${pagePocketFestival<=1}">
							이전&nbsp;
						</c:if>
						<c:if test="${pagePocketFestival>1 }">
							<a href='#' class='changePagePocketFestival'>이전&nbsp;</a>
						</c:if>
						<c:forEach var='a' begin="${startpagePocketFestival}" end="${endpagePocketFestival}" >
							<c:if test="${a==pagePocketFestival}">
							${a}
							</c:if>
							<c:if test="${a!=pagePocketFestival}">
								<a href='#' class='changePagePocketFestival'>${a }</a>
							</c:if>
							</c:forEach>
							<c:if test="${pagePocketFestival>=maxpagePocketFestival}">
								&nbsp;다음
							</c:if>
							<c:if test="${pagePocketFestival<maxpagePocketFestival}">
								<a href="#" class='changePagePocketFestival'>&nbsp;다음</a>
							</c:if>
							<input type="hidden" value="${pagePocketFestival}" id="hiddenPagePocketFestival">
						</td>
					</tr>			
						</tfoot>
					
					
					
					
					
					<tbody>
					<c:forEach var="p" items="${fest_list }">
					<tr>
					<td>
						<input type="checkbox" class="pocket_list" name="pock_fest_list" value="${p.festival_no}">	
						</td>
						<td><a href="#" class="show_festival" id="${p.festival_no}" style="text-decoration:none;">▼</a></td>
						<td>${p.festival_name}</td>
						<td> ${p.festival_address }</td>
						<td>${p.festival_startday}~${p.festival_endday }</td>
						</tr>
						<tr id="show_festival_${p.festival_no}" style="display:none"><td colspan="5"> <br>${p.festival_content }<br><br></td></tr>
						</c:forEach>
						</tbody>
	
						
						
						
					</table>
		
				</div>	
								
				<%-- 장바구니 정보 출력할 필요 없다/ 장바구니 정보를 이용해서 담아둔 축제와 여행지 정보를 가져오므로  
				<div id="pocket"> 
					<c:forEach var="p" items="${pocket}">	
					<input type="checkbox" class="pocket_list" name="pock_list" value="${p.pocket_no}">		
						[유저번호 : ${p.user_no}]   
						[포켓번호 : ${p.pocket_no}]
						[여행 번호 : ${p.travel_no}] 
						[축제 번호 : ${p.festival_no}]
						<br>
					</c:forEach>
					</div> --%>
						<br>					
						<input type="button" value="장바구니 삭제" id="pocket_del">	
						
			</fieldset>				
		</div>
		
		
		<br>	
		<div id="buttons_main" >
			<input type="button" class='main_button' value="내 일정 보기" onClick="location.href='./MyScheduleManage.s?Schedule_choice=all'">
			<input type="button" class='main_button' value="일정 등록하기" onClick="location.href='./Schedule_make.s'">
		</div>						
	
	</div>
</body>
</html>