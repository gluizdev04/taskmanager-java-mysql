# TaskManager Java + MySQL

Aplicação Java de gerenciamento de tarefas com conexão direta ao banco de dados MySQL via JDBC. Projeto de console focado no aprendizado e prática de JDBC, POO e integração com banco de dados relacional.

### Funcionalidades
Login e cadastro de usuário.
Cadastro de novas tarefas.
Listagem de todas as tarefas.
Edição de tarefas existentes.
Exclusão de tarefas.
Marcar tarefas como "Concluídas" ou "Pendentes".

### Tecnologias Utilizadas
Linguagem: Java 11
Banco de Dados: MySQL 8
Conexão: JDBC (Java Database Connectivity)
Build: Maven

### Como Rodar o Projeto
Siga os passos abaixo para executar a aplicação localmente.

Pré-requisitos:
Java 11 (ou superior) instalado.
MySQL Workbench (ou outro cliente SQL) instalado.
Sua IDE de preferência (ex: IntelliJ IDEA).

Passo a Passo:
1. Clone o repositório:
    ```bash
    git clone [https://github.com/gluizdev04/taskmanager-java-mysql.git](https://github.com/gluizdev04/taskmanager-java-mysql.git)
    ```

2. Configure o Banco de Dados:
    Abra seu MySQL Workbench.
    Crie um novo database (schema) chamado `taskmanager`.
    Abra o arquivo `databasetaskmanager.sql` (que está na raiz do projeto).
    Copie e execute o script SQL dentro do database `taskmanager` para criar as tabelas necessárias.

3. Configure a Conexão:
    Abra o projeto na sua IDE.
    [IMPORTANTE - Ver Nota 1]
    Navegue até a classe `src/main/java/connection/ConnectionFactory.java` (ou onde sua conexão é feita).
    Altere as variáveis `USER` e `PASSWORD` para o seu usuário e senha do MySQL.

4. Execute o Projeto:
    [IMPORTANTE - Ver Nota 2]**
    Encontre a classe principal (ex: `src/main/java/Main.java`).
    Execute o método `main()` para iniciar a aplicação no console.

### 📷 Screenshots (Opcional, mas recomendado)

[IMPORTANTE - Ver Nota 3]

*Coloque aqui 1 ou 2 screenshots da aplicação rodando no console (ex: a tela de login e a lista de tarefas).*

![Tela de Login](link-para-sua-imagem.png)
![Lista de Tarefas](link-para-sua-imagem.png)

---

Feito por **[Luiz Gustavo (gluizdev04)](https://github.com/gluizdev04)**
