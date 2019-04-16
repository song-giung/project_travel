package client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.db.Client;
import client.db.ClientDAO;

public class FindPasswdAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		System.out.println(user_id+"\n"+user_name +"\n"+ user_email);
		ClientDAO  cd = new ClientDAO();
		
		int result = cd.checkgetPW(user_id, user_name, user_email);
		Client c = cd.getPW(user_id, user_name, user_email);
		
		forward.setPath("/Client/FindPW.jsp");
	    forward.setRedirect(false);
	    request.setAttribute("client",c);
	    request.setAttribute("result",result);
		
		return forward;
	}

}
