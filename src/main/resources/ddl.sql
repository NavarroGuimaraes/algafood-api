create table cities (id bigint not null auto_increment, city_name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table cookeries (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB
create table group_permissions (group_id bigint not null, permission_id bigint not null) engine=InnoDB
create table payment_methods (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) engine=InnoDB
create table permission_groups (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table products (id bigint not null auto_increment, description varchar(255) not null, is_active bit not null, name varchar(255) not null, price decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table restaurant_payment_methods (restaurant_id bigint not null, payment_method_id bigint not null) engine=InnoDB
create table restaurants (id bigint not null auto_increment, address_district varchar(255), address_number varchar(255), address_street varchar(255), address_zip_code varchar(255), created_at datetime not null, delivery_fee decimal(19,2) not null, name varchar(255) not null, updated_at datetime not null, address_city_id bigint, cookery_id bigint, primary key (id)) engine=InnoDB
create table states (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB
create table system_permissions (id bigint not null auto_increment, description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table system_users (id bigint not null auto_increment, created_at datetime(6) not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table users_groups (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table cities add constraint FKsu54e1tlhaof4oklvv7uphsli foreign key (state_id) references states (id)
alter table group_permissions add constraint FK226uvn10urnj2k6puqe8ph1lj foreign key (permission_id) references system_permissions (id)
alter table group_permissions add constraint FKlcasxgwfqudpycn80frqn1bpd foreign key (group_id) references permission_groups (id)
alter table products add constraint FK8tiok83nulb20v1i722tlil3a foreign key (restaurant_id) references restaurants (id)
alter table restaurant_payment_methods add constraint FKegjt3k83q1i56kwjbk90rp4mh foreign key (payment_method_id) references payment_methods (id)
alter table restaurant_payment_methods add constraint FK16oyq3ntcbkvxsqd8p6uq4rvb foreign key (restaurant_id) references restaurants (id)
alter table restaurants add constraint FK3f379oqief9wkedokteuppeh2 foreign key (address_city_id) references cities (id)
alter table restaurants add constraint FKhajfkp654xu5ps799au0be4ww foreign key (cookery_id) references cookeries (id)
alter table users_groups add constraint FKfg5rlur0j3q4bhycp6gj0scwe foreign key (group_id) references permission_groups (id)
alter table users_groups add constraint FKpiro5rodnpx3ccwljfe1r0pg1 foreign key (user_id) references system_users (id)
INSERT INTO cookeries (name) VALUES ('Brasileira')
INSERT INTO cookeries (name) VALUES ('Japonesa')
INSERT INTO cookeries (name) VALUES ('Chinesa')
INSERT INTO states (id, name) values (1, 'Minas Gerais')
INSERT INTO states (id, name) values (2, 'São Paulo')
INSERT INTO states (id, name) values (3, 'Pernambuco')
INSERT INTO cities (id, name, state_id) values (1, 'Uberlândia', 1)
INSERT INTO cities (id, name, state_id) values (2, 'Belo Horizonte', 1)
INSERT INTO cities (id, name, state_id) values (3, 'São Paulo', 2)
INSERT INTO cities (id, name, state_id) values (4, 'Campinas', 2)
INSERT INTO cities (id, name, state_id) values (5, 'Recife', 3)
INSERT INTO restaurants (name, delivery_fee, cookery_id, created_at, updated_at) VALUES ('Kami Sushi', 3, 2, utc_timestamp, utc_timestamp)
insert into restaurants (name, delivery_fee, cookery_id, address_city_id, address_zip_code, address_street, address_number, address_district, created_at, updated_at) values ('Zen', 10, 2, 5, '38400-999', 'Rua em Piedade', '6969', 'Piedade', utc_timestamp, utc_timestamp)
INSERT INTO restaurants (name, delivery_fee, cookery_id, created_at, updated_at) VALUES ('Mineiro', 0, 1, utc_timestamp, utc_timestamp)
INSERT INTO restaurants (name, delivery_fee, cookery_id, created_at, updated_at) VALUES ('Okami', 0, 2, utc_timestamp, utc_timestamp)
INSERT INTO restaurants (name, delivery_fee, cookery_id, created_at, updated_at) VALUES ('China Yakisoba', 0, 3, utc_timestamp, utc_timestamp)
INSERT INTO restaurants (name, delivery_fee, cookery_id, address_city_id, address_zip_code, address_street, address_number, address_district, created_at, updated_at) VALUES ('Feijoada do zé', 5.5, 1, 5, '99999-9999', 'Rua Vicente Amorim', '512', 'Água Fria', utc_timestamp, utc_timestamp)
INSERT INTO payment_methods (id, description) values (1, 'Cartão de crédito')
INSERT INTO payment_methods (id, description) values (2, 'Cartão de débito')
INSERT INTO payment_methods (id, description) values (3, 'Dinheiro')
INSERT INTO system_permissions (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
INSERT INTO system_permissions (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
INSERT INTO products (name, description, price, is_active, restaurant_id) VALUES ('Açaí', 'Açaí batido com açúcar', 10, true, 6)
INSERT INTO products (name, description, price, is_active, restaurant_id) VALUES ('Cariocas', '30 cariocas mistos', 30.50, true, 1)
