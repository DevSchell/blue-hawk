# Developer Environment
# Release Environment

# BoardGame Hub 🎲

Repositório inicial do projeto de gerenciamento e marketplace de jogos de tabuleiro.

A ideia do projeto é criar uma plataforma onde o usuário consiga cadastrar e organizar sua própria coleção de
boardgames (CRUD completo) e também navegar por um feed para encontrar jogos que outros usuários estejam vendendo.

### 📌 Sobre o Projeto

O desenvolvimento está dividido em duas partes principais:

**Backend:** API REST desenvolvida em Spring Boot para gerenciamento de regras de negócio, usuários e persistência dos
dados.

**Frontend:** Aplicativo em Flutter para oferecer uma interface simples e intuitiva tanto no mobile quanto no
desktop/web.

### 🛠️ Tecnologias Pretendidas

**Backend:** Java, Spring Boot, Spring Data JPA, Banco de Dados Relacional.

**Frontend:** Dart, Flutter.

**Ferramentas:** Git e GitHub.

### 🚀 Funcionalidades Planejadas

[ ] Cadastro e autenticação de usuários.

[ ] CRUD de coleção pessoal de jogos (adicionar, listar, editar e remover).

[ ] Marcação de itens da coleção como "disponíveis para venda".

[ ] Feed público de anúncios de jogos à venda por outros usuários.

[ ] Filtros de busca por nome ou categoria.

### 📋 Pré-requisitos para Rodar Localmente

- JDK 17+
- Flutter SDK
- MySQL instalado ou rodando via container
- IDE de sua preferência (VS Code, IntelliJ, Android Studio)

### ⚙️ Configuração

Antes de rodar o projeto, crie o arquivo `src/main/resources/application.properties` com o seguinte conteúdo básico:

```properties
spring.application.name=boardgame-hub
spring.datasource.url=jdbc:mysql://localhost:3306/boardgame_hub
spring.datasource.username=root
spring.datasource.password=sua_senha_aqui

spring.jpa.hibernate.ddl-auto=validate

spring.flyway.enabled=true
```

**Observações:**
- `ddl-auto=validate` faz o Hibernate apenas validar se o schema do banco bate com as entidades — quem cria e atualiza as tabelas é o Flyway, não o Hibernate.
- Com `spring.flyway.enabled=true`, ao subir a aplicação o Flyway roda automaticamente as migrations presentes em `src/main/resources/db/migration` (arquivos no padrão `V1__descricao.sql`, `V2__descricao.sql`, etc.).
- Ajuste `username`, `password` e o nome do banco (`boardgame_hub`) conforme o seu ambiente. Nunca versione senhas reais — use variáveis de ambiente em produção (ex: `${DB_PASSWORD}`).

Para rodar apenas a migration (criar/atualizar o schema do banco), basta iniciar a aplicação:

```bash
./mvnw spring-boot:run
```

O Flyway vai aplicar automaticamente as migrations pendentes antes da aplicação subir.

### Lista de API

#### User

| Método | Rota                     | Descrição                      |
|--------|--------------------------|---------------------------------|
| POST   | `/users`                 | Criar um usuário               |
| POST   | `/users/authenticate`    | Autenticar um usuário          |
| PUT    | `/users/{uuid}`          | Atualizar perfil (completo)    |
| PATCH  | `/users/{uuid}`          | Atualizar perfil (parcial)     |
| PATCH  | `/users/{uuid}/password` | Alterar senha                  |
| GET    | `/users/{uuid}`          | Detalhar um usuário específico |
| GET    | `/users`                 | Listar usuários                |
| DELETE | `/users/{uuid}`          | Remover um usuário             |

#### UserBoardgame

| Método | Rota                      | Descrição                                              |
|--------|---------------------------|---------------------------------------------------------|
| POST   | `/user-boardgames`        | Criar uma posse                                         |
| PUT    | `/user-boardgames/{uuid}` | Atualizar posse (completo)                              |
| PATCH  | `/user-boardgames/{uuid}` | Atualizar posse (parcial)                               |
| GET    | `/user-boardgames/{uuid}` | Detalhar uma posse específica                           |
| GET    | `/user-boardgames`        | Listar posses (query params: `?userId=&boardgameId=`)  |
| DELETE | `/user-boardgames/{uuid}` | Remover posse                                           |

#### Offer

| Método | Rota             | Descrição                                                      |
|--------|------------------|-----------------------------------------------------------------|
| POST   | `/offers`        | Criar uma oferta (a partir de um `userBoardgameId`)            |
| PUT    | `/offers/{uuid}` | Atualizar oferta (completo)                                     |
| PATCH  | `/offers/{uuid}` | Atualizar oferta (parcial, ex: só o preço)                     |
| GET    | `/offers/{uuid}` | Detalhar uma oferta específica                                  |
| GET    | `/offers`        | Listar ofertas (query params: `?userId=&boardgameId=&status=`) |
| DELETE | `/offers/{uuid}` | Remover oferta                                                  |

#### Match

| Método | Rota              | Descrição                                        |
|--------|-------------------|----------------------------------------------------|
| POST   | `/matches`        | Criar uma partida                                 |
| PUT    | `/matches/{uuid}` | Atualizar partida (completo)                      |
| PATCH  | `/matches/{uuid}` | Atualizar partida (parcial)                       |
| GET    | `/matches/{uuid}` | Detalhar uma partida específica                   |
| GET    | `/matches`        | Listar partidas (query params: `?boardgameId=`)   |
| DELETE | `/matches/{uuid}` | Remover partida                                   |

#### MatchParticipant

| Método | Rota                    | Descrição                                                          |
|--------|-------------------------|----------------------------------------------------------------------|
| POST   | `/match-participants`   | Adicionar um user a uma partida (a partir de um `matchId` e `userId`) |
| PUT    | `/match-user/{uuid}`    | Atualizar participante (completo)                                     |
| PATCH  | `/match-user/{uuid}`    | Atualizar participante (parcial)                                      |
| GET    | `/match-user/{uuid}`    | Detalhar uma participante específica                                  |
| GET    | `/match-user`           | Listar participantes (query params: `?matchId=`)                     |
| DELETE | `/match-user/{uuid}`    | Remover participante da partida                                       |

### Boardgame

| Método | Rota                          | Descrição                                  |
|--------|-------------------------------|----------------------------------------------|
| POST   | `/boardgames`                 | Cria um novo boardgame                     |
| GET    | `/boardgames`                 | Listagem de boardgames no sistema          |
| GET    | `/boardgames/{page}`          | Listagem paginada de boardgames no sistema |
| GET    | `/boardgames/{uuid}`          | Busca um jogo pelo UUID                    |
| GET    | `/boardgames/{uuid}/overview` | Retorna um resumo do boardgame             |
| POST   | `/boardgames/{uuid}`          | Altera o status de um jogo para desativado |

### Publisher

| Método | Rota                 | Descrição                                       |
|--------|----------------------|----------------------------------------------------|
| POST   | `/publishers`        | Cadastra uma nova editora                          |
| GET    | `/publishers`        | Lista todas as editoras                            |
| GET    | `/publishers/{page}` | Lista paginada de editoras                         |
| GET    | `/publishers/{uuid}` | Busca os dados de uma editora com base no uuid     |
| POST   | `/publishers/{uuid}` | Atualiza os dados de uma editora                   |