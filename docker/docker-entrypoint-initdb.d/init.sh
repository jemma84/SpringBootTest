set -e

export PGPASSWORD="$POSTGRES_PASSWORD"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE USER $TEST_USER WITH PASSWORD '$TEST_PASSWORD';
  CREATE DATABASE $TEST_DB;
  GRANT ALL PRIVILEGES ON DATABASE $TEST_DB TO $TEST_USER;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d "$TEST_DB" <<-EOSQL
   GRANT ALL ON SCHEMA public TO $TEST_USER;
EOSQL
psql -v ON_ERROR_STOP=1 --username "$TEST_USER" -d "$TEST_DB" <<-EOSQL
create table person (
     person_id	serial primary key,
     login	varchar(50)	not null,
     email varchar(50) not null unique,
     password	varchar(100) not null
);

create table sector
(
    sector_id int,
    name varchar(100) not null,
    parent_id int,
    constraint pk_sector_id primary key (sector_id),
    foreign key (parent_id) references sector (sector_id)
);

create table person_sector
(
    person_id  serial not null,
    sector_id int not null,
    foreign key (person_id) references person (person_id),
    foreign key (sector_id) references sector (sector_id),
    constraint pk_person_sector primary key (person_id, sector_id)
);
CREATE EXTENSION IF NOT EXISTS pgcrypto;
insert into person (login, email, password)
values ('bender', 'bender@gmail.com', crypt('1qaz', gen_salt('bf')));
insert into person (login, email, password)
values ('lila', 'lila@gmail.com', crypt('2wsx', gen_salt('bf')));
insert into person (login, email, password)
values ('fry', 'fry@gmail.com', crypt('3edc', gen_salt('bf')));

