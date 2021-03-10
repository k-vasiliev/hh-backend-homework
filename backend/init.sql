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
