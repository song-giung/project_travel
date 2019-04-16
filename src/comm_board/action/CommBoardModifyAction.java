package comm_board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm_board.db.CommBoardBean;
import comm_board.db.CommBoardDAO;

public class CommBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward=new ActionForward();
		CommBoardDAO boarddao = new CommBoardDAO();
		CommBoardBean boarddata = new CommBoardBean();
		//값받아오기
		int comm_bo_no=Integer.parseInt(request.getParameter("comm_bo_no"));
		//사용자 받아오기 session
		//수정 시 게시판 테이블 user_no 랑 현재 로그인 user_no맞나 확인
	
		/* 작성자가 맞는지 확인하기
		 * boolean usercheck = boarddao.isBoardWriter(user_no,comm_bo_no);	*/	
		String if_admin=(String)session.getAttribute("user_id");
		
		int user_no=0;
	      if(session.getAttribute("user_no")!=null) { //client는 user_no 세션으로 넘어옴, admin은 안넘어옴
	      
	         user_no=(int) session.getAttribute("user_no");
	      
	      }
		System.out.println("Session에 있는 user_no " + user_no);
		int check_user=0;
		check_user=boarddao.isBoardWriter(comm_bo_no);
		
		System.out.println("check로 가져온 user_no " + check_user);
		
		if(check_user==user_no||user_no==0)
		{
				System.out.println("수정 권한있음");
		}else{
			System.out.println("writer!=user");
			return null;
		}
		//board data에 값 넣기
		boarddata.setComm_bo_no(comm_bo_no);
		boarddata.setComm_bo_title(request.getParameter("comb_title"));
		boarddata.setComm_bo_file(request.getParameter("comb_file"));
		boarddata.setComm_bo_content(request.getParameter("comb_content"));
		
		
		//boardModify()함수에 boarddata 넘김

		boolean result=false;
		result=boarddao.boardModify(boarddata);
		if(result==false) {
			System.out.println("게시판 수정 실패");
			return null;
		}
		
		System.out.println("게시판 수정 완료");
		forward.setRedirect(true);
		//comm_bo_no넘긴당ㅇ
		forward.setPath("./CommDetailView.combo?num="+boarddata.getComm_bo_no());
		return forward;
	}

}
