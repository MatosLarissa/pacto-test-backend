<h1 align="center" style="color:#069bfb;">
  <span style="color:red;">Olá! Seja bem vindo(a) ao Sistema de Recrutamento Interno - Pacto (backend)!</span>
</h1>
<br />

<p align="center">
  <img src="https://i.giphy.com/hkn6aDCzKWA33TMBPq.webp" alt="Logo">
</p>
<h2>Descrição</h2>

<p>O Sistema de Recrutamento Interno da Pacto é uma aplicação web desenvolvida para facilitar o processo de recrutamento interno para os colaboradores da empresa. A aplicação permite aos usuários pesquisar e candidatar-se a vagas internas, passando por filtros de requisitos específicos.</p>
<br />

<h2>✨ Funcionalidades Principais</h2>

<p>O sistema foi meticulosamente projetado para oferecer uma experiência de usuário segura e intuitiva, com funcionalidades como:</p>

<ul>
  <li>Autenticação e autorização de usuários..</li>
  <li>Pesquisa e candidatura a vagas internas.</li>
  <li>Filtros de requisitos específicos para as vagas.</li>
  <li>Gerenciamento de feedbacks de candidatura.</li>
  <li>Gerenciamento de vagas e candidatura.</li>
<li> <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">Swagger</a></li>

</ul>

<br />

<h2>🚀 Tecnologias Empregadas</h2>

<p>Este projeto é uma vitrine da inovação tecnológica, incorporando um arsenal de ferramentas de ponta:</p>

<ul>
    <li><strong>Spring Boot 2.7.1: </strong> Framework para desenvolvimento de aplicações Java.</li>
    <li><strong>Spring Data JPA: </strong> Facilita a implementação de repositórios baseados em JPA.</li>
    <li><strong>Spring Web:</strong> Componentes para construção de aplicativos web.</li>
    <li><strong>Spring DevTools:</strong> Ferramentas de desenvolvimento para facilitar o ciclo de desenvolvimento.</li>
    <li><strong>PostgreSQL:</strong> Banco de dados relacional.</li>
    <li><strong>Project Lombok</strong> Biblioteca para reduzir a verbosidade do código Java.</li>
    <li><strong>Spring Boot Starter Security:</strong> Implementação de segurança baseada em Spring Security.</li>
    <li><strong>Auth0 Java JWT:</strong> Biblioteca para manipulação de JSON Web Tokens (JWT).</li>
    <li><strong>Spring Boot Starter Validation:</strong> Suporte para validação de dados de entrada.</li>
</ul>

<h2>💻 Pré-requisitos</h2>

<p>Antes de começar você vai precisar ter instalado em sua máquina as seguintes ferramentas:

<ul>
  <li> <a href="https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html" target="_blank">JDK 8</a></li>
  <li> <a href="https://maven.apache.org/download.cgi?" target="_blank">Maven</a></li>
  <li> <a href="https://www.postgresql.org/download/" target="_blank">PostgreSQL</a></li>
  <li> <a href="https://www.jetbrains.com/idea/download/?fromIDE=&section=windows" target="_blank">IDE (por exemplo, IntelliJ IDEA, Eclipse)</a></li>
</ul>


<br />

<h2>🚀 Rodando o projeto</h2>

<h3>Clonar o Repositório</h3>
<br />

```bash
$ git clone https://github.com/MatosLarissa/pacto-test-backend.git
```
<h3>Execute a aplicação Spring Boot Docker ou utilizando:</h3>

<h4>
Instalação do Docker e Docker Compose: Certifique-se de que o Docker e o Docker Compose estejam instalados em sua máquina. Caso não estejam, você pode baixá-los e instalá-los a partir dos seguintes links:
</h4>

<br />

<ul>
  <li>Docker: Instalação do Docker:</li>
  <li>Docker Compose: Instalação do Docker Compose</li>
</ul>

<br />

<h4>Construção e execução do Docker Compose: No terminal, navegue até o diretório onde o arquivo docker-compose.yml está localizado e execute o seguinte comando para construir e iniciar os serviços:</h4>

<br />

```bash
$ docker-compose up --build
```

<br />
<h4>
Verificação: Após a execução do comando, o Docker irá baixar as imagens necessárias, construir o serviço do aplicativo e iniciar os serviços definidos.
</h4>
<h5> 
O sistema irá iniciar com um admin padrão
</h5>
<ul>
  <li> <b> email: </b> admin@pacto.com</li>
  <li> <b> senha: </b> 123</li>
</ul>

<h4> 
 Você pode verificar se os serviços estão rodando corretamente acessando a aplicação no navegador em <strong><a href="http://localhost:8080/swagger-ui/index.html" target="_blank">http://localhost:8080/swagger-ui/index.html#/?</a>. </h4>

<h4>Parar os serviços: Quando terminar, você pode parar os serviços do Docker Compose executando o seguinte comando no terminal:</h4>

<br />

```bash
$ docker-compose down
```
<br />

<h3>Importar o Projeto na IDE:</h3>
<br />
<ul>
  <li>Importe o projeto para sua IDE como um projeto Maven existente.</li>
</ul>
<h3>Executar o Projeto:</h3>
<ul>
  <li>Instale as dependências do projeto com o seguinte comando Maven:</li>
</ul>
<br />

```bash
$ mvn install
```
<ul>

<h3>Configurar o Banco de Dados:</h3>
<ul>
  <li>Criar um banco de dados PostgreSQL.</li>
  <li>Atualizar as credenciais do banco de dados no arquivo <b> application.properties</b>.</li>
</ul>

<h3>Importar o Projeto na IDE:</h3>
  <h3>Execute a aplicação Spring Boot pela IDE ou utilizando o seguinte comando Maven:</h3>
</ul>
<br />

```bash
$ mvn spring-boot:run
```

<h3>Acessar a Aplicação:</h3>
<ul>
  <li>A aplicação estará disponível em <strong><a href="http://localhost:8080" target="_blank">http://localhost:8080</a> <strong>.</li>
</ul>


<br />
<h2>🧑‍💻 Desenvolvedora</h2>
<table align="center">
  <tr>
     <td align="center"><a href="https://github.com/MatosLarissa" target="_blank">
      <img src="https://avatars.githubusercontent.com/u/63737673?v=4" width="150px" alt="Larissa Matos"/>
      <br />
      <sub><b>Larissa Matos</b></sub><br/><br/>
      <sub><a href="https://www.linkedin.com/in/larissa-matos-b5aa93127/" target="_blank">Linkedin</a></sub>
      <br />
    </td>
  </tr>
</table>
<br />
<p align="center">
  ⬆️ <a href="#top">Voltar para o topo</a>
</p>
