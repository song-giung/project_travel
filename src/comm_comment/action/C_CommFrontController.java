package comm_comment.action;

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
@WebServlet("*.ccomt")
public class C_CommFrontController extends HttpServlet {
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
		
		//�ʱ�ȭ
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/ListComt.ccomt")) {
			action=new C_CommListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/c_comt_insert.ccomt")) {
			action=new C_CommInsertAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/c_comt_delete.ccomt")) {
			action = new C_CommDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			/*
		}else if(command.equals("/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/qna_board_write.jsp");
		}else if(command.equals("/BoardAddAction.bo")) {
			action = new BoardAddAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReplyView.bo")) {
			action = new BoardReplyView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReplyAction.bo")) {
			action = new BoardReplyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardModifyView.bo")) {
			action = new BoardModifyView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardModifyAction.bo")) {
			action = new BoardModifyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDelete.bo")) {
			forward=new ActionForward();
			forward.setRedirect(false); //������ ������� �ּҰ� �ٲ��� �ʾƿ�
			forward.setPath("./board/qna_board_delete.jsp");
		}else if(command.equals("/BoardDeleteAction.bo")) {
			action = new BoardDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardFileDown.bo")) {
			action = new BoardFileDownAction();
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
    //doProcess (request,response)�޼��带 �����Ͽ� ��û�� get����̵� post ����̵�
    //���� �޼��� ��û ó�� ����
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
