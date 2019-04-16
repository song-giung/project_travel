package sch_comment.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm_comment.action.C_CommDeleteAction;
import comm_comment.action.C_CommInsertAction;
import comm_comment.action.C_CommListAction;

/**
 * Servlet implementation class FrontController1
 */
@WebServlet("*.schcomt")
public class Sch_CommFrontController extends HttpServlet {
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
		
		//占십깍옙화
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/ListComt.schcomt")) {
			action=new Sch_CommListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/sch_comt_insert.schcomt")) {
			action=new Sch_CommInsert();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/sch_comt_delete.schcomt")) {
			action = new Sch_CommDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
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
    //doProcess (request,response)占쌨쇽옙占썲를 占쏙옙占쏙옙占싹울옙 占쏙옙청占쏙옙 get占쏙옙占쏙옙絹占� post 占쏙옙占쏙옙絹占�
    //占쏙옙占쏙옙 占쌨쇽옙占쏙옙 占쏙옙청 처占쏙옙 占쏙옙占쏙옙
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
