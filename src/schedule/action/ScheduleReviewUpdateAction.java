package schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.ScheduleDAO;

public class ScheduleReviewUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ScheduleDAO sch_DAO = new ScheduleDAO();
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("user_id");
		int sch_no =Integer.parseInt(request.getParameter("sch_no"));
		String update_content= request.getParameter("sch_content");
		
		sch_DAO.updateScheduleReview(user_id, sch_no, update_content);
		
		return null;
	}

}
