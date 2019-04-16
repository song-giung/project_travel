package schedule_board.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule_board.db.SBBforList;
import schedule_board.db.SchBoardBean;
import schedule_board.db.SchBoardDAO;

public class SchBoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		SchBoardBean cb = new SchBoardBean();
		SchBoardDAO cdao = new SchBoardDAO();
		List<SBBforList> sbb=new ArrayList<SBBforList>();
		List<SBBforList> sbb3=new ArrayList<SBBforList>();
		
		String showtype=null;
		String[] types2=null; //ajax로 배열받기;
		
		System.out.println("여기까진 되겠지1");
		String searchindex=null;
		String searchcontent=null;
		
		
		if(request.getParameter("state")!=null){// type으로 검색하는 ajax실행 할 때
			System.out.println("검색 눌러서 ajax받음 ");
			types2=request.getParameterValues("type[]"); //ajax 로 배열 받기
			session.setAttribute("types", types2);
			
		}else if(request.getParameter("page")!=null||request.getParameter("search")!=null){	//검색단어 입력 후 검색할때
			System.out.println("새로고침/anchor누름");
			types2=(String[]) session.getAttribute("types");
		}else if(request.getParameter("search")==null){
			session.removeAttribute("types");
			System.out.println("초기화/검색만");	
		}

		System.out.println("여기까진 되겠지2?");
		
		if(request.getParameter("searchcheck")!=null) {
			searchindex=request.getParameter("searchindex");
			searchcontent=request.getParameter("searchcontent");
		}
		
		//a눌렀을때 url처리 해주려고!
		String url="";
		
		System.out.println("types:"+types2);
		//페이징처리
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null) {	
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		
		if(session.getAttribute("limit")!=null) {
			limit=Integer.parseInt(session.getAttribute("limit").toString());
		}
		if(request.getParameter("limit")!=null) {
			limit=Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit", limit);
			System.out.println("limit = "+ limit);
		} 
		int listcount=0;
		
		System.out.println("여기까진 되겠지3?");
		
		if(types2==null) {
			if(request.getParameter("searchcheck")!=null) {
				sbb=cdao.getBoardList(searchindex, searchcontent, page, limit);
				for(SBBforList s:sbb) {
					listcount=s.getListcount();
				}
			}else{
				sbb=cdao.firstgetBoardList(page, limit);
				listcount=cdao.getListCount();
				showtype="";
			}
		}else{
			if(request.getParameter("searchcheck")!=null) {
				sbb=cdao.getBoardList(searchindex, searchcontent, types2, page, limit);
			}else {
				sbb=cdao.getBoardList(types2, page, limit);
				} 
			
			
			for(String a:types2) {
				System.out.println("^_^"+a);
			}
			showtype="";
			for(String a:types2) {
				showtype+=("성향 " + a +" ");

			}
			showtype+=" 선택하셨습니다.";
			
			
			//a눌렀을떄 url 처리
			for(int i=0;i<types2.length;i++) {
				url+="&type="+types2[i];
			}
			
			for(SBBforList s:sbb) {
				listcount=s.getListcount();
			}
		
		}
		
		System.out.println(listcount);
		
		System.out.println("url~~"+url);
		System.out.println("listcount3 : " + listcount);
		
		//한페이지가 10개일 때 db 리스트 0이면 총 0페이지, 리스트가 1~10이면 1페이지..
		int maxpage=(listcount+limit-1)/limit;
		//start 페이지 :현재 보이는 페이지 그룹 중 처음표시될 것
		//end 페이지 : 마지막 표시될 것
		//ex) 내가보는 페이지 9면 start는 1 end는 10, 15 일땐 start 10 end 20
		System.out.println("총 페이지 수 =" + maxpage);
		int startpage=((page-1)/10)*10 +1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		int endpage =startpage +10 -1;
		System.out.println("현재 페이지에 보여 줄 마지막 페이지 수 =" + endpage);
		
		if(endpage>maxpage) endpage=maxpage;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("sbb", sbb);
		request.setAttribute("showtype", showtype);
		
		session.setAttribute("url", url);

		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		
		
		if(request.getParameter("state")==null) {
			sbb3=cdao.getTop3();
			request.setAttribute("sbb3", sbb3);
			forward.setPath("./ScheduleBoard/SchBoardListView3.jsp");
		}else {
			sbb3=cdao.getTop3(types2);
			request.setAttribute("sbb3", sbb3);
			forward.setPath("./ScheduleBoard/SchBoardListView2.jsp");
			System.out.println("일정게시판 ajax");
		}
		
		if(request.getParameter("searchcheck")!=null) {
			forward.setPath("./ScheduleBoard/SchBoardListView2.jsp");
			System.out.println("검색함");
		}
			return forward;
	}

}
