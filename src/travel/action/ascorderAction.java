package travel.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.action.ActionForward;

import travel.db.travel;
import travel.db.travelDAO;

public class ascorderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =new ActionForward();
		travelDAO trdao = new travelDAO();
		List<travel> list  = new ArrayList<travel>();
		
		
		String state =request.getParameter("state");
		
		System.out.println(state);
		if(state==null) {
			list  = trdao.getTravelList();
			
		}else {
			list = trdao.getTravelList_desc();
		}
		
		
		forward.setPath("/travel/admin_travel_list.jsp");
		forward.setRedirect(false);
		request.setAttribute("travellist", list);
		return forward;
		
		 
		
	}

}
