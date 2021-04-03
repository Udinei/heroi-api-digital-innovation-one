## Diário de DEV 
Esse arquivo tem como objetivo manter informações  
sobre erros e soluções de problemas encontrados durante o desenvolvimento,
bem como referências utilizadas, dicas e outras anotações consideradas relevantes.

### Sequência das tarefas para criação do projeto
- Criar conta no AWS
- Cria usuario AWS e incluir no grupo de administradores
- Instalação o AWS CLI (local)
- Geração da aplicação no start.spring.io (site)
  - Configuração do pom.xml
- Criação das classes do projeto
- Instalação do banco NoSQL DynamoDB

### Configurando acessos com AWS CLI 
Apos instalar o AWS CLI no terminal usar o comando "aws configure"

Utilizado para dar acesso a maquina e Banco de dados local, com as credenciais fornecidas pelo AWS
No terminal digite: 

<pre>aws configure</pre>

Após executar o comando acima será pedido que forneça os seguintes dados, gerados ao criar
o usuario no AWS (site da Amazon AWS):

AWS Access Key ID [None]:

AWS Secret Access Key [None]:

Default region name [None]:

Default output format [None]: json




## Rodando o Banco DynamoDB
Apos baixar os pacotes de instalação, dentro da pasta de instalação do DynamoDB rodar o comando abaixo para subir o banco 

<pre>java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb</pre>

-sharedDb - Essa opção no comando acima, indica que todos os clientes locais do DynamoDB interagirão com o mesmo
conjunto de tabelas, independentemente de sua região ou configuração de credencial.

# Comando de manipulação do Banco DynamoDB no terminal 

### Lista todas as tabelas 
<pre>aws dynamodb list-tables --endpoint-url http://localhost:8000</pre>

### Lista a estrutura da tabela Heroes_Table
<pre>aws dynamodb describe-table --table-name Heroes_Table --endpoint-url http://localhost:8000 | grep TableStatus</pre>

### Comando para deletar tabela do dynamoDB localmente
<pre>aws dynamodb delete-table --table-name Herois_Table --endpoint-url http://localhost:8000</pre>
### Consulta por id no BD dynamoDB
<pre>aws dynamodb get-item --table-name Herois_Table --key '{"id": {"S": "2"}}' --endpoint-url http://localhost:8000</pre>

### Exemplos de Outras consultas
<pre>aws dynamodb get-item --consistent-read --table-name Herois_Table --key '{ "name": {"S": "Viuva negra"}}'</pre>

### Listar todos os itens da tabela
<pre>aws dynamodb scan --table-name Herois_Table --endpoint-url http://localhost:8000</pre>

Nota: Localmente no final de todos os comandos "aws" usar o parametro:
<pre>--endpoint-url http://localhost:8000</pre> (endereço local do banco DynamoDB)

## Publicando a API no Postman
- 1 - Na tela em "My Workspace" clique na colection que deseja publicar
- 2 - Na proxima tela Edite as informações dos request (opcional)
- 3 - Depois Clique em "Publish"
- 4 - Na próxima tela altere os dados da publicação (opcional)
  - 4.1 E clique no botão "Publish Colletions"

- 5 - O postman vai publicar a API no seu repositorio e gerar um link para acessar a documentação da API
link da minha API ex: https://documenter.getpostman.com/view/431683/TzCMe8zf

- 6 - Apos publicado o link fica disponivel o menu Publish muda para Published, e voce pode copiar
o link para enviar aos seus amigos, despublicar documentacao ou edita-la.

## Exemplos de request da API em várias linguagens.
- 1 - Acesse no postman a tela My Workspace
- 2 - Clique na Colletions desejada
- 3 - Acesse a tela de publicação da API clicando em Publish
- 4 O postman vai abrir no browser a tela para publicação da API
  Na tela de publicação clique em:  Preview Documentation
  
  e veja as formas de fazer os request na linguagem preferida

Nota: Você pode ver no browser esses request acessando o link de publicação da API

# Acessando a API na página do swagger
Localmente no endereço do servidor Netty que roda na porta padrão 8080

http://localhost:8080/swagger-ui.html

# Erros

### Ao executar o projeto no terminal com o comando
<pre>mvn spring-boot:run </pre>

###o Erro 
abaixo é exibido:

Input length =
1 -> [Help 1]

### solução
Inserir o código abaixo no pom.xml do projeto
~~~
<plugins>
...
 <plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-resources-plugin</artifactId>
	<version>3.1.0</version>
 </plugin>
 ...
 
</plugins>
~~~

# Dicas e Lembretes

### Atalhos uteis no IntelliJ 

### Geral
- Alt + 1 - Focar aba de projetos
- Alt + Shift + Insert - Alterna entre modo de seleção de linha/coluna
- Ctrl + Shift + F12 - Expandir a tela
- Alt + Insert - Para criar um novo arquivo
- Alt + Home -  Ir para a barra de navegação

### Snippets de código
- psvm - Criar método main
- sout - System.out.println()
- Ctrl + Alt + C -  Cria constante (precisa estar sobre código que permite atribuição)
- Ctrl + Alt + F - Cria field - propriedade da classe (precisa estar sobre código que permite atribuição)
- Ctrl + Alt + V - Cria variável (precisa estar sobre código que permite atribuição)
- Ctrl + Alt + T - Templates de IF, for, while, etc (precisa selecionar o código antes)
- Ctrl + J - Exibe todos os templates disponíveis

### Edição 
- Ctrl + Y - Apaga a linha inteira
- Ctrl + D - Duplicar linha atual
- Alt + Shift + Setas - Mover linha atual
- Ctrl + Shift + Setas - Mover linha atual mantendo contexto
- Ctrl + G -  Ir para linha

### Buscas  
- Ctrl + Shift + A -  Find Action - mostra todas as ações da IDE em uma lista
- Ctrl + N - Busca rápida de classes
- Ctrl + Shift + N - Busca rápida de arquivos
- Shift & Shift - Search everywhere
- Ctrl + Shift + F - Busca por conteúdo de arquivo
- Alt + F7 -  Find usages - mostra a lista de quem está chamando o método, usando a variável, etc

  
### Inspenção
- Ctrl + B - Inspecionar elemento - go to class, method, declaration, etc
- Ctrl + Alt + O -  Reajustar imports da classe (remove imports não utilizados)
- Ctrl + F12 - Mostra a estrutura do arquivo - por exemplo, exibe métodos de uma classe
- Ctrl + Shift + F6 -  Usar referência de outra classe
- Ctrl + H -  Mostrar hierarquia da classe (classes pais)
- Ctrl + P -  Mostrar parâmetros do método
- Ctrl + Q - Documentação rápida

### Refatoração
- Ctrl + Alt + L - Reformatar código
- Ctrl + Alt + M - Extrair método - transforma código selecionado em método
- Ctrl + Shift + Alt + T - Métodos de refatoração
- Shift + F6 -  Renomeia classe, variável, método, etc


### Geração de código
- Alt + Enter - Intention actions
- Alt + Insert -  Generate shortcut


### Comentários
- Ctrl + / -  Comentar 1 linha de código
- Ctrl + Shift + / -  Comentar várias linhas de códigos

# Referências
* [sobre web-reactive](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)

* [consulta no BD dynamoDB - via AWS CLI](https://docs.aws.amazon.com/pt_br/amazondynamodb/latest/developerguide/getting-started-step-5.html)

* [Link de referência para consultas no banco dynamoDB](https://docs.aws.amazon.com/cli/latest/reference/dynamodb/get-item.html)

* [Lista geral de atalhos do Intellij](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
