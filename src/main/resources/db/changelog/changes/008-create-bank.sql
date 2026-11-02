--liquibase formatted sql

--changeset BenedictoGeraldo:008-create-bank.sql

create table bank (
    id bigint generated always as identity,
    bank_name varchar(50) not null,
    constraint pk_bank primary key (id)
)