INSERT INTO users(creation_date, correction_date, user_name, user_type) VALUES
(now(), now(), 'Денис', 'applicant'),
(now(), now(), 'Александр', 'applicant'),
(now(), now(), 'Саша', 'applicant'),
(now(), now(), 'Маша', 'applicant'),
(now(), now(), 'Аркадий Волож', 'employer'),
(now(), now(), 'Борис Добродеев', 'employer');

INSERT INTO company(creation_date, correction_date, company_name, user_id) VALUES
(now(), now(), 'Яндекс', 5),
(now(), now(), 'Mail.ru', 6);

INSERT INTO resume(creation_date, correction_date, resume_title, work_expirience, contacts, user_id) VALUES
(now(), now(), 'Программист Java', 3, 'Тел: 8(999) 555-44-55', 1),
(now(), now(), 'Java-junior developer', 1, 'Тел: 8(495) 111-23-34', 2),
(now(), now(), 'Аналитик', 2, 'Тел: 8(854) 123-45-67', 3),
(now(), now(), 'Социолог-исследователь', 4, 'Тел: 8(900) 000-11-22', 4);

INSERT INTO vacancy(creation_date, correction_date, vacancy_title, compensation, description, contacts, company_id) VALUES
(now(), now(), 'Программист-стажер', 60000, 'Программист-стажер в Яндекс-дзен', 'Анна: 8(900) 200-00-23', 1),
(now(), now(), 'Программист-стажер', 60000, 'Программист-стажер в Яндекс-музыку', 'Анна: 8(900) 200-00-23', 1),
(now(), now(), 'Стажер-тестировщик', 50000, 'Стажер-тестировщик в Auto.ru', 'Игорь: 8(900) 200-00-25', 1),
(now(), now(), 'Аналитик', 80000, 'Аналитик в юлу', 'Петр: 8(495) 212-22-34', 2);

INSERT INTO negotiation(creation_date, correction_date, resume_id, vacancy_id) VALUES
(now(), now(), 1, 2),
(now(), now(), 3, 4);