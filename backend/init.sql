create table if not exists area (
  area_id serial primary key,
  area_name varchar(100) not null
);

create table if not exists favorite_employer (
  employer_id serial primary key,
  company_name varchar(100) not null,
  company_description text,
  area_id int,
  creation_time timestamptz,
  comment varchar(100),
  views_count int
);

create table if not exists favorite_vacancy (
  vacancy_id serial primary key,
  vacancy_name varchar(100) not null,
  archiving_time timestamptz,
  area_id int,
  compensation_from int,
  compensation_to int,
  compensation_gross boolean,
  compensation_currency varchar(3), -- poor cryptocurrencies should follow ISO convention
  creation_time timestamptz,
  employer_id int not null references favorite_employer(employer_id),
  views_count int,
  comment varchar(100)
);
