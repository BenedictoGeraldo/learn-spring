--liquibase formatted sql

--changeset BenedictoGeraldo:003-dml.sql

INSERT INTO penitipan_kucing_store (store_name, alamat)
VALUES ('Meow Meow', 'Jalan gotong royong No.5 Jakarta timur, DKI Jakarta'),
       ('Leave Here', 'Jalan Durian No.1 Jakarta Barat, DKI Jakarta.');

INSERT INTO jenis_kucing(jenis, deskripsi)
VALUES ('Anggora', 'bulu lebat dan sangat aktif di siang hari'),
       ('Persia', 'bulu lebat, wajah cenderung datar, sifat tenang'),
       ('British Shorthair', 'tubuh kokoh dengan wajah bulat dan bulu tebal');

INSERT INTO cat_owner(name, alamat, email)
VALUES ('geral', 'Jakarta', 'geraldo@gmail.com'),
       ('hayabusa', 'Bandung', 'haya@gmail.com');

INSERT INTO kucing(owner_id, store_id, jenis_kucing_id, nama_kucing, umur_bulan, gender)
VALUES ('2', '1', '3', 'Cimeng', 5, 'P'),
       ('1', '2', '1', 'Ceppi', 10, 'L')