package schedule.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.*;

public class ScheduleDAO {
		
		DataSource ds ;
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		
		public ScheduleDAO() {
			try {
				Context init = new InitialContext();
				ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			}catch(Exception e) {
				System.out.println("DB 연결 실패 : " + e);
				return;
			}
			
		}
		
		public int getScheduleListCount(String user_id) {
			int listCount =0;
			int user_no = getUser_no(user_id);
			try {
				String sql = "Select count(*) from schedule where user_no = ? ";
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				rs=pstmt.executeQuery();
				rs.next();
				listCount = rs.getInt(1);
				
			}catch(Exception e) {
				System.out.println("getScheduleListCount error : " + e);
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			return listCount;
		}
		
		public int getUser_no(String user_id) {
				int result = 0;
			try {
				conn=ds.getConnection();
				String sql = "";
				sql ="select user_no from client where user_id = ? ";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,user_id );
				rs=pstmt.executeQuery();
				rs.next();
				int user_no = rs.getInt(1); //rs.getInt("user_no");
				//user_no 가져오기
				result = user_no;
			}catch(Exception e) {
				System.out.println("getUser_no error : " + e);
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			return result;
		}
			
		public boolean addSchedule (ScheduleBean sch_bean,String user_id) {
			try {
				
				conn=ds.getConnection();
				String sql = "";
				
				ScheduleDAO sch_DAO =new ScheduleDAO();	
				int user_no = sch_DAO.getUser_no(user_id);
				
			
				sql="insert into Schedule (sch_no, user_no, start_date, end_date, sch_title, sch_content) "
								  + " values(schedule_seq.nextval ,?,?,?,?,?)" ;
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				pstmt.setString(2,sch_bean.getStart_date());
				pstmt.setString(3, sch_bean.getEnd_date());
				pstmt.setString(4, sch_bean.getSch_title());
				pstmt.setString(5, sch_bean.getSch_content());
				//가져온 user_no와 입력받은 자료들로 일정 db에 입력하기
				
				int n = pstmt.executeUpdate();
				if(n==0)
					return false;
			}catch(Exception e) {
				System.out.println("addSchedule() error : " + e);
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			
			
			return true;
		}//addSchedule end
		
		public boolean delSchedule(String sch_no[]) {
				boolean result = false;
			try {
				conn=ds.getConnection();
				String sql = "delete from schedule where  ";
				String sql2 = "sch_no =  ? or ";
				for(int i=0;i<sch_no.length;i++) {
					if(i==sch_no.length-1) {
						sql2=" sch_no = ? ";
					}
					sql+=sql2;
				}
				
				pstmt=conn.prepareStatement(sql);
				for(int i=0;i<sch_no.length;i++)
					pstmt.setInt(i+1, Integer.parseInt(sch_no[i]));
				int n=pstmt.executeUpdate();
				if(n!=0)
					result = true;
			}catch(Exception e) {
				System.out.println("delSchedule error : " + e);
			}finally{
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			return result;
		}

		public List<ScheduleBean> viewSchedule_main(String user_id,int page,int limit){

			List<ScheduleBean> list = new ArrayList<ScheduleBean>();
			
			try {
				conn = ds.getConnection();
				ScheduleDAO sch_DAO = new ScheduleDAO();
				int user_no = sch_DAO.getUser_no(user_id);
				String sql = "";
				sql = "select * from "
						+" (select rownum rnum, sch_no, user_no, start_Date, " 
						+" end_date, sch_title, sch_content, sch_review from "
						+" (select sch_no,user_no,start_Date,end_date,sch_Title,sch_content,sch_review from schedule where user_no = ? "
						+ " order by start_date desc)) where rnum between ? and ? ";
			
				int startrow=(page-1)*limit+1;
				int endrow = startrow+limit-1;
				
				pstmt = conn.prepareStatement(sql);				
				pstmt.setInt(1, user_no);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				rs=pstmt.executeQuery();
				
				
				while(rs.next()) {
					ScheduleBean sch_bean = new ScheduleBean();
					sch_bean.setSch_no(rs.getInt("sch_no"));
					sch_bean.setUser_no(rs.getInt("user_no"));
					sch_bean.setStart_date(rs.getString("start_date").substring(0, 10));
					sch_bean.setEnd_date(rs.getString("end_date").substring(0, 10)); //db에서 date 형태 꺼낼때 getString으로 가져올 수 있나?
					sch_bean.setSch_title(rs.getString("sch_title"));
					sch_bean.setSch_content(rs.getString("sch_content"));
					if(rs.getString("sch_review")!=null&&!rs.getString("sch_review").equals("")) //후기는 입력되지 않았을 수도 있따 null일 수도
						sch_bean.setSch_review(rs.getString("sch_review"));
					list.add(sch_bean);
				}
			
				rs.close();
			}catch(Exception e) {
				System.out.println("viewSchedule_main error : " + e);
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			
			return list;
		}
		
		public List<ScheduleBean> viewSchedule(String user_id){

			List<ScheduleBean> list = new ArrayList<ScheduleBean>();
			
			try {
				conn = ds.getConnection();
				ScheduleDAO sch_DAO = new ScheduleDAO();
				int user_no = sch_DAO.getUser_no(user_id);
				String sql = "";
				sql = "select sch_no,user_no,start_Date,end_date,sch_Title,sch_content,sch_review from schedule where user_no = ?  order by start_date desc ";
				
			
				
				pstmt = conn.prepareStatement(sql);				
				pstmt.setInt(1, user_no);
				rs=pstmt.executeQuery();
				
				
				while(rs.next()) {
					ScheduleBean sch_bean = new ScheduleBean();
					sch_bean.setSch_no(rs.getInt("sch_no"));
					sch_bean.setUser_no(rs.getInt("user_no"));
					sch_bean.setStart_date(rs.getString("start_date").substring(0, 10));
					sch_bean.setEnd_date(rs.getString("end_date").substring(0, 10)); //db에서 date 형태 꺼낼때 getString으로 가져올 수 있나?
					sch_bean.setSch_title(rs.getString("sch_title"));
					sch_bean.setSch_content(rs.getString("sch_content"));
					if(rs.getString("sch_review")!=null&&!rs.getString("sch_review").equals("")) //후기는 입력되지 않았을 수도 있따 null일 수도
						sch_bean.setSch_review(rs.getString("sch_review"));
					list.add(sch_bean);
				}
			
				rs.close();
			}catch(Exception e) {
				System.out.println("viewSchedule error : " + e);
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			
			return list;
		}
		
		public ScheduleBean viewScheduleManagePage(int sch_no){
			ScheduleBean sch_bean = new ScheduleBean();
			
			try {
				conn = ds.getConnection();
				String sql = "";
				sql = "select * from schedule where sch_no = ? order by sch_no desc ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sch_no);
				rs=pstmt.executeQuery();
			
				
				while(rs.next()) {
					
					sch_bean.setSch_no(rs.getInt(1));
					sch_bean.setUser_no(rs.getInt(2));
					sch_bean.setStart_date(rs.getString(3).substring(0, 10));
					sch_bean.setEnd_date(rs.getString(4).substring(0, 10)); //db에서 date 형태 꺼낼때 getString으로 가져올 수 있나?
					sch_bean.setSch_title(rs.getString(5));
					sch_bean.setSch_content(rs.getString(6));
					if(rs.getString(7)!="") //후기는 입력되지 않았을 수도 있따 null일 수도
						sch_bean.setSch_review(rs.getString(7));
					
				}
			
				rs.close();
			}catch(Exception e) {
				System.out.println("viewSchedule error : " + e);
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			
			return sch_bean;
		}
		
		public void updateScheduleReview(String user_id,int sch_no,String update_content) {
			
			try {
				int user_no = getUser_no(user_id);
				String sql = "";
				conn=ds.getConnection();
				sql="update schedule set sch_content=? where user_no = ? and sch_no = ? ";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, update_content);
				pstmt.setInt(2, user_no);
				pstmt.setInt(3, sch_no);
				int i = pstmt.executeUpdate();
				if(i>0)
					System.out.println("후기 작성 완료");
				
			}catch(Exception e) {
				System.out.println("updateScheduleReview error : "+ e);
				e.printStackTrace();
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			
		}
	
		public ScheduleBean viewScheduleShareBoard(String user_id,int sch_no) {
				ScheduleBean sch_Bean = new ScheduleBean();
				int user_no = getUser_no(user_id);
			try {
				conn= ds.getConnection();
				String sql = "select * from schedule where user_no = ? and sch_no = ? ";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				pstmt.setInt(2, sch_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					
					sch_Bean.setSch_no(rs.getInt("sch_no"));
					sch_Bean.setUser_no(rs.getInt("user_no"));
					sch_Bean.setStart_date(rs.getString("start_date").substring(0, 10));
					sch_Bean.setEnd_date(rs.getString("end_Date").substring(0, 10)); //db에서 date 형태 꺼낼때 getString으로 가져올 수 있나?
					sch_Bean.setSch_title(rs.getString("sch_title"));
					sch_Bean.setSch_content(rs.getString("sch_content"));
					if(rs.getString("sch_content")!="") //후기는 입력되지 않았을 수도 있따 null일 수도
						sch_Bean.setSch_review(rs.getString("sch_content"));
					
				}
				
			}catch(Exception e) {
				System.out.println("viewScheduleShareBoard error : "+e);
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			
			return sch_Bean;
		}
	
}
