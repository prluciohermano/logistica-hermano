CREATE TABLE usuario (
id bigint not null UNIQUE,
nome VARCHAR(100) not null UNIQUE,
login VARCHAR(20) not null UNIQUE,
email VARCHAR(50) not null UNIQUE,
senha VARCHAR(200) not null,

primary key (id)
);

insert into usuario (id, nome, login, email, senha) values('1', 'Lúcio Hermano', 'lucio', 'prluciohermano@gmail.com', '2201');
insert into usuario (id, nome, login, email, senha) values('2', 'Tafenis Batista', 'admin', 'tafenisfmb@gmail.com', '2201');
 
