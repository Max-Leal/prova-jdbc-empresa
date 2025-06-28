# 📊 Sistema de Gestão da Empresa - Java + JDBC + MySQL

Este projeto é uma aplicação Java backend desenvolvida com JDBC, que realiza operações de CRUD em três entidades: **Pessoa**, **Funcionário** e **Projeto**, conectando-se a um banco de dados MySQL.

---

## 🚀 Funcionalidades

- ✅ Inserção, listagem, atualização, busca e exclusão de Pessoas  
- ✅ Inserção, listagem, atualização, busca e remoção de vínculo de Funcionários  
- ✅ Inserção, listagem, atualização, busca e exclusão de Projetos  
- ✅ Regras de negócio implementadas com validações e mensagens apropriadas  

---

## 🗃️ Estrutura do Banco de Dados

O banco de dados se chama `empresa` e possui 3 tabelas:

- `pessoa (id, nome, email)`
- `funcionario (id, matricula, departamento)`
- `projeto (id, nome, descricao, id_funcionario)`

> O script completo do banco está incluído no projeto em `prova-jdbc-empresa/Empresa.sql`.

---

## 📂 Estrutura do Projeto

```
src/
├── org.example/
│   ├── Main.java
│   ├── daos/
│   │   ├── PessoaDao.java
│   │   ├── FuncionarioDao.java
│   │   └── ProjetoDao.java
│   ├── models/
│   │   ├── Pessoa.java
│   │   ├── Funcionario.java
│   │   └── Projeto.java
│   └── utils/
│       └── Conexao.java
```

---

## ⚙️ Configuração do Banco de Dados

A conexão com o MySQL está sendo feita via JDBC. O arquivo responsável é o Conexao.java:

```java
// Caminho: src/org/example/utils/Conexao.java

String url = "jdbc:mysql://localhost:3306/empresa";
String user = "root"; // INSERIR SEU USUARIO CASO FOI TROCADO
String password = "SENHA_AQUI"; // <<< TROQUE AQUI pela sua senha do MySQL
```

---

## ✅ Requisitos

- Java 11 ou superior  
- MySQL Server instalado e em execução  
- Driver JDBC do MySQL incluído no classpath  
- IDE Java (Eclipse, IntelliJ, VS Code, etc.)

---

## 💻 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/Max-Leal/prova-jdbc-empresa.git
   ```

2. Importe o projeto na sua IDE Java

3. Crie o banco de dados:
   - No MySQL Workbench ou terminal:
```sql
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
```

4. Altere o arquivo `Conexao.java` com sua senha do banco

5. Execute a classe `Main.java`

---

## ✅ Regras de Negócio Implementadas

1. Verifica se a pessoa existe ao cadastrar funcionário.  
2. Impede a criação de projeto sem funcionário existente.  
3. Proíbe exclusão de funcionário com projeto vinculado.  
4. Mensagens claras para falhas de operação.  
5. Confirmações visuais para cada sucesso de operação.
