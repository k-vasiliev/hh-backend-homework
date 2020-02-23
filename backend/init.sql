CREATE TABLE users (
    id serial PRIMARY KEY,
    date_create TIMESTAMP,
    date_modify TIMESTAMP,
    name VARCHAR(255),
    user_type VARCHAR(50)
);

CREATE TABLE companies (
     id serial PRIMARY KEY,
     date_create TIMESTAMP,
     date_modify TIMESTAMP,
     name VARCHAR(255),
     user_id INTEGER,
     FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE resumes (
    id serial PRIMARY KEY,
    date_create TIMESTAMP,
    date_modify TIMESTAMP,
    title VARCHAR(255),
    work_experience TEXT,
    contacts TEXT,
    user_id INTEGER,
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE vacancies (
   id serial PRIMARY KEY,
   date_create TIMESTAMP,
   date_modify TIMESTAMP,
   title VARCHAR(255),
   salary INTEGER,
   description TEXT,
   contacts TEXT,
   company_id INTEGER,
   FOREIGN KEY(company_id) REFERENCES companies(id)
);

CREATE TABLE negotiations (
   id serial PRIMARY KEY,
   date_create TIMESTAMP,
   date_modify TIMESTAMP,
   resume_id INTEGER,
   vacancy_id INTEGER,
   FOREIGN KEY(resume_id) REFERENCES resumes(id),
   FOREIGN KEY(vacancy_id) REFERENCES vacancies(id)
);

INSERT INTO users(date_create, date_modify, name, user_type) VALUES
(now(), now(), 'Денис', 'applicant'),
(now(), now(), 'Александр', 'applicant'),
(now(), now(), 'Саша', 'applicant'),
(now(), now(), 'Маша', 'applicant'),
(now(), now(), 'Аркадий Волож', 'employer'),
(now(), now(), 'Борис Добродеев', 'employer');

INSERT INTO companies(date_create, date_modify, name, user_id) VALUES
(now(), now(), 'Яндекс', 5),
(now(), now(), 'Mail.ru', 6);

INSERT INTO resumes(date_create, date_modify, title, work_experience, contacts, user_id) VALUES
(now(), now(), 'Программист Java', '3 года', 'Тел: 8(999) 555-44-55', 1),
(now(), now(), 'Java-junior developer', '1 год', 'Тел: 8(495) 111-23-34', 2),
(now(), now(), 'Аналитик', '2 года', 'Тел: 8(854) 123-45-67', 3),
(now(), now(), 'Социолог-исследователь', '4 года', 'Тел: 8(900) 000-11-22', 4);

INSERT INTO vacancies(date_create, date_modify, title, salary, description, contacts, company_id) VALUES
(now(), now(), 'Программист-стажер', 60000, 'Программист-стажер в Яндекс-дзен', 'Анна: 8(900) 200-00-23', 1),
(now(), now(), 'Программист-стажер', 60000, 'Программист-стажер в Яндекс-музыку', 'Анна: 8(900) 200-00-23', 1),
(now(), now(), 'Стажер-тестировщик', 50000, 'Стажер-тестировщик в Auto.ru', 'Игорь: 8(900) 200-00-25', 1),
(now(), now(), 'Аналитик', 80000, 'Аналитик в юлу', 'Петр: 8(495) 212-22-34', 2);

INSERT INTO negotiations(date_create, date_modify, resume_id, vacancy_id) VALUES
(now(), now(), 1, 2),
(now(), now(), 3, 4);
