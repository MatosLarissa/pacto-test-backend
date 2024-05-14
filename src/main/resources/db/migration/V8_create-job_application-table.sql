    CREATE TABLE IF NOT EXISTS job_application (
        job_application_id SERIAL PRIMARY KEY,
        status VARCHAR(100),
        user_id INTEGER REFERENCES users(user_id),
        job_id INTEGER REFERENCES job(job_id),
        application_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );