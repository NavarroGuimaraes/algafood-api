alter table orders add column id bigint not null auto_increment primary key first;
alter table orders add constraint order_payment_method_fk foreign key (payment_method_id) references payment_methods (id);
alter table orders add constraint order_restaurant_fk foreign key (restaurant_id) references restaurants (id);
alter table orders add constraint order_user_fk foreign  key (user_id) references system_users (id);
alter table orders add constraint order_city_fk foreign  key (address_city_id) references cities (id);

alter table order_items add column id bigint not null auto_increment primary key first;
alter table order_items add column order_id bigint not null after product_id;
alter table order_items add constraint item_product_fk foreign key (product_id) references products (id);
alter table order_items add constraint order_item_uk unique key (product_id, order_id);