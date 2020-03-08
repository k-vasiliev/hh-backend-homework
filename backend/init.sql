CREATE TABLE users (
    id bigserial PRIMARY KEY,
    creation_time timestamp NOT NULL,
    last_modify timestamp NOT NULL,
    name varchar(220) DEFAULT ''::varchar NOT NULL,
    type varchar(50) DEFAULT 'PRETENDER'::varchar NOT NULL	
);

CREATE TABLE company(
    id bigserial PRIMARY KEY,
    creation_time timestamp NOT NULL,
    last_modify timestamp NOT NULL,
    name varchar(220) DEFAULT ''::varchar NOT NULL,
    user_id bigint,
    CONSTRAINT fk_company_id FOREIGN KEY (user_id)  REFERENCES users (id)
);

CREATE TABLE vacancy(
    id bigserial PRIMARY KEY,
    creation_time timestamp NOT NULL,
    last_modify timestamp NOT NULL,
    company_name varchar(220) DEFAULT ''::varchar NOT NULL,
    header varchar(220) DEFAULT ''::varchar NOT NULL,
    salary integer,
    description varchar(500) DEFAULT '',
    contacts varchar(500) DEFAULT '',
    user_id bigint,
    CONSTRAINT fk_vacancy_id FOREIGN KEY (user_id)  REFERENCES users (id)	
);

CREATE TABLE resume(
    id bigserial PRIMARY KEY,
    creation_time timestamp NOT NULL,
    last_modify timestamp NOT NULL,
    resume_name varchar(220) DEFAULT ''::varchar NOT NULL,
    header varchar(220) DEFAULT ''::varchar NOT NULL,
    experience varchar(500) DEFAULT '',
    contacts varchar(500) DEFAULT '',
    user_id bigint,
    CONSTRAINT fk_resume_id FOREIGN KEY (user_id)  REFERENCES users (id)	
);

CREATE TABLE negotiation(
    id bigserial PRIMARY KEY,
    creation_time timestamp NOT NULL,
    last_modify timestamp NOT NULL,
    resume_id bigint,
    vacancy_id bigint,
    CONSTRAINT fk_resume_link_id FOREIGN KEY (resume_id)  REFERENCES resume (id),
    CONSTRAINT fk_vacancy_link_id FOREIGN KEY (vacancy_id)  REFERENCES vacancy (id)
);


