--liquibase formatted sql

--changeset BenedictoGeraldo:004-advanced.sql


SELECT * FROM KUCING;

--AGREGATE
SELECT
    COUNT(1) AS TOTAL_KUCING,
    MAX(umur_bulan) KUCING_TERTUA,
    MIN(umur_bulan) KUCING_TERMUDA,
    AVG(umur_bulan) AVG_UMUR_KUCING
FROM KUCING;

--GROUP BY
SELECT
    gender,
    COUNT(1) AS TOTAL_KUCING,
    MAX(umur_bulan) KUCING_TERTUA,
    MIN(umur_bulan) KUCING_TERMUDA,
    AVG(umur_bulan) AVG_UMUR_KUCING
FROM KUCING
GROUP BY gender ;

--HAVING
SELECT
    AVG(umur_bulan) UMUR_KUCING,
    COUNT(*) JUMLAH_KUCING
FROM kucing
HAVING count(*) > 1
AND AVG(UMUR_BULAN) > 7;


SELECT
    nama_kucing,
    CASE
        WHEN umur_bulan <= 5 THEN 'KITTEN'
        WHEN umur_bulan >=6 THEN 'REMAJA'
    END
FROM kucing;