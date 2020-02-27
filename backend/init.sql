create table users (
    user_id serial primary key,
    name varchar(150) not null,
    type varchar(8) not null,
    creation_date timestamp not null,
    last_update_date timestamp not null
    -- todo add type constraint
);

create table resumes (
    resume_id serial primary key,
    title varchar(150) not null,
    user_id integer not null,
    experience varchar(250) not null,
    contacts varchar(50) not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    foreign key (user_id) references users(user_id)
);

create table companies (
    company_id serial primary key,
    name varchar(150) not null,
    user_id integer not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    foreign key (user_id) references users(user_id)
);

create table vacancies (
    vacancy_id serial primary key,
    title varchar(150) not null,
    company_id integer not null,
    salary bigint,
    description text not null,
    contacts varchar(50) not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    foreign key (company_id) references companies(company_id)
);

create table responses (
    response_id serial primary key,
    vacancy_id integer not null,
    resume_id integer not null,
    creation_date timestamp not null,
    last_update_date timestamp not null,
    foreign key (vacancy_id) references vacancies(vacancy_id),
    foreign key (resume_id) references resumes(resume_id)
);

insert into users (name, type, creation_date, last_update_date) values ('Просто Саня', 'employee', now(), now());
insert into users (name, type, creation_date, last_update_date) values ('Анна Иванова', 'employer', now(), now());
insert into users (name, type, creation_date, last_update_date) values ('Денис Кузнецов', 'employer', now(), now());
insert into users (name, type, creation_date, last_update_date) values ('Евгений Аркадьевич', 'employee', now(), now());
insert into users (name, type, creation_date, last_update_date) values ('Менеджер Екатерина', 'employer', now(), now());


insert into resumes (title, user_id, experience, contacts, creation_date, last_update_date) values ('Разработчик Java', 1, '1 год', '783-1233', now(), now());
insert into resumes (title, user_id, experience, contacts, creation_date, last_update_date) values ('QA инженер (РФТ)', 4, '3 года', '133-8282', now(), now());
insert into resumes (title, user_id, experience, contacts, creation_date, last_update_date) values ('QA инженер (АФТ)', 4, 'нет опыта', '133-8282', now(), now());

insert into companies (name, user_id, creation_date, last_update_date) values ('HeadHunter', 2, now(), now());
insert into companies (name, user_id, creation_date, last_update_date) values ('Яндекс', 3, now(), now());

insert into vacancies (title, company_id, salary, description, contacts, creation_date, last_update_date)
    values ('Java Developer', 1, 2000, 'В команду поиска требуется разработчик', '800-1234', now(), now());
insert into vacancies (title, company_id, salary, description, contacts, creation_date, last_update_date)
    values ('Нужен Java разработчик', 2, 2500, 'Срочно', '178-9881', now(), now());
insert into vacancies (title, company_id, salary, description, contacts, creation_date, last_update_date)
    values ('Инженер-тестировщик', 2, 1700, 'В команду Алисы требуется тестировщик', '178-9881', now(), now());

insert into responses (vacancy_id, resume_id, date) values (1, 1, now(), now());
insert into responses (vacancy_id, resume_id, date) values (2, 1, now(), now());
insert into responses (vacancy_id, resume_id, date) values (3, 2, now(), now());
insert into responses (vacancy_id, resume_id, date) values (3, 3, now(), now());
