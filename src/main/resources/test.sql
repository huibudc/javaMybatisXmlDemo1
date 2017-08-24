CREATE TABLE user(
  id int auto_increment,
  name VARCHAR(50),
  password VARCHAR(50)
);

INSERT INTO user(name, password) VALUE ('leon','123456');
INSERT INTO user(name, password)  VALUE('test','654321');

SELECT * FROM  user;

create database mybatis;
use mybatis;
CREATE TABLE users(id INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), age INT);
INSERT INTO users(NAME, age) VALUES('孤傲苍狼', 27);
INSERT INTO users(NAME, age) VALUES('白虎神皇', 27);