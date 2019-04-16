create table admin(
	admin_no			number primary key,
	admin_id			varchar2(20) unique,
	admin_passwd		varchar2(40) not null,
	admin_name			varchar2(20) not null
)

create table client(
	user_no			number primary key,
	user_id			varchar2(20) unique,
	user_passwd		varchar2(40) not null,
	user_name		varchar2(20) not null,
	user_email		varchar2(50) unique,
	user_type		number not null
)

create sequence client_seq
increment by 1
start with 1;

insert into admin values(1,'admin','1111','관리자');

create table notice(
	notice_no		number	primary key,
	notice_title	varchar2(100) not null,
	notice_content	clob	not null,
	admin_id		varchar2(20),
	notice_date		date not null,
	constraint fk_admin_id foreign key(admin_id)
	references admin(admin_id)
)

create table bus_list(
	bus_area		varchar2(20) not null,
	bus_no			number primary key,
	bus_linename	varchar2(30) not null,
	bus_start		varchar2(30) not null,
	bus_end			varchar2(30) not null,
	bus_traveltime	varchar2(10) not null,
	bus_cost_ad		number not null,
	bus_cost_st		number not null,
	bus_cost_ch		number not null
)

create sequence bus_no_seq
increment by 1
start with 1;

create table bus_Seoul_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Incheon_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Daejeon_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Busan_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Daegu_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Gwangju_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Ulsan_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Sejong_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Jeju_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Gyeonggi_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Gangwon_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Chungbuk_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Chungnam_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Jeonbuk_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Jeonnam_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Gyeongbuk_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)

create table bus_Gyeongnam_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no foreign key(bus_no) 
	references bus_list(bus_no)
)
	
	
	
	
	
	