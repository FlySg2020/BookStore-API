--Below scripts to create table added by lihao @13/05/2023

CREATE TABLE Book (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  ISBN VARCHAR(20) UNIQUE NOT NULL,
  TITLE VARCHAR(255) NOT NULL,
  YEAR INTEGER,
  PRICE DECIMAL(10,2),
  GENRE VARCHAR(50),
  PRIMARY KEY(ID)
);

CREATE TABLE Author (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(100) NOT NULL,
  BIRTHDAY DATE,
  BOOK_ISBN VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT FK_Book
    FOREIGN KEY (BOOK_ISBN)
    REFERENCES Book (ISBN)
    ON DELETE CASCADE
);

CREATE TABLE Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Email VARCHAR(50) NOT NULL,
    USER_NAME VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    PHONE VARCHAR(255),
    CREATED DATE,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE ROLES (
    ID INT NOT NULL,
    NAME varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE USER_ROLE (
    ID INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (role_id) REFERENCES Roles(id),
    PRIMARY KEY (ID)
);

--Created Table Scripts end