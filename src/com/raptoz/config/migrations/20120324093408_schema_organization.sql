drop table if exists user;
create table user (
	id int not null auto_increment,
	email varchar(100) not null,
	nickname varchar(30) not null,
	password varchar(50) not null,
	profile_image blob not null,
	join_timestamp timestamp default current_timestamp,
	primary key(id, email),
	unique key(email, nickname)
);

drop table if exists comment;

drop table if exists toz;
create table toz (
	id int not null auto_increment,
	title varchar(50) not null,
	questioner_id int not null,
	description text not null,
	create_timestamp timestamp default current_timestamp,
	view_count int not null default 0,
	primary key(id)
);

drop table if exists toz_participant;
create table toz_participant (
	id int not null auto_increment,
	user_id int not null,
	toz_id int not null,
	comment text not null,
	primary key(id)
);
