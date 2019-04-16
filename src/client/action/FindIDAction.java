package client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.db.*;

public class FindIDAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		ActionForward forward  = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		System.out.println(user_name +"\n"+ user_email);
		ClientDAO  cd = new ClientDAO();
		
		int result = cd.checkgetID(user_name, user_email);
		Client c = cd.getId(user_name, user_email);
		System.out.println(c);
		forward.setPath("/Client/FindID.jsp");
	    forward.setRedirect(false);
	    request.setAttribute("client",c);
	    request.setAttribute("result", result);
		
		return forward;
	}

}
