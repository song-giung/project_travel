package client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		response.setContentType("text/html;charset=utf-8");
		
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.')");
		out.println("location.href='./Mainpage.jsp'");
		out.println("</script>");
		out.close();
		
	
		
		// TODO Auto-generated method stub
		return null;
	}

}
