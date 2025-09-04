-- This file will be executed automatically by Spring Boot if spring.jpa.hibernate.ddl-auto is 'create' or 'create-drop'
-- It's useful for populating initial data like roles.

-- Insert roles if they don't exist
INSERT INTO roles (name) VALUES ('ROLE_EMPLOYEE') ON DUPLICATE KEY UPDATE name=name;
INSERT INTO roles (name) VALUES ('ROLE_ADMIN') ON DUPLICATE KEY UPDATE name=name;