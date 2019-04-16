package comm_board.db;
/*
    DAO(data access object)클래스
    -데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스
    -어떤 Action 클래스가 호출되더라도 그에 해당하는 데이터베이스 연동 처리는 DAO 클래스에서 이루어지게 됩니다.
 */

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Date;

import javax.sql.*;



import java.util.*;
import javax.naming.*;


public class CommBoardDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public CommBoardDAO() {
		try {
			Context init= new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e){
			System.out.println("DB 연결 실패 : "+ e);
			return;
		}
	}
	public void searchCommu() {
		/* select commu_board.comm_bo_title, commu_board.comm_bo_content, client.user_name
		 from commu_board natural join client;*/
		 
	}
	public int getListCount() {
		// TODO Auto-generated method stub
		String sql="select count(*) from commu_board";
			int result=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
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
	public List<CBBforList> firstgetBoardList(int page, int limit) {
		// TODO Auto-generated method stub
		//types를 어떻게 뽑아오지? join?
		System.out.println("firstgetBoardList 들어옴");
		String board_list_sql1="select * from "+ "(select rownum rnum, comm_bo_no, comm_bo_title, comm_bo_content, comm_bo_file, comm_bo_date, "
				+ " user_id, user_type from "
				+ " (select * from commu_board natural join client order by comm_bo_date desc) )where rnum>=? and rnum<= ?";
		
		List<CBBforList> list= new ArrayList<CBBforList>();
								  //한페이지당 10개씩 목록 1page 2   3   4
		int startrow=(page-1)* limit +1;	//시작번호	  1   11  21  31
		int endrow=startrow + limit -1;		//마지막번호 10  20  30  40
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(board_list_sql1);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				CBBforList b=new CBBforList();
				b.setComm_bo_no(rs.getInt("comm_bo_no"));
				b.setComm_bo_title(rs.getString("comm_bo_title"));
				b.setComm_bo_content(rs.getString("comm_bo_content"));
				b.setComm_bo_date(rs.getDate("comm_bo_date"));
				b.setUser_id(rs.getString("user_id"));
				b.setUser_type(rs.getInt("user_type"));
				b.setComm_bo_file(rs.getString("comm_bo_file"));
			
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
	
	public List<CBBforList> getBoardList(String types[],int page, int limit) {
		// TODO Auto-generated method stub
		//types를 어떻게 뽑아오지? join?
		String board_list_sql1="select * from "+ "(select rownum rnum, comm_bo_no, comm_bo_title, comm_bo_content, comm_bo_file, comm_bo_date, "
				+ " user_no, user_id, user_type from "
				+ " (select * from commu_board natural join client where user_type=? ";
		
		//개수가져오기
		String countlistsql="select count(comm_bo_no) from "+ "(select rownum rnum, comm_bo_no from "
				+ " (select * from commu_board natural join client where user_type=? ";
		
		
		for(int i=1; i<types.length;i++) {
			board_list_sql1+=" or user_type=? ";
			countlistsql+=" or user_type=? ";
		}
		
		board_list_sql1+="order by comm_bo_date desc) )where rnum>=? and rnum<= ?";
		countlistsql+=") )";
		
		List<CBBforList> list= new ArrayList<CBBforList>();
								  //한페이지당 10개씩 목록 1page 2   3   4
		int startrow=(page-1)* limit +1;	//시작번호	  1   11  21  31
		int endrow=startrow + limit -1;		//마지막번호 10  20  30  40
		int listcount=0;
		
		try {
			con=ds.getConnection();
			
			pstmt=con.prepareStatement(countlistsql);
			for(int i=1; i<=types.length; i++) {
				pstmt.setInt(i, Integer.parseInt(types[i-1]));
			}
			rs=pstmt.executeQuery();
			rs.next();
			listcount=rs.getInt(1);
			pstmt.close();
			pstmt=con.prepareStatement(board_list_sql1);
			for(int i=1; i<=types.length; i++) {
				pstmt.setInt(i, Integer.parseInt(types[i-1]));
			}
			pstmt.setInt(types.length+1, startrow);
			pstmt.setInt(types.length+2, endrow);
			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				CBBforList b=new CBBforList();
				b.setComm_bo_no(rs.getInt("comm_bo_no"));
				b.setComm_bo_title(rs.getString("comm_bo_title"));
				b.setComm_bo_content(rs.getString("comm_bo_content"));
				b.setComm_bo_date(rs.getDate("comm_bo_date"));
				b.setUser_id(rs.getString("user_id"));
				b.setListcount(listcount);
				b.setUser_type(rs.getInt("user_type"));
				b.setComm_bo_file(rs.getString("comm_bo_file"));
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
	public List<CBBforList> getBoardList(String searchindex, String searchcontent, String types[],int page, int limit) {
		// TODO Auto-generated method stub
		String board_list_sql1="select * from "+ "(select rownum rnum, comm_bo_no, comm_bo_title, comm_bo_content, comm_bo_file, comm_bo_date, "
				+ " user_no, user_id, user_type from "
				+ " (select * from commu_board natural join client where user_type=? ";
		
		String countlistsql="select count(comm_bo_no) from (select comm_bo_no, comm_bo_title, comm_bo_content, user_id from"
				+ " (select * from commu_board natural join client where user_type=? ";
		
		String aa="%"+searchcontent+"%";
		
		switch(searchindex){
		case "title": searchindex="comm_bo_title"; break;
		case "user": searchindex="user_id"; break;
		case "content": searchindex="comm_bo_content"; break;
 		}
		
		for(int i=1; i<types.length;i++) {
			board_list_sql1+=" or user_type=? ";
			countlistsql+=" or user_type=? ";
		}
		
		board_list_sql1+=" order by comm_bo_date desc) ";
		board_list_sql1 +=" where "+searchindex+" like ?";
		board_list_sql1 +=" )where rnum>=? and rnum<= ?";
		
		countlistsql +=" )) where "+searchindex+" like ?";
		
		List<CBBforList> list= new ArrayList<CBBforList>();
								  //한페이지당 10개씩 목록 1page 2   3   4
		int startrow=(page-1)* limit +1;	//시작번호	  1   11  21  31
		int endrow=startrow + limit -1;		//마지막번호 10  20  30  40
		try {
			int listcount=0;
			
			con=ds.getConnection();
			pstmt=con.prepareStatement(countlistsql);
			for(int i=1; i<=types.length; i++) {
				pstmt.setInt(i, Integer.parseInt(types[i-1]));
			}
			pstmt.setString(types.length+1, aa);
			rs=pstmt.executeQuery();
			rs.next();
			listcount=rs.getInt(1);
			pstmt.close();
			
			pstmt=con.prepareStatement(countlistsql);
			pstmt=con.prepareStatement(board_list_sql1);
			for(int i=1; i<=types.length; i++) {
				pstmt.setInt(i, Integer.parseInt(types[i-1]));
			}
			
			pstmt.setString(types.length+1, aa);
			pstmt.setInt(types.length+2, startrow);
			pstmt.setInt(types.length+3, endrow);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CBBforList b=new CBBforList();
				b.setComm_bo_no(rs.getInt("comm_bo_no"));
				b.setComm_bo_title(rs.getString("comm_bo_title"));
				b.setComm_bo_content(rs.getString("comm_bo_content"));
				b.setComm_bo_date(rs.getDate("comm_bo_date"));
				b.setUser_id(rs.getString("user_id"));
				b.setUser_type(rs.getInt("user_type"));
				b.setListcount(listcount);
				b.setComm_bo_file(rs.getString("comm_bo_file"));
	
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
	public List<CBBforList> getBoardList(String searchindex, String searchcontent, int page, int limit) {
		// TODO Auto-generated method stub
		String board_list_sql1="select * from "+ "(select rownum rnum, comm_bo_no, comm_bo_title, comm_bo_content, comm_bo_file, comm_bo_date, "
				+ " user_no, user_id ,user_type from "
				+ " (select * from commu_board natural join client order by comm_bo_date desc) ";
		
		String countlistsql="select count(comm_bo_no) from (select comm_bo_no, comm_bo_content, comm_bo_title, user_id "
				+"from (select * from commu_board natural join client order by comm_bo_date desc) )";

		String aa="%"+searchcontent+"%";
		
		switch(searchindex){
		case "title": searchindex="comm_bo_title"; break;
		case "user": searchindex="user_id"; break;
		case "content": searchindex="comm_bo_content"; break;
 		}
	
		board_list_sql1 +=" where "+searchindex+" like ?";
		board_list_sql1 += " )where rnum>=? and rnum<= ?";
		
		countlistsql+=" where "+searchindex+" like ?";
		List<CBBforList> list= new ArrayList<CBBforList>();
								  //한페이지당 10개씩 목록 1page 2   3   4
		int startrow=(page-1)* limit +1;	//시작번호	  1   11  21  31
		int endrow=startrow + limit -1;		//마지막번호 10  20  30  40
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(countlistsql);
			pstmt.setString(1, aa);
			rs=pstmt.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			
			pstmt.close();
			
			pstmt=con.prepareStatement(board_list_sql1);
			pstmt.setString(1, aa);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			
			rs=pstmt.executeQuery();

			while(rs.next()) {
				CBBforList b=new CBBforList();
				b.setComm_bo_no(rs.getInt("comm_bo_no"));
				b.setComm_bo_title(rs.getString("comm_bo_title"));
				b.setComm_bo_content(rs.getString("comm_bo_content"));
				b.setComm_bo_date(rs.getDate("comm_bo_date"));
				b.setUser_id(rs.getString("user_id"));
				b.setUser_type(rs.getInt("user_type"));
				b.setListcount(count);
				b.setComm_bo_file(rs.getString("comm_bo_file"));
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


	public boolean boardInsert(CommBoardBean boarddata) {
		// TODO Auto-generated method stub
		int num=0;
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			sql="insert into commu_board(comm_bo_no, comm_bo_title, comm_bo_content, comm_bo_file, user_no, comm_bo_date )"
				+" values( comm_bo_no.nextval,?, ?, ?, ?, sysdate)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, boarddata.getComm_bo_title());
			pstmt.setString(2, boarddata.getComm_bo_content());
			pstmt.setString(3, boarddata.getComm_bo_file());
			pstmt.setInt(4, boarddata.getUser_no());
			result=pstmt.executeUpdate();
			
			if(result==0)
				return false;
				return true;
			
		}catch(Exception e) {
			System.out.println("boardinsert()에러 입니다."+e);
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}

	public CBBforList getDetail(int comm_bo_no) {
		// TODO Auto-generated method stub
		String sql="select * from commu_board natural join client where comm_bo_no=?";
		CBBforList bb=new CBBforList();
		
	try {
		con=ds.getConnection();
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, comm_bo_no);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			bb.setComm_bo_no(rs.getInt("comm_bo_no"));
			bb.setComm_bo_title(rs.getString("comm_bo_title"));
			bb.setComm_bo_content(rs.getString("comm_bo_content"));
			bb.setComm_bo_date(rs.getDate("comm_bo_date"));
			bb.setUser_no(rs.getInt("user_no"));
			bb.setUser_id(rs.getString("user_id"));
			bb.setUser_type(rs.getInt("user_type"));
			bb.setComm_bo_file(rs.getString("comm_bo_file"));
			bb.setComm_bo_update(rs.getDate("comm_bo_update"));
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
		if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
		if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
	}
		return bb;
	}

	public boolean boardModify(CommBoardBean boarddata) {
		// TODO Auto-generated method stub
		String sql="update commu_board set comm_bo_title=? , comm_bo_content=? , comm_bo_file=? , comm_bo_update=sysdate where comm_bo_no=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
	
			pstmt.setString(1, boarddata.getComm_bo_title());
			pstmt.setString(2, boarddata.getComm_bo_content());
			pstmt.setString(3, boarddata.getComm_bo_file());
			pstmt.setInt(4, boarddata.getComm_bo_no());
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

	public int isBoardWriter(int comm_bo_no) {
		// TODO Auto-generated method stub
		String sql="select user_no from commu_board where  comm_bo_no=?";
		int userno=0;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comm_bo_no);
			rs=pstmt.executeQuery();
			
			
			if(rs.next())
				userno=rs.getInt(1);
			
			System.out.println("dao 에서 찾은 user_no"+userno);
			return userno;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}

		System.out.println("isboardwriter오류");
		return userno;
	}

	public boolean boardDelete(int comm_bo_no) {
		// TODO Auto-generated method stub
		String sql="delete from commu_board where comm_bo_no=? ";
		String sql1="delete from comm_coment where comm_bo_no=? ";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql1);
			pstmt.setInt(1, comm_bo_no);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comm_bo_no);
			pstmt.executeUpdate();
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
