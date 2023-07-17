DROP table if exists "Order";

DROP table if exists "OrderItem";

-- Create the Order table
CREATE TABLE "Order" (
                         order_id SERIAL PRIMARY KEY,
                         customer_id INTEGER REFERENCES "User" (user_id),
                         coupon_id INTEGER, -- No foreign key constraint as Coupon belongs to a separate microservice
                         order_date TIMESTAMP,
                         total_amount DECIMAL(10, 2),
                         status VARCHAR(20)
);

-- Create the OrderItem table
CREATE TABLE OrderItem (
                           order_item_id SERIAL PRIMARY KEY,
                           order_id INTEGER REFERENCES "Order" (order_id),
                           product_id INTEGER, -- No foreign key constraint as Product belongs to a separate microservice
                           quantity INTEGER,
                           price DECIMAL(10, 2)
);
