package festival.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import festival.db.festival;
import festival.db.festivalDAO;

public class ListFestivalAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		List<festival> list = new ArrayList<festival>();
		festival f = new festival();
		festivalDAO fdao = new festivalDAO();
		
		
		
		
		
	
			list=fdao.getFestivalList();
		
		
		
		request.setAttribute("festivalList", list);
		forward.setPath("/festival/festival_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
