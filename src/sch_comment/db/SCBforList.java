package sch_comment.db;

import java.util.Date;

public class SCBforList {
	private int sch_comt_no;
	private String sch_comt_cont;
	private int sch_bo_no;
	private Date sch_comt_date;
	private int user_no;
	private int sch_comt_rev;
	private String user_id;
	private int count;
	private String user_type;
	
	
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getSch_comt_no() {
		return sch_comt_no;
	}
	public void setSch_comt_no(int sch_comt_no) {
		this.sch_comt_no = sch_comt_no;
	}
	public String getSch_comt_cont() {
		return sch_comt_cont;
	}
	public void setSch_comt_cont(String sch_comt_cont) {
		this.sch_comt_cont = sch_comt_cont;
	}
	public int getSch_bo_no() {
		return sch_bo_no;
	}
	public void setSch_bo_no(int sch_bo_no) {
		this.sch_bo_no = sch_bo_no;
	}
	public Date getSch_comt_date() {
		return sch_comt_date;
	}
	public void setSch_comt_date(Date sch_comt_date) {
		this.sch_comt_date = sch_comt_date;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getSch_comt_rev() {
		return sch_comt_rev;
	}
	public void setSch_comt_rev(int sch_comt_rev) {
		this.sch_comt_rev = sch_comt_rev;
	}
	
	
}
