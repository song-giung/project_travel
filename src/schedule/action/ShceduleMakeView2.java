package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.FestivalBean;
import schedule.db.PocketDAO;
import schedule.db.TravelBean;
import schedule.db.Travel_InfoDAO;

public class ShceduleMakeView2 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		PocketDAO pock_DAO = new PocketDAO();
		Travel_InfoDAO travel_InfoDAO = new Travel_InfoDAO();
		List<TravelBean> travel = new ArrayList<TravelBean>();
		List<FestivalBean>festival = new ArrayList<FestivalBean>();
		String user_id = (String) session.getAttribute("user_id");
		////////////////////////////////////////////////
		//pt = pocket Travel
		int pagePT =1;
		int limitPT = 5;
		int listcountPT =travel_InfoDAO.getTravelListCount(user_id);//해당 회원의 생성된 일정 총 개수 
		int maxpagePT=listcountPT/limitPT;
		if(listcountPT%limitPT>0)
			maxpagePT++;

		if(request.getParameter("pagePocketTravel")!=null) 
			pagePT=Integer.parseInt(request.getParameter("pagePocketTravel"));
		
		int startpagePT=((pagePT-1)/limitPT)*limitPT+1;
		int endpagePT=startpagePT+limitPT-1;
		if(endpagePT>maxpagePT)
			endpagePT=maxpagePT;
		
		travel = travel_InfoDAO.loadTravelInfo_main(user_id,pagePT,limitPT);// 장바구니에 담겨져 있는 여행지  정보 
	
		request.setAttribute("pagePocketTravel", pagePT); //일정 현재 페이지 수
		request.setAttribute("maxpagePocketTravel", maxpagePT); //일정 최대 페이지 수
		request.setAttribute("startpagePocketTravel", startpagePT);//일정 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpagePocketTravel", endpagePT);//일정 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcountPocketTravel", listcountPT); //총 글의 수
		request.setAttribute("trav_list", travel);
		
		/////////////////////////////////////////////////장바구니 여행 페이징 처리
		
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
		festival = travel_InfoDAO.loadFestivalInfo_main(user_id,pagePF,limitPF);// 장바구니에 담겨져 있는 축제 정보 
		
		
		request.setAttribute("pagePocketFestival", pagePF); //일정 현재 페이지 수
		request.setAttribute("maxpagePocketFestival", maxPagePF); //일정 최대 페이지 수
		request.setAttribute("startpagePocketFestival", startPagePF);//일정 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpagePocketFestival", endPagePF);//일정 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcountPocketFestival", listcountPF); //총 글의 수
		request.setAttribute("fest_list", festival);
		
		
		
		
		///////////////////////////////////////////////장바구니 축제 페이징 처리
		forward.setRedirect(false);
		if(request.getParameter("statePocketTravel")!=null) {
			forward.setPath("/Schedule/Ajax_Make_PocketTravel.jsp");
		}else if(request.getParameter("statePocketFestival")!=null){
			forward.setPath("/Schedule/Ajax_Make_PocketFestival.jsp");
		}else{

		forward.setPath("/Schedule/Schedule_make.jsp");
		}
		return forward;
	}

}
