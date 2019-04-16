/*
 DAO(Data Access Object)클래스
 - 데이터 베이스와 연동하여 레코드의 추가,수정, 삭제 작업이 이루어지는 클래스입니다.
 - 어떤 Action클래스가 호출되더라도 그에 해당하는 데이터 베이스 연동처리는 DAO 클래스에서 이루어지게 됩니다.
 */

package travel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class travel_imgDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public travel_imgDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}//생성자 end

	

	
	public void travel_img_insert(travel_image t) {
		int no =0;
		try {
			con=ds.getConnection();
			String seq = "select max(travel_img_no) from travel_image ";
			
			pstmt=con.prepareStatement(seq);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				no=rs.getInt(1)+1;
			}
			
			String sql = "insert into travel_image values(?,?,?,?) ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, t.getTravel_img_name());
			pstmt.setInt(3, t.getTravel_img_type());
			pstmt.setInt(4, t.getTravel_no());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		
	}//travel_img_insert end
	
	
	
	public List<travel_image> getTravel_info(int no){
		List<travel_image> list = new ArrayList<travel_image>();
		try {
			con=ds.getConnection();
			String sql="select * from travel_image where travel_no =? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				travel_image t = new travel_image();
				t.setTravel_img_no(rs.getInt("travel_img_no"));
				t.setTravel_img_name(rs.getString("travel_img_name"));
				t.setTravel_img_type(rs.getInt("travel_img_type"));
				t.setTravel_no(rs.getInt("travel_no"));
				list.add(t);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return list;
		
	}//getTravel_info end
	
	public travel_image getFirstImg(int no) {
		travel_image t = new travel_image();
		try {
			con=ds.getConnection();
			
			String sql = "select * from travel_image where travel_no =? and travel_img_type=1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				t.setTravel_img_name(rs.getString("travel_img_name"));
				t.setTravel_img_no(rs.getInt("travel_img_no"));
				t.setTravel_img_type(rs.getInt("travel_img_type"));
				t.setTravel_no(rs.getInt("travel_no"));
			}
		}catch(Exception ee) {
			ee.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}// getFirstImg end
	
	
	public String usergetFirstImg(int no) {
		travel_image t = new travel_image();
		String fileName = null;
		try {
			con=ds.getConnection();
			
			String sql = "select * from travel_image where travel_no =? and travel_img_type=1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				t.setTravel_img_name(rs.getString("travel_img_name"));
				t.setTravel_img_no(rs.getInt("travel_img_no"));
				t.setTravel_img_type(rs.getInt("travel_img_type"));
				t.setTravel_no(rs.getInt("travel_no"));
			}
			fileName=t.getTravel_img_name();
		}catch(Exception ee) {
			ee.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}// getFirstImg end
	
	
	public int travel_img_modify(travel_image t) {
		int result =0;
		try {
			con=ds.getConnection();
			String sql = "update from travel_image set travel_img_name =?, travel_img_type=? travel_no=?  where  travel_img_no =? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, t.getTravel_img_name());
			pstmt.setInt(2, t.getTravel_img_type());
			pstmt.setInt(3, t.getTravel_no());
			pstmt.setInt(4, t.getTravel_img_no());
			
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("여행지 이미지 수정 에러 "+e.getMessage());
		}finally {
			try {
				
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return result;
	}//travel_img_modify end
	
	public void travel_img_delete(travel_image t) {
		try {
			con=ds.getConnection();
			String sql="delete from travel_image where travel_no =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, t.getTravel_no());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
	}// travel_img_delete end
	
	
	
}//class end


