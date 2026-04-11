-- Inserir fornecedores
INSERT INTO fornecedor (id, name, cnpj) VALUES (1, 'Guitar Pro Supplies', '12.345.678/0001-10');
INSERT INTO fornecedor (id, name, cnpj) VALUES (2, 'MusicGear Brasil', '23.456.789/0001-20');
INSERT INTO fornecedor (id, name, cnpj) VALUES (3, 'Audio Professional', '34.567.890/0001-30');

-- Inserir acessórios (usando apenas INSERT INTO produto com dados completos)
-- Correias
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (1, 'Correia Couro Premium 5cm', 35.50, 50, 1);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (1, 'CORREIA', 'Couro', 5.0);

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (2, 'Correia Nylon 4cm', 22.00, 80, 2);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (2, 'CORREIA', 'Nylon', 4.0);

-- Cabos
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (3, 'Cabo P10 3 Metros', 45.00, 40, 1);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (3, 'CABO', 'Cobre', 3.0);

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (4, 'Cabo P10 5 Metros', 65.00, 30, 2);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (4, 'CABO', 'Cobre', 5.0);

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (17, 'Cabo P10 5 Metros', 65.00, 30, 2);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (17, 'CABO', 'Cobre', 5.0);

-- Palhetas
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (5, 'Palheta Tortex 0.88mm', 8.50, 200, 1);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (5, 'PALHETA', 'Tortex', 0.88);

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (6, 'Palheta Nylon 1.0mm', 6.00, 250, 3);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (6, 'PALHETA', 'Nylon', 1.0);

-- Cases
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (7, 'Case Rígido Premium', 280.00, 15, 2);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (7, 'CASE', 'Plástico Reforçado', 85.0);

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (8, 'Case Soft Bag', 120.00, 25, 1);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (8, 'CASE', 'Nylon', 80.0);

-- Afinador
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (9, 'Afinador Digital Cromático', 95.00, 35, 3);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (9, 'AFINADOR', 'Plástico', 8.0);

-- Suportes
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (10, 'Suporte Estante Madeira', 120.00, 20, 1);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (10, 'SUPORTE', 'Madeira Pinho', 60.0);

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (11, 'Suporte Parede Metal', 85.00, 30, 2);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (11, 'SUPORTE', 'Aço Inoxidável', 45.0);

-- Inserir baixos
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (12, 'Fender Jazz Bass Classic', 4500.00, 5, 1);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (12, 'JAZZ_BASS', 4, 'PRETO');

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (13, 'Fender Precision Bass Deluxe', 3800.00, 3, 2);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (13, 'PRECISION', 4, 'SUNBURST');

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (14, 'Music Man Stingray 4', 5200.00, 2, 3);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (14, 'STINGRAY', 4, 'VERMELHO');

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (15, 'Gibson Thunderbird IV', 4800.00, 4, 1);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (15, 'THUNDERBIRD', 4, 'BRANCO');

INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid) VALUES (16, 'Baixo Custom 5 Cordas', 3500.00, 6, 2);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (16, 'CUSTOM', 5, 'AZUL');

-- -- Inserir pessoas (clientes e luthiers)
-- INSERT INTO pessoa (id, name, email) VALUES (1, 'Ana Silva', 'ana.silva@example.com');
-- INSERT INTO pessoacliente (id, cpf) VALUES (1, '12345678901');

-- INSERT INTO pessoa (id, name, email) VALUES (2, 'Carlos Pereira', 'carlos.pereira@example.com');
-- INSERT INTO pessoacliente (id, cpf) VALUES (2, '98765432100');

-- INSERT INTO pessoa (id, name, email) VALUES (3, 'Luthier João', 'joao.luthier@example.com');
-- INSERT INTO pessoaluthier (id, cnpj) VALUES (3, '12.345.678/0001-99');

-- INSERT INTO pessoa (id, name, email) VALUES (4, 'Luthier Maria', 'maria.luthier@example.com');
-- INSERT INTO pessoaluthier (id, cnpj) VALUES (4, '98.765.432/0001-88');

-- -- Inserir captadores ativos e passivos
-- INSERT INTO captador (id, marca, price, captadorposicao) VALUES (1, 'EMG', 350.00, 'PONTE');
-- INSERT INTO captadorativo (id, possuibateria, possuinamplificador) VALUES (1, true, true);

-- INSERT INTO captador (id, marca, price, captadorposicao) VALUES (2, 'Seymour Duncan', 420.00, 'MEIO');
-- INSERT INTO captadorativo (id, possuibateria, possuinamplificador) VALUES (2, true, false);

-- INSERT INTO captador (id, marca, price, captadorposicao) VALUES (3, 'DiMarzio', 220.00, 'BRANCO');
-- INSERT INTO captadorpassivo (id, resistencia, numerobobinas) VALUES (3, 8.2, 2);

-- INSERT INTO captador (id, marca, price, captadorposicao) VALUES (4, 'Fender', 180.00, 'PONTE');
-- INSERT INTO captadorpassivo (id, resistencia, numerobobinas) VALUES (4, 5.6, 1);

-- -- Inserir configurações eletrônicas e baixos customizados
-- INSERT INTO configuracaoeletronica (id, volumeknobs, toneknobs, circuitoativo) VALUES (1, 2, 2, true);
-- INSERT INTO configuracaoeletronica (id, volumeknobs, toneknobs, circuitoativo) VALUES (2, 1, 1, false);

-- INSERT INTO baixocustomizado (id, baixomodelobase, description, baixocor, estimatedprice, baixostatus, baixocustomizados, pessoaluthier, configuracaoeletronica_id) VALUES (1, 'CUSTOM', 'Baixo custom com captadores ativos e configuração ativa', 'AZUL', 7200.00, 'EM_CONSTRUCAO', 1, 3, 1);
-- INSERT INTO baixocustomizado (id, baixomodelobase, description, baixocor, estimatedprice, baixostatus, baixocustomizados, pessoaluthier, configuracaoeletronica_id) VALUES (2, 'CUSTOM', 'Baixo custom passivo para som vintage', 'BRANCO', 6500.00, 'PROJETANDO', 2, 4, 2);

-- INSERT INTO baixo_captador (baixo_id, captador_id) VALUES (1, 1);
-- INSERT INTO baixo_captador (baixo_id, captador_id) VALUES (1, 2);
-- INSERT INTO baixo_captador (baixo_id, captador_id) VALUES (2, 3);
-- INSERT INTO baixo_captador (baixo_id, captador_id) VALUES (2, 4);

-- -- Inserir pedidos e itens de pedido
-- INSERT INTO pedido (id, data, valortotal, pessoaclienteid) VALUES (1, '2026-04-01', 4535.50, 1);
-- INSERT INTO pedidoitem (id, quantidade, precounitario, produtoid, pedidoid) VALUES (1, 1, 35.50, 1, 1);
-- INSERT INTO pedidoitem (id, quantidade, precounitario, produtoid, pedidoid) VALUES (2, 1, 4500.00, 12, 1);

-- INSERT INTO pedido (id, data, valortotal, pessoaclienteid) VALUES (2, '2026-04-02', 240.00, 2);
-- INSERT INTO pedidoitem (id, quantidade, precounitario, produtoid, pedidoid) VALUES (3, 2, 120.00, 8, 2);
