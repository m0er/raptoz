drop table if exists user_initial_tag;
drop table if exists user_earned_tag;
drop table if exists every_tag;
drop table if exists toz_tag;
drop table if exists user_tag;

create table user_tag(
	user_id int not null,
	tag varchar(50) not null
);

create table toz_tag(
	toz_id int not null,
	tag varchar(50) not null
);