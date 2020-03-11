CREATE TYPE hh_user_type AS ENUM(
    'EMPLOYER',
    'APPLICANT'
);

CREATE TABLE hh_user(
    hh_user_id                      BIGSERIAL       PRIMARY KEY,
    hh_user_name                    VARCHAR         NOT NULL,
    hh_user_type                    hh_user_type    NOT NULL,
    hh_user_creation_timestamp      TIMESTAMPTZ     NOT NULL,
    hh_user_last_update_timestamp   TIMESTAMPTZ     NOT NULL
);

CREATE TABLE hh_company(
    hh_company_id                       BIGSERIAL       PRIMARY KEY,
    hh_user_id                          BIGINT          REFERENCES hh_user(hh_user_id),
    hh_company_name                     VARCHAR         NOT NULL,
    hh_company_creation_timestamp       TIMESTAMPTZ     NOT NULL,
    hh_company_last_update_timestamp    TIMESTAMPTZ     NOT NULL
);

INSERT INTO hh_user(hh_user_name, hh_user_type, hh_user_creation_timestamp, hh_user_last_update_timestamp) VALUES
    ('user #1 emp', 'EMPLOYER', now(), now()),
    ('user #2 emp', 'EMPLOYER', now(), now()),
    ('user #3 app', 'APPLICANT', now(), now()),
    ('user #4 app', 'APPLICANT', now(), now()),
    ('user #5 app', 'APPLICANT', now(), now());

INSERT INTO hh_company(hh_user_id, hh_company_name, hh_company_creation_timestamp, hh_company_last_update_timestamp) VALUES
    (1, 'emp #1 com #1', now(), now()),
    (1, 'emp #1 com #2', now(), now()),
    (2, 'emp #2 com #3', now(), now());