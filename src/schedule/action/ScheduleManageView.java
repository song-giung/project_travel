package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.DetailBean;
import schedule.db.DetailDAO;
import schedule.db.FestivalBean;
import schedule.db.ScheduleBean;
import schedule.db.ScheduleDAO;
import schedule.db.TravelBean;
import schedule.db.Travel_InfoDAO;

public class ScheduleManageView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		List<ScheduleBean> list = new ArrayList<ScheduleBean>();
		List<DetailBean> detail = new ArrayList<DetailBean>();
		String user_id =(String)session.getAttribute("user_id");
		ScheduleDAO sch_DAO = new ScheduleDAO();
		DetailDAO det_DAO = new DetailDAO();
		ScheduleBean sch_bean = new ScheduleBean();
		
		String check=request.getParameter("Schedule_choice");
		
		System.out.println(check);
		
		//지도만들기
		System.out.println("지도 만들기 시작");
		List<TravelBean> trav_List= new ArrayList<TravelBean>();
		List<FestivalBean> fest_List = new ArrayList<FestivalBean>();
		Travel_InfoDAO mDAO = new Travel_InfoDAO();
		
		String check_map="";
		if(request.getParameter("check")!=null)
			check_map = (String)request.getParameter("check");//total or select가 온다	
		
	
		
	
		if(check.equals("all")) {
		
			trav_List = mDAO.loadManagePageTravelInfo(user_id);// check
			fest_List = mDAO.loadManagePageFestivalInfo(user_id); //check
		
			request.setAttribute("trav_List",trav_List);
			request.setAttribute("fest_List", fest_List);
			
			//////////////////////////////////////////////////
			int page = 1;
			int limit = 5;
			int listcount = sch_DAO.getScheduleListCount(user_id);
			int maxpage = listcount/limit ; 
			if(listcount%limit>0)
				maxpage++;
			
			if(request.getParameter("page")!=null)
				page = Integer.parseInt(request.getParameter("page"));
			
			int startpage=((page-1)/limit)*limit+1;
			int endpage = startpage+limit-1;
			if(endpage>maxpage)
				endpage=maxpage;
			
			
			list=sch_DAO.viewSchedule_main(user_id,page,limit);
			request.setAttribute("page",page);
			request.setAttribute("maxpage", maxpage);
			request.setAttribute("startpage", startpage);
			request.setAttribute("endpage", endpage);
			request.setAttribute("listcount", listcount);
			request.setAttribute("list", list);
			
			//////////////////////////////////////////////
			
			int pageDT = 1;
			int limitDT = 5;
			int listcountDT=det_DAO.getDetailListCount(user_id);
			int maxpageDT=listcountDT/limitDT;
			if(listcountDT%limitDT>0);
				maxpageDT++;
			
		
			if(request.getParameter("pageDT")!=null)
				pageDT = Integer.parseInt(request.getParameter("pageDT"));
			
			int startpageDT = ((pageDT-1)/limitDT)*limitDT+1;
			int endpageDT = startpageDT+limitDT-1;
			if(endpageDT>maxpageDT)
				endpageDT=maxpageDT;

			
			detail=det_DAO.viewDetail(user_id,pageDT,limitDT);
			request.setAttribute("pageDT",pageDT);
			request.setAttribute("maxpageDT", maxpageDT);
			request.setAttribute("startpageDT", startpageDT);
			request.setAttribute("endpageDT", endpageDT);
			request.setAttribute("listcountDT", listcountDT);
			request.setAttribute("detail",detail); 
			
			forward.setRedirect(false);
			/////////////////////////////////////////////////////////////
			if(request.getParameter("state")!=null) {
				forward.setPath("/Schedule/Ajax_main_Schedule.jsp");
			}else if(request.getParameter("stateDT")!=null){
				forward.setPath("/Schedule/Ajax_manage_detailAll.jsp");
			}else {
				forward.setPath("/Schedule/Schedule_manage.jsp");
			}
			
		}else {
			
			int sch_no = Integer.parseInt(check);
			
			trav_List = mDAO.loadManagePageTravelInfoBySelect(sch_no);
			fest_List = mDAO.loadManagePageFestivalInfoBySelect(sch_no);

			request.setAttribute("trav_List",trav_List);
			request.setAttribute("fest_List", fest_List);
			
			
			detail = det_DAO.viewDetail_ManagePage(sch_no);
			sch_bean = sch_DAO.viewScheduleManagePage(sch_no);
			list=sch_DAO.viewSchedule(user_id); //매니지 화면 선택 목록 출력위해 보냄
			request.setAttribute("scheduleList", list);
			request.setAttribute("detail",detail);
			request.setAttribute("list", sch_bean);
			request.setAttribute("sch_no", sch_bean.getSch_no());//manage2.jsp에서 달력부분에서 일정에 관련된 세부 일정 불러오기위해서 일정 번호를 보내준다.
			forward.setRedirect(false);
			
			
			////////////////

			int pageDT = 1;
			int limitDT = 5;
			int listcountDT=det_DAO.getDetailListCount(user_id);
			int maxpageDT=listcountDT/limitDT;
			if(listcountDT%limitDT>0);
				maxpageDT++;
			
		
			if(request.getParameter("pageDT")!=null)
				pageDT = Integer.parseInt(request.getParameter("pageDT"));
			
			int startpageDT = ((pageDT-1)/limitDT)*limitDT+1;
			int endpageDT = startpageDT+limitDT-1;
			if(endpageDT>maxpageDT)
				endpageDT=maxpageDT;

			
			detail=det_DAO.viewDetail_select(user_id,sch_no,pageDT,limitDT);
			request.setAttribute("pageDT",pageDT);
			request.setAttribute("maxpageDT", maxpageDT);
			request.setAttribute("startpageDT", startpageDT);
			request.setAttribute("endpageDT", endpageDT);
			request.setAttribute("listcountDT", listcountDT);
			request.setAttribute("detail",detail); 
			request.setAttribute("sch_no", sch_no);
	
			///////////
			
			
			
			forward.setRedirect(false);
			if(request.getParameter("stateDT")!=null){
				forward.setPath("/Schedule/Ajax_manage_detailAll.jsp");
			}else {
			forward.setPath("/Schedule/Schedule_manage2.jsp");
		
			}
		
		
		
		
		}
		
		
	
		return forward;
	}

}
