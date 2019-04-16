package bus.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bus.db.BusBean;
import bus.db.BusDAO;

public class BusAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		
		request.setCharacterEncoding("utf-8");
		BusDAO bd = new BusDAO();
		BusBean bb = new BusBean();
		
		String bus_area = request.getParameter("bus_area");
		String bus_linename = request.getParameter("bus_linename");
		String bus_start = request.getParameter("bus_start");
		String bus_end = request.getParameter("bus_end");
		String bus_traveltime = request.getParameter("bus_traveltime");
		int bus_cost_ad = Integer.parseInt(request.getParameter("bus_cost_ad"));
		int bus_cost_st = Integer.parseInt(request.getParameter("bus_cost_st"));
		int bus_cost_ch = Integer.parseInt(request.getParameter("bus_cost_ch"));
		String [] seq = request.getParameterValues("bus_seq");		
		int[] bus_seq = new int[seq.length];		
		for(int i=0; i<seq.length; i++) {
			bus_seq[i]=Integer.parseInt(seq[i]);
		}
		String [] bus_departure = request.getParameterValues("bus_departure");
		String [] bus_arrival = request.getParameterValues("bus_arrival");
		
		bb.setBus_area(bus_area);
		bb.setBus_linename(bus_linename);
		bb.setBus_start(bus_start);
		bb.setBus_end(bus_end);
		bb.setBus_traveltime(bus_traveltime);
		bb.setBus_cost_ad(bus_cost_ad);
		bb.setBus_cost_st(bus_cost_st);
		bb.setBus_cost_ch(bus_cost_ch);
		
		int result = bd.BusInsert(bb);
		System.out.println("BusAddAction=" + result);
		int schedule = bd.BusInsertSchedule(result,bb, bus_seq, bus_departure, bus_arrival);
		

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		if(schedule==1) {
		out.println("alert('버스 노선이 추가되었습니다.')");
		out.println("location.href='./BusAdd.bus'");
		
		}else if(schedule==-1){
			out.println("alert('버스 노선 추가에 실패하였습니다.')");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
		return null;
	}

	
}
