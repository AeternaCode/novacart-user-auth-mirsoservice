CREATE TABLE address (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,

                         user_id BIGINT NOT NULL,

                         address_type VARCHAR(20) NOT NULL DEFAULT 'HOME',

                         address_line_1 VARCHAR(255) NOT NULL,
                         address_line_2 VARCHAR(255),

                         city VARCHAR(100) NOT NULL,
                         state VARCHAR(100) NOT NULL,
                         country VARCHAR(100) NOT NULL,
                         postal_code VARCHAR(20) NOT NULL,
                         is_default BOOLEAN NOT NULL DEFAULT FALSE,

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                             ON UPDATE CURRENT_TIMESTAMP,
                         deleted_at TIMESTAMP NULL,
                         created_by BIGINT,
                         updated_by BIGINT,

                         CONSTRAINT fk_address_user
                             FOREIGN KEY (user_id)
                                 REFERENCES users(id)
);