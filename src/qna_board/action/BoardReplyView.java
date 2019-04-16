package qna_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna_board.db.BoardBean;
import qna_board.db.BoardDAO;

public class BoardReplyView implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		BoardDAO boarddao = new BoardDAO();

		int num = Integer.parseInt(request.getParameter("num"));

		BoardBean boarddata = boarddao.getDetail(num);

		if (boarddata == null) {
			System.out.println("½ÇÆÐ");
			return null;
		}
		System.out.println("¤·");

		forward.setPath("/qna_board/qna_board_reply.jsp");
		forward.setRedirect(false);
		request.setAttribute("boarddata", boarddata);
		return forward;
	}

}
