package comm_comment.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm_comment.db.CCBforList;
import comm_comment.db.CommCommentDAO;

public class C_CommListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<CCBforList> ccb = new ArrayList<CCBforList>();
		CommCommentDAO cdao = new CommCommentDAO();
		ActionForward forward=new ActionForward();
		int comt_count=0;
		int comm_bo_no=Integer.parseInt(request.getParameter("comm_bo_no"));

		//페이지 처리 
		int page=1;
		int limit=10;
		if(request.getParameter("page")!=null) {	
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		
		ccb=cdao.getC_CommentList(page, limit, comm_bo_no);
		comt_count=cdao.getC_comt_ListCount(comm_bo_no);
		
		//페이징 처리
		int maxpage=(comt_count+limit-1)/limit;
		//start 페이지 :현재 보이는 페이지 그룹 중 처음표시될 것
		//end 페이지 : 마지막 표시될 것
		//ex) 내가보는 페이지 9면 start는 1 end는 10, 15 일땐 start 10 end 20
		int startpage=((page-1)/limit)*limit +1;
		int endpage =startpage +limit -1;
		if(endpage>maxpage) endpage=maxpage;
		int count=0;
		
		for(CCBforList a:ccb) {
			count=a.getCount();
		}
		
		request.setAttribute("ccb", ccb);
		request.setAttribute("comm_bo_no", comm_bo_no);
		request.setAttribute("comt_count", comt_count);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("count", count);
		request.setAttribute("page", page);
		
		forward.setRedirect(false);
		forward.setPath("./comm_comment/comm_list_view.jsp");
		return forward;
	}

}
