    CREATE TABLE IF NOT EXISTS feedback (
        id SERIAL PRIMARY KEY,
        title VARCHAR(100),
        content TEXT,
        user_response_id INTEGER REFERENCES users(user_id),
        user_candidate_id INTEGER REFERENCES users(user_id),
        job_application_id INTEGER REFERENCES job_application(job_id),
        job_id INTEGER REFERENCES job(job_id),
        response_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );