    CREATE TABLE IF NOT EXISTS job_requirement (
        id SERIAL PRIMARY KEY,
        job_id INTEGER REFERENCES job(id),
        requirement_id INTEGER REFERENCES requirement(id)
    );