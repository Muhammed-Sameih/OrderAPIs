DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;

-- Create the Order table
CREATE TABLE orders (
                        order_id SERIAL PRIMARY KEY,
                        customer_id INTEGER,
                        coupon_id INTEGER,
                        order_date TIMESTAMP,
                        total_amount DECIMAL(10, 2),
                        status VARCHAR(20)
);

-- Create the OrderItem table
CREATE TABLE order_item (
                            order_item_id SERIAL PRIMARY KEY,
                            order_id INTEGER REFERENCES orders (order_id),
                            product_id INTEGER,
                            quantity INTEGER,
                            price DECIMAL(10, 2)
);
