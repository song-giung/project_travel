package schedule_board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.ScheduleBean;
import schedule.db.ScheduleDAO;

public class SchBoardInsertView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		//기웅오빠네 빈들
		ScheduleBean schbean=new ScheduleBean();
		ScheduleDAO scheduledao = new ScheduleDAO();
		List<ScheduleBean> sblist=new ArrayList<ScheduleBean>();
		
		ActionForward forward=new ActionForward();
		
		//둘중하나 씀...기웅오빠 DAO 엔 id로 받음
		int user_no = (int)session.getAttribute("user_no");
		String user_id=(String)session.getAttribute("user_id");
		
		sblist=scheduledao.viewSchedule(user_id);
		
		
		request.setAttribute("sblist", sblist);
		request.setAttribute("user_no", user_no);
		forward.setPath("/ScheduleBoard/SchBoardInsertView.jsp");
		return forward;
	}

}
