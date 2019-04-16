package comm_comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import comm_comment.db.CommCommentBean;
import comm_comment.db.CommCommentDAO;

public class C_CommInsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommCommentBean cb = new CommCommentBean();
		CommCommentDAO cdao = new CommCommentDAO();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		int comm_bo_no=Integer.parseInt(request.getParameter("comm_bo_no"));
		String c_comt_cont=request.getParameter("c_comt_cont");
		
		cb.setUser_no(user_no);
		cb.setComm_bo_no(comm_bo_no);
		cb.setC_comt_cont(c_comt_cont);
		
		System.out.println(c_comt_cont);
		boolean check=false;		
		check=cdao.C_CommInsert(cb);
		
		if(check==true) System.out.println("µÊ!");
		else System.out.println("¾ÈµÊ!");
		ActionForward forward=new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("ListComt.ccomt");
		return forward;
	}

}
