<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.24/themes/base/jquery-ui.css">

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<input type="image" id="datepicker" src="Bus/calendar.jpg" width="17px">
<input type="text" id="getdate" name="getdate">
<script>
    $('#datepicker').datepicker({
        altField : '#getdate',   
        minDate: 0
    });
  
</script>

