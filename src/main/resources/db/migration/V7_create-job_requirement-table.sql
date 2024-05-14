    CREATE TABLE IF NOT EXISTS job_requirement (
        job_requirement_id SERIAL PRIMARY KEY,
        job_id INTEGER REFERENCES job(job_id),
        requirement_id INTEGER REFERENCES requirement(requirement_id)
    );