create table resume(
     resume_id SERIAL PRIMARY KEY,
     user_id INTEGER,
     description VARCHAR(512),
     is_active BOOLEAN
);


create table hhuser(
     user_id SERIAL PRIMARY KEY,
     first_name VARCHAR(124),
     last_name VARCHAR(124)
);

insert into hhuser (first_name, last_name) values ('Sarah', 'Connor');
insert into hhuser (first_name, last_name) values ('John', 'Connor');