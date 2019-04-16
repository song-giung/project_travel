drop table board;

create table board(
   board_num      number, 
   user_id         varchar2(30),
   board_pass      varchar2(30),
   board_subject   varchar2(300),    
   board_content   varchar2(4000),     
   board_file      varchar2(50),     
   board_re_ref   number,           
   board_re_lev   number,          
   board_re_seq   number, 
   board_date date,               
   primary key(board_num), 
   FOREIGN KEY(USER_ID) REFERENCES CLIENT(USER_ID)
)


select*from board where board_re_lev=0;
delete from board;


select * from (select rownum rnum, board_num, user_id, 
				 board_subject, board_content, board_file,  board_re_ref, board_re_lev, board_re_seq,
				 board_date from (select * from board  where board_re_lev = 0  order by board_re_ref desc,
				 board_re_seq asc)) where rnum>=1 and rnum<=10

