package festival.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import festival.db.userFestivalDAO;
import festival.action.ActionForward;

public class festival_updeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				ActionForward forward = new ActionForward();
				
				int no = Integer.parseInt(request.getParameter("festival_no"));
				int user_no = Integer.parseInt(request.getParameter("user_no"));
				
				userFestivalDAO utdao = new userFestivalDAO();
				
				utdao.up_delete(user_no, no);
				
				int u1=utdao.up_count1(no);
				int u2=utdao.up_count2(no);
				int u3=utdao.up_count3(no);
				int u4=utdao.up_count4(no);
				
				int d1=utdao.down_count1(no);
				int d2=utdao.down_count2(no);
				int d3=utdao.down_count3(no);
				int d4=utdao.down_count4(no);
				
				
				int up_check = utdao.up_select(user_no, no);
				int down_check = utdao.down_select(user_no, no);
				
				System.out.println("up_check~~"+up_check+"down_check~~~"+down_check);
				request.setAttribute("up_check", up_check);
				request.setAttribute("down_check", down_check);
				
				
				request.setAttribute("u1", u1);
				request.setAttribute("u2", u2);
				request.setAttribute("u3", u3);
				request.setAttribute("u4", u4);
				
				request.setAttribute("d1", d1);
				request.setAttribute("d2", d2);
				request.setAttribute("d3", d3);
				request.setAttribute("d4", d4);
				
				forward.setPath("/travel/userfestival_typecount.jsp");
				forward.setRedirect(false);
				return forward;
	}

}
