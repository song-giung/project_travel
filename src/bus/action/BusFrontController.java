package bus.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.bus")
public class BusFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public BusFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doProcess(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
    	String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);
    	
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);
    	
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = " + command);
    	
    	ActionForward forward =null;
    	Action action = null;
    	
    	if(command.equals("/AreaList.bus")) {
			forward=new ActionForward();
			forward.setRedirect(false);	
			forward.setPath("./Bus/AreaList.jsp");
		}
    	else if(command.equals("/select_start.bus")) {
			action = new Select_start();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
    	else if(command.equals("/select_end.bus")) {
			action = new Select_end();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
    	else if(command.equals("/select_no.bus")) {
			action = new Select_no();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
    	else if(command.equals("/search.bus")) {
			action = new Search();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
    	}
    	else if(command.equals("/BusAdd.bus")) {
			forward=new ActionForward();
			forward.setRedirect(false);	
			forward.setPath("./Bus/BusAdd.jsp");
		}
    	else if(command.equals("/BusSearch.bus")) {
			forward=new ActionForward();
			forward.setRedirect(false);	
			forward.setPath("./Bus/BusSearch.jsp");
		}
    	else if(command.equals("/BusModify.bus")) {
			action = new BusModifyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
    	}
    	else if(command.equals("/BusModifyProcess.bus")) {
    		action = new BusModifyProcess();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
    	else if(command.equals("/BusDelete.bus")) {
			action = new BusDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
    	}
    	else if(command.equals("/BusAddAction.bus")) {
			action = new BusAddAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
    	else if(command.equals("/ScheduleAddAction.bus")) {
			action = new ScheduleAddAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
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
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
