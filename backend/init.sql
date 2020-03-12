CREATE TYPE hh_user_type AS ENUM(
    'EMPLOYER',
    'APPLICANT'
);

CREATE TABLE hh_user(
    id                      BIGSERIAL       PRIMARY KEY,
    created_at              TIMESTAMPTZ     NOT NULL,
    updated_at              TIMESTAMPTZ     NOT NULL,
    name                    VARCHAR         NOT NULL,
    type                    hh_user_type    NOT NULL
);

CREATE TABLE hh_company(
    id                      BIGSERIAL       PRIMARY KEY,
    created_at              TIMESTAMPTZ     NOT NULL,
    updated_at              TIMESTAMPTZ     NOT NULL,
    name                    VARCHAR         NOT NULL,
    user_id                 BIGINT          REFERENCES hh_user(id)
);

CREATE TABLE hh_resume(
    id                      BIGSERIAL       PRIMARY KEY,
    created_at              TIMESTAMPTZ     NOT NULL,
    updated_at              TIMESTAMPTZ     NOT NULL,
    title                   VARCHAR         NOT NULL,
    user_id                 BIGINT          REFERENCES hh_user(id),
    experience              VARCHAR         NOT NULL,
    contacts                VARCHAR         NOT NULL
);

CREATE TABLE hh_vacancy(
    id                      BIGSERIAL       PRIMARY KEY,
    created_at              TIMESTAMPTZ     NOT NULL,
    updated_at              TIMESTAMPTZ     NOT NULL,
    title                   VARCHAR         NOT NULL,
    company_id              BIGINT          REFERENCES hh_company(id),
    salary                  BIGINT          NULL,
    description             VARCHAR         NOT NULL,
    contacts                VARCHAR         NOT NULL
);

CREATE TABLE hh_negotiation(
    id                      BIGSERIAL       PRIMARY KEY,
    created_at              TIMESTAMPTZ     NOT NULL,
    updated_at              TIMESTAMPTZ     NOT NULL,
    vacancy_id              BIGINT          REFERENCES hh_vacancy(id),
    resume_id               BIGINT          REFERENCES hh_resume(id)
);

INSERT INTO hh_user(name, type, created_at, updated_at) VALUES
    ('user #1 emp', 'EMPLOYER', now(), now()),
    ('user #2 emp', 'EMPLOYER', now(), now()),
    ('user #3 app', 'APPLICANT', now(), now()),
    ('user #4 app', 'APPLICANT', now(), now()),
    ('user #5 app', 'APPLICANT', now(), now());

INSERT INTO hh_company(user_id, name, created_at, updated_at) VALUES
    (1, 'emp #1 com #1', now(), now()),
    (1, 'emp #1 com #2', now(), now()),
    (2, 'emp #2 com #3', now(), now());

INSERT INTO hh_resume(user_id, title, experience, contacts, created_at, updated_at) VALUES
    (4, 'app #4 res #1', 'exp1', 'con1', now(), now()),
    (4, 'app #4 res #2', 'exp2', 'con2', now(), now()),
    (5, 'app #5 res #3', 'exp3', 'con3', now(), now());

INSERT INTO hh_vacancy(company_id, title, salary, description, contacts, created_at, updated_at) VALUES
    (1, 'com #1 res #1', 10000, 'desc1', 'con1', now(), now()),
    (2, 'com #2 res #2', 50000, 'desc2', 'con2', now(), now()),
    (3, 'com #3 res #3', 75000, 'desc3', 'con3', now(), now());