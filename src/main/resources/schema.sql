    create table if not exists board_entity2 (
       id bigint generated by default as identity,
        contents varchar(255),
        title varchar(255),
        primary key (id)
    );