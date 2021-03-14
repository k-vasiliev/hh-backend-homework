CREATE TABLE areas
(
    area_id    serial  PRIMARY KEY,
    name  text
);

CREATE TABLE favourite_employers
(
    id serial PRIMARY KEY,
    hh_id         integer NOT NULL,
    name text NOT NULL,
    comment text DEFAULT '',
    description text,
    views_count integer DEFAULT 0,
    date_create timestamp NOT NULL DEFAULT NOW(),
    area_id   integer  NOT NULL REFERENCES areas (area_id)
);

CREATE TABLE favourite_vacancies
(
    vacancy_id    serial  PRIMARY KEY,
    hh_id         integer NOT NULL,
    employer_id          integer,
    employer_name        text,
    area_id   integer  NOT NULL REFERENCES areas (area_id),
    name text NOT NULL,
    compensation_from    integer,
    compensation_to      integer,
    compensation_gross   boolean,
    creation_time timestamp NOT NULL DEFAULT NOW(),
    views_count integer DEFAULT 0,
    comment text DEFAULT '',
    created_at timestamp NOT NULL
);
