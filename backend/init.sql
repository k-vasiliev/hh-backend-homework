-- create database hh;
-- \c hh
CREATE TABLE users (
    id serial PRIMARY KEY,
    name varchar(200) NOT NULL,
    type varchar(20) NOT NULL
);

CREATE TABLE company (
    id serial PRIMARY KEY,
    name varchar(200) NOT NULL,
    owner_user_id integer REFERENCES users(id) NOT NULL
);

CREATE TABLE resume (
    id serial PRIMARY KEY,
    user_id integer REFERENCES users(id) NOT NULL,
    title varchar(200) NOT NULL,
    creation_date timestamp NOT NULL,
    work_experience varchar(200) NOT NULL,
    contacts varchar(200) NOT NULL
);

CREATE TABLE vacancy (
    id serial PRIMARY KEY,
    title varchar(200) NOT NULL,
    company_id integer REFERENCES company(id) NOT NULL,
    salary integer,
    description varchar(200) NOT NULL,
    contacts varchar(200) NOT NULL
);

CREATE TABLE negotiation (
    id serial PRIMARY KEY,
    resume_id integer REFERENCES resume(id) NOT NULL,
    vacancy_id integer REFERENCES vacancy(id) NOT NULL
);

-- create user hh with encrypted password 'hh';
-- grant all privileges on database hh to hh;
-- grant all privileges on all tables in schema public to hh;
