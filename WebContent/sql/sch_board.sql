CREATE TABLE SCHEDULE(
	sch_no number, 	--일정 번호(pk)
	user_no number not null, --회원번호(참조)
	start_date date not null, --일정 시작 일자
	end_date date not null, --일정 종료 일자
	sch_title varchar2(45) not null, --일정 제목
	sch_content varchar2(3000) not null, --일정에 대한 내용,메모
	sch_review varchar2(3000) , --일정에대한 후기
	constraint schedule_sch_no_pk primary key(sch_no), --sch_no를 pk로 설정
	constraint schedule_user_no_fk foreign key(user_no) references client(user_no)
	--user_no를 fk로 설정 user 테이블의 user_no 참조하도록
); --생성 완료
select*from schedule;

 create sequence schedule_seq 
 start with 10000
 increment by 1
 
 
 
 --------------------------------------------------
 
 create table sch_detail(
	detail_no number, --세부 일정 번호(pk)
	user_no number not null,-- 회원번호(참조)
	sch_no number not null,	--일정 번호 (참조)
	travel_no number ,--여행 번호(참조)
	festival_no number , --축제 번호(참조)
	det_content varchar2(3000), --세부 일정 내용
	det_start date, --세부 일정 시작 시간(안쓸수도 있음)
	det_end date, --세부 일정 종료 시간(안쓸수도 있음)
	det_date date, --세부 일정 날짜(당일 일정)
	det_title varchar2(300),
	constraint detail_user_no_fk foreign key(user_no) references client(user_no), 
	constraint detail_no_pk primary key(detail_no), --세부 일정 번호 pk로 설정
	constraint detail_sch_no_fk foreign key(sch_no) references schedule(sch_no), --sch_no 참조키로 설정
	constraint detail_travel_no_fk foreign key(travel_no) references travel_information(travel_no), --travel_no 참조키로 설정
	constraint detail_festival_no_fk foreign key(festival_no) references festival_information(festival_no) --festival_no 참조키로 설정	
);
 create sequence sch_detail_seq
 start with 20000
 increment by 1
 
 
 ------------------------------------------------------------
 create table pocket(
	user_no number not null, --fk
	pocket_no number, --pk
	travel_no number , --fk
	festival_no , --fk
	constraint pocket_user_no_fk foreign key(user_no) references client(user_no),
	constraint pocket_no_pk primary key(pocket_no),
	constraint pocket_travel_no_fk foreign key(travel_no) references travel_information(travel_no),
	constraint pocket_festival_no_fk foreign key(festival_no) references festival_information(festival_no)
	)
	
 create sequence pocket_seq
 start with 1000
 increment by 1
 
 
 