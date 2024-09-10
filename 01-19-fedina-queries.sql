-- Получение пользователя по его id
SELECT * FROM users WHERE user_id = ?;
-- Добавление нового пользователя
INSERT INTO users (user_block_status, login, password, first_name, last_name, user_role) VALUES (?, ?, ?, ?, ?, ?);
-- Получение пользователя по логину
SELECT * FROM Users WHERE login = ?;
-- Получение всех пользователей
SELECT * FROM Users;
-- Обновление информации о пользователе по его id
UPDATE Users SET user_block_status = ?, login = ?, password = ?, first_name = ?, last_name = ?, user_role = ? WHERE user_id = ?;
-- Удаление пользователя по его id
DELETE FROM Users WHERE user_id = ?;
-- Подписка пользователя на другого пользователя
INSERT INTO user_subscriptions (user_subscription_user_id, subscription_user_id) VALUES (?, ?);
-- Отмена подписки пользователя на другого пользователя
DELETE FROM User_subscriptions WHERE user_subscription_user_id = ? AND subscription_user_id = ?;
-- Обновление времени активности сессии по ее id
UPDATE Sessions Set active_until = ? WHERE session_id = ?;
-- Создание новой сессии для пользователя
INSERT INTO Sessions (session_user_id, active_until) VALUES (?, ?);
-- Получение всех сессий
SELECT * FROM Sessions s RIGHT JOIN Users u ON s.session_user_id = u.user_id;
-- Удаление всех сессий пользователя
DELETE FROM Sessions WHERE session_user_id = ?;
-- Получение всех сессий пользователя, отсортированных по времени завершения
SELECT * FROM Sessions s LEFT JOIN Users u ON s.session_user_id = u.user_id WHERE u.user_id = ? ORDER BY s.active_until DESC;
-- Получение заметок пользователя
SELECT DISTINCT n.note_id, n.note_status, n.NOTE_STATUS, n.text, n.date_publication, u.user_id, u.first_name, u.last_name, u.user_block_status, u.login, u.password, u.user_role FROM Notes n JOIN Users u ON n.notes_user_id = u.user_id JOIN Note_categories nc ON n.note_id=nc.note_category_note_id JOIN Categories c ON nc.note_category_category_id = c.category_id WHERE n.notes_user_id = ?;
-- Добавление новой заметки
INSERT INTO Notes (note_status, text, notes_user_id, date_publication) VALUES (?, ?, ?, ?);
-- Связывание заметки с категорией
INSERT INTO note_categories (note_category_note_id, note_category_category_id) VALUES (?, ?);
-- Обновление заметки
UPDATE Notes SET note_status = ?, text = ? WHERE note_id = ?;
-- Получение всех категорий для заметки
SELECT c.category_id, c.category FROM Categories c JOIN Note_categories nc ON c.category_id = nc.note_category_category_id WHERE nc.note_category_note_id = ?;
-- Получение всех заметок
SELECT DISTINCT n.note_id, n.note_status, n.NOTE_STATUS, n.text, n.date_publication, u.user_id, u.first_name, u.last_name, u.user_block_status, u.login, u.password, u.user_role FROM Notes n JOIN Users u ON n.notes_user_id = u.user_id JOIN Note_Categories nc ON n.note_id = nc.note_category_note_id JOIN Categories c ON nc.note_category_category_id = c.category_id;
-- Удаление заметки по ее id
DELETE FROM Notes WHERE note_id = ?;
-- Получение заметки
SELECT * FROM Notes n JOIN Users u ON u.user_id = n.notes_user_id WHERE note_id = ?;
-- Удаление всех связей заметки с категориями
DELETE FROM Note_categories WHERE note_category_note_id = ?;
-- Добавление новой категории
INSERT INTO Categories (category) VALUES (?);
-- Обновление информации о категории по ее id
UPDATE Categories SET category WHERE category_id = ?;
-- Получение категории по ее id
SELECT * FROM Categories WHERE category_id = ?;
-- Получение всех категорий
SELECT * FROM Categories;
-- Получение всех категорий, на которые подписан пользователь
SELECT * FROM Category_subscriptions cs JOIN Categories c ON cs.category_subscriptions_category_id = c.category_id WHERE cs.category_subscriptions_user_id = ?;
-- Получение всех подписок пользователя
SELECT * FROM User_subscriptions us JOIN Users u ON us.subscription_user_id = u.user_id WHERE us.user_subscription_user_id = ?;
-- Подписка пользователя на категорию
INSERT INTO Category_subscriptions (category_subscriptions_user_id, category_subscriptions_category_id) VALUES (?, ?)
-- Отмена подписки пользователя на категорию
DELETE FROM Category_subscriptions WHERE category_subscriptions_user_id = ? AND category_subscriptions_category_id = ?