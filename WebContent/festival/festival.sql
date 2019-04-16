create table festival_up(
   festival_up_no number,
   festival_no number,
   user_no number,
   user_type number,
   primary key(festival_up_no),
   foreign key(festival_no) references festival_information(festival_no),
   foreign key(user_no) references client(user_no)
);

create table festival_down(
   festival_down_no number,
   festival_no number,
   user_no number,
   user_type number,
   primary key(festival_down_no),
   foreign key(festival_no) references festival_information(festival_no),
   foreign key(user_no) references client(user_no)
);

select * from pocket;