# herois-api  
Criação de uma API Reativa para gerenciamento de herois utilizando 
Spring WebFlux e Banco de dados NoSql dynamoDB. E immplementação 
de testes unitários para camada de controle com Junit.
Projeto realizado para o BootCamp da Digital Innovation One.    

### Requisitos
- Java 15
- Postman  
- Maven 3.6.3
- Spring webflux
- Spring data
- Spring doc
- Spring boot 2.4.3
- Dynamodb
- Junit
- Sl4j
- Reactor
- Lombok
- Mapstruct
- Mapstruct-processor
- Devtools
- Gson
- IDE IntelliJ (Opcional)   

Nota: Para mais detalhes sobre versões e outras dependências
e starts do Spring Boot ver arquivo pom.xml do projeto.

# Estruturas de pastas
## Projeto
- main
  - java
    - builder
    - config
    - constants  
    - controller
    - document 
    - dto
    - exception
    - repository
    - service
    - utils
    - mapper
    - service
    - utils
  - resources 
  
  
## Test
- test

# Getting Started
- Na pasta de instalação do DynamoDB rodar o comando abaixo para subir o banco de dados:
    
  <pre>java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb</pre>

A opção -sharedDb indica que todos os clientes locais do DynamoDB interagirão com o mesmo
conjunto de tabelas, independentemente de sua região e configuração de credenciais.

### Executando os testes
<pre>mvn clean test</pre>

### Executando o projeto no terminal

<pre>mvn spring-boot:run</pre>

### Link da publicação da API no postman
<pre>https://documenter.getpostman.com/view/431683/TzCMe8zf</pre>

## Acessando API na página local do swagger 
<pre>http://localhost:8080/swagger-ui.html</pre>


# Referências
* [Web Reactive](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
* [Link GitHub do projeto da LiveCoding por Kamila Santos](https://github.com/Kamilahsantos/Heroes-SpringWebflux-API)
* [Guide to Spring 5 WebFlux](https://www.baeldung.com/spring-webflux)
