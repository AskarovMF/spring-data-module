drop table books;
create table if not exists books (
    id integer not null auto_increment,
    name varchar(150) not null,
    language varchar(50),
    category varchar(50)
);