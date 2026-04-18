---
-- 1. FORNECEDORES (ID 100+)
---
INSERT INTO fornecedor (id, name, cnpj, ativo) VALUES (100, 'Guitar Pro Supplies', '12.345.678/0001-10', true);
INSERT INTO fornecedor (id, name, cnpj, ativo) VALUES (101, 'MusicGear Brasil', '23.456.789/0001-20', true);
INSERT INTO fornecedor (id, name, cnpj, ativo) VALUES (102, 'Audio Professional', '34.567.890/0001-30', true);

---
-- 2. ACESSÓRIOS (ID 110+)
---
-- 1. Cabo Monster Bass 6m
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (130, 'Cabo Monster Bass 6m', 280.00, 10, 100, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (130, 'CABO', 'Cobre Premium', 6.0);

-- 2. Correia Comfort Strapp
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (131, 'Correia Comfort G', 220.00, 15, 101, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (131, 'CORREIA', 'Neoprene/Lycra', 10.0);

-- 3. Case Gator TSA Jazz/Precision
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (132, 'Case Gator TSA Bass', 850.00, 4, 100, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (132, 'CASE', 'Polietileno', 120.0);

-- 4. Afinador Boss TU-05 Clip
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (133, 'Afinador Boss Clip', 145.00, 25, 102, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (133, 'AFINADOR', 'Plástico/LCD', 5.0);

-- 5. Suporte Hercules com Trava
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (134, 'Suporte Hercules GS414B', 290.00, 12, 100, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (134, 'SUPORTE', 'Aço/Espuma', 70.0);

-- 6. Kit de Ferramentas Ernie Ball
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (135, 'Kit Ferramentas EB', 320.00, 8, 101, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (135, 'AFINADOR', 'Aço Vanádio', 15.0);

-- 7. Cabo Fender Deluxe 3m Tweed
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (136, 'Cabo Fender Tweed 3m', 160.00, 20, 100, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (136, 'CABO', 'Tecido/Cobre', 3.0);

-- 8. Correia Ibanez Powerpad
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (137, 'Correia Ibanez Preta', 95.00, 30, 100, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (137, 'CORREIA', 'Poliéster Acolchoado', 7.0);

-- 9. Soft Case Mono Vertigo
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (138, 'Mono Vertigo Bass Case', 1400.00, 2, 102, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (138, 'CASE', 'Sharkskin/Borracha', 125.0);

-- 10. Afinador Pedal TC Polytune 3
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (139, 'TC Polytune 3 Pedal', 750.00, 6, 102, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (139, 'AFINADOR', 'Alumínio', 10.0);

-- 11. Cabo Santo Angelo Ninja 5m
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (140, 'Cabo Ninja 5m', 85.00, 50, 101, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (140, 'CABO', 'Cobre LNE', 5.0);

-- 12. Correia Basso de Couro Vintage
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (141, 'Correia Basso Vintage', 110.00, 18, 101, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (141, 'CORREIA', 'Couro Ecológico', 6.0);

-- 13. Kit de Polimento Dunlop 65
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (142, 'Dunlop 65 Care Kit', 130.00, 15, 102, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (142, 'AFINADOR', 'Cera/Spray', 0.0);

-- 14. Suporte Parede Stay
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (143, 'Suporte Parede Stay', 45.00, 40, 101, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (143, 'SUPORTE', 'Aço Carbono', 20.0);

-- 15. Palhetas Jim Dunlop (Pack 12)
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (144, 'Pack 12 Palhetas Dunlop', 65.00, 100, 100, true);
INSERT INTO acessorio (id, acessoriotipo, material, tamanho) VALUES (144, 'PALHETA', 'Delrin', 1.14);

---
-- 3. BAIXOS DE LINHA (ID 200+)
---
-- 1. Ibanez SR500
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (220, 'Ibanez SR500E', 4200.00, 3, 100, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (220, 'STINGRAY', 4, 'SUNBURST');

-- 2. Yamaha BB434
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (221, 'Yamaha BB434 Teal', 3100.00, 5, 101, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (221, 'PRECISION', 4, 'AZUL');

-- 3. Warwick RockBass Corvette
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (222, 'Warwick Corvette Basic', 5500.00, 2, 102, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (222, 'STINGRAY', 4, 'PRETO');

-- 4. Music Man STINGRAY Special 5
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (223, 'Music Man STINGRAY 5HH', 12500.00, 1, 102, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (223, 'STINGRAY', 5, 'SUNBURST');

-- 5. Fender Vintera 60s Jazz Bass
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (224, 'Fender Vintera 60s JB', 8900.00, 2, 100, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (224, 'JAZZ_BASS', 4, 'SUNBURST');

-- 6. Squier Classic Vibe 70s Precision
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (225, 'Squier CV 70s P-Bass', 3200.00, 4, 100, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (225, 'PRECISION', 4, 'BRANCO');

-- 7. Spector Legend 4 Classic
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (226, 'Spector Legend 4', 4800.00, 3, 101, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (226, 'STINGRAY', 4, 'AZUL');

-- 8. Cort C4 Plus
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (227, 'Cort C4 Plus ZBMH', 2900.00, 6, 100, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (227, 'STINGRAY', 4, 'SUNBURST');

-- 9. Jackson David Ellefson CBX IV
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (228, 'Jackson CBX IV Quicksilver', 3700.00, 2, 100, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (228, 'STINGRAY', 4, 'SUNBURST');

-- 10. Schecter Stiletto Extreme-4
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (229, 'Schecter Stiletto Cherry', 4100.00, 3, 102, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (229, 'STINGRAY', 4, 'VERMELHO');

-- 11. Sire Marcus Miller M2 5-String
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (230, 'Sire M2 5-String Black', 3950.00, 4, 101, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (230, 'STINGRAY', 5, 'PRETO');

-- 12. Rickenbacker 4003
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (231, 'Rickenbacker 4003 FG', 18500.00, 1, 102, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (231, 'STINGRAY', 4, 'SUNBURST');

-- 13. Hofner Ignition Violin Bass
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (232, 'Hofner Violin Bass Beetles', 3200.00, 3, 100, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (232, 'STINGRAY', 4, 'SUNBURST');

-- 14. ESP LTD B-204SM
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (233, 'ESP LTD B-204 Ash', 3600.00, 5, 101, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (233, 'STINGRAY', 4, 'SUNBURST');

-- 15. G&L Tribute L-2000
INSERT INTO produto (id, name, price, quantidadeestoque, fornecedorid, ativo) VALUES (234, 'G&L Tribute L-2000 Blue', 5200.00, 2, 102, true);
INSERT INTO baixo (id, baixomodelobase, numerodecordas, baixocor) VALUES (234, 'STINGRAY', 4, 'AZUL');

---
-- 4. PESSOAS (ID 10+)
---
INSERT INTO pessoa (id, name, email, ativo) VALUES (10, 'Ana Silva', 'ana.silva@example.com', true);
INSERT INTO pessoacliente (id, cpf) VALUES (10, '12345678901');

INSERT INTO pessoa (id, name, email, ativo) VALUES (30, 'Luthier João', 'joao.luthier@example.com', true);
INSERT INTO pessoaluthier (id, cnpj) VALUES (30, '12.345.678/0001-99');


---
-- 6. CONFIGURAÇÃO E BAIXOS CUSTOMIZADOS (ID 400+)
---
INSERT INTO configuracaoeletronica (id, volumeknobs, toneknobs, circuitoativo, ativo) VALUES (400, 2, 2, true, true);
INSERT INTO configuracaoeletronica (id, volumeknobs, toneknobs, circuitoativo, ativo) VALUES (401, 6, 4, false, true);

INSERT INTO baixocustomizado (id, baixomodelobase, description, baixocor, estimatedprice, baixostatus, clientebaixoCustomizados, pessoaLuthier, configuracaoeletronica_id, ativo) 
VALUES (890, 'CUSTOM', 'Baixo custom completo', 'AZUL', 7200.00, 'EM_CONSTRUCAO', 10, 30, 400, true);

-- Captador Ativo (ID 300)
INSERT INTO captador (id, marca, price, captadorposicao, ativo) VALUES (300, 'show', 350.00, 'PONTE', true);
INSERT INTO captadorativo (id, possuibateria, possuiamplificador) VALUES (300, true, true);

-- Captador Passivo (ID 310)
INSERT INTO captador (id, marca, price, captadorposicao, ativo) VALUES (310, 'DiMarzio', 220.00, 'BRANCO', true);
INSERT INTO captadorpassivo (id, resistencia, numerobobinas) VALUES (310, 8.2, 2);

-- Captador Passivo (ID 311)
INSERT INTO captador (id, marca, price, captadorposicao, baixocustomizado_id, ativo) VALUES (311, 'Fender', 180.00, 'PONTE', 890, true);
INSERT INTO captadorpassivo (id, resistencia, numerobobinas) VALUES (311, 5.6, 1);

---
-- 7. SINCRONIZAÇÃO DE SEQUENCE
---
-- Isso garante que o próximo ID gerado pelo Hibernate não colida com os manuais
-- SELECT setval('hibernate_sequence', (SELECT max(id) FROM produto));