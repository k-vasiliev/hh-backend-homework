drop table if exists users CASCADE;
drop table if exists resume CASCADE;
drop table if exists company CASCADE;
drop table if exists vacancy CASCADE;
drop table if exists vacancy_response CASCADE;

CREATE TABLE users(
	user_id		serial PRIMARY KEY,
	name		varchar(50),
	is_company	boolean default false,
	UNIQUE(name, is_company)
);

CREATE TABLE resume
(
  resume_id serial PRIMARY KEY,
  user_id integer not NULL,
  title varchar(50),
  experience character varying(50),
  contacts character varying(50),
  creation_time timestamp default CURRENT_TIMESTAMP
);

CREATE TABLE company
(
  company_id serial PRIMARY KEY,
  user_id integer not NULL,
  title character varying(50) UNIQUE,
  creation_time timestamp default CURRENT_TIMESTAMP
);

CREATE TABLE vacancy
(
  vacancy_id serial PRIMARY KEY,
  company_id integer not NULL,
  title character varying(50),
  salary integer,
  description character varying(50),
  contacts character varying(50),
  creation_time timestamp default CURRENT_TIMESTAMP,
  UNIQUE(company_id, title)
);

create table vacancy_response (
	vacancy_response_id serial PRIMARY KEY,
	vacancy_id integer not NULL,
	resume_id integer not NULL,
	creation_time timestamp default CURRENT_TIMESTAMP,
	UNIQUE(vacancy_id, resume_id)
);

---
--- Create sample 12 users
---
insert 
into users(name, is_company)
select 'user ' || uid, false 
from generate_series(1,12) as uid;

---
--- Create sample 14 company user accounts
---
insert 
into users(name, is_company)
select 'company_user ' || uid, true 
from generate_series(1,14) as uid;

---
--- Create sample 10 Resumes
---
insert 
into resume(title, user_id, experience, contacts)
select
	'Resume Title ' || uid || ' - ' || try, uid, 'No experience', 'Address ' || uid
from
	(
		select * 
		from generate_series(1,5) as uid
	) as users,
	(
		select *
		from generate_series(1,5) as try
	) as tries;

---
--- Create sample 6 companies : 3 company from two users
---
insert 
into company(title, user_id)
select 
	'Company name ' || (cid*2 + uid), uid 
from
	(
		select * 
		from generate_series(1,2) as uid
	) as users,
	(
		select *
		from generate_series(1,3) as cid
	) as tries;


--
-- Create 12 vacancies: 2 from each of 6 companies
--
insert 
into vacancy(company_id, title, salary, description, contacts)
select 
	cid, 'Vacancy '|| cid || '-' || vid, 10000*((random()* 5)::integer) + 1000000,'Vacancy Description '|| cid || '-' || vid, 'Contacts '|| cid || '-' || vid
from
	(
		select * 
		from generate_series(1,6) as cid
	) as users,
	(
		select *
		from generate_series(1,2) as vid
	) as tries;

--
-- Create 12 responses: 1 for each vacancy
--
insert into vacancy_response(vacancy_id, resume_id)
select 
	vid,  (random() * 9)::integer + 1
from 
	generate_series(1,12) as vid;
