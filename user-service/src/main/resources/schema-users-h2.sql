DROP TABLE IF EXISTS user;
CREATE TABLE user (id SERIAL PRIMARY KEY, email VARCHAR(100), password VARCHAR(64), full_name varchar(100), created_at timestamp, user_auth varchar(10), user_id char(36));
