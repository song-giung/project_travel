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

public class userTravelDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public userTravelDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}//생성자 end

	
	public List<userTravelList> getUserTravel_Tema_List(String tema){
		List<userTravelList> list = new ArrayList<userTravelList>();
		try {
			con=ds.getConnection();
			
			String sql="select A.travel_no,A.travel_address,A.travel_content,A.travel_location,A.travel_name,A.travel_tema,B.travel_img_name  from travel_information A,travel_image B where A.travel_no=B.travel_no and A.travel_tema=? and B.travel_img_type=1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tema);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				userTravelList temp = new userTravelList();
				temp.setTravel_no(rs.getInt("travel_no"));
				temp.setTravel_address(rs.getString("travel_address"));
				temp.setTravel_content(rs.getString("travel_content"));
				temp.setTravel_location(rs.getString("travel_location"));
				temp.setTravel_name(rs.getString("travel_name"));
				temp.setTravel_tema(rs.getString("travel_tema"));
				temp.setTravel_img_name(rs.getString("travel_img_name"));
				list.add(temp);
			}
		}catch(Exception e) {
			System.out.println("유저 여행지 테마 리스트 추가 에러"+e.getMessage());
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
	}//getTraveList end
	
	
	public travel getTravel_info(int no) {
		travel t = new travel();
		try {
			con=ds.getConnection();
			
			String sql="select * from travel_information where travel_no =? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				t.setTravel_no(rs.getInt("travel_no"));
				t.setTravel_address(rs.getString("travel_address"));
				t.setTravel_location(rs.getString("travel_location"));
				t.setTravel_content(rs.getString("travel_content"));
				t.setTravel_name(rs.getString("travel_name"));
				t.setTravel_tema(rs.getString("travel_tema"));
			}
		}catch(Exception e) {
			System.out.println("여행지 상세정보 에러" + e.getMessage());
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
				
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return t;
	}//getTravel_info end
	
	public List<travel_type> getTravel_type_info(int no) {
		List<travel_type> list = new ArrayList<travel_type>();
		try {
			con=ds.getConnection();
			String sql="select * from travel_type where travel_no = ? ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				travel_type t= new travel_type();
				t.setTravel_no(rs.getInt("travel_no"));
				t.setTravel_type(rs.getInt("travel_type"));
				t.setTravel_type_no(rs.getInt("travel_type_no"));
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
	}//getTravel_type_info end
	
	public List<userTravelList> getTypeList(String type[],String tema){
		List<userTravelList> list = new ArrayList<userTravelList>();
		try {
			con=ds.getConnection();
			
			String sql = "select distinct A.travel_no,A.travel_address,A.travel_content,A.travel_location, "
					+"A.travel_name,A.travel_tema,B.travel_img_name "
					+"from travel_information A,travel_image B ,travel_type C " + 
					"where A.travel_no=B.travel_no " + 
					"and A.travel_no=C.travel_no " + 
					"and B.travel_img_type=1 " +
					" and A.travel_tema=? "+
					"and ( C.travel_type=? ";
					
					for(int i=1;i<type.length;i++) {
						sql+= "or C.travel_type=? ";
					}
					sql += ") order by A.travel_no ";
					System.out.println(sql);
					
					pstmt=con.prepareStatement(sql);
					
					pstmt.setString(1, tema);
					
					for(int i=0;i<type.length;i++) {
						pstmt.setInt(i+2, Integer.parseInt(type[i]));
					}
					
					System.out.println(pstmt.toString());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						userTravelList t = new userTravelList();
						t.setTravel_no(rs.getInt("travel_no"));
						t.setTravel_address(rs.getString("travel_address"));
						t.setTravel_content(rs.getString("travel_content"));
						t.setTravel_location(rs.getString("travel_location"));
						t.setTravel_name(rs.getString("travel_name"));
						t.setTravel_tema(rs.getString("travel_tema"));
						t.setTravel_img_name(rs.getString("travel_img_name"));
						list.add(t);
						System.out.println(1);
					}
					
					return list;
					
					
		}catch(Exception e) {
			System.out.println("여기에러냐?");
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
	
	public int travel_modify(travel t) {
		int result=0;
		try {
			con=ds.getConnection();
			
			String sql="update travel_information set travel_name=? ,travel_address=? , travel_content = ? ,travel_tema =? ,travel_location = ? "
					+" where travel_no =? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, t.getTravel_name());
			pstmt.setString(2, t.getTravel_address());
			pstmt.setString(3, t.getTravel_content());
			pstmt.setString(4, t.getTravel_tema());
			pstmt.setString(5, t.getTravel_location());
			pstmt.setInt(6, t.getTravel_no());
			
			result =pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("여행 수정 에러" +e.getMessage());
		}finally {
			try {
				
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return result;
	}//travel_modify end
	
	public boolean travel_delete(int no) {
		boolean check=false;
		int result=0;
		try {
			con=ds.getConnection();
			
			//이미지 삭제
			
			String sql_img = "delete from travel_image where travel_no =? ";
			
			pstmt=con.prepareStatement(sql_img);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			
			//코멘트 삭제
			
			String sql_comment = "delete from travel_comment where travel_no= ? ";
			
			pstmt=con.prepareStatement(sql_comment);
			
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			
			//타입 삭제
			
			String sql_type = "delete from travel_type where travel_no= ? ";
			 
			pstmt=con.prepareStatement(sql_type);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			//여행지 삭제
			String sql = "delete from travel_information where travel_no =? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result=pstmt.executeUpdate();
			
			if(result==1) {
				check=true;
			}
		}catch(Exception e) {
			System.out.println("여행지 삭제 에러"+e.getMessage());
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return check;
	}//travel_delete end
	
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
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return result;
	}//travel_img_modify end
	
	public int getTravelcount() {
		int result = 0;
		try {
			con=ds.getConnection();
			String sql = "select travel_seq.currval from dual; ";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}else {
				result=1;
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
		
		return result;
		
		
	}//getTravelcount end
	
	
	public void travel_img_insert(travel_image t) {
		try {
			con=ds.getConnection();
			
			
			
			String sql = "insert into travel_img values(travel_img_seq.nextval,?,?,?) ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, t.getTravel_img_no());
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
	
	public userTravelList getuserTravel_info(int no){
		userTravelList t = new userTravelList();
		try {
			con=ds.getConnection();
			
			String sql= "select A.travel_no,A.travel_address,A.travel_location,A.travel_name,A.travel_content, "
					+"A.travel_tema,B.travel_img_name "
					+"from travel_information A, travel_image B "
					+"where A.travel_no=B.travel_no "
					+"and B.travel_img_type =1 "
					+"and A.travel_no = ? ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				t.setTravel_no(rs.getInt("travel_no"));
				t.setTravel_address(rs.getString("travel_address"));
				t.setTravel_content(rs.getString("travel_content"));
				t.setTravel_location(rs.getString("travel_location"));
				t.setTravel_name(rs.getString("travel_name"));
				t.setTravel_tema(rs.getString("travel_tema"));
				t.setTravel_img_name(rs.getString("travel_img_name"));
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
		return t;
	}// getuserTravel_info end
	
	public boolean travel_pocketplus(int user_no,int travel_no) {
		boolean temp = false;
		try {
			con=ds.getConnection();
			String sql = "insert into pocket (user_no, pocket_no, travel_no) values (? , pocket_no_seq.nextval ,? ) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, travel_no);
			
			result=pstmt.executeUpdate();
			
			if(result==1) {
				temp = true;
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
		return temp;
	}// travel_pocketplus
	
	public int up_count1(int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(travel_up_no) " + 
					"from travel_up " + 
					"where travel_no =? and travel_type=1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_no);
			
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
	
	public int up_count2(int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(travel_up_no) " + 
					"from travel_up " + 
					"where travel_no =? and travel_type=2 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_no);
			
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
	
	public int up_count3(int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(travel_up_no) " + 
					"from travel_up " + 
					"where travel_no =? and travel_type=3 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_no);
			
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
	
	public int up_count4(int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(travel_up_no) " + 
					"from travel_up " + 
					"where travel_no =? and travel_type=4 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_no);
			
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

	public int down_count1(int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(travel_down_no) " + 
					"from travel_down " + 
					"where travel_no =? and travel_type=1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_no);
			
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

	public int down_count2(int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(travel_down_no) " + 
					"from travel_down " + 
					"where travel_no =? and travel_type=2 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_no);
			
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
	
	public int down_count3(int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(travel_down_no) " + 
					"from travel_down " + 
					"where travel_no =? and travel_type=3 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_no);
			
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
	                        
	public int down_count4(int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(travel_down_no) " + 
					"from travel_down " + 
					"where travel_no =? and travel_type=4 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_no);
			
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
	
	public int up_select(int user_no , int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="select * from travel_up where user_no=? and travel_no = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, travel_no);
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
	
	public int down_select(int user_no,int travel_no) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="select * from travel_down where user_no=? and travel_no=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, travel_no);
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

	public int up_insert(int user_no,int travel_no,int travel_type) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="insert into travel_up "
					+ "(travel_up_no,user_no,travel_no,travel_type) values(travel_up_seq.nextval,?,?,?) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, travel_no);
			pstmt.setInt(3, travel_type);
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
	
	public int down_insert(int user_no,int travel_no,int travel_type) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="insert into travel_down(travel_down_no,user_no,travel_no,travel_type) "
					+ "values(travel_down_seq.nextval,?,?,?) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, travel_no);
			pstmt.setInt(3, travel_type);
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
	
	public void up_delete(int user_no,int travel_no) {
		try {
			con=ds.getConnection();
			String sql="delete from travel_up where user_no=? and travel_no=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, travel_no);
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
	
	public void down_delete(int user_no,int travel_no) {
		try {
			con=ds.getConnection();
			String sql="delete from travel_down where user_no=? and travel_no=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, travel_no);
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
	
	public int getUserType(int user_no) {
		int num=0;
		try {
			con=ds.getConnection();
			String sql="select user_type from client where user_no =? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt("user_type");
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
}//class end


