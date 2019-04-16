package notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.db.*;


public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		
		ActionForward forward = new ActionForward();
		NoticeDAO nd = new NoticeDAO();
		NoticeBean nb = new NoticeBean();
		request.setCharacterEncoding("utf-8");
		boolean result =false;
		int num = Integer.parseInt(request.getParameter("num"));
	
		nb.setNotice_no(num);
		
		result = nd.delete(nb);
		request.setAttribute("nb", nb);

		if(result ==false) {
			System.out.println("실패");
			return null;
		}
		System.out.println("성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 완료.')");
		out.println("location.href='./NoticeList.no';");
		out.println("</script>");
		out.close();
		return null;
	}

}
