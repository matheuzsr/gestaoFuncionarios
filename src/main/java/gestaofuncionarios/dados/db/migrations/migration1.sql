CREATE TABLE funcionario (
id INTEGER primary key AUTOINCREMENT not null,
nome varchar(100) not null,
data_nascimento date not null,
cargo varchar(100),
salario_base double,
salario double,
faltas INTEGER,
distancia_trabalho double,
data_admissao date not null,
funcionario_mes boolean,
deleted_at date
);

CREATE TABLE bonus (
id INTEGER primary key AUTOINCREMENT not null,
tipo varchar(100) not null
);

CREATE TABLE historico_bonus (
id INTEGER primary key AUTOINCREMENT not null,
data_inclusao date,
valor double not null,
id_funcionario int,
id_bonus int,
FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
FOREIGN KEY (id_bonus) REFERENCES bonus(id)
);

CREATE TABLE historico_salario (
id INTEGER primary key AUTOINCREMENT not null,
data_inclusao date,
valor_salario double not null,
valor_bonus double not null,
id_funcionario int,
id_bonus int,
FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);

insert into bonus(tipo) values ('tempo_servico');
insert into bonus(tipo) values ('bonus_assiduidade');
insert into bonus(tipo) values ('funcionario_mes');
