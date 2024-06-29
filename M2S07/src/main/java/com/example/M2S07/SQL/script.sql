drop table if exists funcionario;
drop table if exists consulta;
drop table if exists nutricionista;
drop table if exists paciente;
drop table if exists endereco;

create table funcionario (
	id_funcionario bigserial primary key,
	matricula varchar,
	tempo_experiencia int,
	id_endereco bigint
);

create table nutricionista (
	id_nutricionista bigserial primary key,
	crn varchar,
	especialidade varchar,
	telefone varchar,
	nome varchar,
	tempo_experiencia int,
	certificacoes varchar
);

create table paciente (
	id_paciente bigserial primary key,
	nome varchar,
	data_nascimento date,
	cpf varchar,
	telefone varchar,
	email varchar,
	id_endereco bigint
);

create table consulta (
	id_consulta bigserial primary key,
	id_nutricionista bigint,
	id_paciente bigint,
	data_consulta date,
	observacoes varchar,
	hora_consulta time
);

create table endereco (
	id_endereco bigserial primary key,
	logradouro varchar,
	estado varchar,
	cidade varchar,
	numero varchar,
	cep varchar
);

alter table funcionario
add constraint funcionario_id_endereco_fkey
foreign key (id_endereco)
references endereco(id_endereco);

alter table paciente
add constraint paciente_id_endereco_fkey
foreign key (id_endereco)
references endereco(id_endereco);

alter table consulta
add constraint consulta_id_nutricionista_fkey
foreign key (id_nutricionista)
references nutricionista(id_nutricionista);

alter table consulta
add constraint consulta_id_paciente_fkey
foreign key (id_paciente)
references paciente(id_paciente);

insert into endereco (logradouro, estado, cidade, numero, cep)
	values ('Rua Um', 'SP', 'São Paulo', '111', '11111-111'),
	('Rua Dois', 'RJ', 'Rio de Janeiro', '222', '22222-222'),
	('Rua Três', 'PR', 'Curitiba', '333', '33333-333');

insert into funcionario (matricula, tempo_experiencia, id_endereco)
values ('123', 5, 1),
('456', 10, 2),
('789', 15, 3);

insert into nutricionista (crn, especialidade, telefone, nome, tempo_experiencia, certificacoes)
values ('CRN-1/1111', 'Educação alimentar e nutricional', '(44) 9 4444-4444', 'Antônio Alves', 4, 'Graduação'),
('CRN-2/2222', 'Gestão de políticas públicas e programas em alimentação e nutrição', '(55) 9 5555-5555', 'Ana Ferreira', 6, 'Graduação, especialização'),
('CRN-3/3333', 'Nutrição clínica', '(66) 9 6666-6666', 'Francisco Rodrigues', 8, 'Graduação, mestrado');

insert into paciente (nome, data_nascimento, cpf, telefone, email, id_endereco)
values ('Maria da Silva', '1991-01-01', '111.111.111-11', '(11) 9 1111-1111', 'mariadasilva@gmail.com', 1),
('José dos Santos', '1992-02-02', '222.222.222-22', '(22) 9 2222-2222', 'josedossantos@gmail.com', 2),
('João Pereira', '1993-03-03', '333.333.333-33', '(33) 9 3333-3333', 'joaopereira@gmail.com', 3);

insert into consulta (id_nutricionista, id_paciente, data_consulta, observacoes, hora_consulta)
values (1, 1, '2024-01-01', 'Observação 1', '08:00'),
(2, 2, '2024-02-02', 'Observação 2', '09:00'),
(3, 3, '2024-03-03', 'Observação 3', '10:00');

update nutricionista set telefone = '(77) 9 7777-7777' where id_nutricionista = 1;

delete from funcionario where id_funcionario = 3;
delete from consulta where id_consulta = 3;
delete from nutricionista where id_nutricionista = 3;
delete from paciente where id_paciente = 3;
delete from endereco where id_endereco = 3;