CREATE TABLE pessoa (
id bigint,
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

primary key (id)
);

insert into pessoa (id, bairro, cep, cidade, comp, cpf, nome, numero, rg, rua, sexo, uf) values('1', 'Capuava', '74450210', 'Goiânia', 'Casa126', '47861932168', 'Lúcio Hermano Batista', '330', '1912528', 'Av Raposo Tavares', 'MASCULINO', 'GO');
 
insert into pessoa (id, bairro, cep, cidade, comp, cpf, nome, numero, rg, rua, sexo, uf) values('2', 'Capuava', '74450210', 'Goiânia', 'Casa126', '50730061191', 'Tafenis Mezenes Batista', '330', '2056562', 'Av Raposo Tavares', 'FEMININO', 'GO');
