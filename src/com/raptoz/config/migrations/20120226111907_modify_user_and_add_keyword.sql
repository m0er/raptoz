drop table if exists user;

create table user (
	id int not null auto_increment,
	email varchar(100) not null,
	nickname varchar(30) not null,
	password varchar(50) not null,
	primary key(id, email),
	unique key(email, nickname)
);

INSERT INTO user(email, nickname, password) VALUES
('ethdemor@gmail.com', 'ethdemor', 'test'),
('hagony@gmail.com', 'hagony', 'test'),
('ssong.i.jin@gmail.com', 'ssong', 'test');

drop table if exists keyword;

create table keyword (
	user_id int not null,
	keyword varchar(100) not null
);