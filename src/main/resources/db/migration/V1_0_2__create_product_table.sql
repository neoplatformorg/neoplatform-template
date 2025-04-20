-- V1_0_2__create_product_table.sql

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    create_time DATETIME,
    update_time DATETIME,
    deleted INT DEFAULT 0
);