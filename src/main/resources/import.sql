-- Este arquivo é executado automaticamente pelo Hibernate/Quarkus na inicialização.
-- É usado para popular o banco de dados com dados de teste para facilitar o desenvolvimento.

-- =================================================================
-- CATÁLOGO DE COMPONENTES
-- Objetivo: Inserir componentes que existem por si só, sem estarem
-- associados a nenhum baixo. Eles são as opções que o usuário poderá escolher.
-- A coluna `baixo_id` fica como NULL para indicar que estão "no catálogo".
-- =================================================================

-- Inserindo Acessórios no catálogo
INSERT INTO Acessorios(tipoAcessorio, marcaAcessorios, material, baixo_id) VALUES (1, 'Gotoh', 'Latão', NULL);
INSERT INTO Acessorios(tipoAcessorio, marcaAcessorios, material, baixo_id) VALUES (2, 'Hipshot', 'Aço', NULL);
INSERT INTO Acessorios(tipoAcessorio, marcaAcessorios, material, baixo_id) VALUES (3, 'Fender', 'Plástico 3-ply', NULL);
INSERT INTO Acessorios(tipoAcessorio, marcaAcessorios, material, baixo_id) VALUES (4, 'Fender', 'Metal', NULL);

-- Inserindo Captadores no catálogo
INSERT INTO Captadores(tipoCaptador, marcaCaptador, posicao, baixo_id) VALUES (1, 'Seymour Duncan', 'Ponte', NULL);
INSERT INTO Captadores(tipoCaptador, marcaCaptador, posicao, baixo_id) VALUES (3, 'EMG', 'Meio', NULL);
INSERT INTO Captadores(tipoCaptador, marcaCaptador, posicao, baixo_id) VALUES (2, 'Music Man', 'Ponte', NULL);


-- =================================================================
-- EXEMPLO DE UM BAIXO JÁ MONTADO
-- Objetivo: Criar um BaixoCustomizado completo, com todas as suas partes já associadas.
-- Isso é útil para testar as funcionalidades de GET, UPDATE e DELETE.
-- =================================================================

-- Passo 1: Criar a "parte" (ConfiguracaoEletronica). Ela será associada ao baixo no próximo passo.
-- Note que ela não tem um `baixo_id`, pois a relação é controlada pelo BaixoCustomizado.
INSERT INTO ConfiguracaoEletronica(id, volume_knobs, tone_knobs, circuito_ativo) VALUES (1, 2, 1, true);

-- Passo 2: Criar o "todo" (BaixoCustomizado) e associar a configuração eletrônica criada acima.
-- A coluna `configuracao_eletronica_id` cria o link da composição.
INSERT INTO BaixoCustomizado(modeloBaseBaixo, corBaixo, priceEstimated, configuracao_eletronica_id) VALUES (1, 1, 4500.00, 1);

