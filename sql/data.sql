CREATE DATABASE book_service;
USE book_service;

CREATE TABLE users (
    id  BINARY(16)  PRIMARY KEY,
    name    varchar(100)    NOT NULL,
    email   varchar(100)    UNIQUE  NOT NULL,
    password varchar(255)   NOT NULL
);

CREATE TABLE books (
    id  BINARY(16)  PRIMARY KEY,
    title   varchar(100)    NOT NULL,
    author  varchar(100)    NOT NULL,
    image_url   varchar(255) NOT NULL
);

CREATE TABLE book_loans (
    id  BINARY(16)  PRIMARY KEY ,
    loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    due_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP NULL,
    penalty_until   INT DEFAULT 0,
    return_status   BOOLEAN DEFAULT FALSE NOT NULL,

    user_id BINARY(16)  NOT NULL ,
    book_id BINARY(16)  NOT NULL ,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

