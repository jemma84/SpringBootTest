drop table if exists person_sector;
drop table if exists person;
drop table if exists sector;

create table person (
    person_id	identity primary key,
    login	varchar(50)	not null,
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
    person_id  varchar(100) not null,
    sector_id int not null,
    foreign key (person_id) references person (person_id),
    foreign key (sector_id) references sector (sector_id),
    constraint pk_person_sector primary key (person_id, sector_id)
);