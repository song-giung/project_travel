package comm_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm_board.db.CBBforList;
import comm_board.db.CommBoardBean;
import comm_board.db.CommBoardDAO;

public class CommBoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		CommBoardDAO boarddao = new CommBoardDAO();
		CommBoardBean boardbean=new CommBoardBean();
		CBBforList boarddata = new CBBforList();
		
		int comm_bo_no= Integer.parseInt(request.getParameter("num"));
		
		int user_no=0;
	      if(session.getAttribute("user_no")!=null) { //client는 user_no 세션으로 넘어옴, admin은 안넘어옴
	      
	         user_no=(int) session.getAttribute("user_no");
	      
	      }
	      
		int check_user=0;
		check_user=boarddao.isBoardWriter(comm_bo_no);
		
		String if_admin=(String)session.getAttribute("user_id");
		
		if(check_user==user_no||user_no==0)
		{
				System.out.println("수정 권한있음");
		}else{
			System.out.println("writer!=user");
			return null;
		}	
				
		boarddata= boarddao.getDetail(comm_bo_no);
			
		if(boarddata==null) {
			System.out.println("수정 페이지 이동 실패");
			return null;
		}
		System.out.println("수정 페이지 이동 완료");
		
		request.setAttribute("boarddata", boarddata);
		forward.setRedirect(false);
		forward.setPath("./CommBoard/CommBoardModifyView.jsp");
		return forward;
	}

}
