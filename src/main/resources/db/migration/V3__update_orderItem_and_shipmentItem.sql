DROP TABLE IF EXISTS ORDER_ITEM;

DROP TABLE IF EXISTS SHIPMENT_ITEM;

-- Create the ORDER_ITEM table

CREATE TABLE ORDER_ITEM (
                            id SERIAL PRIMARY KEY,
                            order_id INTEGER,
                            product_code VARCHAR(255),
                            quantity INTEGER CHECK (quantity > 0),
                            price DECIMAL,
                            is_shipped BOOLEAN,
                            FOREIGN KEY (order_id) REFERENCES ORDERS (id)
);

CREATE TABLE SHIPMENT_ITEM (
                               id SERIAL PRIMARY KEY,
                               shipment_id INTEGER,
                               store_id INTEGER,
                               order_item_id INTEGER,
                               quantity INTEGER CHECK (quantity > 0),
                               FOREIGN KEY (shipment_id) REFERENCES SHIPMENT(id),
                               FOREIGN KEY (store_id) REFERENCES STORE(id),
                               FOREIGN KEY (order_item_id) REFERENCES ORDER_ITEM(id)
);