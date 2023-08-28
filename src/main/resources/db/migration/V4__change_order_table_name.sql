DROP TABLE IF EXISTS "ORDER" CASCADE;

CREATE TABLE ORDERS (
    id SERIAL PRIMARY KEY,
    customer_email VARCHAR(100) NOT NULL CHECK (customer_email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    coupon_id INTEGER,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL,
    status VARCHAR,
    code VARCHAR,
    FOREIGN KEY (coupon_id) REFERENCES COUPON(id)
);