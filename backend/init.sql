drop table if exists users CASCADE;
drop table if exists resume;
drop table if exists company;
drop table if exists vacancy;

CREATE TABLE users(
	user_id		serial PRIMARY KEY,
	name		varchar(50),
	is_company	boolean default false
);

CREATE TABLE resume
(
  resume_id serial PRIMARY KEY,
  user_id integer,
  title varchar(50),
  experience character varying(50),
  contacts character varying(50),
  creation_time timestamp default CURRENT_TIMESTAMP
);

CREATE TABLE company
(
  company_id serial PRIMARY KEY,
  user_id integer,
  title character varying(50)
);

CREATE TABLE vacancy
(
  vacancy_id serial PRIMARY KEY,
  company_id integer,
  title character varying(50),
  salary integer,
  description character varying(50),
  contacts character varying(50),
  creation_time timestamp default CURRENT_TIMESTAMP
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
--- Create sample 6 companies
---
insert 
into company(title, user_id)
select 
	'Company name ' || cid, uid 
from
	(
		select * 
		from generate_series(1,2) as uid
	) as users,
	(
		select *
		from generate_series(1,3) as cid
	) as tries;


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
