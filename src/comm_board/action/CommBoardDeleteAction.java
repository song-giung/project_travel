package comm_board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm_board.db.CommBoardDAO;

public class CommBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		CommBoardDAO cdao = new CommBoardDAO();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//가져온 정보
		
		int num= Integer.parseInt(request.getParameter("num"));
	
		PrintWriter out=response.getWriter();
		String if_admin=(String)session.getAttribute("user_id");
		
		boolean deletecheck=cdao.boardDelete(num);
		if(deletecheck==true||if_admin.equals("admin")) {
			out.println("<script>");
			out.println("alert(' 게시판 삭제 성공 ')");
			out.println("location.href='./ListPage.combo';");
			out.println("</script>");
			out.close();
		}else
			System.out.println("삭제 실패요");
		
		return null;
	}

}
