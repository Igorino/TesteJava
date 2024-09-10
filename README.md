# Teste de java para um sistema de agendamento de transferências financeiras

Este projeto é uma aplicação teste de agendamento de transferências financeiras, implementada com Spring Boot no back-end e Angular no front-end. O sistema permite aos usuários agendar transferências, calcular taxas de acordo com a tabela fornecida e visualizar extratos de agendamentos.

## Arquitetura

### Back-end

- **Framework**: [Spring Boot](https://spring.io/projects/spring-boot)
    - Utilizado para construir a API RESTful, facilitando o desenvolvimento e configuração do servidor.

- **Linguagem**: [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
    - Escolhida por sua estabilidade, suporte a longo prazo e robustez para desenvolvimento de aplicações empresariais.

- **Persistência**: [H2 Database](https://www.h2database.com/html/main.html)
    - Banco de dados em memória usado para simplificar a configuração e o desenvolvimento sem a necessidade de um banco de dados externo. Ideal para testes e desenvolvimento.

- **Arquitetura**:
    - **Controladores**: Expostos endpoints REST para manipulação de transferências.
    - **Serviços**: Contêm a lógica de negócios, incluindo o cálculo de taxas e validação de transferências.
    - **Repositórios**: Interfaces que extendem `JpaRepository` para acesso a dados.

- **Método de Cálculo de Taxa**:
    - Implementado no serviço `TransferenciaService`, o cálculo é baseado na tabela fornecida, com uma lógica específica para determinar a taxa conforme a data de transferência.

### Front-end

- **Framework**: [Angular 15](https://angular.io/)
    - Escolhido para criar uma interface de usuário interativa e responsiva. Angular é conhecido por sua estrutura robusta e integração fácil com serviços RESTful.

- **Estrutura**:
    - **Componentes**: Para exibir formulários e listas de transferências.
    - **Serviços**: Para consumir a API REST do back-end e gerenciar o estado da aplicação.
    - **Roteamento**: Para navegação entre diferentes vistas da aplicação.

## Instalação e Configuração

### Back-end

1. **Clone o repositório**:

   ```bash
   git clone git@github.com:Igorino/TesteJava.git

2. **Navegue até o diretório do back-end**:

    ```bash
    cd TesteJava/

3. **Compile e execute a aplicação**:

    ```bash
    mvn clean spring-boot:run

- A aplicação estará disponível em http://localhost:8080.

### Front-end

1. **Clone o repositório**:

   ```bash
   git clone git@github.com:Igorino/transferencia-app.git

2. Navegue até o diretório do front-end:

    ```bash
    cd transferencia-app/

3. Instale as dependências:

    ```bash
    npm install

4. Inicie a aplicação Angular:

    ```bash
    ng serve

- A aplicação Angular estará disponível em http://localhost:4200.

### Testes
1. **Back-end**: 
- Para executar os testes unitários da aplicação back-end, utilize o comando Maven:

    ```bash
    mvn test

2. **Front-end**:
- Para executar os testes unitários do Angular, use o comando npm:

    ```bash
    npm test
