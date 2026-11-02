--liquibase formatted sql

--changeset BenedictoGeraldo:009-create-rekening.sql

create table rekening (
                          id bigint generated always as identity,
    no_rekening varchar(12) not null,
    id_nasabah bigint not null,
    id_bank bigint not null,
    constraint pk_rekening primary key (id),
    constraint fk_nasabah foreign key (id_nasabah) references nasabah(id),
    constraint fk_bank foreign key (id_bank) references bank(id)
)
