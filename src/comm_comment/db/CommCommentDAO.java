package comm_comment.db;


import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;
import javax.sql.*;



import java.util.*;
import javax.naming.*;


public class CommCommentDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public CommCommentDAO() {
		try {
			Context init= new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e){
			System.out.println("DB 연결 실패 : "+ e);
			return;
		}
	}
	
	//필요한가?.. 
	public int getC_comt_ListCount(int comm_bo_no) {
		// TODO Auto-generated method stub
		String sql="select count(*) from comm_coment where comm_bo_no=?";
			int result=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comm_bo_no);
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

	//한 게시글에 있는 댓글들 list 뽑아옴/ 게시글/시간순(최근이 위)
	public List<CCBforList> getC_CommentList(int page, int limit,int comm_bo_no) {
		// TODO Auto-generated method stub
		String board_list_sql="select * from "+ "(select rownum rnum, user_type, c_comt_no, c_comt_cont, user_no, c_comt_date, comm_bo_no, c_comt_rev, user_id from "
				+ " (select * from comm_coment natural join client where comm_bo_no=? and c_comt_rev IS NULL order by c_comt_date asc)) where rnum>=? and rnum<= ?";
		
		//개수
		String countsql="select count(c_comt_no) from "+ "(select rownum rnum, c_comt_no, c_comt_cont, user_no, c_comt_date, comm_bo_no, c_comt_rev, user_id from "
				+ " (select * from comm_coment natural join client where comm_bo_no=? and c_comt_rev IS NULL order by c_comt_date asc)) where rnum>=? and rnum<= ?";

		List<CCBforList> list= new ArrayList<CCBforList>();
								  //한페이지당 10개씩 목록 1page 2   3   4
		int startrow=(page-1)* limit +1;	//시작번호	  1   11  21  31
		int endrow=startrow + limit -1;		//마지막번호 10  20  30  40
		int count=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(countsql);
			pstmt.setInt(1, comm_bo_no);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs=pstmt.executeQuery();
			rs.next();
			count=rs.getInt(1);
			
			pstmt.close();
			pstmt=con.prepareStatement(board_list_sql);
			pstmt.setInt(1, comm_bo_no);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				CCBforList b=new CCBforList();
				b.setC_comt_no(rs.getInt("c_comt_no"));
				b.setC_comt_cont(rs.getString("c_comt_cont"));
				b.setUser_no(rs.getInt("user_no"));
				b.setC_comt_date(rs.getDate("c_comt_date"));
				b.setC_comt_rev(rs.getInt("c_comt_rev"));
				b.setComm_bo_no(rs.getInt("comm_bo_no"));
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
	
	//대댓글 뽑아옴
	//c_comt_re_no : 대댓글의 원본댓글-pk
	public List<CCBforList> getRECommentList(int page, int limit,int comm_bo_no, int c_comt_re_no) {
		// TODO Auto-generated method stub
		String board_list_sql="select * from "+ "(select c_comt_no, c_comt_cont, user_no, c_comt_date, comm_bo_no, c_comt_rev from "
				+ " (select * from comm_coment where comm_bo_no=? and c_comt_re_no=? order by c_comt_date desc)) where rnum>=? and rnum<= ?";
		
		List<CCBforList> list= new ArrayList<CCBforList>();
								  //한페이지당 10개씩 목록 1page 2   3   4
		int startrow=(page-1)* limit +1;	//시작번호	  1   11  21  31
		int endrow=startrow + limit -1;		//마지막번호 10  20  30  40
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(board_list_sql);
			pstmt.setInt(1, comm_bo_no);
			pstmt.setInt(2, c_comt_re_no);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				CCBforList b=new CCBforList();
				
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

	//그냥 댓글 쓰기
	public boolean C_CommInsert(CommCommentBean boarddata) {
		// TODO Auto-generated method stub
		int num=0;
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			sql="insert into comm_coment (c_comt_no, c_comt_cont, user_no, comm_bo_no, c_comt_rev, c_comt_date) "
				+" values(c_comt_no.nextval, ?, ?, ?,null, sysdate)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getC_comt_cont());
			pstmt.setInt(2, boarddata.getUser_no());
			pstmt.setInt(3, boarddata.getComm_bo_no());
			result=pstmt.executeUpdate();
			
			if(result==0)
				return false;
			return true;
			
		}catch(Exception e) {
			System.out.println("boardinsert()에러 입니다." );
			e.printStackTrace();
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}

	//대댓글 쓰기
	public boolean C_ReCommInsert(CommCommentBean boarddata) {
		// TODO Auto-generated method stub
		int num=0;
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			sql="insert into comm_coment (c_comt_no, c_comt_cont, user_no, comm_bo_no, c_comt_rev, c_comt_date) "
				+" values(c_comt_no.nextval, ?, ?, ?,?, sysdate)";
	
			pstmt.setString(1, boarddata.getC_comt_cont());
			pstmt.setInt(2, boarddata.getUser_no());
			pstmt.setInt(3, boarddata.getComm_bo_no());
			pstmt.setInt(4, boarddata.getC_comt_rev()); //원본댓글의 pk가 들어감
			result=pstmt.executeUpdate();
			
			if(result==0)
				return false;
				return true;
			
		}catch(Exception e) {
			System.out.println("boardinsert()에러 입니다.");
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}

	public boolean C_CommentModify(CommCommentBean boarddata) {
		// TODO Auto-generated method stub
		String sql="update comm_coment set c_comt_cont=?  where c_comt_no=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getC_comt_cont());
			pstmt.setInt(2, boarddata.getC_comt_no());
	
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

	public int isC_CommWriter(int c_comt_no) {
		// TODO Auto-generated method stub
		String sql="select user_no from comm_coment where c_comt_no=?";
		int user_check=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, c_comt_no);
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

		System.out.println("isCommwriter오류");
		return 0;
	}

	public boolean CommDelete(int c_comt_no) {
		// TODO Auto-generated method stub
		String sql="delete from comm_coment where c_comt_no=? ";
		int result=0;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, c_comt_no);
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
