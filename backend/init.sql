SET timezone = 'Europe/Moscow';
TRUNCATE area CASCADE;
TRUNCATE employer CASCADE;
TRUNCATE vacancy CASCADE;

DROP TABLE area CASCADE;
DROP TABLE employer CASCADE;
DROP TABLE vacancy CASCADE;

CREATE TABLE IF NOT EXISTS area (
    id  INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS  employer (
    id  INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_create DATE NOT NULL DEFAULT CURRENT_DATE,
    description TEXT,
    area INTEGER NOT NULL REFERENCES area ON DELETE SET NULL,
    comment TEXT,
    views_count INTEGER default 0
);

CREATE TABLE IF NOT EXISTS vacancy (
    id  INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_create DATE NOT NULL DEFAULT CURRENT_DATE,
    area INTEGER NOT NULL REFERENCES area ON DELETE SET NULL,
    salary_to INTEGER,
    salary_from INTEGER,
    salary_curr VARCHAR(20),
    salary_gross BOOLEAN,
    created_at TIMESTAMPTZ,
    employer INTEGER NOT NULL REFERENCES employer ON DELETE CASCADE,
    views_count INTEGER default 0
);