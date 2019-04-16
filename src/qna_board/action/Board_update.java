package qna_board.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna_board.db.BoardBean;
import qna_board.db.BoardDAO;

	public class Board_update implements Action{

		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ActionForward forward = new ActionForward();
			request.setCharacterEncoding("utf-8");
			BoardDAO boarddao = new BoardDAO();
			int num = Integer.parseInt(request.getParameter("num"));
			BoardBean boarddata = boarddao.getDetail(num);
			
			
			response.setContentType("text/html;charset=utf-8");
			forward.setRedirect(false);
			forward.setPath("/qna_board/qna_board_update.jsp");
			request.setAttribute("boarddata", boarddata);
			return forward;
		}
}
