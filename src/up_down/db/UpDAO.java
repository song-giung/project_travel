package up_down.db;
/*
    DAO(data access object)클래스
    -데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스
    -어떤 Action 클래스가 호출되더라도 그에 해당하는 데이터베이스 연동 처리는 DAO 클래스에서 이루어지게 됩니다.
 */

import java.io.PrintStream;
import java.io.PrintWriter;
import up_down.db.UpBean;
import java.sql.*;
import javax.sql.*;



import java.util.*;
import javax.naming.*;


public class UpDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public UpDAO() {
		try {
			Context init= new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e){
			System.out.println("DB 연결 실패 : "+ e);
			return;
		}
	}

	//sch_bo_no로 각 글에 count몇개있는지 카운트
	public int UpCount(int num) {
		// TODO Auto-generated method stub
		String sql="select count(*) from up where sch_bo_no=?";
			int result=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next())
				result=rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return result;
	}

	public int UpCount_byType(int num, int type) {
		// TODO Auto-generated method stub
		String sql="select count(user_no) from client a natural join up b where b.sch_bo_no=? and a.user_type=?";
			int result=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, type);
			
			rs=pstmt.executeQuery();
			if(rs.next())
				result=rs.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return result;
	}

	//up한번만누를수있게
	public boolean UpInsert(UpBean boarddata) {
		// TODO Auto-generated method stub
		int num=0;
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			sql="insert into up	(up_no, sch_bo_no, user_no) values(up_no.nextval,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, boarddata.getSch_bo_no());
			pstmt.setInt(2, boarddata.getUser_no());
			
			result=pstmt.executeUpdate();
			
			if(result==0)
				return false;
			return true;
				

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("upinsert()에러 입니다.");
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}

	public boolean isUpWriter(int sch_bo_no, int user_no) {
		// TODO Auto-generated method stub
		String sql= "select user_no from up where sch_bo_no=? and user_no=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sch_bo_no);
			pstmt.setInt(2, user_no);
			rs=pstmt.executeQuery();
			
			if(rs.next())	//테이블에 있으면 return false->delete해야됨,
				return false;
			return true; //테이블에 없으면 return true ->insrt해야됨
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}

		System.out.println("isUpwriter오류");
		return false;
	}

	    public boolean UpDelete(UpBean boarddata) {
		// TODO Auto-generated method stub
	    	
		String sql="delete from up where sch_bo_no=? and user_no=?";
		int result=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, boarddata.getSch_bo_no());
			pstmt.setInt(2, boarddata.getUser_no());
			result=pstmt.executeUpdate();
			if(result==0)
				return false;
			return true;
		}catch(Exception e) {
			System.out.println("updelete 에러");
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}

	
}
