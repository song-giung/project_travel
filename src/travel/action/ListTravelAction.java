package travel.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.db.travel;
import travel.db.travelDAO;

public class ListTravelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		

		ActionForward forward =new ActionForward();
		travelDAO trdao = new travelDAO();
		List<travel> list  = trdao.getTravelList();
		
		forward.setPath("/travel/travel_list.jsp");
		forward.setRedirect(false);
		request.setAttribute("travellist", list);
		return forward;
	}

}
