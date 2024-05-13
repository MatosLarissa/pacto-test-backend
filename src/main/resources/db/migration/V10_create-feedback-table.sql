    CREATE TABLE IF NOT EXISTS feedback (
        id SERIAL PRIMARY KEY,
        title VARCHAR(100),
        content TEXT,
        user_response_id INTEGER REFERENCES users(id),
        user_candidate_id INTEGER REFERENCES users(id),
        job_application_id INTEGER REFERENCES job_application(id),
        job_id INTEGER REFERENCES job(id),
        response_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );