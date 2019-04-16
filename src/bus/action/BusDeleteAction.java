package bus.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import bus.db.BusDAO;

public class BusDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		BusDAO bd = new BusDAO();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String area=(String)session.getAttribute("area");
		String start=(String)session.getAttribute("start");
		String end=(String)session.getAttribute("end");
		int no = Integer.parseInt(request.getParameter("bus_no"));
		
		session.setAttribute("no", no);
		
		System.out.println("delete1 "+ area);
		System.out.println("delete2 "+ start);
		System.out.println("delete3 "+ end);
		System.out.println("delete4 " + no );
		
		int result = bd.Busdelete(area, no);
		System.out.println(result);
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		if(result==1) {
		out.println("alert('버스 노선을 삭제하였습니다.')");
		out.println("location.href='./BusSearch.bus'");
		
		}else if(result==-1){
			out.println("alert('버스 노선 삭제에 실패하였습니다.')");
			out.println("location.href='./BusSearch.bus'");
		}
		out.println("</script>");
		out.close();
		
		return null;
	}

}
