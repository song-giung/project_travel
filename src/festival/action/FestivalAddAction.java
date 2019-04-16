package festival.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import festival.db.festival;
import festival.db.festivalDAO;
import festival.db.festival_image;

public class FestivalAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		festival f = new festival();
		festival_image f_img = new festival_image();
		festivalDAO fdao = new festivalDAO();
		String realFolder = "";
		
		//WebContent아래에 꼭 폴더 생성하세요
		String saveFolder = "festivalUpload";
		int fileSize = 5*1024*1024;//업로드할 파일의 최대 사이즈입니다.
		
		//실제 저장 경로를 지정합니다.
		 ServletContext sc = request.getServletContext();
	     realFolder = sc.getRealPath(saveFolder);
	     System.out.println(realFolder);
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			int no=0;
			
			String name = multi.getParameter("name");
			String content = multi.getParameter("textarea");
			String address =multi.getParameter("add");
			String location = multi.getParameter("location");
			String startdate = multi.getParameter("startdate");
			String enddate = multi.getParameter("enddate");
			
			System.out.println(name+content+address+location+startdate+enddate);
			f.setFestival_name(name);
			f.setEndday(enddate);
			f.setStartday(startdate);
			f.setFestival_address(address);
			f.setLocation(location);
			f.setFestival_content(content);
			
			no=fdao.insertFestival(f);
			
			
			//이미지 삽입.
			String type = multi.getParameter("imgType"); //대표이미지 설정
			System.out.println("체크박스 :"+type);
			
			for(int i=1;i<5;i++) {
				String temp="test"+i;
				String fileName= multi.getFilesystemName(temp);
				if(fileName!=null) {
					if(temp.equals(type)) {
						System.out.println(fileName);
						f_img.setFestival_img_name(fileName);
						f_img.setFestival_img_type(1);
						f_img.setFestival_no(no);
						fdao.insertFestival_img(f_img);
					
					}else {
						
						f_img.setFestival_img_name(fileName);
						f_img.setFestival_img_type(0);
						f_img.setFestival_no(no);
						fdao.insertFestival_img(f_img);
					}
					
				}   
				
				
			}
			forward.setRedirect(true);
			forward.setPath("./festival_list.fs");
		  }catch(Exception e) {
			 e.printStackTrace();
	      }
	      return forward;
	}

}
