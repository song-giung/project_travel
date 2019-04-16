package notice.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.db.*;

public class NoticeAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		NoticeDAO nd = new NoticeDAO();
		NoticeBean nb = new NoticeBean();
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();

		boolean result= false;
		
		nb.setAdmin_id(request.getParameter("admin_id"));
		nb.setNotice_title(request.getParameter("notice_title"));
		nb.setNotice_content(request.getParameter("notice_content"));
		
		result=nd.noticeInsert(nb);
		
	
			if(result==false) {
				System.out.println("실패");
				return null;
			}
			System.out.println("성공");
			
			
			forward.setRedirect(true);
		
			forward.setPath("./NoticeList.no");
			return forward;
	}
}








