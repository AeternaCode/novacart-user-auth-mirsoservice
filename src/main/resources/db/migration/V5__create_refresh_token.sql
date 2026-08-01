CREATE TABLE refresh_tokens (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                token VARCHAR(512) NOT NULL UNIQUE,

                                user_id BIGINT NOT NULL,

                                expires_at DATETIME NOT NULL,

                                revoked BOOLEAN NOT NULL DEFAULT FALSE,

                                created_at DATETIME NOT NULL,

                                updated_at DATETIME NOT NULL,

                                deleted_at DATETIME NULL,

                                created_by BIGINT,

                                updated_by BIGINT,

                                CONSTRAINT fk_refresh_token_user
                                    FOREIGN KEY (user_id)
                                        REFERENCES users(id)
);