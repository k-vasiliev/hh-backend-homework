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
	work_expirience INTEGER DEFAULT 0 NOT NULL,
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