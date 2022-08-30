CREATE TABLE IF NOT EXISTS account
(
    account_id SERIAL PRIMARY KEY,
    name       varchar(50),
    login      varchar(20) UNIQUE NOT NULL,
    password   varchar(20)        NOT NULL
);
CREATE TABLE IF NOT EXISTS items
(
    items_id    SERIAL PRIMARY KEY,
    name        text,
    description text,
    created     timestamp,
    done        boolean
);
ALTER TABLE items
    ADD COLUMN account_id int REFERENCES account (account_id);

CREATE TABLE IF NOT EXISTS category
(
    category_id SERIAL PRIMARY KEY,
    name       varchar(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS items_category
(
    items_id int REFERENCES items(items_id),
    category_id int REFERENCES category(category_id),
    CONSTRAINT category_items_pkey PRIMARY KEY (items_id, category_id)
);

