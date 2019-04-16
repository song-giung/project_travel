package up_down.db;

import java.util.Date;

public class DBforList {
	int down_no;
	int sch_bo_no;
	int user_no;
	int down_total_count;

	int down_type_count[];

	public int getDown_no() {
		return down_no;
	}

	public void setDown_no(int down_no) {
		this.down_no = down_no;
	}

	public int getSch_bo_no() {
		return sch_bo_no;
	}

	public void setSch_bo_no(int sch_bo_no) {
		this.sch_bo_no = sch_bo_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getDown_total_count() {
		return down_total_count;
	}

	public void setDown_total_count(int down_total_count) {
		this.down_total_count = down_total_count;
	}

	public int[] getDown_type_count() {
		return down_type_count;
	}

	public void setDown_type_count(int[] down_type_count) {
		this.down_type_count = down_type_count;
	}
	
}
