package schedule_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule_board.db.SchBoardBean;
import schedule_board.db.SchBoardDAO;

public class SchBoardInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SchBoardBean sbbean = new SchBoardBean();
		SchBoardDAO sbdao=new SchBoardDAO();
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int sch_no=Integer.parseInt(request.getParameter("sch_no"));
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		String sch_bo_title=request.getParameter("schb_title");
		String sch_bo_content=request.getParameter("schb_content");
		
		sbbean.setSch_no(sch_no);
		sbbean.setUser_no(user_no);
		sbbean.setSch_bo_title(sch_bo_title);
		sbbean.setSch_bo_content(sch_bo_content);
		
		boolean check=false;
		
		check= sbdao.boardInsert(sbbean);
		
		if(check==false)
			System.out.println("일정 게시판 등록 실패");
		else {
			System.out.println("일정 게시판 등록 성공");
			forward.setPath("ListPage.schbo");
		}
		
		
		
		return forward;
	}

}
