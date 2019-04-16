package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.DetailBean;
import schedule.db.FestivalBean;
import schedule.db.TravelBean;
import schedule.db.Travel_InfoDAO;

public class MapManagePageView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		List<TravelBean> trav_List= new ArrayList<TravelBean>();
		List<FestivalBean> fest_List = new ArrayList<FestivalBean>();
		List<DetailBean> detail = new ArrayList<DetailBean>();
		Travel_InfoDAO mDAO = new Travel_InfoDAO();
		HttpSession session = request.getSession();
		String check = (String)request.getParameter("check");//total or select°¡ ¿Â´Ù	
		String user_id = (String)session.getAttribute("user_id");
		
		
		
		if(check.equals("total")) {
			trav_List = mDAO.loadManagePageTravelInfo(user_id);// check
			fest_List = mDAO.loadManagePageFestivalInfo(user_id); //check
		
			request.setAttribute("trav_List",trav_List);
			request.setAttribute("fest_List", fest_List);
			forward.setRedirect(false);
			forward.setPath("/Schedule/Travel_Map.jsp");
		}else if(check.equals("select")) {
			
			int sch_no = Integer.parseInt(request.getParameter("sch_no"));
			trav_List = mDAO.loadManagePageTravelInfoBySelect(sch_no);
			fest_List = mDAO.loadManagePageFestivalInfoBySelect(sch_no);

			request.setAttribute("trav_List",trav_List);
			request.setAttribute("fest_List", fest_List);
			forward.setRedirect(false);
			forward.setPath("/Schedule/Travel_Map.jsp");
			
		}
	
		return forward;
	}

}
