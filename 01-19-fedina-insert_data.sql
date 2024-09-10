-- Заполнение таблицы пользователей
INSERT INTO USERS (user_block_status, login, password, first_name, last_name, user_role)
VALUES (
           'NOT_BLOCKED',
           'admin1',
           '1',
           'admin1',
           'last',
           'ADMIN'
       );

INSERT INTO USERS (user_block_status, login, password, first_name, last_name, user_role)
VALUES (
           'NOT_BLOCKED',
           'admin2',
           '1',
           'admin2',
           'last',
           'ADMIN'
       );

INSERT INTO USERS (user_block_status, login, password, first_name, last_name, user_role)
VALUES (
           'NOT_BLOCKED',
           'moder1',
           '1',
           'moder1',
           'last',
           'MODERATOR'
       );

INSERT INTO USERS (user_block_status, login, password, first_name, last_name, user_role)
VALUES (
           'NOT_BLOCKED',
           'moder2',
           '1',
           'moder2',
           'last',
           'MODERATOR');

INSERT INTO USERS (user_block_status, login, password, first_name, last_name, user_role)
VALUES (
           'NOT_BLOCKED',
           'user1',
           '1',
           'user1',
           'last',
           'USER'
       );

INSERT INTO USERS (user_block_status, login, password, first_name, last_name, user_role)
VALUES (
           'NOT_BLOCKED',
           'user2',
           '1',
           'user2',
           'last',
           'USER'
       );


-- Заполнение таблицы категорий
INSERT INTO CATEGORIES (category) VALUES ('Животные');
INSERT INTO CATEGORIES (category) VALUES ('Техника');
INSERT INTO CATEGORIES (category) VALUES ('Машины');
INSERT INTO CATEGORIES (category) VALUES ('Еда');
INSERT INTO CATEGORIES (category) VALUES ('Путешествия');
INSERT INTO CATEGORIES (category) VALUES ('Программирование');


-- Заполнение таблицы заметок
INSERT INTO Notes (note_status, text, notes_user_id, date_publication)
VALUES (
           'NOT_HIDDEN',
           'Кошек я люблю больше всего',
           (SELECT user_id FROM USERS WHERE login = 'user1'),
           TO_DATE('01-09-2024', 'DD-MM-YYYY')
       );

INSERT INTO Notes (note_status, text, notes_user_id, date_publication)
VALUES (
           'NOT_HIDDEN',
           'Не забыть сделать лабораторную',
           (SELECT user_id FROM USERS WHERE login = 'user1'),
           TO_DATE('02-09-2024', 'DD-MM-YYYY')
       );

INSERT INTO Notes (note_status, text, notes_user_id, date_publication)
VALUES (
           'NOT_HIDDEN',
           'Нужно купить новую машину',
           (SELECT user_id FROM USERS WHERE login = 'user1'),
           TO_DATE('03-09-2024', 'DD-MM-YYYY')
       );

INSERT INTO Notes (note_status, text, notes_user_id, date_publication)
VALUES (
           'NOT_HIDDEN',
           'Лучшее блюдо в Грузии - хинкали!',
           (SELECT user_id FROM USERS WHERE login = 'user2'),
           TO_DATE('03-09-2024', 'DD-MM-YYYY')
       );

-- Заполнение таблицы, которая связывает замети и категории
INSERT INTO note_categories (note_category_note_id, note_category_category_id)
VALUES (
           (SELECT note_id FROM Notes WHERE text = 'Кошек я люблю больше всего'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Животные')
       );

INSERT INTO note_categories (note_category_note_id, note_category_category_id)
VALUES (
           (SELECT note_id FROM Notes WHERE text = 'Не забыть сделать лабораторную'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Программирование')
       );

INSERT INTO note_categories (note_category_note_id, note_category_category_id)
VALUES (
           (SELECT note_id FROM Notes WHERE text = 'Нужно купить новую машину'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Техника')
       );

INSERT INTO note_categories (note_category_note_id, note_category_category_id)
VALUES (
           (SELECT note_id FROM Notes WHERE text = 'Нужно купить новую машину'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Машины')
       );

INSERT INTO note_categories (note_category_note_id, note_category_category_id)
VALUES (
           (SELECT note_id FROM Notes WHERE text = 'Лучшее блюдо в Грузии - хинкали!'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Еда')
       );

INSERT INTO note_categories (note_category_note_id, note_category_category_id)
VALUES (
           (SELECT note_id FROM Notes WHERE text = 'Лучшее блюдо в Грузии - хинкали!'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Путешествия')
       );

-- Заполнение таблицы подписок на категории
INSERT INTO Category_subscriptions (category_subscriptions_user_id, category_subscriptions_category_id)
VALUES (
           (SELECT user_id FROM USERS WHERE login = 'user1'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Животные')
       );

INSERT INTO Category_subscriptions (category_subscriptions_user_id, category_subscriptions_category_id)
VALUES (
           (SELECT user_id FROM USERS WHERE login = 'user1'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Программирование')
       );

INSERT INTO Category_subscriptions (category_subscriptions_user_id, category_subscriptions_category_id)
VALUES (
           (SELECT user_id FROM USERS WHERE login = 'user1'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Техника')
       );

INSERT INTO Category_subscriptions (category_subscriptions_user_id, category_subscriptions_category_id)
VALUES (
           (SELECT user_id FROM USERS WHERE login = 'user1'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Машины')
       );

INSERT INTO Category_subscriptions (category_subscriptions_user_id, category_subscriptions_category_id)
VALUES (
           (SELECT user_id FROM USERS WHERE login = 'user2'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Еда')
       );

INSERT INTO Category_subscriptions (category_subscriptions_user_id, category_subscriptions_category_id)
VALUES (
           (SELECT user_id FROM USERS WHERE login = 'user2'),
           (SELECT category_id FROM CATEGORIES WHERE category = 'Путешествия')
       );

-- Заполнение таблицы подписок на пользователей
INSERT INTO user_subscriptions (user_subscription_user_id, subscription_user_id)
VALUES (
           (SELECT user_id FROM USERS WHERE login = 'user1'),
           (SELECT user_id FROM USERS WHERE login = 'user2')
       );

INSERT INTO user_subscriptions (user_subscription_user_id, subscription_user_id)
VALUES (
           (SELECT user_id FROM USERS WHERE login = 'user2'),
           (SELECT user_id FROM USERS WHERE login = 'user1')
       );

COMMIT;