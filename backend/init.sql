create table employer
(
    id          bigint not null
        constraint employer_pkey
            primary key,
    areaid      bigint,
    description text,
    name        varchar(255)
);

alter table employer
    owner to hh;

create table vacancy
(
    id           bigint not null
        constraint vacancy_pkey
            primary key,
    areaid       bigint,
    createdat    timestamp,
    name         text,
    currency     text,
    start_salary double precision,
    gross        boolean,
    end_salary   double precision,
    employer_id  bigint
        constraint fkeepnrqfc3aewgiwvmi0ywxs6b
            references employer
);

alter table vacancy
    owner to hh;

create table favourite
(
    id          bigint not null
        constraint favourite_pkey
            primary key,
    comment     varchar(255),
    datecreate  timestamp,
    linkid      bigint,
    type        integer,
    viewscount  bigint,
    employer_id bigint
        constraint fk4yq50796p050s3dk6rv95aefd
            references employer,
    vacancy_id  bigint
        constraint fk3yqwo4uqt7qc9at2mt441c8sp
            references vacancy
);

alter table favourite
    owner to hh;