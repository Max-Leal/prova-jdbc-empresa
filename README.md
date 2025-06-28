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
     SOURCE caminho/para/empresa.sql;
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
