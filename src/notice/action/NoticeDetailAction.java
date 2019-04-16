package notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.db.NoticeBean;
import notice.db.NoticeDAO;

public class NoticeDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		NoticeDAO nd = new NoticeDAO();
		NoticeBean nb = new NoticeBean();
		
		int num = Integer.parseInt(request.getParameter("num"));

		nb = nd.getDetail(num);
		

		if(nb == null) {
			System.out.println("실패");
			return null;
		}
		System.out.println("성공");
		
		request.setAttribute("nb", nb);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("./Notice/notice_view.jsp");
		return forward;
	}

}











