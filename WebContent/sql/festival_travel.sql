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


 create table festival_information(
 	festival_no number,
 	festival_name varchar2(30) not null,
	festival_content varchar2(4000),
 	festival_startday date,
 	festival_endday date,
 	festival_address varchar2(50) not null,
 	festival_location varchar2(50) not null,
 	primary key (festival_no)
 );
 
 
 
 create table festival_image(
 	festival_img_no number,
 	festival_img_name varchar2(50) not null,
 	festival_img_type char(1) not null,
 	festival_no,
 	primary key(festival_img_no),
 	foreign key(festival_no) references festival_information(festival_no)
 );
 
select  * from travel_image; 
 
 
create sequence travel_image_seq;
create sequence travel_seq; 
create sequence travel_comment_seq;

create sequence festival_seq;
 create sequence festival_image_seq;