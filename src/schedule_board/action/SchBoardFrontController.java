package schedule_board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class FrontController1
 */
@WebServlet("*.schbo")
public class SchBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI=request.getRequestURI();
		System.out.println("RequestURI : "+RequestURI);
		
		String contextPath=request.getContextPath();
		System.out.println("contextPath : "+contextPath);
		
		String command=RequestURI.substring(contextPath.length());
		System.out.println("command= "+command);
		
		//초기화
		ActionForward forward=null;
		Action action=null;

		/*if(command.equals("/Insert.schbo")) {
			action = new CommBoardInsertAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}if(command.equals("/InsertPage.schbo")) {
			forward= new ActionForward();
			forward.setRedirect(false);
			forward.setPath("ScheduleBoard/CommBoardInsertView.jsp");
		}*/
		if(command.equals("/ListPage.schbo")) {
			action = new SchBoardListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/InsertPage.schbo")) {
			action= new SchBoardInsertView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/InsertAction.schbo")) {
			action= new SchBoardInsertAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/SchDetailView.schbo")) {
			action = new SchBoardDetailAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}//forward.setPath("CommBoard/CommBoardListView.jsp");
		}else if(command.equals("/SchBoardDelete.schbo")) {
			action=new SchBoardDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/get3.schbo")) {
			action= new get3List();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		/*}if(command.equals("/CommModifyView.combo")) {
			action = new CommBoardModifyView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}if(command.equals("/ModifyAction.combo")) {
			action = new CommBoardModifyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}if(command.equals("/CommBoardDelete.combo")) {
			action = new CommBoardDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}*/
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher= request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //doProcess (request,response)메서드를 구현하여 요청이 get방식이든 post 방식이든
    //같은 메서드 요청 처리 설정
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	

}
