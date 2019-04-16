
drop table client;
drop table admin;
drop sequence client_seq;

select*from CLIENT;
delete from client;
delete from admin;

create sequence client_seq
increment by 1
start with 1;

create table client(
	user_no			number primary key,
	user_id			varchar2(20) unique,
	user_passwd		varchar2(40) not null,
	user_name		varchar2(20) not null,
	user_email		varchar2(50) unique,
	user_type		number not null
)
create table admin(
	admin_no			number primary key,
	admin_id			varchar2(20) unique,
	admin_passwd		varchar2(40) not null,
	admin_name			varchar2(20) not null
)

select*from client;
select*from admin;

insert into client values(client_seq.nextval,'qwer','1234','asdf','asdfasdf',1);
insert into client values(client_seq.nextval,'whrudqh','1234','asdf','asdf',3);

select user_id from client where user_name ='asdf';
insert into admin values(1,'admin','1111','asdf');
update admin set admin_no=10;


