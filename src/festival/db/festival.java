package festival.db;

import java.util.Date;

public class festival {
	private int festival_no;
	private String festival_name;
	private String startday;
	private String endday;
	private String festival_address;
	private String location;
	private String festival_content;
	
	
	
	
	
	public String getFestival_content() {
		return festival_content;
	}
	public void setFestival_content(String festival_content) {
		this.festival_content = festival_content;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getFestival_no() {
		return festival_no;
	}
	public void setFestival_no(int festival_no) {
		this.festival_no = festival_no;
	}
	public String getFestival_name() {
		return festival_name;
	}
	public void setFestival_name(String festival_name) {
		this.festival_name = festival_name;
	}
	public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	public String getEndday() {
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}
	public String getFestival_address() {
		return festival_address;
	}
	public void setFestival_address(String festival_address) {
		this.festival_address = festival_address;
	}
	
	
		
}
