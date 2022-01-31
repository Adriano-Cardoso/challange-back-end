create table tb_categoria (categoria_id bigint generated by default as identity, nome_categoria varchar(255) not null, primary key (categoria_id));
create table tb_despesas (despesas_id bigint generated by default as identity, categoria_id bigint, data date not null, descricao varchar(255) not null, valor decimal(19,2) not null, primary key (despesas_id));
create table tb_receitas (receita_id bigint generated by default as identity, data date not null, descricao varchar(255) not null, valor decimal(19,2) not null, primary key (receita_id));
alter table tb_despesas add constraint UK_mgnfye95glo5fxmkus3ger53j unique (categoria_id);
alter table tb_despesas add constraint FKkhqu0p5f2knas7wvhgkkbewye foreign key (categoria_id) references tb_categoria;