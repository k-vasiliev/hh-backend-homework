CREATE FUNCTION update_modification_date() RETURNS trigger AS $$
    BEGIN
           NEW.modification_date = now();
           RETURN NEW;
    END;
$$ language 'plpgsql';

CREATE TABLE users (
    id serial PRIMARY KEY NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    modification_date TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL
);

CREATE TRIGGER update_user_modification_date BEFORE UPDATE ON users
FOR EACH ROW EXECUTE FUNCTION update_modification_date();

CREATE TABLE companies (
    id serial PRIMARY KEY NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    modification_date TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TRIGGER update_company_modification_date BEFORE UPDATE ON companies
FOR EACH ROW EXECUTE FUNCTION update_modification_date();

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

CREATE TRIGGER update_resume_modification_date BEFORE UPDATE ON resumes
FOR EACH ROW EXECUTE FUNCTION update_modification_date();

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

CREATE TRIGGER update_vacancy_modification_date BEFORE UPDATE ON vacancies
FOR EACH ROW EXECUTE FUNCTION update_modification_date();

CREATE TABLE negotiations (
   id serial PRIMARY KEY NOT NULL,
   creation_date TIMESTAMP NOT NULL,
   modification_date TIMESTAMP NOT NULL,
   resume_id INTEGER NOT NULL,
   vacancy_id INTEGER NOT NULL,
   FOREIGN KEY (resume_id) REFERENCES resumes(id),
   FOREIGN KEY (vacancy_id) REFERENCES vacancies(id)
);

CREATE TRIGGER update_negotiations_modification_date BEFORE UPDATE ON negotiations
FOR EACH ROW EXECUTE FUNCTION update_modification_date();