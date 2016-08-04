CREATE DATABASE coding_bootcamp;

USE coding_bootcamp;

CREATE USER 'codeboot'@'localhost' IDENTIFIED BY 'whatever';

GRANT ALL PRIVILEGES ON coding_bootcamp.* TO 'codeboot'@'localhost';

DROP TABLE user_roles;
DROP TABLE users;

CREATE TABLE users(
	id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(30),
    name VARCHAR(30),
    surname VARCHAR(30),
    password CHAR(60),
    email VARCHAR(30),
    address VARCHAR(200),
    age INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE roles(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30),
    PRIMARY KEY (id),
    UNIQUE KEY role_name(name)
);

CREATE TABLE user_roles(
	id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY(id),
    UNIQUE KEY user_id_role_id(role_id, user_id),
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);

INSERT INTO roles(name) VALUES("user");

SELECT * FROM users;
