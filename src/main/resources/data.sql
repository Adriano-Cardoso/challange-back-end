

INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (1, 'Salário mensal ', 3000, '2021-12-07' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (2, '1° parcela do 13° Salário', 1600, '2021-12-01' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (3, '2° parcela do 13° Salário', 1200, '2021-12-20' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (4, 'Salário mensal', 3000, '2022-01-07' );

INSERT INTO TB_USER(username, email, password_user) VALUES('Admin', 'admin@email.com', '$2a$10$ScxWIRKjc4MAX4czBWFC1.A2BjxMdflbo3dgMmqdY8a.MirR2OPVS');

insert into tb_profile values(1,'ADMIN');

insert into tb_profile values(2,'USER');

insert into TB_USER_PERFIS  values(1,1);


INSERT INTO  tb_despesas(despesas_id, descricao, valor, data, categoria) VALUES (1, 'Compras mercado ', 800, '2021-12-07', 'ALIMENTACAO' );
