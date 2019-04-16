package festival.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import festival.db.userFestival;
import festival.db.userFestivalDAO;

public class UserFestivalListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		List<userFestival> list = new ArrayList<userFestival>();
		userFestivalDAO ufdao = new userFestivalDAO();
		String state =request.getParameter("state");
		System.out.println(state);
		String location =request.getParameter("location");
		if(state==null) {
		list=ufdao.userFestival_list();
		request.setAttribute("festivalList", list);
		forward.setPath("/festival/userfestival_list.jsp");
		forward.setRedirect(false);
		}else {
			list=ufdao.userFestival_list(location);
			request.setAttribute("festivalList", list);
			forward.setPath("/festival/userfestival_location.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
