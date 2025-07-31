CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    username TEXT UNIQUE,
    password TEXT
);
CREATE TABLE IF NOT EXISTS products(
    id TEXT PRIMARY KEY,
    name TEXT,
    description TEXT,
    price INT
);

