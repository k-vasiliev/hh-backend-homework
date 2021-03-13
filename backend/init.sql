CREATE TABLE IF NOT EXISTS area (
    id serial PRIMARY KEY,
    area_id integer NOT NULL,
    area_name varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS company (
    id serial PRIMARY KEY,
    company_id integer NOT NULL,
    company_name varchar NOT NULL,
    date_create timestamp NOT NULL,
    description varchar NOT NULL,
    area_id integer NOT NULL references area(id),
    comment varchar,
    views_count integer NOT NULL
);

CREATE TABLE IF NOT EXISTS salary (
    id serial PRIMARY KEY,
    to_sal integer,
    from_sal integer,
    currency varchar NOT NULL,
    gross boolean  NOT NULL
);

CREATE TABLE IF NOT EXISTS vacancy (
    id serial PRIMARY KEY,
    vacancy_id integer NOT NULL,
    vacancy_name varchar NOT NULL,
    date_create timestamp NOT NULL,
    area_id integer NOT NULL references area(id),
    salary_id integer NOT NULL references salary(id),
    created_at varchar NOT NULL,
    employer_id integer NOT NULL references company(id),
    comment varchar,
    views_count integer NOT NULL
);