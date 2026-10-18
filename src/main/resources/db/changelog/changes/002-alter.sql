--liquibase formatted sql

--changeset BenedictoGeraldo:002-alter.sql

ALTER TABLE jenis_kucing ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;