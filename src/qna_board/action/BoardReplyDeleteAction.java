package qna_board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import qna_board.db.BoardDAO;

public class BoardReplyDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		BoardDAO boarddao = new BoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		

		System.out.println(num);
		boarddao.delete(num);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<script>");
		out.println("alert('삭제하였습니다.')");
		out.println("location.href='BoardDetailAction.bo?num="+ board_num +"'");
		out.println("</script>");
		out.close();
		return null;
	}

}
