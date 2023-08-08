-- V2__Remove_Store_Relation.sql

DROP TABLE IF EXISTS ORDER_ITEM;


CREATE TABLE ORDER_ITEM (
                            id SERIAL PRIMARY KEY,
                            order_id INTEGER,
                            product_code VARCHAR(255),
                            quantity INTEGER CHECK (quantity > 0),
                            price DECIMAL,
                            is_shipped BOOLEAN,
                            shipment_item_id INTEGER,
                            FOREIGN KEY (order_id) REFERENCES ORDERS (id),
                            FOREIGN KEY (shipment_item_id) REFERENCES SHIPMENT_ITEM(id)
);