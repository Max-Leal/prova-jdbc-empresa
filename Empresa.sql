-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS empresa;
USE empresa;

-- Tabela PESSOA
DROP TABLE IF EXISTS pessoa;
CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

-- Tabela FUNCIONARIO
DROP TABLE IF EXISTS funcionario;
CREATE TABLE funcionario (
    id INT PRIMARY KEY,  -- mesma id da pessoa (herança)
    matricula VARCHAR(10) NOT NULL UNIQUE,
    departamento VARCHAR(100) NOT NULL,
    FOREIGN KEY (id) REFERENCES pessoa(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- Tabela PROJETO
DROP TABLE IF EXISTS projeto;
CREATE TABLE projeto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    descricao VARCHAR(200),
    id_funcionario INT NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- INSERTS PESSOA
INSERT INTO pessoa (nome, email) VALUES
('Ana Costa', 'ana.costa@email.com'),
('Bruno Lima', 'bruno.lima@email.com'),
('Carlos Souza', 'carlos.souza@email.com'),
('Daniela Alves', 'daniela.alves@email.com'),
('Eduardo Mendes', 'eduardo.mendes@email.com'),
('Fernanda Rocha', 'fernanda.rocha@email.com'),
('Gabriel Pinto', 'gabriel.pinto@email.com'),
('Helena Duarte', 'helena.duarte@email.com'),
('Igor Castro', 'igor.castro@email.com'),
('Juliana Martins', 'juliana.martins@email.com');

-- INSERTS FUNCIONARIO (com base nos ids da pessoa)
INSERT INTO funcionario (id, matricula, departamento) VALUES
(1, 'F001', 'TI'),
(2, 'F002', 'RH'),
(3, 'F003', 'Marketing'),
(4, 'F004', 'Financeiro'),
(5, 'F005', 'TI'),
(6, 'F006', 'RH'),
(7, 'F007', 'TI'),
(8, 'F008', 'Design'),
(9, 'F009', 'TI'),
(10, 'F010', 'Marketing');

-- INSERTS PROJETO
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES
('Sistema ERP', 'Desenvolvimento de sistema interno', 1),
('Recrutamento 2025', 'Planejamento de novos processos seletivos', 2),
('Campanha Verão', 'Campanha publicitária de verão', 3),
('Relatórios Financeiros', 'Automação de relatórios mensais', 4),
('Portal Web', 'Novo portal para clientes', 5),
('Treinamento RH', 'Capacitação de novos colaboradores', 6),
('API Interna', 'Desenvolvimento de microserviço', 7),
('Manual da Marca', 'Criação do novo manual visual', 8),
('Sistema de Monitoramento', 'Ferramenta de monitoramento em tempo real', 9),
('Social Media', 'Estratégia de mídias sociais', 10);
