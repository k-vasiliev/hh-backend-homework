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