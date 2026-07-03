-- Создание таблицы
create table Product
(
    id    serial unique not null,
    name  char(20)      not null,
    price integer check (price > -1)
);

-- Добавление данных
insert into Product (name, price)
values ('Milk', 89);