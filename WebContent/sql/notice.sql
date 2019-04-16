drop table notice;

create table notice(
	notice_no		number	primary key,
	notice_title	varchar2(100) not null,
	notice_content	clob	not null,
	admin_id		varchar2(20),
	notice_date		date not null,
	constraint fk_admin_id foreign key(admin_id)
	references admin(admin_id)
)

select*from notice;


insert into notice values(1,'1번','1번 내용','admin', sysdate);
insert into notice values(2,'2번','2번 내용','admin', sysdate);
insert into notice values(3,'3번','3번 내용','admin', sysdate);
insert into notice values(4,'4번','4번 내용','admin', sysdate);

select*from (select rownum rnum, notice_no, notice_title,  
notice_content, admin_no, admin_id, notice_date from 
(select*from notice order by notice_no desc)) 
where rnum>=1 and rnum<=3