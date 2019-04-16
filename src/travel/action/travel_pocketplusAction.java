package travel.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import festival.db.userFestivalDAO;
import travel.db.userTravelDAO;

public class travel_pocketplusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int travel_no = Integer.parseInt(request.getParameter("no"));
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		
		System.out.println(user_no);
		userTravelDAO utdao = new userTravelDAO();
		
		utdao.travel_pocketplus(user_no, travel_no);
		
		
		return null;
	}

}
