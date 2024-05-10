DROP TABLE IF EXISTS TBL_EMPLOYEES;

CREATE TABLE TBL_EMPLOYEES (
                               id SERIAL  PRIMARY KEY,
                               name VARCHAR(250) NOT NULL,
                               surname VARCHAR(250) NOT NULL,
                               email VARCHAR(250) DEFAULT NULL
);