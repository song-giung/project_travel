package travel.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travel.db.travel_down;
import travel.db.travel_image;
import travel.db.travel_imgDAO;
import travel.db.travel_up;
import travel.db.userTravelDAO;
import travel.db.userTravelList;

public class userTravelInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		HttpSession session=request.getSession();
		int no = Integer.parseInt(request.getParameter("no"));
		int user_no = (int) session.getAttribute("user_no");
		userTravelList ut = new userTravelList();
		userTravelDAO utdao = new userTravelDAO();
		
		travel_up tup = new travel_up();
		travel_down tdown = new travel_down();
		
		
		int up_check = utdao.up_select(user_no, no);
		int down_check = utdao.down_select(user_no, no);
		
		System.out.println("up_check~~"+up_check+"down_check~~~"+down_check);
		request.setAttribute("up_check", up_check);
		request.setAttribute("down_check", down_check);
		
		int u1=utdao.up_count1(no);
		int u2=utdao.up_count2(no);
		int u3=utdao.up_count3(no);
		int u4=utdao.up_count4(no);
		
		int d1=utdao.down_count1(no);
		int d2=utdao.down_count2(no);
		int d3=utdao.down_count3(no);
		int d4=utdao.down_count4(no);
		
		request.setAttribute("u1", u1);
		request.setAttribute("u2", u2);
		request.setAttribute("u3", u3);
		request.setAttribute("u4", u4);
		
		request.setAttribute("d1", d1);
		request.setAttribute("d2", d2);
		request.setAttribute("d3", d3);
		request.setAttribute("d4", d4);
		
		
		
		travel_image t_img = new travel_image();
		List<travel_image> imglist  = new ArrayList<travel_image>();
		travel_imgDAO t_imgdao= new travel_imgDAO();
		
		
		ut=utdao.getuserTravel_info(no);
		
		t_img = t_imgdao.getFirstImg(no);
		imglist=t_imgdao.getTravel_info(no);
		
		request.setAttribute("travel", ut);
		request.setAttribute("firstImage",t_img);
		
		if(imglist.size()==1) {
			request.setAttribute("img", imglist);
		}else {
		request.setAttribute("imglist", imglist);
		}
		
		
		forward.setPath("/travel/userTravel_info.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
