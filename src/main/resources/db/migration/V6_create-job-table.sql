    CREATE TABLE IF NOT EXISTS job (
        job_id SERIAL PRIMARY KEY,
        title VARCHAR(100),
        description TEXT,
        status VARCHAR(100),
        creator_id INTEGER REFERENCES users(user_id),
        last_update TIMESTAMP,
        creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );