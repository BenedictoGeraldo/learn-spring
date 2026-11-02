--liquibase formatted sql

--changeset BenedictoGeraldo:010-add-constraint-rekening.sql


alter table rekening
add constraint uq_no_rekening UNIQUE (no_rekening);