    CREATE TABLE IF NOT EXISTS feedback (
        feedback_id SERIAL PRIMARY KEY,
        title VARCHAR(100),
        content TEXT,
        user_response_id INTEGER,
        user_candidate_id INTEGER REFERENCES users(user_id),
        job_application_id INTEGER REFERENCES job_application(job_application_id),
        response_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );
