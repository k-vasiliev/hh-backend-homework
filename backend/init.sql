CREATE TABLE area
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR
) IF NOT EXISTS;

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

CREATE TABLE employer
(
    id            BIGINT       NOT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    area_id       BIGINT       NULL,
    CONSTRAINT pk_employer PRIMARY KEY (id)
);

ALTER TABLE employer
    ADD CONSTRAINT FK_EMPLOYER_ON_AREA FOREIGN KEY (area_id) REFERENCES area (id);

CREATE TABLE favourite
(
    id          BIGINT       NOT NULL,
    employer_id BIGINT       NULL,
    vacancy_id  BIGINT       NULL,
    type        INT          NULL,
    comment     VARCHAR(255) NULL,
    viewsCount  BIGINT       NULL,
    dateCreate  datetime     NULL,
    CONSTRAINT pk_favourite PRIMARY KEY (id)
);

ALTER TABLE favourite
    ADD CONSTRAINT FK_FAVOURITE_ON_EMPLOYER FOREIGN KEY (employer_id) REFERENCES employer (id);

ALTER TABLE favourite
    ADD CONSTRAINT FK_FAVOURITE_ON_VACANCY FOREIGN KEY (vacancy_id) REFERENCES vacancy (id);

CREATE TABLE vacancy
(
    id          BIGINT       NOT NULL,
    name        VARCHAR(255) NULL,
    createdAt   datetime     NULL,
    employer_id BIGINT       NULL,
    area_id     BIGINT       NULL,
    `from`      DOUBLE       NULL,
    `to`        DOUBLE       NULL,
    currency    VARCHAR(255) NULL,
    gross       BIT(1)       NULL,
    CONSTRAINT pk_vacancy PRIMARY KEY (id)
);

ALTER TABLE vacancy
    ADD CONSTRAINT FK_VACANCY_ON_AREA FOREIGN KEY (area_id) REFERENCES area (id);

ALTER TABLE vacancy
    ADD CONSTRAINT FK_VACANCY_ON_EMPLOYER FOREIGN KEY (employer_id) REFERENCES employer (id);