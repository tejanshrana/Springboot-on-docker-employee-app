CREATE DATABASE mewtwo;
USE mewtwo;
SHOW tables;

CREATE TABLE USERS(
    ID int NOT NULL AUTO_INCREMENT,
    user_name varchar(255) NOT NULL,
    Age int,
    date_of_birth date,
    PRIMARY KEY (ID)
);
