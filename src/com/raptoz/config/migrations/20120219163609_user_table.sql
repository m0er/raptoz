drop table IF EXISTS user;
create table user (
	email varchar(100) not null,
	password varchar(50) not null,
	primary key(email)
);