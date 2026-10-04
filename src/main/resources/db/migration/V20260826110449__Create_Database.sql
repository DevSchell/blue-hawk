

-- tables
-- Table: board_game
CREATE TABLE board_game (
    id          CHAR(36)        NOT NULL,
    description VARCHAR(150)    NOT NULL,
    name        VARCHAR(150)    NOT NULL,
    release_date DATE           NOT NULL,
    player_number INT           NOT NULL,
    gameplay_time INT           NOT NULL,
    status      VARCHAR(20)     NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT board_game_pk PRIMARY KEY (id),
    CONSTRAINT board_game_status_chk CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

-- Table: match
CREATE TABLE match (
    id            CHAR(36)    NOT NULL,
    board_game_id CHAR(36)    NOT NULL,
    status        VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED',
    played_at     TIMESTAMP   NULL,
    created_at    TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMP   NOT NULL DEFAULT NOW(),
    CONSTRAINT match_pk PRIMARY KEY (id),
    CONSTRAINT match_status_chk CHECK (status IN ('SCHEDULED', 'IN_PROGRESS', 'FINISHED', 'CANCELLED'))
);

-- Table: match_user
CREATE TABLE match_user (
    id       CHAR(36) NOT NULL,
    match_id CHAR(36) NOT NULL,
    user_id  CHAR(36) NOT NULL,
    score    INT      NULL,
    CONSTRAINT match_user_pk PRIMARY KEY (id)
);

-- Table: publisher
CREATE TABLE publisher (
    id      CHAR(36)    NOT NULL,
    name    VARCHAR(150) NOT NULL,
    country INT          NOT NULL,
    CONSTRAINT publisher_pk PRIMARY KEY (id)
);

-- Table: publisher_board_game
CREATE TABLE publisher_board_game (
    id            CHAR(36) NOT NULL,
    publisher_id  CHAR(36) NOT NULL,
    board_game_id CHAR(36) NOT NULL,
    CONSTRAINT publisher_board_game_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user" (
    id         CHAR(36)     NOT NULL,
    name       VARCHAR(150) NOT NULL,
    email      VARCHAR(150) NOT NULL,
    password   VARCHAR(150) NOT NULL,
    role       INT          NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Table: user_board_game
CREATE TABLE user_board_game (
    id            CHAR(36) NOT NULL,
    user_id       CHAR(36) NOT NULL,
    board_game_id CHAR(36) NOT NULL,
    CONSTRAINT user_board_game_pk PRIMARY KEY (id)
);

-- Table: user_review
CREATE TABLE user_review (
    id            CHAR(36)     NOT NULL,
    user_id       CHAR(36)     NOT NULL,
    board_game_id CHAR(36)     NOT NULL,
    grade         INT          NOT NULL,
    comment       VARCHAR(150) NOT NULL,
    review_date   DATE         NOT NULL,
    CONSTRAINT user_review_pk PRIMARY KEY (id)
);

-- Table: offer
CREATE TABLE offer (
    id                  CHAR(36)        NOT NULL,
    user_board_game_id  CHAR(36)        NOT NULL,
    price               DECIMAL(10, 2)  NOT NULL,
    status              VARCHAR(20)     NOT NULL DEFAULT 'ACTIVE',
    description         VARCHAR(500)    NULL,
    created_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT offer_pk PRIMARY KEY (id),
    CONSTRAINT offer_status_chk CHECK (status IN ('ACTIVE', 'INACTIVE', 'SOLD', 'CANCELLED'))
);

-- foreign keys
-- Reference: board_game_publisher_board_game (table: publisher_board_game)
ALTER TABLE publisher_board_game ADD CONSTRAINT board_game_publisher_board_game FOREIGN KEY (board_game_id)
    REFERENCES board_game (id);

-- Reference: board_game_user_board_game (table: user_board_game)
ALTER TABLE user_board_game ADD CONSTRAINT board_game_user_board_game FOREIGN KEY (board_game_id)
    REFERENCES board_game (id);

-- Reference: board_game_user_review (table: user_review)
ALTER TABLE user_review ADD CONSTRAINT board_game_user_review FOREIGN KEY (board_game_id)
    REFERENCES board_game (id);

-- Reference: match_board_game (table: match)
ALTER TABLE match ADD CONSTRAINT match_board_game FOREIGN KEY (board_game_id)
    REFERENCES board_game (id);

-- Reference: match_match_user (table: match_user)
ALTER TABLE match_user ADD CONSTRAINT match_match_user FOREIGN KEY (match_id)
    REFERENCES match (id);

-- Reference: publisher_publisher_board_game (table: publisher_board_game)
ALTER TABLE publisher_board_game ADD CONSTRAINT publisher_publisher_board_game FOREIGN KEY (publisher_id)
    REFERENCES publisher (id);

-- Reference: user_match_user (table: match_user)
ALTER TABLE match_user ADD CONSTRAINT user_match_user FOREIGN KEY (user_id)
    REFERENCES "user" (id);

-- Reference: user_user_board_game (table: user_board_game)
ALTER TABLE user_board_game ADD CONSTRAINT user_user_board_game FOREIGN KEY (user_id)
    REFERENCES "user" (id);

-- Reference: user_user_review (table: user_review)
ALTER TABLE user_review ADD CONSTRAINT user_user_review FOREIGN KEY (user_id)
    REFERENCES "user" (id);

-- Reference: offer_user_board_game (table: offer)
ALTER TABLE offer ADD CONSTRAINT offer_user_board_game FOREIGN KEY (user_board_game_id)
    REFERENCES user_board_game (id);
