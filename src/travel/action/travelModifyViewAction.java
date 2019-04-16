package travel.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.db.travel;
import travel.db.travelDAO;
import travel.db.travel_image;
import travel.db.travel_imgDAO;
import travel.db.travel_type;

public class travelModifyViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		travel t = new travel();
		List<travel_type> t_type = new ArrayList<travel_type>();
		List<travel_image> t_img = new ArrayList<travel_image>();
		travel_image timg = new travel_image();
		travelDAO tdao = new travelDAO();
		travel_imgDAO tdao_img = new travel_imgDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		t = tdao.getTravel_info(no);
		t_type = tdao.getTravel_type_info(no);
		t_img = tdao_img.getTravel_info(no);
		timg = tdao_img.getFirstImg(no);
		
		
		request.setAttribute("travel", t);
		request.setAttribute("t_type", t_type);
		request.setAttribute("t_img", t_img);
		
		
		if(timg.getTravel_img_name()!=null) {
		request.setAttribute("Firstimg", timg);
		}
		
		forward.setPath("/travel/travel_modify.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
