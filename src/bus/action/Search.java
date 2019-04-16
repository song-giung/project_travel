package bus.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import bus.db.BusDAO;

public class Search implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		BusDAO bd = new BusDAO();
		JSONArray array = new JSONArray();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String area=(String)session.getAttribute("area");
		String start=(String)session.getAttribute("start");
		String end=(String)session.getAttribute("end");
		int no = Integer.parseInt(request.getParameter("bus_no"));
		
		session.setAttribute("no", no);
		System.out.println("search1 "+ area);
		System.out.println("search2 "+ start);
		System.out.println("search3 "+ end);
		System.out.println("search4 " + no );
		
		array = bd.search(area, no);
		
		response.setHeader("cache-control","no-cache,no-store");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(array);
		out.print(array);
		return null;
	}

}
