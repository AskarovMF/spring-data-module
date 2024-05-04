insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Лондон', 'Кент');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Санта-Барбара', 'Мэн');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Сан Франциско', '1 стрит');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Эшленд', 'Нордвинд');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Ричмонд', 'Дакота');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Гемпшир', 'Эвергрин');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Москва', 'Садовая 16');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Ереван', 'Ленина 54');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Санкт-Петербург', 'Колнтай 35');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Москва', 'Богданова 2');
insert into ADDRESS(id, CITY, STREET) values (nextval('ADDRESS_SEQ'), 'Москва', 'Миронова 76');

insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Герберт', 'Уэллс', null, 1);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Дуглас', 'Адамс', '+1805132689', 2);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Фрэнк', 'Герберт', '+1806132689', 3);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Эрнест', 'Клайн', '+1805142835', 4);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'энди', 'вейер', '+1805178395', 5);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Артур', 'кларк', '+1805199172', 6);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Юрий', 'Домбровский', '+78532638978', 7);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'СТАЛИК', 'ХАНКИШИЕВ', '+72531538928', 8);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Виктор', 'Дашкевич', '+7561738939', 9);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Андрей', 'Васильев', '+73568951213', 10);
insert into AUTHORS(id, FIRST_NAME, LAST_NAME, PHONE, ADDRESS_ID)
values (nextval('AUTHORS_SEQ'), 'Наталья', 'Мамлеева', '+79194652233', 11);

insert into CATEGORIES(id, NAME) values (nextval('CATEGORIES_SEQ'), 'фантастика');
insert into CATEGORIES(id, NAME) values (nextval('CATEGORIES_SEQ'), 'фэнтези');
insert into CATEGORIES(id, NAME) values (nextval('CATEGORIES_SEQ'), 'кулинария');
insert into CATEGORIES(id, NAME) values (nextval('CATEGORIES_SEQ'), 'детектив');
insert into CATEGORIES(id, NAME) values (nextval('CATEGORIES_SEQ'), 'роман');

insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 1, 'английский', 'Война миров');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 1, 'английский', 'Автостопом по галактике');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 1, 'английский', 'Дюна');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 1, 'английский', 'Первому игроку приготовиться');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), false, 1, 'английский', 'Марсианин');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), false, 1, 'английский', 'Одиссея 2001');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 2, 'русский', 'Охотник на ведьм');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 3, 'русский', 'Счастье кулинара');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 3, 'русский', '101 идея для десерта');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 4, 'русский', 'Дело ведет Самойлов');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 5, 'русский', 'Современная золушка');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 1, 'английский', 'Машина времени');
insert into BOOKS (id, active, category_id, language, title)
values (nextval('BOOKS_SEQ'), true, 5, 'русский', 'Любовь вне времени');

insert into BOOKS_AUTHORS(author_id, book_id) VALUES (1, 1);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (2, 2);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (3, 3);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (4, 4);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (5, 5);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (6, 6);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (7, 7);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (8, 8);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (9, 9);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (10, 10);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (11, 11);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (1, 12);
insert into BOOKS_AUTHORS(author_id, book_id) VALUES (11, 13);