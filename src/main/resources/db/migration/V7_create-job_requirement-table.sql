    CREATE TABLE IF NOT EXISTS job_requirement (
        id SERIAL PRIMARY KEY,
        requirement VARCHAR(255),
        years_experience VARCHAR(100),
        job_id INTEGER REFERENCES job(id)
    );