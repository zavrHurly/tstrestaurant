DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dish;
DELETE FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', 'admin'), --100000
  ('Guest', 'guest@gmail.com', 'guest'),--100001
  ('User', 'user@yandex.ru', 'password'), --100002
  ('1', '1', '1'); --100003

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_ADMIN', 100003);

INSERT INTO restaurant (creationDay, name) VALUES
  ('2023-03-08 0:00:00', 'restaurant1'),   --100003
  ('2023-03-08 0:00:00', 'restaurant2'),   --100004
  ('2023-03-08 0:00:00', 'restaurant3');   --100005

INSERT INTO dish (name, price, restaurant_id) VALUES
  ('dish1', 200, 100003),   --100006
  ('dish2', 300, 100004), --100007
  ('dish3', 100, 100005), --100008
  ('dish4', 50, 100005);  --100008

INSERT INTO vote (user_id, restaurant_id, creation_date) VALUES
  (100000, 100006, '2023-03-08 0:00:00'),
  (100001, 100006, '2023-03-08 0:00:00');