CREATE TABLE area
(
    id     INTEGER PRIMARY KEY,
    name   VARCHAR(100)
);

CREATE TABLE employer
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(200),
    date_create DATE,
    description TEXT,
    area_id     INTEGER NOT NULL REFERENCES area(id),
    comment     TEXT,
    views_count INTEGER
);

CREATE TABLE vacancy
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(200),
    date_create DATE,
    area_id     INTEGER NOT NULL REFERENCES area(id),
    salary      REAL,
    created_at  DATE,
    employer_id INTEGER NOT NULL REFERENCES employer(id),
    views_count INTEGER
);
