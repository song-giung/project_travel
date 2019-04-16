package comm_board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm_board.db.CBBforList;
import comm_board.db.CommBoardDAO;

public class CommBoardListAction2 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session= request.getSession();
		CommBoardDAO cdao = new CommBoardDAO();
		List<CBBforList> cblist=new ArrayList<CBBforList>();
		String[] types=null;
		String searchcontent="";
		String searchindex="";
		
		
		/*//커뮤니티임당 눌렀을때 파라미터session=reset을 값을 보내줌
		if(request.getParameter("session")!=null) {
			session.removeAttribute("searchcontent");
			session.removeAttribute("searchindex");
			session.removeAttribute("type");
		}else {*/
			if(request.getParameter("state")!=null){//ajax실행 할 때
				searchcontent=(String) session.getAttribute("searchcontent");
				searchindex=(String)session.getAttribute("searchindex");
				types=(String[])session.getAttribute("type");
			}else{	//검색 눌렀을때::검색 누르면 check value값이 들어옴
				types=request.getParameterValues("type");
				searchindex=request.getParameter("searchindex");
				searchcontent =request.getParameter("searchcontent");
				if(searchcontent==null) {
					searchcontent="";
				}
				/* type 넘겨주는  url string으로 만들거임
				 *  &type=1&type=2... 그리고 넘겨줄거임
				 */
				String url="";
				if(types!=null) {
						for(int i=0;i<types.length;i++) {
							url+="&type="+types[i];
						}
				}
				System.out.println("@@@@@url@@@"+url);
				session.setAttribute("url", url);
				
				//넘겨줌
				session.setAttribute("searchindex", searchindex);
				session.setAttribute("searchcontent", searchcontent);
				session.setAttribute("type", types);	
			}
		


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
		//검색 시 성향 체크 되어있을 시
		System.out.println("types : "+types);
		if(types==null) {
			if(searchcontent=="") {
				cblist =cdao.firstgetBoardList( page, limit);
				listcount=cdao.getListCount();
		
			}else {
				cblist =cdao.getBoardList(searchindex,searchcontent, page, limit);
				for(CBBforList a:cblist) {
					listcount=a.getListcount();
				}
			}
		}else {
			if(searchcontent!="") {
				cblist=cdao.getBoardList(searchindex,searchcontent, types, page, limit);
				for(CBBforList a:cblist) {
					listcount=a.getListcount();
				}
			}else {
				cblist =cdao.getBoardList(types, page, limit);
				for(CBBforList a:cblist) {
					listcount=a.getListcount();
				}
			}
		}
		
		System.out.println("listcount : " + listcount);
		
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
		request.setAttribute("cblist", cblist);
		
		
		ActionForward forward= new ActionForward();
		forward.setRedirect(false);
		
		System.out.println("ajax상태..state넘어왔는지 : " + request.getParameter("state"));
		
				//글 목록 페이지로 이동ㅇ하기 위한 경로 설정
				if(request.getParameter("state")!=null) {
					forward.setPath("./CommBoard/CommBoardListView2.jsp");
					System.out.println("아작스 실행 중");
				}else {
					forward.setPath("./CommBoard/CommBoardListView3.jsp");
				
				}
		return forward;

	}

}
