INSERT INTO ROLES (name) VALUES ("ROLE_ADMIN");
INSERT INTO ROLES (name) VALUES ("ROLE_COORDINATOR");
INSERT INTO ROLES (name) VALUES ("ROLE_NUTRITIONIST");
INSERT INTO ROLES (name) VALUES ("ROLE_PSYCHOLOGIST");
INSERT INTO ROLES (name) VALUES ("ROLE_SOCIAL_WORKER");

--Creating an admin user in order to create and test new users
INSERT INTO USERS (role_id, password, username) VALUES (1, "AdminPassword", "admin_test")