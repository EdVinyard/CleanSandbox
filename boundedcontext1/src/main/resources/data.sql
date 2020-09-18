drop table if exists greetings;

create table greetings (
    id int auto_increment primary key,
    language varchar(64),
    text nvarchar(1024)
);
