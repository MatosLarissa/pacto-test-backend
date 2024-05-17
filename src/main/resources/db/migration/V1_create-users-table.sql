    CREATE TABLE IF NOT EXISTS users (
        user_id SERIAL PRIMARY KEY,
        first_name VARCHAR(100),
        last_name VARCHAR(100),
        email VARCHAR(100) UNIQUE,
        password VARCHAR(100),
        role VARCHAR(100),
        status VARCHAR(100),
        years_experience VARCHAR(100),
        last_activity TIMESTAMP,
        creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );