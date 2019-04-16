package schedule_board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule_board.db.SchBoardBean;
import schedule_board.db.SchBoardDAO;

public class SchBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		SchBoardBean sb = new SchBoardBean();
		SchBoardDAO sdao = new SchBoardDAO();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		
		//가져온 정보
		int sch_bo_no= Integer.parseInt(request.getParameter("num"));
		PrintWriter out=response.getWriter();
		
		//작성자가 맞는지 확인
		int user_no=0;
	      if(session.getAttribute("user_no")!=null) { //client는 user_no 세션으로 넘어옴, admin은 안넘어옴
	      
	         user_no=(int) session.getAttribute("user_no");
	      
	      }
	      
		int user_check=0;
		
		String if_admin=(String)session.getAttribute("user_id");
		
		//db에서 게시글 writer 가져옴
		user_check=sdao.isBoardWriter(sch_bo_no);
		
		if(user_check==user_no||if_admin.equals("admin")) {
			sdao.boardDelete(sch_bo_no);
			forward.setPath("ListPage.schbo");
		}else {
			return null;
		}
		
		return forward;
	}

}
