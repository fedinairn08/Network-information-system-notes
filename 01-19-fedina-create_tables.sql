--Таблица подписок на пользователей
CREATE TABLE user_subscriptions(
    user_subscription_user_id INTEGER NOT NULL,
    subscription_user_id INTEGER NOT NULL
);

ALTER TABLE
    user_subscriptions ADD PRIMARY KEY(user_subscription_user_id, subscription_user_id);

--Таблица для связи между заметками и категориями
CREATE TABLE note_categories(
    note_category_note_id INTEGER NOT NULL,
    note_category_category_id INTEGER NOT NULL
);

ALTER TABLE
    note_categories ADD PRIMARY KEY(note_category_note_id, note_category_category_id);

--Таблица пользователей
CREATE TABLE users(
    user_id INTEGER,
    user_block_status VARCHAR(255) CHECK
        (user_block_status IN('')) NOT NULL,
        login VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        first_name VARCHAR(255) NOT NULL,
        last_name VARCHAR(255) NOT NULL,
        user_role VARCHAR(255) NOT NULL
);

CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER users_on_insert
    BEFORE INSERT ON users
    FOR EACH ROW
BEGIN
    SELECT users_seq.nextval
    INTO :new.user_id
    FROM dual;
END;

ALTER TABLE
    users ADD PRIMARY KEY(user_id);

--Таблица сессий
CREATE TABLE sessions(
    session_id INTEGER,
    session_user_id INTEGER NOT NULL,
    active_until TIMESTAMP(0) NOT NULL
);

CREATE SEQUENCE sessions_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER sessions_on_insert
    BEFORE INSERT ON sessions
    FOR EACH ROW
BEGIN
    SELECT sessions_seq.nextval
    INTO :new.session_id
    FROM dual;
END;

ALTER TABLE
    sessions ADD PRIMARY KEY(session_id);

--Таблица категорий заметок
CREATE TABLE categories(
    category_id INTEGER,
    category VARCHAR(255) NOT NULL
);

CREATE SEQUENCE categories_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER categories_on_insert
  BEFORE INSERT ON categories
  FOR EACH ROW
BEGIN
    SELECT categories_seq.nextval
    INTO :new.category_id
    FROM dual;
END;

ALTER TABLE
    categories ADD PRIMARY KEY(category_id);

--Таблица подписок на категории
CREATE TABLE category_subscriptions(
    category_subscriptions_user_id INTEGER NOT NULL,
    category_subscriptions_category_id INTEGER NOT NULL
);

ALTER TABLE
    category_subscriptions ADD PRIMARY KEY(category_subscriptions_user_id, category_subscriptions_category_id);

--Таблица заметок
CREATE TABLE notes(
    note_id INTEGER,
    note_status VARCHAR(255) CHECK
        (note_status IN('')) NOT NULL,
        text VARCHAR(255) NOT NULL,
        notes_user_id INTEGER NOT NULL,
        date_publication TIMESTAMP(0) NOT NULL
);

CREATE SEQUENCE notes_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER notes_on_insert
  BEFORE INSERT ON notes
  FOR EACH ROW
BEGIN
    SELECT notes_seq.nextval
    INTO :new.note_id
    FROM dual;
END;

ALTER TABLE
    notes ADD PRIMARY KEY(note_id);

ALTER TABLE
    user_subscriptions ADD CONSTRAINT "usersubscriptions_user_id_foreign" FOREIGN KEY(user_subscription_user_id) REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE
    sessions ADD CONSTRAINT "sessions_user_id_foreign" FOREIGN KEY(session_user_id) REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE
    note_categories ADD CONSTRAINT "notecategories_note_category_foreign" FOREIGN KEY(note_category_category_id) REFERENCES categories(category_id) ON DELETE CASCADE;
ALTER TABLE
    category_subscriptions ADD CONSTRAINT "categorysubscriptions_category_id_foreign" FOREIGN KEY(category_subscriptions_category_id) REFERENCES categories(category_id) ON DELETE CASCADE;
ALTER TABLE
    user_subscriptions ADD CONSTRAINT "usersubscriptions_user_subscription_foreign" FOREIGN KEY(subscription_user_id) REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE
    category_subscriptions ADD CONSTRAINT "categorysubscriptions_user_id_foreign" FOREIGN KEY(category_subscriptions_user_id) REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE
    note_categories ADD CONSTRAINT "notecategories_note_id_foreign" FOREIGN KEY(note_category_note_id) REFERENCES notes(note_id) ON DELETE CASCADE;
ALTER TABLE
    notes ADD CONSTRAINT "notes_user_id_foreign" FOREIGN KEY(notes_user_id) REFERENCES users(user_id) ON DELETE CASCADE;