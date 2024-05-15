    CREATE TABLE IF NOT EXISTS user_role (
        id SERIAL PRIMARY KEY,
        role_type VARCHAR(100),
        user_id INTEGER REFERENCES users(user_id)
    );