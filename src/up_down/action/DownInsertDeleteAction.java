package up_down.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import up_down.db.DownBean;
import up_down.db.DownDAO;
import up_down.db.UpBean;
import up_down.db.UpDAO;

public class DownInsertDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DownBean dwnb=new DownBean();
		DownDAO ddao =new DownDAO();
		HttpSession session=request.getSession();
		ActionForward forward=new ActionForward();
		
		
		int user_no=(int)session.getAttribute("user_no");
		int sch_bo_no=Integer.parseInt(request.getParameter("sch_bo_no"));
		
		dwnb.setUser_no(user_no);
		dwnb.setSch_bo_no(sch_bo_no);
		
		boolean down_insert_check=false;
		boolean down_delete_check=false;
		
		boolean downcheck=Boolean.parseBoolean(request.getParameter("downcheck"));
		boolean upcheck=Boolean.parseBoolean(request.getParameter("upcheck"));
		
		System.out.println("DOWNINSERT BEFORE ACTIOLN: downcheck "+ downcheck);
		
		if(downcheck==false) { //down취소 시킬것임
			down_delete_check=ddao.DownDelete(dwnb);
				if(down_delete_check==false) System.out.println("downdelete 실패");
				else System.out.println("downdelete 성공");
			downcheck=true;	 
		}else { //down등록할것임
			down_insert_check=ddao.DownInsert(dwnb);
				if(down_insert_check==false) System.out.println("downinsert 실패");
				else System.out.println("downinsert 성공");
			downcheck=false;
		}
		
	/*	
	 * boolean upcheck=false;
		upcheck=udao.isUpWriter(upb);*/
		
		//check : 있으면 false, 없으면 true 넘어옴
		
		request.setAttribute("upcheck", upcheck);
		request.setAttribute("sch_bo_no", sch_bo_no);
		request.setAttribute("downcheck", downcheck);
		System.out.println("DOWNINSERT AFTER ACTIOLN: downcheck "+ downcheck);
		forward.setPath("List.updown");
		return forward;
	}

}
