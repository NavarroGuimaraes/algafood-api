create table orders (
	sub_total decimal(6,2) not null,
	delivery_fee decimal(4,2) not null,
	total decimal(6, 2) not null,
	status varchar(10) not null,
	payment_method_id bigint not null,
	restaurant_id bigint not null,
	user_id bigint not null,
	address_zip_code varchar(9) not null,
	address_district varchar(80) not null,
	address_number varchar(5) not null,
	address_street varchar(80) not null,
	address_city_id bigint not null,
	address_complement varchar(160),
	created_at datetime not null,
	confirmed_at datetime,
	canceled_at datetime,
	delivered_at datetime
) engine=InnoDB default charset=utf8;


create table order_items (
	product_id bigint not null,
	amount tinyint not null,
	unit_price decimal(4,2) not null,
	total_price decimal(6, 2) not null,
	observation varchar(255)
) engine=InnoDB default charset=utf8;