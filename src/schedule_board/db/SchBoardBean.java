package schedule_board.db;

import java.util.Date;

public class SchBoardBean {
	private int sch_bo_no;
	private String sch_bo_title;
	private String sch_bo_content;
	private Date sch_bo_date;
	private int user_no;
	private int sch_no;
	private Date comm_bo_update;
	
	public Date getComm_bo_update() {
		return comm_bo_update;
	}
	public void setComm_bo_update(Date comm_bo_update) {
		this.comm_bo_update = comm_bo_update;
	}
	public int getSch_bo_no() {
		return sch_bo_no;
	}
	public void setSch_bo_no(int sch_bo_no) {
		this.sch_bo_no = sch_bo_no;
	}
	public String getSch_bo_title() {
		return sch_bo_title;
	}
	public void setSch_bo_title(String sch_bo_title) {
		this.sch_bo_title = sch_bo_title;
	}
	public String getSch_bo_content() {
		return sch_bo_content;
	}
	public void setSch_bo_content(String sch_bo_content) {
		this.sch_bo_content = sch_bo_content;
	}
	public Date getSch_bo_date() {
		return sch_bo_date;
	}
	public void setSch_bo_date(Date sch_bo_date) {
		this.sch_bo_date = sch_bo_date;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getSch_no() {
		return sch_no;
	}
	public void setSch_no(int sch_no) {
		this.sch_no = sch_no;
	}
	
	
	
}
