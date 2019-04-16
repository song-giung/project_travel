create table user_bus_info(

user_no number,

bus_no number,

bus_start_location varchar2(100),

bus_end_location varchar2(100),

bus_time_cost varchar2(100),

bus_time_departure varchar2(100),

bus_time_arrival varchar2(100))

select * from user_bus_info


select*from bus_list natural join bus_Seoul_schedule where bus_no=18 and bus_seq=4;