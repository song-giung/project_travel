package travel.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.db.userTravelDAO;
import travel.db.userTravelList;

public class userTravel_TypeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String[] types= null;
		
		List<userTravelList> list = new ArrayList<userTravelList>();
		types=request.getParameterValues("type[]");
		String tema=request.getParameter("tema");
		userTravelDAO utdao = new userTravelDAO();
		
		if(types== null) {
			list=utdao.getUserTravel_Tema_List(tema);
		}else {
		
		list=utdao.getTypeList(types, tema);
		}
		request.setAttribute("travellists",list );
		request.setAttribute("tema", tema);
		ActionForward forward = new ActionForward();
		forward.setPath("/travel/userTravel_typelist.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
