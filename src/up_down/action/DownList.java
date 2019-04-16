package up_down.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import up_down.db.DBforList;
import up_down.db.DownBean;
import up_down.db.DownDAO;
import up_down.db.UBforList;
import up_down.db.UpBean;
import up_down.db.UpDAO;

public class DownList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UpBean upb=new UpBean();
		UpDAO udao =new UpDAO();
		UBforList uplist=new UBforList();
		
		DownBean dwnb=new DownBean();
		DownDAO ddao =new DownDAO();
		DBforList downlist=new DBforList();
		
		//두번눌렀을때
		boolean upcheck=(boolean) request.getAttribute("upcheck");
		request.setAttribute("upcheck", upcheck); //false : insert 못함->취소해야됨 , true  : insert 할수있음
		
		boolean downcheck=(boolean) request.getAttribute("downcheck");
		request.setAttribute("downcheck", downcheck);
		
		HttpSession session=request.getSession();
		ActionForward forward=new ActionForward();
		
		int sch_bo_no=(int) request.getAttribute("sch_bo_no");
		
		int up_total_count=udao.UpCount(sch_bo_no);
		int down_total_count=ddao.DownCount(sch_bo_no);
		
		
		int[] up_type_count;
		int[] down_type_count;
		
		up_type_count=new int[4];
		down_type_count=new int[4];
		
		for(int i=0; i<4; i++) {
			up_type_count[i]=udao.UpCount_byType(sch_bo_no, i+1);
			down_type_count[i]=ddao.DownCount_byType(sch_bo_no, i+1);
		}

		uplist.setSch_bo_no(sch_bo_no);
		uplist.setUp_total_count(up_total_count);
		uplist.setUp_type_count(up_type_count);
		
		downlist.setSch_bo_no(sch_bo_no);
		downlist.setDown_total_count(down_total_count);
		downlist.setDown_type_count(down_type_count);
		
		request.setAttribute("uplist", uplist);
		request.setAttribute("downlist", downlist);
		
		forward.setPath("./up_down/up_down_view.jsp");
		return forward;
	}

}
