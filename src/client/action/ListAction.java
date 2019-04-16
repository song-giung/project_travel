package client.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.db.Client;
import client.db.ClientDAO;

public class ListAction implements Action {
		@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward();
		ClientDAO cd = new ClientDAO();
		List<Client> list = cd.getList();
		
		forward.setPath("/Client/client_list.jsp");
		forward.setRedirect(false);
		request.setAttribute("totallist", list);
	
		return forward;
	}
}
