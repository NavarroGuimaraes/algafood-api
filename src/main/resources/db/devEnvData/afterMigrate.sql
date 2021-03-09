set foreign_key_checks = 0;

TRUNCATE TABLE cities;
TRUNCATE TABLE cookeries;
TRUNCATE TABLE group_permissions;
TRUNCATE TABLE payment_methods;
TRUNCATE TABLE permission_groups;
TRUNCATE TABLE products;
TRUNCATE TABLE restaurant_payment_methods;
TRUNCATE TABLE restaurants;
TRUNCATE TABLE states;
TRUNCATE TABLE system_permissions;
TRUNCATE TABLE system_users;
TRUNCATE TABLE users_groups;

set foreign_key_checks = 1;

INSERT IGNORE INTO cookeries (name) VALUES ('Brasileira');
INSERT IGNORE INTO cookeries (name) VALUES ('Japonesa');
INSERT IGNORE INTO cookeries (name) VALUES ('Chinesa');

INSERT IGNORE INTO states (id, name) values (1, 'Minas Gerais');
INSERT IGNORE INTO states (id, name) values (2, 'São Paulo');
INSERT IGNORE INTO states (id, name) values (3, 'Pernambuco');

INSERT IGNORE INTO cities (id, name, state_id) values (1, 'Uberlândia', 1);
INSERT IGNORE INTO cities (id, name, state_id) values (2, 'Belo Horizonte', 1);
INSERT IGNORE INTO cities (id, name, state_id) values (3, 'São Paulo', 2);
INSERT IGNORE INTO cities (id, name, state_id) values (4, 'Campinas', 2);
INSERT IGNORE INTO cities (id, name, state_id) values (5, 'Recife', 3);

INSERT IGNORE INTO restaurants (name, delivery_fee, cookery_id, created_at, updated_at) VALUES ('Kami Sushi', 3, 2, utc_timestamp, utc_timestamp);
insert IGNORE into restaurants (name, delivery_fee, cookery_id, address_city_id, address_zip_code, address_street, address_number, address_district, created_at, updated_at) values ('Zen', 10, 2, 5, '38400-999', 'Rua em Piedade', '6969', 'Piedade', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO restaurants (name, delivery_fee, cookery_id, created_at, updated_at) VALUES ('Mineiro', 0, 1, utc_timestamp, utc_timestamp);
INSERT IGNORE INTO restaurants (name, delivery_fee, cookery_id, created_at, updated_at) VALUES ('Okami', 0, 2, utc_timestamp, utc_timestamp); 
INSERT IGNORE INTO restaurants (name, delivery_fee, cookery_id, created_at, updated_at) VALUES ('China Yakisoba', 0, 3, utc_timestamp, utc_timestamp);
INSERT IGNORE INTO restaurants (name, delivery_fee, cookery_id, address_city_id, address_zip_code, address_street, address_number, address_district, created_at, updated_at) VALUES ('Feijoada do zé', 5.5, 1, 5, '99999-9999', 'Rua Vicente Amorim', '512', 'Água Fria', utc_timestamp, utc_timestamp);

INSERT IGNORE INTO payment_methods (id, description) values (1, 'Cartão de crédito');
INSERT IGNORE INTO payment_methods (id, description) values (2, 'Cartão de débito');
INSERT IGNORE INTO payment_methods (id, description) values (3, 'Dinheiro');

INSERT IGNORE INTO system_permissions (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT IGNORE INTO system_permissions (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT IGNORE INTO restaurant_payment_methods (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

INSERT IGNORE INTO products (name, description, price, is_active, restaurant_id) VALUES ('Açaí', 'Açaí batido com açúcar', 10, true, 6);
INSERT IGNORE INTO products (name, description, price, is_active, restaurant_id) VALUES ('Cariocas', '30 cariocas mistos', 30.50, true, 1);
INSERT IGNORE INTO products (name, description, price, is_active, restaurant_id) VALUES ('Uramaki de salmão', '10 uramakis de salmão', 12, true, 1);