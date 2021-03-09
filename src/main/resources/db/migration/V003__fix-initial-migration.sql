create table states (
	id bigint not null auto_increment,
	name varchar(80) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

alter table cities add column state_id bigint not null;

alter table cities add constraint fk_state_city
foreign key (state_id) references states (id);

alter table cities drop column state_name;

alter table cities change city_name name varchar(80) not null;
