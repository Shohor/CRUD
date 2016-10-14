DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(100) NOT NULL,
  age        INT,
  email      VARCHAR(100),
  createdDate TIMESTAMP DEFAULT now()
);

INSERT INTO users (name, age, email)
VALUES ('Ivanov ivan', 23, 'ivanov@mail.com'),
       ('Jon Mikle', 18, 'mikle@mail.com'),
       ('Fedorov Igor', 57, 'fedorov@mail.com');