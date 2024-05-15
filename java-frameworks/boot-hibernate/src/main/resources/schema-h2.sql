DROP TABLE IF EXISTS students;
CREATE TABLE public.students (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               name VARCHAR(250) NOT NULL,
                               age INT NOT NULL,
                               group_id bigint

);

DROP TABLE IF EXISTS groups;
CREATE TABLE public.groups (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       name VARCHAR(250) NOT NULL
);
