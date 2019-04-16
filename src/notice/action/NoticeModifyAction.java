package notice.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.db.*;


public class NoticeModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		request.setCharacterEncoding("utf-8");
		NoticeDAO nd = new NoticeDAO();
		NoticeBean nb = new NoticeBean();
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		//전달 받은 파라미터 num 변수에 저장합니다.
		int num = Integer.parseInt(request.getParameter("notice_no"));

		nb.setNotice_no(num);
		nb.setAdmin_id(request.getParameter("admin_id"));
		nb.setNotice_title(request.getParameter("notice_title"));
		nb.setNotice_content(request.getParameter("notice_content"));
		
		
		///DAO에서 수정 메소드 호출하여 수정합니다.
		result = nd.noticeModify(nb);
		//수정에 실패한 경우
		if(result==false) {
			System.out.println("게시판 수정 실패");
			return null;
		}
		//수정 성공의 경우
		System.out.println("게시판 수정 완료");
		
		forward.setRedirect(true);
		//수정한 글 내용을 보여주기 위해 글 내용 보기 보기 페이지로 이동하기위해 경로를 설정
		forward.setPath("./NoticeDetailAction.no?num=" + nb.getNotice_no());
		
		return forward;
	}
}








