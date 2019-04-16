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

public class Travel_InfoDAO {

		DataSource ds;
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		
		public Travel_InfoDAO() {
			try {
				Context init = new InitialContext();
				ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			}catch(Exception e) {
				System.out.println("DB 연결 실패 : "+e);
			}
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

		public int getTravelListCount(String user_id) {
			int listCountPocketTravel = 0;
			int user_no = getUser_no(user_id);
			try {
				conn=ds.getConnection();
				String sql ="select count(*) from travel_information natural join pocket where user_no = ? ";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				rs=pstmt.executeQuery();
				rs.next();
				 listCountPocketTravel=rs.getInt(1);
			}catch(Exception e) {
				System.out.println("getTravelListCount error : " + e);
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
			return  listCountPocketTravel;
		}
		
		public int getFestivalListCount(String user_id) {
			int listCountPocketFestival = 0;
			int user_no = getUser_no(user_id);
			try {
				conn=ds.getConnection();
				String sql ="select count(*) from festival_information natural join pocket where user_no = ? ";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				rs=pstmt.executeQuery();
				rs.next();
				 listCountPocketFestival=rs.getInt(1);
			}catch(Exception e) {
				System.out.println("getFestivalListCount error : " + e);
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
			return  listCountPocketFestival;
		}

		//장바구니에 있는 여행 및 축제 정보 보내기 order by travel_no desc
		public List<TravelBean> loadTravelInfo(String user_id){
			List<TravelBean> trav_List = new ArrayList<TravelBean>();
			List<TravelBean> trav_List2 = new ArrayList<TravelBean>();
			int user_no = getUser_no(user_id);
			try {
				conn=ds.getConnection();
				String sql = "";
				sql = "select travel_no from pocket where user_no = ?  order by travel_no desc" ;
				pstmt =conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					TravelBean trav_Bean = new TravelBean();
					if(rs.getInt(1)!=0) {
					trav_Bean.setTravel_no(rs.getInt(1));
					trav_List.add(trav_Bean);
					}
				}
				
				rs.close();
				pstmt.close();
				
	
				sql = "select * from travel_information where travel_no = ? ";
				pstmt=conn.prepareStatement(sql);
				for(int i =0;i<trav_List.size();i++) {
					pstmt.setInt(1,trav_List.get(i).getTravel_no());
					rs=pstmt.executeQuery();
					while(rs.next()) {
						TravelBean trav_Bean2 = new TravelBean();
						trav_Bean2.setTravel_no(rs.getInt(1));
						trav_Bean2.setTravel_name(rs.getString(2));
						trav_Bean2.setTravel_content(rs.getString(3));
						trav_Bean2.setTravel_address(rs.getString(4));
						trav_Bean2.setTravel_location(rs.getString(5));
						trav_Bean2.setTravel_tema(rs.getString(6));
						trav_List2.add(trav_Bean2);
					}
					
				}
								
			}catch(Exception e) {
				System.out.println("MapDAO / loadTravelInfo error : "+e);
			}finally {
				if(rs!=null) {try{rs.close();}catch(SQLException e) {}}
				if(pstmt!=null) {try{pstmt.close();}catch(SQLException e) {}}
				if(conn!=null) {try{conn.close();}catch(SQLException e) {}}
			}
			
			return trav_List2;
			
		}
		// order by festival_no desc
		
		public List<TravelBean> loadTravelInfo_main(String user_id,int page,int limit ){
			List<TravelBean> trav_List = new ArrayList<TravelBean>();
			
			int user_no = getUser_no(user_id);
			try {
				conn=ds.getConnection();
				String sql = "select * from (select rownum rnum, travel_no, travel_name, travel_content, travel_Address, travel_location, travel_tema from "
						+" (select travel_no, travel_name, travel_content, travel_Address, travel_location, travel_tema "
						+" from travel_information natural join pocket where user_no = ? order by pocket_no)) where rnum between ? and ? ";
				
				int startrow =(page-1)*limit+1;
				int endrow=startrow+limit-1;
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					TravelBean trav_bean = new TravelBean();
					trav_bean.setTravel_address(rs.getString("travel_address"));
					trav_bean.setTravel_content(rs.getString("travel_content"));
					trav_bean.setTravel_location(rs.getString("travel_location"));
					trav_bean.setTravel_name(rs.getString("travel_name"));
					trav_bean.setTravel_no(rs.getInt("travel_no"));
					trav_bean.setTravel_tema(rs.getString("travel_tema"));
					
					trav_List.add(trav_bean);
				}
								
			}catch(Exception e) {
				System.out.println("loadTravelInfo_main error : "+e);
				e.printStackTrace();
			}finally {
				if(rs!=null) {try{rs.close();}catch(SQLException e) {}}
				if(pstmt!=null) {try{pstmt.close();}catch(SQLException e) {}}
				if(conn!=null) {try{conn.close();}catch(SQLException e) {}}
			}
			
			return trav_List;
			
		}
		
		public List<FestivalBean> loadFestivalInfo(String user_id){
			List<FestivalBean> fest_List = new ArrayList<FestivalBean>();
			List<FestivalBean> fest_List2 = new ArrayList<FestivalBean>();
			int user_no = getUser_no(user_id);
			try {
				conn=ds.getConnection();
				String sql = "";
				sql = "select festival_no from pocket where user_no = ? order by festival_no desc " ;
				pstmt =conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					FestivalBean fest_Bean = new FestivalBean();
					if(rs.getInt(1)!=0) {
					fest_Bean.setFestival_no(rs.getInt(1));
					fest_List.add(fest_Bean);
					}
				}
				
				rs.close();
				pstmt.close();
				
				sql = "select * from festival_information where festival_no = ? ";
				pstmt=conn.prepareStatement(sql);
				for(int i =0;i<fest_List.size();i++) {
					pstmt.setInt(1,fest_List.get(i).getFestival_no());
					rs=pstmt.executeQuery();
					while(rs.next()) {
						FestivalBean fest_Bean2 = new FestivalBean();
						fest_Bean2.setFestival_no(rs.getInt("festival_no"));
						fest_Bean2.setFestival_name(rs.getString("festival_name"));
						fest_Bean2.setFestival_startday(rs.getString("festival_startday"));
						fest_Bean2.setFestival_endday(rs.getString("festival_endday"));
						fest_Bean2.setFestival_location(rs.getString("festival_location"));
						fest_Bean2.setFestival_address(rs.getString("festival_address"));
						fest_Bean2.setFestival_content(rs.getString("festival_content"));
						
						fest_List2.add(fest_Bean2);
					}
					
				}
				
				
				
					
			}catch(Exception e) {
				System.out.println("MapDAO / loadFestivalInfo error : "+e);
			}finally {
				if(rs!=null) {try{rs.close();}catch(SQLException e) {}}
				if(pstmt!=null) {try{pstmt.close();}catch(SQLException e) {}}
				if(conn!=null) {try{conn.close();}catch(SQLException e) {}}
			}
			
			return fest_List2;
			
		}
		
