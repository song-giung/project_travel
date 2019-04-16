package qna_board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna_board.db.BoardBean;
import qna_board.db.BoardDAO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDAO boarddao = new BoardDAO();
		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		ActionForward forward = new ActionForward();

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지=" + page);

		int listcount = boarddao.getListCount();

		boardlist = boarddao.getBoardList(page, limit);

		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지=" + maxpage);

		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 =" + startpage);

		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수=" + endpage);

		if (endpage > maxpage) {
			endpage = maxpage;
		}
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);

		request.setAttribute("startpage", startpage);

		request.setAttribute("endpage", endpage);

		request.setAttribute("listcount", listcount);

		request.setAttribute("boardlist", boardlist);
		System.out.println(boardlist.size());

		forward.setRedirect(false);
		forward.setPath("./qna_board/qna_board_list.jsp");

		System.out.println(listcount);
		return forward;
	}

}
