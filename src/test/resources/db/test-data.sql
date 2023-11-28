drop table if exists person_sector;
drop table if exists person;
drop table if exists sector;

create table person
(
    person_id serial primary key,
    login     varchar(50)  not null,
    email     varchar(50)  not null,
    password  varchar(100) not null
);

create table sector
(
    sector_id int,
    name      varchar(100) not null,
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

insert into person (login, email, password)
values ('bender', 'bender@gmail.com', '$2a$04$RC1YL4cHzsnk2wYoZkiHEOVUY/5qpZwoi1P0Uc1QS45GzIF9m1znO');

insert into sector (sector_id, name, parent_id)
values (1, 'Manufacturing', null);
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