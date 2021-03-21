DROP TABLE IF EXISTS area CASCADE;
DROP TABLE IF EXISTS employer CASCADE;
DROP TYPE IF EXISTS salary CASCADE;
DROP TABLE IF EXISTS vacancy CASCADE;

CREATE TABLE area
(
    area_id   serial PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE employer
(
    employer_id          serial PRIMARY KEY,
    name        text NOT NULL,
    date_create timestamp NOT NULL,
    description text,
    area_id        integer NOT NULL references area (area_id),
    comment     text,
    views_count integer
);

CREATE TABLE vacancy
(
    vacancy_id   serial PRIMARY KEY,
    name text,
    date_create timestamp NOT NULL,
    area_id integer NOT NULL references area (area_id),
    compensation_from integer,
    compensation_to integer,
    currency varchar,
    gross boolean,
    created_at text NOT NULL,
    employer_id integer NOT NULL references employer(employer_id),
    views_count integer,
    comment text
);
