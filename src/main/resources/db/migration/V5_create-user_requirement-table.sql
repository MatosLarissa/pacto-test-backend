    CREATE TABLE IF NOT EXISTS user_requirement (
        id SERIAL PRIMARY KEY,
        requirement VARCHAR(255),
        years_experience VARCHAR(100),
        user_id INTEGER REFERENCES users(id)
    );