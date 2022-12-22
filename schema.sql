DROP DATABASE IF EXISTS paf_day24;

CREATE DATABASE paf_day24;

USE paf_day24;

CREATE TABLE orders(
    order_id CHAR(8) NOT NULL,
    order_date DATE NOT NULL,
    customer_name VARCHAR(128) NOT NULL,
    ship_address VARCHAR(128) NOT NULL,
    notes TEXT,
    tax DECIMAL(2,2) DEFAULT '0.05',

    PRIMARY KEY(order_id)
);

CREATE TABLE order_details (
    id INT AUTO_INCREMENT NOT NULL,
    product VARCHAR(64) NOT NULL,
    unit_price DECIMAL(3,2) NOT NULL,
    discount DECIMAL(2,2) DEFAULT '0.01',
    quantity INT NOT NULL,
    order_id CHAR(8) NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_order_id
        FOREIGN KEY(order_id) REFERENCES orders(order_id)
);