DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description,  calories)  VALUES
    (100000,'2015-04-20 10:00:00'::TIMESTAMP, 'Завтрак' ,500),
    (100000,'2015-04-20 14:00:00'::TIMESTAMP, 'Обед' ,1000),
    (100000,'2015-04-20 19:00:00'::TIMESTAMP, 'Ужин' ,500),
    (100001,'2015-04-20 10:00:00'::TIMESTAMP, 'Ужин_Админ' ,700),
    (100000,'2015-04-20 14:00:00'::TIMESTAMP, 'Обед_Адми' ,1200),
    (100000,'2015-04-20 19:00:00'::TIMESTAMP, 'Ужин_Адми' ,800);


