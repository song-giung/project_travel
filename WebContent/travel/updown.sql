select * from travel_down;


select a.travel_name ,b.travel_img_name,count(c.travel_up_no) 
from TRAVEL_INFORMATION a , TRAVEL_IMAGE b , TRAVEL_UP c
where a.travel_no=b.travel_no
and a.travel_no=c.travel_no
group by count(c.travel_up_no) desc;

create sequence travel_up_seq;
create sequence travel_down_seq;

select * from client;
select * from travel_type;

select count(a.travel_up_no)
from travel_up a,travel_information b , travel_type d
where a.travel_no=b.travel_no
and a.travel_no = d.travel_no
and d.travel_type=1
and a.travel_no=23;


select count(a.travel_down_no)
from travel_down a,travel_information b , travel_type d
where a.travel_no=b.travel_no
and a.travel_no = d.travel_no
and d.travel_type=1
and a.travel_no=23;



insert into travel_up (travel_up_no,travel_no,user_no) values(travel_up_seq.nextval,?,?);
insert into travel_down(travel_down_no,travel_no,user_no) values(travel_down_seq.nextval,?,?);

select * from travel_up;

select * from travel_up where user_no=3;

delete from travel_up where user_no=2 and travel_no=41;


select count(a.travel_up_no)
					from travel_up a,travel_information b , travel_type d 
					where a.travel_no=b.travel_no 
					and a.travel_no = d.travel_no 
					and a.travel_type=1 
					and a.travel_no= 
					
select count(travel_down_no)
from travel_down
where travel_no =? and travel_type=1;

create sequence festival_up_seq;
create sequence festival_down_seq;

select * from client;
