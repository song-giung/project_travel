package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.DetailBean;
import schedule.db.DetailDAO;

public class Schedule_Detail_DelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		DetailDAO det_DAO = new DetailDAO();
		List<DetailBean> detail = new ArrayList<DetailBean>();
		HttpSession session = request.getSession();
		String user_id =(String)session.getAttribute("user_id");
		
		String str = request.getParameter("ch1");
		String str_array[]=str.split(" ");
		boolean result = det_DAO.delDetail(str_array);
		if(result)
			System.out.println("delDetail(String sch_no[] ¿Ï·á");
		detail = det_DAO.viewDetail_all(user_id);
		
		request.setAttribute("detail",detail);
		forward.setRedirect(false);
		forward.setPath("/Schedule/AjaxDetailDel.jsp");
		return forward;
	}

}
