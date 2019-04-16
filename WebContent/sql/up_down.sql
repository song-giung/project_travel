create table travel_up(
   travel_up_no number,
   travel_no number,
   user_no number,
   travel_type number,
   primary key(travel_up_no),
   foreign key(travel_no) references travel_information(travel_no),
   foreign key(user_no) references client(user_no)
);

create table travel_down(
   travel_down_no number,
   travel_no number,
   user_no number,
   travel_type number,
   primary key(travel_down_no),
   foreign key(travel_no) references travel_information(travel_no),
   foreign key(user_no) references client(user_no)
);

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