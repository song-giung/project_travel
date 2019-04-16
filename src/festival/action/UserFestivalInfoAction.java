package festival.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import festival.db.festivalDAO;
import festival.db.festival_image;
import festival.db.userFestival;
import festival.db.userFestivalDAO;

public class UserFestivalInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		int no = Integer.parseInt(request.getParameter("no"));
		int user_no = (int)session.getAttribute("user_no");
		
		userFestival uf = new userFestival();
		userFestivalDAO ufdao = new userFestivalDAO();
		festival_image f_img = new festival_image();
		
		int up_check =  ufdao.up_select(user_no, no);
		int down_check = ufdao.down_select(user_no, no);
		
		System.out.println("up_check~~"+up_check+"down_check~~~"+down_check);
		request.setAttribute("up_check", up_check);
		request.setAttribute("down_check", down_check);
		
		int u1=ufdao.up_count1(no);
		int u2=ufdao.up_count2(no);
		int u3=ufdao.up_count3(no);
		int u4=ufdao.up_count4(no);
		
		int d1=ufdao.down_count1(no);
		int d2=ufdao.down_count2(no);
		int d3=ufdao.down_count3(no);
		int d4=ufdao.down_count4(no);
		
		request.setAttribute("u1", u1);
		request.setAttribute("u2", u2);
		request.setAttribute("u3", u3);
		request.setAttribute("u4", u4);
		
		request.setAttribute("d1", d1);
		request.setAttribute("d2", d2);
		request.setAttribute("d3", d3);
		request.setAttribute("d4", d4);
		
		
		List<festival_image> f_imglist = new ArrayList<festival_image>();
		festivalDAO fdao = new festivalDAO();
		
		uf=ufdao.userFestival_info(no);
		f_imglist = fdao.getFestival_image_info(no);
		
		request.setAttribute("festival", uf);
		request.setAttribute("no", no);
		
		if(f_imglist.size()==1) {
			request.setAttribute("img", f_imglist);
		}else {
			request.setAttribute("imglist", f_imglist);
		}
		forward.setPath("/festival/userFestival_info.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
