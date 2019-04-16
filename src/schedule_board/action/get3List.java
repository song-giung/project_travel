package schedule_board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule_board.db.SBBforList;
import schedule_board.db.SchBoardBean;
import schedule_board.db.SchBoardDAO;

public class get3List implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		SchBoardBean cb = new SchBoardBean();
		SchBoardDAO cdao = new SchBoardDAO();
		List<SBBforList> sbb=new ArrayList<SBBforList>();
		List<SBBforList> sbb3=new ArrayList<SBBforList>();
		ActionForward forward=new ActionForward();
		
		String[] types2=null; //ajax로 배열받기;
	
		
		if(request.getParameter("state")!=null){//ajax실행 할 때
			types2=request.getParameterValues("type[]"); //ajax 로 배열 받기
			session.setAttribute("types", types2);	
		}else if(request.getParameter("page")!=null){	//ajax실행안할때
			System.out.println("ajax실행안함~초기화면/anchor누름");
			types2=(String[]) session.getAttribute("types");
		}else
			session.removeAttribute("types");
		
		if(types2==null) {
			sbb3=cdao.getTop3();
			request.setAttribute("sbb3", sbb3);
		}else {
			sbb3=cdao.getTop3(types2);
			request.setAttribute("sbb3", sbb3);
		}
		
		forward.setPath("./ScheduleBoard/schTop3View.jsp");
		return forward;
		}
	}


