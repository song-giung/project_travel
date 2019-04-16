package qna_board.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB �뿰寃� �떎�뙣 : " + e);
			return;
		}
	}

	public int getListCount() {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board where board_re_lev = 0");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount(); �뿉�윭 : " + ex);
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return x;
	}

	public List<BoardBean> getBoardList(int page, int limit) {

		String board_list_sql = "select * from " + "(select rownum rnum, board_num, user_id,"
				+ " board_subject, board_content, board_file," + " board_re_ref, board_re_lev, board_re_seq,"
				+ " board_date from " + "(select * from board where board_re_lev = 0" + " order by board_re_ref desc,"
				+ " board_re_seq asc)) " + " where rnum>=? and rnum<=?";

		List<BoardBean> list = new ArrayList<BoardBean>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("board_num"));
				board.setUSER_ID(rs.getString("user_id"));
				board.setBOARD_SUBJECT(rs.getString("board_subject"));
				board.setBOARD_CONTENT(rs.getString("board_content"));
				board.setBOARD_FILE(rs.getString("board_file"));
				board.setBOARD_RE_REF(rs.getInt("board_re_ref"));
				board.setBOARD_RE_LEV(rs.getInt("board_re_lev"));
				board.setBOARD_RE_SEQ(rs.getInt("board_re_seq"));
				board.setBOARD_DATE(rs.getDate("board_date"));
				list.add(board);
			}

		} catch (Exception e) {
			System.out.println("getBoardList() �뿉�윭:" + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return list;
	}

	public boolean boardInsert(BoardBean board) {
		int num = 0;
		int result = 0;
		boolean y = false;
		System.out.println("board.getBOARD_FILE() = " + board.getBOARD_FILE());
		try {
			conn = ds.getConnection();

			String max_sql = "select max(board_num) from board";
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}

			pstmt.close();
			String sql = "insert into board values(?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getUSER_ID());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, board.getBOARD_FILE());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			result = pstmt.executeUpdate();

			if (result == 0)
				return false;
			return true;
		} catch (Exception e) {
			System.out.println("getListCount() �뿉�윭:" + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return false;
	}

	public BoardBean getDetail(int num) {
		BoardBean boarddata = new BoardBean();
		try {
			conn = ds.getConnection();
			String sql = "select * from board where BOARD_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boarddata.setBOARD_NUM(rs.getInt("board_num"));
				boarddata.setUSER_ID(rs.getString("user_id"));
				boarddata.setBOARD_PASS(rs.getString("board_pass"));
				boarddata.setBOARD_SUBJECT(rs.getString("board_subject"));
				boarddata.setBOARD_CONTENT(rs.getString("board_content"));
				boarddata.setBOARD_FILE(rs.getString("board_file"));
				boarddata.setBOARD_RE_REF(rs.getInt("board_re_ref"));
				boarddata.setBOARD_RE_LEV(rs.getInt("board_re_lev"));
				boarddata.setBOARD_RE_SEQ(rs.getInt("board_re_seq"));
				boarddata.setBOARD_DATE(rs.getDate("board_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return boarddata;
	}

	public String deleteCheck(int num) {
		String BOARD_PASS = "";
		String sql = "select BOARD_PASS from board where BOARD_NUM = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				BOARD_PASS = rs.getString(1);
			} else {
				BOARD_PASS = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return BOARD_PASS;
	}

	public void delete(int num) {

		String sql = "delete from board where BOARD_NUM = ?";
		String sql2 = "delete from board where BOARD_RE_REF = ?";
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
	}

	public String updateCheck(int num) {
		String BOARD_PASS = "";
		String sql = "select BOARD_PASS from board where BOARD_NUM = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				BOARD_PASS = rs.getString(1);
			} else {
				BOARD_PASS = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return BOARD_PASS;
	}

	public void update(BoardBean boarddata) {

		String sql2 = "update board " + "set BOARD_SUBJECT = ?, BOARD_CONTENT = ? " + "where BOARD_NUM = ?";

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql2);

			pstmt.setString(1, boarddata.getBOARD_SUBJECT());
			pstmt.setString(2, boarddata.getBOARD_CONTENT());
			pstmt.setInt(3, boarddata.getBOARD_NUM());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
	}

	public int boardReply(String content, int num) {

		int result = 0;
		String sql = "update BOARD set BOARD_CONTENT= ? where BOARD_NUM= ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, num);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}

		return result;
	}

	public List<BoardBean> getreplyList(int num) {
		List<BoardBean> list = new ArrayList<BoardBean>();
		String sql = "select board_num, user_id, board_content, board_date from board where board_re_ref=? and board_re_lev=1 order by board_re_seq";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();

				board.setBOARD_NUM(rs.getInt("board_num"));
				board.setUSER_ID(rs.getString("user_id"));
				board.setBOARD_CONTENT(rs.getString("board_content"));
				board.setBOARD_DATE(rs.getDate("board_date"));
				list.add(board);
			}

		} catch (Exception e) {
			System.out.println("replyList() �뿉�윭:" + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return list;
	}

	public int ADDReply(BoardBean boarddata) {
		int num = 0;

		String max_sql = "select max(board_num) from board";

		String sql = "update board " + "set BOARD_RE_SEQ=BOARD_RE_SEQ + 1 " + "where BOARD_RE_REF = ? "
				+ "and BOARD_RE_SEQ > ?";

		String sql2 = "insert into board " + "(BOARD_NUM,USER_ID," + " BOARD_CONTENT, BOARD_RE_REF,"
				+ " BOARD_RE_LEV, BOARD_RE_SEQ," + " BOARD_DATE) " + "values(?,?,?,?,?,?,sysdate)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			pstmt.close();

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boarddata.getBOARD_RE_REF());
			pstmt.setInt(2, boarddata.getBOARD_RE_SEQ());
			pstmt.executeUpdate();
			pstmt.close();

			int re_lev = boarddata.getBOARD_RE_LEV();
			re_lev += 1;
			int re_seq = boarddata.getBOARD_RE_SEQ();
			re_seq += 1;

			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.setString(2, boarddata.getUSER_ID());
			pstmt.setString(3, boarddata.getBOARD_CONTENT());
			pstmt.setInt(4, boarddata.getBOARD_RE_REF());
			pstmt.setInt(5, re_lev);
			pstmt.setInt(6, re_seq);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return num;
	}

}
