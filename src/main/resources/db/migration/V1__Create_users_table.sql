CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL
);