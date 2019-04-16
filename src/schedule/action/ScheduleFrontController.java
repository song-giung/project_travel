package schedule.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.s")
public class ScheduleFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		
		if(command.equals("/MySchedule.s")){
			action = new ScheduleView(); //ù��° ������ ���� �� ���� ���� ���� + ���� ���� ��������
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("ScheduleView() error : " + e);
			}
		}else if(command.equals("/pocket_del.s")) {
			action = new PocketDelAction(); // ù��° ������ ���� ���� �����ϰ� ajax�� �����ֱ�
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("PocketDelAction() error : "+e);
			}
		}else if(command.equals("/schedule_del.s")) { //ù��° ������ ����  �����ϰ� ajax�� �����ֱ�
			action = new ScheduleDelAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("ScheduleDelAction() error : "+e);
			}
		}else if(command.equals("/detail_reload.s")) {// ���� ���� ��ư ������ �� ������ �����ϱ� 
			action = new SchDetailView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("ScheduleDelAction() error : "+e);
			}
		}else if(command.equals("/detail_del.s")) {//��������ư �������� ������ �����ϰ� ajax�� �����ֱ�(���������� ���� �ҽ�)
			action = new DetailDelAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("ScheduleDelAction() error : "+e);
			}
		}else if(command.equals("/Calendar_load.s")) { // �޷� �ҷ����� AJAX
			action = new CalendarloadAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("CalendarReloadAction() error : "+e);
			}
		}else if(command.equals("/Schedule_make.s")) { // ���� ����� ȭ�� view 
			action = new ShceduleMakeView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				System.out.println("CalendarReloadAction() error : "+e);
			}
		}else if(command.equals("/Map_load.s")) { //���� ����� ��ٱ��� ��ȸ ajax
		
			action = new MapView();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("MapView() error : " + e );
			}
		}else if(command.equals("/MapManagePageLoad.s")) { //���� ����� ���� �ı� ������ ��ȸ ajax 
			action = new MapManagePageView();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("MapManagePageView() error : " + e );
			}
		}
		else if(command.equals("/make_Schedule.s")) { //���� �����ϱ� ������ ���� ���� �����ϱ�	
		
			action = new ScheduleAddAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("ScheduleAddAction() error : " + e );
			}
		}else if(command.equals("/make_detail.s")) { //���� �����ϱ� ������ ����  �� ���� �����ϱ�		
			action = new SchDetailAddAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("SchDetailAddAction() error : " + e );
			}
		}else if(command.equals("/MyScheduleManage.s")) { //���� �����ϱ� ������ view  �۾� 
			action = new ScheduleManageView();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("ScheduleManageView() error : " + e );
			}
		}else if(command.equals("/Managepage_make_review.s")) { //���� �����ϱ� ������ �ı� ������Ʈ 
			action = new ScheduleReviewUpdateAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("ScheduleReviewUpdateAction error : " + e );
			}
		}
		
	
		
		
		
		
		
		
		if(forward!=null) {
			if(forward.isRedirect())  {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
			}
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doProcess(request,response);
	}

}
