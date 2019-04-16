package travel.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.db.travelDAO;

public class travelDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward =new ActionForward();
		String[] no = request.getParameterValues("array[]");
		System.out.println(request.getParameterValues("array[]"));
		travelDAO tdao = new travelDAO();
		
		
		boolean check  =false;
		for(int i=0;i<no.length;i++) {
			check=tdao.travel_delete(Integer.parseInt(no[i]));
		}

		forward.setPath("/travel/travel_list.tr");
		forward.setRedirect(false);
		return forward;
	}

}
