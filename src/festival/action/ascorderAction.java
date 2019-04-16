package festival.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import festival.db.festival;
import festival.db.festivalDAO;

public class ascorderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Èþ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		ActionForward forward = new ActionForward();
		List<festival> list = new ArrayList<festival>();
		festival f = new festival();
		festivalDAO fdao = new festivalDAO();
		String state =request.getParameter("state");
		System.out.println(state);
		if(state==null) {
			System.out.println("Èþ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			list=fdao.getFestivalList();
		}else {
			System.out.println("Çó!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			list=fdao.getFestivalList_asc();
		}
		
		request.setAttribute("festivalList", list);
		
		forward.setPath("/festival/admin_festival_list.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
