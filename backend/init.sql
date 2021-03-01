CREATE TABLE employer
(
  id      serial primary key,
  created timestamptz default now(),
  updated timestamptz,
  name    text not null,
);

CREATE TABLE vacancy
(
  id                 serial primary key,
  created            timestamptz      default now(),
  updated            timestamptz,
  employer_id        integer not null references employer (id),
  position_name      text    not null,
  description        text    not null,
  compensation_from  integer,
  compensation_to    integer,
  compensation_gross boolean not null default false
);