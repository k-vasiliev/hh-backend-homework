DROP TABLE resume;
DROP TABLE vacancy;
DROP TABLE company;
DROP TABLE userHH;

CREATE TABLE userHH(
     user_id SERIAL PRIMARY KEY,
     name VARCHAR(256),
     user_type integer DEFAULT 0 NOT NULL,
     creation_date timestamp NOT NULL,
     update_date timestamp NOT NULL,
     CONSTRAINT user_type_validate CHECK ((user_type = ANY (ARRAY[0, 1])))
);

CREATE TABLE resume(
     resume_id SERIAL PRIMARY KEY,
     user_id INTEGER DEFAULT 0 NOT NULL,
     title varchar(256) DEFAULT ''::varchar NOT NULL,
     work_experience text,
     contacts text,
     creation_date timestamp NOT NULL,
     update_date timestamp NOT NULL,
     FOREIGN KEY (user_id) REFERENCES userHH(user_id)
);

CREATE TABLE company(
     company_id SERIAL PRIMARY KEY,
     user_id INTEGER DEFAULT 0 NOT NULL,
     title varchar(256) DEFAULT ''::varchar NOT NULL,
     creation_date timestamp NOT NULL,
     update_date timestamp NOT NULL,
     FOREIGN KEY (user_id) REFERENCES userHH(user_id)
);

CREATE TABLE vacancy(
     vacancy_id SERIAL PRIMARY KEY,
     company_id INTEGER DEFAULT 0 NOT NULL,
     title varchar(256) DEFAULT ''::varchar NOT NULL,
     compensation bigint DEFAULT 0,
     description text,
     contacts text,
     creation_date timestamp NOT NULL,
     update_date timestamp NOT NULL,
     FOREIGN KEY (company_id) REFERENCES company(company_id)
);

INSERT INTO userHH (name, user_type, creation_date, update_date) VALUES
    ('Павел Иванов', 0, now(), now()),
    ('Татьяна Семеновна', 0, now(), now()),
    ('Сергей Сидоров', 0, now(), now()),
    ('Александр Петров', 1, now(), now());

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