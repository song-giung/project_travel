package festival.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.action.travel_downAddAction;
import travel.action.travel_downDeleteAction;
import travel.action.travel_upaddAction;
import travel.action.travel_updeleteAction;


@WebServlet("*.fs")
public class FrontController extends HttpServlet {
       
    protected void doProcess(HttpServletRequest request, 
    		HttpServletResponse response) 
    				throws ServletException ,IOException{
       
   /* RequestDispatcher dispatcher = request.getRequestDispatcher("/180502/8.member/loginForm.jsp");


        dispatcher.forward(request, response);*/
    String RequestURI = request.getRequestURI();
    System.out.println("RequestURI = "+RequestURI);
    
    //getContextPath() : 
    //contextPath�� "/JspProject"�� ��ȯ�˴ϴ�.
    String contextPath=request.getContextPath();
    System.out.println("contextPath = "+contextPath);
    
   
    String command=RequestURI.substring(contextPath.length());
    System.out.println("command = " + command);
   
   
    //�ʱ�ȭ
    ActionForward forward = null;
    Action action = null;
    
   if(command.equals("/festival_list.fs")) {
    	action = new ListFestivalAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }else if(command.equals("/festival_list2.fs")) {
    	action = new ascorderAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }else if(command.equals("/festival_write.fs")){
    	forward = new ActionForward();
    	forward.setRedirect(false);
    	forward.setPath("/festival/festival_add.jsp");
    }
   
   else if(command.equals("/festival_add.fs")) {
    	action = new FestivalAddAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/festival_delete.fs")) {
    	action = new FestivalDeleteAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/festival_info.fs")) {
    	action = new FestivalDetailAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/festival_modify.fs")) {
    	action = new FestivalModifyViewAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/festival_modifyAction.fs")) {
    	action = new FestivalModifyAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/userFestivallist.fs")) {
    	action = new UserFestivalListAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    } else if(command.equals("/userfestival_location.fs")) {
    	action = new UserFestivalListAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/userFestival_info.fs")) {
    	action = new UserFestivalInfoAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}

    }else if(command.equals("/festival_pocketplus.fs")) {
    	action = new festival_pocketplusAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/festival_upadd.fs")) {
    	action = new festival_upaddAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/festival_updelete.fs")) {
    	action = new festival_updeleteAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/festival_downadd.fs")) {
    	action = new festival_downAddAction();
    	try {
    		forward=action.execute(request, response);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }else if(command.equals("/festival_downdelete.fs")) {
    	action = new festival_downDeleteAction();
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