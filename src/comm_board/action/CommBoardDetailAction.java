package comm_board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm_board.db.CBBforList;
import comm_board.db.CommBoardDAO;
import comm_comment.db.CCBforList;
import comm_comment.db.CommCommentDAO;

public class CommBoardDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CBBforList cb = new CBBforList();
		CommBoardDAO cdao = new CommBoardDAO();
		CommCommentDAO ccdao=new CommCommentDAO();	//댓글 개수 보여주기 위해
		List<CCBforList> ccb = new ArrayList<CCBforList>();//댓글 리스트 가져오기위해
		
		ActionForward forward = new ActionForward();
		int c_comt_count=0;
		int comm_bo_no = Integer.parseInt(request.getParameter("num"));
		
		cb=cdao.getDetail(comm_bo_no);
		
		c_comt_count=ccdao.getC_comt_ListCount(comm_bo_no); //각 게시글 번호 당 댓글 개수 함수
		String shownum=request.getParameter("shownum");
		System.out.println(shownum);
		/*댓글 페이징 처리*/
		int page=1;
		int limit=10;
		if(request.getParameter("page")!=null) {	
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		ccb=ccdao.getC_CommentList(page, limit, comm_bo_no);
		
		if(cb==null) {
			System.out.println("상세보기 실패");
		}else
			System.out.println("성공");
		
		//댓글 페이징 처리
		int maxpage=(c_comt_count+limit-1)/limit;
		int startpage=((page-1)/10)*10 +1;
		int endpage =startpage +10 -1;
		if(endpage>maxpage) endpage=maxpage;
		
		request.setAttribute("ccb", ccb);
		request.setAttribute("comm_bo_no", comm_bo_no);
		request.setAttribute("comt_count", c_comt_count);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("page", page);
		request.setAttribute("cbdata", cb);
		request.setAttribute("c_comt_count", c_comt_count);
		request.setAttribute("shownum", shownum);
		forward.setPath("/CommBoard/CommBoardDetailView.jsp");
		
		return forward;
	}

}
