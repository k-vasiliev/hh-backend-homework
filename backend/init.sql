SET timezone = 'Europe/Moscow';

CREATE TABLE area (
    id  INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE employer (
    id  INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_create DATE NOT NULL DEFAULT CURRENT_DATE,
    description TEXT,
    area INTEGER NOT NULL REFERENCES area ON DELETE SET NULL,
    comment TEXT,
    popularity INTEGER default 0,
    views_count INTEGER default 0
);

CREATE TABLE vacancy (
    id  INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_create DATE NOT NULL DEFAULT CURRENT_DATE,
    area INTEGER NOT NULL REFERENCES area ON DELETE SET NULL,
<<<<<<< HEAD
    "to" INTEGER,
    "from" INTEGER,
    currency VARCHAR(20),
    gross BOOLEAN,
=======
    salary_to INTEGER,
    salary_from INTEGER,
    salary_curr VARCHAR(20),
    salary_gross BOOLEAN,
>>>>>>> 6ce77fe156b7228300b41e8b359dd101282a5771
    created_at TIMESTAMPTZ,
    employer INTEGER NOT NULL REFERENCES employer ON DELETE CASCADE,
    popularity INTEGER default 0,
    views_count INTEGER default 0
);