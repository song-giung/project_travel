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

public class festivalDAO {
   DataSource ds;
   Connection con;
   PreparedStatement pstmt;
   ResultSet rs;
   int result;

   public festivalDAO() {
      try {
         Context init = new InitialContext();
         ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
      } catch (Exception ex) {
         System.out.println("DB 연결 실패 : " + ex);
         return;
      }
   }//생성자 end
   
   public int insertFestival(festival f) {
      int result =0;
      int num=0;
      try {
         con=ds.getConnection();
         
         
         String seq = " select festival_seq.nextval from dual ";
         pstmt=con.prepareStatement(seq);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            num=rs.getInt(1);
         }
         
         pstmt.close();
         
         
         String sql = "insert into festival_information(festival_no,festival_name,festival_startday,festival_endday,festival_address,festival_content,festival_location)"
                  +"values(?,?,?,?,?,?,?) ";
         
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, num);
         pstmt.setString(2, f.getFestival_name());
         pstmt.setString(3, f.getStartday());
         pstmt.setString(4, f.getEndday());
         pstmt.setString(5, f.getFestival_address());
         pstmt.setString(6,f.getFestival_content());
         pstmt.setString(7, f.getLocation());
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
      return num;
   }//insertFestival end
   
   
   
   public List<festival> getFestivalList(){
      List<festival> list = new ArrayList<festival>();
      try {
         con=ds.getConnection();
         String sql="select * from festival_information order by festival_no desc ";
         pstmt=con.prepareStatement(sql);
         rs=pstmt.executeQuery();
         
         while(rs.next()) {
            festival f = new festival();
            f.setFestival_no(rs.getInt("festival_no"));
            f.setFestival_address(rs.getString("festival_address"));
            f.setFestival_content(rs.getString("festival_content"));
            f.setFestival_name(rs.getString("festival_name"));
            f.setLocation(rs.getString("festival_location"));
            f.setStartday(rs.getString("festival_startday"));
            f.setEndday(rs.getString("festival_endday"));
            list.add(f);
         }
      }catch(Exception e) {
         System.out.println("축제 리스트 에러" + e.getMessage());
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
   
   public List<festival> getFestivalList_asc(){
	      List<festival> list = new ArrayList<festival>();
	      try {
	         con=ds.getConnection();
	         String sql="select * from festival_information order by festival_no ";
	         pstmt=con.prepareStatement(sql);
	         rs=pstmt.executeQuery();
	         
	         while(rs.next()) {
	            festival f = new festival();
	            f.setFestival_no(rs.getInt("festival_no"));
	            f.setFestival_address(rs.getString("festival_address"));
	            f.setFestival_content(rs.getString("festival_content"));
	            f.setFestival_name(rs.getString("festival_name"));
	            f.setLocation(rs.getString("festival_location"));
	            f.setStartday(rs.getString("festival_startday"));
	            f.setEndday(rs.getString("festival_endday"));
	            list.add(f);
	         }
	      }catch(Exception e) {
	         System.out.println("축제 리스트 에러" + e.getMessage());
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
   
   public void insertFestival_img(festival_image t) {
      try {
         con=ds.getConnection();
         String sql="insert into festival_image values(festival_image_seq.nextval,?,?,?) ";
         pstmt=con.prepareStatement(sql);
         pstmt.setString(1, t.getFestival_img_name());
         pstmt.setInt(2, t.getFestival_img_type());
         pstmt.setInt(3, t.getFestival_no());
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
   }// insertFestival_img end
   
   public boolean delete_festival(int no) {
      boolean temp = false;
      int test = 0;
      try {
         con=ds.getConnection();
         String img = "delete from festival_image where festival_no =? ";
         pstmt=con.prepareStatement(img);
         pstmt.setInt(1,no);
         pstmt.executeUpdate();
         
         pstmt.close();
         
         String up = "delete from festival_up where festival_no=? ";
         pstmt = con.prepareStatement(up);
         pstmt.setInt(1, no);
         pstmt.executeUpdate();
         
         pstmt.close();
         
         String down = "delete from festival_down where festival_no=? ";
         pstmt = con.prepareStatement(down);
         pstmt.setInt(1, no);
         pstmt.executeUpdate();
         
         pstmt.close();
         
         String pocket = "delete from pocket where festival_no=? ";
         pstmt = con.prepareStatement(pocket);
         pstmt.setInt(1, no);
         pstmt.executeUpdate();
         
         pstmt.close();
         
         String sql = "delete from festival_information where festival_no =? ";
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, no);
         test=pstmt.executeUpdate();
         
         if(test==1) {
            temp=true;
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
   } // delete_festival end
   
   public List<festival_image> getFestival_img_list(){
      List<festival_image> list= new ArrayList<festival_image>();
      try {
         con=ds.getConnection();
         String sql="select * from travel_image ";
         pstmt=con.prepareStatement(sql);
         rs=pstmt.executeQuery();
         
         while(rs.next()) {
            festival_image f = new festival_image();
            f.setFestival_igm_no(rs.getInt("festival_img_no"));
            f.setFestival_img_name(rs.getString("festival_img_name"));
            f.setFestival_img_type(rs.getInt("festival_img_type"));
            f.setFestival_no(rs.getInt("festival_no"));
            list.add(f);
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
   }// getFestival_img_list end
   
   public festival getFestival_info(int no) {
      festival f = new festival();
      try {
         con=ds.getConnection();
         
         String sql = "select * from festival_information where festival_no = ? ";
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, no);
         rs=pstmt.executeQuery();
         
         if(rs.next()) {
            f.setEndday(rs.getString("festival_endday"));
            f.setFestival_address(rs.getString("festival_address"));
            f.setFestival_content(rs.getString("festival_content"));
            f.setFestival_name(rs.getString("festival_name"));
            f.setFestival_no(rs.getInt("festival_no"));
            f.setLocation(rs.getString("festival_location"));
            f.setStartday(rs.getString("festival_startday"));
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
      return f;
   }// getFestival_info end
   
   public List<festival_image> getFestival_image_info(int no){
      List<festival_image> list = new ArrayList<festival_image>();
      try {
         con=ds.getConnection();
         
         String sql = "select * from festival_image where festival_no=? order by festival_img_type desc ";
         
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, no);
         rs=pstmt.executeQuery();
         
         while(rs.next()) {
            festival_image f = new festival_image();
            f.setFestival_igm_no(rs.getInt("festival_img_no"));
            f.setFestival_img_name(rs.getString("festival_img_name"));
            f.setFestival_img_type(rs.getInt("festival_img_type"));
            f.setFestival_no(rs.getInt("festival_no"));
            list.add(f);
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
   } // getFestival_image_info end 
   
   public festival_image getFirstImage(int no) {
      festival_image f= new festival_image();
      try {
         con=ds.getConnection();
         
         String sql="select * from festival_image where festival_no =? and festival_img_type=1 ";
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, no);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            f.setFestival_igm_no(rs.getInt("festival_img_no"));
            f.setFestival_img_name(rs.getString("festival_img_name"));
            f.setFestival_img_type(rs.getInt("festival_img_type"));
            f.setFestival_no(rs.getInt("festival_no"));
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
      return f;
   }// getFirstImage end
   
   
}//class end

