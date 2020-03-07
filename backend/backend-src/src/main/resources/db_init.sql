CREATE TABLE users( user_id     serial PRIMARY KEY, name        varchar(50), is_company  boolean default false, UNIQUE(name, is_company) );
CREATE TABLE resume (
    resume_id serial PRIMARY KEY,
    user_id integer not NULL,
    title varchar(50),
    experience character varying(50),
    contacts character varying(50),
    creation_time timestamp default CURRENT_TIMESTAMP,
    UNIQUE(user_id, title, experience, contacts)
);
CREATE TABLE company ( company_id serial PRIMARY KEY, user_id integer not NULL, title character varying(50) UNIQUE, creation_time timestamp default CURRENT_TIMESTAMP );
CREATE TABLE vacancy ( vacancy_id serial PRIMARY KEY, company_id integer not NULL, title character varying(50), salary integer, description character varying(50), contacts character varying(50), creation_time timestamp default CURRENT_TIMESTAMP, UNIQUE(company_id, title) );
create table vacancy_response ( vacancy_response_id serial PRIMARY KEY, vacancy_id integer not NULL, resume_id integer not NULL, creation_time timestamp default CURRENT_TIMESTAMP, UNIQUE(vacancy_id, resume_id) );
