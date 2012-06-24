drop table if exists user;
create table user (
	id int not null auto_increment,
	email varchar(100) not null,
	nickname varchar(30) not null,
	password varchar(50) not null,
	encode_profile_image text not null,
	join_timestamp timestamp default current_timestamp,
	primary key(id, email),
	unique key(email, nickname)
);