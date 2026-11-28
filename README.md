# SISTEMA-JAVA

Sistema desktop (Java Swing) de cadastro de clientes, fornecedores, funcionários e
produtos, com PostgreSQL. Projeto original de Otavio15 (2017), refatorado como
trabalho da disciplina Evolução e Modernização de Software (UCSAL).

## Requisitos
- JDK 17 ou superior
- Maven 3.9 ou superior
- PostgreSQL (o backup do exemplo é do 9.6, e funciona em versões mais novas)

## Banco de dados
1. Restaurar o backup de exemplo (banco DB_OTA):
   pg_restore -C -d postgres BKP
2. Aplicar as restrições (chaves primárias e UNIQUE):
   sql/restricoes.sql

## Configuração
Copie `db.properties.example` para `db.properties` e preencha URL, usuário e senha.
O `db.properties` não vai para o repositório.

## Como rodar
- Testes automatizados: `mvn test`
- Aplicação: executar a classe `view.Menu` pela IDE.

## Estrutura
control (entidades), dao (acesso ao banco), db (conexão), tablemodel, view (telas).

## Telas do Sistema

### Menu Geral
Permite acessar os módulos de **produto**, **funcionários**, **fornecedores** e **clientes**, além do gerenciamento das conexões com o banco de dados.

![Menu Geral](https://raw.githubusercontent.com/Otavio15/Sistema_em_java_2017/master/s_geral.png)

### Gerenciamento de Conexão
Configuração de **URL**, **usuário** e **senha** do banco de dados PostgreSQL.

![Conexão](https://raw.githubusercontent.com/Otavio15/Sistema_em_java_2017/master/s_conexao.png)

### CRUD de Clientes
![Clientes](https://raw.githubusercontent.com/Otavio15/Sistema_em_java_2017/master/s_clientes.png)

### CRUD de Fornecedores
![Fornecedores](https://raw.githubusercontent.com/Otavio15/Sistema_em_java_2017/master/s_fornecedor.png)

### CRUD de Produtos
![Produtos](https://raw.githubusercontent.com/Otavio15/Sistema_em_java_2017/master/s_produtos.png)

### CRUD de Funcionários
![Funcionários](https://raw.githubusercontent.com/Otavio15/Sistema_em_java_2017/master/s_funcionarios.png)