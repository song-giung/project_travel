package schedule.db;

public class DetailBean {
	
	private int sch_no; //일정 번호
	private int user_no;	//회원 번호
	private int detail_no;	//상세 일정 번호
	private int travel_no; //참조키
	private int festival_no; //참조키
	private String det_content; //상세 일정 내용
	private String det_start; //상세 일정 시작시간
	private String det_end;	//상세 일정 종료 시간
	private String det_date; // 상세 일정 날짜
	private String det_title;
	//sch_detail 테이블
	
	
	
	public int getSch_no() {
		return sch_no;
	}
	public void setSch_no(int sch_no) {
		this.sch_no = sch_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getDetail_no() {
		return detail_no;
	}
	public void setDetail_no(int detail_no) {
		this.detail_no = detail_no;
	}
	public int getTravel_no() {
		return travel_no;
	}
	public void setTravel_no(int travel_no) {
		this.travel_no = travel_no;
	}
	public int getFestival_no() {
		return festival_no;
	}
	public void setFestival_no(int festival_no) {
		this.festival_no = festival_no;
	}
	public String getDet_content() {
		return det_content;
	}
	public void setDet_content(String det_content) {
		this.det_content = det_content;
	}
	public String getDet_start() {
		return det_start;
	}
	public void setDet_start(String det_start) {
		this.det_start = det_start;
	}
	public String getDet_end() {
		return det_end;
	}
	public void setDet_end(String det_end) {
		this.det_end = det_end;
	}
	public String getDet_date() {
		return det_date;
	}
	public void setDet_date(String det_date) {
		this.det_date = det_date;
	}
	public String getDet_title() {
		return det_title;
	}
	public void setDet_title(String det_title) {
		this.det_title = det_title;
	}
	
	
	
}
