package client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.db.*;


public class LoginProcessAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		
		String user_passwd = request.getParameter("user_passwd");
		ClientDAO cd= new ClientDAO();
		Client c = new Client();
		
		String checkmain=null;
		if(request.getParameter("mainlogin")!=null) {
			checkmain=request.getParameter("mainlogin");
		}
		
		if(user_id.equals("admin")) {
			int result = cd.adminId(user_id, user_passwd);
			System.out.println("admin ?? " + result);	
			if(result==1) {
				HttpSession session = request.getSession();
				//嚥≪뮄�젃占쎌뵥 占쎄쉐�⑨옙 
				c = cd.isNo(user_id);
				session.setAttribute("user_id", user_id);
				session.setAttribute("user_no", 0);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				//硫붿씤�뿉�꽌 �꽆�뼱�솕�뒗吏�
				if(checkmain==null) { 
					out.println("<script>");
					out.println("self.close();");
					out.println("opener.location.reload();");
					out.println("</script>");
					out.close();
				}else {
				out.println("<script>");
				out.println("location.href='./'");
				out.println("</script>");
				out.close();
				}
				
				return null;
			}else {
				String message="";
				if(result==-1)
					message = "ID가 존재하지 않습니다.";
				else
					message = "비밀번호가 일치하지 않습니다.";
			
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				if(checkmain==null) {
				out.println("<script>");
				out.println("alert('" + message + "');");
				out.println("location.href='./login.net'");
				out.println("</script>");
				out.close();
				}else {
					out.println("<script>");
					out.println("location.href='./'");
					out.println("</script>");
					out.close();
					}
				return null;
			}
		}else {	
		
			int result = cd.isId(user_id, user_passwd);
			System.out.println("client ?? " + result);
			c = cd.isNo(user_id);
			System.out.println(c.getUser_no());
			if(result==1) {
				HttpSession session = request.getSession();
				//嚥≪뮄�젃占쎌뵥 占쎄쉐�⑨옙
				session.setAttribute("user_id", user_id);
				session.setAttribute("user_no", c.getUser_no());
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				//硫붿씤�뿉�꽌 �꽆�뼱�솕�뒗吏�
				if(checkmain==null) {
					out.println("<script>");
					out.println("self.close();");
					out.println("opener.location.reload();");
					out.println("</script>");
					out.close();
				}else {
				out.println("<script>");
				out.println("location.href='./'");
				out.println("</script>");
				out.close();
				}
				return null;
			}else {
				String message="";
				if(result==-1)
					message = "ID가 존재하지않습니다.";
				else
					message = "비밀번호가 일치하지 않습니다.";
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				if(checkmain==null) {
					out.println("<script>");
					out.println("alert('" + message + "');");
					out.println("location.href='./login.net'");
					out.println("</script>");
					out.close();
					}else {
						out.println("<script>");
						out.println("location.href='./'");
						out.println("</script>");
						out.close();
						}
				return null;
			}
		}		

	}
}
