package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.FestivalBean;
import schedule.db.PocketBean;
import schedule.db.PocketDAO;
import schedule.db.TravelBean;
import schedule.db.Travel_InfoDAO;

public class PocketDelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		PocketDAO pocket_DAO = new PocketDAO();
		String str = request.getParameter("ch");
		String check = request.getParameter("check");
		
		String str_array[] = str.split(" ");
		Travel_InfoDAO travel_InfoDAO = new Travel_InfoDAO();
		List<PocketBean> pocket = new ArrayList<PocketBean>();

		if(check.equals("travel")) {		
			boolean result = pocket_DAO.delPocket_travel(str_array);
			if(result)
				System.out.println("delPocket_travel:��ٱ��Ͽ� �ִ� ������ ���� ���� �Ϸ�");
				
		}else if(check.equals("festival")) {
			boolean result = pocket_DAO.delPocket_festival(str_array);
			if(result)
				System.out.println("delPocket_festival:��ٱ��Ͽ� �ִ� ���� ���� ���� �Ϸ�");
		}
		
		
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		List<TravelBean> travel = new ArrayList<TravelBean>();
		List<FestivalBean>festival = new ArrayList<FestivalBean>();
		
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




///////////////////////////////////////////////��ٱ��� ���� ����
		forward.setRedirect(false);
		forward.setPath("./Schedule/AjaxPocketDel.jsp");
		
		return forward;
	}

}
