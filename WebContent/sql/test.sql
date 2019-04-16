create table travel_information(
 	travel_no number,
 	travel_name varchar2(40) not null,
 	travel_content varchar2(400) not null,
 	travel_address varchar2(300) not null,
 	travel_location varchar2(50) not null,
 	travel_tema varchar2(50) not null,
 	primary key(travel_no)
 );
 
 create table travel_type(
 	travel_type_no number,
 	travel_type number not null,
 	travel_no,
 	primary key(travel_type_no),
 	foreign key(travel_no) references travel_information(travel_no)
 )
 
 
  create table travel_image(
 	travel_img_no number,
 	travel_img_name varchar2(30) not null,
 	travel_img_type number,
 	travel_no number ,
 	primary key(travel_img_no),
 	foreign key(travel_no) references travel_information(travel_no)
 );
 
 
create table travel_comment(
 	travel_comt_no number,
 	travel_comt_cont varchar2(150) not null,
 	user_no number,
 	travel_no number,
 	primary key (travel_comt_no),
 	foreign key (user_no) references client(user_no),
 	foreign key (travel_no) references travel_information(traveL_no)
 );
 
 
 create sequence travel_image_seq;
create sequence travel_seq; 
create sequence travel_comment_seq;
create sequence travel_type_seq;
create sequence travel_up_seq;
create sequence travel_down_seq;
create sequence festival_up_seq;
create sequence festival_down_seq;
create sequence pocket_no_seq;


select * from TRAVEL_INFORMATION 
where travel_no=18;


select * from travel_image;
insert into travel_image values(travel_image_seq.nextval,?,?,?);
insert into travel_image values(travel_image_seq.nextval,'do',0,20);

select * from travel_image;
delete from travel_image where travel_no=7;
select * from travel_image where travel_no =2 and travel_img_type=1;

select * from travel_type;

select * from FESTIVAL_INFORMATION;
select * from festival_image;
delete from travel_type where travel_no=17;

select * from travel_information;

select B.travel_im

select * from travel_information where travel_tema='�԰Ÿ�';
g_name  from travel_information A,travel_image B where A.
travel_no=B.travel_no and A.travel_tema='�԰Ÿ�' and B.travel_img_type=1;

select * from travel_image;

select * from travel_information;

delete from travel_information where travel_no=21;

ALTER TABLE festival_information

MODIFY (festival_name VARCHAR2(4000));