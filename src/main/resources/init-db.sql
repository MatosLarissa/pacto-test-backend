CREATE TABLE IF NOT EXISTS role (
    role_id SERIAL PRIMARY KEY,
    role_type VARCHAR(100)
);

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

CREATE TABLE IF NOT EXISTS user_role (
    id SERIAL PRIMARY KEY,
    role_id INTEGER REFERENCES role(role_id),
    user_id INTEGER REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS requirement (
    requirement_id SERIAL PRIMARY KEY,
    requirement_name VARCHAR(100),
    years_experience VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS user_requirement (
    id SERIAL PRIMARY KEY,
    requirement_id INTEGER REFERENCES requirement(requirement_id),
    user_id INTEGER REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS job (
    job_id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    description TEXT,
    status VARCHAR(100),
    creator_id INTEGER REFERENCES users(user_id),
    last_update TIMESTAMP,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS job_requirement (
    job_requirement_id SERIAL PRIMARY KEY,
    job_id INTEGER REFERENCES job(job_id),
    requirement_id INTEGER REFERENCES requirement(requirement_id)
);

CREATE TABLE IF NOT EXISTS job_application (
    job_application_id SERIAL PRIMARY KEY,
    status VARCHAR(100),
    user_id INTEGER REFERENCES users(user_id),
    job_id INTEGER REFERENCES job(job_id),
    application_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS feedback (
    feedback_id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    content TEXT,
    user_response_id INTEGER,
    user_candidate_id INTEGER REFERENCES users(user_id),
    job_application_id INTEGER REFERENCES job_application(job_application_id),
    response_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS notification (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    content TEXT,
    job_application_id INTEGER REFERENCES job_application(job_application_id),
    creator_job_id INTEGER REFERENCES users(user_id),
    send BOOLEAN,
    read BOOLEAN,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

