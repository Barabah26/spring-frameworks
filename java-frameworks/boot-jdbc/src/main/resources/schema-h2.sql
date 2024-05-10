DROP TABLE IF EXISTS TBL_EMPLOYEES;

CREATE TABLE TBL_EMPLOYEES (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               name VARCHAR(250) NOT NULL,
                               surname VARCHAR(250) NOT NULL,
                               email VARCHAR(250) DEFAULT NULL,
                               department_id INTEGER DEFAULT NULL
);

CREATE TABLE DEPARTMENTS (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               name VARCHAR(250) NOT NULL

);