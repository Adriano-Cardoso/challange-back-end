DROP TABLE IF EXISTS tb_despesas;
DROP TABLE IF EXISTS tb_receitas;

CREATE TABLE tb_receitas(
	receita_id SERIAL NOT NULL ,
    descricao CHARACTER VARYING(255),
    valor NUMERIC(12,3),
    data CHARACTER VARYING,
    PRIMARY KEY(receita_id)
);


create table tb_despesas(
    despesas_id SERIAL NOT NULL ,
    descricao CHARACTER VARYING(255),
    valor NUMERIC(12,3),
    data CHARACTER VARYING,
    PRIMARY KEY(despesas_id)
);


create table tb_categoria(
    categoria_id SERIAL NOT NULL ,
    nome_categoria CHARACTER VARYING(255),
    PRIMARY KEY(categoria_id)
);


INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (1, 'Salário mensal ', 3000, '2021-12-07' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (2, '1° parcela do 13° Salário', 1600, '2021-12-01' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (3, '2° parcela do 13° Salário', 1200, '2021-12-20' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (4, 'Salário mensal', 3000, '2022-01-07' );


INSERT INTO  tb_despesas(despesas_id, descricao, valor, data) VALUES (1, 'Compras ', 800, '2021-12-07' );
INSERT INTO  tb_despesas(despesas_id, descricao, valor, data) VALUES (2, 'Aluguel', 800, '2021-12-01' );
INSERT INTO  tb_despesas(despesas_id, descricao, valor, data) VALUES (3, 'Roupa', 100, '2021-12-20' );
INSERT INTO  tb_despesas(despesas_id, descricao, valor, data) VALUES (4, 'Gás', 110, '2022-01-07' );


INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (1, 'Alimentação');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (2, 'Saúde');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (3, 'Moradia');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (4, 'Transporte');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (5, 'Educação');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (6, 'Lazer');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (7, 'Imprevistos');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (8, 'Outras');