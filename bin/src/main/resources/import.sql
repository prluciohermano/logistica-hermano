CREATE TABLE tb_pessoa (
id uuid,
nome VARCHAR(100),
cpf VARCHAR(11),
rg VARCHAR(20),
sexo VARCHAR(10),
cep VARCHAR(10),
rua VARCHAR(60),
numero VARCHAR(10),
bairro VARCHAR(60),
comp VARCHAR(150),
cidade VARCHAR(60),
uf VARCHAR(4),
tipospessoa_id bigint,
cargo VARCHAR(20),
data_nasci DATE,
documento oid,
nome_file_documento VARCHAR(255),
tipo_file_documento VARCHAR(50),

primary key (id)
);

CREATE TABLE tb_user (
user_id uuid not null,
password VARCHAR(255) not null,
username VARCHAR(255) not null,

primary key (user_id)
);

CREATE TABLE tb_role (
role_id uuid not null,
role_name VARCHAR(255) not null,

primary key (role_id)
);

CREATE TABLE tb_users_roles (
user_id uuid not null,
role_id uuid not null
);

CREATE TABLE tb_tipopessoa (
id bigint not null,
nome VARCHAR(100) not null,

primary key (id)
);

CREATE TABLE tb_telefone (
id uuid not null,
numero VARCHAR(20) not null,
tipo VARCHAR(20),
pessoa_id uuid,

primary key (id)
);

CREATE TABLE tb_garage_box (
id uuid not null,
cor_car VARCHAR(70),
defeito_car VARCHAR(150),
entrada_car TIMESTAMP,
marca_car VARCHAR(70),
mecanico_car VARCHAR(30),
modelo_car VARCHAR(70),
nome_resp VARCHAR(130),
numero_box VARCHAR(10),
placa_car VARCHAR(7),

primary key (id)
);
	

INSERT INTO public.tb_user(user_id, password, username)	
VALUES ('59a9637f-f063-4ac1-a291-4e4b6e1c461f', '$2a$10$dcVsJUTm16WOuTbon1jvJORxj.tujJIPGvUL.q0x5yt3dcPxTnUtu', 'admin');

INSERT INTO public.tb_user(user_id, password, username)	
VALUES ('5c486c31-3b34-4fff-934a-d590ca4a5183', '$2a$10$dcVsJUTm16WOuTbon1jvJORxj.tujJIPGvUL.q0x5yt3dcPxTnUtu', 'lucio');


INSERT INTO public.tb_role(role_id, role_name)
	VALUES ('657d64d4-d60b-4158-b273-cb5690e6c1f7', 'ROLE_ADMIN');
	
INSERT INTO public.tb_role(role_id, role_name)
	VALUES ('9172cdf4-081d-43d9-a3a4-d02f28b449dc', 'ROLE_USER');



INSERT INTO public.tb_users_roles(user_id, role_id)
	VALUES ('59a9637f-f063-4ac1-a291-4e4b6e1c461f', '657d64d4-d60b-4158-b273-cb5690e6c1f7');
	
INSERT INTO public.tb_users_roles(user_id, role_id)
	VALUES ('5c486c31-3b34-4fff-934a-d590ca4a5183', '9172cdf4-081d-43d9-a3a4-d02f28b449dc');
	
INSERT INTO public.tb_users_roles(user_id, role_id)
	VALUES ('59a9637f-f063-4ac1-a291-4e4b6e1c461f', '9172cdf4-081d-43d9-a3a4-d02f28b449dc');
	
	
INSERT INTO public.tb_tipopessoa(id, nome)
	VALUES (1, 'Funcionário');
	
INSERT INTO public.tb_tipopessoa(id, nome)
	VALUES (2, 'Fornecedor');
	
INSERT INTO public.tb_tipopessoa(id, nome)
	VALUES (3, 'Cliente');

INSERT INTO public.tb_tipopessoa(id, nome)
	VALUES (4, 'Diretor');

INSERT INTO public.tb_tipopessoa(id, nome)
	VALUES (5, 'Vendedor');
	

insert into tb_pessoa pessoa_id, bairro, cep, cidade, comp, cpf, nome, numero, rg, rua, sexo, uf, tipospessoa_id, cargo, data_nasci, documento, nome_file_documento, tipo_file_documento)
 values('69d5028a-408a-4d72-a5d2-402a60f39f82', 'Capuava', '74450210', 'Goiânia', 'Casa126', '47861932168', 'Lúcio Hermano Batista', '330', '1912528', 'Av Raposo Tavares', 'MASCULINO', 'GO', '5', 'VENDEDOR', '1968-12-12', 'NULL', 'NULL', 'NULL');
 
 
insert into tb_pessoa pessoa_id, bairro, cep, cidade, comp, cpf, nome, numero, rg, rua, sexo, uf, tipospessoa_id, cargo, data_nasci, documento, nome_file_documento, tipo_file_documento)
 values('356ba7af-80eb-4208-a0ca-05015ebca861', 'Capuava', '74450210', 'Goiânia', 'Casa126', '50730061191', 'Tafenis Batista', '330', '1912528', 'Av Raposo Tavares', 'MASCULINO', 'GO', '5', 'VENDEDOR', '1968-12-12', 'NULL', 'NULL', 'NULL');
 
 
INSERT INTO public.tb_telefone(id, numero, tipo, pessoa_id)
	VALUES ('1ae58b11-1656-4430-8c90-781ef1398787', '62 999394747', 'Celular', '69d5028a-408a-4d72-a5d2-402a60f39f82'); 
	
INSERT INTO public.tb_telefone(id, numero, tipo, pessoa_id)
	VALUES ('4c100cda-dcec-4b57-99e6-233cb6f42341', '62 999881968', 'Celular', '356ba7af-80eb-4208-a0ca-05015ebca861');
	
	
INSERT INTO public.tb_garage_box(
	id, cor_car, defeito_car, entrada_car, marca_car, mecanico_car, modelo_car, nome_resp, numero_box, placa_car)
	VALUES ('0b0e6ea1-d5ed-4a66-bd3b-2132e1236803', 'Vermelho-Escuro', 'sem defeitos', '2022-07-28 14:28:06.375599', 'Ford', 'Manoel', 'Edge', 'Lúcio Hermano', 'box-01', 'PQW3765');
	
INSERT INTO public.tb_garage_box(
	id, cor_car, defeito_car, entrada_car, marca_car, mecanico_car, modelo_car, nome_resp, numero_box, placa_car)
	VALUES ('6c37025e-8f08-4bc6-9b90-2af88b1d6bf1', 'Branco', 'sem defeitos', '2022-07-28 14:31:30.662299', 'Mercedes', 'Manoel', 'SLE 300', 'Lúcio Hermano', 'box-02', 'PQW3767');
	