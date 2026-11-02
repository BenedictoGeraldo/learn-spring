--liquibase formatted sql

--changeset BenedictoGeraldo:007-create-nasabah.sql

create table nasabah (
    id bigint generated always as identity,
    name varchar(25) not null,
    constraint pk_nasabah primary key(id)
)