drop table if exists comment;

create table comment (
	id int not null auto_increment,
	commenter int not null,
	content text not null,
	primary key(id)
);