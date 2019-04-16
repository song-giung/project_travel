package bus.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import bus.db.BusDAO;

public class Select_end implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		BusDAO bd = new BusDAO();
		JSONArray array = new JSONArray();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String area=(String)session.getAttribute("area");
		String start= request.getParameter("bus_start");
		
		session.setAttribute("start", start);
		
		System.out.println("end1 "+ area);
		System.out.println("end2 "+ start);
		
		array = bd.select_end(area, start);
		
		response.setHeader("cache-control","no-cache,no-store");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(array);
		out.print(array);
		return null;
	}

}
