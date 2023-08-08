-- Create the CUSTOMER table
CREATE TABLE CUSTOMER (
                          id SERIAL PRIMARY KEY,
                          email VARCHAR(100) UNIQUE NOT NULL CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
                          location VARCHAR(255) NOT NULL
);

-- Create the STORE table
CREATE TABLE STORE (
                       id SERIAL PRIMARY KEY,
                       store_code VARCHAR(255) NOT NULL UNIQUE,
                       location VARCHAR(255) NOT NULL
);

-- Create the COUPON table
CREATE TABLE COUPON (
                        id SERIAL PRIMARY KEY,
                        coupon_code VARCHAR(255) NOT NULL UNIQUE ,
                        coupon_Type VARCHAR(255) NOT NULL DEFAULT 'PERCENTAGE',
                        discount_value_or_percentage DECIMAL NOT NULL
);

-- Create the SHIPMENT table
CREATE TABLE SHIPMENT (
                          id SERIAL PRIMARY KEY,
                          customer_id INTEGER,
                          shipment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          status VARCHAR(255),
                          FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id)
);

-- Create the SHIPMENT_ITEM table
CREATE TABLE SHIPMENT_ITEM (
                               id SERIAL PRIMARY KEY,
                               shipment_id INTEGER,
                               store_id INTEGER,
                               FOREIGN KEY (shipment_id) REFERENCES SHIPMENT(id),
                               FOREIGN KEY (store_id) REFERENCES STORE(id)
);

DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;

-- Create the ORDER table
CREATE TABLE ORDERS (
                         id SERIAL PRIMARY KEY,
                         customer_id INTEGER,
                         coupon_id INTEGER, -- Added column for the coupon
                         order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         total_price DECIMAL ,
                         status VARCHAR(255),
                         FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id),
                         FOREIGN KEY (coupon_id) REFERENCES COUPON(id) -- Foreign key constraint to reference the coupon
);

-- Create the ORDER_ITEM table
CREATE TABLE ORDER_ITEM (
                            id SERIAL PRIMARY KEY,
                            order_id INTEGER,
                            product_code VARCHAR(255),
                            quantity INTEGER CHECK (quantity > 0),
                            price DECIMAL,
                            is_shipped BOOLEAN,
                            shipment_item_id INTEGER,
                            store_id INTEGER,
                            FOREIGN KEY (order_id) REFERENCES ORDERS (id),
                            FOREIGN KEY (shipment_item_id) REFERENCES SHIPMENT_ITEM(id),
                            FOREIGN KEY (store_id) REFERENCES STORE(id)
)




