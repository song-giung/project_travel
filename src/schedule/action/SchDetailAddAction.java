package schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.DetailBean;
import schedule.db.DetailDAO;
import schedule.db.ScheduleDAO;

public class SchDetailAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		DetailBean det_Bean = new DetailBean();
		DetailDAO det_DAO = new DetailDAO();
		HttpSession session = request.getSession();
		boolean result = false;
		String user_id = (String)session.getAttribute("user_id");
		String check = request.getParameter("check");
	
		String travel_no ="";
		String festival_no = "";
		String det_start = "";
		String det_end ="";
		String det_date ="";
		String det_content = request.getParameter("det_content");
		System.out.println("check 绰  " +check);
		if(check.equals("travel")) {
			travel_no = request.getParameter("travel_no");
			det_start = request.getParameter("det_start");
			det_end = request.getParameter("det_end");
			System.out.println("travel");
			System.out.println("travel_no :"+travel_no);
			System.out.println("det_start :"+det_start);
			System.out.println("det_end :"+det_end);
			if(det_start.equals(det_end)) {
				det_date = det_start;
				det_Bean.setDet_date(det_date);
			}
			det_Bean.setTravel_no(Integer.parseInt(travel_no));
			det_Bean.setDet_start(det_start);
			det_Bean.setDet_end(det_end);
			det_Bean.setDet_content(det_content);
			
		}else if(check.equals("festival")){
			festival_no = request.getParameter("festival_no");
			det_start = request.getParameter("det_start");
			det_end = request.getParameter("det_end");
			System.out.println("festival");
			System.out.println("festival_no :"+festival_no);
			System.out.println("det_start :"+det_start);
			System.out.println("det_end :"+det_end);
			if(det_start.equals(det_end)) {
				det_date = det_start;
				det_Bean.setDet_date(det_date);
			}
			det_Bean.setFestival_no(Integer.parseInt(festival_no));
			det_Bean.setDet_start(det_start);
			det_Bean.setDet_end(det_end);
			det_Bean.setDet_content(det_content);
		}
			
	
			result = det_DAO.addDetail(det_Bean,user_id,check);
		//	if(result)
			//	System.out.println("技何 老沥 积己 肯丰");
			
				
		return null;
	}

}
