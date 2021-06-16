DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_roles (
  id INT PRIMARY KEY,
  role VARCHAR(250) NOT NULL
);

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL UNIQUE,
  password VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES user_roles(id)
);


INSERT INTO user_roles (id, role) VALUES (1, 'ADMIN');
INSERT INTO user_roles (id, role) VALUES (2, 'USER');

--INSERT INTO users (first_name, last_name, username, password, email, role_id) VALUES
--  ('John', 'Smith', 'jsmith', '1234', 'jsmith@example.com', 2);