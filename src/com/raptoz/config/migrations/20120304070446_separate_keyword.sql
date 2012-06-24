drop table if exists keyword;

create table user_initial_keyword(
	user_id int not null,
	keyword1 varchar(50) not null,
	keyword2 varchar(50) not null,
	keyword3 varchar(50) not null,
	primary key(user_id)
);

create table user_earned_keyword(
	user_id int not null,
	keyword varchar(50) not null
);

create table every_keyword(
	id int not null auto_increment,
	keyword varchar(50) not null,
	primary key (id),
	unique key (keyword)
);

create table toz_keyword(
	toz_id int not null,
	keyword varchar(50) not null
);