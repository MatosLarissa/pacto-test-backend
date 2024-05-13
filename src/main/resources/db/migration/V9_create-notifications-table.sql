    CREATE TABLE IF NOT EXISTS notifications (
        id SERIAL PRIMARY KEY,
        title VARCHAR(100),
        content TEXT,
        job_application_id INTEGER REFERENCES job_application(id),
        creator_job_id INTEGER REFERENCES users(id),
        send BOOLEAN,
        read BOOLEAN,
        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );