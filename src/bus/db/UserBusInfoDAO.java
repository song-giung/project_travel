package bus.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import schedule.db.ScheduleDAO;

public class UserBusInfoDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	int result;
	
	public UserBusInfoDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e) {
			System.out.println("DB 연결되었습니다. : " + e);
			return;
		}
	}
		
	public List<UserBusInfoBean> view_bus_info(String user_id){
		
		System.out.println("시작");
		List<UserBusInfoBean> ubib = new ArrayList<UserBusInfoBean>();
		System.out.println("시작1");
		
		System.out.println("오잉");
		try {
			String sql = "select * from user_bus_info where user_no = ? ";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			ScheduleDAO sch_dao = new ScheduleDAO();
			int user_no = sch_dao.getUser_no(user_id);
			pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
			System.out.println("여기");
			while(rs.next()) {
				System.out.println("여긴가?");
				UserBusInfoBean ubib_bean = new UserBusInfoBean();
				ubib_bean.setBus_end_location(rs.getString("bus_end_location"));
				ubib_bean.setBus_no(rs.getInt("bus_no"));
				ubib_bean.setBus_start_location(rs.getString("bus_start_location"));
				ubib_bean.setBus_time_arrival(rs.getString("bus_time_arrival"));
				ubib_bean.setBus_time_cost(rs.getString("bus_time_cost"));
				ubib_bean.setBus_time_departure(rs.getString("bus_time_Departure"));
				ubib_bean.setUser_no(rs.getInt("user_no"));
				ubib.add(ubib_bean);
			
			}
					
		}catch(Exception e) {
			System.out.println("view_bus_info error : "+e);
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}
		
		return ubib;
	}
	
}
