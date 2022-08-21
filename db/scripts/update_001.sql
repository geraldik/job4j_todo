CREATE TABLE IF NOT EXISTS account
(
    account_id       SERIAL PRIMARY KEY,
    name     varchar(50),
    login    varchar(20) UNIQUE NOT NULL ,
    password varchar(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS items
(
    items_id          SERIAL PRIMARY KEY,
    name        varchar(50),
    description text,
    created     timestamp,
    done        boolean,
    account_id int REFERENCES account(account_id)
);
