/*
 * DAO(Data Access Object)클래스
 * - 데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스입니다.
 * - 어떤 Action 클래스가 호출되더라도 그에 해당하는 데이터 베이스 연동 처리는 DAO 클래스에서 이루어지게 됩니다.
 */
package notice.db;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class NoticeDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	
	public NoticeDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}//BoardDAO end

	public int getListCount() {
		int x =0;
		try {
			con=ds.getConnection();
			String sql = "select count(*) from notice ";
			pstmt = con.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x=rs.getInt(1);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {if( pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if( rs != null) rs.close();}catch(Exception e) {e.printStackTrace();}
			try {if( con != null) con.close();}catch(Exception e) {e.printStackTrace();}
		}		
		return x;
	}//list count end


	public List<NoticeBean> getNoticeList(int page, int limit) {
		//page : 페이지
		//notice_no desc에  의해 정렬한 것을 
		//조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.		
		String sql="select*from (select rownum rnum, notice_no, notice_title, "
				+ "notice_content, admin_id, notice_date from"
				+ "(select*from notice order by notice_no desc)) "
				+ "where rnum>=? and rnum<=?";
		List<NoticeBean> list = new ArrayList<NoticeBean>();
								//한 페이지당 10개씩 목록인 경우		1페이지	2페이지	3페이지 	
		int startrow = (page-1)* limit +1;//읽기 시작할 row 번호(1		11		21)
		int endrow = startrow + limit -1; //읽을 마지막 row 번호(10		20		30)
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  startrow);;
			pstmt.setInt(2,  endrow);
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()) {
				NoticeBean nb = new NoticeBean();
				nb.setNotice_no(rs.getInt("notice_no"));
				nb.setNotice_title(rs.getString("notice_title"));
				nb.setNotice_content(rs.getString("notice_content"));
				nb.setAdmin_id(rs.getString("admin_id"));
				nb.setNotice_date(rs.getDate("notice_date"));
				list.add(nb);//값을 감은 객체를 리스트에 저장합니다.
			}			
		}catch(Exception ex) {
			System.out.println("getNoticeList() 에러 : " + ex);
		}
		finally {
			try {if(rs !=null)rs.close();}catch(SQLException e) {e.printStackTrace();}
			try {if(pstmt !=null)pstmt.close();}catch(SQLException e) {e.printStackTrace();}
			try {if(con !=null)con.close();}catch(SQLException e) {e.printStackTrace();}
		}
		return list;//값을 담은 객체 저장한 리스트를 호출한 곳으로
	}//notice list end

	public boolean noticeInsert(NoticeBean nb) {
		int num =0;
		String sql="";
		int result=0;
		try {
			con=ds.getConnection();
			//notice 테이블의 notice_no 필드의 최대값을 구해와서 글을 등록할 때
			//글 번호를 순차적으로 지정하기 위함입니다.
			String max_sql = "select max(notice_no) from notice";
			pstmt = con.prepareStatement(max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				num=rs.getInt(1)+1;//최대값보다 1만큼 큰값을 지정합니다.
			else
				num=1;//처음 데이터를 등록하는 경우입니다.
			pstmt.close();
	
			
			sql = "insert into notice values(?,?,?,?,sysdate)";
			
			//새로운 글을 등록하는 부분입니다.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, nb.getNotice_title());
			pstmt.setString(3, nb.getNotice_content());
			pstmt.setString(4, nb.getAdmin_id());
					
			result = pstmt.executeUpdate();
						
		}catch(Exception ex) {
			System.out.println("noticeInsert() 에러 :  " + ex);
		}
		finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}
		if(result==0) {	return false;}else {return true;}
	}//noticeInsert end

	

	public NoticeBean getDetail(int num) {
		NoticeBean nb = null;
		
		try {
			con=ds.getConnection();
			String sql="select*from notice where notice_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				nb = new NoticeBean();
				nb.setNotice_no(rs.getInt("notice_no"));
				nb.setNotice_title(rs.getString("notice_title"));
				nb.setNotice_content(rs.getString("notice_content"));
				nb.setAdmin_id(rs.getString("admin_id"));
				nb.setNotice_date(rs.getDate("notice_date"));
			}			
		}catch(Exception e) {
			System.out.println("getDetail 에러 : " + e );
		}
		finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
		}	
		
		return nb;
	}//notice detail end

	public boolean noticeModify(NoticeBean nb) {
		String sql = "update notice set"
				+ " admin_id=?, notice_title=?, notice_content=? "
				+ " where notice_no=?";
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nb.getAdmin_id());
			pstmt.setString(2, nb.getNotice_title());
			pstmt.setString(3, nb.getNotice_content());
			pstmt.setInt(4, nb.getNotice_no());			
			
			result =pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("공지 사항 modify() 에러 :  " + ex);
		}
		finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}	
		if(result == 1) {return true;}else {return false;}
	}//noticeModify end

	public boolean delete(NoticeBean nb) {
		String sql = "delete from notice where notice_no=?";
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nb.getNotice_no());
		
			result =pstmt.executeUpdate();
			//삭제가 안된 경우에는 false를 반환합니다.
		}catch(Exception ex) {
			System.out.println("공지 사항 delete() 에러 :  " + ex);
		}
		finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}		
		if(result == 1) {return true;}else {return false;}
	}//delete end
}













