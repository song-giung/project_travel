package client.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.db.Client;
import client.db.ClientDAO;

public class Client_updateAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, 
         HttpServletResponse response) throws Exception {
	   request.setCharacterEncoding("utf-8");
	   ActionForward forward = new ActionForward();
      HttpSession session = request.getSession();
      String user_id = (String)session.getAttribute("user_id");
      ClientDAO cd = new ClientDAO();
      Client c = cd.client_info(user_id);
      
      forward.setPath("/Client/updateForm.jsp");
      forward.setRedirect(false);
      request.setAttribute("client",c);
      
      return forward;
   }
}






