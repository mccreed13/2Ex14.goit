create table if not exists note(
    id int auto_increment,
    primary key(id),
    title varchar not null,
    content varchar
);