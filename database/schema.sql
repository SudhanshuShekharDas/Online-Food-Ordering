CREATE DATABASE IF NOT EXISTS food_ordering;
USE food_ordering;

CREATE TABLE IF NOT EXISTS foods (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    category VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    available BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100),
    customer_email VARCHAR(150),
    total_amount DECIMAL(10,2),
    status VARCHAR(30),
    created_at DATETIME
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    food_id BIGINT NOT NULL,
    food_name VARCHAR(150),
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY(order_id) REFERENCES orders(id),
    FOREIGN KEY(food_id) REFERENCES foods(id)
);

INSERT INTO foods(name,category,price,available)
SELECT 'Chicken Biryani','Biryani',180.00,TRUE
WHERE NOT EXISTS (SELECT 1 FROM foods WHERE name='Chicken Biryani');

INSERT INTO foods(name,category,price,available)
SELECT 'Paneer Roll','Rolls',90.00,TRUE
WHERE NOT EXISTS (SELECT 1 FROM foods WHERE name='Paneer Roll');

INSERT INTO foods(name,category,price,available)
SELECT 'Veg Burger','Burgers',110.00,TRUE
WHERE NOT EXISTS (SELECT 1 FROM foods WHERE name='Veg Burger');

INSERT INTO foods(name,category,price,available)
SELECT 'Cold Coffee','Beverages',80.00,TRUE
WHERE NOT EXISTS (SELECT 1 FROM foods WHERE name='Cold Coffee');
