create table area
(
    id   bigint not null
        constraint area_pkey
            primary key,
    name varchar(255)
);

alter table area
    owner to hh;

INSERT INTO area (name)
VALUES ('Республика Марий Эл'),
       ('Республика Татарстан'),
       ('Удмуртская Республика'),
       ('Чувашская Республика'),
       ('Забайкальский край'),
       ('Иркутская область'),
       ('Красноярский край'),
       ('Республика Бурятия'),
       ('Республика Саха (Якутия)'),
       ('Республика Тыва'),
       ('Республика Хакасия'),
       ('Кировская область'),
       ('Нижегородская область'),
       ('Рязанская область'),
       ('Алтайский край'),
       ('Кемеровская область'),
       ('Новосибирская область'),
       ('Омская область'),
       ('Республика Алтай'),
       ('Томская область'),
       ('Москва'),
       ('Московская область'),
       ('Амурская область'),
       ('Еврейская АО'),
       ('Камчатский край'),
       ('Магаданская область'),
       ('Приморский край'),
       ('Сахалинская область'),
       ('Хабаровский край'),
       ('Чукотский АО'),
       ('Архангельская область'),
       ('Калининградская область'),
       ('Ленинградская область'),
       ('Мурманская область'),
       ('Ненецкий АО'),
       ('Новгородская область'),
       ('Псковская область'),
       ('Республика Карелия'),
       ('Республика Коми'),
       ('Санкт-Петербург'),
       ('Смоленская область'),
       ('Владимирская область'),
       ('Вологодская область'),
       ('Ивановская область'),
       ('Костромская область'),
       ('Тверская область'),
       ('Ярославская область'),
       ('Оренбургская область'),
       ('Пензенская область'),
       ('Республика Мордовия'),
       ('Самарская область'),
       ('Саратовская область'),
       ('Ульяновская область'),
       ('Курганская область'),
       ('Пермский край'),
       ('Республика Башкортостан'),
       ('Свердловская область'),
       ('Тюменская область'),
       ('Ханты-Мансийский АО - Югра'),
       ('Челябинская область'),
       ('Ямало-Ненецкий АО'),
       ('Кабардино-Балкарская республика'),
       ('Карачаево-Черкесская Республика'),
       ('Краснодарский край'),
       ('Республика Адыгея'),
       ('Республика Дагестан'),
       ('Республика Ингушетия'),
       ('Республика Северная Осетия-Алания'),
       ('Ставропольский край'),
       ('Чеченская республика'),
       ('Белгородская область'),
       ('Брянская область'),
       ('Воронежская область'),
       ('Калужская область'),
       ('Курская область'),
       ('Липецкая область'),
       ('Орловская область'),
       ('Тамбовская область'),
       ('Тульская область'),
       ('Астраханская область'),
       ('Республика Крым'),
       ('Волгоградская область'),
       ('Республика Калмыкия'),
       ('Ростовская область');

create table employer
(
    id          bigint not null
        constraint employer_pkey
            primary key,
    description text,
    name        varchar(255),
    area_id     bigint
        constraint fkqwv7by5nh76d5l2dvkva4lhwf
            references area
);

alter table employer
    owner to hh;

create table vacancy
(
    id           bigint not null
        constraint vacancy_pkey
            primary key,
    createdat    timestamp,
    name         text,
    currency     text,
    start_salary double precision,
    gross        boolean,
    end_salary   double precision,
    area_id      bigint
        constraint fksajma553prtccg29gaoaer4um
            references area,
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