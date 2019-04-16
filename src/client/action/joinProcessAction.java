package client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.db.Client;
import client.db.ClientDAO;



public class joinProcessAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		String user_passwd = request.getParameter("user_passwd");
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		
		int x1 = Integer.parseInt(request.getParameter("x1"));
		int x2 = Integer.parseInt(request.getParameter("x2"));
		int x3 = Integer.parseInt(request.getParameter("x3"));
		int x4 = Integer.parseInt(request.getParameter("x4"));
		int x5 = Integer.parseInt(request.getParameter("x5"));
		int y1 = Integer.parseInt(request.getParameter("y1"));
		int y2 = Integer.parseInt(request.getParameter("y2"));
		int y3 = Integer.parseInt(request.getParameter("y3"));
		int y4 = Integer.parseInt(request.getParameter("y4"));
		int y5 = Integer.parseInt(request.getParameter("y5"));
		
		int xsum = x1+x2+x3+x4+x5;
		int ysum = y1+y2+y3+y4+y5;
		int user_type =0;
		if(xsum >0 && ysum>0) {
			user_type=1;
		}else if(xsum<0 && ysum>0) {
			user_type=2;
		}else if(xsum<0 && ysum<0) {
			user_type=3;
		}else if(xsum>0 && ysum<0) {
			user_type=4;
		}else {
			user_type=0;
		}		
		System.out.println(user_type);
		
		ClientDAO cd = new ClientDAO();
		Client c = new Client();
		
		c.setUser_id(user_id);
		c.setUser_passwd(user_passwd);
		c.setUser_name(user_name);
		c.setUser_email(user_email);
		c.setUser_type(user_type);
		
		int result = cd.join(c);

		if(result==1) {	
			session.setAttribute("c", user_type);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href='./JoinSuccess.net';");
			out.println("</script>");
			out.close();
			
		}else if(result==-1){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ID가 중복되었습니다. 다시 입력하세요.')");
			//out.println("location.href='./join.net';");//새로고침
			out.println("history.back()");//비밀번호를 제외한 다른 데이터
			out.println("</script>");
			out.close();
			
		}
		if(result ==1) {
			return forward;
		}else {
			return null;
		}
	}

}
