
package qna_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna_board.db.BoardBean;
import qna_board.db.BoardDAO;

public class BoardModifyAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		BoardDAO boarddao = new BoardDAO();
		int result = 0;

		int num = Integer.parseInt(request.getParameter("num"));
		String content = request.getParameter("content");
		System.out.println(num);
		System.out.println(content);
		result = boarddao.boardReply(content, num);

		if (result == 0) {
			System.out.println("½ÇÆÐ");
		}
		return null;

	}

}
