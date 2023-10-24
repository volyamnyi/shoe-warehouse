create table shoes (
	id serial NOT NULL,
	brand VARCHAR(50),
	model VARCHAR(50),
	size int,
	stock int
);
insert into shoes (brand, model, size, stock) values ('Reebok', 'Fresh Foam', 35, 985);
insert into shoes (brand, model, size, stock) values ('Puma', 'Ultra Boost', 36, 486);
insert into shoes (brand, model, size, stock) values ('Nike', 'Ultra Boost', 37, 834);
insert into shoes (brand, model, size, stock) values ('Reebok', 'Classic Leather', 38, 799);
insert into shoes (brand, model, size, stock) values ('Adidas', 'Classic Leather', 39, 78);
insert into shoes (brand, model, size, stock) values ('Puma', 'Fresh Foam', 40, 842);
insert into shoes (brand, model, size, stock) values ('New Balance', 'Ultra Boost', 35, 554);
insert into shoes (brand, model, size, stock) values ('New Balance', 'Fresh Foam', 36, 190);
insert into shoes (brand, model, size, stock) values ('Reebok', 'Air Max', 37, 11);
insert into shoes (brand, model, size, stock) values ('Nike', 'Fresh Foam', 38, 145);

create table warehouses (
	id serial NOT NULL,
	name VARCHAR(50),
	location VARCHAR(50)
);
insert into warehouses (name, location) values ('Rippin, Wilderman and Predovic', '268 Green Ridge Terrace');
insert into warehouses (name, location) values ('Gleichner-Kuhic', '3 Lakeland Place');
insert into warehouses (name, location) values ('Weissnat and Sons', '5 Del Mar Place');
insert into warehouses (name, location) values ('Boyer, Willms and Boehm', '4074 Sage Road');
insert into warehouses (name, location) values ('Swaniawski and Sons', '368 Kedzie Point');
insert into warehouses (name, location) values ('Berge-Bechtelar', '2 Sherman Court');
insert into warehouses (name, location) values ('Howell, Ebert and Abbott', '325 Erie Pass');
insert into warehouses (name, location) values ('Morissette-Hettinger', '71952 Nelson Junction');
insert into warehouses (name, location) values ('Bogan, Gerhold and Daugherty', '237 Granby Pass');
insert into warehouses (name, location) values ('Kiehn, Stark and Cronin', '6 Almo Circle');
