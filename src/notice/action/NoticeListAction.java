package notice.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.db.NoticeBean;
import notice.db.NoticeDAO;


public class NoticeListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		
		NoticeDAO nd = new NoticeDAO();
		List<NoticeBean> noticelist = new ArrayList<NoticeBean>();
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("limit") != null) {
			limit = Integer.parseInt(session.getAttribute("limit").toString());
		}
		if(request.getParameter("limit") !=null) {
			limit = Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit", limit);
			System.out.println("limit =" + limit);
		}
		
		//총 리스트 수를 받아옵니다.
		int listcount = nd.getListCount();
		
		//리스트를 받아옵니다.
		noticelist = nd.getNoticeList(page, limit);

		/*
		 * 예를 들어 한 페이지에서 보여주는 리스트의 수가 10개인 경우
		 * 예1) DB에 저장된 총 리스트의 수가 0이면 총 페이지수는 0페이지
		 * 예2) DB에 저장된 총 리스트의 수가( 1~10)이면 총 페이지수는 1페이지
		 * 예3) DB에 저장된 총 리스트의 수가(11~20)이면 총 페이지수는 2페이지
		 * 예4) DB에 저장된 총 리스트의 수가(21~30)이면 총 페이지수는 3페이지
		 */
		int maxpage = (listcount+limit -1)/limit;
		System.out.println("총 페이지수 =" + maxpage);
		
		/*
		 * startpage : 현재 페이지 그룹에서 맨 처음에 표시될 페이지 수를 의미합니다.
		 * 				([1],[11],[21] 등...)
		 * 보여줄 페이지가 30개일 경우[1][2][3]...[30]까지 다 표시하기에는 너무
		 * 많기 때문에 보통 한 페이지에는 10페이지 정도까지 이동할 수 있게 표시합니다.
		 * 예) 페이지 그룹이 아래와 같은 경우
		 *	[1][2][3][4][5][6][7][8][9][10]
		 *	페이지그룹의 시작 페이지는 startpage에 마지막 페이지는 endpage에 구합니다.
		 *
		 *  예로 1~10페이지으 ㅣ내용을 나타낼때는 페이지 그룹은 
		 *  [1][2][3]..[10]로 표시되고
		 *  11~20페이지의 내용을 나타낼때는 페이지 그룹은
		 *  [11][12][13]..[20]까지 표시됩니다.
		 */
		int startpage = ((page-1) / 10)*10 +1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 =" + startpage);
		
		//endpage : 현재 페이지 그룹에서 보여줄 마지막 페이지 수([10], [20], [30])
		int endpage = startpage +10 -1;
		System.out.println("현재 페이지에서 보여줄 마지막 페이지 수 = " + endpage);
		
		/*
		 * 마지막 그룹의 마지막 페이지 값은 최대 페이지 값입니다.
		 * 옐 마지막 페이지 그룹이 [21]~[30]인 경우
		 * 시작페이지(startpage=21)와 마지막페이지(endpage=30)
		 * 이지만 최대 페이지(maxpage)가 25라면 [21]~[25]까지만 표시되도록 합니다. 
		 */
		if(endpage > maxpage) endpage = maxpage;
		
		request.setAttribute("page", page);	//현재 페이지수
		request.setAttribute("maxpage", maxpage);	//최대 페이지 수
		
		//현재 페이지에 표시할 첫 페이지수
		request.setAttribute("startpage", startpage);
		
		//해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("noticelist", noticelist);
		
		request.setAttribute("listcount", listcount);
		
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("limit", limit);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/Notice/notice_list.jsp");
		
		return forward;
	}

}















