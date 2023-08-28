-- V1__Create_ORDER_ORDERITEM_COUPON_Tables

CREATE TABLE COUPON (
    id SERIAL PRIMARY KEY,
    coupon_code INT NOT NULL,
    coupon_Type VARCHAR,
    discount_value_or_percentage DECIMAL,
    CONSTRAINT non_negative_discount CHECK (discount_value_or_percentage >= 0)
);

CREATE TABLE "ORDER" (
    id SERIAL PRIMARY KEY,
    customer_email VARCHAR(100) NOT NULL CHECK (customer_email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    coupon_id INTEGER,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL,
    status VARCHAR,
    code VARCHAR,
    FOREIGN KEY (coupon_id) REFERENCES COUPON(id)
);

CREATE TABLE ORDER_ITEM (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES "ORDER" (id),
    product_code INT,
    quantity INT CHECK (ORDER_ITEM.quantity > 0),
    price DECIMAL CHECK (price >= 0),
    code VARCHAR
);


