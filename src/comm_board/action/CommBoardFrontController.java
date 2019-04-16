package comm_board.action;

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
@WebServlet("*.combo")
public class CommBoardFrontController extends HttpServlet {
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
		if(command.equals("/Insert.combo")) {
			action = new CommBoardInsertAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}if(command.equals("/InsertPage.combo")) {
			forward= new ActionForward();
			forward.setRedirect(false);
			forward.setPath("CommBoard/CommBoardInsertView.jsp");
		}if(command.equals("/ListPage.combo")) {
			action = new CommBoardListAction2();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}//forward.setPath("CommBoard/CommBoardListView.jsp");
		}if(command.equals("/CommDetailView.combo")) {
			action = new CommBoardDetailAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}//forward.setPath("CommBoard/CommBoardListView.jsp");
		}if(command.equals("/CommModifyView.combo")) {
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
		}if(command.equals("/CommBoardDown.combo")) {
			action = new CommFileDownAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}if(command.equals("/CommBoardMain.combo")) {
			forward= new ActionForward();
			forward.setRedirect(false);
			forward.setPath("CommBoard/CommBoardMain.jsp");
		}


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
