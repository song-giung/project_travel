package sch_comment.db;
/*
    DAO(data access object)클래스
    -데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스
    -어떤 Action 클래스가 호출되더라도 그에 해당하는 데이터베이스 연동 처리는 DAO 클래스에서 이루어지게 됩니다.
 */

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;
import javax.sql.*;

import comm_comment.db.CCBforList;
import comm_comment.db.CommCommentBean;

import java.util.*;
import javax.naming.*;


public class SchCommentDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public SchCommentDAO() {
		try {
			Context init= new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e){
			System.out.println("DB 연결 실패 : "+ e);
			return;
			
		}
	}

	//각 게시글당 총 몇개 댓글 잇는지.
	public int getSch_comt_ListCount(int sch_bo_no) {
		// TODO Auto-generated method stub
		String sql="select count(*) from sch_coment where sch_bo_no =?";
			int result=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sch_bo_no);
			rs=pstmt.executeQuery();
			if(rs.next())
				result=rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();} catch (SQLException e) {	e.printStackTrace();	}
			if(pstmt!=null)try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(con!=null)try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return result;
	}

	public List<SCBforList> getSch_CommentList(int page, int limit, int sch_bo_no) {
		// TODO Auto-generated method stub
		String board_list_sql="select * from "+ "(select rownum rnum, user_type, sch_comt_no, sch_comt_cont, user_no, sch_comt_date, sch_bo_no, user_id from "
				+ " (select * from sch_coment natural join client where sch_bo_no=? order by sch_comt_date asc)) where rnum>=? and rnum<= ?";
		
		//개수
		String countsql="select count(sch_comt_no) from "+ "(select rownum rnum, sch_comt_no, sch_comt_cont, user_no, sch_comt_date, sch_bo_no, user_id from "
				+ " (select * from sch_coment natural join client where sch_bo_no=? order by sch_comt_date asc)) where rnum>=? and rnum<= ?";

		List<SCBforList> list= new ArrayList<SCBforList>();
								  //한페이지당 10개씩 목록 1page 2   3   4
		int startrow=(page-1)* limit +1;	//시작번호	  1   11  21  31
		int endrow=startrow + limit -1;		//마지막번호 10  20  30  40
		int count=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(countsql);
			pstmt.setInt(1, sch_bo_no);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs=pstmt.executeQuery();
			rs.next();
			count=rs.getInt(1);
			
			pstmt.close();
			pstmt=con.prepareStatement(board_list_sql);
			pstmt.setInt(1, sch_bo_no);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				SCBforList b=new SCBforList();
				b.setSch_comt_no(rs.getInt("sch_comt_no"));
				b.setSch_comt_cont(rs.getString("sch_comt_cont"));
				b.setUser_no(rs.getInt("user_no"));
				b.setSch_comt_date(rs.getDate("sch_comt_date"));
				b.setSch_bo_no(rs.getInt("sch_bo_no"));
				b.setUser_id(rs.getString("user_id"));
				b.setUser_type(rs.getString("user_type"));
				b.setCount(count);
				list.add(b);
			}
			return list;
		}catch(Exception e) {
			System.out.println("getboardlist()에러 입니다."+e);
			e.printStackTrace();
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return null;
		
	}

	public boolean Sch_CommInsert(SchCommentBean boarddata) {
		// TODO Auto-generated method stub
		int num=0;
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			sql="insert into sch_coment (sch_comt_no, sch_comt_cont, user_no, sch_bo_no, sch_comt_date) "
				+" values(sch_comt_no.nextval, ?, ?, ?, sysdate)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getSch_comt_cont());
			pstmt.setInt(2, boarddata.getUser_no());
			pstmt.setInt(3, boarddata.getSch_bo_no());
			result=pstmt.executeUpdate();
			
			if(result==0)
				return false;
			return true;
			
		}catch(Exception e) {
			System.out.println("sch코멘트 insert()에러 입니다.");
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}
	public boolean Sch_ReCommInsert(SchCommentBean boarddata) {
		// TODO Auto-generated method stub
		int num=0;
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			sql="insert into sch_coment (sch_comt_no, sch_comt_cont, user_no, sch_bo_no, sch_comt_rev, sch_comt_date) "
				+" values(sch_comt_no.nextval, ?, ?, ?,?, sysdate)";
	
			pstmt.setString(1, boarddata.getSch_comt_cont());
			pstmt.setInt(2, boarddata.getUser_no());
			pstmt.setInt(3, boarddata.getSch_bo_no());
			pstmt.setInt(4, boarddata.getSch_comt_rev()); //원본댓글의 pk가 들어감
			result=pstmt.executeUpdate();
			
			if(result==0)
				return false;
				return true;
			
		}catch(Exception e) {
			System.out.println(" 일정 댓글 boardinsert()에러 입니다.");
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}
	public boolean Sch_CommentModify(SchCommentBean boarddata) {
		// TODO Auto-generated method stub
		String sql="update sch_coment set sch_comt_cont=?  where sch_comt_no=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,boarddata.getSch_comt_cont());
			pstmt.setInt(2, boarddata.getSch_comt_no());
	
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}

	public int isSch_CommWriter(int sch_comt_no) {
		// TODO Auto-generated method stub
		String sql="select user_no from sch_coment where sch_comt_no=?";
		int user_check=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sch_comt_no);
		
			rs=pstmt.executeQuery();
			if(rs.next())
				user_check=rs.getInt(1);
			
			return user_check;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}

		System.out.println("isSchwriter오류");
		return user_check;
	}

	public boolean CommDelete(int sch_comt_no) {
		// TODO Auto-generated method stub
		String sql="delete from sch_coment where sch_comt_no=? ";
		int result=0;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sch_comt_no);
			result=pstmt.executeUpdate();
			if(result==0)
				return false;
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}

	
	
	
	
	
	
}
