
create sequence comm_bo_no
   increment by 1
   start with 1;

create sequence sch_bo_no
   increment by 1
   start with 1;

create sequence c_comt_no
 increment by 1
 start with 1;

create sequence sch_comt_no
increment by 1
start with 1;

create sequence up_no
increment by 1
start with 1;

create sequence down_no
increment by 1
start with 1;

create table commu_board(
comm_bo_no number,
comm_bo_title varchar2(100) not null,
comm_bo_content varchar2(3000) not null,
comm_bo_date date not null,
comm_bo_update date,
user_no number ,
comm_bo_file varchar2(1000),
constraint commu_board_pk primary key(comm_bo_no),
constraint commu_board_fk foreign key(user_no) references client(user_no)
)
-?
create table sch_board(
sch_bo_no number,
sch_bo_title varchar2(100) not null,
sch_bo_content varchar2(3000) not null,
sch_bo_date date not null,
user_no number ,
sch_no number,
constraint sch_board_pk primary key(sch_bo_no),
constraint sch_board_userfk foreign key(user_no) references client(user_no),
constraint sch_board_schfk foreign key(sch_no) references schedule(sch_no)
)

create table up(
	up_no number,
	sch_bo_no number,
	user_no number,
	constraint up_no_pk primary key(up_no),
	constraint up_sch_bo_no foreign key(sch_bo_no) references sch_board	(sch_bo_no),
	constraint up_user_no foreign key(user_no) references client(user_no)
)

create table down(
	down_no number,
	sch_bo_no number,
	user_no number,
	constraint down_no_pk primary key(down_no),
	constraint down_sch_bo_no foreign key(sch_bo_no) references sch_board(sch_bo_no),
	constraint down_user_no foreign key(user_no) references client(user_no)
)

create table comm_coment(
	c_comt_no number,
	c_comt_cont varchar2(100) not null,
	user_no number,
	c_comt_date date not null,
	c_comt_rev number,
	comm_bo_no number,
	constraint c_comt_pk primary key(c_comt_no),
	constraint c_comt_fk foreign key(comm_bo_no) references commu_board(comm_bo_no)
);

create table sch_coment(
	sch_comt_no number,
	sch_comt_cont varchar2(100) not null,
	user_no number,
	sch_comt_date date not null,
	sch_bo_no number,
	constraint sch_comt_pk primary key(sch_comt_no),
	constraint sch_comt_fk foreign key(sch_bo_no) references sch_board(sch_bo_no)
);



