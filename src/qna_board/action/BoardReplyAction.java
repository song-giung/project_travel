package qna_board.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import qna_board.db.BoardBean;
import qna_board.db.BoardDAO;

public class BoardReplyAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();

		request.setCharacterEncoding("utf-8");

		BoardBean boarddata = new BoardBean();
		BoardDAO boarddao = new BoardDAO();
		int result = 0;

		int BOARD_NUM = Integer.parseInt(request.getParameter("num"));
		int BOARD_RE_REF = Integer.parseInt(request.getParameter("BOARD_RE_REF"));
		int BOARD_RE_LEV = Integer.parseInt(request.getParameter("BOARD_RE_LEV"));
		int BOARD_RE_SEQ = Integer.parseInt(request.getParameter("BOARD_RE_SEQ"));

		String BOARD_CONTENT = request.getParameter("content");

		String USER_ID = request.getParameter("USER_ID");

		boarddata.setBOARD_NUM(BOARD_NUM);
		boarddata.setBOARD_RE_REF(BOARD_RE_REF);
		boarddata.setBOARD_RE_LEV(BOARD_RE_LEV);
		boarddata.setBOARD_RE_SEQ(BOARD_RE_SEQ);

		boarddata.setBOARD_CONTENT(BOARD_CONTENT);

		boarddata.setUSER_ID(USER_ID);

		result = boarddao.ADDReply(boarddata);

		if (result == 0) {
			System.out.println("Ω«∆–");
			return null;
		}

		System.out.println("¿¿");
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.bo?num=" + BOARD_NUM);
		return forward;
	}

}
