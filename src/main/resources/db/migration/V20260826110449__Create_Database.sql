

-- tables
-- Table: board_game
CREATE TABLE board_game (
                            id CHAR(36) NOT NULL,
                            description varchar(150)  NOT NULL,
                            name varchar(150)  NOT NULL,
                            release_date date  NOT NULL,
                            player_number int  NOT NULL,
                            gameplay_time int  NOT NULL,
                            status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                            created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            CONSTRAINT board_game_pk PRIMARY KEY (id),
                            CONSTRAINT board_game_status_chk CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

-- Table: match
CREATE TABLE `match` (
                         id CHAR(36) NOT NULL,
                         board_game_id CHAR(36) NOT NULL,
                         status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED',
                         played_at DATETIME NULL,
                         created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         CONSTRAINT match_pk PRIMARY KEY (id),
                         CONSTRAINT match_status_chk CHECK (status IN ('SCHEDULED', 'IN_PROGRESS', 'FINISHED', 'CANCELLED'))
);

-- Table: match_user
CREATE TABLE match_user (
                            id CHAR(36) NOT NULL,
                            match_id CHAR(36) NOT NULL,
                            user_id CHAR(36) NOT NULL,
                            score int NULL,
                            CONSTRAINT match_user_pk PRIMARY KEY (id)
);

-- Table: publisher
CREATE TABLE publisher (
                           id CHAR(36) NOT NULL,
                           name varchar(150)  NOT NULL,
                           country int  NOT NULL,
                           CONSTRAINT publisher_pk PRIMARY KEY (id)
);

-- Table: publisher_board_game
CREATE TABLE publisher_board_game (
                                      publisher_id CHAR(36) NOT NULL,
                                      board_game_id CHAR(36) NOT NULL,
                                      id CHAR(36) NOT NULL,
                                      CONSTRAINT publisher_board_game_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE `user` (
                        id CHAR(36) NOT NULL,
                        name varchar(150)  NOT NULL,
                        email varchar(150)  NOT NULL,
                        password varchar(150)  NOT NULL,
                        role int  NOT NULL,
                        created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Table: user_board_game
CREATE TABLE user_board_game (
                                 user_id CHAR(36) NOT NULL,
                                 board_game_id CHAR(36) NOT NULL,
                                 id CHAR(36) NOT NULL,
                                 CONSTRAINT user_board_game_pk PRIMARY KEY (id)
);

-- Table: user_review
CREATE TABLE user_review (
                             user_id CHAR(36) NOT NULL,
                             board_game_id CHAR(36) NOT NULL,
                             id CHAR(36) NOT NULL,
                             grade int  NOT NULL,
                             comment varchar(150)  NOT NULL,
                             review_date date  NOT NULL,
                             CONSTRAINT user_review_pk PRIMARY KEY (id)
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
ALTER TABLE `match` ADD CONSTRAINT match_board_game FOREIGN KEY (board_game_id)
    REFERENCES board_game (id);

-- Reference: match_match_user (table: match_user)
ALTER TABLE match_user ADD CONSTRAINT match_match_user FOREIGN KEY (match_id)
    REFERENCES `match` (id);

-- Reference: publisher_publisher_board_game (table: publisher_board_game)
ALTER TABLE publisher_board_game ADD CONSTRAINT publisher_publisher_board_game FOREIGN KEY (publisher_id)
    REFERENCES publisher (id);

-- Reference: user_match_user (table: match_user)
ALTER TABLE match_user ADD CONSTRAINT user_match_user FOREIGN KEY (user_id)
    REFERENCES `user` (id);

-- Reference: user_user_board_game (table: user_board_game)
ALTER TABLE user_board_game ADD CONSTRAINT user_user_board_game FOREIGN KEY (user_id)
    REFERENCES `user` (id);

-- Reference: user_user_review (table: user_review)
ALTER TABLE user_review ADD CONSTRAINT user_user_review FOREIGN KEY (user_id)
    REFERENCES `user` (id);

-- Table: offer
CREATE TABLE offer (
    id CHAR(36) NOT NULL,
    user_board_game_id CHAR(36) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    description VARCHAR(500) NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT offer_pk PRIMARY KEY (id),
    CONSTRAINT offer_status_chk CHECK (status IN ('ACTIVE', 'INACTIVE', 'SOLD', 'CANCELLED'))
);

-- Reference: offer_user_board_game (table: offer)
ALTER TABLE offer ADD CONSTRAINT offer_user_board_game FOREIGN KEY (user_board_game_id)
    REFERENCES user_board_game (id);
