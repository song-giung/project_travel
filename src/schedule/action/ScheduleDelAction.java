package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.*;

public class ScheduleDelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ScheduleDAO sch_DAO =  new ScheduleDAO();
	
		DetailDAO det_DAO = new DetailDAO();
		
		List<ScheduleBean> list = new ArrayList<ScheduleBean>();
		List<DetailBean> detail = new ArrayList<DetailBean>();
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		//일정 삭제 , 세부일정 삭제 , 일정 출력 
		
		
		String str =request.getParameter("ch1");
		String str_array[] = str.split(" ");
		System.out.println(str);
	
		//관련된 세부 일정 삭제
		boolean result1 =det_DAO.delDetail(str_array);
		/*if(result1)
			System.out.println("delDetail 완료");*/
		//일정 삭제
		boolean result = sch_DAO.delSchedule(str_array);
		/*if(result)
			System.out.println("delSchedule 완료");
*/
		
		
		int page = 1; //기본 값
		int limit = 5; //한페이지에 보여줄 일정 개수
		int listcount = sch_DAO.getScheduleListCount(user_id); //해당 회원의 생성된 일정 총 개수 
		int maxpage=listcount/limit;
		if(listcount%limit>0)
			maxpage++;

		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}else {}
		int startpage=((page-1)/limit)*limit+1;
		int endpage=startpage+limit-1;
		if(endpage>maxpage)
			endpage=maxpage;
		
		list = sch_DAO.viewSchedule_main(user_id,page,limit); //일정 목록 가져오기 
		System.out.println("page : "+page);
		request.setAttribute("page", page); //일정 현재 페이지 수
		request.setAttribute("maxpage", maxpage); //일정 최대 페이지 수
		request.setAttribute("startpage", startpage);//일정 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpage", endpage);//일정 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount", listcount); //총 글의 수
		request.setAttribute("list", list); //글 bean
		
		
				
		forward.setRedirect(false);
		forward.setPath("/Schedule/Ajax_main_Schedule.jsp");
		
		return forward;
	}

}
