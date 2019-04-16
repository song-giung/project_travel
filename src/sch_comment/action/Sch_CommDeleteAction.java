package sch_comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sch_comment.db.SchCommentBean;
import sch_comment.db.SchCommentDAO;

public class Sch_CommDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ActionForward forward=new ActionForward();
		SchCommentBean scb = new SchCommentBean();
		SchCommentDAO scdao = new SchCommentDAO();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//가져온 정보
		int sch_comt_no= Integer.parseInt(request.getParameter("sch_comt_no"));
		
		int user_no=0;
		if(session.getAttribute("user_no")!=null) { //client는 user_no 세션으로 넘어옴, admin은 안넘어옴
			
			user_no=(int) session.getAttribute("user_no");
		
		}
		int check_user=0;
		
		PrintWriter out=response.getWriter();
		
		//작성자가 맞는지 확인
		check_user=scdao.isSch_CommWriter(sch_comt_no);
		String if_admin=(String)session.getAttribute("user_id");
		
		if(check_user==user_no||if_admin.equals("admin")) {
			scdao.CommDelete(sch_comt_no);
			System.out.println("댓글 삭제 성공!");
		}
		else {
			System.out.println("댓글 삭제 실패!");
			return null;
		}
		
		forward.setRedirect(false);
		forward.setPath("ListComt.schcomt");
		return forward;
	}

}
