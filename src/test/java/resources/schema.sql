SET FOREIGN_KEY_CHECKS = 0;
--

DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS users;
--

SET FOREIGN_KEY_CHECKS = 1;

-- Re-create tables
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role ENUM('USER', 'ADMIN') NOT NULL,
  password VARCHAR(255) NOT NULL,
  UNIQUE (username),
  UNIQUE (email)
);

--CREATE TABLE user_role (
--  user_id BIGINT NOT NULL,
--  roles VARCHAR(20) DEFAULT NULL,
--  CONSTRAINT FK_user_role_user FOREIGN KEY (user_id) REFERENCES users(id)
--);
