package comm_board.db;

import java.util.Date;

public class CommBoardBean {
	private int comm_bo_no;
	private String comm_bo_title;
	private String comm_bo_content;
	private Date comm_bo_date;
	private int user_no;
	private String comm_bo_file;
	private Date comm_bo_update;
	
	public Date getComm_bo_update() {
		return comm_bo_update;
	}
	public void setComm_bo_update(Date comm_bo_update) {
		this.comm_bo_update = comm_bo_update;
	}
	public int getComm_bo_no() {
		return comm_bo_no;
	}
	public void setComm_bo_no(int comm_bo_no) {
		this.comm_bo_no = comm_bo_no;
	}
	public String getComm_bo_title() {
		return comm_bo_title;
	}
	public void setComm_bo_title(String comm_bo_title) {
		this.comm_bo_title = comm_bo_title;
	}
	public String getComm_bo_content() {
		return comm_bo_content;
	}
	public void setComm_bo_content(String comm_bo_content) {
		this.comm_bo_content = comm_bo_content;
	}
	public Date getComm_bo_date() {
		return comm_bo_date;
	}
	public void setComm_bo_date(Date comm_bo_date) {
		this.comm_bo_date = comm_bo_date;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getComm_bo_file() {
		return comm_bo_file;
	}
	public void setComm_bo_file(String comm_bo_file) {
		this.comm_bo_file = comm_bo_file;
	}
	
	
}
