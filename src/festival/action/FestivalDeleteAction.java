package festival.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import festival.db.festivalDAO;
import travel.db.travelDAO;

public class FestivalDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward =new ActionForward();
		String[] no = request.getParameterValues("array[]");
		System.out.println(request.getParameterValues("array[]"));
		
		festivalDAO fdao = new festivalDAO();
		
		for(int i=0;i<no.length;i++) {
			fdao.delete_festival(Integer.parseInt(no[i]));
		}

		forward.setPath("/festival/festival_list.fs");
		forward.setRedirect(false);
		return forward;
	}

}
