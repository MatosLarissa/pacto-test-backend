    CREATE TABLE IF NOT EXISTS user_requirement (
        id SERIAL PRIMARY KEY,
        requirement_id INTEGER REFERENCES requirement(id),
        user_id INTEGER REFERENCES users(id)
    );