INSERT INTO roles(name) 
VALUES ("admin"),
("user");

INSERT INTO users(enable, username, password)
VALUES (true, "admin", "1234");

INSERT INTO users_roles(role_id, user_id)
VALUES (1, 1);