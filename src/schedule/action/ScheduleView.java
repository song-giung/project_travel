package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bus.db.UserBusInfoBean;
import bus.db.UserBusInfoDAO;
import schedule.db.*;

public class ScheduleView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ScheduleDAO sch_DAO = new ScheduleDAO();
		DetailDAO det_DAO = new DetailDAO();
		PocketDAO pock_DAO = new PocketDAO();
		Travel_InfoDAO travel_InfoDAO = new Travel_InfoDAO();
		HttpSession session = request.getSession();
		List<ScheduleBean> list = new ArrayList<ScheduleBean>();
		List<DetailBean> detail = new ArrayList<DetailBean>();
		List<PocketBean> pocket = new ArrayList<PocketBean>();
		List<TravelBean> travel = new ArrayList<TravelBean>();
		List<FestivalBean>festival = new ArrayList<FestivalBean>();
		
		
		
		
		/*session.setAttribute("user_id","admin");*/
		String user_id = (String)session.getAttribute("user_id");
		
		System.out.println(user_id);
		
		System.out.println("1");
		List<UserBusInfoBean> ubib= new ArrayList<UserBusInfoBean>();
		UserBusInfoDAO ubibDAO = new UserBusInfoDAO();
		System.out.println("2");
		
		ubib=ubibDAO.view_bus_info(user_id);
		
		System.out.println("3");
		request.setAttribute("ubib", ubib);
		
		
		
		
		
		
		
		//////////////////////////////////////////////////////////////////////////////
		int page = 1; //�⺻ ��
		int limit = 5; //���������� ������ ���� ����
		int listcount = sch_DAO.getScheduleListCount(user_id); //�ش� ȸ���� ������ ���� �� ���� 
		int maxpage=listcount/limit;
		if(listcount%limit>0)
			maxpage++;

		if(request.getParameter("page")!=null) 
			page=Integer.parseInt(request.getParameter("page"));
	
		int startpage=((page-1)/limit)*limit+1;
		int endpage=startpage+limit-1;
		if(endpage>maxpage)
			endpage=maxpage;
		
		list = sch_DAO.viewSchedule_main(user_id,page,limit); //���� ��� �������� 
	
		request.setAttribute("page", page); //���� ���� ������ ��
		request.setAttribute("maxpage", maxpage); //���� �ִ� ������ ��
		request.setAttribute("startpage", startpage);//���� ���� �������� ǥ���� ù ������ ��
		request.setAttribute("endpage", endpage);//���� ���� �������� ǥ���� �� ������ ��
		request.setAttribute("listcount", listcount); //�� ���� ��
		request.setAttribute("list", list); //�� bean
		
		//////////////////////////////////////////////// ���� ����¡ ó��
		
		////////////////////////////////////////////////
		//pt = pocket Travel
		int pagePT =1;
		int limitPT = 5;
		int listcountPT =travel_InfoDAO.getTravelListCount(user_id);//�ش� ȸ���� ������ ���� �� ���� 
		int maxpagePT=listcountPT/limitPT;
		if(listcountPT%limitPT>0)
			maxpagePT++;

		if(request.getParameter("pagePocketTravel")!=null) 
			pagePT=Integer.parseInt(request.getParameter("pagePocketTravel"));
		
		int startpagePT=((pagePT-1)/limitPT)*limitPT+1;
		int endpagePT=startpagePT+limitPT-1;
		if(endpagePT>maxpagePT)
			endpagePT=maxpagePT;
		
		travel = travel_InfoDAO.loadTravelInfo_main(user_id,pagePT,limitPT);// ��ٱ��Ͽ� ����� �ִ� ������  ���� 
	
		request.setAttribute("pagePocketTravel", pagePT); //���� ���� ������ ��
		request.setAttribute("maxpagePocketTravel", maxpagePT); //���� �ִ� ������ ��
		request.setAttribute("startpagePocketTravel", startpagePT);//���� ���� �������� ǥ���� ù ������ ��
		request.setAttribute("endpagePocketTravel", endpagePT);//���� ���� �������� ǥ���� �� ������ ��
		request.setAttribute("listcountPocketTravel", listcountPT); //�� ���� ��
		request.setAttribute("trav_list", travel);
		
		/////////////////////////////////////////////////��ٱ��� ���� ����¡ ó��
		
		///////////////////////////////////////////////////////////////
		int pagePF = 1;
		int limitPF = 5;
		int listcountPF = travel_InfoDAO.getFestivalListCount(user_id);
		int maxPagePF = listcountPF /limitPF;
		if(listcountPF %limitPF>0)
			maxPagePF ++;
		
		if(request.getParameter("pagePocketFestival")!=null)
			pagePF = Integer.parseInt(request.getParameter("pagePocketFestival"));
		
		int startPagePF=((pagePF-1)/limitPF)*limitPF+1;
		int endPagePF=startPagePF+limitPF-1;
		if(endPagePF>maxPagePF)
			endPagePF = maxPagePF;
		festival = travel_InfoDAO.loadFestivalInfo_main(user_id,pagePF,limitPF);// ��ٱ��Ͽ� ����� �ִ� ���� ���� 
		
		
		request.setAttribute("pagePocketFestival", pagePF); //���� ���� ������ ��
		request.setAttribute("maxpagePocketFestival", maxPagePF); //���� �ִ� ������ ��
		request.setAttribute("startpagePocketFestival", startPagePF);//���� ���� �������� ǥ���� ù ������ ��
		request.setAttribute("endpagePocketFestival", endPagePF);//���� ���� �������� ǥ���� �� ������ ��
		request.setAttribute("listcountPocketFestival", listcountPF); //�� ���� ��
		request.setAttribute("fest_list", festival);
		
		
		
		
		///////////////////////////////////////////////��ٱ��� ���� ����¡ ó��
		
		
		
		
		
		
		
		
		detail = det_DAO.viewDetail_all(user_id); //������ ���õ� ���� ���� ��������
		pocket = pock_DAO.viewPocket(user_id); //��ٱ���  ��� ��������

		request.setAttribute("detail", detail);
		request.setAttribute("pocket", pocket);
			
		forward.setRedirect(false);	
	if(request.getParameter("state")!=null) {
		forward.setPath("/Schedule/Ajax_main_Schedule.jsp");
	}else if(request.getParameter("statePocketTravel")!=null){
		forward.setPath("/Schedule/Ajax_main_PocketTravel.jsp");
	}else if(request.getParameter("statePocketFestival")!=null){
		forward.setPath("/Schedule/Ajax_main_PocketFestival.jsp");
	}else{
		
		forward.setPath("/Schedule/Schedule_main.jsp");
		}
		
				
		return forward;
	}

	

}
