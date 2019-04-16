package qna_board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna_board.db.BoardDAO;




public class BoardDeleteAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		BoardDAO boarddao = new BoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		String BOARD_PASS = boarddao.deleteCheck(num);
		
		System.out.println("BOARD_PASS = " + BOARD_PASS);
		String requestpass = request.getParameter("BOARD_PASS");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		
		
		if(BOARD_PASS==null) {
			out.println("alert('비밀번호가 틀렸습니다.');");
			out.println("history.back()");
			boarddao.delete(num);
		} else if(BOARD_PASS.equals(requestpass)) {
			out.println("alert('삭제 완료하였습니다.');");
			out.println("location.href='BoardList.bo';");
			boarddao.delete(num);
		} else {
			out.println("alert('삭제가 되지 않았습니다.');");
			out.println("history.back()");
		}
		
		out.println("</script>");
		out.close();
		return null;
	}

}
