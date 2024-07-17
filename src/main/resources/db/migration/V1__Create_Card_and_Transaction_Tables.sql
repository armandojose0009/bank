CREATE TABLE IF NOT EXISTS card (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    card_number VARCHAR(16) NOT NULL,
    holder_name VARCHAR(255) NOT NULL,
    expiry_date DATE NOT NULL,
    active BOOLEAN NOT NULL,
    blocked BOOLEAN NOT NULL,
    balance DOUBLE NOT NULL
    );

CREATE INDEX idx_card_number ON card (card_number);

CREATE TABLE IF NOT EXISTS transaction (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           card_id VARCHAR(16) NOT NULL,
    amount DOUBLE NOT NULL,
    transaction_date DATETIME NOT NULL,
    annulled BOOLEAN NOT NULL,
    FOREIGN KEY (card_id) REFERENCES card(card_number)
    );
