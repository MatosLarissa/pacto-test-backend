    CREATE TABLE IF NOT EXISTS user_requirement (
        id SERIAL PRIMARY KEY,
        requirement_id INTEGER REFERENCES requirement(requirement_id),
        user_id INTEGER REFERENCES users(user_id)
    );