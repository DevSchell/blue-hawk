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

- JDK 21 (ou superior compatível)
- Apache Maven instalado (`mvn` disponível no terminal)
- PostgreSQL instalado/rodando e acessível

### ⚙️ Configuração e execução (application.properties)

Configurações sensíveis e detalhes do banco de dados não são mantidos no repositório. Você precisa configurar o arquivo localmente.

1. Navegue até o diretório `src/main/resources/`.
2. Crie um arquivo chamado `application.properties` (que é ignorado pelo Git).
3. Adicione o seguinte conteúdo base e ajuste de acordo com as configurações em sua máquina:

```properties
spring.application.name=blue-hawk

# Configurações do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=sua_senha_aqui
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

# Desabilita a conversão de CamelCase para snake_case
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

# ProblemDetails
spring.mvc.problemdetails.enabled=true

# JWT Secreto (apenas exemplo para dev local, 32+ caracteres)
security.jwt.secret=9a3f4b5c6d7e8f9a0b1c2d3e4f5a6b7c8d9e0f1a2b3c4d5e6f7a8b9c0d1e2f3a
security.jwt.expiration-seconds=86400
```

Com o banco de dados rodando e o arquivo configurado, na raiz do repositório inicie o backend usando o comando Maven:

```bash
mvn spring-boot:run
```

O Flyway aplica automaticamente as migrations pendentes de `src/main/resources/db/migration` ao iniciar a aplicação.
O Hibernate usa `ddl-auto=validate`, então o schema do banco precisa corresponder às entidades. O projeto não configura
o plugin Maven do Flyway; por isso, a forma documentada de aplicar migrations é iniciar a aplicação.

Em seguida, a API fica disponível em `http://localhost:8080`.

### Lista de API

#### User

| Método | Rota                     | Descrição                      |
|--------|--------------------------|--------------------------------|
| POST   | `/users`                 | Criar um usuário               |
| POST   | `/users/authenticate`    | Autenticar um usuário          |
| PUT    | `/users/{uuid}`          | Atualizar perfil (completo)    |
| PATCH  | `/users/{uuid}`          | Atualizar perfil (parcial)     |
| PATCH  | `/users/{uuid}/password` | Alterar senha                  |
| GET    | `/users/{uuid}`          | Detalhar um usuário específico |
| GET    | `/users`                 | Listar usuários                |
| DELETE | `/users/{uuid}`          | Remover um usuário             |

#### UserBoardgame

| Método | Rota                      | Descrição                                             |
|--------|---------------------------|-------------------------------------------------------|
| POST   | `/user-boardgames`        | Criar uma posse                                       |
| PUT    | `/user-boardgames/{uuid}` | Atualizar posse (completo)                            |
| PATCH  | `/user-boardgames/{uuid}` | Atualizar posse (parcial)                             |
| GET    | `/user-boardgames/{uuid}` | Detalhar uma posse específica                         |
| GET    | `/user-boardgames`        | Listar posses (query params: `?userId=&boardgameId=`) |
| DELETE | `/user-boardgames/{uuid}` | Remover posse                                         |

#### Offer

| Método | Rota             | Descrição                                                      |
|--------|------------------|----------------------------------------------------------------|
| POST   | `/offers`        | Criar uma oferta (a partir de um `userBoardgameId`)            |
| PUT    | `/offers/{uuid}` | Atualizar oferta (completo)                                    |
| PATCH  | `/offers/{uuid}` | Atualizar oferta (parcial, ex: só o preço)                     |
| GET    | `/offers/{uuid}` | Detalhar uma oferta específica                                 |
| GET    | `/offers`        | Listar ofertas (query params: `?userId=&boardgameId=&status=`) |
| DELETE | `/offers/{uuid}` | Remover oferta                                                 |

#### Match

| Método | Rota              | Descrição                                       |
|--------|-------------------|-------------------------------------------------|
| POST   | `/matches`        | Criar uma partida                               |
| PUT    | `/matches/{uuid}` | Atualizar partida (completo)                    |
| PATCH  | `/matches/{uuid}` | Atualizar partida (parcial)                     |
| GET    | `/matches/{uuid}` | Detalhar uma partida específica                 |
| GET    | `/matches`        | Listar partidas (query params: `?boardgameId=`) |
| DELETE | `/matches/{uuid}` | Remover partida                                 |

#### MatchParticipant

| Método | Rota                  | Descrição                                                             |
|--------|-----------------------|-----------------------------------------------------------------------|
| POST   | `/match-participants` | Adicionar um user a uma partida (a partir de um `matchId` e `userId`) |
| PUT    | `/match-user/{uuid}`  | Atualizar participante (completo)                                     |
| PATCH  | `/match-user/{uuid}`  | Atualizar participante (parcial)                                      |
| GET    | `/match-user/{uuid}`  | Detalhar uma participante específica                                  |
| GET    | `/match-user`         | Listar participantes (query params: `?matchId=`)                      |
| DELETE | `/match-user/{uuid}`  | Remover participante da partida                                       |

### Boardgame

| Método | Rota                          | Descrição                                  |
|--------|-------------------------------|--------------------------------------------|
| POST   | `/boardgames`                 | Cria um novo boardgame                     |
| GET    | `/boardgames`                 | Listagem de boardgames no sistema          |
| GET    | `/boardgames/{page}`          | Listagem paginada de boardgames no sistema |
| GET    | `/boardgames/{uuid}`          | Busca um jogo pelo UUID                    |
| GET    | `/boardgames/{uuid}/overview` | Retorna um resumo do boardgame             |
| POST   | `/boardgames/{uuid}`          | Altera o status de um jogo para desativado |

### Publisher

| Método | Rota                 | Descrição                                      |
|--------|----------------------|------------------------------------------------|
| POST   | `/publishers`        | Cadastra uma nova editora                      |
| GET    | `/publishers`        | Lista todas as editoras                        |
| GET    | `/publishers/{page}` | Lista paginada de editoras                     |
| GET    | `/publishers/{uuid}` | Busca os dados de uma editora com base no uuid |
| POST   | `/publishers/{uuid}` | Atualiza os dados de uma editora               |
