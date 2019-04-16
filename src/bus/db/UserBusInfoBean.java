package bus.db;

public class UserBusInfoBean {
	private int user_no;
	private int bus_no;
	private String bus_start_location;
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getBus_no() {
		return bus_no;
	}
	public void setBus_no(int bus_no) {
		this.bus_no = bus_no;
	}
	public String getBus_start_location() {
		return bus_start_location;
	}
	public void setBus_start_location(String bus_start_location) {
		this.bus_start_location = bus_start_location;
	}
	public String getBus_end_location() {
		return bus_end_location;
	}
	public void setBus_end_location(String bus_end_location) {
		this.bus_end_location = bus_end_location;
	}
	public String getBus_time_cost() {
		return bus_time_cost;
	}
	public void setBus_time_cost(String bus_time_cost) {
		this.bus_time_cost = bus_time_cost;
	}
	public String getBus_time_departure() {
		return bus_time_departure;
	}
	public void setBus_time_departure(String bus_time_departure) {
		this.bus_time_departure = bus_time_departure;
	}
	public String getBus_time_arrival() {
		return bus_time_arrival;
	}
	public void setBus_time_arrival(String bus_time_arrival) {
		this.bus_time_arrival = bus_time_arrival;
	}
	private String bus_end_location;
	private String bus_time_cost;
	private String bus_time_departure;
	private String bus_time_arrival;
}
