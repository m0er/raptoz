drop table if exists user;

create table user (
	id int not null auto_increment,
	email varchar(100) not null,
	nickname varchar(30) not null,
	password varchar(50) not null,
	imageurl varchar(100) not null default 'anonymous',
	primary key(id, email),
	unique key(email, nickname, imageurl)
);

insert into user(email, nickname, password) values
('ethdemor@gmail.com', 'ethdemor', 'test'),
('hagony@gmail.com', 'hagony', 'test'),
('ssong.i.jin@gmail.com', 'ssong', 'test');