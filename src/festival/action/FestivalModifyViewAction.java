package festival.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import festival.db.festival;
import festival.db.festivalDAO;
import festival.db.festival_image;
import travel.db.travel;
import travel.db.travelDAO;
import travel.db.travel_image;
import travel.db.travel_imgDAO;

public class FestivalModifyViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				ActionForward forward = new ActionForward();
				festival f = new festival();
				List<festival_image> f_img = new ArrayList<festival_image>();
				festival_image fimg = new festival_image();
				festivalDAO fdao = new festivalDAO();
				
				
			
				
				int no = Integer.parseInt(request.getParameter("no"));
				
				f = fdao.getFestival_info(no);
				f_img=fdao.getFestival_image_info(no);
				fimg = fdao.getFirstImage(no);
			
				request.setAttribute("festival", f);
				request.setAttribute("f_img", f_img);
				
				
				if(fimg.getFestival_img_name()!=null) {
				request.setAttribute("Firstimg", fimg);
				}
				forward.setPath("/festival/festival_modify.jsp");
				forward.setRedirect(false);

				return forward;
	}

}
