package sch_comment.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm_comment.db.CCBforList;
import sch_comment.db.SCBforList;
import sch_comment.db.SchCommentDAO;


public class Sch_CommListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<SCBforList> scb = new ArrayList<SCBforList>();
		SchCommentDAO scdao = new SchCommentDAO();
		ActionForward forward=new ActionForward();
		
		int comt_count=0;
		int sch_bo_no=Integer.parseInt(request.getParameter("sch_bo_no"));
		
		//페이지처리
		int page=1;
		int limit=10;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("넘어온 일정게시판 댓글 페이지 = "+ page);
		
		scb=scdao.getSch_CommentList(page, limit, sch_bo_no);
		comt_count=scdao.getSch_comt_ListCount(sch_bo_no);
		
		int maxpage=(comt_count+limit-1)/limit;
		//start 페이지 :현재 보이는 페이지 그룹 중 처음표시될 것
		//end 페이지 : 마지막 표시될 것
		//ex) 내가보는 페이지 9면 start는 1 end는 10, 15 일땐 start 10 end 20
		int startpage=((page-1)/limit)*limit +1;
		int endpage =startpage +limit -1;
		if(endpage>maxpage) endpage=maxpage;
		int count=0;
		
		for(SCBforList a:scb) {
			count=a.getCount();
		}
		
		request.setAttribute("scb", scb);
		request.setAttribute("scb_bo_no", sch_bo_no);
		request.setAttribute("comt_count", comt_count);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("count", count);
		request.setAttribute("page", page);
		
		forward.setRedirect(false);
		forward.setPath("./sch_comment/schcomt_list_view.jsp");
		return forward;
	}

}
