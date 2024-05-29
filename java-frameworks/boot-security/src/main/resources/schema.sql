DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE users (
        user_id SERIAL PRIMARY KEY,
        user_name VARCHAR(36) NOT NULL,
        encrypted_password VARCHAR(128) NOT NULL,
        creation_date TIMESTAMP  NULL,
        last_modified_date TIMESTAMP  NULL,
        created_by INT NULL,
        last_modified_by INT NULL,
        enabled boolean NOT NULL
);

CREATE TABLE roles (
        role_id SERIAL PRIMARY KEY,
        role_name VARCHAR(30) NOT NULL,
        user_id INT
);