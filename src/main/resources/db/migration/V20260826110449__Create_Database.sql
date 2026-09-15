

-- tables
-- Table: BoardGame
CREATE TABLE BoardGame (
                           id CHAR(36) NOT NULL,
                           description varchar(150)  NOT NULL,
                           name varchar(150)  NOT NULL,
                           release_date date  NOT NULL,
                           player_number int  NOT NULL,
                           gameplay_time int  NOT NULL,
                           CONSTRAINT BoardGame_pk PRIMARY KEY (id)
);

-- Table: Match
CREATE TABLE `Match` (
                         id CHAR(36) NOT NULL,
                         BoardGame_id CHAR(36) NOT NULL,
                         CONSTRAINT Match_pk PRIMARY KEY (id)
);

-- Table: MatchUser
CREATE TABLE MatchUser (
                           Match_id CHAR(36) NOT NULL,
                           User_id CHAR(36) NOT NULL,
                           id CHAR(36) NOT NULL,
                           CONSTRAINT MatchUser_pk PRIMARY KEY (id)
);

-- Table: Publisher
CREATE TABLE Publisher (
                           id CHAR(36) NOT NULL,
                           name varchar(150)  NOT NULL,
                           country int  NOT NULL,
                           CONSTRAINT Publisher_pk PRIMARY KEY (id)
);

-- Table: PublisherBoardGame
CREATE TABLE PublisherBoardGame (
                                    Publisher_id CHAR(36) NOT NULL,
                                    BoardGame_id CHAR(36) NOT NULL,
                                    id CHAR(36) NOT NULL,
                                    CONSTRAINT PublisherBoardGame_pk PRIMARY KEY (id)
);

-- Table: User
CREATE TABLE User (
                      id CHAR(36) NOT NULL,
                      name varchar(150)  NOT NULL,
                      email varchar(150)  NOT NULL,
                      password varchar(150)  NOT NULL,
                      role int  NOT NULL,
                      CONSTRAINT User_pk PRIMARY KEY (id)
);

-- Table: UserBoardGame
CREATE TABLE UserBoardGame (
                               User_id CHAR(36) NOT NULL,
                               BoardGame_id CHAR(36) NOT NULL,
                               id CHAR(36) NOT NULL,
                               CONSTRAINT UserBoardGame_pk PRIMARY KEY (id)
);

-- Table: UserReview
CREATE TABLE UserReview (
                            User_id CHAR(36) NOT NULL,
                            BoardGame_id CHAR(36) NOT NULL,
                            id CHAR(36) NOT NULL,
                            grade int  NOT NULL,
                            comment varchar(150)  NOT NULL,
                            review_date date  NOT NULL,
                            CONSTRAINT UserReview_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: BoardGame_PublisherBoardGame (table: PublisherBoardGame)
ALTER TABLE PublisherBoardGame ADD CONSTRAINT BoardGame_PublisherBoardGame FOREIGN KEY BoardGame_PublisherBoardGame (BoardGame_id)
    REFERENCES BoardGame (id);

-- Reference: BoardGame_UserBoardGame (table: UserBoardGame)
ALTER TABLE UserBoardGame ADD CONSTRAINT BoardGame_UserBoardGame FOREIGN KEY BoardGame_UserBoardGame (BoardGame_id)
    REFERENCES BoardGame (id);

-- Reference: BoardGame_UserReview (table: UserReview)
ALTER TABLE UserReview ADD CONSTRAINT BoardGame_UserReview FOREIGN KEY BoardGame_UserReview (BoardGame_id)
    REFERENCES BoardGame (id);

-- Reference: Match_BoardGame (table: Match)
ALTER TABLE `Match` ADD CONSTRAINT Match_BoardGame FOREIGN KEY Match_BoardGame (BoardGame_id)
    REFERENCES BoardGame (id);

-- Reference: Match_MatchUser (table: MatchUser)
ALTER TABLE MatchUser ADD CONSTRAINT Match_MatchUser FOREIGN KEY Match_MatchUser (Match_id)
    REFERENCES `Match` (id);

-- Reference: Publisher_PublisherBoardGame (table: PublisherBoardGame)
ALTER TABLE PublisherBoardGame ADD CONSTRAINT Publisher_PublisherBoardGame FOREIGN KEY Publisher_PublisherBoardGame (Publisher_id)
    REFERENCES Publisher (id);

-- Reference: User_MatchUser (table: MatchUser)
ALTER TABLE MatchUser ADD CONSTRAINT User_MatchUser FOREIGN KEY User_MatchUser (User_id)
    REFERENCES User (id);

-- Reference: User_UserBoardGame (table: UserBoardGame)
ALTER TABLE UserBoardGame ADD CONSTRAINT User_UserBoardGame FOREIGN KEY User_UserBoardGame (User_id)
    REFERENCES User (id);

-- Reference: User_UserReview (table: UserReview)
ALTER TABLE UserReview ADD CONSTRAINT User_UserReview FOREIGN KEY User_UserReview (User_id)
    REFERENCES User (id);
