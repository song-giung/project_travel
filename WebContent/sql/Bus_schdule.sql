select*from tab;
purge recyclebin;

create table bus_Seoul_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Seoul foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Incheon_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Incheon foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Daejeon_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Daejeon foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Busan_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Busan foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Daegu_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Daegu foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Gwangju_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Gwangju foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Ulsan_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Ulsan foreign key(bus_no) 
	references bus_list(bus_no)
);


create table bus_Sejong_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Sejong foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Jeju_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Jeju foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Gyeonggi_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Gyeonggi foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Gangwon_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Gangwon foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Chungbuk_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Chungbuk foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Chungnam_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Chungnam foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Jeonbuk_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Jeonbuk foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Jeonnam_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Jeonnam foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Gyeongbuk_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Gyeongbuk foreign key(bus_no) 
	references bus_list(bus_no)
);

create table bus_Gyeongnam_schedule(
	bus_no			number not null,
	bus_seq			number not null,
	bus_departure	varchar2(10) not null,
	bus_arrival		varchar2(10) not null,
	
	constraint fk_no_Gyeongnam foreign key(bus_no) 
	references bus_list(bus_no)
);



