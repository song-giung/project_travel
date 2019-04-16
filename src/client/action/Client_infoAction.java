package client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.db.Client;
import client.db.ClientDAO;

public class Client_infoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		System.out.println("id:" + user_id);
		ClientDAO cd = new ClientDAO();
		Client c = cd.client_info(user_id);
		
		forward.setPath("/Client/client_info.jsp");
		forward.setRedirect(false);
		request.setAttribute("info",c);
		
		
			
		// TODO Auto-generated method stub
		return forward;
	}

}
