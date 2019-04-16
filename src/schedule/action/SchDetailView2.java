package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.DetailBean;
import schedule.db.DetailDAO;
import schedule.db.PocketBean;
import schedule.db.PocketDAO;
import schedule.db.ScheduleBean;
import schedule.db.ScheduleDAO;

public class SchDetailView2 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();	
		DetailDAO det_DAO = new DetailDAO();
		HttpSession session = request.getSession();
		
		String user_id =(String)session.getAttribute("user_id");
		
	
		List<DetailBean> detail= new ArrayList<DetailBean>();

		detail = det_DAO.viewDetail_all(user_id);
		
		
		request.setAttribute("detail", detail);
		
		forward.setRedirect(false);
		forward.setPath("/Schedule/AjaxDetailDel.jsp");
		return forward;
	}

}
