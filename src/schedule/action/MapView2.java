package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.FestivalBean;
import schedule.db.Travel_InfoDAO;
import schedule.db.TravelBean;

public class MapView2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		List<TravelBean> trav_List = new ArrayList<TravelBean>();
		List<FestivalBean> fest_List = new ArrayList<FestivalBean>();		
		Travel_InfoDAO mDAO = new Travel_InfoDAO();
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		
		trav_List = mDAO.loadTravelInfo(user_id);
		fest_List = mDAO.loadFestivalInfo(user_id);
		
		request.setAttribute("trav_List",trav_List);
		request.setAttribute("fest_List", fest_List);
		forward.setRedirect(false);
		forward.setPath("/Schedule/Travel_Map.jsp");
		return forward;
	}

}
