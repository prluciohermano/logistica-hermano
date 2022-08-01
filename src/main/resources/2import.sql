CREATE TABLE usuario (
id bigint not null UNIQUE,
role VARCHAR(100) not null UNIQUE

primary key (id)
);

insert into role (id, role) values('1', 'ADMIN');
insert into role (id, role) values('2', 'USER');
 
