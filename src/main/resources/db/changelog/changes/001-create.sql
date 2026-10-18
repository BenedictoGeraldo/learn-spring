--liquibase formatted sql

--changeset BenedictoGeraldo:001-create.sql

CREATE TABLE IF NOT EXISTS penitipan_kucing_store(
    id INT GENERATED ALWAYS AS IDENTITY,
    store_name VARCHAR(50) NOT NULL,
    alamat TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_penitipan_kucing_store PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jenis_kucing (
    id INT GENERATED ALWAYS AS IDENTITY,
    jenis VARCHAR(25) NOT NULL,
    deskripsi TEXT NOT NULL,
    -- created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (tambahkan sebagai alter column)
    CONSTRAINT pk_jenis_kucing PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cat_owner (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(25) NOT NULL,
    alamat TEXT NOT NULL,
    email VARCHAR(50),
    CONSTRAINT pk_cat_owner PRIMARY KEY (id)
);

CREATE TYPE gender_kucing AS ENUM ('L', 'P');


CREATE TABLE IF NOT EXISTS kucing (
    id INT GENERATED ALWAYS AS IDENTITY,
    owner_id INT NOT NULL,
    store_id INT NOT NULL,
    jenis_kucing_id INT NOT NULL,
    nama_kucing VARCHAR(25) NOT NULL,
    umur_bulan INT NULL,
    gender gender_kucing NULL,
    created_ad TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_kucing PRIMARY KEY(id),
    CONSTRAINT fk_kucing_owner FOREIGN KEY(owner_id) REFERENCES cat_owner(id) ON DELETE CASCADE,
    CONSTRAINT fk_store_id FOREIGN KEY(store_id) REFERENCES penitipan_kucing_store(id),
    CONSTRAINT fk_jenis_kucing_id FOREIGN KEY(jenis_kucing_id) REFERENCES jenis_kucing(id)
);


CREATE INDEX idx_kucing_owner_id ON kucing(owner_id);
CREATE INDEX idx_kucing_store_id ON kucing(store_id);
CREATE INDEX idx_kucing_jenis_id ON kucing(jenis_kucing_id);