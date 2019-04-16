package notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import notice.db.NoticeBean;
import notice.db.NoticeDAO;

public class NoticeModifyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		request.setCharacterEncoding("utf-8");
		
		NoticeDAO nd = new NoticeDAO();
		NoticeBean nb = new NoticeBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		//글의 내용을 DAO에서 읽은 후 얻은 결과를 board 객체에 저장합니다.
		nb = nd.getDetail(num);
		
		//DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
		if(nb == null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
		
		request.setAttribute("nb", nb);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("./Notice/notice_modify.jsp");
		return forward;
	}

}
