package schedule_board.db;
/*
    DAO(data access object)�겢�옒�뒪
    -�뜲�씠�꽣 踰좎씠�뒪�� �뿰�룞�븯�뿬 �젅肄붾뱶�쓽 異붽�, �닔�젙, �궘�젣 �옉�뾽�씠 �씠猷⑥뼱吏��뒗 �겢�옒�뒪
    -�뼱�뼡 Action �겢�옒�뒪媛� �샇異쒕릺�뜑�씪�룄 洹몄뿉 �빐�떦�븯�뒗 �뜲�씠�꽣踰좎씠�뒪 �뿰�룞 泥섎━�뒗 DAO �겢�옒�뒪�뿉�꽌 �씠猷⑥뼱吏�寃� �맗�땲�떎.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import comm_board.db.CBBforList;


public class SchBoardDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public SchBoardDAO() {
		try {
			Context init= new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e){
			System.out.println("DB �뿰寃� �떎�뙣 : "+ e);
			return;
		}
	}
	
	public int getListCount() {
		// TODO Auto-generated method stub
		String sql="select count(*) from sch_board";
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

	public List<SBBforList> firstgetBoardList(int page, int limit) {
		// TODO Auto-generated method stub
		System.out.println("firstgetBoardList �뱾�뼱�샂");
		String board_list_sql1="select * from "+ "(select rownum rnum, sch_bo_no, sch_bo_title, sch_bo_content, sch_no, sch_bo_date, "
				+ " user_id, user_type from "
				+ " (select * from sch_board natural join client order by sch_bo_date desc) )where rnum>=? and rnum<= ?";
		
		List<SBBforList> list= new ArrayList<SBBforList>();
								  //�븳�럹�씠吏��떦 10媛쒖뵫 紐⑸줉 1page 2   3   4
		int startrow=(page-1)* limit +1;	//�떆�옉踰덊샇	  1   11  21  31
		int endrow=startrow + limit -1;		//留덉�留됰쾲�샇 10  20  30  40
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(board_list_sql1);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				SBBforList b=new SBBforList();
				b.setSch_bo_no(rs.getInt("sch_bo_no"));
				b.setSch_bo_title(rs.getString("sch_bo_title"));
				b.setSch_bo_content(rs.getString("sch_bo_content"));
				b.setSch_bo_date(rs.getDate("sch_bo_date"));
				b.setUser_id(rs.getString("user_id"));
				b.setUser_type(rs.getInt("user_type"));
				b.setSch_no(rs.getInt("sch_no"));
	
				list.add(b);
			}
			return list;
		}catch(Exception e) {
			System.out.println("getboardlist()�뿉�윭 �엯�땲�떎."+e);
			e.printStackTrace();
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return null;
		
	}
	   
	   public List<SBBforList> getTop3(String types[]){
	      int[] list3;
	      list3=new int[3];
	      List<SBBforList> list= new ArrayList<SBBforList>();
	      int index=0;
	      
	      //상위 3개 sch_bo_no 찾아오는 sql 문
	      String sql="select sch_bo_no, count(sch_bo_no) count from (select * from up natural join client where user_type= ? ";
	      
	      for(int i=1; i<types.length;i++) 
	         sql+=" or user_type= ? ";
	      
	      sql+=" ) group by sch_bo_no order by count desc ";
	      
	      //가져온 sch_bo_no로 찾는 sql문
	      String getlistsql="select * from sch_board natural join client where sch_bo_no=? or sch_bo_no=? or sch_bo_no =? ";
	      
	      try {
	         
	         con=ds.getConnection();
	         pstmt=con.prepareStatement(sql);
	         
	         //checkbox 통해 가져온 types query에 넣어주기
	         
	         for(int i=1; i<=types.length; i++) {
	            System.out.println(types[i-1]);
	            System.out.println(Integer.parseInt(types[i-1]));
	            pstmt.setInt(i, Integer.parseInt(types[i-1]));
	         }
	         
	         
	         rs=pstmt.executeQuery();
	         
	         //상위 3개 가져오기 위해 3번 실행
	         for(int i=0; i<list3.length;i++) {
	            if(rs.next())
	               list3[i]=rs.getInt("sch_bo_no");
	            else
	               list3[i]=0;
	         }
	      
	         pstmt.close();

	         pstmt=con.prepareStatement(getlistsql);
	      
	         //3개 리스트 가져옴
	         for(int i=0; i<list3.length;i++) {
	            if(list3[i]==0)
	               pstmt.setInt(i+1, 0);
	            pstmt.setInt(i+1, list3[i]);
	         }
	         
	         rs=pstmt.executeQuery();
	      
	         while(rs.next()) {
	            SBBforList b=new SBBforList();
	            b.setSch_bo_no(rs.getInt("sch_bo_no"));
	            b.setSch_bo_title(rs.getString("sch_bo_title"));
	            b.setUser_id(rs.getString("user_id"));
	            b.setUser_type(rs.getInt("user_type"));
	            b.setSch_no(rs.getInt("sch_no"));
	            list.add(b);
	         }
	         System.out.println("list3가져옴!");
	         return list;
	         
	      }catch(Exception e) {
	         System.out.println("getTopList3()에러 입니다.");
	         e.printStackTrace();
	         
	      }finally {
	         if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
	         if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
	         if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
	      }
	      return null;
	   }
	   
	   public List<SBBforList> getTop3(){
	      int[] list3;
	      list3=new int[3];
	      List<SBBforList> list= new ArrayList<SBBforList>();
	      int index=0;
	      
	      String sql="select sch_bo_no, count(sch_bo_no) count from (select * from up natural join client ) group by sch_bo_no order by count desc ";
	      
	      String getlistsql="select * from sch_board natural join client where sch_bo_no=? or sch_bo_no=? or sch_bo_no =? ";
	      
	      try {
	         //상위 3개 의 게시판 pk 찾음
	         con=ds.getConnection();
	         pstmt=con.prepareStatement(sql);
	      
	         rs=pstmt.executeQuery();
	         
	         for(int i=0; i<list3.length;i++) {
	            if(rs.next())
	               list3[i]=rs.getInt("sch_bo_no");
	            else
	               list3[i]=0;
	         }
	         /*if(rs.next()) {
	            for(int i=0; i<list3.length;i++) {
	               rs.next();
	               list3[i]=rs.getInt("sch_bo_no");
	            }
	         }*/
	         
	         pstmt.close();
	         pstmt=con.prepareStatement(getlistsql);
	   
	         //3개 리스트 가져옴
	         /*for(int i=0; i<list3.length;i++) {
	            pstmt.setInt(i+1, list3[i]);
	         }*/
	         for(int i=0; i<list3.length;i++) {
	            if(list3[i]==0)
	               pstmt.setInt(i+1, 0);
	            pstmt.setInt(i+1, list3[i]);
	         }
	         
	         rs=pstmt.executeQuery();
	      
	         while(rs.next()) {
	            SBBforList b=new SBBforList();
	            b.setSch_bo_no(rs.getInt("sch_bo_no"));
	            b.setSch_bo_title(rs.getString("sch_bo_title"));
	            b.setUser_id(rs.getString("user_id"));
	            b.setUser_type(rs.getInt("user_type"));
	            b.setSch_no(rs.getInt("sch_no"));
	            list.add(b);
	         }
	         System.out.println("list3가져옴!");
	         return list;
	         
	      }catch(Exception e) {
	         System.out.println("getTopList3()에러 입니다.");
	         e.printStackTrace();
	         
	      }finally {
	         if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
	         if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
	         if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
	      }
	      return null;
	   }
	   
	
	public List<SBBforList> getBoardList(String types[],int page, int limit) {
		System.out.println("types�벐�뒗 method吏꾩엯");
		// TODO Auto-generated method stub
		
		String board_list_sql1="select * from "+ "(select rownum rnum, sch_bo_no, sch_bo_title, sch_bo_content, sch_no, sch_bo_date, "
				+ " user_no, user_id, user_type from "
				+ " (select * from sch_board natural join client where user_type=? ";
		
		//媛쒖닔媛��졇�삤湲�
		String countlistsql="select count(sch_bo_no) from "+ "(select rownum rnum, sch_bo_no from "
				+ " (select * from sch_board natural join client where user_type=? ";
		
		
		for(int i=1; i<types.length;i++) {
			board_list_sql1+=" or user_type=? ";
			countlistsql+=" or user_type=? ";
		}
		
		board_list_sql1+="order by sch_bo_date desc) )where rnum>=? and rnum<= ?";
		countlistsql+=") )";
		
		List<SBBforList> list= new ArrayList<SBBforList>();
		
		
		
		int startrow=(page-1)* limit +1;	//�떆�옉踰덊샇	  1   11  21  31
		int endrow=startrow + limit -1;		//留덉�留됰쾲�샇 10  20  30  40
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
			if(listcount <=10) {
				startrow=1;
				endrow=10;
			}
			
			
			pstmt.close();
			pstmt=con.prepareStatement(board_list_sql1);
			
			for(int i=1; i<=types.length; i++) {
				pstmt.setInt(i, Integer.parseInt(types[i-1]));
			}
			pstmt.setInt(types.length+1, startrow);
			pstmt.setInt(types.length+2, endrow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				SBBforList b=new SBBforList();
				b.setSch_bo_no(rs.getInt("sch_bo_no"));
				b.setSch_bo_title(rs.getString("sch_bo_title"));
				b.setSch_bo_content(rs.getString("sch_bo_content"));
				b.setSch_bo_date(rs.getDate("sch_bo_date"));
				b.setUser_id(rs.getString("user_id"));
				b.setListcount(listcount);
				b.setUser_type(rs.getInt("user_type"));
				b.setSch_no(rs.getInt("sch_no"));
				list.add(b);
				
			}
			
			return list;
		}catch(Exception e) {
			System.out.println("getboardlist()�뿉�윭 �엯�땲�떎."+e);
			e.printStackTrace();
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return null;
		
	}
	

	public boolean boardInsert(SchBoardBean boarddata) {
		// TODO Auto-generated method stub
		int num=0;
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			
			sql="insert into sch_board (sch_bo_no,sch_bo_title, sch_bo_content,user_no,sch_no,sch_bo_date)"
				+" values(sch_bo_no.nextval, ?, ?, ?, ?, sysdate)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, boarddata.getSch_bo_title());
			pstmt.setString(2, boarddata.getSch_bo_content());
			pstmt.setInt(3, boarddata.getUser_no());
			pstmt.setInt(4, boarddata.getSch_no());
			
			result=pstmt.executeUpdate();
			
			if(result==0)
				return false;
			return true;
			
		}catch(Exception e) {
			System.out.println("�씪�젙 寃뚯떆�뙋 boardinsert()�뿉�윭 �엯�땲�떎.");
			e.printStackTrace();
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return false;
	}

	public SBBforList getDetail(int sch_bo_no) {
		// TODO Auto-generated method stub
		String sql="select * from sch_board natural join client where sch_bo_no=?";
		SBBforList bb=new SBBforList();
	try {
		con=ds.getConnection();
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, sch_bo_no);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			bb.setSch_bo_no(rs.getInt("sch_bo_no"));
			bb.setSch_bo_title(rs.getString("sch_bo_title"));
			bb.setSch_bo_content(rs.getString("sch_bo_content"));
			bb.setSch_bo_date(rs.getDate("sch_bo_date"));
			bb.setUser_no(rs.getInt("user_no"));
			bb.setSch_no(rs.getInt("sch_no"));
			bb.setUser_no(rs.getInt("user_no"));
			bb.setUser_id(rs.getString("user_id"));
			bb.setUser_type(rs.getInt("user_type"));
			
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

	public List<SBBforList> getBoardList(String searchindex, String searchcontent, int page, int limit) {
		// TODO Auto-generated method stub
		System.out.println("寃��깋留� �븯�뒗 DAO �뱾�뼱�샂");
		String board_list_sql1="select * from "+ "(select rownum rnum, sch_bo_no, sch_bo_title, sch_bo_content, sch_bo_date, "
				+ " user_no, user_id ,user_type, sch_no from "
				+ " (select * from sch_board natural join client order by sch_bo_date desc) ";
		
		String countlistsql="select count(sch_bo_no) from (select sch_bo_no, sch_bo_content, sch_bo_title, user_id "
				+"from (select * from sch_board natural join client order by sch_bo_date desc) )";

		String aa="%"+searchcontent+"%";
		
		switch(searchindex){
		case "title": searchindex="sch_bo_title"; break;
		case "user": searchindex="user_id"; break;
		case "content": searchindex="sch_bo_content"; break;
 		}
	
		board_list_sql1 +=" where "+searchindex+" like ?";
		board_list_sql1 += " )where rnum>=? and rnum<= ?";
		
		countlistsql+=" where "+searchindex+" like ?";
		List<SBBforList> list= new ArrayList<SBBforList>();
								  //�븳�럹�씠吏��떦 10媛쒖뵫 紐⑸줉 1page 2   3   4
		int startrow=(page-1)* limit +1;	//�떆�옉踰덊샇	  1   11  21  31
		int endrow=startrow + limit -1;		//留덉�留됰쾲�샇 10  20  30  40
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(countlistsql);
			pstmt.setString(1, aa);
			rs=pstmt.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			
			System.out.println("count�븷�삤"+count);
			
			if(count<=10) {
				startrow=1;
				endrow=10;
			}
			pstmt.close();
			pstmt=con.prepareStatement(board_list_sql1);
			pstmt.setString(1, aa);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			System.out.println("sql�씠�뿉�삤"+board_list_sql1);
			
			rs=pstmt.executeQuery();

			while(rs.next()) {
				SBBforList b=new SBBforList();
				b.setSch_bo_no(rs.getInt("sch_bo_no"));
				b.setSch_bo_title(rs.getString("sch_bo_title"));
				b.setSch_bo_content(rs.getString("sch_bo_content"));
				b.setSch_bo_date(rs.getDate("sch_bo_date"));
				b.setUser_id(rs.getString("user_id"));
				b.setUser_type(rs.getInt("user_type"));
				b.setListcount(count);
				b.setSch_no(rs.getInt("sch_no"));
				list.add(b);
			}
			return list;
		}catch(Exception e) {
			System.out.println("searchgetboardlist()�뿉�윭 �엯�땲�떎."+e);
			e.printStackTrace();
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return null;
		
	}
	
	public List<SBBforList> getBoardList(String searchindex, String searchcontent, String types[],int page, int limit) {
		// TODO Auto-generated method stub
		String board_list_sql1="select * from "+ "(select rownum rnum, sch_bo_no, sch_bo_title, sch_bo_content, sch_no, sch_bo_date, "
				+ " user_no, user_id, user_type from "
				+ " (select * from sch_board natural join client where user_type=? ";
		
		String countlistsql="select count(sch_bo_no) from (select sch_bo_no, sch_bo_title, sch_bo_content, user_id from"
				+ " (select * from sch_board natural join client where user_type=? ";
		
		String aa="%"+searchcontent+"%";
		
		switch(searchindex){
		case "title": searchindex="sch_bo_title"; break;
		case "user": searchindex="user_id"; break;
		case "content": searchindex="sch_bo_content"; break;
 		}
		
		for(int i=1; i<types.length;i++) {
			board_list_sql1+=" or user_type=? ";
			countlistsql+=" or user_type=? ";
		}
		
		board_list_sql1+=" order by sch_bo_date desc) ";
		board_list_sql1 +=" where "+searchindex+" like ?";
		board_list_sql1 +=" )where rnum>=? and rnum<= ?";
		
		countlistsql +=" )) where "+searchindex+" like ?";
		
		List<SBBforList> list= new ArrayList<SBBforList>();
								  //�븳�럹�씠吏��떦 10媛쒖뵫 紐⑸줉 1page 2   3   4
		int startrow=(page-1)* limit +1;	//�떆�옉踰덊샇	  1   11  21  31
		int endrow=startrow + limit -1;		//留덉�留됰쾲�샇 10  20  30  40
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
			
			if(listcount<=10) {
				startrow=1;
				endrow=10;
			}
			System.out.println("++listcount�븷�삤 " + listcount);
			
			pstmt=con.prepareStatement(countlistsql);
			pstmt=con.prepareStatement(board_list_sql1);
			for(int i=1; i<=types.length; i++) {
				pstmt.setInt(i, Integer.parseInt(types[i-1]));
			}
			
			pstmt.setString(types.length+1, aa);
			pstmt.setInt(types.length+2, startrow);
			pstmt.setInt(types.length+3, endrow);
			
			System.out.println("++sql�씠�뿉�삤 "+board_list_sql1 );
			rs=pstmt.executeQuery();
			while(rs.next()) {
				SBBforList b=new SBBforList();
				b.setSch_bo_no(rs.getInt("sch_bo_no"));
				b.setSch_bo_title(rs.getString("sch_bo_title"));
				b.setSch_bo_content(rs.getString("sch_bo_content"));
				b.setSch_bo_date(rs.getDate("sch_bo_date"));
				b.setUser_id(rs.getString("user_id"));
				b.setUser_type(rs.getInt("user_type"));
				b.setListcount(listcount);
				b.setSch_no(rs.getInt("sch_no"));
				list.add(b);
				
			}
			return list;
		}catch(Exception e) {
			System.out.println("getboardlist()�뿉�윭 �엯�땲�떎."+e);
			e.printStackTrace();
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}
		return null;
		
	}
	
	/*public boolean boardModify(SchBoardBean boarddata) {
		// TODO Auto-generated method stub
		String sql="update sch_board set sch_bo_title=? , sch_bo_content=? , sch_no=?,  where sch_bo_no=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getSch_bo_title());
			pstmt.setString(2, boarddata.getSch_bo_content());
			pstmt.setInt(3, boarddata.getSch_no());
			pstmt.setInt(4, boarddata.getSch_bo_no());
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
*/
	public int isBoardWriter(int sch_bo_no) {
		// TODO Auto-generated method stub
		String sql="select user_no from sch_board where sch_bo_no=?";
		int userno=0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sch_bo_no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
				userno=rs.getInt(1);
			return userno;
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) { ex.printStackTrace();}
			if(con!=null) try {con.close();}catch(SQLException ex) { ex.printStackTrace();}
		}

		System.out.println("isboardwriter�삤瑜�");
		return userno;
	}


	public boolean boardDelete(int sch_bo_no) {
		// TODO Auto-generated method stub
		String sql="delete from sch_board where sch_bo_no=? ";
		String sql1="delete from sch_coment where sch_bo_no=? "; //�뙎湲��궘�젣
		String sql2="delete from up where sch_bo_no=? "; //�뾽�궘�젣
		String sql3="delete from down where sch_bo_no=? "; //�떎�슫�궘�젣
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql1);
			pstmt.setInt(1, sch_bo_no);
			pstmt.executeUpdate();
			pstmt=con.prepareStatement(sql2);
			pstmt.setInt(1, sch_bo_no);
			pstmt.executeUpdate();
			pstmt=con.prepareStatement(sql3);
			pstmt.setInt(1, sch_bo_no);
			pstmt.executeUpdate();
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sch_bo_no);
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
