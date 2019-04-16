drop table bus_list;
drop table bus_schedule;
drop sequence bus_no_seq

create sequence bus_no_seq
increment by 1
start with 1;

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


select*from bus_list;


