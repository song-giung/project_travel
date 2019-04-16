/*
 DAO(Data Access Object)클래스
 - 데이터 베이스와 연동하여 레코드의 추가,수정, 삭제 작업이 이루어지는 클래스입니다.
 - 어떤 Action클래스가 호출되더라도 그에 해당하는 데이터 베이스 연동처리는 DAO 클래스에서 이루어지게 됩니다.
 */

package festival.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class userFestivalDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public userFestivalDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}//생성자 end
	
	public List<userFestival> userFestival_list(){
		List<userFestival> list = new ArrayList<userFestival>();
		try {
			con =ds.getConnection();
			
			
			String sql = "select a.festival_no,a.festival_name,a.festival_content,a.festival_address,a.festival_startday "
					+ " ,a.festival_endday, b.festival_img_name,a.festival_location "
					+ "from festival_information a , festival_image b where a.festival_no = b.festival_no and b.festival_img_type=1 ";
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				userFestival f = new userFestival();
				f.setFestival_address(rs.getString("festival_address"));
				f.setFestival_content(rs.getString("festival_content"));
				f.setFestival_endday(rs.getString("festival_endday"));
				f.setFestival_location(rs.getString("festival_location"));
				f.setFestival_name(rs.getString("festival_name"));
				f.setFestival_no(rs.getInt("festival_no"));
				f.setFestival_startday(rs.getString("festival_startday"));
				f.setFestival_img_name(rs.getString("festival_img_name"));
				System.out.println(f.getFestival_img_name());
				list.add(f);
			}
			return list;
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
	}
	
	public List<userFestival> userFestival_list(String location){
		List<userFestival> list = new ArrayList<userFestival>();
		try {
			con =ds.getConnection();
			
			
			String sql = "select a.festival_no,a.festival_name,a.festival_content,a.festival_address,a.festival_startday "
					+ " ,a.festival_endday, b.festival_img_name,a.festival_location "
					+ "from festival_information a , festival_image b where a.festival_no = b.festival_no and b.festival_img_type=1"
					+ "and a.festival_location =? ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, location);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				userFestival f = new userFestival();
				f.setFestival_address(rs.getString("festival_address"));
				f.setFestival_content(rs.getString("festival_content"));
				f.setFestival_endday(rs.getString("festival_endday"));
				f.setFestival_location(rs.getString("festival_location"));
				f.setFestival_name(rs.getString("festival_name"));
				f.setFestival_no(rs.getInt("festival_no"));
				f.setFestival_startday(rs.getString("festival_startday"));
				f.setFestival_img_name(rs.getString("festival_img_name"));
				System.out.println(f.getFestival_img_name());
				list.add(f);
			}
			return list;
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
	}
	
	public userFestival userFestival_info(int no) {
		userFestival f = new userFestival();
		try {
			con=ds.getConnection();
			String sql = "select A.festival_no,A.festival_name,A.festival_content,A.festival_address, "
					+ "A.festival_startday,A.festival_endday,A.festival_location,B.festival_img_name "
					+ "from festival_information A, festival_image B where A.festival_no=B.festival_no "
					+ "and A.festival_no = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				f.setFestival_address(rs.getString("festival_address"));
				f.setFestival_content(rs.getString("festival_content"));
				f.setFestival_endday(rs.getString("festival_endday"));
				f.setFestival_img_name(rs.getString("festival_img_name"));
				f.setFestival_location(rs.getString("festival_location"));
				f.setFestival_name(rs.getString("festival_name"));
				f.setFestival_no(rs.getInt("festival_no"));
				f.setFestival_startday(rs.getString("festival_startday"));
			}
			
			return f;
			
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
		return f;
	}// userFestival_info
	
	public boolean festival_pocketplus(int user_no,int festival_no) {

		boolean temp = false;
		try {
			con=ds.getConnection();
			String sql = "insert into pocket (user_no, pocket_no, festival_no) values (? , pocket_no_seq.nextval ,? ) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, festival_no);
			
			result=pstmt.executeUpdate();
			
			if(result==1) {
				temp = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return temp;
	}

	public int up_insert(int user_no,int festival_no,int user_type) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="insert into festival_up "
					+ "(festival_up_no,user_no,festival_no,user_type) values(festival_up_seq.nextval,?,?,?) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, festival_no);
			pstmt.setInt(3, user_type);
			num=pstmt.executeUpdate();
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
		return num;
	}

	public int down_insert(int user_no,int festival_no,int user_type) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="insert into festival_down(festival_down_no,user_no,festival_no,user_type) "
					+ "values(festival_down_seq.nextval,?,?,?) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, festival_no);
			pstmt.setInt(3, user_type);
			num=pstmt.executeUpdate();
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
		return num;
	}

	public void up_delete(int user_no,int festival_no) {
		try {
			con=ds.getConnection();
			String sql="delete from festival_up where user_no=? and festival_no=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, festival_no);
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
	}

	public void down_delete(int user_no,int festival_no) {
		try {
			con=ds.getConnection();
			String sql="delete from festival_down where user_no=? and festival_no=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, festival_no);
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
	}
	
	public int up_select(int user_no , int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="select * from festival_up where user_no=? and festival_no = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, festival_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=1;
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
		return num;
	}

	public int down_select(int user_no,int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="select * from festival_down where user_no=? and festival_no=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, festival_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=1;
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
		return num;
	}

	public int up_count1(int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(festival_up_no) " + 
					"from festival_up " + 
					"where festival_no =? and user_type=1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, festival_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
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
		return num;
		
	}
	
	public int up_count2(int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(festival_up_no) " + 
					"from festival_up " + 
					"where festival_no =? and user_type=2 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, festival_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
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
		return num;
		
	}

	public int up_count3(int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(festival_up_no) " + 
					"from festival_up " + 
					"where festival_no =? and user_type=3 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, festival_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
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
		return num;
		
	}
	
	public int up_count4(int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(festival_up_no) " + 
					"from festival_up " + 
					"where festival_no =? and user_type=4 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, festival_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
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
		return num;
		
	}

	public int down_count1(int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(festival_down_no) " + 
					"from festival_down " + 
					"where festival_no =? and user_type=1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, festival_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
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
		return num;
		
	}
	
	public int down_count2(int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(festival_down_no) " + 
					"from festival_down " + 
					"where festival_no =? and user_type=2 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, festival_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
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
		return num;
		
	}

	public int down_count3(int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(festival_down_no) " + 
					"from festival_down " + 
					"where festival_no =? and user_type=3 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, festival_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
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
		return num;
		
	}

	public int down_count4(int festival_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(festival_down_no) " + 
					"from festival_down " + 
					"where festival_no =? and user_type=4 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, festival_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
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
		return num;
		
	}
}//class end


