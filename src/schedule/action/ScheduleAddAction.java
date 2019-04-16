package schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.ScheduleBean;
import schedule.db.ScheduleDAO;

public class ScheduleAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ScheduleDAO sch_DAO = new ScheduleDAO();
		ScheduleBean sch_bean = new ScheduleBean();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");//세션에 저장된 아이디를 가져온다.
		boolean result = false;
		
		String sch_title= request.getParameter("sch_title");
		String sch_content = request.getParameter("sch_content");
		String start_date = request.getParameter("start_date"); //날짜는 DB데이터 형식이 DATE임 쿼리문에서 변경 후 insert해야함
		String end_date = request.getParameter("end_date");
		System.out.println("sch_title : " + sch_title);
		System.out.println("sch_content : "+sch_content);
		System.out.println("start_date : " +start_date);
		System.out.println("end_date : "+end_date);
		
		sch_bean.setSch_title(sch_title);
		sch_bean.setSch_content(sch_content);
		sch_bean.setStart_date(start_date);
		sch_bean.setEnd_date(end_date);
		//받아온 일정 이름/일정내용/일정 시작,종료시간을 bean에 넣어줌
		 
		result = sch_DAO.addSchedule(sch_bean,user_id) ;
		
		
		return null;
	}

}
