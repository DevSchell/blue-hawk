# BoardGame Hub 🎲

Repositório inicial do projeto de gerenciamento e marketplace de jogos de tabuleiro.

A ideia do projeto é criar uma plataforma onde o usuário consiga cadastrar e organizar sua própria coleção de boardgames (CRUD completo) e também navegar por um feed para encontrar jogos que outros usuários estejam vendendo.

### 📌 Sobre o Projeto

O desenvolvimento está dividido em duas partes principais:

**Backend:** API REST desenvolvida em Spring Boot para gerenciamento de regras de negócio, usuários e persistência dos dados.

**Frontend:** Aplicativo em Flutter para oferecer uma interface simples e intuitiva tanto no mobile quanto no desktop/web.

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

Quando a estrutura inicial estiver pronta, você precisará de:

- JDK 17+
- Flutter SDK
- Um banco de dados instalado ou rodando via container (a definir)
- IDE de sua preferência (VS Code, IntelliJ, Android Studio)

### Lista de API

#### User

| Método | Rota | Descrição |
|---|---|---|
| POST | `/users` | Criar um usuário |
| POST | `/users/authenticate` | Autenticar um usuário |
| PUT | `/users/{uuid}` | Atualizar perfil (completo) |
| PATCH | `/users/{uuid}` | Atualizar perfil (parcial) |
| PATCH | `/users/{uuid}/password` | Alterar senha |
| GET | `/users/{uuid}` | Detalhar um usuário específico |
| GET | `/users` | Listar usuários |
| DELETE | `/users/{uuid}` | Remover um usuário |

#### UserBoardgame

| Método | Rota | Descrição |
|---|---|---|
| POST | `/user-boardgames` | Criar uma posse |
| PUT | `/user-boardgames/{uuid}` | Atualizar posse (completo) |
| PATCH | `/user-boardgames/{uuid}` | Atualizar posse (parcial) |
| GET | `/user-boardgames/{uuid}` | Detalhar uma posse específica |
| GET | `/user-boardgames` | Listar posses (query params: `?userId=&boardgameId=`) |
| DELETE | `/user-boardgames/{uuid}` | Remover posse |

#### Offer

| Método | Rota | Descrição |
|---|---|---|
| POST | `/offers` | Criar uma oferta (a partir de um `userBoardgameId`) |
| PUT | `/offers/{uuid}` | Atualizar oferta (completo) |
| PATCH | `/offers/{uuid}` | Atualizar oferta (parcial, ex: só o preço) |
| GET | `/offers/{uuid}` | Detalhar uma oferta específica |
| GET | `/offers` | Listar ofertas (query params: `?userId=&boardgameId=&status=`) |
| DELETE | `/offers/{uuid}` | Remover oferta |

#### Match


| Método | Rota | Descrição |
|---|---|---|
| POST | `/matches | Criar uma partida |
| PUT | `/matches/{uuid}` | Atualizar partida (completo) |
| PATCH | `/matches/{uuid}` | Atualizar partida (parcial) |
| GET | `/matches/{uuid}` | Detalhar uma partida específica |
| GET | `/macthes` | Listar partidas (query params: `?boardgameId=`) |
| DELETE | `/matches/{uuid}` | Remover partida |

#### MatchParticipant


| Método | Rota | Descrição |
|---|---|---|
| POST | `/match-participants | adicionar um user a uma partida (a partir de um matchId e UserId |
| PUT | `/match-participants/{uuid}` | Atualizar participante (completo) |
| PATCH | `/match-participants/{uuid}` | Atualizar participante (parcial) |
| GET | `/match-participants/{uuid}` | Detalhar uma participante específica |
| GET | `/macth-participants` | Listar participantes (query params: `?matchId=`) |
| DELETE | `/match-participants/{uuid}` | Remover participante da partida |


| DELETE | `/match-participants/{uuid}` | Remover participante da partida |


