package up_down.db;

import java.util.Date;

public class UBforList {
	int up_no;
	int sch_bo_no;
	int user_no;
	int up_total_count;

	int up_type_count[];

	public int getUp_no() {
		return up_no;
	}

	public void setUp_no(int up_no) {
		this.up_no = up_no;
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


	public int getUp_total_count() {
		return up_total_count;
	}

	public void setUp_total_count(int up_total_count) {
		this.up_total_count = up_total_count;
	}

	public int[] getUp_type_count() {
		return up_type_count;
	}

	public void setUp_type_count(int[] up_type_count) {
		this.up_type_count = up_type_count;
	}

}
