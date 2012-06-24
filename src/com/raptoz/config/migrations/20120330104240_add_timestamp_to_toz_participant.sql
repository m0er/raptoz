drop table if exists toz_participant;
create table toz_participant (
	id int not null auto_increment,
	user_id int not null,
	toz_id int not null,
	comment text not null,
	join_timestamp timestamp default current_timestamp,
	primary key(id)
);