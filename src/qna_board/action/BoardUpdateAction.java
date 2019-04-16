package qna_board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import qna_board.db.BoardBean;
import qna_board.db.BoardDAO;

public class BoardUpdateAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		request.setCharacterEncoding("utf-8");

		String realFolder = "";

		String saveFolder = "boardupload";

		int fileSize = 5 * 1024 * 1024;

		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		int result = -1;

		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());

			boarddata.setBOARD_NUM(Integer.parseInt(multi.getParameter("BOARD_NUM")));
			boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));

			String BOARD_PASS = boarddao.updateCheck(Integer.parseInt(multi.getParameter("BOARD_NUM")));
			String requestpass = multi.getParameter("BOARD_PASS");

			if (BOARD_PASS == null) {
				result = 0;
				boarddao.update(boarddata);
			}

			if (BOARD_PASS.equals(requestpass)) {
				result = 0;
				boarddao.update(boarddata);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<script>");
		if (result == 0) {
			out.println("alert('수정 완료 하였습니다.');");
			out.println("location.href='BoardList.bo';");
		} else {
			out.println("alert('수정을 하지 못하였습니다.');");
			out.println("history.back()");
		}

		out.println("</script>");
		out.close();

		return null;
	}
}
