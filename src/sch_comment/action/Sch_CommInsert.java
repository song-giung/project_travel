package sch_comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sch_comment.db.SchCommentBean;
import sch_comment.db.SchCommentDAO;

public class Sch_CommInsert implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SchCommentBean scb = new SchCommentBean();
		SchCommentDAO scdao = new SchCommentDAO();
		ActionForward forward=new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		int sch_bo_no=Integer.parseInt(request.getParameter("sch_bo_no"));
		String sch_comt_cont=request.getParameter("sch_comt_cont");//댓글 내용
		
		scb.setUser_no(user_no);
		scb.setSch_bo_no(sch_bo_no);
		scb.setSch_comt_cont(sch_comt_cont);
		
		System.out.println(sch_comt_cont);
		boolean check=false;		
		check=scdao.Sch_CommInsert(scb);
		
		if(check==true) System.out.println("댓글 달기 성공!");
		else System.out.println("댓글 달기 실패!");
		
		
		forward.setRedirect(false);
		forward.setPath("ListComt.schcomt");
		return forward;
	}

}
