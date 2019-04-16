/**
 * 
 */

$(function(){
	$('#golist').click(function(){
		location.href="./ListPage.combo?page=${page}&searchindex=${sessionScope.searchindex}&searchcontent=${sessionScope.searchcontent}${sessionScope.url}"
	})
	
	$('#insertComt').click(function(){
		var user_no=$("#user_no").val();
		var comm_bo_no=$("#comm_bo_no").val();
		var c_comt_cont=$("#comment").val();
		$.ajax({
			type:'post',
			data:{'comm_bo_no':comm_bo_no, 'user_no':user_no, 'c_comt_cont':c_comt_cont},
			url: "./c_comt_insert.ccomt",
			success : function(data){
			
	               $("#clean").empty().prepend(data); 
				  	var comtcount=$("#clean").find("#comt_count").val();
				  	 $("#comt_count").text(comtcount); 
			},
	            error : function(){
	               alert('error');
	            }
		})	
		
		$("#comment").val("");
	})
	
	//댓글 페이징 처리
	$(document).on("click", ".a",function(e){
		e.preventDefault();
		
		var a=$(this).text().trim();
		var page=$("#page").val();
		var realpage=0;
		
		if(a=="이전"){
			realpage=parseInt(page)-1;
			}
		else if(a=="다음"){
			realpage=parseInt(page)+1;
			console.log(realpage);
		}else
			realpage=a;
		
		
		var user_no=$("#user_no").val();
		var comm_bo_no=$("#comm_bo_no").val();
		var c_comt_cont=$("#comment").val();
		
		$.ajax({
			type:'post',
			data:{'comm_bo_no':comm_bo_no, 'user_no':user_no, 'c_comt_cont':c_comt_cont,'page':realpage},
			url: "./ListComt.ccomt",
			success : function(data){
			
	               $("#clean").empty().prepend(data); 
				  
			},
	            error : function(){
	               alert('error');
	            }
		})	

	})
	
	$(document).on("click", '.comtDelete', function(){
	
		if($("#page").val()!=null)
			var a=$("#page").val();
		else
			var a=1;
		
		var user_no=$("#user_no").val();
		var comm_bo_no=$("#comm_bo_no").val();
		var c_comt_cont=$("#comment").val();
		var c_comt_no=$(this).val();
		
		$.ajax({
			type:'post',
			data:{'comm_bo_no':comm_bo_no, 'user_no':user_no, 'c_comt_no':c_comt_no, 'page':a},
			url: "./c_comt_delete.ccomt",
			success : function(data){
				
	               $("#clean").empty().prepend(data); 
				  	var comtcount=$("#clean").find("#comt_count").val();
				  	 $("#comt_count").text(comtcount); 
				  	if(comtcount%10==1){
				  		
				  	}
			},
	            error : function(){
	               alert('error');
	            }
		})	
		
		$("#comment").val("");
	})
	
	
	

})