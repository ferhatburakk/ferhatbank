CREATE TABLE customer (
    id VARCHAR(255) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    customerCode VARCHAR(255) UNIQUE NOT NULL,
    accountList_id VARCHAR(255),
    FOREIGN KEY (accountList_id) REFERENCES account(id)
);

CREATE TABLE account (
    id VARCHAR(255) PRIMARY KEY AUTO_INCREMENT,
    accountNumber VARCHAR(255) UNIQUE NOT NULL,
    ibanNumber VARCHAR(255) UNIQUE NOT NULL,
    balance BIGINT NOT NULL
);