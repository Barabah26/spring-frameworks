INSERT INTO users(user_id, enabled, encrypted_password, user_name) VALUES
 (101, true, '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq', 'a'),
 (102, true, '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq', 'admin');
INSERT INTO roles(role_id, role_name, user_id) VALUES
 (101, 'USER', 101),
 (102, 'ADMIN', 102);