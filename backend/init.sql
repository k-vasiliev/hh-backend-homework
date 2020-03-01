-- DROP TABLE resume;
-- DROP TABLE vacancy;
-- DROP TABLE company;
-- DROP TABLE users;
CREATE TYPE user_type AS ENUM ('employer', 'applicant');

CREATE TABLE users(
     users_id SERIAL PRIMARY KEY,
     name VARCHAR(256),
     user_type user_type NOT NULL,
     creation_date TIMESTAMP NOT NULL,
     update_date TIMESTAMP NOT NULL
);

CREATE TABLE resume(
     resume_id SERIAL PRIMARY KEY,
     users_id INTEGER NOT NULL,
     title VARCHAR(256) DEFAULT ''::VARCHAR NOT NULL,
     work_experience TEXT,
     contacts TEXT,
     creation_date TIMESTAMP NOT NULL,
     update_date TIMESTAMP NOT NULL,
     FOREIGN KEY (users_id) REFERENCES users(users_id)
);

CREATE TABLE company(
     company_id SERIAL PRIMARY KEY,
     users_id INTEGER NOT NULL,
     title VARCHAR(256) DEFAULT ''::VARCHAR NOT NULL,
     creation_date TIMESTAMP NOT NULL,
     update_date TIMESTAMP NOT NULL,
     FOREIGN KEY (users_id) REFERENCES users(users_id)
);

CREATE TABLE vacancy(
     vacancy_id SERIAL PRIMARY KEY,
     company_id INTEGER NOT NULL,
     title VARCHAR(256) DEFAULT ''::VARCHAR NOT NULL,
     compensation bigint DEFAULT 0,
     description TEXT,
     contacts TEXT,
     creation_date TIMESTAMP NOT NULL,
     update_date TIMESTAMP NOT NULL,
     FOREIGN KEY (company_id) REFERENCES company(company_id)
);

INSERT INTO users (name, user_type, creation_date, update_date) VALUES
    ('Павел Иванов', 'applicant', now(), now()),
    ('Татьяна Семеновна', 'applicant', now(), now()),
    ('Сергей Сидоров', 'applicant', now(), now()),
    ('Александр Петров', 'employer', now(), now());

INSERT INTO resume (title, user_id, work_experience, contacts, creation_date, update_date) VALUES
    ('Программист Java', 1, '3 года', 'hh1@mail.com', now(), now()),
    ('Программист C++', 1, '5 лет', 'hh1@mail.com', now(), now()),
    ('Инженер', 2, 'без опыта', 'hh2@gmail.com', now(), now()),
    ('Доцент', 3, '10 лет', 'hh3@mgu.com', now(), now());

INSERT INTO company (user_id, title, creation_date, update_date) VALUES
    (4, 'Мини HH', now(), now());

INSERT INTO vacancy (company_id, title, compensation, description, contacts, creation_date, update_date) VALUES
    (1, 'java разработчик', 70000, 'разработка сервиса для подбора персонала', 'contacts_hh@hh.com', now(), now()),
    (1, 'охранник', 50000, 'дежуство на КПП', 'contacts_hh@hh.com', now(), now()),
    (1, 'Аналитик', 75000, 'Анализ рисков новых проектов', 'contacts_hh@hh.com', now(), now());