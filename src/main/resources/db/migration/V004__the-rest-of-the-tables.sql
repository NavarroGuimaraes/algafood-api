create table group_permissions (
	group_id bigint not null,
	permission_id bigint not null) 
	engine=InnoDB default charset=utf8;

create table payment_methods (
		id bigint not null auto_increment,
		description varchar(80) not null,
		
		primary key (id) ) 
		engine=InnoDB default charset=utf8;

create table permission_groups (
		id bigint not null auto_increment,
		name varchar(60),

		primary key (id) )
		engine=InnoDB default charset=utf8;

create table products (
		id bigint not null auto_increment,
		description varchar(160) not null,
		is_active bit not null,
		name varchar(80) not null,
		price decimal(19,2) not null,
		restaurant_id bigint not null,

		primary key (id))
		engine=InnoDB default charset=utf8;
	
create table restaurant_payment_methods (
		restaurant_id bigint not null,
		payment_method_id bigint not null)
		engine=InnoDB default charset=utf8;
	
create table restaurants (
		id bigint not null auto_increment,
		address_district varchar(80),
		address_number varchar(80),
		address_street varchar(80),
		address_zip_code varchar(80),
		created_at datetime not null,
		delivery_fee decimal(19,2) not null,
		name varchar(80) not null,
		updated_at datetime not null,
		address_city_id bigint,
		cookery_id bigint, 
		
		primary key (id))
		engine=InnoDB default charset=utf8;
	
create table system_permissions (
		id bigint not null auto_increment,
		description varchar(255) not null,
		name varchar(255) not null,
		
		primary key (id))
		engine=InnoDB default charset=utf8;

create table system_users (
	id bigint not null auto_increment,
	created_at datetime(6) not null,
	email varchar(60) not null,
	name varchar(80) not null,
	password varchar(30) not null,
	
	primary key (id))
	engine=InnoDB default charset=utf8;

create table users_groups (
	user_id bigint not null,
	group_id bigint not null)
	engine=InnoDB default charset=utf8;

alter table group_permissions add constraint group_system_permissions_fk foreign key (permission_id) references system_permissions (id);

alter table group_permissions add constraint group_id_permissions_fk foreign key (group_id) references permission_groups (id);

alter table products add constraint product_restaurant_fk foreign key (restaurant_id) references restaurants (id);

alter table restaurant_payment_methods add constraint payment_payment_methods_fk foreign key (payment_method_id) references payment_methods (id);

alter table restaurant_payment_methods add constraint restaurant_payment_methods_fk foreign key (restaurant_id) references restaurants (id);

alter table restaurants add constraint restaurant_cities_fk foreign key (address_city_id) references cities (id);

alter table restaurants add constraint restaurant_cookeries_fk foreign key (cookery_id) references cookeries (id);

alter table users_groups add constraint user_groups_permissions_fk foreign key (group_id) references permission_groups (id);

alter table users_groups add constraint FKpiro5rodnpx3ccwljfe1r0pg1 foreign key (user_id) references system_users (id);
