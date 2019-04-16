package up_down.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import up_down.db.UpBean;
import up_down.db.UpDAO;

public class UpInsertDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UpBean upb=new UpBean();
		UpDAO udao =new UpDAO();
		HttpSession session=request.getSession();
		ActionForward forward=new ActionForward();
		
		
		int user_no=(int)session.getAttribute("user_no");
		int sch_bo_no=Integer.parseInt(request.getParameter("sch_bo_no"));
		
		boolean downcheck=Boolean.parseBoolean(request.getParameter("downcheck"));
		
		/*if(downcheck==false)*/
		upb.setUser_no(user_no);
		upb.setSch_bo_no(sch_bo_no);
		
		boolean up_insert_check=false;
		boolean up_delete_check=false;
		
		boolean upcheck=Boolean.parseBoolean(request.getParameter("upcheck"));
		System.out.println("UPINSERTDETAIL : UPCHECK  BEFORE INSERT/DELETE" +upcheck);
		
		if(upcheck==false) { //up취소 시킬것임
			up_delete_check=udao.UpDelete(upb);
				if(up_delete_check==false) System.out.println("updelete 실패");
				else System.out.println("updelete 성공");
			upcheck=true;	 
		}else { //up등록할것임
			up_insert_check=udao.UpInsert(upb);
				if(up_insert_check==false) System.out.println("upinsert 실패");
				else System.out.println("upinsert 성공");
			upcheck=false;
		}
		
		System.out.println("UPINSERTDETAIL : UPCHECK  AFTER INSERT/DELETE" +upcheck);
		
		
	/*	
	 * boolean upcheck=false;
		upcheck=udao.isUpWriter(upb);*/
		
		//check : 있으면 false, 없으면 true 넘어옴
		request.setAttribute("downcheck", downcheck);
		request.setAttribute("sch_bo_no", sch_bo_no);
		request.setAttribute("upcheck", upcheck);
		forward.setPath("List.updown");
		return forward;
	}

}