		public List<FestivalBean> loadFestivalInfo_main(String user_id,int page,int limit ){
			List<FestivalBean> fest_List = new ArrayList<FestivalBean>();
			
			int user_no = getUser_no(user_id);
			try {
				conn=ds.getConnection();
				String sql = "select * from (select rownum rnum,  festival_no, Festival_name, Festival_startday, Festival_endday, Festival_location, Festival_address, Festival_content from "
						+" (select festival_no, Festival_name, Festival_startday, Festival_endday, Festival_location, Festival_address, Festival_content "
						+" from festival_information natural join pocket where user_no = ? order by pocket_no)) where rnum between ? and ? ";
				
				int startrow =(page-1)*limit+1;
				int endrow=startrow+limit-1;
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					FestivalBean fest_bean = new FestivalBean();
					fest_bean.setFestival_address(rs.getString("festival_address"));
					fest_bean.setFestival_content(rs.getString("festival_Content"));
					fest_bean.setFestival_endday(rs.getString("festival_endday").substring(0, 10));
					fest_bean.setFestival_location(rs.getString("festival_location"));
					fest_bean.setFestival_name(rs.getString("festival_name"));
					fest_bean.setFestival_no(rs.getInt("festival_no"));
					fest_bean.setFestival_startday(rs.getString("festival_startday").substring(0, 10));
					fest_List.add(fest_bean);
				}
								
			}catch(Exception e) {
				System.out.println("loadTravelInfo_main error : "+e);
				e.printStackTrace();
			}finally {
				if(rs!=null) {try{rs.close();}catch(SQLException e) {}}
				if(pstmt!=null) {try{pstmt.close();}catch(SQLException e) {}}
				if(conn!=null) {try{conn.close();}catch(SQLException e) {}}
			}
			
