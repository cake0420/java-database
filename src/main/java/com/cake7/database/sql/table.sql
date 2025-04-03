USE book_service;

CREATE TABLE users (
    id binary(16) NOT NULL,
    name varchar(50) NOT NULL,
    email varchar(100) NOT NULL UNIQUE ,
    password varchar(255) NOT NULL,
    salt    varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE books (
    id  binary(16) NOT NULL,
    title varchar(100) NOT NULL,
    author  varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book_loans (
    id  BINARY(16)  PRIMARY KEY,
    user_id BINARY(16),
    book_id BINARY(16),
    loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 대출일 추가 (추천)
    due_date    TIMESTAMP NOT NULL ,
    return_date TIMESTAMP NULL, -- 반납일 추가 가능
    return_status BOOLEAN DEFAULT FALSE NOT NULL,

    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_books FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);