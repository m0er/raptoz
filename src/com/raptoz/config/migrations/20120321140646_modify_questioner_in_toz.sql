drop table if exists toz;

create table toz (
	id int not null auto_increment,
	title varchar(50) not null,
	questioner_id int not null,
	description text not null,
	primary key(id)
);