			return fest_List;
			
		}
		//생성된 일정의 여행 및 축제 정보 보내기
		public List<TravelBean> loadManagePageTravelInfo(String user_id){
			List<TravelBean> trav_List = new ArrayList<TravelBean>();
			List<TravelBean> trav_List2 = new ArrayList<TravelBean>();
			int user_no = getUser_no(user_id);
			try {
				conn=ds.getConnection();
				String sql = "";
				sql = "select travel_no from sch_detail where user_no = ?  " ;
				pstmt =conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					TravelBean trav_Bean = new TravelBean();
					if(rs.getInt(1)!=0) {
					trav_Bean.setTravel_no(rs.getInt(1));
					trav_List.add(trav_Bean);
					}
				}
				
				rs.close();
				pstmt.close();
				
	
				sql = "select * from travel_information where travel_no = ? ";
				pstmt=conn.prepareStatement(sql);
				for(int i =0;i<trav_List.size();i++) {
					pstmt.setInt(1,trav_List.get(i).getTravel_no());
					rs=pstmt.executeQuery();
					while(rs.next()) {
						TravelBean trav_Bean2 = new TravelBean();
						trav_Bean2.setTravel_no(rs.getInt(1));
						trav_Bean2.setTravel_name(rs.getString(2));
						trav_Bean2.setTravel_content(rs.getString(3));
						trav_Bean2.setTravel_address(rs.getString(4));
						trav_Bean2.setTravel_location(rs.getString(5));
						trav_Bean2.setTravel_tema(rs.getString(6));
						trav_List2.add(trav_Bean2);
					}
					
				}
								
			}catch(Exception e) {
				System.out.println("MapDAO / loadManagePageTravelInfo error : "+e);
			}finally {
				if(rs!=null) {try{rs.close();}catch(SQLException e) {}}
				if(pstmt!=null) {try{pstmt.close();}catch(SQLException e) {}}
				if(conn!=null) {try{conn.close();}catch(SQLException e) {}}
			}
			
			return trav_List2;
			
		}

		public List<FestivalBean> loadManagePageFestivalInfo(String user_id){
			List<FestivalBean> fest_List = new ArrayList<FestivalBean>();
			List<FestivalBean> fest_List2 = new ArrayList<FestivalBean>();
			int user_no = getUser_no(user_id);
			try {
				conn=ds.getConnection();
				String sql = "";
				sql = "select festival_no from sch_detail where user_no = ?  " ;
				pstmt =conn.prepareStatement(sql);
				pstmt.setInt(1, user_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					FestivalBean fest_Bean = new FestivalBean();
					if(rs.getInt(1)!=0) {
					fest_Bean.setFestival_no(rs.getInt(1));
					fest_List.add(fest_Bean);
					}
				}
				
				rs.close();
				pstmt.close();
				
				sql = "select * from festival_information where festival_no = ? ";
				pstmt=conn.prepareStatement(sql);
				for(int i =0;i<fest_List.size();i++) {
					pstmt.setInt(1,fest_List.get(i).getFestival_no());
					rs=pstmt.executeQuery();
					while(rs.next()) {
						FestivalBean fest_Bean2 = new FestivalBean();
						fest_Bean2.setFestival_no(rs.getInt("festival_no"));
						fest_Bean2.setFestival_name(rs.getString("festival_name"));
						fest_Bean2.setFestival_startday(rs.getString("festival_startday"));
						fest_Bean2.setFestival_endday(rs.getString("festival_endday"));
						fest_Bean2.setFestival_location(rs.getString("festival_location"));
						fest_Bean2.setFestival_address(rs.getString("festival_address"));
						fest_Bean2.setFestival_content(rs.getString("festival_content"));
						
						fest_List2.add(fest_Bean2);
					}
					
				}
				
				
				
					
			}catch(Exception e) {
				System.out.println("MapDAO / loadManagePageFestivalInfo error : "+e);
			}finally {
				if(rs!=null) {try{rs.close();}catch(SQLException e) {}}
				if(pstmt!=null) {try{pstmt.close();}catch(SQLException e) {}}
				if(conn!=null) {try{conn.close();}catch(SQLException e) {}}
			}
			
			return fest_List2;
			
		}
		
		//건건이 선택된 일정의 상세일정에 있는 여행 및 축제 정보 보내기
		
		public List<TravelBean> loadManagePageTravelInfoBySelect(int sch_no){
			List<TravelBean> trav_List = new ArrayList<TravelBean>();
			String sql="";
			try {
				conn=ds.getConnection();
				sql = "select travel_no , travel_name, travel_content, travel_Address, travel_location, travel_tema " +
						" from travel_information natural join sch_Detail where sch_no = ? " ;						
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, sch_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {

					TravelBean trav_bean = new TravelBean();System.out.println("4");
					trav_bean.setTravel_address(rs.getString("travel_address"));System.out.println("5");
					trav_bean.setTravel_content(rs.getString("travel_content"));System.out.println("6");
					trav_bean.setTravel_location(rs.getString("travel_location"));System.out.println("7");
					trav_bean.setTravel_name(rs.getString("travel_name"));System.out.println("8");
					trav_bean.setTravel_no(rs.getInt("travel_no"));System.out.println("9");
					trav_bean.setTravel_tema(rs.getString("travel_tema"));
					trav_List.add(trav_bean);					
				}
							
			}catch(Exception e) {
				System.out.println("loadManagePageTrevelInfoBySelect error : "+e);
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			
			return trav_List;
		}

		public List<FestivalBean> loadManagePageFestivalInfoBySelect(int sch_no){
			List<FestivalBean> fest_List = new ArrayList<FestivalBean>();
			String sql="";
			try {
				conn=ds.getConnection();
				sql = "select festival_no , festival_name, festival_startday, festival_endday, festival_location, festival_address, festival_content " +
						" from festival_information natural join sch_Detail where sch_no = ? " ;						
				pstmt=conn.prepareStatement(sql);				
				pstmt.setInt(1, sch_no);
				rs = pstmt.executeQuery();
				while(rs.next()) {
				
					FestivalBean fest_bean = new FestivalBean();
					fest_bean.setFestival_address(rs.getString("festival_address"));
					fest_bean.setFestival_content(rs.getString("festival_content"));
					fest_bean.setFestival_endday(rs.getString("festival_endday"));
					fest_bean.setFestival_location(rs.getString("festival_location"));
					fest_bean.setFestival_name(rs.getString("festival_name"));
					fest_bean.setFestival_no(rs.getInt("festival_no"));
					fest_bean.setFestival_startday(rs.getString("festival_startday"));
					
					
					fest_List.add(fest_bean);
				}
			}catch(Exception e) {
				System.out.println("loadManagePageFestivalInfoBySelect error : "+e);			
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			return fest_List;
		}
		
}
