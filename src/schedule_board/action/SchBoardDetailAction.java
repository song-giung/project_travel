package schedule_board.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sch_comment.db.SCBforList;
import sch_comment.db.SchCommentDAO;
import schedule.db.DetailBean;
import schedule.db.DetailDAO;
import schedule.db.FestivalBean;
import schedule.db.ScheduleBean;
import schedule.db.ScheduleDAO;
import schedule.db.TravelBean;
import schedule.db.Travel_InfoDAO;
import schedule_board.db.SBBforList;
import schedule_board.db.SchBoardDAO;
import up_down.db.DBforList;
import up_down.db.DownBean;
import up_down.db.DownDAO;
import up_down.db.UBforList;
import up_down.db.UpBean;
import up_down.db.UpDAO;

public class SchBoardDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		SBBforList sb = new SBBforList();
		SchBoardDAO sbdao = new SchBoardDAO();
		HttpSession session=request.getSession();
		SchCommentDAO scdao=new SchCommentDAO();	//댓글 개수 보여주기 위해
		List<SCBforList> scb = new ArrayList<SCBforList>();//댓글 리스트 가져오기위해
		response.setCharacterEncoding("utf-8");
		UpBean upb=new UpBean();
		UpDAO udao =new UpDAO();
		UBforList uplist=new UBforList();
		PrintWriter out = response.getWriter();
		DownBean downb=new DownBean();
		DownDAO ddao =new DownDAO();
		DBforList downlist=new DBforList();
		
		ActionForward forward = new ActionForward();
		
		int comt_count=0;
		
		int sch_bo_no = Integer.parseInt(request.getParameter("num"));
		
		int user_no=0;
		
		if(session.getAttribute("user_id").equals("admin")){
			user_no=0;
		}else if(session.getAttribute("user_no")==null) {
			
			out.println("<script>");
			out.println("window.open('./login.net','login','width=500, height=470, top=250, left=750, fullscreen=no, resizable=no, scrollbar=no');");
			out.println("</script>");
			out.close();
		}else {
			user_no=(int) session.getAttribute("user_no");
		}
		sb=sbdao.getDetail(sch_bo_no);
		
		/*-------------------일정 가져오기-----------------*/
		int sch_no=sb.getSch_no();
		ScheduleDAO sch_DAO = new ScheduleDAO();
		DetailDAO det_DAO = new DetailDAO();
		Travel_InfoDAO travel_infoDAO = new Travel_InfoDAO();
		ScheduleBean sch_bean = new ScheduleBean();
		List<DetailBean> det_List = new ArrayList<DetailBean>();
		List<TravelBean> trav_List = new ArrayList<TravelBean>();
		List<FestivalBean> fest_List = new ArrayList<FestivalBean>();
		String user_id = (String) session.getAttribute("user_id");
		
		sch_bean = sch_DAO.viewScheduleShareBoard(user_id, sch_no); //회원 아이디 그 회원이 가지고 있는 일정중 번호로 한개 가져옴
		det_List= det_DAO.viewDetail_ManagePage(sch_no); // 그 일정 번호에 담긴 상세일정들 가져옴
		trav_List=travel_infoDAO.loadManagePageTravelInfoBySelect(sch_no);
		fest_List=travel_infoDAO.loadManagePageFestivalInfoBySelect(sch_no);
		
		request.setAttribute("sch_bean", sch_bean);
		request.setAttribute("List_detBean", det_List);
		request.setAttribute("trav_List", trav_List);
		request.setAttribute("fest_List", fest_List);
		/*-------------------댓글----------------------*/
		comt_count=scdao.getSch_comt_ListCount(sch_bo_no); //각 게시글 번호 당 댓글 개수 함수
		
		/*댓글 페이징 처리*/
		int page=1;
		int limit=10;
		if(request.getParameter("page")!=null) {	
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		scb=scdao.getSch_CommentList(page, limit, sch_bo_no);//댓글 리스트 가져옴
		
		if(sb==null) {
			System.out.println("상세보기 실패");
		}else
			System.out.println("성공");
		
		//댓글 페이징 처리
		int maxpage=(comt_count+limit-1)/limit;
		int startpage=((page-1)/10)*10 +1;
		int endpage =startpage +10 -1;
		if(endpage>maxpage) endpage=maxpage;
		
		
		
		
		//up list 가져옴
		
		
		int[] up_type_count;
		up_type_count=new int[4];
		int up_total_count=udao.UpCount(sch_bo_no);
		
		for(int i=0; i<4; i++) {
			up_type_count[i]=udao.UpCount_byType(sch_bo_no, i+1);
		}
		
		//up 했었는지 확인
		
	
		boolean upcheck=false;
		upcheck=udao.isUpWriter(sch_bo_no,user_no);
		System.out.println("SCHBOARDDETAIL ACTION UPCHECK" + upcheck);
		request.setAttribute("upcheck",upcheck);
		

		//down list가져옴
		int[] down_type_count;
		down_type_count=new int[4];
		int down_total_count=ddao.DownCount(sch_bo_no);
		
		for(int i=0; i<4; i++) {
			down_type_count[i]=ddao.DownCount_byType(sch_bo_no, i+1);
		}
		
		//down 했었는지 확인
			
			boolean downcheck=false;
			downcheck=ddao.isDownWriter(sch_bo_no,user_no);

			System.out.println("SCHBOARDDETAIL ACTIO N : downcheck :: "+ downcheck);	
			request.setAttribute("downcheck",downcheck);
			
		//up 보내줌
		uplist.setSch_bo_no(sch_bo_no);
		uplist.setUp_total_count(up_total_count);
		uplist.setUp_type_count(up_type_count);
		request.setAttribute("uplist", uplist);

		//down 보내줌
		downlist.setSch_bo_no(sch_bo_no);
		downlist.setDown_total_count(down_total_count);
		downlist.setDown_type_count(down_type_count);
		request.setAttribute("downlist", downlist);
		
		//다른 값들 보내줌
		
		request.setAttribute("scb", scb); //댓글 리스트
		request.setAttribute("sch_bo_no", sch_bo_no); //일정 게시글 pk
		request.setAttribute("comt_count", comt_count); //댓글 개수
		request.setAttribute("startpage", startpage); //시작페이지
		request.setAttribute("endpage", endpage);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("page", page);
		
		request.setAttribute("sbdata", sb);
		request.setAttribute("sch_comt_count", comt_count);
		
		forward.setPath("/ScheduleBoard/SchBoardDetailView.jsp");
		
		return forward;
	}

}
