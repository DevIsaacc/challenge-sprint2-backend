# 🌊 Challenge Sprint 2 – Backend (Águia Branca / Aguatrans)

API REST desenvolvida em **Java com Spring Boot** e integrada ao **MongoDB Atlas**, para a entrega da Sprint 2 do Challenge.

O sistema apoia a gestão de **ideias de inovação** e **projetos**: operadores registram ideias, que nascem como *pendentes* e aguardam a aprovação de um gestor; os projetos têm investimento, retorno e lucro acompanhados por um **dashboard** com o ROI geral; e há um cadastro de **orientações estratégicas** (campanhas e categorias) às quais ideias e projetos podem ser vinculados.

---

## 📑 Sumário

1. [Tecnologias](#-tecnologias)
2. [Pré-requisitos](#-pré-requisitos)
3. [Como executar (passo a passo)](#-como-executar-passo-a-passo)
4. [Testando a API](#-testando-a-api)
5. [Endpoints](#-endpoints)
6. [Modelos de dados](#-modelos-de-dados)
7. [Estrutura do projeto](#-estrutura-do-projeto)
8. [Solução de problemas](#-solução-de-problemas)
9. [Limitações conhecidas](#-limitações-conhecidas)

---

## 🛠️ Tecnologias

| Tecnologia | Versão / detalhe |
|---|---|
| Java | 17 ou superior |
| Spring Boot | 4.1.1 (`spring-boot-starter-webmvc`) |
| Spring Data MongoDB | via `spring-boot-starter-data-mongodb` |
| Banco de dados | MongoDB Atlas (nuvem) |
| Build | Maven (já incluso via **Maven Wrapper**, não precisa instalar) |
| Extra | Spring Boot DevTools (reinício automático em desenvolvimento) |

---

## ✅ Pré-requisitos

Antes de começar, tenha instalado:

- **JDK 17 ou superior.** Confira com `java -version`.
- **Git**, para clonar o repositório.
- Uma conta gratuita no **[MongoDB Atlas](https://www.mongodb.com/atlas)** (ou um MongoDB local, veja a [alternativa](#alternativa-mongodb-local-ou-variável-de-ambiente)).
- *(Opcional)* **Postman** ou `curl` para testar os endpoints.

> Não é necessário instalar o Maven. O projeto traz o `mvnw` (Maven Wrapper), que baixa a versão certa sozinho na primeira execução.

---

## 🚀 Como executar (passo a passo)

### 1. Clone o repositório

```bash
git clone https://github.com/DevIsaacc/challenge-sprint2-backend.git
cd challenge-sprint2-backend/challenge-sprint2-backend
```

> ⚠️ O projeto Maven é a pasta **de dentro**, a que contém o arquivo `pom.xml`. Todos os comandos abaixo devem ser executados nela. Se for abrir no VS Code, use **File → Open Folder** nessa mesma pasta.

### 2. Prepare o banco no MongoDB Atlas

1. Crie uma conta no MongoDB Atlas e um cluster gratuito (**M0**).
2. Em **Security → Database Access**, clique em **Add New Database User** e crie um usuário e uma senha. Esse é o usuário *do banco*, diferente do login da sua conta no site. Prefira uma senha só com letras e números.
3. Em **Security → Network Access**, clique em **Add IP Address** e libere o seu IP (**Add Current IP Address**). Para testes rápidos, também é possível liberar `0.0.0.0/0`, mas isso **não é recomendado** para uso real.
4. Em **Database → Clusters**, clique em **Connect → Drivers** e copie a string de conexão. Ela tem este formato:
   `mongodb+srv://<db_user>:<db_password>@cluster0.xxxxx.mongodb.net/?appName=...`

O banco (`aguatrans`) e as coleções são criados automaticamente na primeira gravação, então não é preciso criar nada manualmente.

### 3. Crie o arquivo de configuração local

O arquivo `application.properties` já ativa o perfil `local`. Por segurança, as credenciais ficam em um arquivo à parte, `application-local.properties`, que **não é enviado ao GitHub** (está no `.gitignore`). Você precisa criá-lo:

**Caminho:** `src/main/resources/application-local.properties`

```properties
spring.mongodb.uri=mongodb+srv://<USUARIO>:<SENHA>@<ENDERECO-DO-CLUSTER>/aguatrans?retryWrites=true&w=majority
```

Troque os três itens entre `< >` (e apague os símbolos `< >`):

| Item | O que colocar |
|---|---|
| `<USUARIO>` | O usuário criado em *Database Access* |
| `<SENHA>` | A senha desse usuário |
| `<ENDERECO-DO-CLUSTER>` | O endereço do seu cluster, copiado do Atlas (algo como `cluster0.xxxxx.mongodb.net`). É o trecho entre o `@` e o `/` |

O texto `aguatrans` logo depois da barra é o **nome do banco** (pode manter).

> 💡 Se a senha tiver caracteres especiais (`@`, `#`, `/`, `:`, `%`), eles precisam ser codificados na URL (por exemplo, `@` vira `%40`). O mais simples é usar uma senha só com letras e números.
>
> 💡 No **Spring Boot 4**, a propriedade se chama `spring.mongodb.uri`. O nome antigo (`spring.data.mongodb.uri`) usado em muitos tutoriais **não funciona** nesta versão.

### 4. Execute a aplicação

**Linux, macOS ou Git Bash (Windows):**
```bash
./mvnw spring-boot:run
```

**Windows (CMD ou PowerShell):**
```powershell
.\mvnw.cmd spring-boot:run
```

Na primeira vez o Maven baixa as dependências, então pode demorar um pouco. Quando aparecer uma linha como `Started ChallengeSprint2BackendApplication`, a API está no ar em:

**http://localhost:8080**

Para conferir que a conexão com o Atlas funcionou, procure no log linhas como `Monitor thread successfully connected to server` e `Discovered replica set primary`. Para parar a aplicação, use `Ctrl + C` no terminal.

### Alternativa: MongoDB local ou variável de ambiente

- **MongoDB instalado na sua máquina:** use no `application-local.properties`:
  ```properties
  spring.mongodb.uri=mongodb://localhost:27017/aguatrans
  ```
- **Sem criar o arquivo:** defina a variável de ambiente `SPRING_MONGODB_URI` com a string de conexão antes de executar.
  - Git Bash / Linux / macOS: `export SPRING_MONGODB_URI="mongodb+srv://..."`
  - PowerShell: `$env:SPRING_MONGODB_URI="mongodb+srv://..."`

---

## 🧪 Testando a API

Use o **Postman** (método, URL, aba *Body → raw → JSON*) ou o `curl`. Exemplos com `curl` (Git Bash, Linux ou macOS):

**1. Cadastrar um usuário**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Silva","email":"maria@aguatrans.com","senha":"123456","perfil":"GESTOR"}'
```

**2. Fazer login**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"maria@aguatrans.com","senha":"123456"}'
```

**3. Criar um projeto**
```bash
curl -X POST http://localhost:8080/api/projects \
  -H "Content-Type: application/json" \
  -d '{"nome":"Projeto Teste","etapa":"Planejamento","status":"Em andamento","investimento":10000,"prazo":"2026-12-31","retornoFinanceiro":15000,"lucroObtido":5000,"aumentoProdutividade":12.5}'
```

**4. Ver o resumo do dashboard**
```bash
curl http://localhost:8080/api/dashboard/summary
```

Depois, no Atlas, em **Browse Collections**, você deve ver o banco `aguatrans` com as coleções criadas.

---

## 📡 Endpoints

Base URL: `http://localhost:8080`. Todos os corpos de requisição e respostas são em **JSON**.

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/api/auth/register` | Cadastra um usuário. Retorna `400` se o e-mail já existir |
| `POST` | `/api/auth/login` | Autentica por `email` e `senha`. Retorna `401` se estiverem incorretos |
| `GET` | `/api/projects` | Lista todos os projetos |
| `POST` | `/api/projects` | Cria um projeto |
| `PUT` | `/api/projects/{id}` | Atualiza um projeto existente (todos os campos do corpo substituem os atuais) |
| `GET` | `/api/ideas` | Lista todas as ideias |
| `POST` | `/api/ideas` | Cria uma ideia. O `status` é sempre definido como `PENDENTE` |
| `GET` | `/api/strategies` | Lista as orientações estratégicas |
| `POST` | `/api/strategies` | Cria uma orientação estratégica |
| `GET` | `/api/dashboard/summary` | Retorna o resumo consolidado dos projetos |

### Resposta do dashboard (`GET /api/dashboard/summary`)

```json
{
  "totalProjetos": 1,
  "investimentoTotal": 10000.0,
  "retornoTotal": 15000.0,
  "lucroObtidoTotal": 5000.0,
  "roiGeralPercentual": 50.0,
  "projetos": [ ... ]
}
```

O ROI geral é calculado como `((retornoTotal − investimentoTotal) / investimentoTotal) × 100`. Se o investimento total for zero, o ROI retorna `0`.

---

## 🗄️ Modelos de dados

Os identificadores (`id`) são gerados pelo MongoDB e não precisam ser enviados ao criar um registro.

**Usuário** – coleção `usuarios`

| Campo | Tipo | Observação |
|---|---|---|
| `id` | String | gerado automaticamente |
| `nome` | String | |
| `email` | String | único no cadastro |
| `senha` | String | |
| `perfil` | String | `OPERADOR`, `GESTOR` ou `LIDER` |

**Projeto** – coleção `projetos`

| Campo | Tipo | Observação |
|---|---|---|
| `id` | String | gerado automaticamente |
| `nome`, `etapa`, `status` | String | |
| `investimento`, `retornoFinanceiro`, `lucroObtido` | Double | |
| `aumentoProdutividade` | Double | |
| `prazo` | String | ex.: `"2026-12-31"` |
| `estrategiaId` | String | id de uma orientação estratégica |

**Ideia** – coleção `ideias`

| Campo | Tipo | Observação |
|---|---|---|
| `id` | String | gerado automaticamente |
| `titulo`, `descricao` | String | |
| `autorId` | String | id do usuário (operador) que criou a ideia |
| `status` | String | `PENDENTE`, `APROVADA` ou `REJEITADA` (nasce `PENDENTE`) |
| `estrategiaId` | String | id de uma orientação estratégica |
| `pontuacaoIa` | Double | |

**Orientação estratégica** – coleção `orientacoes_estrategicas`

| Campo | Tipo | Observação |
|---|---|---|
| `id` | String | gerado automaticamente |
| `data` | Data | formato `AAAA-MM-DD`, ex.: `"2026-09-19"` |
| `categoria`, `campanha`, `descricao` | String | |

---

## 📁 Estrutura do projeto

```
challenge-sprint2-backend/                     ← raiz do repositório
└── challenge-sprint2-backend/                 ← projeto Maven (execute os comandos aqui)
    ├── pom.xml
    ├── mvnw / mvnw.cmd                        ← Maven Wrapper
    └── src/
        ├── main/
        │   ├── java/com/aguatrans/challenge_sprint2_backend/
        │   │   ├── ChallengeSprint2BackendApplication.java   ← classe principal
        │   │   ├── controller/                ← endpoints REST
        │   │   │   ├── AuthController.java
        │   │   │   ├── DashboardController.java
        │   │   │   ├── IdeaController.java
        │   │   │   ├── ProjectController.java
        │   │   │   └── StrategicController.java
        │   │   ├── model/                     ← documentos do MongoDB
        │   │   │   ├── Idea.java
        │   │   │   ├── Project.java
        │   │   │   ├── StrategicOrientation.java
        │   │   │   └── User.java
        │   │   └── repository/                ← acesso ao banco (Spring Data)
        │   └── resources/
        │       ├── application.properties          ← ativa o perfil "local"
        │       └── application-local.properties    ← SUA conexão (você cria; não vai ao Git)
        └── test/
```

---

## 🩺 Solução de problemas

| Sintoma | Causa provável e solução |
|---|---|
| `./mvnw: No such file or directory` | Você está na pasta errada. Entre em `challenge-sprint2-backend/challenge-sprint2-backend` (a que tem o `pom.xml`). |
| `Permission denied` ao rodar `./mvnw` (Linux/macOS) | Rode `chmod +x mvnw` e tente de novo. |
| Erros do Maven sobre versão do Java | Instale o JDK 17+ e confira o `JAVA_HOME` e o `java -version`. |
| Muitos erros vermelhos no VS Code (`cannot be resolved`), mas o `./mvnw clean compile` funciona | O VS Code foi aberto na pasta errada. Abra a pasta que contém o `pom.xml`. Se persistir: `Ctrl+Shift+P` → **Java: Clean Java Language Server Workspace** → **Restart and delete**. |
| `Timed out while waiting for a server ... localhost:27017` | A aplicação não recebeu a URI do Atlas e tentou um MongoDB local. Verifique se o arquivo `application-local.properties` existe, está em `src/main/resources`, e usa `spring.mongodb.uri` (e não `spring.data.mongodb.uri`). |
| `Failed looking up SRV record ... DNS name not found` | O endereço do cluster na URI está errado. Copie-o de novo em **Connect → Drivers** no Atlas. |
| `Timed out` mesmo com a URI correta | Seu IP não está liberado. Adicione-o em **Network Access** no Atlas. |
| `Authentication failed` | Usuário ou senha incorretos. Use os de **Database Access** (não os da sua conta do Atlas) e codifique caracteres especiais da senha. |
| `405 Method Not Allowed` | Método HTTP errado para a rota. Confira a tabela de [endpoints](#-endpoints) (ex.: o dashboard é `GET`, não `POST`). |
| `400` com "E-mail já registado" | Comportamento esperado: já existe um usuário com esse e-mail. |
| `Port 8080 was already in use` | Outra aplicação usa a porta. Encerre-a ou rode com `./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081`. |

---

## ⚠️ Limitações conhecidas

Por se tratar da Sprint 2, alguns pontos ainda estão simplificados e são candidatos a melhoria nas próximas entregas:

- **Autenticação simples:** as senhas são armazenadas e comparadas em texto puro e não há token (JWT) nem controle de acesso por perfil.
- **CORS não configurado:** se um front-end rodar em outra origem (por exemplo, `localhost:3000`), será necessário habilitar o CORS no backend.
- **Sem validação de campos** nos corpos das requisições.
- **Sem endpoints de exclusão** (`DELETE`) e sem `PUT` para ideias e orientações estratégicas.