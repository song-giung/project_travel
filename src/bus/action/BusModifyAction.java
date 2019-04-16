package bus.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bus.db.BusBean;
import bus.db.BusDAO;


public class BusModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		  request.setCharacterEncoding("utf-8");
		  ActionForward forward = new ActionForward();
		  //HttpSession session = request.getSession();
		  
		  String bus_area = request.getParameter("bus_area"); 
		  System.out.println("버스 수정 지역 : " + bus_area);
		 
		  int bus_no = Integer.parseInt(request.getParameter("hidden"));
		   System.out.println("버스 수정 번호 : " + bus_no );
		  
		  BusDAO bd = new BusDAO();
	      BusBean bb = bd.bus_info(bus_area, bus_no);
	      System.out.println("수정 완료");
	      
	      List<BusBean> list = bd.scehdulelist(bus_area,bus_no);
	      System.out.println("수정 리스트 뽑기 완료");
	
	      forward.setPath("/Bus/BusModify.jsp");
	      forward.setRedirect(false);
	      request.setAttribute("bus", bb);
	      request.setAttribute("schedulelist", list);
	      
	      return forward;
	}

}
