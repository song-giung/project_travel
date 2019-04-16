package comm_board.action;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import comm_board.db.CommBoardBean;
import comm_board.db.CommBoardDAO;

public class CommBoardInsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommBoardBean cb = new CommBoardBean();
		CommBoardDAO cdao = new CommBoardDAO();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String realFolder="";
		String saveFolder="commuboardupload";
		HttpSession session= request.getSession();
		int fileSize=5*1024*1024;
		
		//?
		ServletContext sc=request.getServletContext();
		realFolder=sc.getRealPath(saveFolder);
		
		System.out.println("realfolder="+realFolder);
		
		try {
			
			MultipartRequest multi=new MultipartRequest(request,realFolder,fileSize,"utf-8", new DefaultFileRenamePolicy());
			
			cb.setUser_no((int)session.getAttribute("user_no"));
			cb.setComm_bo_title(multi.getParameter("comb_title"));
			cb.setComm_bo_content(multi.getParameter("comb_content"));
			cb.setComm_bo_file(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			
			boolean result=false;
			result=cdao.boardInsert(cb);
			
			if(result==false) {
				System.out.println(" commu 등록 실패");
				return null;
			}
			System.out.println(" commu 등록 성공 ");
			
			ActionForward forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./ListPage.combo");
			return forward;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
	}

}
