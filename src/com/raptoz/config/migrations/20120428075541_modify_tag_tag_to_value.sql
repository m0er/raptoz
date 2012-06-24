drop table if exists user_tag;
drop table if exists toz_tag;

create table user_tag(
	id int not null auto_increment,
	user_id int not null,
	value varchar(50) not null,
	primary key(id)
);

create table toz_tag(
	id int not null auto_increment,
	toz_id int not null,
	value varchar(50) not null,
	primary key(id)
);