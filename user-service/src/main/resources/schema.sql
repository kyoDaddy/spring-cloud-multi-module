--DROP TABLE IF EXISTS customer;
--CREATE TABLE customer (id SERIAL PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255));

DROP TABLE IF EXISTS user;
CREATE TABLE user (id SERIAL PRIMARY KEY, email VARCHAR(100), password VARCHAR(64), full_name varchar(100), created_at timestamp, user_auth varchar(10), user_id char(36));
