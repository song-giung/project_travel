package schedule.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DetailDAO {
	
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs ;
	int result;
	
	public DetailDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
		
	}
		
	public int getDetailListCount(String user_id) {
		int result=0;
		int user_no = getUser_no(user_id);
		try {		
			String sql = "select count(*) from sch_detail where user_no = ? ";
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
			
		}catch(Exception e) {
			System.out.println("getDetailList error : "+e);
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
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
	//일정 생성하기 페이지에서 장바구니에 담아둔 정보들로 일정을 만들기
	public boolean addDetail(DetailBean det_bean,String user_id,String check) {
		
		boolean result = false;
	
		int user_no = getUser_no(user_id);
		
		try {	
			String	sql="";
			String sql2="";
	
			String title ="";
			sql="select max(sch_no) from schedule ";

			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int sch_no = rs.getInt(1); //최근에 생성된 일정의 일정번호를 가져온다
			rs.close();
			pstmt.close();
			
		
			boolean check_empty =false; //det_date가 비었는지 당일 여행인지 확인한다.
			if(det_bean.getDet_date()!=null&&det_bean.getDet_date().equals("")); 
				check_empty=true ;//date에 값이 저장되어져 있다/ 
	
			System.out.println("!");
			if(check.equals("festival")) { //당일 일정인지 기간 일정인지에 대한 쿼리문 2개
				//축제 테이블에서 제목을 가져온다.

				sql ="select festival_name from festival_information where festival_no = ? ";
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, det_bean.getFestival_no());		
					rs=pstmt.executeQuery();				
					rs.next();					
					title = rs.getString(1);
									
				rs.close();				
				pstmt.close();
				
				
				sql = "insert into sch_detail (sch_no , user_no , detail_no , festival_no , det_content, det_Start, det_end, det_date , det_title)"
								+ " values( ?, ? , 	sch_detail_seq.nextval , ? , ? , ? , ? ,? , ?)" ; //당일 여행 start /end / date /title 존재
				sql2 ="insert into sch_detail (sch_no , user_no , detail_no , festival_no , det_content, det_Start, det_end ,  det_title)"
					+ " values( ?, ? , 	sch_detail_seq.nextval , ? , ? , ? , ? , ?)" ; //기간 여행 start / end / title 존재
			
				if(check_empty) // date 값 존재하면 				
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, sch_no);
					pstmt.setInt(2, user_no);
					pstmt.setInt(3, det_bean.getFestival_no());	
					pstmt.setString(4, det_bean.getDet_content());			
					pstmt.setString(5,det_bean.getDet_start() );
					pstmt.setString(6, det_bean.getDet_end());
					pstmt.setString(7, det_bean.getDet_date());
					pstmt.setString(8, title);
					if(pstmt.executeUpdate()!=0)
						result = true;
					
				else//date 값 존재 x					
					pstmt=conn.prepareStatement(sql2);
					pstmt.setInt(1, sch_no);
					pstmt.setInt(2, user_no);				
					pstmt.setInt(3, det_bean.getFestival_no());						
					pstmt.setString(4, det_bean.getDet_content());			
					pstmt.setString(5,det_bean.getDet_start() );
					pstmt.setString(6, det_bean.getDet_end());
					pstmt.setString(7, title);
					if(pstmt.executeUpdate()!=0)
						result = true;		
			}else if(check.equals("travel")) { //당일 일정인지 기간 일정인지에 대한 쿼리문 2개
				//여행 테이블에서 제목을 가져온다.
				sql ="select travel_name from travel_information where travel_no = ? ";
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, det_bean.getTravel_no());
					System.out.println("travel_no : " + det_bean.getTravel_no());
					rs=pstmt.executeQuery();
					rs.next();
					title = rs.getString(1);
				
				rs.close();
				pstmt.close();
				
				
				
				sql = "insert into sch_detail (sch_no , user_no , detail_no , travel_no , det_content, det_Start, det_end, det_date , det_title)"
						+ " values( ?, ? , 	sch_detail_seq.nextval , ? , ? , ? , ? ,? , ?)" ; //당일 여행 start /end / date /title 존재
				sql2 ="insert into sch_detail (sch_no , user_no , detail_no , travel_no , det_content, det_Start, det_end ,  det_title)"
						+ " values( ?, ? , 	sch_detail_seq.nextval , ? , ? , ? , ? , ?)" ; //기간 여행 start / end / title 존재
	
				if(check_empty) // date 값 존재하면 				
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, sch_no);
					pstmt.setInt(2, user_no);
					pstmt.setInt(3, det_bean.getTravel_no());	
					pstmt.setString(4, det_bean.getDet_content());			
					pstmt.setString(5,det_bean.getDet_start() );
					pstmt.setString(6, det_bean.getDet_end());
					pstmt.setString(7, det_bean.getDet_date());
					pstmt.setString(8, title);
					if(pstmt.executeUpdate()!=0)
						result = true;
			
				else//date 값 존재 x					
					pstmt=conn.prepareStatement(sql2);
					pstmt.setInt(1, sch_no);
					pstmt.setInt(2, user_no);				
					pstmt.setInt(3, det_bean.getTravel_no());						
					pstmt.setString(4, det_bean.getDet_content());			
					pstmt.setString(5,det_bean.getDet_start() );
					pstmt.setString(6, det_bean.getDet_end());
					pstmt.setString(7, title);
					if(pstmt.executeUpdate()!=0)
						result = true;		
			
			
			}
			
		}catch(Exception e) {
			System.out.println("addDetail error : "+e );
		}finally {
			if(pstmt!=null) {try{pstmt.close();}catch(SQLException e) {}}
			if(conn!=null) {try{conn.close();}catch(SQLException e) {}}
		}
		
		
		return result;
	}
	
	public boolean delDetail_det_no(String det_no[]) {
			boolean result = false;
		try {
			conn=ds.getConnection();
			String sql = "delete  from sch_detail where  ";
			String sql2 =" detail_no = ? or " ;
			for(int i=0;i<det_no.length;i++) {				
				if(i==det_no.length-1) {
					sql2=" detail_no= ? ";
					
					
				}
				sql+=sql2;
			}
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<det_no.length;i++)
				pstmt.setInt(i+1,Integer.parseInt(det_no[i]));
			System.out.println(sql);
			int n =pstmt.executeUpdate();
			System.out.println("n : "+n);
			if(n!=0) {
				result = true;
			}		
			
		}catch(Exception e) {
			System.out.println("delDetail error : " + e);
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
		
	public boolean delDetail(String sch_no[]) {
		boolean result = false;
	try {
		conn=ds.getConnection();
		String sql = "delete  from sch_detail where  ";
		String sql2 =" sch_no = ? or " ;
		for(int i=0;i<sch_no.length;i++) {				
			if(i==sch_no.length-1) {
				sql2=" sch_no= ? ";
			}
			sql+=sql2;
		}
		pstmt=conn.prepareStatement(sql);
		for(int i=0;i<sch_no.length;i++)
			pstmt.setInt(i+1,Integer.parseInt(sch_no[i]));
		System.out.println(sql);
		int n =pstmt.executeUpdate();
		System.out.println("n : "+n);
		if(n!=0) {
			result = true;
		}		
		
	}catch(Exception e) {
		System.out.println("delDetail error : " + e);
	}finally {
		if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
		if(conn!=null)try {conn.close();}catch(SQLException e) {}
	}
	
	return result;
}
	
	public List<DetailBean> viewDetail(String user_id,int page,int limit){
		List<DetailBean> detail = new ArrayList<DetailBean>();	
		try {
			conn=ds.getConnection();
			ScheduleDAO sch_DAO = new ScheduleDAO();
			int user_no = sch_DAO.getUser_no(user_id); //유저 번호 가져오기
			String sql="";
			
			sql = "select * from (select rownum rnum, detail_no, user_no, sch_no, travel_no, festival_no, "
					+ " det_content, det_start, det_end, det_Date, det_title from (select detail_no, user_no, sch_no, travel_no, festival_no, det_content,"
					+ " det_start, det_end, det_date, det_title from sch_detail where user_no = ? order by detail_no desc)) where rnum between ? and ? ";
			
			int startrow = (page-1)*limit+1;
			int endrow = startrow+limit-1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				DetailBean det_bean = new DetailBean();
				det_bean.setDetail_no(rs.getInt("detail_no"));
				det_bean.setUser_no(rs.getInt("user_no"));
				det_bean.setSch_no(rs.getInt("sch_no"));
				det_bean.setTravel_no(rs.getInt("travel_no"));
				det_bean.setFestival_no(rs.getInt("festival_no"));
				det_bean.setDet_content(rs.getString("det_content"));
				if(rs.getString("det_start")!=null)
					det_bean.setDet_start(rs.getString("det_start").substring(0, 10));
				if(rs.getString("det_end")!=null)
					det_bean.setDet_end(rs.getString("det_End").substring(0, 10));
				if(rs.getString("det_date")!=null)
					det_bean.setDet_date(rs.getString("det_date").substring(0, 10));
				det_bean.setDet_title(rs.getString("det_title"));
				detail.add(det_bean);				
			}	
		}catch(Exception e) {
			System.out.println("viewDetail error : " + e);
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return detail;
	}

	public List<DetailBean> viewDetail_select(String user_id,int sch_no,int page,int limit){
		List<DetailBean> detail = new ArrayList<DetailBean>();	
		try {
			conn=ds.getConnection();
			ScheduleDAO sch_DAO = new ScheduleDAO();
			int user_no = sch_DAO.getUser_no(user_id); //유저 번호 가져오기
			String sql="";
			
			sql = "select * from (select rownum rnum, detail_no, user_no, sch_no, travel_no, festival_no, "
					+ " det_content, det_start, det_end, det_Date, det_title from (select detail_no, user_no, sch_no, travel_no, festival_no, det_content,"
					+ " det_start, det_end, det_date, det_title from sch_detail where user_no = ? and sch_no = ? order by detail_no desc)) where rnum between ? and ? ";
			System.out.println(sql);
			int startrow = (page-1)*limit+1;
			int endrow = startrow+limit-1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, sch_no);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				DetailBean det_bean = new DetailBean();
				det_bean.setDetail_no(rs.getInt("detail_no"));
				det_bean.setUser_no(rs.getInt("user_no"));
				det_bean.setSch_no(rs.getInt("sch_no"));
				det_bean.setTravel_no(rs.getInt("travel_no"));
				det_bean.setFestival_no(rs.getInt("festival_no"));
				det_bean.setDet_content(rs.getString("det_content"));
				if(rs.getString("det_start")!=null)
					det_bean.setDet_start(rs.getString("det_start").substring(0, 10));
				if(rs.getString("det_end")!=null)
					det_bean.setDet_end(rs.getString("det_End").substring(0, 10));
				if(rs.getString("det_date")!=null)
					det_bean.setDet_date(rs.getString("det_date").substring(0, 10));
				det_bean.setDet_title(rs.getString("det_title"));
				detail.add(det_bean);				
			}	
		}catch(Exception e) {
			System.out.println("viewDetail error : " + e);
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return detail;
	}
	
	//order by sch_no desc
	public List<DetailBean> viewDetail_all(String user_id){
		List<DetailBean> detail = new ArrayList<DetailBean>();	
		try {
			conn=ds.getConnection();
			ScheduleDAO sch_DAO = new ScheduleDAO();
			int user_no = sch_DAO.getUser_no(user_id); //유저 번호 가져오기
			String sql="";
			
			sql = "select * from sch_Detail where user_no = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				DetailBean det_bean = new DetailBean();
				det_bean.setDetail_no(rs.getInt(1));
				det_bean.setUser_no(rs.getInt(2));
				det_bean.setSch_no(rs.getInt(3));
				det_bean.setTravel_no(rs.getInt(4));
				det_bean.setFestival_no(rs.getInt(5));
				det_bean.setDet_content(rs.getString(6));
				if(rs.getString(7)!=null)
					det_bean.setDet_start(rs.getString(7).substring(0, 10));
				if(rs.getString(8)!=null)
					det_bean.setDet_end(rs.getString(8).substring(0, 10));
				if(rs.getString(9)!=null)
					det_bean.setDet_date(rs.getString(9).substring(0, 10));
				det_bean.setDet_title(rs.getString(10));
				detail.add(det_bean);				
			}	
		}catch(Exception e) {
			System.out.println("viewDetail_all error : " + e);
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return detail;
	}
	//order by sch_no desc
	
	public List<DetailBean> viewDetail_ManagePage(int sch_no) {
		List<DetailBean> detail = new ArrayList<DetailBean>();	
		try {
			conn=ds.getConnection();
			
		
			String sql="";
			
			sql = "select * from sch_detail where sch_no = ? order by sch_no desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sch_no);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				DetailBean det_bean = new DetailBean();
				det_bean.setDetail_no(rs.getInt(1));
				det_bean.setUser_no(rs.getInt(2));
				det_bean.setSch_no(rs.getInt(3));
				det_bean.setTravel_no(rs.getInt(4));
				det_bean.setFestival_no(rs.getInt(5));
				det_bean.setDet_content(rs.getString(6));
				if(rs.getString(7)!=null)
					det_bean.setDet_start(rs.getString(7).substring(0, 10));
				if(rs.getString(8)!=null)
					det_bean.setDet_end(rs.getString(8).substring(0, 10));
				if(rs.getString(9)!=null)
					det_bean.setDet_date(rs.getString(9).substring(0, 10));
				det_bean.setDet_title(rs.getString(10));
				detail.add(det_bean);				
			}	
		}catch(Exception e) {
			System.out.println("viewDetail error : " + e);
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return detail;
		
		
	}
	
	
}
