CREATE TABLE IF NOT EXISTS users (
                                   user_id serial PRIMARY KEY,
                                   name VARCHAR(250),
                                   type VARCHAR(50),
                                   last_update timestamp,
                                   active BOOLEAN
);

CREATE TABLE IF NOT EXISTS resume (
                                    resume_id serial PRIMARY KEY,
                                    user_id INTEGER,
                                    exp VARCHAR(50),
                                    head VARCHAR(250),
                                    contacts VARCHAR(250),
                                    last_update timestamp,
                                    active BOOLEAN,
                                    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS company (
                                     company_id serial PRIMARY KEY,
                                     user_id INTEGER,
                                     name VARCHAR(250),
                                     last_update timestamp,
                                     active BOOLEAN,
                                     FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS vacancy (
                                     vacancy_id serial PRIMARY KEY,
                                     company_id INTEGER,
                                     description VARCHAR(250),
                                     header VARCHAR(250),
                                     contacts VARCHAR(250),
                                     salary INTEGER,
                                     last_update timestamp,
                                     active BOOLEAN,
                                     FOREIGN KEY(company_id) REFERENCES company(company_id)
);

INSERT INTO users
(name, type, last_update, active)
VALUES ('Pasha', 'APPLICANT',
        now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE),
       ('Masha', 'APPLICANT',
       now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE),
       ('Misha', 'EMPLOYER',
        now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE),
       ('Kolya', 'EMPLOYER',
        now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE);

INSERT INTO resume
(user_id, exp, head, contacts, last_update, active)
 VALUES (1, 'большой опыт', 'java developer', 'мои контакты',
         now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE),
        (2, 'малый опыт', 'java', 'контакты Маши',
        now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE);

INSERT INTO company (user_id, name, last_update, active)
VALUES
(3, 'ZiBBEN', now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE),
(4, 'Yand', now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE);

INSERT INTO vacancy (company_id, description, header, contacts, salary, last_update, active)
VALUES
(1, 'Требуется java разработчик', 'java', 'контакты компании 1', 100000,
 now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE),
(2, 'Требуется c++ разработчик', 'c++', 'контакты компании 2', 90000,
 now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE);
