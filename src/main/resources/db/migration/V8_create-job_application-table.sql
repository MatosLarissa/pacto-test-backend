    CREATE TABLE IF NOT EXISTS job_application (
        id SERIAL PRIMARY KEY,
        status VARCHAR(100),
        user_id INTEGER REFERENCES users(id),
        job_id INTEGER REFERENCES job(id),
        application_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );