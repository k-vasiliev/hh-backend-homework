CREATE TABLE users (
    id serial PRIMARY KEY,
    name varchar(200) DEFAULT ''::varchar NOT NULL,
    type varchar(20) DEFAULT ''::varchar NOT NULL
);

CREATE TABLE company (
    id serial PRIMARY KEY,
    name varchar(200) DEFAULT ''::varchar NOT NULL,
    ownerUserId integer REFERENCES users(id)
);

CREATE TABLE resume (
    id serial PRIMARY KEY,
    userId integer REFERENCES users(id),
    title varchar(200) DEFAULT ''::varchar NOT NULL,
    workExperience varchar(200) DEFAULT ''::varchar NOT NULL,
    contacts varchar(200) DEFAULT ''::varchar NOT NULL
);

CREATE TABLE vacancy (
    id serial PRIMARY KEY,
    title varchar(200) DEFAULT ''::varchar NOT NULL,
    companyId integer REFERENCES company(id),
    salary integer,
    description varchar(200) DEFAULT ''::varchar NOT NULL,
    contacts varchar(200) DEFAULT ''::varchar NOT NULL
);

CREATE TABLE negotiation (
    resumeId integer REFERENCES resume(id),
    vacancyId integer REFERENCES vacancy(id)
);

