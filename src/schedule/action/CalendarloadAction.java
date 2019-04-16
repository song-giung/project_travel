package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import schedule.db.DetailBean;
import schedule.db.DetailDAO;
import schedule.db.ScheduleBean;
import schedule.db.ScheduleDAO;

public class CalendarloadAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ScheduleDAO sch_DAO = new ScheduleDAO();
		DetailDAO det_DAO = new DetailDAO();
		HttpSession session = request.getSession();
		List<ScheduleBean> list= new ArrayList<ScheduleBean>();
		List<DetailBean> detail = new ArrayList<DetailBean>();
		String user_id = (String) session.getAttribute("user_id");
		
		list=sch_DAO.viewSchedule(user_id);
		detail=det_DAO.viewDetail_all(user_id);
		
		
		
		request.setAttribute("list", list);
		request.setAttribute("detail", detail);
		
		forward.setRedirect(false);
		forward.setPath("/Schedule/Calendar_view.jsp");
		return forward;
	}

}
