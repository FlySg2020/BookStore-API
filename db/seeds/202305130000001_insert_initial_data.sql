-- Insert initial data to API users table
INSERT INTO USERS(EMAIL,USER_NAME,PASSWORD,PHONE,CREATED,ENABLED) VALUES('hao.li.ch@gmail.com','lihao','lihao123456','97285678','2023-05-13',1);
INSERT INTO USERS (EMAIL,USER_NAME,PASSWORD,PHONE,CREATED,ENABLED) VALUES ('Lucas.li@gmail.com','Lucas','Lucas123456','97112345','2023-05-13',1);

-- Insert initial data to Book table
insert into Book (ISBN, TITLE,YEAR,PRICE,GENRE) VALUES ('202305131352','Thinking Java', 1995, 20,'Strong Thinking Java Book');
insert into Book (ISBN, TITLE,YEAR,PRICE,GENRE) VALUES ('202305131353','Data Structure', 1996, 25,'Strong Data Structure Book');
insert into Book (ISBN, TITLE,YEAR,PRICE,GENRE) VALUES ('202305131354','Spring MVC', 1999, 26,'Spring MVC and WEB Flow');

-- Insert initial data to Author table
INSERT INTO Author(NAME,BIRTHDAY,BOOK_ISBN) VALUES ('Li Hao','1990-05-05','202305131352');
INSERT INTO Author(NAME,BIRTHDAY,BOOK_ISBN) VALUES ('Li Hao','1990-05-05','202305131353');
INSERT INTO Author(NAME,BIRTHDAY,BOOK_ISBN) VALUES ('Lucas','1995-05-05','202305131354');

-- Insert initial data to Roles table,only Admin role can access delete API
INSERT INTO ROLES(ID,NAME) VALUES(1,'ADMIN');
INSERT INTO ROLES(ID,NAME) VALUES(2,'CHECKER');
-- Insert initial data to User_Role table
INSERT INTO USER_ROLE (user_id,role_id) values (1,1);
INSERT INTO USER_ROLE (user_id,role_id) values (2,1);
INSERT INTO USER_ROLE (user_id,role_id) values (2,2);