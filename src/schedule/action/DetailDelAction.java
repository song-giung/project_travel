package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.DetailBean;
import schedule.db.DetailDAO;

public class DetailDelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		DetailDAO det_DAO = new DetailDAO();
		HttpSession session = request.getSession();
		List<DetailBean> detail = new ArrayList<DetailBean>();
		
		String str = request.getParameter("ch2");
		String str_array[] = str.split(" ");
	
		String user_id = (String)session.getAttribute("user_id");
	
		//세부 일정 지움
		boolean result = det_DAO.delDetail_det_no(str_array);
		if(result)
			System.out.println("delDetail 완료");
		//세부 일정 재 조회 후 ajax로 전달
		detail = det_DAO.viewDetail_all(user_id);
		
		request.setAttribute("detail", detail);
	
		
		forward.setRedirect(false);
		forward.setPath("/Schedule/AjaxDetailDel.jsp");	

		return forward;
	}

}