insert into sector (sector_id, name, parent_id)
values (1, 'Manufacturing', null);
insert into sector (sector_id, name, parent_id)
values (19, 'Construction materials', 1);
insert into sector (sector_id, name, parent_id)
values (18, 'Electronics and Optics', 1);
insert into sector (sector_id, name, parent_id)
values (6, 'Food and Beverage', 1);
insert into sector (sector_id, name, parent_id)
values (342, 'Bakery and confectionery products', 6);
insert into sector (sector_id, name, parent_id)
values (43, 'Beverages', 6);
insert into sector (sector_id, name, parent_id)
values (42, 'Fish and fish products', 6);
insert into sector (sector_id, name, parent_id)
values (40, 'Meat and meat products', 6);
insert into sector (sector_id, name, parent_id)
values (39, 'Milk and dairy products', 6);
insert into sector (sector_id, name, parent_id)
values (437, 'Other', 6);
insert into sector (sector_id, name, parent_id)
values (378, 'Sweets and snack food', 6);
insert into sector (sector_id, name, parent_id)
values (13, 'Furniture', 1);
insert into sector (sector_id, name, parent_id)
values (389, 'Bathroom/sauna', 13);
insert into sector (sector_id, name, parent_id)
values (385, 'Bedroom', 13);
insert into sector (sector_id, name, parent_id)
values (390, 'Children’s room', 13);
insert into sector (sector_id, name, parent_id)
values (98, 'Kitchen', 13);
insert into sector (sector_id, name, parent_id)
values (101, 'Living room', 13);
insert into sector (sector_id, name, parent_id)
values (392, 'Office', 13);
insert into sector (sector_id, name, parent_id)
values (394, 'Other (Furniture)', 13);
insert into sector (sector_id, name, parent_id)
values (341, 'Outdoor', 13);
insert into sector (sector_id, name, parent_id)
values (99, 'Project furniture', 13);
insert into sector (sector_id, name, parent_id)
values (12, 'Machinery', 1);
insert into sector (sector_id, name, parent_id)
values (94, 'Machinery components', 12);
insert into sector (sector_id, name, parent_id)
values (91, 'Machinery equipment/tools', 12);
insert into sector (sector_id, name, parent_id)
values (224, 'Manufacture of machinery', 12);
insert into sector (sector_id, name, parent_id)
values (97, 'Maritime', 12);
insert into sector (sector_id, name, parent_id)
values (271, 'Aluminium and steel workboats', 97);
insert into sector (sector_id, name, parent_id)
values (269, 'Boat/Yacht building', 97);
insert into sector (sector_id, name, parent_id)
values (230, 'Ship repair and conversion', 97);
insert into sector (sector_id, name, parent_id)
values (93, 'Metal structures', 12);
insert into sector (sector_id, name, parent_id)
values (508, 'Other', 12);
insert into sector (sector_id, name, parent_id)
values (227, 'Repair and maintenance service', 12);
insert into sector (sector_id, name, parent_id)
values (11, 'Metalworking', 1);
insert into sector (sector_id, name, parent_id)
values (67, 'Construction of metal structures', 11);
insert into sector (sector_id, name, parent_id)
values (263, 'Houses and buildings', 11);
insert into sector (sector_id, name, parent_id)
values (267, 'Metal products', 11);
insert into sector (sector_id, name, parent_id)
values (542, 'Metal works', 11);
insert into sector (sector_id, name, parent_id)
values (75, 'CNC-machining', 542);
insert into sector (sector_id, name, parent_id)
values (62, 'Forgings, Fasteners', 542);
insert into sector (sector_id, name, parent_id)
values (69, 'Gas, Plasma, Laser cutting', 542);
insert into sector (sector_id, name, parent_id)
values (66, 'MIG, TIG, Aluminum welding', 542);
insert into sector (sector_id, name, parent_id)
values (9, 'Plastic and Rubber', 1);
insert into sector (sector_id, name, parent_id)
values (54, 'Packaging', 9);
insert into sector (sector_id, name, parent_id)
values (556, 'Plastic goods', 9);
insert into sector (sector_id, name, parent_id)
values (559, 'Plastic processing technology', 9);
insert into sector (sector_id, name, parent_id)
values (55, 'Blowing', 559);
insert into sector (sector_id, name, parent_id)
values (57, 'Moulding', 559);
insert into sector (sector_id, name, parent_id)
values (53, 'Plastics welding and processing', 559);
insert into sector (sector_id, name, parent_id)
values (560, 'Plastic profiles', 9);
insert into sector (sector_id, name, parent_id)
values (5, 'Printing', 1);
insert into sector (sector_id, name, parent_id)
values (148, 'Advertising', 5);
insert into sector (sector_id, name, parent_id)
values (150, 'Book/Periodicals printing', 5);
insert into sector (sector_id, name, parent_id)
values (145, 'Labelling and packaging printing', 5);
insert into sector (sector_id, name, parent_id)
values (7, 'Textile and Clothing', 1);
insert into sector (sector_id, name, parent_id)
values (44, 'Clothing', 7);
insert into sector (sector_id, name, parent_id)
values (45, 'Textile', 7);
insert into sector (sector_id, name, parent_id)
values (8, 'Wood', 1);
insert into sector (sector_id, name, parent_id)
values (337, 'Other (Wood)', 8);
insert into sector (sector_id, name, parent_id)
values (51, 'Wooden building materials', 8);
insert into sector (sector_id, name, parent_id)
values (47, 'Wooden houses', 8);
insert into sector (sector_id, name, parent_id)
values (3, 'Other', null);
insert into sector (sector_id, name, parent_id)
values (37, 'Creative industries', 3);
insert into sector (sector_id, name, parent_id)
values (29, 'Energy technology', 3);
insert into sector (sector_id, name, parent_id)
values (33, 'Environment', 3);
insert into sector (sector_id, name, parent_id)
values (2, 'Service', null);
insert into sector (sector_id, name, parent_id)
values (25, 'Business services', 2);
insert into sector (sector_id, name, parent_id)
values (35, 'Engineering', 2);
insert into sector (sector_id, name, parent_id)
values (28, 'Information Technology and Telecommunications', 2);
insert into sector (sector_id, name, parent_id)
values (581, 'Data processing, Web portals, E-marketing', 28);
insert into sector (sector_id, name, parent_id)
values (576, 'Programming, Consultancy', 28);
insert into sector (sector_id, name, parent_id)
values (121, 'Software, Hardware', 28);
insert into sector (sector_id, name, parent_id)
values (122, 'Telecommunications', 28);
insert into sector (sector_id, name, parent_id)
values (22, 'Tourism', 2);
insert into sector (sector_id, name, parent_id)
values (141, 'Translation services', 2);
insert into sector (sector_id, name, parent_id)
values (21, 'Transport and Logistics', 2);
insert into sector (sector_id, name, parent_id)
values (111, 'Air', 21);
insert into sector (sector_id, name, parent_id)
values (114, 'Rail', 21);
insert into sector (sector_id, name, parent_id)
values (112, 'Road', 21);
insert into sector (sector_id, name, parent_id)
values (113, 'Water', 21);
EOSQL
