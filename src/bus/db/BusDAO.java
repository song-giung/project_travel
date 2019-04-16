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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import schedule.db.ScheduleDAO;
	
public class BusDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	int result;
	
	public BusDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e) {
			System.out.println("DB 연결되었습니다. : " + e);
			return;
		}
	}

	public List<BusBean> getInfo(String bus_area) {
		BusBean bb = new BusBean();
		List<BusBean> list = new ArrayList<BusBean>();
		System.out.println(1+ bus_area);
		
		try {
			con = ds.getConnection();
			System.out.println("getConnection");			
			String sql = "select*from bus_list where bus_area=? order by bus_no";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bus_area);
			rs = pstmt.executeQuery();
			while(rs.next()) {			
				bb.setBus_area(rs.getString("bus_area"));
				bb.setBus_no(rs.getInt("bus_no"));
				bb.setBus_linename(rs.getString("bus_linename"));
				bb.setBus_start(rs.getString("bus_start"));
				bb.setBus_end(rs.getString("bus_end"));
				bb.setBus_traveltime(rs.getString("bus_traveltime"));
				bb.setBus_cost_ad(rs.getInt("bus_cost_ad"));
				bb.setBus_cost_st(rs.getInt("bus_cost_st"));
				bb.setBus_cost_ch(rs.getInt("bus_cost_ch"));
				list.add(bb);
			}			
		} catch (SQLException e) {
			System.out.println("getInfo 에러 : " + e);
		}
		finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
		}
		return list;
	}//info end
	
	public int BusInsert(BusBean bb) {
		int no=0;
		try {
			con=ds.getConnection();		
			String sql = "insert into bus_list"
				+ " values(?, bus_no_seq.nextval,?,?,?,?,?,?,?)";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bb.getBus_area());
			pstmt.setString(2, bb.getBus_linename());
			pstmt.setString(3, bb.getBus_start());
			pstmt.setString(4, bb.getBus_end());
			pstmt.setString(5, bb.getBus_traveltime());
			pstmt.setInt(6, bb.getBus_cost_ad());
			pstmt.setInt(7, bb.getBus_cost_st());
			pstmt.setInt(8, bb.getBus_cost_ch());
			pstmt.executeQuery();
			pstmt.close();
			System.out.println("list 성공");
			
			String sql2 ="select bus_no from bus_list"
			+ " where bus_area=? and bus_start=? and bus_end=?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, bb.getBus_area());
			pstmt.setString(2, bb.getBus_start());
			pstmt.setString(3, bb.getBus_end());
			rs=pstmt.executeQuery();
			System.out.println("노선 뽑기 성공");		
		
			if(rs.next()) {	no = rs.getInt(1);}
			System.out.println(no);
			
		
		}catch(Exception ex) {
			System.out.println("Insert() 에러 :  " + ex);
		}
		finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}		
		return no;
	}//insert end
	public int BusInsertSchedule(int result, BusBean bb, int[] bus_seq, String[] bus_departure, String[] bus_arrival) {
			int num=0;
		try {
			con=ds.getConnection();		
			stmt=con.createStatement();
			
			for(int i=0; i<bus_departure.length; i++) {
			String sql = "insert into bus_" +bb.getBus_area() +"_schedule"
					+ " values("+result+","+bus_seq[i]+",\'"+bus_departure[i]+"\',\'"+bus_arrival[i]+"\')";
			System.out.println(sql);			
			num=stmt.executeUpdate(sql);
			}
			System.out.println(num);
			System.out.println("schedule 성공");
		
		}catch(Exception ex) {
			System.out.println("Schdule Insert() 에러 :  " + ex);
		}
		finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}		
		return num;
	}//insert schedule end

	
	
	public int BusModify(BusBean bb) {
		result=0;
		String sql = "update bus_list set bus_area=?, bus_linename=?, "
				+ "bus_start=?, bus_end=?, bus_traveltime=?, "
				+ "bus_cost_ad=?, bus_cost_st=?, bus_cost_ch=? "
				+ "where bus_no=?";
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql);		
			pstmt.setString(1, bb.getBus_area());
			pstmt.setString(2, bb.getBus_linename());
			pstmt.setString(3, bb.getBus_start());
			pstmt.setString(4, bb.getBus_end());
			pstmt.setString(5, bb.getBus_traveltime());
			pstmt.setInt(6, bb.getBus_cost_ad());
			pstmt.setInt(7, bb.getBus_cost_st());
			pstmt.setInt(8, bb.getBus_cost_ch());
			pstmt.setInt(9, bb.getBus_no());
			result =pstmt.executeUpdate();
					
		
		}catch(Exception ex) {
			System.out.println("modify() 에러 :  " + ex);
		}
		finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}
			return result;
	}//modify end
	
	public int Busdelete(String area, int no) {
		result=0;
		
		try {
			con=ds.getConnection();
			String sql = "delete from bus_"+area+"_schedule"
					+ " where bus_no="+no;
			
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("schedule 삭제 성공");
			stmt.close();
			
			String sql2= "delete from bus_list where bus_no=?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, no);
			result =pstmt.executeUpdate();
			System.out.println("list 노선 삭제 성공");
					
		}catch(Exception ex) {
			System.out.println("delete() 에러 :  " + ex);
		}
		finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}	
			return result;
	}//delete end	

	public JSONArray select_start(String area) {
		JSONArray array = new JSONArray();
		try{
			con = ds.getConnection();
		
			String sql ="select distinct bus_start from bus_list where bus_area=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, area);
					rs=pstmt.executeQuery();

			//JSON 형태로 데이터를 만듭니다.		
			while(rs.next()){
				JSONObject object = new JSONObject();
				object.put("bus_start", rs.getString(1));
				array.add(object);
			}			
			
		}catch(Exception e){
			System.out.println("select_start 에러() : " + e);
		}
		finally{
			if(pstmt!=null)try{pstmt.close();}catch(Exception ex){}
			if(rs!=null)try{rs.close();}catch(Exception ex){}
			if(con!=null)try{con.close();}catch(Exception ex){}
		}
		
		return array;
	}//select_start end

	public JSONArray select_end(String area, String start) {
		JSONArray array = new JSONArray();
		try{
			con = ds.getConnection();
		
			String sql ="select bus_end from bus_list where bus_area=? and bus_start=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, area);
					pstmt.setString(2, start);
					rs=pstmt.executeQuery();

			while(rs.next()){
				JSONObject object = new JSONObject();
				object.put("bus_end", rs.getString(1));
				array.add(object);
			}
		}catch(Exception e){
			System.out.println("select_end 에러() : " + e);
		}
		finally{
			if(pstmt!=null)try{pstmt.close();}catch(Exception ex){}
			if(rs!=null)try{rs.close();}catch(Exception ex){}
			if(con!=null)try{con.close();}catch(Exception ex){}
		}
		return array;
	}//select end end

	public JSONArray select_no(String area, String start, String end) {
		JSONArray array = new JSONArray();
		try{
			con = ds.getConnection();
		
			String sql ="select bus_no from bus_list where bus_area=? and bus_start=? and bus_end=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, area);
					pstmt.setString(2, start);
					pstmt.setString(3, end);
				rs=pstmt.executeQuery();

			while(rs.next()){
				JSONObject object = new JSONObject();
				object.put("bus_no", rs.getInt(1));
				array.add(object);
			}
		}catch(Exception e){
			System.out.println("select_no 에러() : " + e);
		}
		finally{
			if(pstmt!=null)try{pstmt.close();}catch(Exception ex){}
			if(rs!=null)try{rs.close();}catch(Exception ex){}
			if(con!=null)try{con.close();}catch(Exception ex){}
		}
		return array;
	}//select no end

	public JSONArray search(String area, int no) {
		JSONArray array = new JSONArray();
		try{
			con = ds.getConnection();

			String sql ="select * from bus_list natural join bus_" 
						+area+"_schedule where bus_no=" + no +" order by bus_seq";
			System.out.println(sql);
			stmt=con.createStatement();
			rs = stmt.executeQuery(sql);

				while(rs.next()){
				JSONObject object = new JSONObject();
				object.put("bus_no", rs.getInt(1));		
				object.put("bus_area", rs.getString(2));
				object.put("bus_linename", rs.getString(3));
				object.put("bus_start", rs.getString(4));
				object.put("bus_end", rs.getString(5));
				object.put("bus_traveltime", rs.getString(6));
				object.put("bus_cost_ad", rs.getInt(7));
				object.put("bus_cost_st", rs.getInt(8));
				object.put("bus_cost_ch", rs.getInt(9));
				object.put("bus_seq", rs.getInt(10));
				object.put("bus_departure", rs.getString(11));
				object.put("bus_arrival", rs.getString(12));
				array.add(object);
			} 

		}catch(Exception e){
			System.out.println("search 에러() : " +e);
		}
		finally{
			if(stmt!=null)try{stmt.close();}catch(Exception ex){}
			if(rs!=null)try{rs.close();}catch(Exception ex){}
			if(con!=null)try{con.close();}catch(Exception ex){}
		}
		return array;
	}//search end

	public BusBean bus_info(String bus_area, int bus_no) {
		BusBean bb = new BusBean();
		try {
			con = ds.getConnection();
			String sql ="select * from bus_list natural join bus_" 
					+bus_area+"_schedule where bus_no=" + bus_no;
		System.out.println(sql);
		stmt=con.createStatement();
		rs = stmt.executeQuery(sql);

			while(rs.next()){
			bb.setBus_no(rs.getInt("bus_no"));
			bb.setBus_area(rs.getString("bus_area"));
			bb.setBus_linename(rs.getString("bus_linename"));
			bb.setBus_start(rs.getString("bus_start"));
			bb.setBus_end(rs.getString("bus_end"));
			bb.setBus_traveltime(rs.getString("bus_traveltime"));
			bb.setBus_cost_ad(rs.getInt("bus_cost_ad"));
			bb.setBus_cost_st(rs.getInt("bus_cost_st"));
			bb.setBus_cost_ch(rs.getInt("bus_cost_ch"));
			bb.setBus_seq(rs.getInt("bus_seq"));
			bb.setBus_departure(rs.getString("bus_departure"));
			bb.setBus_arrival(rs.getString("bus_arrival"));

		} 	
		}catch(Exception e) {
			System.out.println("bus_info 에러() : " + e);
		}
		finally {
			try {if(rs !=null) rs.close();}catch(Exception e) {e.printStackTrace();}
			try {if(stmt !=null) stmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(con!=null) con.close();} catch (SQLException sqle) {sqle.printStackTrace();
			}
		}	
		return bb;
	}//bus_info end

	public List<BusBean> scehdulelist(String bus_area, int bus_no) {
		List<BusBean> list = new ArrayList();
		
		try {
			con = ds.getConnection();
			String sql = "select bus_seq, bus_departure, bus_arrival"
						+" from bus_" + bus_area + "_schedule where bus_no=" + bus_no + " order by bus_seq";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);		
			while(rs.next()) {
				BusBean bb = new BusBean();
				bb.setBus_seq(rs.getInt("bus_seq"));
				bb.setBus_departure(rs.getString("bus_departure"));
				bb.setBus_arrival(rs.getString("bus_arrival"));
				list.add(bb);
			}
		}catch(Exception e) {
			System.out.println("scehdulelist 에러() : " + e);
		}
		finally {
			try {if(rs !=null) rs.close();}catch(Exception e) {e.printStackTrace();}
			try {if(stmt !=null) stmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(con!=null) con.close();} catch (SQLException sqle) {sqle.printStackTrace();
			}
		}
		return list;
	}//schedulelist end

	public int BusModifySchedule(int bus_no, String bus_area, int[] bus_seq, String[] bus_departure, String[] bus_arrival) {
		int num=0;
		try {
			con=ds.getConnection();		
			stmt=con.createStatement();
			System.out.println(bus_no);
			String sql = "delete from bus_"+ bus_area
						+ "_schedule where bus_no=" + bus_no;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("스케줄 삭제 성공");
			
			stmt=con.createStatement();
			for(int i=0; i<bus_departure.length; i++) {
			String sql2 = "insert into bus_" + bus_area +"_schedule "
					+ "values(" + bus_no + "," + bus_seq[i] + ",\'"
					+ bus_departure[i] + "\',\'" + bus_arrival[i] + "\')";
			System.out.println(sql2);			
			num=stmt.executeUpdate(sql2);
			}
			System.out.println(num);
			System.out.println("schedule 성공");
		
		}catch(Exception ex) {
			System.out.println("BusModifySchedule() 에러 :  " + ex);
		}
		finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}		
		return num;
	}

	public BusBean addSchedule(String user_id,String area, int no, int seq) {
		BusBean bb = new BusBean();
		UserBusInfoBean UBIB = new UserBusInfoBean();
		
		ScheduleDAO sch_DAO = new ScheduleDAO();
		int user_no = sch_DAO.getUser_no(user_id);
		try {
			con = ds.getConnection();
			String sql = "select*from bus_list natural join bus_" + area + "_schedule where bus_no=" + no + " and bus_seq=" + seq;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("시간표 출력 성공");
			
			while(rs.next()) {
				UBIB.setUser_no(user_no);
					System.out.println("user_no : " + user_no);
				UBIB.setBus_no(rs.getInt("bus_no")); //버스 번호	
					System.out.println("bus_no : " + rs.getInt("bus_no"));
				UBIB.setBus_start_location(rs.getString("bus_start")); //버스 출발지
					System.out.println("버스 출발지 : "+rs.getString("bus_start"));
				UBIB.setBus_end_location(rs.getString("bus_end")); //버스 도착지
					System.out.println("버스 도착지 : "+rs.getString("bus_end"));
				UBIB.setBus_time_cost(rs.getString("bus_traveltime")); //소요 시간
					System.out.println("버스 소요 시간 : "+rs.getString("bus_traveltime"));
				UBIB.setBus_time_departure(rs.getString("bus_departure")); //버스 출발 시간
					System.out.println("버스 출발 시간 : "+rs.getString("bus_departure"));
				UBIB.setBus_time_arrival(rs.getString("bus_arrival")); //버스 도착 시간
					System.out.println("버스 도착 시간 : "+rs.getString("bus_arrival"));
			}
			rs.close();
			stmt.close();
			String sql1="insert into user_bus_info values(?, ?, ?, ?, ?, ?,? ) ";
			pstmt=con.prepareStatement(sql1);
			pstmt.setInt(1, UBIB.getUser_no());
			pstmt.setInt(2,	UBIB.getBus_no());
			pstmt.setString(3, UBIB.getBus_start_location());
			pstmt.setString(4, UBIB.getBus_end_location());
			pstmt.setString(5, UBIB.getBus_time_cost());
			pstmt.setString(6, UBIB.getBus_time_departure());
			pstmt.setString(7, UBIB.getBus_time_arrival());
			pstmt.executeUpdate();
			
			
			
		}catch(Exception e) {
			System.out.println("scehdulelist 에러() : " + e);
		}
		finally {
			try {if(rs !=null) rs.close();}catch(Exception e) {e.printStackTrace();}
			try {if(stmt !=null) stmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(con!=null) con.close();} catch (SQLException sqle) {sqle.printStackTrace();
			}
		}
		return bb;
	}//addSchedule end

	
}
