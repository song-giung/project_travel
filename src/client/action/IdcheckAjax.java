package client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.db.ClientDAO;


public class IdcheckAjax implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		ClientDAO cd = new ClientDAO();
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		int result = cd.checkId(user_id);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		return null;
	}

}
