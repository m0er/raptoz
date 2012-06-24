drop table if exists user_initial_keyword;
drop table if exists user_earned_keyword;
drop table if exists every_keyword;
drop table if exists toz_keyword;
drop table if exists toz_tag;
drop table if exists user_initial_tag;
drop table if exists user_earned_tag;
drop table if exists every_tag;

create table user_initial_tag(
	user_id int not null,
	tag1 varchar(50) not null,
	tag2 varchar(50) not null,
	tag3 varchar(50) not null,
	primary key(user_id)
);

create table user_earned_tag(
	user_id int not null,
	tag varchar(50) not null
);

create table every_tag(
	id int not null auto_increment,
	tag varchar(50) not null,
	primary key (id),
	unique key (tag)
);

create table toz_tag(
	toz_id int not null,
	tag varchar(50) not null
);