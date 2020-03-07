CREATE TABLE IF NOT EXISTS users
(
  user_id SERIAL PRIMARY KEY,
  name VARCHAR(250) DEFAULT ''::VARCHAR NOT NULL,
  type VARCHAR(50) DEFAULT 'applicant'::VARCHAR NOT NULL,
  last_update TIMESTAMP NOT NULL,
  active BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE IF NOT EXISTS resume
(
  resume_id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  experience VARCHAR(250) DEFAULT ''::VARCHAR NOT NULL,
  head VARCHAR(250) DEFAULT ''::VARCHAR NOT NULL,
  contacts VARCHAR(250) DEFAULT ''::VARCHAR NOT NULL,
  last_update TIMESTAMP NOT NULL,
  active BOOLEAN DEFAULT TRUE NOT NULL,
  FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS company
(
  company_id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  name VARCHAR(250) DEFAULT ''::VARCHAR NOT NULL,
  last_update TIMESTAMP NOT NULL,
  active BOOLEAN DEFAULT TRUE NOT NULL,
  FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS vacancy
(
  vacancy_id SERIAL PRIMARY KEY,
  company_id INTEGER NOT NULL,
  description VARCHAR(250) DEFAULT ''::VARCHAR NOT NULL,
  header VARCHAR(250) DEFAULT ''::VARCHAR NOT NULL,
  contacts VARCHAR(250) DEFAULT ''::VARCHAR NOT NULL,
  salary INTEGER DEFAULT 0 NOT NULL,
  last_update TIMESTAMP NOT NULL,
  active BOOLEAN DEFAULT TRUE NOT NULL,
  FOREIGN KEY(company_id) REFERENCES company(company_id)
);

CREATE TABLE IF NOT EXISTS negotiation
(
  negotiation_id SERIAL PRIMARY KEY,
  resume_id INTEGER NOT NULL,
  vacancy_id INTEGER NOT NULL,
  last_update TIMESTAMP NOT NULL,
  active BOOLEAN DEFAULT TRUE NOT NULL
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
(user_id, experience, head, contacts, last_update, active)
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

INSERT INTO negotiation (resume_id, vacancy_id, last_update, active)
VALUES
(1, 1, now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE),
(2, 2, now()-(random() * 365 * 24 * 3600 * 5) * '1 second'::interval, TRUE);
