DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS
(
  ID                 INT NOT NULL,
  USERNAME           VARCHAR(32) NOT NULL UNIQUE,

  PRIMARY KEY (ID)
);
