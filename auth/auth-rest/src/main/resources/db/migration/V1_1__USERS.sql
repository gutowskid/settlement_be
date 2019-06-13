CREATE TABLE users (
 ID VARCHAR(30) NOT NULL,
 PASSWORD_HASH VARCHAR(64) NOT NULL,
 ROLE VARCHAR(20) NOT NULL,
 IS_ACTIVE TINYINT(1) NOT NULL,
 FORENAME VARCHAR(25) NOT NULL,
 SURNAME VARCHAR(25) NOT NULL,
 PRIMARY KEY(ID)
);
