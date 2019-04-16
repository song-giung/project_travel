package comm_comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm_comment.db.CommCommentBean;
import comm_comment.db.CommCommentDAO;

public class C_CommDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommCommentBean cb = new CommCommentBean();
		CommCommentDAO cdao = new CommCommentDAO();
		HttpSession session=request.getSession();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int c_comt_no=Integer.parseInt(request.getParameter("c_comt_no"));
		
		int user_no=0;
		if(session.getAttribute("user_no")!=null) { 
		
			user_no=(int) session.getAttribute("user_no");
		
		}
		
		int check_user=0;
		check_user=cdao.isC_CommWriter(c_comt_no);
		String if_admin=(String)session.getAttribute("user_id");
		
		if(check_user==user_no||if_admin.equals("admin")) {
			cdao.CommDelete(c_comt_no);
			System.out.println("댓글 삭제 성공!");
		}
		else {
			System.out.println("댓글 삭제 실패!");
			return null;
		}
		ActionForward forward=new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("ListComt.ccomt");
		return forward;
		
	}

}
