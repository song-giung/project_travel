package travel.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.tr")
public class FrontController extends HttpServlet {
       
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
       
   /* RequestDispatcher dispatcher = request.getRequestDispatcher("/180502/8.member/loginForm.jsp");


        dispatcher.forward(request, response);*/
    String RequestURI = request.getRequestURI();
    System.out.println("RequestURI = "+RequestURI);
    

    String contextPath=request.getContextPath();
    System.out.println("contextPath = "+contextPath);
    

    String command=RequestURI.substring(contextPath.length());
    System.out.println("command = " + command);
   
   
    //�ʱ�ȭ
    ActionForward forward = null;
    Action action = null;
   if(command.equals("/travel_list.tr")) {
    	action = new ListTravelAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }else if(command.equals("/travel_list2.tr")) {
    	action = new ascorderAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/travel_add.tr")) {
    	action = new TravelAddAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/travel_info.tr")) {
    	action = new travelDetailAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }else if(command.equals("/travel_delete.tr")) {
    	action = new travelDeleteAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }else if(command.equals("/travel_modify.tr")) {
    	action = new travelModifyViewAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/travel_modifyAction.tr")) {
    	action = new travelModifyAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/userTravelList.tr")) {
    	action = new userTravelListAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    else if(command.equals("/usertypelist.tr")) {
    	action = new userTravel_TypeListAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }else if(command.equals("/userTravel_info.tr")) {
    	action = new userTravelInfoAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }else if(command.equals("/userTravelTemalist.tr")) {
    	forward = new ActionForward();
    	forward.setPath("/travel/userTravel_tema.jsp");
    	forward.setRedirect(false);
    	
    }else if(command.equals("/travel_write.tr")) {
    	forward = new ActionForward();
    	forward.setPath("/travel/travel_add.jsp");
    	forward.setRedirect(false);
    	
    }else if(command.equals("/travel_pocketplus.tr")) {
    	action = new travel_pocketplusAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/travel_upadd.tr")) {
    	action = new travel_upaddAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/travel_updelete.tr")) {
    	action = new travel_updeleteAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/travel_downadd.tr")) {
    	action = new travel_downAddAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/travel_downdelete.tr")) {
    	action = new travel_downDeleteAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
   
   
  
    
    if(forward != null) {
    	if(forward.isRedirect()){//�����̷�Ʈ�˴ϴ�.
    		response.sendRedirect(forward.getPath());
    	}else {//�������˴ϴ�.
    		RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request,response);
    	}
    }
    
}
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doProcess(request,response);
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request,response);
   }

}