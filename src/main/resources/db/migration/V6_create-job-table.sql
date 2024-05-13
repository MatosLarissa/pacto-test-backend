    CREATE TABLE IF NOT EXISTS job (
        id SERIAL PRIMARY KEY,
        title VARCHAR(100),
        description TEXT,
        status VARCHAR(100),
        creator_id INTEGER REFERENCES users(id),
        last_update TIMESTAMP,
        creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );