<h1 align="center" style="font-weight: bold;">Criador de Prompt para Gemini AI</h1>

<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#started">Começando</a> • 
 <a href="#routes">API Endpoints</a> •

</p>

<p align="left">
    <b>
        O projeto foi criado com o intuito de ajudar aos usuários do Gemini AI
        na criação de prompts bem estruturados usando a própria tecnologia do Gemini AI
        facilitando e melhorando a comunicação e eficiência das respostas do modelo Gemini.
        Com esse prompt gerado pela API <span style="color:blue;">Criador de Prompt </span> você pode coeçar a usar o Gemini AI com mais eficiência
    </b>
</p>

<h2 id="tech">💻 Tecnologias</h2>

- java 17
- Spring Boot
- Spring Web
- Lombok
- Google Gemini AI

<h2 id="started">🚀 Começando</h2>

Deve fazer um <a href="#gitClone">Git Clone</a> do projeto para sua máquina e abrir o projeto 
com STS(Spring Tool Suite) ou IDE de sua preferência.
Após abrir o projeto é necessário fazer instalação das dependências no Maven.
Para fazer os testes e requisições use o Postman ou o Insonia.
A requisição deve ser feita com o metodo POST.
Payload para inserir no body da requisição:<br>

```json

{
  "tipo": "string",
  "sobre": "string",
  "configureCriatividade": {
    "temperature": 0,
    "maxOutputTokens": 0,
    "topP": 0,
    "topK": 0  }
}

```

<h3>Prerequisitos</h3>

Prerequisitos necessários para rodar o projeto na sua máquina:

- [Java 17](https://www.azul.com/downloads/?package=jdk#zulu)
- [Git](https://git-scm.com/)
- [Intellij](https://www.jetbrains.com/pt-br/idea/) - Se você é estudante pode solicitar autorização para utilizar

<h3 id="gitClone">Cloning</h3>

Como clonar o projeto.

```bash
git clone git@github.com:claudiocarige/create-prompt-gemini-api.git
```

<h3>Configurando variaveis de ambiente</h2>

No arquivo application.properties você insere:

```application.properties
gemini.url = ${GOOGLE_API_KEY}
```

<h2 id="routes">📍 API Endpoints</h2>

​
| route               | description                                          
|----------------------|-----------------------------------------------------
|<br> <kbd>POST / /api/v1/root/criar-prompt</kbd>     | retorna um prompt [request details](#post-auth-detail)

<h3>POST /api/v1/root/criar-prompt</h3>

**REQUEST**
```json
{
  "tipo": "string",
  "sobre": "string",
  "configureCriatividade": {
    "temperature": 0,
    "maxOutputTokens": 0,
    "topP": 0,
    "topK": 0  }
}

```

**RESPONSE**
```String
Resposta do propt pronto. 
```
<br>
<h4> Agradeço a Alura e a Google pela oportunidade de participar dessa imersão.</h4>