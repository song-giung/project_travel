package qna_board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import qna_board.db.BoardBean;
import qna_board.db.BoardDAO;



public class BoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardBean boarddata = new BoardBean();
		BoardDAO boarddao = new BoardDAO();
		request.setCharacterEncoding("utf-8");		
		String realFolder="";
		
		
		String saveFolder="boardupload";
		
		int fileSize=5*1024*1024; 
		

		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("readFolder=" + realFolder);
	    boolean result=false;
	    
	    try {
	    	MultipartRequest multi=null;
	    	multi=new MultipartRequest(request, realFolder, fileSize, "utf-8",
	    								new DefaultFileRenamePolicy());
	    	
	    
			boarddata.setUSER_ID(multi.getParameter("USER_ID"));
			boarddata.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
			boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
			

			boarddata.setBOARD_FILE(multi.getFilesystemName(
												(String)multi.getFileNames().nextElement()));
	    	
			
			result=boarddao.boardInsert(boarddata);
		
			if(result==false) {
				System.out.println("입력 실패");
				return null;
			}
			System.out.println("ㅇ");
			
	    } catch(Exception e) {
	    	e.printStackTrace();
	    } 
	    
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('문의하기 등록하였습니다.');");
		out.println("location.href='BoardList.bo';");
		out.println("</script>");
		out.close();
		return null;
	}

}
