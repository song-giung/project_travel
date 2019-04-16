package bus.action;
import java.io.PrintWriter;
import java.sql.*;
import javax.sql.*;

import org.json.simple.JSONArray;

import bus.db.BusDAO;

import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Select_start implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		BusDAO bd = new BusDAO();
		JSONArray array = new JSONArray();
		request.setCharacterEncoding("utf-8");
		String area= request.getParameter("area");
		
	  	HttpSession session = request.getSession();
	  	session.setAttribute("area", area);
	  	
		System.out.print(area);
		array = bd.select_start(area);
		
		response.setHeader("cache-control","no-cache,no-store");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(array);
		out.print(array);
		return null;
	}
	

}
