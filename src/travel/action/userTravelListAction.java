package travel.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.db.travel_image;
import travel.db.travel_imgDAO;
import travel.db.userTravelDAO;
import travel.db.userTravelList;

public class userTravelListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		String tema = request.getParameter("tema");
		System.out.println(tema);
		
		travel_image timg = new travel_image();
		userTravelDAO utdao = new userTravelDAO();
		List<userTravelList> list = new ArrayList<userTravelList>();
		list=utdao.getUserTravel_Tema_List(tema);
		
		
		request.setAttribute("travellist", list);
		request.setAttribute("tema", tema);
		forward.setPath("/travel/userTravel_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
