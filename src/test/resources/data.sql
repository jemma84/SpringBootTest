insert into person (login, password)
values ('bender', '$2a$04$RC1YL4cHzsnk2wYoZkiHEOVUY/5qpZwoi1P0Uc1QS45GzIF9m1znO');

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
