package qna_board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna_board.db.BoardBean;
import qna_board.db.BoardDAO;

public class BoardDetailAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		BoardDAO boarddao = new BoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		List<BoardBean> replylist = new ArrayList<BoardBean>();
				
		BoardBean boarddata = boarddao.getDetail(num);
		
		replylist = boarddao.getreplyList(num);
		
		if(boarddata==null) {
			System.out.println("¾ÈµÊ");
			return null;
		}
		System.out.println("¤¾¤·");
		
		
		
		forward.setPath("/qna_board/qna_board_view.jsp");
		forward.setRedirect(false);
		request.setAttribute("boarddata", boarddata);
		request.setAttribute("re", replylist);
		return forward;
	}

}
