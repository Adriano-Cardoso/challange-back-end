

INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (1, 'Salário mensal ', 3000, '2021-12-07' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (2, '1° parcela do 13° Salário', 1600, '2021-12-01' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (3, '2° parcela do 13° Salário', 1200, '2021-12-20' );
INSERT INTO  tb_receitas(receita_id, descricao, valor, data) VALUES (4, 'Salário mensal', 3000, '2022-01-07' );



INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (1, 'Alimentação');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (2, 'Saúde');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (3, 'Moradia');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (4, 'Transporte');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (5, 'Educação');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (6, 'Lazer');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (7, 'Imprevistos');
INSERT INTO  tb_categoria(categoria_id, nome_categoria) VALUES (8, 'Outras');



INSERT INTO  tb_despesas(despesas_id, descricao, valor, data, categoria_id) VALUES (1, 'Compras mercado ', 800, '2021-12-07', 1 );
