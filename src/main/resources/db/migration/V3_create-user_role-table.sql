    CREATE TABLE IF NOT EXISTS user_role (
        id SERIAL PRIMARY KEY,
        role_id INTEGER REFERENCES role(role_id),
        user_id INTEGER REFERENCES users(user_id)
    );