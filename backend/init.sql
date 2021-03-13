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

CREATE TABLE vac_employer
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(200)
);

CREATE TABLE vacancy
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(200),
    date_create DATE,
    area_id     INTEGER NOT NULL REFERENCES area(id),
    salary      INTEGER,
    created_at  TEXT,
    employer_id INTEGER NOT NULL REFERENCES vac_employer(id),
    views_count INTEGER,
    comment     TEXT
);
