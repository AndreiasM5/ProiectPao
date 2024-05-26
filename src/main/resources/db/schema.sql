CREATE TABLE User (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    description TEXT,
    category VARCHAR(255),
    condition VARCHAR(255),
	discount DECIMAL(10, 2),
    limitedStock INT,
    discriminator VARCHAR(20)
);


INSERT INTO Product (name, price, description, category, condition, discriminator)
VALUES ('Regular Product', 20.00, 'Regular description', 'Regular category', 'Regular condition', 'PRODUCT');

INSERT INTO Product (name, price, description, category, condition, discount, discriminator)
VALUES ('Discounted Product', 15.00, 'Discounted description', 'Discounted category', 'Discounted condition', 10.00, 'DISCOUNTED_PRODUCT');

INSERT INTO Product (name, price, description, category, condition, limitedStock, discriminator)
VALUES ('Last Chance Product', 50.00, 'Last chance description', 'Last chance category', 'Last chance condition', 5, 'LAST_CHANCE_ITEM');

delete from product where discriminator ISNULL

