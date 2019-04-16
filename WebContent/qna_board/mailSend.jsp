<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 자바 메일 API를 사용하기 위해 관련 패키지를 import합니다. --%>
<%@ page import="javax.activation.*"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<style>
body{
		margin : 0px;
		background-color : #28c3dc;
	}
	table{
		background-color : white;
	}
	form{
		margin-top : 20px;
	}
</style>
<body>
	<%
   request.setCharacterEncoding("utf-8");
   String sender=request.getParameter("sender");
   String receiver=request.getParameter("receiver");
   String subject=request.getParameter("subject");
   String content=request.getParameter("content");
   
   
   String server = "smtp.naver.com";
   
   try{
      
      Properties properties = new Properties();
      
      
     
      properties.put("mail.smtp.host", server);
      
      
      Session s = Session.getDefaultInstance(properties);
      
      
      Address sender_address= new InternetAddress(sender);
      Address receiver_address = new InternetAddress(receiver);
      
      
      Message message = new MimeMessage(s);
      
     
      message.setHeader("content-type", "text/html; charset=utf-8");
      
      
      message.setFrom(sender_address);
      
     
      message.addRecipient(Message.RecipientType.TO, receiver_address);
      
    
      message.setSubject(subject);
      
      
      message.setContent(content,"text/html;charset=utf-8");
      
     
      message.setSentDate(new java.util.Date());
      
     
      Transport transport = s.getTransport("smtp");
      
     
      transport.connect(server,"rodgj","621201sa");
      
      
      transport.sendMessage(message, message.getAllRecipients());
      
      //연결을 종료합니다.
      transport.close();
      
      out.println("<script>");
      out.println("alert('메일이 전송됨')");
      out.println("self.close()");
      out.println("</script>");
      
   }catch(Exception e){
      out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.");
      e.printStackTrace();
   }
%>
</body>
</html>