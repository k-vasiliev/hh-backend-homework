CREATE TABLE users (
	id serial PRIMARY KEY,
	user_name varchar(255),
	user_type varchar(255),
	creation_date timestamp,
	correction_date timestamp DEFAULT current_timestamp
);

CREATE TABLE resume (
	id serial PRIMARY KEY,
	resume_title varchar(255),
	user_id INTEGER REFERENCES users(id),
	work_expirience varchar(255),
	contacts varchar(255),
	creation_date timestamp,
	correction_date timestamp DEFAULT current_timestamp
);

CREATE TABLE company (
	id serial PRIMARY KEY,
	company_name varchar(255),
	user_id INTEGER REFERENCES users(id), 
	creation_date timestamp,
	correction_date timestamp DEFAULT current_timestamp
);

CREATE TABLE vacancy (
	id serial PRIMARY KEY,
	vacancy_title varchar(255),
	company_id INTEGER REFERENCES company(id),
	compensation INTEGER DEFAULT 0 NOT NULL,
	description varchar(255),
	contacts varchar(255),
	creation_date timestamp,
	correction_date timestamp DEFAULT current_timestamp
);

CREATE TABLE negotiation (
	id serial PRIMARY KEY,
	vacancy_id INTEGER REFERENCES vacancy(id),
	resume_id INTEGER REFERENCES resume(id),
	creation_date timestamp,
	correction_date timestamp DEFAULT current_timestamp
);

INSERT INTO users(creation_date, correction_date, user_name, user_type) VALUES
(now(), now(), 'Антон', 'applicant'),
(now(), now(), 'Алексей', 'applicant'),
(now(), now(), 'Егор', 'applicant'),
(now(), now(), 'Сергей', 'applicant'),
(now(), now(), 'Иван', 'employer'),
(now(), now(), 'Артем', 'employer');

INSERT INTO company(creation_date, correction_date, company_name, user_id) VALUES
(now(), now(), 'ООО ААА', 5),
(now(), now(), 'ОАО АОА', 6);

INSERT INTO resume(creation_date, correction_date, resume_title, work_expirience, contacts, user_id) VALUES
(now(), now(), 'Работник 1', '1', 'телефон', 1),
(now(), now(), 'Работник 2', '2', 'телефон', 2),
(now(), now(), 'Работник 3', '3', 'е-майл', 3),
(now(), now(), 'Работник 4', '4', 'факс', 4);

INSERT INTO vacancy(creation_date, correction_date, vacancy_title, compensation, description, contacts, company_id) VALUES
(now(), now(), 'Вакансия 1', 10000, 'Хорошая вакансия', 'телефон', 1),
(now(), now(), 'Вакансия 2', 20000, 'Отличная вакансия', 'телефон', 1),
(now(), now(), 'Вакансия 3', 30000, 'Легендарная вакансия', 'телефон', 1),
(now(), now(), 'Вакансия 4', 40000, 'Эпичная вакансия', 'телефон', 2);

INSERT INTO negotiation(creation_date, correction_date, resume_id, vacancy_id) VALUES
(now(), now(), 1, 2),
(now(), now(), 3, 4);