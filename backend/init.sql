CREATE TABLE users (
    id serial PRIMARY KEY NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    modification_date TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL
);

CREATE TABLE companies (
    id serial PRIMARY KEY NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    modification_date TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE resumes (
    id serial PRIMARY KEY NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    modification_date TIMESTAMP NOT NULL,
    title VARCHAR(255) NOT NULL,
    work_experience TEXT NOT NULL,
    contacts TEXT NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE vacancies (
   id serial PRIMARY KEY NOT NULL,
   creation_date TIMESTAMP NOT NULL,
   modification_date TIMESTAMP NOT NULL,
   title VARCHAR(255) NOT NULL,
   salary INTEGER NOT NULL,
   description TEXT NOT NULL,
   contacts TEXT NOT NULL,
   company_id INTEGER NOT NULL,
   FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE negotiations (
   id serial PRIMARY KEY NOT NULL,
   creation_date TIMESTAMP NOT NULL,
   modification_date TIMESTAMP NOT NULL,
   resume_id INTEGER NOT NULL,
   vacancy_id INTEGER NOT NULL,
   FOREIGN KEY (resume_id) REFERENCES resumes(id),
   FOREIGN KEY (vacancy_id) REFERENCES vacancies(id)
);