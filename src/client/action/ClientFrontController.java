package client.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.net")
public class ClientFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ClientFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doProcess(HttpServletRequest request,
								HttpServletResponse response)
		throws ServletException, IOException {
	
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		String contextPath=request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		String command=RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		
		ActionForward forward=null;
		Action action=null;
		if(command.equals("/login.net")) {
			forward=new ActionForward();
			forward.setRedirect(false);	
			forward.setPath("./Client/loginForm.jsp");
		}
		else if(command.equals("/loginProcess.net")) {
			action = new LoginProcessAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/client_list.net")) {
			action = new ListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/client_info.net")) {
			action = new Client_infoAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/client_delete.net")) {
			action = new Client_deleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/client_update.net")) {
			action = new Client_updateAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/updateProcess.net")) {
			action = new updateProcessAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/logout.net")) {
			action = new LogoutAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/join.net")) {
			forward=new ActionForward();
			forward.setRedirect(false);	
			forward.setPath("./Client/joinForm.jsp");
		}
		else if(command.equals("/joinProcess.net")) {
			action = new joinProcessAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/IdcheckAjax.net")) {
			action = new IdcheckAjax();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/FindClientData.net")) {
			forward=new ActionForward();
			forward.setRedirect(false);	
			forward.setPath("./Client/Find.jsp");
		}
		else if(command.equals("/FindIDAction.net")) {
			action = new FindIDAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/FindPasswdAction.net")) {
			action = new FindPasswdAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/JoinSuccess.net")) {
			forward=new ActionForward();
			forward.setRedirect(false);	
			forward.setPath("./Client/JoinSuccess.jsp");
		}
		else if(command.equals("/TravelerType.net")) {
			forward=new ActionForward();
			forward.setRedirect(false);	
			forward.setPath("./Client/TravelerType.jsp");
		}
		
		
		
		if(forward !=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	
		
	}
	
	protected void doGet(HttpServletRequest request,
								HttpServletResponse response)
			throws ServletException, IOException {
			doProcess(request, response);
		}

	protected void doPost(HttpServletRequest request,
								HttpServletResponse response) 
		throws ServletException, IOException {
		doProcess(request, response);
	}

}
