--liquibase formatted sql

--changeset BenedictoGeraldo: 006-create-refresh-token.sql

CREATE TABLE IF NOT EXISTS refresh_token (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_refresh_token PRIMARY KEY(id),
    CONSTRAINT uq_token UNIQUE(token),
    CONSTRAINT fk_app_user FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
);

CREATE INDEX idx_refresh_token ON refresh_token(token);