package bus.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bus.db.*;

public class ScheduleAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		BusDAO bd = new BusDAO();
		BusBean bb = new BusBean();
		request.setCharacterEncoding("utf-8");
		String user_id=(String) session.getAttribute("user_id");
		
		
		String area = (String)session.getAttribute("area");
		int no = (Integer)session.getAttribute("no");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		String date = request.getParameter("date");
		
		
		System.out.println(area);
		System.out.println(no);
		System.out.println(seq);
		System.out.println(date);
		
		bb = bd.addSchedule(user_id,area, no, seq);
		System.out.println("노선 일정 추가");
	/*	forward.setPath("/Bus/BusSchedule.jsp");
		forward.setRedirect(false);
		request.setAttribute("add", bb); 
		request.setAttribute("date", date);*/
		
		return null;//null로 변경
	}
}
