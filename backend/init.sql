create table users (
    user_id serial primary key,
    name varchar(150) not null,
    type varchar(9) not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    check (type = 'applicant' or type = 'employer')
);

create table resume (
    resume_id serial primary key,
    title varchar(150) not null,
    user_id integer not null,
    experience varchar(250) not null,
    contacts varchar(50) not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    foreign key (user_id) references users(user_id)
);

create table company (
    company_id serial primary key,
    name varchar(150) not null,
    user_id integer not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    foreign key (user_id) references users(user_id)
);

create table vacancy (
    vacancy_id serial primary key,
    title varchar(150) not null,
    company_id integer not null,
    salary bigint,
    description text not null,
    contacts varchar(50) not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    foreign key (company_id) references company(company_id)
);

create table negotiation (
    negotiation_id serial primary key,
    vacancy_id integer not null,
    resume_id integer not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    foreign key (vacancy_id) references vacancy(vacancy_id),
    foreign key (resume_id) references resume(resume_id)
);

insert into users (name, type, creation_date, last_update_date) values ('Просто Саня', 'applicant', now(), now());
insert into users (name, type, creation_date, last_update_date) values ('Анна Иванова', 'employer', now(), now());
insert into users (name, type, creation_date, last_update_date) values ('Денис Кузнецов', 'employer', now(), now());
insert into users (name, type, creation_date, last_update_date) values ('Евгений Аркадьевич', 'applicant', now(), now());
insert into users (name, type, creation_date, last_update_date) values ('Менеджер Екатерина', 'employer', now(), now());


insert into resume (title, user_id, experience, contacts, creation_date, last_update_date) values ('Разработчик Java', 1, '1 год', '783-1233', now(), now());
insert into resume (title, user_id, experience, contacts, creation_date, last_update_date) values ('QA инженер (РФТ)', 4, '3 года', '133-8282', now(), now());
insert into resume (title, user_id, experience, contacts, creation_date, last_update_date) values ('QA инженер (АФТ)', 4, 'нет опыта', '133-8282', now(), now());

insert into company (name, user_id, creation_date, last_update_date) values ('HeadHunter', 2, now(), now());
insert into company (name, user_id, creation_date, last_update_date) values ('Яндекс', 3, now(), now());

insert into vacancy (title, company_id, salary, description, contacts, creation_date, last_update_date)
    values ('Java Developer', 1, 2000, 'В команду поиска требуется разработчик', '800-1234', now(), now());
insert into vacancy (title, company_id, salary, description, contacts, creation_date, last_update_date)
    values ('Нужен Java разработчик', 2, 2500, 'Срочно', '178-9881', now(), now());
insert into vacancy (title, company_id, salary, description, contacts, creation_date, last_update_date)
    values ('Инженер-тестировщик', 2, 1700, 'В команду Алисы требуется тестировщик', '178-9881', now(), now());

insert into negotiation (vacancy_id, resume_id, creation_date, last_update_date) values (1, 1, now(), now());
insert into negotiation (vacancy_id, resume_id, creation_date, last_update_date) values (2, 1, now(), now());
insert into negotiation (vacancy_id, resume_id, creation_date, last_update_date) values (3, 2, now(), now());
insert into negotiation (vacancy_id, resume_id, creation_date, last_update_date) values (3, 3, now(), now());
