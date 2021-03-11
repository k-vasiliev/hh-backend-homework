create table area
(
  id      integer not null,
  name    text,
  created timestamp default now(),
updated timestamp default now()
);
create unique index area_id_uindex
on area (id);

create table employer
(
  id          integer not null,
  name        text,
  description text,
  area_id     integer
              constraint employer_area_id_fk
              references area (id)
on update cascade on delete cascade,
created     timestamp default now(),
updated     timestamp default now()
);
create unique index employer_id_uindex
on employer (id);

create table favorite_employer
(
  employer_id integer             not null
  constraint favorite_employer_employer_id_fk
  references employer (id)
on update cascade on delete cascade,
created     timestamp default now(),
updated     timestamp default now(),
comment     text,
views_count integer   default 0
);
create unique index favorite_employer_employer_id_uindex
on favorite_employer (employer_id);

create table vacancy
(
  id          integer not null,
  name        text,
  area_id     integer
              constraint vacancy_area_id_fk
              references area (id)
on update cascade on delete cascade,
created     timestamp,
employer_id integer
constraint vacancy_employer_id_fk
references employer (id)
on update cascade on delete cascade
);
create unique index vacancy_id_uindex
on vacancy (id);

create table favorites_vacancy
(
  vacancy_id  integer not null
  constraint favorites_vacancy_vacancy_id_fk
  references vacancy (id),
  comment text,
created     timestamp default now(),
views_count integer   default 0
);

create table salary
(
  vacancy_id integer not null
  constraint salary_vacancy_id_fk
  references vacancy (id)
on update cascade on delete cascade,
"from"     integer,
"to"       integer,
currency   text,
gross      boolean
);
