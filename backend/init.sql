CREATE TABLE area
(
    id     INTEGER PRIMARY KEY,
    name   VARCHAR(100) NOT NULL
);

CREATE TABLE employer
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    date_create DATE NOT NULL,
    description TEXT,
    area_id     INTEGER NOT NULL REFERENCES area(id),
    comment     TEXT,
    views_count INTEGER NOT NULL
);

CREATE TABLE vacancy_employer
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(200) NOT NULL
);

CREATE TABLE vacancy
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    date_create DATE NOT NULL,
    area_id     INTEGER NOT NULL REFERENCES area(id),
    salary      INTEGER,
    created_at  TEXT,
    employer_id INTEGER NOT NULL REFERENCES vacancy_employer(id),
    views_count INTEGER NOT NULL,
    comment     TEXT
);
