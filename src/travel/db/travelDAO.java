/*
 DAO(Data Access Object)클래스
 - 데이터 베이스와 연동하여 레코드의 추가,수정, 삭제 작업이 이루어지는 클래스입니다.
 - 어떤 Action클래스가 호출되더라도 그에 해당하는 데이터 베이스 연동처리는 DAO 클래스에서 이루어지게 됩니다.
 */

package travel.db;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.jdbc.OracleResultSet;
import oracle.sql.CLOB;

public class travelDAO {
   DataSource ds;
   Connection con;
   PreparedStatement pstmt;
   ResultSet rs;
   int result;

   public travelDAO() {
      try {
         Context init = new InitialContext();
         ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
      } catch (Exception ex) {
         System.out.println("DB 연결 실패 : " + ex);
         return;
      }
   }//생성자 end
   public int insertTravel(travel t) {
      int result=0;
      int num=0;
      
      try {
         con=ds.getConnection();
         String seq = "select travel_seq.nextval from dual ";
         pstmt=con.prepareStatement(seq);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            num=rs.getInt(1);
         }
         
         pstmt.close();
         
         
         String travel_insert ="insert into travel_information(travel_no,travel_name,travel_content,travel_address,travel_location,travel_tema) " 
                        +"values(?,?,?,?,?,?) ";
         pstmt=con.prepareStatement(travel_insert);
         pstmt.setInt(1, num);
         pstmt.setString(2, t.getTravel_name());
         pstmt.setString(3,t.getTravel_content());
         pstmt.setString(4,t.getTravel_address());
         pstmt.setString(5, t.getTravel_location());
         pstmt.setString(6, t.getTravel_tema());
         pstmt.executeUpdate();
   
         
         
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
   }//insertTravel end
   
   
   
   
   
   
   public void insertTravelType(travel_type t) {
   
      try {
         con=ds.getConnection();
         int no= 0;
         
         String tt= "Select max(travel_no) from travel_information ";
         
         pstmt=con.prepareStatement(tt);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            no=rs.getInt(1);
         }
         
         pstmt.close();
         
         
         String sql= "insert into travel_type values(travel_type_seq.nextval,?,?) ";
         
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, t.getTravel_type());
         pstmt.setInt(2, no);
         result=pstmt.executeUpdate();
         
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
      
      
   }//insertTravelType
   
   
   
   public void modifyTravelType(travel_type t) {
      try {
         con=ds.getConnection();
         
         
         
         String sql= "insert into travel_type values(travel_type_seq.nextval,?,?) ";
         
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, t.getTravel_type());
         pstmt.setInt(2, t.getTravel_no());
         result=pstmt.executeUpdate();
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
   } // modifyTravelType end
   
   
   public void deleteTravelType(travel_type t) {
      try {
         con=ds.getConnection();
         String sql="delete from travel_type where travel_no =? ";
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, t.getTravel_no());
         pstmt.executeUpdate();
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
   } // deleteTravelType end
   
   
   public List<travel> getTravelList(){
      List<travel> list = new ArrayList<travel>();
      try {
         con=ds.getConnection();
         
         String sql="select * from travel_information order by travel_no desc ";
         pstmt=con.prepareStatement(sql);
         rs=pstmt.executeQuery();
         
         while(rs.next()) {
            travel temp = new travel();
            temp.setTravel_no(rs.getInt("travel_no"));
            temp.setTravel_address(rs.getString("travel_address"));
            temp.setTravel_content(rs.getString("travel_content"));
            temp.setTravel_location(rs.getString("travel_location"));
            temp.setTravel_name(rs.getString("travel_name"));
            temp.setTravel_tema(rs.getString("travel_tema"));
            list.add(temp);
         }
      }catch(Exception e) {
         System.out.println("여행지 리스트 추가 에러"+e.getMessage());
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
   
   public List<travel> getTravelList_desc(){
	      List<travel> list = new ArrayList<travel>();
	      try {
	         con=ds.getConnection();
	         
	         String sql="select * from travel_information order by travel_no ";
	         pstmt=con.prepareStatement(sql);
	         rs=pstmt.executeQuery();
	         
	         while(rs.next()) {
	            travel temp = new travel();
	            temp.setTravel_no(rs.getInt("travel_no"));
	            temp.setTravel_address(rs.getString("travel_address"));
	            temp.setTravel_content(rs.getString("travel_content"));
	            temp.setTravel_location(rs.getString("travel_location"));
	            temp.setTravel_name(rs.getString("travel_name"));
	            temp.setTravel_tema(rs.getString("travel_tema"));
	            list.add(temp);
	         }
	      }catch(Exception e) {
	         System.out.println("여행지 리스트 추가 에러"+e.getMessage());
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
	   }//getTraveListdesc end
   
   
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
         
         
         String up = "delete from travel_up where travel_no=? ";
         pstmt = con.prepareStatement(up);
         pstmt.setInt(1, no);
         pstmt.executeUpdate();
         
         pstmt.close();
         
         String down = "delete from travel_down where travel_no=? ";
         pstmt = con.prepareStatement(down);
         pstmt.setInt(1, no);
         pstmt.executeUpdate();
         
         pstmt.close();
         
         String pocket = "delete from pocket where travel_no=? ";
         pstmt = con.prepareStatement(pocket);
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
   
   
   
}//class end

