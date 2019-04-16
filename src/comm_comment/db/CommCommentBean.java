package comm_comment.db;

import java.util.Date;

public class CommCommentBean {
	private int c_comt_no;
	private String c_comt_cont;
	private int comm_bo_no;
	private Date c_comt_date;
	private int user_no;
	private int c_comt_rev;
	
	public int getC_comt_no() {
		return c_comt_no;
	}
	public void setC_comt_no(int c_comt_no) {
		this.c_comt_no = c_comt_no;
	}
	public String getC_comt_cont() {
		return c_comt_cont;
	}
	public void setC_comt_cont(String c_comt_cont) {
		this.c_comt_cont = c_comt_cont;
	}
	public int getComm_bo_no() {
		return comm_bo_no;
	}
	public void setComm_bo_no(int comm_bo_no) {
		this.comm_bo_no = comm_bo_no;
	}
	public Date getC_comt_date() {
		return c_comt_date;
	}
	public void setC_comt_date(Date c_comt_date) {
		this.c_comt_date = c_comt_date;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getC_comt_rev() {
		return c_comt_rev;
	}
	public void setC_comt_rev(int c_comt_rev) {
		this.c_comt_rev = c_comt_rev;
	}
	
	
}
