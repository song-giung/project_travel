package festival.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import festival.db.userFestivalDAO;

public class festival_pocketplusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		int festival_no = Integer.parseInt(request.getParameter("no"));
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		
		System.out.println(user_no);
		userFestivalDAO ufdao = new userFestivalDAO();
		

		ufdao.festival_pocketplus(user_no, festival_no);
		
		return null;
	}

}
