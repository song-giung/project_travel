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

public class PocketDAO {
		
	DataSource ds ;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public PocketDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
		
	}
	 //order by pocket_no desc 
	public List<PocketBean> viewPocket(String user_id){
		List<PocketBean> pocket = new ArrayList<PocketBean>();
		
	
		try {
			conn=ds.getConnection();
			ScheduleDAO sch_DAO= new ScheduleDAO();
			int user_no = sch_DAO.getUser_no(user_id);
			String sql = "";
			sql = "select * from pocket where user_no = ? order by pocket_no desc ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PocketBean pock_bean = new PocketBean();
				pock_bean.setUser_no(rs.getInt(1));
				pock_bean.setPocket_no(rs.getInt(2));
				pock_bean.setTravel_no(rs.getInt(3));
				pock_bean.setFestival_no(rs.getInt(4));
				pocket.add(pock_bean);
			}
			
			rs.close();
		}catch(Exception e) {
			System.out.println("viewPocket error : " + e);
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}

		return pocket;
	}

	public boolean delPocket_travel(String travel_no[]) {
		boolean result = false;
		
		try {
			conn=ds.getConnection();
			String sql = "delete  from pocket where  ";
			String sql2 ="travel_no = ? or " ;
			for(int i=0;i<travel_no.length;i++) {				
				if(i==travel_no.length-1) {
					sql2=" travel_no = ? ";
				}
				sql+=sql2;
			}
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<travel_no.length;i++)
				pstmt.setInt(i+1,Integer.parseInt(travel_no[i]));
			
			int n =pstmt.executeUpdate();
			if(n!=0) {
				result = true;
			}		
		
		}catch(Exception e) {
			System.out.println("delPocket_travel error : " + e );	
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public boolean delPocket_festival(String festival_no[]) {
		boolean result = false;
		
		try {
			conn=ds.getConnection();
			String sql = "delete  from pocket where  ";
			String sql2 ="festival_no = ? or " ;
			for(int i=0;i<festival_no.length;i++) {				
				if(i==festival_no.length-1) {
					sql2=" festival_no = ? ";
				}
				sql+=sql2;
			}
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<festival_no.length;i++)
				pstmt.setInt(i+1,Integer.parseInt(festival_no[i]));
			
			int n =pstmt.executeUpdate();
			if(n!=0) {
				result = true;
			}		
		
		}catch(Exception e) {
			System.out.println("delPocket_festival error : " + e );	
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
}
