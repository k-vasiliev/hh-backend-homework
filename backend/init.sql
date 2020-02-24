CREATE TABLE hh_user(
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    type VARCHAR(16) NOT NULL
);

CREATE TABLE resume(
    resume_id SERIAL PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    user_id INTEGER REFERENCES hh_user(user_id),
    work_experience TEXT NOT NULL,
    contacts TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE company(
    company_id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    user_id INTEGER REFERENCES hh_user(user_id)
);

CREATE TABLE vacancy(
    vacancy_id SERIAL PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    company_id INTEGER REFERENCES company(company_id),
    salary INTEGER,
    description TEXT NOT NULL,
    contacts TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
